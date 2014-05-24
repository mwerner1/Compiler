package edu.calpoly.mwerner.compiler;

import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;
import org.antlr.stringtemplate.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Mini
{
   public static void main(String[] args)
   {
      parseParameters(args);

      CommonTokenStream tokens = new CommonTokenStream(createLexer());
      MiniParser parser = new MiniParser(tokens);
      CommonTree tree = parse(parser);

      if (_displayAST && tree != null)
      {
         DOTTreeGenerator gen = new DOTTreeGenerator();
         StringTemplate st = gen.toDOT(tree);
         System.out.println(st);
      }
      else if (!parser.hasErrors())
      {
         // To use a different tree parser, modify the code within validate.
         validate(tree, tokens);
      }
   }

   private static final String DISPLAYAST = "-displayAST";
   private static final String DUMPIL = "-dumpIL";
   private static final String ASSEM = "-s";

   private static String _inputFile = null;
   private static boolean _displayAST = false;
   private static boolean _dumpIL = false;
   private static boolean _dumpASSEM = false;
   
   private static ArrayList<String> visitedNodes;
   
   public enum Language
   {
      ILOC, ASSEM
   }

   private static void parseParameters(String [] args)
   {
      for (int i = 0; i < args.length; i++)
      {
         if (args[i].equals(DISPLAYAST))
         {
            _displayAST = true;
         }
         else if (args[i].equals(DUMPIL))
         {
            _dumpIL = true;
         }
         else if (args[i].equals(ASSEM))
         {
        	 _dumpASSEM = true;
         }
         else if (args[i].charAt(0) == '-')
         {
            System.err.println("unexpected option: " + args[i]);
            System.exit(1);
         }
         else if (_inputFile != null)
         {
            System.err.println("too many files specified");
            System.exit(1);
         }
         else
         {
            _inputFile = args[i];
         }
      }
   }

   private static CommonTree parse(MiniParser parser)
   {
      try
      {
         MiniParser.program_return ret = parser.program();

         return (CommonTree)ret.getTree();
      }
      catch (org.antlr.runtime.RecognitionException e)
      {
         error(e.toString());
      }
      catch (Exception e)
      {
         System.exit(-1);
      }

      return null;
   }
  
   private static void walkCFG(BasicBlock blk, PrintWriter out, Language lang)
   {
	   //Base Case
	   if (blk.numSuccessors() == 0)
	   {
		   if (!visitedNodes.contains(blk.getLabel()))
		   {
			   out.println(blk.getLabel() + ":");
			   visitedNodes.add(blk.getLabel());
			   
			   if (lang == Language.ILOC)
			   {
				   for (int i=0; i<blk.numInstructions(); i++)
				   {
					   out.println("\t" + blk.getInstr(i).toString());  
				   }
			   }
			   else if (lang == Language.ASSEM)
			   {
				   for (int i=0; i<blk.numAssemInstr(); i++)
				   {
					   out.println("\t" + blk.getAssembly(i).toString());
				   }
			   }
		   }
	   }
	   // Block has successors
	   else
	   {
		   if (!visitedNodes.contains(blk.getLabel())) 
		   {
			   out.println(blk.getLabel() + ":");
			   
			   if (lang == Language.ILOC)
			   {
				   for (int j=0; j<blk.numInstructions(); j++)
				   {
					   out.println("\t" + blk.getInstr(j).toString());  
				   }
			   }
			   else if(lang == Language.ASSEM)
			   { 
				   for (int j=0; j<blk.numAssemInstr(); j++)
				   {
					   out.println("\t" + blk.getAssembly(j).toString());
				   }
			   }
			   
			   visitedNodes.add(blk.getLabel());
			   
			   
			   for (int i=0; i<blk.numSuccessors(); i++)
			   {
				   if (!visitedNodes.contains(blk.getSuccessor(i).getLabel()))
				   {
					   walkCFG(blk.getSuccessor(i), out, lang);
				   }
			   }
		   }
	   }
   }

   private static void validate(CommonTree tree, CommonTokenStream tokens)
   {
      try
      {
    	 CommonTreeNodeStream nodes = new CommonTreeNodeStream(tree);
         nodes.setTokenStream(tokens);
         
         // Walk AST and perform type-checking
         TypeCheck tparser = new TypeCheck(nodes);
         tparser.program();
         
         nodes = new CommonTreeNodeStream(tree);
         nodes.setTokenStream(tokens);
         
         //Walk AST and create CFG/ILOC
         ControlFlow cfg = new ControlFlow(nodes);
         if (_dumpASSEM)
         {
        	 cfg.genAssem(true);
         }
         else
         {
        	 cfg.genAssem(false);
         }
         
         HashMap<String, BasicBlock> cfgs = cfg.program();
         
         if (_dumpIL)
         {
        	 String outFileName = _inputFile.replace(".mini", ".il");
        	 
        	 Iterator<String> iterator = cfgs.keySet().iterator();
        	 
        	 try
        	 {
        		 PrintWriter out = new PrintWriter(outFileName);
        	 
	             while(iterator.hasNext()) 
	             {
	    			BasicBlock funcCFG = cfgs.get(iterator.next());
	    			
	    			visitedNodes = new ArrayList<String>();
		    		walkCFG(funcCFG, out, Language.ILOC);			
	             }
	             
	             out.close();
        	 }
        	 catch(IOException e)
        	 {
        		 e.printStackTrace();
        	 }
         }
         
         if (_dumpASSEM)
         {
        	 String assemOutFile = _inputFile.replace(".mini", ".s");
        	 
        	 HashMap<String, Type> globals = cfg.getGlobals();
    		 
    		 Iterator<String> itr = globals.keySet().iterator();
        	 
        	 Iterator<String> iterator = cfgs.keySet().iterator();
        	 
        	 try
        	 {
        		 PrintWriter assemOut = new PrintWriter(assemOutFile);
        		 
        		 while (itr.hasNext())
        		 {
        		
        			 assemOut.println(".comm glob_" + itr.next().toString() + ", 8, 8");
        		 }
        		 
        		 while(iterator.hasNext())
        		 {
        			 BasicBlock funcCFG = cfgs.get(iterator.next());
        			 
        			 visitedNodes = new ArrayList<String>();
        			 
        			 assemOut.println(".text");
					 assemOut.println(".global " + funcCFG.getLabel());
					 assemOut.println(".type " + funcCFG.getLabel() + ", @function");
        			 
        			 walkCFG(funcCFG, assemOut, Language.ASSEM);
        			 
        			 assemOut.println(".size\t" + funcCFG.getLabel() + ", .-" + funcCFG.getLabel());
        		 }
        		 
        		 assemOut.close();
        	 }
        	 catch(IOException e)
        	 {
        		 e.printStackTrace();
        	 }
         }
      }
      catch (org.antlr.runtime.RecognitionException e)
      {
         error(e.toString());
      }
   }

   private static void error(String msg)
   {
      System.err.println(msg);
      System.exit(1);
   }

   private static MiniLexer createLexer()
   {
      try
      {
         ANTLRInputStream input;
         if (_inputFile == null)
         {
            input = new ANTLRInputStream(System.in);
         }
         else
         {
            input = new ANTLRInputStream(
               new BufferedInputStream(new FileInputStream(_inputFile)));
         }
         return new MiniLexer(input);
      }
      catch (java.io.IOException e)
      {
         System.err.println("file not found: " + _inputFile);
         System.exit(1);
         return null;
      }
   }
}
