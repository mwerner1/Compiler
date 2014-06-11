tree grammar ControlFlow;

options
{
   tokenVocab=Mini;
   ASTLabelType=CommonTree;
}

@header
{
   package edu.calpoly.mwerner.compiler;

   import java.util.Map;
   import java.util.HashMap;
   import java.util.Iterator;
   import java.util.Vector;
}
@members
{  
   //Hashmap of declared structs and the names and types of its variables
   StructTypes sTypes = new StructTypes();
   
   //Hashmap of declared structs
   HashMap<String, Struct> structs = new HashMap<String, Struct>();
   
   //Hashmap of structs and a list of the names of the variables they contain
   HashMap<String, Vector<String>> structVarNames = new HashMap<String, Vector<String>>();
   
   //Symbol table
   SymbolTable sTable = new SymbolTable();
   
   //Hashmap of functions
   HashMap<String, Func> funcs = new HashMap<String, Func>();
   
   //Hashmap of CFGs
   HashMap<String, BasicBlock> cfgMap = new HashMap<String, BasicBlock>();
   
   //Hashmap mapping local vars to registers
   HashMap<String, Register> localRegMap = new HashMap<String, Register>();
   
   //Hashmap mapping params to to registers
   HashMap<String, Register> paramRegMap = new HashMap<String, Register>();
   
   //Hashmap mapping functions to max number of args
   HashMap<String, Integer> maxArgsMap = new HashMap<String, Integer>();
   
   //Hashmap mapping functions to their exit blocks
   HashMap<String, BasicBlock> exitBlocks = new HashMap<String, BasicBlock>();
   
   //Counter for creating block labels
   int labelCounter, regCounter, argOffsetCtr;
   
   //Stores the current block
   BasicBlock currBlock;
   
   //Stores the exit block
   BasicBlock exitBlock;
   
   //Boolean flag that determines whether or not to generate assembly instructions
   boolean genAssem;
   
   int maxNumArgs = 0;
   
   public enum ValCategory
   {
      STRUCT, PARAMS
   }
   
   //Error method
   private void error(int line, String message)
   {
      System.err.println(line + ": " + message);
      System.exit(-1);
   }
   
   // Push callee-saved registers
   private void calleeSavePush(BasicBlock currBlock)
   {
      Pushq pushq = new Pushq(new Register("\%rbx"));
//      Pushq pushq2 = new Pushq(new Register("\%rsp"));
//	  Pushq pushq3 = new Pushq(new Register("\%rbp"));
	  Pushq pushq4 = new Pushq(new Register("\%r12"));
	  Pushq pushq5 = new Pushq(new Register("\%r13"));
	  Pushq pushq6 = new Pushq(new Register("\%r14"));
	  Pushq pushq7 = new Pushq(new Register("\%r15"));
	  currBlock.addAssembly(pushq);
//	  currBlock.addAssembly(pushq2);
//	  currBlock.addAssembly(pushq3);
	  currBlock.addAssembly(pushq4);
	  currBlock.addAssembly(pushq5);
	  currBlock.addAssembly(pushq6);
	  currBlock.addAssembly(pushq7);
   }
   
   // Pop callee-saved registers
   private void calleeSavePop(BasicBlock currBlock)
   {
      Popq popq = new Popq(new Register("\%r15"));
	  Popq popq2 = new Popq(new Register("\%r14"));
	  Popq popq3 = new Popq(new Register("\%r13"));
	  Popq popq4 = new Popq(new Register("\%r12"));
//	  Popq popq5 = new Popq(new Register("\%rbp"));
//	  Popq popq6 = new Popq(new Register("\%rsp"));
	  Popq popq7 = new Popq(new Register("\%rbx"));
	  currBlock.addAssembly(popq);
	  currBlock.addAssembly(popq2);
	  currBlock.addAssembly(popq3);
	  currBlock.addAssembly(popq4);
//	  currBlock.addAssembly(popq5);
//	  currBlock.addAssembly(popq6);
	  currBlock.addAssembly(popq7);
   }
   
   // Push caller-saved registers
   private void callerSavePush(BasicBlock currBlock)
   {
      Pushq pushq = new Pushq(new Register("\%rax"));
      Pushq pushq2 = new Pushq(new Register("\%rcx"));
	  Pushq pushq3 = new Pushq(new Register("\%rdx"));
	  Pushq pushq4 = new Pushq(new Register("\%rsi"));
	  Pushq pushq5 = new Pushq(new Register("\%rdi"));
	  Pushq pushq6 = new Pushq(new Register("\%r8"));
	  Pushq pushq7 = new Pushq(new Register("\%r9"));
	  Pushq pushq8 = new Pushq(new Register("\%r10"));
	  Pushq pushq9 = new Pushq(new Register("\%r11"));
	  currBlock.addAssembly(pushq);
	  currBlock.addAssembly(pushq2);
	  currBlock.addAssembly(pushq3);
	  currBlock.addAssembly(pushq4);
	  currBlock.addAssembly(pushq5);
	  currBlock.addAssembly(pushq6);
	  currBlock.addAssembly(pushq7);
	  currBlock.addAssembly(pushq8);
	  currBlock.addAssembly(pushq9);
   }
   
   // Pop caller-saved registers
   private void callerSavePop(BasicBlock currBlock)
   {
      Popq popq = new Popq(new Register("\%r11"));
	  Popq popq2 = new Popq(new Register("\%r10"));
	  Popq popq3 = new Popq(new Register("\%r9"));
	  Popq popq4 = new Popq(new Register("\%r8"));
	  Popq popq5 = new Popq(new Register("\%rdi"));
	  Popq popq6 = new Popq(new Register("\%rsi"));
	  Popq popq7 = new Popq(new Register("\%rdx"));
	  Popq popq8 = new Popq(new Register("\%rcx"));
	  Popq popq9 = new Popq(new Register("\%rax"));
	  currBlock.addAssembly(popq);
	  currBlock.addAssembly(popq2);
	  currBlock.addAssembly(popq3);
	  currBlock.addAssembly(popq4);
	  currBlock.addAssembly(popq5);
	  currBlock.addAssembly(popq6);
	  currBlock.addAssembly(popq7);
	  currBlock.addAssembly(popq8);
	  currBlock.addAssembly(popq9);
   }
   
   public void genAssem(boolean flag)
   {
      genAssem = flag;
   }
   
   public HashMap<String, Type> getGlobals()
   {
      return sTable.getMapForScope("global");
   }
   
   public int getMaxArgs(String funcName)
   {
      return maxArgsMap.get(funcName).intValue();
   }
   
   public BasicBlock getExitBlock(String funcName)
   {
      return exitBlocks.get(funcName);
   }
}

program returns [HashMap<String, BasicBlock> cfgs = null]
	: ^(PROGRAM types declarations["global"] functions) { $cfgs = cfgMap; } EOF
;

types
	: ^(TYPES struct*)
;

declarations[String scopeName]
	: ^(DECLS declaration[scopeName])
	| { System.out.println("There are no declarations"); }
;

functions
	@init
	{
		labelCounter = 1;
	}: ^(FUNCS function*)
;

struct
	@init
	{
		Vector<String> varNameList = new Vector<String>();
	}: ^(STRUCT id=ID 
		{
			HashMap<String, Type> structVars = new HashMap<String, Type>();
			sTypes.addStruct($id.text, structVars);
			structs.put($id.text, new Struct($id.text));
		} (decs[structVars, varNameList, ValCategory.STRUCT])+) 
		{
			sTypes.addStruct($id.text, structVars);
			structs.put($id.text, new Struct($id.text));
			structVarNames.put($id.text, varNameList);
		}
;

decs [HashMap<String, Type> vals, Vector<String> varList, ValCategory valCat]
	@init
	{
		Movq movq;
	}: ^(DECL ^(TYPE tp=type) id=ID) 
		{ 
			vals.put($id.text, $tp.t);
			varList.add($id.text);
			if (valCat == ValCategory.PARAMS)
			{
				paramRegMap.put($id.text, new Register(regCounter));
				
				switch(argOffsetCtr)
				{
					case 0:	movq = new Movq(new Register("\%rdi"), new Register(regCounter));
							currBlock.addAssembly(movq);
							break;
					case 1:	movq = new Movq(new Register("\%rsi"), new Register(regCounter));
							currBlock.addAssembly(movq);
							break;
					case 2:	movq = new Movq(new Register("\%rdx"), new Register(regCounter));
							currBlock.addAssembly(movq);
							break;
					case 3:	movq = new Movq(new Register("\%rcx"), new Register(regCounter));
							currBlock.addAssembly(movq);
							break;
					case 4:	movq = new Movq(new Register("\%r8"), new Register(regCounter));
							currBlock.addAssembly(movq);
							break;
					case 5:	movq = new Movq(new Register("\%r9"), new Register(regCounter));
							currBlock.addAssembly(movq);
							break;
					default: // spill
							break;
				}
				
				Loadinargument loadinargument = new Loadinargument(new Id($id.text), new Immediate(argOffsetCtr++), new Register(regCounter++));
				currBlock.addInstr(loadinargument);
			}
		}
;

type returns [Type t = null]
	: INT { $t = Type.intType(); }
	| BOOL { $t = Type.boolType(); }
	| ^(STRUCT id=ID)
		{
			$t = Type.structType($id.text);
		}
;

declaration[String scopeName]
	: (decl_list[scopeName])*
;

decl_list[String scopeName]
	: { ArrayList<String> varNames = new ArrayList<String>(); } ^(DECLLIST ^(TYPE tp=type) line=id_list[varNames])
		{ 
			for (String name : varNames)
			{
				if (!sTable.scopePrevDefined(scopeName))
				{
					localRegMap.clear();
					sTable.addScope(scopeName);
					sTable.addVar(scopeName, name, $tp.t);
					localRegMap.put(name, new Register(regCounter++));
				}	
				else if (!sTable.varPrevDefined(scopeName, name))
				{
					sTable.addVar(scopeName, name, $tp.t);
					localRegMap.put(name, new Register(regCounter++));
				}
			}
		}
;
	
id_list[ArrayList<String> varNames] returns [int line = -1]
	: (id=ID { varNames.add($id.text); })+ { $line = $id.line; }
;

function
	: ^(FUN id=ID 
		{
			HashMap<String, Type> params = new HashMap<String, Type>();
			
			regCounter = 0;
			cfgMap.put($id.text, new BasicBlock($id.text));
			
			currBlock = cfgMap.get($id.text);
			
			Register rbp = new Register("\%rbp");
			Register rsp = new Register("\%rsp");
			
			Pushq pushq = new Pushq(rbp);
			Movq movq = new Movq(rsp, rbp);
			currBlock.addAssembly(pushq);
			currBlock.addAssembly(movq);
			
			calleeSavePush(currBlock);
			
			exitBlock = new BasicBlock("L" + labelCounter++);
			exitBlock.addInstr(new Ret());
			
			exitBlocks.put($id.text, exitBlock);
			
			calleeSavePop(exitBlock);
			
			Movq endMovq = new Movq(rbp, rsp);
			Popq popq = new Popq(rbp);
			exitBlock.addAssembly(endMovq);
			exitBlock.addAssembly(popq);
			exitBlock.addAssembly(new Ret());
			
			
		} parameters[params] ^(RETTYPE tp=return_type)
		 	{
		 		funcs.put($id.text, Type.funcType($id.text, params, $tp.t));
		 		
		 		if (!sTable.scopePrevDefined($id.text))
		 		{
		 			sTable.addScope($id.text);
		 		}
		 	} declarations[$id.text] statement_list[$id.text]
		 		{
		 			currBlock.addSuccessor(exitBlock);
		 			exitBlock.addPredecessor(currBlock);
		 			
		 			Jumpi jumpi = new Jumpi(new Label(exitBlock.getLabel()));
		 			currBlock.addInstr(jumpi);
		 			
		 			Jmp jmp = new Jmp(new Label(exitBlock.getLabel()));
		 			currBlock.addAssembly(jmp);
		 			
		 			currBlock = exitBlock;
		 			
		 			maxArgsMap.put($id.text, new Integer(maxNumArgs));
		 			
//		 			BasicBlock funcBlk = cfgMap.get($id.text);
//		 			Subq subq = new Subq(new Immediate(8*maxNumArgs), new Register("\%rsp"));
//		 			funcBlk.addAssembly(subq, 9);
//		 			
//		 			Addq addq = new Addq(new Immediate(8*maxNumArgs), new Register("\%rsp"));
//		 			
//		 			if (exitBlock.numAssemInstr() > 10)
//		 			{
//		 				exitBlock.addAssembly(addq, exitBlock.numAssemInstr()-11);
//		 			}
//		 			else
//		 			{
//		 				exitBlock.addAssembly(addq, 0);
//		 			}
		 		})
;

parameters[HashMap<String, Type> params]
	@init
	{
		Vector<String> paramList = new Vector<String>();
		argOffsetCtr = 0;
		paramRegMap.clear();
	}: ^(PARAMS (decs[params, paramList, ValCategory.PARAMS])*)
;
 
return_type returns [Type t = null]
	: tp=type {$t = $tp.t; }
	| VOID { $t = Type.voidType(); }
;

statement_list[String funcName]
	: ^(STMTS (statement[funcName])*)
;

statement[String funcName]
	: block[funcName]
	| assignment[funcName]
	| print[funcName]
	| read[funcName]
	| conditional[funcName]
	| loop[funcName]
	| delete[funcName]
	| ret[funcName]
	| invocation[funcName]
;

block[String funcName]
	: ^(BLOCK statement_list[funcName])
;

assignment[String funcName]
	: ^(ASSIGN r1=expression[funcName] x=lvalue[funcName])
		{
			if ($x.reg != -1)
			{	
				Register targetReg = new Register($x.reg);
				
				Vector<String> valList = structVarNames.get(((Struct)$x.tp).structName());
			
				int offset = valList.indexOf($x.var) * 8;
						
				Movq movq = new Movq(new Register($r1.reg), new Immediate(offset), targetReg);
				currBlock.addAssembly(movq);
				
				Storeai storeai = new Storeai(new Register($r1.reg), targetReg, $x.var);
				currBlock.addInstr(storeai);
			}
			else
			{
				if (sTable.varPrevDefined(funcName, $x.var))
				{
					Register srcReg = new Register($r1.reg);
					Register targetReg = localRegMap.get($x.var);
					
					Movq movq = new Movq(srcReg, targetReg);
					currBlock.addAssembly(movq);
					
					Mov mov = new Mov(srcReg, targetReg);
					currBlock.addInstr(mov);
				}
				//Check params
				else if (paramRegMap.containsKey($x.var))
				{
					Register srcReg = new Register($r1.reg);
					Register targetReg = paramRegMap.get($x.var);
					
					Movq movq = new Movq(srcReg, targetReg);
					currBlock.addAssembly(movq);
					
					Mov mov = new Mov(srcReg, targetReg);
					currBlock.addInstr(mov);
				}
				//Must be global
				else
				{
					Register srcReg = new Register($r1.reg);
					
					Movq movq = new Movq(srcReg, ("glob_" + $x.var), new Register ("\%rip"));
					currBlock.addAssembly(movq);
					
					Storeglobal storeglobal = new Storeglobal(srcReg, new Id($x.var));
					currBlock.addInstr(storeglobal);
				}
			}
		}
;

lvalue[String funcName] returns [String var = null, int reg = -1, Type tp = null]
	: ^(DOT r1=ldot[funcName] id=ID)
		{
			$var = $id.text;
			$reg = $r1.reg;
			$tp = $r1.tp;
		}
	| id=ID
		{
			$var = $id.text;
		}
;

ldot[String funcName] returns [int reg = -1, Type tp = null]
	: ^(DOT r1=ldot[funcName] id=ID)
		{
			$reg = regCounter;
			$tp = sTypes.getValType(((Struct)$r1.tp).structName(), $id.text);
			
			Register targetReg = new Register(regCounter++);
			
			Vector<String> valList = structVarNames.get(((Struct)$r1.tp).structName());
			
			int offset = valList.indexOf($id.text) * 8;
					
			Movq movq = new Movq(new Immediate(offset), new Register($r1.reg), targetReg);
			currBlock.addAssembly(movq);
			
			Loadai loadai = new Loadai(new Register($r1.reg), $id.text, targetReg);
			currBlock.addInstr(loadai);
		}
	| id=ID
		{
			//Check local vars
			if (sTable.varPrevDefined(funcName, $id.text))
			{
				$reg = localRegMap.get($id.text).getRegNum();
				$tp = sTable.getVarType(funcName, $id.text);
			}
			//Check params
			else if (paramRegMap.containsKey($id.text))
			{
				$reg = paramRegMap.get($id.text).getRegNum();
				$tp = funcs.get(funcName).paramType($id.text);
			}
			//Must be global
			else
			{
				$reg = regCounter;
				$tp = sTable.getVarType("global", $id.text);
				
				Register targetReg = new Register(regCounter++);
				
				Movq movq = new Movq(("glob_" + $id.text), new Register("\%rip"), targetReg);
				currBlock.addAssembly(movq);
				
				Loadglobal loadglobal = new Loadglobal(new Id($id.text), targetReg);
				currBlock.addInstr(loadglobal);
			}
		}
;

print[String funcName]
	@init
	{
		int flag = 0;
	}: ^(PRINT r1=expression[funcName] (ENDL 
		{
			Register reg = new Register($r1.reg);
			Register targetReg = new Register(regCounter++);
			
			currBlock.addInstr(new Println(reg));
			
			Movq movq = new Movq(".LLC0", new Register("\%rdi"));
			Movq movq2 = new Movq(reg, new Register("\%rsi"));
			Movq movq3 = new Movq(new Immediate(0), new Register("\%rax"));
			
			Call call = new Call("printf");
			
			currBlock.addAssembly(movq);
			currBlock.addAssembly(movq2);
			currBlock.addAssembly(movq3);
			callerSavePush(currBlock);
			currBlock.addAssembly(call);
			callerSavePop(currBlock);
			
			flag=1;
		})? 
		{
			if (flag==0) 
			{
				Register reg = new Register($r1.reg);
				
				currBlock.addInstr(new Print(reg));
				
				Movq movq = new Movq(".LLC1", new Register("\%rdi"));
				Movq movq2 = new Movq(reg, new Register("\%rsi"));
				Movq movq3 = new Movq(new Immediate(0), new Register("\%rax"));
				Call call = new Call("printf");
				
				currBlock.addAssembly(movq);
				currBlock.addAssembly(movq2);
				currBlock.addAssembly(movq3);
				callerSavePush(currBlock);
				currBlock.addAssembly(call);
				callerSavePop(currBlock);
			}
		})
;

read[String funcName]
	: ^(READ x=lvalue[funcName])
		{	
			Register rsi = new Register("\%rsi");
			Register readReg = new Register(regCounter++);
			
			Movq movq = new Movq(".LLC2", new Register("\%rdi"));
			Movq movq2 = new Movq("glob_rd", new Register("\%rsi"));
			Movq movq3 = new Movq(new Immediate(0), new Register("\%rax"));
			Call call = new Call("scanf");
			
			currBlock.addAssembly(movq);
			currBlock.addAssembly(movq2);
			currBlock.addAssembly(movq3);
			callerSavePush(currBlock);
			currBlock.addAssembly(call);
			callerSavePop(currBlock);
						
			Read read = new Read(readReg);
			currBlock.addInstr(read);	
			
			if ($x.reg != -1)
			{	
				Register targetReg = new Register($x.reg);
				
				Vector<String> valList = structVarNames.get(((Struct)$x.tp).structName());
			
				int offset = valList.indexOf($x.var) * 8;
						
				Movq movq4 = new Movq("glob_rd", new Register("\%rip"), readReg);
				Movq movq5 = new Movq(readReg, new Immediate(offset), targetReg);
				currBlock.addAssembly(movq4);
				currBlock.addAssembly(movq5);
				
				Storeai storeai = new Storeai(readReg, targetReg, $x.var);
				currBlock.addInstr(storeai);
			}
			else
			{
				//Check local vars
				if (sTable.varPrevDefined(funcName, $x.var))
				{
					Mov mov = new Mov(readReg, localRegMap.get($x.var));
					currBlock.addInstr(mov);
					
					Movq movq6 = new Movq("glob_rd", new Register("\%rip"), localRegMap.get($x.var));
					currBlock.addAssembly(movq6);
				}
				//Check params
				else if (paramRegMap.containsKey($x.var))
				{
					Mov mov = new Mov(readReg, paramRegMap.get($x.var));
					currBlock.addInstr(mov);
					
					Movq movq6 = new Movq("glob_rd", new Register("\%rip"), paramRegMap.get($x.var));
					currBlock.addAssembly(movq6);
				}
				//Must be global
				else
				{
					Movq movq6 = new Movq("glob_rd", new Register("\%rip"), readReg);
					Movq movq7 = new Movq(readReg, ("glob_" + $x.var), new Register("\%rip"));
					currBlock.addAssembly(movq6);
					currBlock.addAssembly(movq7);
	
					Storeglobal storeglobal = new Storeglobal(readReg, new Id($x.var));
					currBlock.addInstr(storeglobal);
					
				}
			}
		}
;

conditional[String funcName]
	@init { 
			BasicBlock topBlock = currBlock;
            BasicBlock endThen;
            Jmp jmp; 
		  }: ^(IF r1=expression[funcName] 
		{  
			BasicBlock thenBlock = new BasicBlock("L" + labelCounter++);
			
			topBlock.addSuccessor(thenBlock);
			thenBlock.addPredecessor(topBlock);
			
			currBlock = thenBlock;
		} block[funcName]
		{
			endThen = currBlock;
			
			BasicBlock elseBlock = new BasicBlock("L" + labelCounter++);
			
			topBlock.addSuccessor(elseBlock);
			elseBlock.addPredecessor(topBlock);
			
			currBlock = elseBlock;
			
			Compi compi = new Compi(new Register($r1.reg), new Immediate(1), new Register("ccr"));
			topBlock.addInstr(compi);
			Cmp cmp = new Cmp(new Immediate(1), new Register($r1.reg));
			topBlock.addAssembly(cmp);
			
			Cbreq cbreq = new Cbreq(new Register("ccr"), new Label(thenBlock.getLabel()), new Label(elseBlock.getLabel()));
			topBlock.addInstr(cbreq);
			Je je = new Je(new Label(thenBlock.getLabel()));
			jmp = new Jmp(new Label(elseBlock.getLabel()));
			topBlock.addAssembly(je);
			topBlock.addAssembly(jmp);
			
		} (block[funcName])?)
		{
			BasicBlock afterBlock = new BasicBlock("L" + labelCounter++);
			
			endThen.addSuccessor(afterBlock);
			
			if (endThen.numInstructions() == 0)
			{
				Jumpi jumpi = new Jumpi(new Label(afterBlock.getLabel()));
				endThen.addInstr(jumpi);
				
				jmp = new Jmp(new Label(afterBlock.getLabel()));
				endThen.addAssembly(jmp);
			}
			else if (!(endThen.getInstr(endThen.numInstructions()-1) instanceof Ret))
			{
				Jumpi jumpi = new Jumpi(new Label(afterBlock.getLabel()));
				endThen.addInstr(jumpi);
				
				jmp = new Jmp(new Label(afterBlock.getLabel()));
				endThen.addAssembly(jmp);
			}
			
			currBlock.addSuccessor(afterBlock);
			
			if (currBlock.numInstructions() == 0)
			{
				Jumpi jumpimm = new Jumpi(new Label(afterBlock.getLabel()));
				currBlock.addInstr(jumpimm);
				
				jmp = new Jmp(new Label(afterBlock.getLabel()));
				currBlock.addAssembly(jmp);
			}
			else if (!(currBlock.getInstr(currBlock.numInstructions()-1) instanceof Ret))
			{
				Jumpi jumpimm = new Jumpi(new Label(afterBlock.getLabel()));
				currBlock.addInstr(jumpimm);
				
				jmp = new Jmp(new Label(afterBlock.getLabel()));
				currBlock.addAssembly(jmp);
			}
			
			afterBlock.addPredecessor(endThen);
			afterBlock.addPredecessor(currBlock);
			
			currBlock = afterBlock;
		}
;

loop[String funcName]
	@init {
			BasicBlock topBlock, loopBody;
			Jmp jmp;
			Cmp cmp;
			Je je;
		  }: ^(WHILE r1=expression[funcName] 
		{
			topBlock = currBlock;
			loopBody = new BasicBlock("L" + labelCounter++);
			
			topBlock.addSuccessor(loopBody);
			loopBody.addPredecessor(topBlock);
			
			currBlock = loopBody;
			
		} block[funcName]
		{
			BasicBlock afterBlock = new BasicBlock("L" + labelCounter++);
			
			currBlock.addSuccessor(loopBody);
			currBlock.addSuccessor(afterBlock);
			
			topBlock.addSuccessor(afterBlock);
			
			afterBlock.addPredecessor(currBlock);
			afterBlock.addPredecessor(topBlock);
			
			Compi compi = new Compi(new Register($r1.reg), new Immediate(1), new Register("ccr"));
			cmp = new Cmp(new Immediate(1), new Register($r1.reg));
			topBlock.addInstr(compi);
			topBlock.addAssembly(cmp);
			
			
			Cbreq cbreq = new Cbreq(new Register("ccr"), new Label(loopBody.getLabel()), new Label(afterBlock.getLabel()));
			topBlock.addInstr(cbreq);
			je = new Je(new Label(loopBody.getLabel()));
			jmp = new Jmp(new Label(afterBlock.getLabel()));
			topBlock.addAssembly(je);
			topBlock.addAssembly(jmp);
			
			//currBlock = afterBlock;
		} r2=expression[funcName])
		{
			Compi compimm = new Compi(new Register($r2.reg), new Immediate(1), new Register("ccr"));
			Cbreq cbrequal = new Cbreq(new Register("ccr"), new Label(loopBody.getLabel()), new Label(afterBlock.getLabel()));
			cmp = new Cmp(new Immediate(1), new Register($r2.reg));
			je = new Je(new Label(loopBody.getLabel()));
			jmp = new Jmp(new Label(afterBlock.getLabel()));
			
			currBlock.addInstr(compimm);
			currBlock.addInstr(cbrequal);
			
			currBlock.addAssembly(cmp);
			currBlock.addAssembly(je);
			currBlock.addAssembly(jmp);
			
			currBlock = afterBlock;
		}
;

delete[String funcName]
	: ^(DELETE r1=expression[funcName])
		{
			Del del = new Del(new Register($r1.reg));
			currBlock.addInstr(del);
			
			Movq movq = new Movq(new Register($r1.reg), new Register("\%rdi"));
			Call call = new Call("free");
			currBlock.addAssembly(movq);
			callerSavePush(currBlock);
			currBlock.addAssembly(call);
			callerSavePop(currBlock);
		}
;

ret[String funcName]
	: ^(RETURN (r1=expression[funcName])?)
		{
			currBlock.addSuccessor(exitBlock);
			exitBlock.addPredecessor(currBlock);
			
			if (!(funcs.get(funcName).getRetType() instanceof Void))
			{
				Storeret storeret = new Storeret(new Register($r1.reg));
				currBlock.addInstr(storeret);
				
				Movq movq = new Movq(new Register($r1.reg), new Register("\%rax"));
				currBlock.addAssembly(movq);
			}
			
			Jumpi jumpi = new Jumpi(new Label(exitBlock.getLabel()));
			currBlock.addInstr(jumpi);
//			
			Jmp jmp = new Jmp(new Label(exitBlock.getLabel()));
			currBlock.addAssembly(jmp);
			
			//currBlock = new BasicBlock("L" + labelCounter++);
			//System.out.println("Curr Block: " + currBlock.getLabel());

		}
;

invocation[String funcName]
	: ^(INVOKE id=ID argRegs=arguments[funcName])
		{	
			if (argRegs != null)
			{
				Movq movq;
				
				int spillArgs = 0;
				
				for (int i=0; i < argRegs.size(); i++)
				{
					Storeoutargument storeoutargument = new Storeoutargument(argRegs.get(i), new Immediate(i));
					currBlock.addInstr(storeoutargument);
					
					switch(i)
					{
						case 0:	movq = new Movq(argRegs.get(i), new Register("\%rdi"));
								currBlock.addAssembly(movq);
								break;
						case 1:	movq = new Movq(argRegs.get(i), new Register("\%rsi"));
								currBlock.addAssembly(movq);
								break;
						case 2:	movq = new Movq(argRegs.get(i), new Register("\%rdx"));
								currBlock.addAssembly(movq);
								break;
						case 3:	movq = new Movq(argRegs.get(i), new Register("\%rcx"));
								currBlock.addAssembly(movq);
								break;
						case 4:	movq = new Movq(argRegs.get(i), new Register("\%r8"));
								currBlock.addAssembly(movq);
								break;
						case 5:	movq = new Movq(argRegs.get(i), new Register("\%r9"));
								currBlock.addAssembly(movq);
								break;
						default: // store to stack
								spillArgs++;
								break;
					}
				}
				
				if (spillArgs > maxNumArgs)
				{
					maxNumArgs = spillArgs;
				}
			}
			
			// push all caller-saved registers onto stack
			callerSavePush(currBlock);
			
			// Make call to function
			Call call = new Call(new Label($id.text));
			currBlock.addInstr(call);
			currBlock.addAssembly(call);
			
			
			if (!(funcs.get($id.text).getRetType() instanceof Void))
			{
				Register targetReg = new Register(regCounter++);
				
				Movq movq = new Movq(new Register("\%rax"), targetReg);
				currBlock.addAssembly(movq);
				
				Loadret loadret = new Loadret(targetReg);
				currBlock.addInstr(loadret);
			}
			
			// pop all caller-saved registers off stack
			callerSavePop(currBlock);
		}
;

arguments[String funcName] returns [ArrayList<Register> argRegs = null]
	: {
	      $argRegs = new ArrayList<Register>();
	  } ^(ARGS (r1=expression[funcName] { $argRegs.add(new Register($r1.reg)); })+)
	| ARGS
;

expression[String funcName] returns [int reg = -1, Type tp = null]
	: ^(AND r1=expression[funcName] r2=expression[funcName])
		{
			$reg = regCounter;
			
			if (genAssem)
			{
				Register targetReg = new Register(regCounter);
				Movq movq = new Movq(new Register($r2.reg), targetReg);
				Andq andq = new Andq(new Register($r1.reg), targetReg);
				
				currBlock.addAssembly(movq);
				currBlock.addAssembly(andq); 
			}
			
			And and = new And(new Register($r1.reg), new Register($r2.reg), new Register(regCounter++));
			currBlock.addInstr(and);
		}
	| ^(OR r1=expression[funcName] r2=expression[funcName])
		{
			$reg = regCounter;
			
			if (genAssem)
			{
				Register targetReg = new Register(regCounter);
				Movq movq = new Movq(new Register($r2.reg), targetReg);
				Orq orq = new Orq(new Register($r1.reg), targetReg);
				
				currBlock.addAssembly(movq);
				currBlock.addAssembly(orq); 
			}
			
			Or or = new Or(new Register($r1.reg), new Register($r2.reg), new Register(regCounter++));
			currBlock.addInstr(or);
		}
	| ^(EQ r1=expression[funcName] r2=expression[funcName])
		{
			$reg = regCounter;
			long val = 0;
			
//			if (genAssem)
//			{
				Register targetReg = new Register(regCounter++);
				
				Movq movq = new Movq(new Immediate(val), targetReg);
				Movq movq2 = new Movq(new Immediate(1), new Register(regCounter));
				Cmp cmp = new Cmp(new Register($r1.reg), new Register($r2.reg));			
				Cmoveq cmoveq = new Cmoveq(new Register(regCounter), targetReg);
				
				currBlock.addAssembly(movq);
				currBlock.addAssembly(movq2);
				currBlock.addAssembly(cmp);
				currBlock.addAssembly(cmoveq);
//			}
			
//			Register targetReg = new Register(regCounter++);
			
			Loadi loadi = new Loadi(new Immediate(val), targetReg);
			currBlock.addInstr(loadi);
			
			Loadi loadi2 = new Loadi(new Immediate(1), new Register(regCounter));
			currBlock.addInstr(loadi2);
			
			Comp comp = new Comp(new Register($r1.reg), new Register($r2.reg), new Register("ccr"));
			currBlock.addInstr(comp);
			
			Moveq moveq = new Moveq(new Register(regCounter), targetReg);
			currBlock.addInstr(moveq);
		}
	| ^(LT r1=expression[funcName] r2=expression[funcName])
		{
			$reg = regCounter;
			long val = 0;
			
//			if (genAssem)
//			{
				Register targetReg = new Register(regCounter++);
				
				Movq movq = new Movq(new Immediate(val), targetReg);
				Movq movq2 = new Movq(new Immediate(1), new Register(regCounter));
				Cmp cmp = new Cmp(new Register($r2.reg), new Register($r1.reg));
				Cmovlq cmovlq = new Cmovlq(new Register(regCounter), targetReg);
				
				currBlock.addAssembly(movq);
				currBlock.addAssembly(movq2);
				currBlock.addAssembly(cmp);
				currBlock.addAssembly(cmovlq);
//			}
			
//			Register targetReg = new Register(regCounter++);
			
			Loadi loadi = new Loadi(new Immediate(val), targetReg);
			currBlock.addInstr(loadi);
			
			Loadi loadi2 = new Loadi(new Immediate(1), new Register(regCounter));
			currBlock.addInstr(loadi2);
			
			Comp comp = new Comp(new Register($r1.reg), new Register($r2.reg), new Register("ccr"));
			currBlock.addInstr(comp);
			
			Movlt movlt = new Movlt(new Register(regCounter), targetReg);
			currBlock.addInstr(movlt);
		}
	| ^(GT r1=expression[funcName] r2=expression[funcName])
		{
			$reg = regCounter;
			long val = 0;
			
//			if (genAssem)
//			{
				Register targetReg = new Register(regCounter++);
				
				Movq movq = new Movq(new Immediate(val), targetReg);
				Movq movq2 = new Movq(new Immediate(1), new Register(regCounter));
				Cmp cmp = new Cmp(new Register($r2.reg), new Register($r1.reg));
				Cmovgq cmovgq = new Cmovgq(new Register(regCounter), targetReg);
				
				currBlock.addAssembly(movq);
				currBlock.addAssembly(movq2);
				currBlock.addAssembly(cmp);
				currBlock.addAssembly(cmovgq);
//			}
			
//			Register targetReg = new Register(regCounter++);
			
			Loadi loadi = new Loadi(new Immediate(val), targetReg);
			currBlock.addInstr(loadi);
			
			Loadi loadi2 = new Loadi(new Immediate(1), new Register(regCounter));
			currBlock.addInstr(loadi2);
			
			Comp comp = new Comp(new Register($r1.reg), new Register($r2.reg), new Register("ccr"));
			currBlock.addInstr(comp);
			
			Movgt movgt = new Movgt(new Register(regCounter), targetReg);
			currBlock.addInstr(movgt);
		}
	| ^(NE r1=expression[funcName] r2=expression[funcName])
		{
			$reg = regCounter;
			long val = 0;
			
//			if (genAssem)
//			{
				Register targetReg = new Register(regCounter++);
				
				Movq movq = new Movq(new Immediate(val), targetReg);
				Movq movq2 = new Movq(new Immediate(1), new Register(regCounter));
				Cmp cmp = new Cmp(new Register($r2.reg), new Register($r1.reg));
				Cmovneq cmovneq = new Cmovneq(new Register(regCounter), targetReg);
				
				currBlock.addAssembly(movq);
				currBlock.addAssembly(movq2);
				currBlock.addAssembly(cmp);
				currBlock.addAssembly(cmovneq);
//			}
			
//			Register targetReg = new Register(regCounter++);
			
			Loadi loadi = new Loadi(new Immediate(val), targetReg);
			currBlock.addInstr(loadi);
			
			Loadi loadi2 = new Loadi(new Immediate(1), new Register(regCounter));
			currBlock.addInstr(loadi2);
			
			Comp comp = new Comp(new Register($r1.reg), new Register($r2.reg), new Register("ccr"));
			currBlock.addInstr(comp);
			
			Movne movne = new Movne(new Register(regCounter), targetReg);
			currBlock.addInstr(movne);
		}
	| ^(LE r1=expression[funcName] r2=expression[funcName])
		{
			$reg = regCounter;
			long val = 0;
			
//			if (genAssem)
//			{
				Register targetReg = new Register(regCounter++);
				
				Movq movq = new Movq(new Immediate(val), targetReg);
				Movq movq2 = new Movq(new Immediate(1), new Register(regCounter));
				Cmp cmp = new Cmp(new Register($r2.reg), new Register($r1.reg));
				Cmovleq cmovleq = new Cmovleq(new Register(regCounter), targetReg);
				
				currBlock.addAssembly(movq);
				currBlock.addAssembly(movq2);
				currBlock.addAssembly(cmp);
				currBlock.addAssembly(cmovleq);
//			}
			
//			Register targetReg = new Register(regCounter++);
			
			Loadi loadi = new Loadi(new Immediate(val), targetReg);
			currBlock.addInstr(loadi);
			
			Loadi loadi2 = new Loadi(new Immediate(1), new Register(regCounter));
			currBlock.addInstr(loadi2);
			
			Comp comp = new Comp(new Register($r1.reg), new Register($r2.reg), new Register("ccr"));
			currBlock.addInstr(comp);
			
			Movle movle = new Movle(new Register(regCounter), targetReg);
			currBlock.addInstr(movle);
		}
	| ^(GE r1=expression[funcName] r2=expression[funcName])
		{
			$reg = regCounter;
			long val = 0;
			
//			if (genAssem)
//			{
				Register targetReg = new Register(regCounter++);
				
				Movq movq = new Movq(new Immediate(val), targetReg);
				Movq movq2 = new Movq(new Immediate(1), new Register(regCounter));
				Cmp cmp = new Cmp(new Register($r2.reg), new Register($r1.reg));
				Cmovgeq cmovgeq = new Cmovgeq(new Register(regCounter), targetReg);
				
				currBlock.addAssembly(movq);
				currBlock.addAssembly(movq2);
				currBlock.addAssembly(cmp);
				currBlock.addAssembly(cmovgeq);
//			}
			
//			Register targetReg = new Register(regCounter++);
			
			Loadi loadi = new Loadi(new Immediate(val), targetReg);
			currBlock.addInstr(loadi);
			
			Loadi loadi2 = new Loadi(new Immediate(1), new Register(regCounter));
			currBlock.addInstr(loadi2);
			
			Comp comp = new Comp(new Register($r1.reg), new Register($r2.reg), new Register("ccr"));
			currBlock.addInstr(comp);
			
			Movge movge = new Movge(new Register(regCounter), targetReg);
			currBlock.addInstr(movge);
		}
	| ^(PLUS r1=expression[funcName] r2=expression[funcName])
		{
			$reg = regCounter;
			
			if (genAssem)
			{
				Register targetReg = new Register(regCounter);
				Movq movq = new Movq(new Register($r1.reg), targetReg);
				Addq addq = new Addq(new Register($r2.reg), targetReg);
				
				currBlock.addAssembly(movq);
				currBlock.addAssembly(addq);
			}
			
			Add add = new Add(new Register($r1.reg), new Register($r2.reg), new Register(regCounter++));
			currBlock.addInstr(add);	
		}
	| ^(MINUS r1=expression[funcName] r2=expression[funcName])
		{	
			$reg = regCounter;
			
			if (genAssem)
			{
				Register targetReg = new Register(regCounter);
				
				Movq movq = new Movq(new Register($r1.reg), targetReg);
				Subq subq = new Subq(new Register($r2.reg), targetReg);
				
				currBlock.addAssembly(movq);
				currBlock.addAssembly(subq); 
			}
			
			Sub sub = new Sub(new Register($r1.reg), new Register($r2.reg), new Register(regCounter++));
			currBlock.addInstr(sub);
		}
	| ^(TIMES r1=expression[funcName] r2=expression[funcName])
		{
			$reg = regCounter;
			
			if (genAssem)
			{
				Register targetReg = new Register(regCounter);
				
				Movq movq = new Movq(new Register($r1.reg), targetReg);
				Imulq imulq = new Imulq(new Register($r2.reg), targetReg);
				
				currBlock.addAssembly(movq);
				currBlock.addAssembly(imulq); 
			}
			
			Mult mult = new Mult(new Register($r1.reg), new Register($r2.reg), new Register(regCounter++));
			currBlock.addInstr(mult);
		}
	| ^(DIVIDE r1=expression[funcName] r2=expression[funcName])
		{
			$reg = regCounter;
			
			Movq movq = new Movq(new Register($r1.reg), new Register("\%rax"));
			Cqto cqto = new Cqto();
			Divq divq = new Divq(new Register($r2.reg));
			Movq movq2 = new Movq(new Register("\%rax"), new Register(regCounter));
			currBlock.addAssembly(movq);
			currBlock.addAssembly(cqto);
			currBlock.addAssembly(divq);
			currBlock.addAssembly(movq2);
			
			Div div = new Div(new Register($r1.reg), new Register($r2.reg), new Register(regCounter++));
			currBlock.addInstr(div);
		}
	| ^(NOT r1=expression[funcName])
		{
			$reg = regCounter;
			long val = 1;
			
			Movq movq = new Movq(new Register($r1.reg), new Register(regCounter));
			Xorq xorq = new Xorq(new Immediate(val), new Register(regCounter));
			
			currBlock.addAssembly(movq);
			currBlock.addAssembly(xorq);
			
			Xori xori = new Xori(new Register($r1.reg), new Immediate(val), new Register(regCounter++));
			currBlock.addInstr(xori);
			
		}
	| ^(NEG r1=expression[funcName])
		{
			$reg = regCounter;
			long val = 0;
			
			Movq movq = new Movq(new Immediate(0), new Register(regCounter));
			Subq subq = new Subq(new Register($r1.reg), new Register(regCounter));
			currBlock.addAssembly(movq);
			currBlock.addAssembly(subq);
			
			Rsubi rsubi = new Rsubi(new Register($r1.reg), new Immediate(val), new Register(regCounter++));
			currBlock.addInstr(rsubi);
		}
	| ^(DOT r1=expression[funcName] id=ID)
		{
			$reg = regCounter;
			$tp = sTypes.getValType(((Struct)$r1.tp).structName(), $id.text);
			
			Register targetReg = new Register(regCounter++);
			
			Vector<String> valList = structVarNames.get(((Struct)$r1.tp).structName());
			
			int offset = valList.indexOf($id.text) * 8;
					
			Movq movq = new Movq(new Immediate(offset), new Register($r1.reg), targetReg);
			currBlock.addAssembly(movq);
			
			Loadai loadai = new Loadai(new Register($r1.reg), $id.text, targetReg);
			currBlock.addInstr(loadai);
		}
	| ^(INVOKE id=ID argRegs=arguments[funcName])
		{
			if (argRegs != null)
			{
				Movq movq;
				
				for (int i=0; i < argRegs.size(); i++)
				{
					Storeoutargument storeoutargument = new Storeoutargument(argRegs.get(i), new Immediate(i));
					currBlock.addInstr(storeoutargument);
					
					switch(i)
					{
						case 0:	movq = new Movq(argRegs.get(i), new Register("\%rdi"));
								currBlock.addAssembly(movq);
								break;
						case 1:	movq = new Movq(argRegs.get(i), new Register("\%rsi"));
								currBlock.addAssembly(movq);
								break;
						case 2:	movq = new Movq(argRegs.get(i), new Register("\%rdx"));
								currBlock.addAssembly(movq);
								break;
						case 3:	movq = new Movq(argRegs.get(i), new Register("\%rcx"));
								currBlock.addAssembly(movq);
								break;
						case 4:	movq = new Movq(argRegs.get(i), new Register("\%r8"));
								currBlock.addAssembly(movq);
								break;
						case 5:	movq = new Movq(argRegs.get(i), new Register("\%r9"));
								currBlock.addAssembly(movq);
								break;
						default: // arguments beyond first 6
								Pushq pushq = new Pushq(argRegs.get(i));
								currBlock.addAssembly(pushq);
								break;
					}
				}
			}
			
			// push all caller-saved registers onto stack
			callerSavePush(currBlock);
			
			// Make call to function
			Call call = new Call(new Label($id.text));
			currBlock.addInstr(call);
			currBlock.addAssembly(call);
			
			$reg = regCounter;
			$tp = funcs.get($id.text).returnType();
			
			if (!((funcs.get($id.text).getRetType()) instanceof Void))
			{
				Movq movq = new Movq(new Register("\%rax"), new Register(regCounter));
				currBlock.addAssembly(movq);
				
				Loadret loadret = new Loadret(new Register(regCounter++));
				currBlock.addInstr(loadret);
			}
			
			// pop all caller-saved registers off stack
			callerSavePop(currBlock);
		}
	| id=ID 
		{
			//Check local vars
			if (sTable.varPrevDefined(funcName, $id.text))
			{
				$reg = Integer.parseInt(localRegMap.get($id.text).toString().substring(1));
				$tp = sTable.getVarType(funcName, $id.text);
			}
			//Check params
			else if (paramRegMap.containsKey($id.text))
			{
				$reg = Integer.parseInt(paramRegMap.get($id.text).toString().substring(1));
				$tp = funcs.get(funcName).paramType($id.text);
			}
			//Must be global
			else
			{
				$reg = regCounter;
				$tp = sTable.getVarType("global", $id.text);
				
				Register targetReg = new Register(regCounter++);
				
				Movq movq = new Movq(("glob_" + $id.text), new Register("\%rip"), targetReg);
				currBlock.addAssembly(movq);
				
				Loadglobal loadglobal = new Loadglobal(new Id($id.text), targetReg);
				currBlock.addInstr(loadglobal);
			}
		}
	| INTEGER
		{ 
			$reg = regCounter;
			long val = Long.parseLong($INTEGER.text);
			
			if (genAssem)
			{
				Movq movq = new Movq(new Immediate(val), new Register(regCounter));
				currBlock.addAssembly(movq);
			}
			
			Loadi loadi = new Loadi(new Immediate(val), new Register(regCounter++));
			currBlock.addInstr(loadi);
		}
	| TRUE 
		{
			$reg = regCounter;
			long val = 1;
			
			if (genAssem)
			{
				Movq movq = new Movq(new Immediate(val), new Register(regCounter));
				currBlock.addAssembly(movq);
			}
			
			Loadi loadi = new Loadi(new Immediate(val), new Register(regCounter++));
			currBlock.addInstr(loadi);
		}
	| FALSE 
		{ 
			$reg = regCounter;
			long val = 0;
			
			if (genAssem)
			{
				Movq movq = new Movq(new Immediate(val), new Register(regCounter));
				currBlock.addAssembly(movq);
			}
			
			Loadi loadi = new Loadi(new Immediate(val), new Register(regCounter++));
			currBlock.addInstr(loadi);
		}
	| ^(NEW id=ID) 
		{
			$reg = regCounter;
			
			Register targetReg = new Register(regCounter++);
			
			int numStrVals = sTypes.getStructTypes($id.text).size();
			Movq movq = new Movq(new Immediate(numStrVals * 8), new Register("\%rdi"));
			Call call = new Call("malloc");
			Movq afterMovq = new Movq(new Register("\%rax"), targetReg);
			currBlock.addAssembly(movq);
			callerSavePush(currBlock);
			currBlock.addAssembly(call);
			currBlock.addAssembly(afterMovq);
			callerSavePop(currBlock);
			New newStruct = new New(structs.get($id.text), sTypes.getStructTypes($id.text), targetReg);
			currBlock.addInstr(newStruct); 
		}
	| NULL
		{
			$reg = regCounter;
			long val = 0;
			
			if (genAssem)
			{
				Movq movq = new Movq(new Immediate(val), new Register(regCounter));
				currBlock.addAssembly(movq);
			}
			
			Loadi loadi = new Loadi(new Immediate(val), new Register(regCounter++));
			currBlock.addInstr(loadi);
		}
;

