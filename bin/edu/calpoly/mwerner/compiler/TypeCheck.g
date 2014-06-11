tree grammar TypeCheck;

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
}
@members
{  
   //Hashmap of declared structs and the names and types of its variables
   StructTypes sTypes = new StructTypes();
   
   //Symbol table
   SymbolTable sTable = new SymbolTable();
   
   //Hashmap of functions
   HashMap<String, Func> funcs = new HashMap<String, Func>();
   
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
}

program
	: ^(PROGRAM types declarations["global"] functions) EOF
;

types
	: ^(TYPES struct*)
;

declarations[String scopeName]
	: ^(DECLS declaration[scopeName])
	| { System.out.println("There are no declarations"); }
;

functions
	: ^(FUNCS function*)
		{
			if (!funcs.containsKey("main"))
			{
				error(0, "No main method defined");
			}
			else if (!(funcs.get("main").returnType() instanceof Int))
			{
				error(0, "main method specifies an incorrect return type.  should be of type int");
			}
		}
;

struct
	: ^(STRUCT id=ID 
		{
			if (sTypes.isDefined($id.text))
			{
				error($id.line, "redefinition of struct ’" + $id + "’");
			}
			HashMap<String, Type> structVars = new HashMap<String, Type>();
			sTypes.addStruct($id.text, structVars);
		} (decs[structVars, ValCategory.STRUCT])+) 
		{
			sTypes.addStruct($id.text, structVars); 
		}
;

decs [HashMap<String, Type> vals, ValCategory valCat]
	: ^(DECL ^(TYPE tp=type) id=ID) 
		{ 
			if (vals.containsKey($id.text))
			{
				// Call came from struct rule
				if (valCat == ValCategory.STRUCT)
				{
					error($id.line, "redefinition of struct variable '" + $id + "'");
				}
				// Call came from parameters rule
				else
				{
					error($id.line, "redefinition of parameter '" + $id + "'");
				}
			}
			vals.put($id.text, $tp.t);
		}
;

type returns [Type t = null]
	: INT { $t = Type.intType(); }
	| BOOL { $t = Type.boolType(); }
	| ^(STRUCT id=ID)
		{
			if (!sTypes.isDefined($id.text))
			{
				error($id.line, "undefined struct type '" + $id + "'");
			}
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
					sTable.addScope(scopeName);
					sTable.addVar(scopeName, name, $tp.t);
				}	
				else if (!sTable.varPrevDefined(scopeName, name))
				{
					sTable.addVar(scopeName, name, $tp.t);
				}
				else
				{
					error(line, "redefinition of variable '" + name + "'");
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
		} parameters[params] ^(RETTYPE tp=return_type)
		 	{
		 		if (funcs.containsKey($id.text))
		 		{
		 			error($id.line, "redefinition of function '" + $id + "'");
		 		}
		 		funcs.put($id.text, Type.funcType($id.text, params, $tp.t));
		 		
		 		if (!sTable.scopePrevDefined($id.text))
		 		{
		 			sTable.addScope($id.text);
		 		}
		 	} declarations[$id.text] statement_list[$id.text])
;

parameters[HashMap<String, Type> params]
	: ^(PARAMS (decs[params, ValCategory.PARAMS])*)
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
	: ^(ASSIGN tp1=expression[funcName] tp2=lvalue[funcName])
		{
			if (($tp2.t instanceof Struct) && !(($tp1.t instanceof Struct) || ($tp1.t instanceof Null)))
			{
				error(0, "assignment to struct requires another struct or null");
			}
			else if (!(($tp1.t).equals($tp2.t)) && (!($tp2.t instanceof Struct && $tp1.t instanceof Null)))
			{
				System.out.println($tp1.t + " " + $tp2.t);
				error(0, "assignment types mismatch");
			}
		}
;

lvalue[String funcName] returns [Type t = null]
	: ^(DOT tp=lvalue[funcName] id=ID)
		{
			if (!sTypes.containsVal(((Struct)$tp.t).structName(), $id.text))
			{
				error($id.line, "Variable '" + $id.text + "' not defined in struct '" + ((Struct)$tp.t).structName() + "'");
			}
			else
			{
				//System.out.println("Struct: " + ((Struct)$tp.t).structName() + " Var: " + $id.text + " Type: " + sTypes.getValType(((Struct)$tp.t).structName(), $id.text));
				return sTypes.getValType(((Struct)$tp.t).structName(), $id.text);
			}
		}
	| id=ID
		{
			if (funcs.get(funcName).containsParam($id.text))
			{
				$t=funcs.get(funcName).paramType($id.text);
			}
			else if (sTable.varPrevDefined(funcName, $id.text))
			{
				$t=sTable.getVarType(funcName, $id.text);
				
			}
			else if (sTable.varPrevDefined("global", $id.text))
			{
				$t=sTable.getVarType("global", $id.text);
			}
			else
			{
				error($id.line, "variable '" + $id + "' was never initialized");
			}
		}
;

print[String funcName]
	: ^(PRINT tp=expression[funcName] (ENDL)?)
		{
			if (!($tp.t instanceof Int))
			{
				error(0, "print requires an integer argument");
			} 
		}
;

read[String funcName]
	: ^(READ tp=lvalue[funcName])
		{
			if (!($tp.t instanceof Int))
			{
				error(0, "read requires an integer argument");
			}
		}
;

conditional[String funcName]
	: ^(IF tp=expression[funcName] block[funcName] (block[funcName])?)
		{
			if (!($tp.t instanceof Bool))
			{
				error(0, "'if' statement requires a bool type guard");
			}
		}
;

loop[String funcName]
	: ^(WHILE tp=expression[funcName] block[funcName] expression[funcName])
		{
			if (!($tp.t instanceof Bool))
			{
				error(0, "'while' statement requires a bool type guard");
			}
		}
;

delete[String funcName]
	: ^(DELETE tp=expression[funcName])
		{
			if (!($tp.t instanceof Struct))
			{
				error(0, "delete requires a struct argument");
			}
		}
;

ret[String funcName]
	: ^(RETURN (tp=expression[funcName])?)
	{
		Type returnType = funcs.get(funcName).getRetType();
		
		if (!(($tp.t instanceof Null) && returnType instanceof Struct) && !(returnType.equals($tp.t)))
		{
			error(0, "incorrect return type from function '" + funcName + "'");
		}
	}
	| RETURN
		{
			if (!(funcs.get(funcName).getRetType().equals(new Void())))
			{
				error(0, "incorrect return type from function '" + funcName + "'");
			}
		}
;

invocation[String funcName] returns [Type t = null]
	: ^(INVOKE id=ID args=arguments[funcName])
		{ 
			if (args == null)
			{
				if (funcs.get($id.text).getParams().size() != 0)
				{
					error($id.line, "Number of args in call to '" + $id + "' is incorrect");
				}
			}
			else if (args.size() != funcs.get($id.text).getParams().size())
			{
				error($id.line, "Number of args in call to '" + $id + "' is incorrect");
			}
			
			$t = funcs.get($id.text).getRetType();
		}
;

arguments[String funcName] returns [ArrayList<Type> argTypes = null]
	: {
	      $argTypes = new ArrayList<Type>();
	  } ^(ARGS (tp=expression[funcName] { $argTypes.add($tp.t); })+)
	| ARGS
;

expression[String funcName] returns [Type t = null]
	: ^(AND tp1=expression[funcName] tp2=expression[funcName])
		{
			if (!($tp1.t instanceof Bool) || !($tp2.t instanceof Bool))
			{
				error(0, "'and' operation requires bool expressions");
			}
			else
			{
				$t = Type.boolType();
			}
		}
	| ^(OR tp1=expression[funcName] tp2=expression[funcName])
		{
			if (!($tp1.t instanceof Bool) || !($tp2.t instanceof Bool))
			{
				error(0, "'or' operation requires bool expressions");
			}
			else
			{
				$t = Type.boolType();
			}
		}
	| ^(EQ tp1=expression[funcName] tp2=expression[funcName])
		{
//			if (!(($tp1.t instanceof Int) && ($tp2.t instanceof Int)) && !(($tp1.t instanceof Struct) && ($tp2.t instanceof Struct)))
//			{
//				error(0, "'equals' operator requires int or struct operands");
//			}
//			else if (!(($tp1.t).equals($tp2.t)))
//			{
//				error(0, "'equals' operator requires that operands both be ints or structs");
//			}
//			else
//			{
//				$t = Type.boolType();
//			}
			if (($tp1.t).equals($tp2.t) || ($tp1.t instanceof Struct && $tp2.t instanceof Null))
			{
				$t = Type.boolType();
			}
			else
			{
				error(0, "'equals' operator requires that operands both be same type");
			}
		}
	| ^(LT tp1=expression[funcName] tp2=expression[funcName])
		{
//			if (!($tp1.t instanceof Int) || !($tp2.t instanceof Int))
//			{
//				error(0, "'less than' operation requires int expressions");
//			}
			if (!(($tp1.t).equals($tp2.t)))
			{
				error(0, "'less than' operator requires that operands both be same type");
			}
			else
			{
				$t = Type.boolType();
			}
		}
	| ^(GT tp1=expression[funcName] tp2=expression[funcName])
		{
//			if (!($tp1.t instanceof Int) || !($tp2.t instanceof Int))
//			{
//				error(0, "'greater than' operation requires int expressions");
//			}
			if (!(($tp1.t).equals($tp2.t)))
			{
				error(0, "'greater than' operator requires that operands both be same type");
			}
			else
			{
				$t = Type.boolType();
			}
		}
	| ^(NE tp1=expression[funcName] tp2=expression[funcName])
		{
//			if (!($tp1.t instanceof Int) || !($tp2.t instanceof Int))
//			{
//				error(0, "'not equals' operation requires int expressions");
//			}
			if (($tp1.t).equals($tp2.t) || ($tp1.t instanceof Struct && $tp2.t instanceof Null))
			{
				$t = Type.boolType();
			}
//			if (!(($tp1.t).equals($tp2.t)))
			else
			{
				error(0, "'not equals' operator requires that operands both be same type");
			}
//			else
//			{
//				$t = Type.boolType();
//			}
		}
	| ^(LE tp1=expression[funcName] tp2=expression[funcName])
		{
//			if (!($tp1.t instanceof Int) || !($tp2.t instanceof Int))
//			{
//				error(0, "'less than or equal to' operation requires int expressions");
//			}
			if (!(($tp1.t).equals($tp2.t)))
			{
				error(0, "'less than or equal to' operator requires that operands both be same type");
			}
			else
			{
				$t = Type.boolType();
			}
		}
	| ^(GE tp1=expression[funcName] tp2=expression[funcName])
		{
//			if (!($tp1.t instanceof Int) || !($tp2.t instanceof Int))
//			{
//				error(0, "'greater than or equal to' operation requires int expressions");
//			}
			if (!(($tp1.t).equals($tp2.t)))
			{
				error(0, "'greater than or equal to' operator requires that operands both be same type");
			}
			else
			{
				$t = Type.boolType();
			}
		}
	| ^(PLUS tp1=expression[funcName] tp2=expression[funcName])
		{
			if (!($tp1.t instanceof Int) || !($tp2.t instanceof Int))
			{
				error(0, "'plus' operation requires int expressions");
			}
			else
			{
				$t = Type.intType();
			}
		}
	| ^(MINUS tp1=expression[funcName] tp2=expression[funcName])
		{
			if (!($tp1.t instanceof Int) || !($tp2.t instanceof Int))
			{
				error(0, "'minus' operation requires int expressions");
			}
			else
			{
				$t = Type.intType();
			}
		}
	| ^(TIMES tp1=expression[funcName] tp2=expression[funcName])
		{
			if (!($tp1.t instanceof Int) || !($tp2.t instanceof Int))
			{
				error(0, "'times' operation requires int expressions");
			}
			else
			{
				$t = Type.intType();
			}
		}
	| ^(DIVIDE tp1=expression[funcName] tp2=expression[funcName])
		{
			if (!($tp1.t instanceof Int) || !($tp2.t instanceof Int))
			{
				error(0, "'divide' operation requires int expressions");
			}
			else
			{
				$t = Type.intType();
			}
		}
	| ^(NOT tp=expression[funcName])
		{
			if (!($tp.t instanceof Bool))
			{
				error(0, "'not' operator requires bool expression");
			}
			else
			{
				$t = Type.boolType();
			}
		}
	| ^(NEG tp=expression[funcName])
		{
			if (!($tp.t instanceof Int))
			{
				error(0, "'neg' operator requires int expression");
			}
			else
			{
				$t = Type.intType();
			}
		}
	| ^(DOT tp=expression[funcName] id=ID)
		{
			if (!($tp.t instanceof Struct))
			{
				error($id.line, "invalid type for 'dot' operator. Requires 'struct' type.");
			}
			else if (!(sTypes.containsVal(((Struct)$tp.t).structName(), $id.text)))
			{
				error($id.line, "struct val ' " + ((Struct)$tp.t).structName() + "' not defined"); 
			}
			else
			{
				$t=sTypes.getValType(((Struct)$tp.t).structName(), $id.text);
			}
		}
	| ^(INVOKE id=ID args=arguments[funcName])
		{
			if (args == null)
			{
				if (funcs.get($id.text).getParams().size() != 0)
				{
					error($id.line, "Number of args in call to '" + $id + "' is incorrect");
				}
			}
			else if (args.size() != funcs.get($id.text).getParams().size())
			{
				error($id.line, "Number of args in call to '" + $id + "' is incorrect");
			}
			
			$t = funcs.get($id.text).getRetType();
		}
	| id=ID 
		{
			if (funcs.get(funcName).containsParam($id.text))
			{
				$t=funcs.get(funcName).paramType($id.text);
			}
			else if (sTable.varPrevDefined(funcName, $id.text))
			{
				$t=sTable.getVarType(funcName, $id.text);
			}
			else if (sTable.varPrevDefined("global", $id.text))
			{
				$t=sTable.getVarType("global", $id.text);
			}
			else
			{
				error($id.line, "variable '" + $id + "' was never initialized");
			}
		}
	| INTEGER { $t = Type.intType(); }
	| TRUE { $t = Type.boolType(); }
	| FALSE { $t = Type.boolType(); }
	| ^(NEW id=ID) 
		{
			if (sTypes.isDefined($id.text))
			{
				$t = Type.structType($id.text);
			}
			else
			{
				error($id.line, "'" + $id + "' is not a valid struct type");
			}
		}
	| VOID { $t = Type.voidType(); }
	| NULL { $t = Type.nullType(); }
;

