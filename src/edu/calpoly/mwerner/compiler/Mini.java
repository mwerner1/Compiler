package edu.calpoly.mwerner.compiler;

import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;
import org.antlr.stringtemplate.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;

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
   
   private static boolean changeFlag;
   
   private static ArrayList<String> visitedNodes;
   private static HashMap<String, BasicBlock> visitedBlocks;
   
   private static HashMap<String, Vector<Register>> genSets = new HashMap<String, Vector<Register>>();
   private static HashMap<String, Vector<Register>> killSets = new HashMap<String, Vector<Register>>();
   private static HashMap<String, Vector<Register>> currLO;
   private static HashMap<String, Vector<Register>> updatedLO;
   private static HashMap<String, Vector<Register>> prevLO;
   
   private static HashMap<String, HashMap<String, Node>> intGraphs = new HashMap<String, HashMap<String, Node>>();
   
   private static List<String> colors = Arrays.asList("%rax", "%rbx", "%rcx", "%rdx", "%rsi", "%rdi", 
	   "%r8", "%r9", "%r10", "%r11", "%r12", "%r13");
   
   private static List<String> spill = Arrays.asList("%r14", "%r15");
   
   private static ArrayList<String> spillArrs;
   
   
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
//			   out.println(blk.getLabel() + ":");
			   
			   visitedNodes.add(blk.getLabel());
			   visitedBlocks.put(blk.getLabel(), blk);
			   
			   genSets.put(blk.getLabel(), new Vector<Register>());
			   killSets.put(blk.getLabel(), new Vector<Register>());
			   
			   if (lang == Language.ILOC)
			   {
				   out.println(blk.getLabel() + ":");
				   for (int i=0; i<blk.numInstructions(); i++)
				   {
					   out.println("\t" + blk.getInstr(i).toString());  
				   }
			   }
			   else if (lang == Language.ASSEM)
			   {  
				   for (int i=0; i<blk.numAssemInstr(); i++)
				   {
					   Instruction currInstr = blk.getAssembly(i);
					   
					   Vector<Register> srcSet = currInstr.getSrc();
					   Vector<Register> targetSet = currInstr.getTarget();
					   
					   if (srcSet != null)
					   {
						   for (int j=0; j<srcSet.size(); j++)
						   {
							   if (!(killSets.get(blk.getLabel()).contains(srcSet.get(j))))
							   {
								   if (!(genSets.get(blk.getLabel()).contains(srcSet.get(j))))
								   {
									   genSets.get(blk.getLabel()).add(srcSet.get(j));
								   }
							   }
						   }
					   }
					   
					   if (targetSet != null)
					   {
						   for (int j=0; j<targetSet.size(); j++)
						   {
							   if (!(killSets.get(blk.getLabel()).contains(targetSet.get(j))))
							   {
								   killSets.get(blk.getLabel()).add(targetSet.get(j));
							   }
						   }
					   }
					   
//					   out.println("\t" + blk.getAssembly(i).toString());
				   }
				   
//				   System.out.println(blk.getLabel() + ": ");
//				   System.out.println("Gen Set: " + genSets.get(blk.getLabel()));
//				   System.out.println("Kill Set: " + killSets.get(blk.getLabel()));
			   }
		   }
	   }
	   // Block has successors
	   else
	   {
		   if (!visitedNodes.contains(blk.getLabel())) 
		   {
//			   out.println(blk.getLabel() + ":");
			   
			   genSets.put(blk.getLabel(), new Vector<Register>());
			   killSets.put(blk.getLabel(), new Vector<Register>());
			   
			   if (lang == Language.ILOC)
			   {
				   out.println(blk.getLabel() + ":");
				   for (int j=0; j<blk.numInstructions(); j++)
				   {
					   out.println("\t" + blk.getInstr(j).toString());  
				   }
			   }
			   else if(lang == Language.ASSEM)
			   {  
				   for (int j=0; j<blk.numAssemInstr(); j++)
				   {
					   Instruction currInstr = blk.getAssembly(j);
					   
					   Vector<Register> srcSet = currInstr.getSrc();
					   Vector<Register> targetSet = currInstr.getTarget();
					   
					   if (srcSet != null)
					   {
						   for (int k=0; k<srcSet.size(); k++)
						   {
							   if (!(killSets.get(blk.getLabel()).contains(srcSet.get(k))))
							   {
								   if (!(genSets.get(blk.getLabel()).contains(srcSet.get(k))))
								   {
									   genSets.get(blk.getLabel()).add(srcSet.get(k));
								   }
							   }
						   }
					   }
					   
					   if (targetSet != null)
					   {
						   for (int k=0; k<targetSet.size(); k++)
						   {
							   if (!(killSets.get(blk.getLabel()).contains(targetSet.get(k))))
							   {
								   killSets.get(blk.getLabel()).add(targetSet.get(k));
							   }
						   }
					   }
					   
//					   out.println("\t" + blk.getAssembly(j).toString());
				   }
				   
//				   System.out.println(blk.getLabel() + ": ");
//				   System.out.println("Gen Set: " + genSets.get(blk.getLabel()));
//				   System.out.println("Kill Set: " + killSets.get(blk.getLabel()));
			   }
			   
			   visitedNodes.add(blk.getLabel());
			   visitedBlocks.put(blk.getLabel(), blk);
			   
			   
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
   
   private static void swapRegisters(BasicBlock blk, PrintWriter assemOut, String funcLabel)
   {
	   //Base Case
	   if (blk.numSuccessors() == 0)
	   {
		   boolean targetSpill;
		   boolean srcSpill;
		   int spillIndex;
		   
		   if (!visitedNodes.contains(blk.getLabel()))
		   {
			   assemOut.println(blk.getLabel() + ":");
			   visitedNodes.add(blk.getLabel());
			   visitedBlocks.put(blk.getLabel(), blk);
			   	    
			   for (int i=0; i<blk.numAssemInstr(); i++)
			   {
				   srcSpill = false;
				   Instruction currInstr = blk.getAssembly(i);
				   
				   Vector<Register> srcSet = currInstr.getSrc();
				   Vector<Register> targetSet = currInstr.getTarget();
				   
				   targetSpill = false;
				   spillIndex = -1;
				   
				   if (srcSet != null)
				   {
					   for (int j=0; j<srcSet.size(); j++)
					   {
						   Register currReg = srcSet.get(j);
						   
						   String color = intGraphs.get(funcLabel).get(currReg.getRegStr()).getColor();
						   
						   if (color.equals("Spill"))
						   {
							   int offset = -8 * (spillArrs.indexOf(currReg.getRegStr()) + 1);
							   
							   Movq movq;
							   
							   if (!srcSpill)
							   {
								   movq = new Movq(new Immediate(offset), new Register("%rbp"), new Register(spill.get(0)));
								   
//							   	   blk.addAssembly(movq, i);
							   
								   currReg.setColor(spill.get(0));
								   srcSpill = true;
							   }
							   else
							   {
								   movq = new Movq(new Immediate(offset), new Register("%rbp"), new Register(spill.get(1)));
								   
								   currReg.setColor(spill.get(1));
							   }
							   
							   assemOut.println("\t" + movq.toString());
						   }
						   else
						   {
							   currReg.setColor(color);
						   }
//						   
//						   currReg = new Register(color);
//						   System.out.println("Bar: " + currReg.toString());
					   }
				   }
				   
				   if (targetSet != null)
				   {
					   for (int j=0; j<targetSet.size(); j++)
					   {
						   Register currReg = targetSet.get(j);
						   
						   String color = intGraphs.get(funcLabel).get(currReg.getRegStr()).getColor();
						   
						   if (color.equals("Spill"))
						   {
							   targetSpill = true;
							   
							   spillIndex = spillArrs.indexOf(currReg.getRegStr());
							   
							   currReg.setColor(spill.get(1));
						   }
						   else
						   {
							   currReg.setColor(color);
						   }
					   }
				   }
				   
				   assemOut.println("\t" + blk.getAssembly(i).toString());
				   
				   if (targetSpill)
				   {
					   int offset = -8 * (spillIndex + 1);
					   Movq movq2 = new Movq(new Register(spill.get(1)), new Immediate(offset), new Register("%rbp"));
					   assemOut.println("\t" + movq2.toString());
//					   blk.addAssembly(movq, i);
				   }
			   }
		   }
	   }
	   // Block has successors
	   else
	   {
		   boolean srcSpill;
		   boolean targetSpill;
		   int spillIndex;
		   
		   if (!visitedNodes.contains(blk.getLabel())) 
		   {  
			   assemOut.println(blk.getLabel() + ":");
			   
			   for (int j=0; j<blk.numAssemInstr(); j++)
			   {
				   srcSpill = false;
				   Instruction currInstr = blk.getAssembly(j);
				   
				   Vector<Register> srcSet = currInstr.getSrc();
				   Vector<Register> targetSet = currInstr.getTarget();
				   
				   targetSpill = false;
				   spillIndex = -1;
				   
				   if (srcSet != null)
				   {
					   for (int k=0; k<srcSet.size(); k++)
					   {
						   Register currReg = srcSet.get(k);
						   
						   String color = intGraphs.get(funcLabel).get(currReg.getRegStr()).getColor();
						   
						   if (color.equals("Spill"))
						   {
							   int offset = -8 * (spillArrs.indexOf(currReg.getRegStr()) + 1);
							   
							   Movq movq;
							   
							   if (!srcSpill)
							   {
								   movq = new Movq(new Immediate(offset), new Register("%rbp"), new Register(spill.get(0)));
								   
	//							   blk.addAssembly(movq, j);
								   
								   currReg.setColor(spill.get(0));
								   srcSpill = true;
							   }
							   else
							   {
								   movq = new Movq(new Immediate(offset), new Register("%rbp"), new Register(spill.get(1)));
								   
								   currReg.setColor(spill.get(1));
							   }
							   
							   assemOut.println("\t" + movq.toString());
						   }
						   else
						   {
							   currReg.setColor(color);
						   }
						   
//						   currReg = new Register(color);
//						   System.out.println("Bar: " + currReg.toString());
					   }
				   }
				   
				   if (targetSet != null)
				   {
					   for (int k=0; k<targetSet.size(); k++)
					   {
						   Register currReg = targetSet.get(k);
						   
						   String color = intGraphs.get(funcLabel).get(currReg.getRegStr()).getColor();
						   
						   if (color.equals("Spill"))
						   {
							   targetSpill = true;
							   
							   spillIndex = spillArrs.indexOf(currReg.getRegStr());
							   
							   currReg.setColor(spill.get(1));
						   }
						   else
						   {
							   currReg.setColor(color);
						   }
					   }
				   }
				   
				   assemOut.println("\t" + blk.getAssembly(j).toString());
				   
				   if (targetSpill)
				   {
					   int offset = -8 * (spillIndex + 1);
					   Movq movq2 = new Movq(new Register(spill.get(1)), new Immediate(offset), new Register("%rbp"));
					   assemOut.println("\t" + movq2.toString());
//					   blk.addAssembly(movq, i);
				   }
			   }				  
			   
			   visitedNodes.add(blk.getLabel());
			   visitedBlocks.put(blk.getLabel(), blk);
			   
			   
			   for (int i=0; i<blk.numSuccessors(); i++)
			   {
				   if (!visitedNodes.contains(blk.getSuccessor(i).getLabel()))
				   {
					   swapRegisters(blk.getSuccessor(i), assemOut, funcLabel);
				   }
			   }
		   }
	   }
   }
   
   private static Vector<Register> computeLiveOut(BasicBlock blk)
   {
	   Vector<Register> union = new Vector<Register>();
	   
	   //Union of all successor blocks
	   for (int i=0; i<blk.numSuccessors(); i++)
	   {
		   BasicBlock successor = blk.getSuccessor(i);
		   
//		   Vector<Register> succLO = currLO.get(successor.getLabel());
		   Vector<Register> succLO = prevLO.get(successor.getLabel());
		   
		   System.out.println(blk.getLabel() + " successor " + successor.getLabel() + ": " + succLO.size());
		   Vector<Register> gen = genSets.get(successor.getLabel());
		   Vector<Register> kill = killSets.get(successor.getLabel());
		  	   
		   //gen(m)
		   for (int j=0; j<gen.size(); j++)
		   {
			   if (!union.contains(gen.get(j)))
			   {
				   union.add(gen.get(j));
			   }  
		   }
		   
		   // U (LiveOut(m) - kill(m))
		   for (int j=0; j<succLO.size(); j++)
		   {
			   if (!kill.contains(succLO.get(j)))
			   {
				   if (!union.contains(succLO.get(j)))
				   {
					   union.add(succLO.get(j));
				   }
			   }
		   } 
	   }
	   
	   return union;
   }
   
   private static void addToIntGraph(String funcLabel, String currBlkLbl, BasicBlock currBlock)
   {
	   Vector<Register> liveNow = new Vector<Register>();
	   
	   for (int i=0; i< currLO.get(currBlkLbl).size(); i++)
	   {
		   liveNow.add(currLO.get(currBlkLbl).get(i));
	   }
	   
	   for (int i=currBlock.numAssemInstr()-1; i>=0; i--)
	   {
		   Vector<Register> targets = currBlock.getAssembly(i).getTarget();
		   Vector<Register> sources = currBlock.getAssembly(i).getSrc();
		   
		   if (targets != null)
		   { 
			   for (int j=0; j<targets.size(); j++)
			   {
				   Node node;
				   
				   if (!(intGraphs.get(funcLabel).containsKey(targets.get(j).toString())))
				   {
					   node = new Node(targets.get(j).toString());
					   intGraphs.get(funcLabel).put(targets.get(j).toString(), node);
				   }
				   else
				   {
					   node = intGraphs.get(funcLabel).get(targets.get(j).toString());
				   }
				   
				   for (int k=0; k<liveNow.size(); k++)
				   {
					   Node node2;
					   
					   if (!(intGraphs.get(funcLabel).containsKey(liveNow.get(k).toString())))
					   {
						   node2 = new Node(liveNow.get(k).toString());
						   intGraphs.get(funcLabel).put(liveNow.get(k).toString(), node2);
					   }
					   else
					   {
						   node2 = intGraphs.get(funcLabel).get(liveNow.get(k).toString());
					   }
					   
					   if ((!node.isNeighbor(node2)) && (!node.getLabel().equals(node2.getLabel())))
					   {
						   node.addNeighbor(node2);
					   }
					   
					   if ((!node2.isNeighbor(node)) && (!node2.getLabel().equals(node.getLabel())))
					   {
						   node2.addNeighbor(node);
					   }
				   }
			   }
			   
			   for (int j=0; j<targets.size(); j++)
			   {
				   int index = liveNow.indexOf(targets.get(j));
				   
				   if (index != -1)
				   {
					   liveNow.remove(index);
				   }
			   }
		   }
		   
		   if (sources != null)
		   {
			   for (int j=0; j<sources.size(); j++)
			   {
				   if (!liveNow.contains(sources.get(j)))
				   {
					   liveNow.add(sources.get(j));
				   }
			   }
		   }
	   }
   }
   
   private static void printGraph(String funcLabel)
   {
	   System.out.println(funcLabel + " Interference Graph:");
	   
	   Iterator<String> itr = intGraphs.get(funcLabel).keySet().iterator();
	   
	   while (itr.hasNext())
	   {
		   String lbl = itr.next();
		   
		   System.out.print("Node " + lbl + ": ");
		   ArrayList<Node> neighbors = intGraphs.get(funcLabel).get(lbl).getNeighbors();
		   
		   for (int i=0; i<neighbors.size(); i++)
		   {
			   if (i < neighbors.size()-1)
			   {
				   System.out.print(neighbors.get(i).getLabel() + ", ");
			   }
			   else
			   {
				   System.out.println(neighbors.get(i).getLabel() + " | Color: " + intGraphs.get(funcLabel).get(lbl).getColor());
			   }
		   }
		   
		   if (neighbors.size() == 0)
		   {
			   System.out.println();
		   }
	   }
   }
   
   // De-constructs graph
   private static Stack<Node> deconstructGraph(String funcLabel)
   {
	   Stack<Node> stack = new Stack<Node>();
	   
	   // While graph not empty
	   while (intGraphs.get(funcLabel).keySet().size() > 0)
	   {
		   boolean foundUncnstr = false;
		   boolean foundCnstr = false;
		   Iterator<String> itr = intGraphs.get(funcLabel).keySet().iterator();
		   
		   while (itr.hasNext())
		   {
			   String nodeStr = itr.next();
			   Node currNode = intGraphs.get(funcLabel).get(nodeStr);
			  
			   // If node is not already a real register and is unconstrained
			   if ((!colors.contains(nodeStr)) && (!spill.contains(nodeStr)) && 
					   (!(nodeStr.equals("%rip") || nodeStr.equals("%rsp") || nodeStr.equals("%rbp"))) && 
							   (currNode.getNeighbors().size() < colors.size()))
			   {
				   stack.push(currNode);
				   
				   // Remove node and its edges from graph
				   for (int i=0; i<currNode.getNeighbors().size(); i++)
				   {
					   currNode.getNeighbors().get(i).removeNeighbor(currNode);
				   }
				   
				   //intGraphs.get(funcLabel).remove(nodeStr);
				   itr.remove();
				   
				   // Unconstrained node found, set flag to true
				   foundUncnstr = true;
			   }
		   }
		   
		   // No unconstrained nodes found, look for constrained node based on heuristic
		   if ((!foundUncnstr) && (intGraphs.get(funcLabel).keySet().size() > 0))
		   {
			   int highDeg = 0;
			   Node highDegNode = null;
			   
			   itr = intGraphs.get(funcLabel).keySet().iterator();
			   // Loop over entire graph looking for the "best" node based on heuristic
			   while (itr.hasNext())
			   {
				   String nodeStr = itr.next();
				   Node currNode = intGraphs.get(funcLabel).get(nodeStr);
				   
				   // If node is not already a real register and is a better fit based on heuristic
				   if ((!colors.contains(nodeStr)) && (!spill.contains(nodeStr)) && 
						   (!(nodeStr.equals("%rip") || nodeStr.equals("%rsp") || nodeStr.equals("%rbp"))) && 
						   (currNode.getNeighbors().size() > highDeg))
				   {
					   highDeg = currNode.getNeighbors().size();
					   highDegNode = currNode;
					   
					   foundCnstr = true;
				   }
			   }
			   
			   // Push constrained node to stack and remove from graph
			   if (foundCnstr)
			   {
				   // This is just an extra saftey check
				   if (highDegNode != null)
				   {
					   stack.push(highDegNode);
					   
					   // Remove node and its edges from graph
					   for (int i=0; i<highDegNode.getNeighbors().size(); i++)
					   {
						   highDegNode.getNeighbors().get(i).removeNeighbor(highDegNode);
					   }
					   
					   intGraphs.get(funcLabel).remove(highDegNode.getLabel());
				   }
			   }
		   }
		   
		   // No constrained nodes found, only real registers must remain
		   if ((!foundUncnstr) && (!foundCnstr) && (intGraphs.get(funcLabel).keySet().size() > 0))
		   {
			   itr = intGraphs.get(funcLabel).keySet().iterator();
			   
			   while (itr.hasNext())
			   {
				   String nodeStr = itr.next();
				   Node currNode = intGraphs.get(funcLabel).get(nodeStr);
	
				   // Just an extra safety check to ensure node is already a real register
				   if (colors.contains(nodeStr) || spill.contains(nodeStr) || nodeStr.equals("%rip") || 
						   nodeStr.equals("%rsp") || nodeStr.equals("%rbp"))
				   {
					   stack.push(currNode);
					   
					   // Remove node and its edges from graph
					   for (int i=0; i<currNode.getNeighbors().size(); i++)
					   {
						   currNode.getNeighbors().get(i).removeNeighbor(currNode);
					   }
					   
					   //intGraphs.get(funcLabel).remove(nodeStr);
					   itr.remove();
				   }
			   }
		   }
	   }
	   
	   System.out.println(funcLabel + "'s Final Stack: ");
//	   if (funcLabel.equals("foo"))
//	   {
//		   while (!stack.empty())
//		   {
//			   Node node = stack.pop();
//			   System.out.print(node.getLabel() + ": ");
//			   
//			   for (int i=0; i<node.getNeighbors().size(); i++)
//			   {
//				   System.out.print(node.getNeighbors().get(i).getLabel() + ", ");
//			   }
//			   
//			   System.out.println();
//		   }
//	   }
	   for (int i=0; i<stack.size(); i++)
	   {
		   Node nd = stack.get(i);
		   System.out.print(nd.getLabel() + ": ");
		   
		   for (int j=0; j<nd.getNeighbors().size(); j++)
		   {
			   System.out.print(nd.getNeighbors().get(j).getLabel() + ", ");
		   }
		   
		   System.out.println();
	   }
	   System.out.println("--------");
	   
	   return stack;
   }
   
   // Assigns colors and reconstructs graph
   private static void colorGraph(String funcLabel, Stack<Node> stack)
   {  
	   spillArrs = new ArrayList<String>();
	   // Pop nodes off stack, assigning colors as we do so and rebuild graph
	   while (!stack.empty())
	   {
		   Node node = stack.pop();
		   
		   // Node is already real register
		   if (colors.contains(node.getLabel()) || spill.contains(node.getLabel()) || node.getLabel().equals("%rip")
				   || node.getLabel().equals("%rsp") || node.getLabel().equals("%rbp"))
		   {
			   node.setColor(node.getLabel());
		   }
		   // Else node is fake register
		   else
		   {
			   boolean colorFound = true;
			   // Loop through all colors looking for a color not used by a neighboring node
			   for (String color : colors)
			   {
				   colorFound = true;
				   
				   // Loop through neighbors and check if current color is already used
				   for (int i=0; i<node.getNeighbors().size(); i++)
				   {
					   Node neighbor = node.getNeighbors().get(i);
					   
					   // Color is being used.  Set flag and break loop
					   if (neighbor.getColor().equals(color))
					   {
						   colorFound = false;
						   break;
					   }
				   }
				   
				   // Found a color not being used by a neighbor, break out of loop
				   if (colorFound)
				   {
					   node.setColor(color);
					   
					   break;
				   }
			   }
			   
			   // No colors available. Spill
			   if (!colorFound)
			   {
				   node.setColor("Spill");
				   
				   if (!spillArrs.contains(node.getLabel()))
				   {
					   spillArrs.add(node.getLabel());
				   }
			   }
		   }
		   
		   // Add edges back to node's neighbors
		   for (int i=0; i<node.getNeighbors().size(); i++)
		   {
			   node.getNeighbors().get(i).addNeighbor(node);
		   }
		   
		   // Add node back to graph
		   intGraphs.get(funcLabel).put(node.getLabel(), node);
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
	    			visitedBlocks = new HashMap<String, BasicBlock>();
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
        	 
        	 HashMap<String, Type> globals;
        	 if (cfg.getGlobals() == null)
        	 {
        		 globals = new HashMap<String, Type>();
        	 }
        	 else
        	 {
        		 globals = cfg.getGlobals();
        	 }
        	 
        	 
    		 Iterator<String> itr = globals.keySet().iterator();
        	 
        	 Iterator<String> iterator = cfgs.keySet().iterator();
        	 
        	 try
        	 {
        		 PrintWriter assemOut = new PrintWriter(assemOutFile);
        		 
        		 while (itr.hasNext())
        		 {
        		
        			 assemOut.println(".comm glob_" + itr.next().toString() + ", 8, 8");
        		 }
        		 
        		 assemOut.println(".comm glob_rd, 8, 8");
        		 
        		 assemOut.println(".section .rodata");
        		 assemOut.println(".LLC0:");
        		 assemOut.println("\t.string \"%ld\\n\"");
        		 assemOut.println(".LLC1:");
        		 assemOut.println("\t.string \"%ld \"");
        		 assemOut.println(".LLC2:");
        		 assemOut.println("\t.string \"%ld\"");
        		 
        		 while(iterator.hasNext())
        		 {
        			 currLO = new HashMap<String, Vector<Register>>();
        			 updatedLO = new HashMap<String, Vector<Register>>();
        			 prevLO = new HashMap<String, Vector<Register>>();
        			 
        			 BasicBlock funcCFG = cfgs.get(iterator.next());
        			 
        			 intGraphs.put(funcCFG.getLabel(), new HashMap<String, Node>());
        			 
        			 visitedNodes = new ArrayList<String>();
        			 visitedBlocks = new HashMap<String, BasicBlock>();
        			 
        			 assemOut.println(".text");
					 assemOut.println(".global " + funcCFG.getLabel());
					 assemOut.println(".type " + funcCFG.getLabel() + ", @function");
        			 
        			 walkCFG(funcCFG, assemOut, Language.ASSEM);
        			 
        			 for (int i=0; i<visitedNodes.size(); i++)
        			 {
        				 System.out.println(visitedNodes.get(i) + " gen set: ");
        				 
        				 for (int j=0; j<genSets.get(visitedNodes.get(i)).size(); j++)
        				 {
        					 System.out.print(genSets.get(visitedNodes.get(i)).get(j).getRegStr() + ", ");
        				 }
        				 System.out.println();
        				 
        				 System.out.println(visitedNodes.get(i) + " kill set: ");
        				 
        				 for (int j=0; j<killSets.get(visitedNodes.get(i)).size(); j++)
        				 {
        					 System.out.print(killSets.get(visitedNodes.get(i)).get(j).getRegStr() + ", ");
        				 }
        				 System.out.println();
        				 
        			 }
        			 
        			 //Initialize LiveOuts to empty sets
        			 for (int i=0; i<visitedNodes.size(); i++)
        			 {
        				 currLO.put(visitedNodes.get(i), new Vector<Register>());
        				 prevLO.put(visitedNodes.get(i), new Vector<Register>());
        			 }
        			 		 
        			 //Repeat until no set changes
        			 do
        			 {
        				 Iterator<String> loItr = currLO.keySet().iterator();
        				 
        				 changeFlag = false;
        				 
        				 //For each node in CFG
        				 while (loItr.hasNext())
        				 {
        					 String blkLabel = loItr.next();
        					 
        					 System.out.println("Order: " + blkLabel);
        					 
        					 updatedLO.put(blkLabel, currLO.get(blkLabel));
        					  
        					 Vector<Register> set = computeLiveOut(visitedBlocks.get(blkLabel));
        					 
        					 // *************MAY HAVE ISSUE HERE since blocks updated in arbitrary order, currLO might be updated
        					 // before successor LO calculated.  computeLiveOut uses currLO!!!!
        					 currLO.put(blkLabel, set);
        					 
        					 //If sizes of sets are different, then set flag to repeat
        					 if (updatedLO.get(blkLabel).size() != currLO.get(blkLabel).size())
        					 {
        						 changeFlag = true;
        					 }
        				 }
        				 
        				 for (int i=0; i<visitedNodes.size(); i++)
        				 {
        					 prevLO.put(visitedNodes.get(i), currLO.get(visitedNodes.get(i)));
        				 }
        			 } while(changeFlag);
        			 
        			 Iterator<String> finalItr = currLO.keySet().iterator();
        			 
        			 System.out.println("--------");
        			 System.out.println("LiveOuts: ");
        			 
        			 while (finalItr.hasNext())
        			 {
        				 String lbl = finalItr.next();
        				 BasicBlock currBlock = visitedBlocks.get(lbl);
        				 
        				 
        				 addToIntGraph(funcCFG.getLabel(), lbl, currBlock);
        				 
        				 System.out.println(lbl + " LiveOut: " + currLO.get(lbl));
        			 }
        			 
        			 
//        			 System.out.println("--------");
//        			 
//        			 printGraph(funcCFG.getLabel());
//        			 
//        			 System.out.println("--------");
//        			 System.out.println("--------");
        			 
        			 
        			 Stack<Node> stack = deconstructGraph(funcCFG.getLabel());
        			 colorGraph(funcCFG.getLabel(), stack);
        			 
        			 for (int i=0; i<spillArrs.size(); i++)
        			 {
        				 System.out.println("Spill array -> " + spillArrs.get(i));
        			 }
        			 
        			 System.out.println("Max number of extra args for " + funcCFG.getLabel() + ": " + cfg.getMaxArgs(funcCFG.getLabel()));
        			 
        			 int maxNumArgs = cfg.getMaxArgs(funcCFG.getLabel());
        			 
        			 BasicBlock currExitBlk = cfg.getExitBlock(funcCFG.getLabel());
        			 
        			 Subq subq = new Subq(new Immediate(8*(maxNumArgs + spillArrs.size())), new Register("%rsp"));
        			 funcCFG.addAssembly(subq, 2);
		 			
        			 Addq addq = new Addq(new Immediate(8*(maxNumArgs + spillArrs.size())), new Register("%rsp"));
		 			
//        			 if (currExitBlk.numAssemInstr() > 8)
//        			 {
//        				 currExitBlk.addAssembly(addq, currExitBlk.numAssemInstr()-8);
//        			 }
//        			 else
//        			 {
//        				 currExitBlk.addAssembly(addq, 0);
//        			 }
        			 
        			 currExitBlk.addAssembly(addq, currExitBlk.numAssemInstr()-3);
        			 
        			 visitedNodes = new ArrayList<String>();
        			 visitedBlocks = new HashMap<String, BasicBlock>();
        			 swapRegisters(funcCFG, assemOut, funcCFG.getLabel());
        			 
        			 printGraph(funcCFG.getLabel());
        			 
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
