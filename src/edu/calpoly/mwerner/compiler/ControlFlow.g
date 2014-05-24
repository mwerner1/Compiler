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
   
   //Counter for creating block labels
   int labelCounter, regCounter, argOffsetCtr;
   
   //Stores the current block
   BasicBlock currBlock;
   
   //Stores the exit block
   BasicBlock exitBlock;
   
   //Boolean flag that determines whether or not to generate assembly instructions
   boolean genAssem;
   
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
   
   public void genAssem(boolean flag)
   {
      genAssem = flag;
   }
   
   public HashMap<String, Type> getGlobals()
   {
      return sTable.getMapForScope("global");
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
			
			exitBlock = new BasicBlock("L" + labelCounter++);
			exitBlock.addInstr(new Ret());
			
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
		 			
		 			currBlock = exitBlock;
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
					Mov mov = new Mov(new Register($r1.reg), localRegMap.get($x.var));
					currBlock.addInstr(mov);
				}
				//Check params
				else if (paramRegMap.containsKey($x.var))
				{
					Mov mov = new Mov(new Register($r1.reg), paramRegMap.get($x.var));
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
			currBlock.addInstr(new Println(new Register($r1.reg)));
			currBlock.addAssembly(new Println(new Register($r1.reg))); 
			flag=1;
		})? 
		{
			if (flag==0) 
			{
				currBlock.addInstr(new Print(new Register($r1.reg)));
				currBlock.addAssembly(new Print(new Register($r1.reg)));
			}
		})
;

read[String funcName]
	: ^(READ x=lvalue[funcName])
		{
			Addi addi = new Addi(new Register("rarp"), $x.var, new Register(regCounter));
			currBlock.addInstr(addi);
			
			Read read = new Read(new Register(regCounter++));
			currBlock.addInstr(read);
			
			Loadai loadai;
			
			//Check local vars
			if (sTable.varPrevDefined(funcName, $x.var))
			{
				
				loadai = new Loadai(new Register("rarp"), $x.var, localRegMap.get($x.var));
			}
			//Check params
			else if (paramRegMap.containsKey($x.var))
			{
				loadai = new Loadai(new Register("rarp"), $x.var, paramRegMap.get($x.var));
			}
			//Must be global
			else
			{
				int reg = regCounter;
				
				Register targetReg = new Register(regCounter++);
				
				Movq movq = new Movq(("glob_" + $x.var), new Register("\%rip"), targetReg);
				currBlock.addAssembly(movq);
				
				Loadglobal loadglobal = new Loadglobal(new Id($x.var), targetReg);
				currBlock.addInstr(loadglobal);
				
				loadai = new Loadai(new Register("rarp"), $x.var, new Register(reg));
			}
			
			currBlock.addInstr(loadai);
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
				endThen.addAssembly(jmp);
			}
			else if (!(currBlock.getInstr(currBlock.numInstructions()-1) instanceof Ret))
			{
				Jumpi jumpimm = new Jumpi(new Label(afterBlock.getLabel()));
				currBlock.addInstr(jumpimm);
				
				jmp = new Jmp(new Label(afterBlock.getLabel()));
				endThen.addAssembly(jmp);
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
			
			Jmp jmp = new Jmp(new Label(exitBlock.getLabel()));
			currBlock.addAssembly(jmp);
			
			currBlock = new BasicBlock("L" + labelCounter++);

		}
;

invocation[String funcName]
	: ^(INVOKE id=ID argRegs=arguments[funcName])
		{	
			if (argRegs != null)
			{
				for (int i=0; i < argRegs.size(); i++)
				{
					Storeoutargument storeoutargument = new Storeoutargument(argRegs.get(i), new Immediate(i));
					currBlock.addInstr(storeoutargument);
				}
			}
			
			Call call = new Call(new Label($id.text));
			currBlock.addInstr(call);
			
			if (!(funcs.get($id.text).getRetType() instanceof Void))
			{
				Loadret loadret = new Loadret(new Register(regCounter++));
				currBlock.addInstr(loadret);
			}
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
			
			if (genAssem)
			{
				Register targetReg = new Register(regCounter);
				
				Movq movq = new Movq(new Immediate(val), targetReg);
				Cmp cmp = new Cmp(new Register($r1.reg), new Register($r2.reg));
				Cmoveq cmoveq = new Cmoveq(new Immediate(1), targetReg);
				
				currBlock.addAssembly(movq);
				currBlock.addAssembly(cmp);
				currBlock.addAssembly(cmoveq);
			}
			
			Loadi loadi = new Loadi(new Immediate(val), new Register(regCounter));
			currBlock.addInstr(loadi);
			
			Comp comp = new Comp(new Register($r1.reg), new Register($r2.reg), new Register("ccr"));
			currBlock.addInstr(comp);
			
			val = 1;
			Moveq moveq = new Moveq(new Immediate(val), new Register(regCounter++));
			currBlock.addInstr(moveq);
		}
	| ^(LT r1=expression[funcName] r2=expression[funcName])
		{
			$reg = regCounter;
			long val = 0;
			
			if (genAssem)
			{
				Register targetReg = new Register(regCounter);
				
				Movq movq = new Movq(new Immediate(val), targetReg);
				Cmp cmp = new Cmp(new Register($r1.reg), new Register($r2.reg));
				Cmovlq cmovlq = new Cmovlq(new Immediate(1), new Register(regCounter));
				
				currBlock.addAssembly(movq);
				currBlock.addAssembly(cmp);
				currBlock.addAssembly(cmovlq);
			}
			
			Loadi loadi = new Loadi(new Immediate(val), new Register(regCounter));
			currBlock.addInstr(loadi);
			
			Comp comp = new Comp(new Register($r1.reg), new Register($r2.reg), new Register("ccr"));
			currBlock.addInstr(comp);
			
			val = 1;
			Movlt movlt = new Movlt(new Immediate(val), new Register(regCounter++));
			currBlock.addInstr(movlt);
		}
	| ^(GT r1=expression[funcName] r2=expression[funcName])
		{
			$reg = regCounter;
			long val = 0;
			
			if (genAssem)
			{
				Register targetReg = new Register(regCounter);
				
				Movq movq = new Movq(new Immediate(val), targetReg);
				Cmp cmp = new Cmp(new Register($r1.reg), new Register($r2.reg));
				Cmovgq cmovgq = new Cmovgq(new Immediate(1), new Register(regCounter));
				
				currBlock.addAssembly(movq);
				currBlock.addAssembly(cmp);
				currBlock.addAssembly(cmovgq);
			}
			
			Loadi loadi = new Loadi(new Immediate(val), new Register(regCounter));
			currBlock.addInstr(loadi);
			
			Comp comp = new Comp(new Register($r1.reg), new Register($r2.reg), new Register("ccr"));
			currBlock.addInstr(comp);
			
			val = 1;
			Movgt movgt = new Movgt(new Immediate(val), new Register(regCounter++));
			currBlock.addInstr(movgt);
		}
	| ^(NE r1=expression[funcName] r2=expression[funcName])
		{
			$reg = regCounter;
			long val = 0;
			
			if (genAssem)
			{
				Register targetReg = new Register(regCounter);
				
				Movq movq = new Movq(new Immediate(val), targetReg);
				Cmp cmp = new Cmp(new Register($r1.reg), new Register($r2.reg));
				Cmovneq cmovneq = new Cmovneq(new Immediate(1), new Register(regCounter));
				
				currBlock.addAssembly(movq);
				currBlock.addAssembly(cmp);
				currBlock.addAssembly(cmovneq);
			}
			
			Loadi loadi = new Loadi(new Immediate(val), new Register(regCounter));
			currBlock.addInstr(loadi);
			
			Comp comp = new Comp(new Register($r1.reg), new Register($r2.reg), new Register("ccr"));
			currBlock.addInstr(comp);
			
			val = 1;
			Movne movne = new Movne(new Immediate(val), new Register(regCounter++));
			currBlock.addInstr(movne);
		}
	| ^(LE r1=expression[funcName] r2=expression[funcName])
		{
			$reg = regCounter;
			long val = 0;
			
			if (genAssem)
			{
				Register targetReg = new Register(regCounter);
				
				Movq movq = new Movq(new Immediate(val), targetReg);
				Cmp cmp = new Cmp(new Register($r1.reg), new Register($r2.reg));
				Cmovleq cmovleq = new Cmovleq(new Immediate(1), new Register(regCounter));
				
				currBlock.addAssembly(movq);
				currBlock.addAssembly(cmp);
				currBlock.addAssembly(cmovleq);
			}
			
			Loadi loadi = new Loadi(new Immediate(val), new Register(regCounter));
			currBlock.addInstr(loadi);
			
			Comp comp = new Comp(new Register($r1.reg), new Register($r2.reg), new Register("ccr"));
			currBlock.addInstr(comp);
			
			val = 1;
			Movle movle = new Movle(new Immediate(val), new Register(regCounter++));
			currBlock.addInstr(movle);
		}
	| ^(GE r1=expression[funcName] r2=expression[funcName])
		{
			$reg = regCounter;
			long val = 0;
			
			if (genAssem)
			{
				Register targetReg = new Register(regCounter);
				
				Movq movq = new Movq(new Immediate(val), targetReg);
				Cmp cmp = new Cmp(new Register($r1.reg), new Register($r2.reg));
				Cmovgeq cmovgeq = new Cmovgeq(new Immediate(1), new Register(regCounter));
				
				currBlock.addAssembly(movq);
				currBlock.addAssembly(cmp);
				currBlock.addAssembly(cmovgeq);
			}
			
			Loadi loadi = new Loadi(new Immediate(val), new Register(regCounter));
			currBlock.addInstr(loadi);
			
			Comp comp = new Comp(new Register($r1.reg), new Register($r2.reg), new Register("ccr"));
			currBlock.addInstr(comp);
			
			val = 1;
			Movge movge = new Movge(new Immediate(val), new Register(regCounter++));
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
						default: // spill
								break;
					}
				}
			}
			
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
			currBlock.addAssembly(call);
			currBlock.addAssembly(afterMovq);
			
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

