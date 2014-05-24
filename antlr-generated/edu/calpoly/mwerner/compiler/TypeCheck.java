// $ANTLR 3.5.2 C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g 2014-05-23 09:05:46

   package edu.calpoly.mwerner.compiler;

   import java.util.Map;
   import java.util.HashMap;
   import java.util.Iterator;


import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class TypeCheck extends TreeParser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "AND", "ARGS", "ASSIGN", "BLOCK", 
		"BOOL", "COMMA", "COMMENT", "DECL", "DECLLIST", "DECLS", "DELETE", "DIVIDE", 
		"DOT", "ELSE", "ENDL", "EQ", "FALSE", "FUN", "FUNCS", "GE", "GT", "ID", 
		"IF", "INT", "INTEGER", "INVOKE", "LBRACE", "LE", "LPAREN", "LT", "MINUS", 
		"NE", "NEG", "NEW", "NOT", "NULL", "OR", "PARAMS", "PLUS", "PRINT", "PROGRAM", 
		"RBRACE", "READ", "RETTYPE", "RETURN", "RPAREN", "SEMI", "STMTS", "STRUCT", 
		"TIMES", "TRUE", "TYPE", "TYPES", "VOID", "WHILE", "WS"
	};
	public static final int EOF=-1;
	public static final int AND=4;
	public static final int ARGS=5;
	public static final int ASSIGN=6;
	public static final int BLOCK=7;
	public static final int BOOL=8;
	public static final int COMMA=9;
	public static final int COMMENT=10;
	public static final int DECL=11;
	public static final int DECLLIST=12;
	public static final int DECLS=13;
	public static final int DELETE=14;
	public static final int DIVIDE=15;
	public static final int DOT=16;
	public static final int ELSE=17;
	public static final int ENDL=18;
	public static final int EQ=19;
	public static final int FALSE=20;
	public static final int FUN=21;
	public static final int FUNCS=22;
	public static final int GE=23;
	public static final int GT=24;
	public static final int ID=25;
	public static final int IF=26;
	public static final int INT=27;
	public static final int INTEGER=28;
	public static final int INVOKE=29;
	public static final int LBRACE=30;
	public static final int LE=31;
	public static final int LPAREN=32;
	public static final int LT=33;
	public static final int MINUS=34;
	public static final int NE=35;
	public static final int NEG=36;
	public static final int NEW=37;
	public static final int NOT=38;
	public static final int NULL=39;
	public static final int OR=40;
	public static final int PARAMS=41;
	public static final int PLUS=42;
	public static final int PRINT=43;
	public static final int PROGRAM=44;
	public static final int RBRACE=45;
	public static final int READ=46;
	public static final int RETTYPE=47;
	public static final int RETURN=48;
	public static final int RPAREN=49;
	public static final int SEMI=50;
	public static final int STMTS=51;
	public static final int STRUCT=52;
	public static final int TIMES=53;
	public static final int TRUE=54;
	public static final int TYPE=55;
	public static final int TYPES=56;
	public static final int VOID=57;
	public static final int WHILE=58;
	public static final int WS=59;

	// delegates
	public TreeParser[] getDelegates() {
		return new TreeParser[] {};
	}

	// delegators


	public TypeCheck(TreeNodeStream input) {
		this(input, new RecognizerSharedState());
	}
	public TypeCheck(TreeNodeStream input, RecognizerSharedState state) {
		super(input, state);
	}

	@Override public String[] getTokenNames() { return TypeCheck.tokenNames; }
	@Override public String getGrammarFileName() { return "C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g"; }

	  
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



	// $ANTLR start "program"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:41:1: program : ^( PROGRAM types declarations[\"global\"] functions ) EOF ;
	public final void program() throws RecognitionException {
		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:42:2: ( ^( PROGRAM types declarations[\"global\"] functions ) EOF )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:42:4: ^( PROGRAM types declarations[\"global\"] functions ) EOF
			{
			match(input,PROGRAM,FOLLOW_PROGRAM_in_program46); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_types_in_program48);
			types();
			state._fsp--;

			pushFollow(FOLLOW_declarations_in_program50);
			declarations("global");
			state._fsp--;

			pushFollow(FOLLOW_functions_in_program53);
			functions();
			state._fsp--;

			match(input, Token.UP, null); 

			match(input,EOF,FOLLOW_EOF_in_program56); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "program"



	// $ANTLR start "types"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:45:1: types : ^( TYPES ( struct )* ) ;
	public final void types() throws RecognitionException {
		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:46:2: ( ^( TYPES ( struct )* ) )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:46:4: ^( TYPES ( struct )* )
			{
			match(input,TYPES,FOLLOW_TYPES_in_types67); 
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); 
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:46:12: ( struct )*
				loop1:
				while (true) {
					int alt1=2;
					int LA1_0 = input.LA(1);
					if ( (LA1_0==STRUCT) ) {
						alt1=1;
					}

					switch (alt1) {
					case 1 :
						// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:46:12: struct
						{
						pushFollow(FOLLOW_struct_in_types69);
						struct();
						state._fsp--;

						}
						break;

					default :
						break loop1;
					}
				}

				match(input, Token.UP, null); 
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "types"



	// $ANTLR start "declarations"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:49:1: declarations[String scopeName] : ( ^( DECLS declaration[scopeName] ) |);
	public final void declarations(String scopeName) throws RecognitionException {
		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:50:2: ( ^( DECLS declaration[scopeName] ) |)
			int alt2=2;
			int LA2_0 = input.LA(1);
			if ( (LA2_0==DECLS) ) {
				alt2=1;
			}
			else if ( (LA2_0==FUNCS||LA2_0==STMTS) ) {
				alt2=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 2, 0, input);
				throw nvae;
			}

			switch (alt2) {
				case 1 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:50:4: ^( DECLS declaration[scopeName] )
					{
					match(input,DECLS,FOLLOW_DECLS_in_declarations83); 
					if ( input.LA(1)==Token.DOWN ) {
						match(input, Token.DOWN, null); 
						pushFollow(FOLLOW_declaration_in_declarations85);
						declaration(scopeName);
						state._fsp--;

						match(input, Token.UP, null); 
					}

					}
					break;
				case 2 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:51:4: 
					{
					 System.out.println("There are no declarations"); 
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "declarations"



	// $ANTLR start "functions"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:54:1: functions : ^( FUNCS ( function )* ) ;
	public final void functions() throws RecognitionException {
		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:55:2: ( ^( FUNCS ( function )* ) )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:55:4: ^( FUNCS ( function )* )
			{
			match(input,FUNCS,FOLLOW_FUNCS_in_functions103); 
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); 
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:55:12: ( function )*
				loop3:
				while (true) {
					int alt3=2;
					int LA3_0 = input.LA(1);
					if ( (LA3_0==FUN) ) {
						alt3=1;
					}

					switch (alt3) {
					case 1 :
						// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:55:12: function
						{
						pushFollow(FOLLOW_function_in_functions105);
						function();
						state._fsp--;

						}
						break;

					default :
						break loop3;
					}
				}

				match(input, Token.UP, null); 
			}


						if (!funcs.containsKey("main"))
						{
							error(0, "No main method defined");
						}
						else if (!(funcs.get("main").returnType() instanceof Int))
						{
							error(0, "main method specifies an incorrect return type.  should be of type int");
						}
					
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "functions"



	// $ANTLR start "struct"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:68:1: struct : ^( STRUCT id= ID ( decs[structVars, ValCategory.STRUCT] )+ ) ;
	public final void struct() throws RecognitionException {
		CommonTree id=null;

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:69:2: ( ^( STRUCT id= ID ( decs[structVars, ValCategory.STRUCT] )+ ) )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:69:4: ^( STRUCT id= ID ( decs[structVars, ValCategory.STRUCT] )+ )
			{
			match(input,STRUCT,FOLLOW_STRUCT_in_struct122); 
			match(input, Token.DOWN, null); 
			id=(CommonTree)match(input,ID,FOLLOW_ID_in_struct126); 

						if (sTypes.isDefined((id!=null?id.getText():null)))
						{
							error((id!=null?id.getLine():0), "redefinition of struct ’" + id + "’");
						}
						HashMap<String, Type> structVars = new HashMap<String, Type>();
						sTypes.addStruct((id!=null?id.getText():null), structVars);
					
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:77:5: ( decs[structVars, ValCategory.STRUCT] )+
			int cnt4=0;
			loop4:
			while (true) {
				int alt4=2;
				int LA4_0 = input.LA(1);
				if ( (LA4_0==DECL) ) {
					alt4=1;
				}

				switch (alt4) {
				case 1 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:77:6: decs[structVars, ValCategory.STRUCT]
					{
					pushFollow(FOLLOW_decs_in_struct134);
					decs(structVars, ValCategory.STRUCT);
					state._fsp--;

					}
					break;

				default :
					if ( cnt4 >= 1 ) break loop4;
					EarlyExitException eee = new EarlyExitException(4, input);
					throw eee;
				}
				cnt4++;
			}

			match(input, Token.UP, null); 


						sTypes.addStruct((id!=null?id.getText():null), structVars); 
					
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "struct"



	// $ANTLR start "decs"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:83:1: decs[HashMap<String, Type> vals, ValCategory valCat] : ^( DECL ^( TYPE tp= type ) id= ID ) ;
	public final void decs(HashMap<String, Type> vals, ValCategory valCat) throws RecognitionException {
		CommonTree id=null;
		Type tp =null;

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:84:2: ( ^( DECL ^( TYPE tp= type ) id= ID ) )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:84:4: ^( DECL ^( TYPE tp= type ) id= ID )
			{
			match(input,DECL,FOLLOW_DECL_in_decs156); 
			match(input, Token.DOWN, null); 
			match(input,TYPE,FOLLOW_TYPE_in_decs159); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_type_in_decs163);
			tp=type();
			state._fsp--;

			match(input, Token.UP, null); 

			id=(CommonTree)match(input,ID,FOLLOW_ID_in_decs168); 
			match(input, Token.UP, null); 

			 
						if (vals.containsKey((id!=null?id.getText():null)))
						{
							// Call came from struct rule
							if (valCat == ValCategory.STRUCT)
							{
								error((id!=null?id.getLine():0), "redefinition of struct variable '" + id + "'");
							}
							// Call came from parameters rule
							else
							{
								error((id!=null?id.getLine():0), "redefinition of parameter '" + id + "'");
							}
						}
						vals.put((id!=null?id.getText():null), tp);
					
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "decs"



	// $ANTLR start "type"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:103:1: type returns [Type t = null] : ( INT | BOOL | ^( STRUCT id= ID ) );
	public final Type type() throws RecognitionException {
		Type t =  null;


		CommonTree id=null;

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:104:2: ( INT | BOOL | ^( STRUCT id= ID ) )
			int alt5=3;
			switch ( input.LA(1) ) {
			case INT:
				{
				alt5=1;
				}
				break;
			case BOOL:
				{
				alt5=2;
				}
				break;
			case STRUCT:
				{
				alt5=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 5, 0, input);
				throw nvae;
			}
			switch (alt5) {
				case 1 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:104:4: INT
					{
					match(input,INT,FOLLOW_INT_in_type188); 
					 t = Type.intType(); 
					}
					break;
				case 2 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:105:4: BOOL
					{
					match(input,BOOL,FOLLOW_BOOL_in_type195); 
					 t = Type.boolType(); 
					}
					break;
				case 3 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:106:4: ^( STRUCT id= ID )
					{
					match(input,STRUCT,FOLLOW_STRUCT_in_type203); 
					match(input, Token.DOWN, null); 
					id=(CommonTree)match(input,ID,FOLLOW_ID_in_type207); 
					match(input, Token.UP, null); 


								if (!sTypes.isDefined((id!=null?id.getText():null)))
								{
									error((id!=null?id.getLine():0), "undefined struct type '" + id + "'");
								}
								t = Type.structType((id!=null?id.getText():null));
							
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return t;
	}
	// $ANTLR end "type"



	// $ANTLR start "declaration"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:116:1: declaration[String scopeName] : ( decl_list[scopeName] )* ;
	public final void declaration(String scopeName) throws RecognitionException {
		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:117:2: ( ( decl_list[scopeName] )* )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:117:4: ( decl_list[scopeName] )*
			{
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:117:4: ( decl_list[scopeName] )*
			loop6:
			while (true) {
				int alt6=2;
				int LA6_0 = input.LA(1);
				if ( (LA6_0==DECLLIST) ) {
					alt6=1;
				}

				switch (alt6) {
				case 1 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:117:5: decl_list[scopeName]
					{
					pushFollow(FOLLOW_decl_list_in_declaration224);
					decl_list(scopeName);
					state._fsp--;

					}
					break;

				default :
					break loop6;
				}
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "declaration"



	// $ANTLR start "decl_list"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:120:1: decl_list[String scopeName] : ^( DECLLIST ^( TYPE tp= type ) line= id_list[varNames] ) ;
	public final void decl_list(String scopeName) throws RecognitionException {
		Type tp =null;
		int line =0;

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:121:2: ( ^( DECLLIST ^( TYPE tp= type ) line= id_list[varNames] ) )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:121:4: ^( DECLLIST ^( TYPE tp= type ) line= id_list[varNames] )
			{
			 ArrayList<String> varNames = new ArrayList<String>(); 
			match(input,DECLLIST,FOLLOW_DECLLIST_in_decl_list241); 
			match(input, Token.DOWN, null); 
			match(input,TYPE,FOLLOW_TYPE_in_decl_list244); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_type_in_decl_list248);
			tp=type();
			state._fsp--;

			match(input, Token.UP, null); 

			pushFollow(FOLLOW_id_list_in_decl_list253);
			line=id_list(varNames);
			state._fsp--;

			match(input, Token.UP, null); 

			 
						for (String name : varNames)
						{
							if (!sTable.scopePrevDefined(scopeName))
							{
								sTable.addScope(scopeName);
								sTable.addVar(scopeName, name, tp);
							}	
							else if (!sTable.varPrevDefined(scopeName, name))
							{
								sTable.addVar(scopeName, name, tp);
							}
							else
							{
								error(line, "redefinition of variable '" + name + "'");
							}
						}
					
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "decl_list"



	// $ANTLR start "id_list"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:142:1: id_list[ArrayList<String> varNames] returns [int line = -1] : (id= ID )+ ;
	public final int id_list(ArrayList<String> varNames) throws RecognitionException {
		int line =  -1;


		CommonTree id=null;

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:143:2: ( (id= ID )+ )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:143:4: (id= ID )+
			{
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:143:4: (id= ID )+
			int cnt7=0;
			loop7:
			while (true) {
				int alt7=2;
				int LA7_0 = input.LA(1);
				if ( (LA7_0==ID) ) {
					alt7=1;
				}

				switch (alt7) {
				case 1 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:143:5: id= ID
					{
					id=(CommonTree)match(input,ID,FOLLOW_ID_in_id_list278); 
					 varNames.add((id!=null?id.getText():null)); 
					}
					break;

				default :
					if ( cnt7 >= 1 ) break loop7;
					EarlyExitException eee = new EarlyExitException(7, input);
					throw eee;
				}
				cnt7++;
			}

			 line = (id!=null?id.getLine():0); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return line;
	}
	// $ANTLR end "id_list"



	// $ANTLR start "function"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:146:1: function : ^( FUN id= ID parameters[params] ^( RETTYPE tp= return_type ) declarations[$id.text] statement_list[$id.text] ) ;
	public final void function() throws RecognitionException {
		CommonTree id=null;
		Type tp =null;

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:147:2: ( ^( FUN id= ID parameters[params] ^( RETTYPE tp= return_type ) declarations[$id.text] statement_list[$id.text] ) )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:147:4: ^( FUN id= ID parameters[params] ^( RETTYPE tp= return_type ) declarations[$id.text] statement_list[$id.text] )
			{
			match(input,FUN,FOLLOW_FUN_in_function295); 
			match(input, Token.DOWN, null); 
			id=(CommonTree)match(input,ID,FOLLOW_ID_in_function299); 

						HashMap<String, Type> params = new HashMap<String, Type>();
					
			pushFollow(FOLLOW_parameters_in_function306);
			parameters(params);
			state._fsp--;

			match(input,RETTYPE,FOLLOW_RETTYPE_in_function310); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_return_type_in_function314);
			tp=return_type();
			state._fsp--;

			match(input, Token.UP, null); 


					 		if (funcs.containsKey((id!=null?id.getText():null)))
					 		{
					 			error((id!=null?id.getLine():0), "redefinition of function '" + id + "'");
					 		}
					 		funcs.put((id!=null?id.getText():null), Type.funcType((id!=null?id.getText():null), params, tp));
					 		
					 		if (!sTable.scopePrevDefined((id!=null?id.getText():null)))
					 		{
					 			sTable.addScope((id!=null?id.getText():null));
					 		}
					 	
			pushFollow(FOLLOW_declarations_in_function323);
			declarations((id!=null?id.getText():null));
			state._fsp--;

			pushFollow(FOLLOW_statement_list_in_function326);
			statement_list((id!=null?id.getText():null));
			state._fsp--;

			match(input, Token.UP, null); 

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "function"



	// $ANTLR start "parameters"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:165:1: parameters[HashMap<String, Type> params] : ^( PARAMS ( decs[params, ValCategory.PARAMS] )* ) ;
	public final void parameters(HashMap<String, Type> params) throws RecognitionException {
		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:166:2: ( ^( PARAMS ( decs[params, ValCategory.PARAMS] )* ) )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:166:4: ^( PARAMS ( decs[params, ValCategory.PARAMS] )* )
			{
			match(input,PARAMS,FOLLOW_PARAMS_in_parameters340); 
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); 
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:166:13: ( decs[params, ValCategory.PARAMS] )*
				loop8:
				while (true) {
					int alt8=2;
					int LA8_0 = input.LA(1);
					if ( (LA8_0==DECL) ) {
						alt8=1;
					}

					switch (alt8) {
					case 1 :
						// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:166:14: decs[params, ValCategory.PARAMS]
						{
						pushFollow(FOLLOW_decs_in_parameters343);
						decs(params, ValCategory.PARAMS);
						state._fsp--;

						}
						break;

					default :
						break loop8;
					}
				}

				match(input, Token.UP, null); 
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "parameters"



	// $ANTLR start "return_type"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:169:1: return_type returns [Type t = null] : (tp= type | VOID );
	public final Type return_type() throws RecognitionException {
		Type t =  null;


		Type tp =null;

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:170:2: (tp= type | VOID )
			int alt9=2;
			int LA9_0 = input.LA(1);
			if ( (LA9_0==BOOL||LA9_0==INT||LA9_0==STRUCT) ) {
				alt9=1;
			}
			else if ( (LA9_0==VOID) ) {
				alt9=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 9, 0, input);
				throw nvae;
			}

			switch (alt9) {
				case 1 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:170:4: tp= type
					{
					pushFollow(FOLLOW_type_in_return_type364);
					tp=type();
					state._fsp--;

					t = tp; 
					}
					break;
				case 2 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:171:4: VOID
					{
					match(input,VOID,FOLLOW_VOID_in_return_type371); 
					 t = Type.voidType(); 
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return t;
	}
	// $ANTLR end "return_type"



	// $ANTLR start "statement_list"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:174:1: statement_list[String funcName] : ^( STMTS ( statement[funcName] )* ) ;
	public final void statement_list(String funcName) throws RecognitionException {
		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:175:2: ( ^( STMTS ( statement[funcName] )* ) )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:175:4: ^( STMTS ( statement[funcName] )* )
			{
			match(input,STMTS,FOLLOW_STMTS_in_statement_list385); 
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); 
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:175:12: ( statement[funcName] )*
				loop10:
				while (true) {
					int alt10=2;
					int LA10_0 = input.LA(1);
					if ( ((LA10_0 >= ASSIGN && LA10_0 <= BLOCK)||LA10_0==DELETE||LA10_0==IF||LA10_0==INVOKE||LA10_0==PRINT||LA10_0==READ||LA10_0==RETURN||LA10_0==WHILE) ) {
						alt10=1;
					}

					switch (alt10) {
					case 1 :
						// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:175:13: statement[funcName]
						{
						pushFollow(FOLLOW_statement_in_statement_list388);
						statement(funcName);
						state._fsp--;

						}
						break;

					default :
						break loop10;
					}
				}

				match(input, Token.UP, null); 
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "statement_list"



	// $ANTLR start "statement"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:178:1: statement[String funcName] : ( block[funcName] | assignment[funcName] | print[funcName] | read[funcName] | conditional[funcName] | loop[funcName] | delete[funcName] | ret[funcName] | invocation[funcName] );
	public final void statement(String funcName) throws RecognitionException {
		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:179:2: ( block[funcName] | assignment[funcName] | print[funcName] | read[funcName] | conditional[funcName] | loop[funcName] | delete[funcName] | ret[funcName] | invocation[funcName] )
			int alt11=9;
			switch ( input.LA(1) ) {
			case BLOCK:
				{
				alt11=1;
				}
				break;
			case ASSIGN:
				{
				alt11=2;
				}
				break;
			case PRINT:
				{
				alt11=3;
				}
				break;
			case READ:
				{
				alt11=4;
				}
				break;
			case IF:
				{
				alt11=5;
				}
				break;
			case WHILE:
				{
				alt11=6;
				}
				break;
			case DELETE:
				{
				alt11=7;
				}
				break;
			case RETURN:
				{
				alt11=8;
				}
				break;
			case INVOKE:
				{
				alt11=9;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 11, 0, input);
				throw nvae;
			}
			switch (alt11) {
				case 1 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:179:4: block[funcName]
					{
					pushFollow(FOLLOW_block_in_statement403);
					block(funcName);
					state._fsp--;

					}
					break;
				case 2 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:180:4: assignment[funcName]
					{
					pushFollow(FOLLOW_assignment_in_statement409);
					assignment(funcName);
					state._fsp--;

					}
					break;
				case 3 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:181:4: print[funcName]
					{
					pushFollow(FOLLOW_print_in_statement415);
					print(funcName);
					state._fsp--;

					}
					break;
				case 4 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:182:4: read[funcName]
					{
					pushFollow(FOLLOW_read_in_statement421);
					read(funcName);
					state._fsp--;

					}
					break;
				case 5 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:183:4: conditional[funcName]
					{
					pushFollow(FOLLOW_conditional_in_statement427);
					conditional(funcName);
					state._fsp--;

					}
					break;
				case 6 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:184:4: loop[funcName]
					{
					pushFollow(FOLLOW_loop_in_statement433);
					loop(funcName);
					state._fsp--;

					}
					break;
				case 7 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:185:4: delete[funcName]
					{
					pushFollow(FOLLOW_delete_in_statement439);
					delete(funcName);
					state._fsp--;

					}
					break;
				case 8 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:186:4: ret[funcName]
					{
					pushFollow(FOLLOW_ret_in_statement445);
					ret(funcName);
					state._fsp--;

					}
					break;
				case 9 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:187:4: invocation[funcName]
					{
					pushFollow(FOLLOW_invocation_in_statement451);
					invocation(funcName);
					state._fsp--;

					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "statement"



	// $ANTLR start "block"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:190:1: block[String funcName] : ^( BLOCK statement_list[funcName] ) ;
	public final void block(String funcName) throws RecognitionException {
		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:191:2: ( ^( BLOCK statement_list[funcName] ) )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:191:4: ^( BLOCK statement_list[funcName] )
			{
			match(input,BLOCK,FOLLOW_BLOCK_in_block464); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_statement_list_in_block466);
			statement_list(funcName);
			state._fsp--;

			match(input, Token.UP, null); 

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "block"



	// $ANTLR start "assignment"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:194:1: assignment[String funcName] : ^( ASSIGN tp1= expression[funcName] tp2= lvalue[funcName] ) ;
	public final void assignment(String funcName) throws RecognitionException {
		Type tp1 =null;
		Type tp2 =null;

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:195:2: ( ^( ASSIGN tp1= expression[funcName] tp2= lvalue[funcName] ) )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:195:4: ^( ASSIGN tp1= expression[funcName] tp2= lvalue[funcName] )
			{
			match(input,ASSIGN,FOLLOW_ASSIGN_in_assignment480); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_expression_in_assignment484);
			tp1=expression(funcName);
			state._fsp--;

			pushFollow(FOLLOW_lvalue_in_assignment489);
			tp2=lvalue(funcName);
			state._fsp--;

			match(input, Token.UP, null); 


						if ((tp2 instanceof Struct) && !((tp1 instanceof Struct) || (tp1 instanceof Null)))
						{
							error(0, "assignment to struct requires another struct or null");
						}
						else if (!((tp1).equals(tp2)))
						{
							System.out.println(tp1 + " " + tp2);
							error(0, "assignment types mismatch");
						}
					
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "assignment"



	// $ANTLR start "lvalue"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:209:1: lvalue[String funcName] returns [Type t = null] : ( ^( DOT tp= lvalue[funcName] id= ID ) |id= ID );
	public final Type lvalue(String funcName) throws RecognitionException {
		Type t =  null;


		CommonTree id=null;
		Type tp =null;

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:210:2: ( ^( DOT tp= lvalue[funcName] id= ID ) |id= ID )
			int alt12=2;
			int LA12_0 = input.LA(1);
			if ( (LA12_0==DOT) ) {
				alt12=1;
			}
			else if ( (LA12_0==ID) ) {
				alt12=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 12, 0, input);
				throw nvae;
			}

			switch (alt12) {
				case 1 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:210:4: ^( DOT tp= lvalue[funcName] id= ID )
					{
					match(input,DOT,FOLLOW_DOT_in_lvalue511); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_lvalue_in_lvalue515);
					tp=lvalue(funcName);
					state._fsp--;

					id=(CommonTree)match(input,ID,FOLLOW_ID_in_lvalue520); 
					match(input, Token.UP, null); 


								if (!sTypes.containsVal(((Struct)tp).structName(), (id!=null?id.getText():null)))
								{
									error((id!=null?id.getLine():0), "Variable '" + (id!=null?id.getText():null) + "' not defined in struct '" + ((Struct)tp).structName() + "'");
								}
								else
								{
									//System.out.println("Struct: " + ((Struct)tp).structName() + " Var: " + (id!=null?id.getText():null) + " Type: " + sTypes.getValType(((Struct)tp).structName(), (id!=null?id.getText():null)));
									return sTypes.getValType(((Struct)tp).structName(), (id!=null?id.getText():null));
								}
							
					}
					break;
				case 2 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:222:4: id= ID
					{
					id=(CommonTree)match(input,ID,FOLLOW_ID_in_lvalue532); 

								if (funcs.get(funcName).containsParam((id!=null?id.getText():null)))
								{
									t =funcs.get(funcName).paramType((id!=null?id.getText():null));
								}
								else if (sTable.varPrevDefined(funcName, (id!=null?id.getText():null)))
								{
									t =sTable.getVarType(funcName, (id!=null?id.getText():null));
									
								}
								else if (sTable.varPrevDefined("global", (id!=null?id.getText():null)))
								{
									t =sTable.getVarType("global", (id!=null?id.getText():null));
								}
								else
								{
									error((id!=null?id.getLine():0), "variable '" + id + "' was never initialized");
								}
							
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return t;
	}
	// $ANTLR end "lvalue"



	// $ANTLR start "print"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:244:1: print[String funcName] : ^( PRINT tp= expression[funcName] ( ENDL )? ) ;
	public final void print(String funcName) throws RecognitionException {
		Type tp =null;

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:245:2: ( ^( PRINT tp= expression[funcName] ( ENDL )? ) )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:245:4: ^( PRINT tp= expression[funcName] ( ENDL )? )
			{
			match(input,PRINT,FOLLOW_PRINT_in_print548); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_expression_in_print552);
			tp=expression(funcName);
			state._fsp--;

			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:245:36: ( ENDL )?
			int alt13=2;
			int LA13_0 = input.LA(1);
			if ( (LA13_0==ENDL) ) {
				alt13=1;
			}
			switch (alt13) {
				case 1 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:245:37: ENDL
					{
					match(input,ENDL,FOLLOW_ENDL_in_print556); 
					}
					break;

			}

			match(input, Token.UP, null); 


						if (!(tp instanceof Int))
						{
							error(0, "print requires an integer argument");
						} 
					
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "print"



	// $ANTLR start "read"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:254:1: read[String funcName] : ^( READ tp= lvalue[funcName] ) ;
	public final void read(String funcName) throws RecognitionException {
		Type tp =null;

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:255:2: ( ^( READ tp= lvalue[funcName] ) )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:255:4: ^( READ tp= lvalue[funcName] )
			{
			match(input,READ,FOLLOW_READ_in_read575); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_lvalue_in_read579);
			tp=lvalue(funcName);
			state._fsp--;

			match(input, Token.UP, null); 


						if (!(tp instanceof Int))
						{
							error(0, "read requires an integer argument");
						}
					
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "read"



	// $ANTLR start "conditional"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:264:1: conditional[String funcName] : ^( IF tp= expression[funcName] block[funcName] ( block[funcName] )? ) ;
	public final void conditional(String funcName) throws RecognitionException {
		Type tp =null;

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:265:2: ( ^( IF tp= expression[funcName] block[funcName] ( block[funcName] )? ) )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:265:4: ^( IF tp= expression[funcName] block[funcName] ( block[funcName] )? )
			{
			match(input,IF,FOLLOW_IF_in_conditional597); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_expression_in_conditional601);
			tp=expression(funcName);
			state._fsp--;

			pushFollow(FOLLOW_block_in_conditional604);
			block(funcName);
			state._fsp--;

			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:265:49: ( block[funcName] )?
			int alt14=2;
			int LA14_0 = input.LA(1);
			if ( (LA14_0==BLOCK) ) {
				alt14=1;
			}
			switch (alt14) {
				case 1 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:265:50: block[funcName]
					{
					pushFollow(FOLLOW_block_in_conditional608);
					block(funcName);
					state._fsp--;

					}
					break;

			}

			match(input, Token.UP, null); 


						if (!(tp instanceof Bool))
						{
							error(0, "'if' statement requires a bool type guard");
						}
					
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "conditional"



	// $ANTLR start "loop"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:274:1: loop[String funcName] : ^( WHILE tp= expression[funcName] block[funcName] expression[funcName] ) ;
	public final void loop(String funcName) throws RecognitionException {
		Type tp =null;

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:275:2: ( ^( WHILE tp= expression[funcName] block[funcName] expression[funcName] ) )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:275:4: ^( WHILE tp= expression[funcName] block[funcName] expression[funcName] )
			{
			match(input,WHILE,FOLLOW_WHILE_in_loop628); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_expression_in_loop632);
			tp=expression(funcName);
			state._fsp--;

			pushFollow(FOLLOW_block_in_loop635);
			block(funcName);
			state._fsp--;

			pushFollow(FOLLOW_expression_in_loop638);
			expression(funcName);
			state._fsp--;

			match(input, Token.UP, null); 


						if (!(tp instanceof Bool))
						{
							error(0, "'while' statement requires a bool type guard");
						}
					
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "loop"



	// $ANTLR start "delete"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:284:1: delete[String funcName] : ^( DELETE tp= expression[funcName] ) ;
	public final void delete(String funcName) throws RecognitionException {
		Type tp =null;

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:285:2: ( ^( DELETE tp= expression[funcName] ) )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:285:4: ^( DELETE tp= expression[funcName] )
			{
			match(input,DELETE,FOLLOW_DELETE_in_delete656); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_expression_in_delete660);
			tp=expression(funcName);
			state._fsp--;

			match(input, Token.UP, null); 


						if (!(tp instanceof Struct))
						{
							error(0, "delete requires a struct argument");
						}
					
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "delete"



	// $ANTLR start "ret"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:294:1: ret[String funcName] : ( ^( RETURN (tp= expression[funcName] )? ) | RETURN );
	public final void ret(String funcName) throws RecognitionException {
		Type tp =null;

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:295:2: ( ^( RETURN (tp= expression[funcName] )? ) | RETURN )
			int alt16=2;
			int LA16_0 = input.LA(1);
			if ( (LA16_0==RETURN) ) {
				int LA16_1 = input.LA(2);
				if ( (LA16_1==DOWN) ) {
					alt16=1;
				}
				else if ( (LA16_1==UP||(LA16_1 >= ASSIGN && LA16_1 <= BLOCK)||LA16_1==DELETE||LA16_1==IF||LA16_1==INVOKE||LA16_1==PRINT||LA16_1==READ||LA16_1==RETURN||LA16_1==WHILE) ) {
					alt16=2;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 16, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 16, 0, input);
				throw nvae;
			}

			switch (alt16) {
				case 1 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:295:4: ^( RETURN (tp= expression[funcName] )? )
					{
					match(input,RETURN,FOLLOW_RETURN_in_ret678); 
					if ( input.LA(1)==Token.DOWN ) {
						match(input, Token.DOWN, null); 
						// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:295:13: (tp= expression[funcName] )?
						int alt15=2;
						int LA15_0 = input.LA(1);
						if ( (LA15_0==AND||(LA15_0 >= DIVIDE && LA15_0 <= DOT)||(LA15_0 >= EQ && LA15_0 <= FALSE)||(LA15_0 >= GE && LA15_0 <= ID)||(LA15_0 >= INTEGER && LA15_0 <= INVOKE)||LA15_0==LE||(LA15_0 >= LT && LA15_0 <= OR)||LA15_0==PLUS||(LA15_0 >= TIMES && LA15_0 <= TRUE)||LA15_0==VOID) ) {
							alt15=1;
						}
						switch (alt15) {
							case 1 :
								// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:295:14: tp= expression[funcName]
								{
								pushFollow(FOLLOW_expression_in_ret683);
								tp=expression(funcName);
								state._fsp--;

								}
								break;

						}

						match(input, Token.UP, null); 
					}


							if (!(funcs.get(funcName).getRetType().equals(tp)))
							{
								error(0, "incorrect return type from function '" + funcName + "'");
							}
						
					}
					break;
				case 2 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:302:4: RETURN
					{
					match(input,RETURN,FOLLOW_RETURN_in_ret695); 

								if (!(funcs.get(funcName).getRetType().equals(new Void())))
								{
									error(0, "incorrect return type from function '" + funcName + "'");
								}
							
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ret"



	// $ANTLR start "invocation"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:311:1: invocation[String funcName] returns [Type t = null] : ^( INVOKE id= ID args= arguments[funcName] ) ;
	public final Type invocation(String funcName) throws RecognitionException {
		Type t =  null;


		CommonTree id=null;
		ArrayList<Type> args =null;

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:312:2: ( ^( INVOKE id= ID args= arguments[funcName] ) )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:312:4: ^( INVOKE id= ID args= arguments[funcName] )
			{
			match(input,INVOKE,FOLLOW_INVOKE_in_invocation715); 
			match(input, Token.DOWN, null); 
			id=(CommonTree)match(input,ID,FOLLOW_ID_in_invocation719); 
			pushFollow(FOLLOW_arguments_in_invocation723);
			args=arguments(funcName);
			state._fsp--;

			match(input, Token.UP, null); 

			 
						if (args.size() != funcs.get((id!=null?id.getText():null)).getParams().size())
						{
							error((id!=null?id.getLine():0), "Number of args in call to '" + id + "' is incorrect");
						}
						
						t = funcs.get((id!=null?id.getText():null)).getRetType();
					
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return t;
	}
	// $ANTLR end "invocation"



	// $ANTLR start "arguments"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:323:1: arguments[String funcName] returns [ArrayList<Type> argTypes = null] : ( ^( ARGS (tp= expression[funcName] )+ ) | ARGS );
	public final ArrayList<Type> arguments(String funcName) throws RecognitionException {
		ArrayList<Type> argTypes =  null;


		Type tp =null;

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:324:2: ( ^( ARGS (tp= expression[funcName] )+ ) | ARGS )
			int alt18=2;
			int LA18_0 = input.LA(1);
			if ( (LA18_0==ARGS) ) {
				int LA18_1 = input.LA(2);
				if ( (LA18_1==DOWN) ) {
					alt18=1;
				}
				else if ( (LA18_1==UP) ) {
					alt18=2;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 18, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 18, 0, input);
				throw nvae;
			}

			switch (alt18) {
				case 1 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:324:4: ^( ARGS (tp= expression[funcName] )+ )
					{

						      argTypes = new ArrayList<Type>();
						  
					match(input,ARGS,FOLLOW_ARGS_in_arguments747); 
					match(input, Token.DOWN, null); 
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:326:13: (tp= expression[funcName] )+
					int cnt17=0;
					loop17:
					while (true) {
						int alt17=2;
						int LA17_0 = input.LA(1);
						if ( (LA17_0==AND||(LA17_0 >= DIVIDE && LA17_0 <= DOT)||(LA17_0 >= EQ && LA17_0 <= FALSE)||(LA17_0 >= GE && LA17_0 <= ID)||(LA17_0 >= INTEGER && LA17_0 <= INVOKE)||LA17_0==LE||(LA17_0 >= LT && LA17_0 <= OR)||LA17_0==PLUS||(LA17_0 >= TIMES && LA17_0 <= TRUE)||LA17_0==VOID) ) {
							alt17=1;
						}

						switch (alt17) {
						case 1 :
							// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:326:14: tp= expression[funcName]
							{
							pushFollow(FOLLOW_expression_in_arguments752);
							tp=expression(funcName);
							state._fsp--;

							 argTypes.add(tp); 
							}
							break;

						default :
							if ( cnt17 >= 1 ) break loop17;
							EarlyExitException eee = new EarlyExitException(17, input);
							throw eee;
						}
						cnt17++;
					}

					match(input, Token.UP, null); 

					}
					break;
				case 2 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:327:4: ARGS
					{
					match(input,ARGS,FOLLOW_ARGS_in_arguments763); 
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return argTypes;
	}
	// $ANTLR end "arguments"



	// $ANTLR start "expression"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:330:1: expression[String funcName] returns [Type t = null] : ( ^( AND tp1= expression[funcName] tp2= expression[funcName] ) | ^( OR tp1= expression[funcName] tp2= expression[funcName] ) | ^( EQ tp1= expression[funcName] tp2= expression[funcName] ) | ^( LT tp1= expression[funcName] tp2= expression[funcName] ) | ^( GT tp1= expression[funcName] tp2= expression[funcName] ) | ^( NE tp1= expression[funcName] tp2= expression[funcName] ) | ^( LE tp1= expression[funcName] tp2= expression[funcName] ) | ^( GE tp1= expression[funcName] tp2= expression[funcName] ) | ^( PLUS tp1= expression[funcName] tp2= expression[funcName] ) | ^( MINUS tp1= expression[funcName] tp2= expression[funcName] ) | ^( TIMES tp1= expression[funcName] tp2= expression[funcName] ) | ^( DIVIDE tp1= expression[funcName] tp2= expression[funcName] ) | ^( NOT tp= expression[funcName] ) | ^( NEG tp= expression[funcName] ) | ^( DOT tp= expression[funcName] id= ID ) | ^( INVOKE id= ID args= arguments[funcName] ) |id= ID | INTEGER | TRUE | FALSE | ^( NEW id= ID ) | VOID | NULL );
	public final Type expression(String funcName) throws RecognitionException {
		Type t =  null;


		CommonTree id=null;
		Type tp1 =null;
		Type tp2 =null;
		Type tp =null;
		ArrayList<Type> args =null;

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:331:2: ( ^( AND tp1= expression[funcName] tp2= expression[funcName] ) | ^( OR tp1= expression[funcName] tp2= expression[funcName] ) | ^( EQ tp1= expression[funcName] tp2= expression[funcName] ) | ^( LT tp1= expression[funcName] tp2= expression[funcName] ) | ^( GT tp1= expression[funcName] tp2= expression[funcName] ) | ^( NE tp1= expression[funcName] tp2= expression[funcName] ) | ^( LE tp1= expression[funcName] tp2= expression[funcName] ) | ^( GE tp1= expression[funcName] tp2= expression[funcName] ) | ^( PLUS tp1= expression[funcName] tp2= expression[funcName] ) | ^( MINUS tp1= expression[funcName] tp2= expression[funcName] ) | ^( TIMES tp1= expression[funcName] tp2= expression[funcName] ) | ^( DIVIDE tp1= expression[funcName] tp2= expression[funcName] ) | ^( NOT tp= expression[funcName] ) | ^( NEG tp= expression[funcName] ) | ^( DOT tp= expression[funcName] id= ID ) | ^( INVOKE id= ID args= arguments[funcName] ) |id= ID | INTEGER | TRUE | FALSE | ^( NEW id= ID ) | VOID | NULL )
			int alt19=23;
			switch ( input.LA(1) ) {
			case AND:
				{
				alt19=1;
				}
				break;
			case OR:
				{
				alt19=2;
				}
				break;
			case EQ:
				{
				alt19=3;
				}
				break;
			case LT:
				{
				alt19=4;
				}
				break;
			case GT:
				{
				alt19=5;
				}
				break;
			case NE:
				{
				alt19=6;
				}
				break;
			case LE:
				{
				alt19=7;
				}
				break;
			case GE:
				{
				alt19=8;
				}
				break;
			case PLUS:
				{
				alt19=9;
				}
				break;
			case MINUS:
				{
				alt19=10;
				}
				break;
			case TIMES:
				{
				alt19=11;
				}
				break;
			case DIVIDE:
				{
				alt19=12;
				}
				break;
			case NOT:
				{
				alt19=13;
				}
				break;
			case NEG:
				{
				alt19=14;
				}
				break;
			case DOT:
				{
				alt19=15;
				}
				break;
			case INVOKE:
				{
				alt19=16;
				}
				break;
			case ID:
				{
				alt19=17;
				}
				break;
			case INTEGER:
				{
				alt19=18;
				}
				break;
			case TRUE:
				{
				alt19=19;
				}
				break;
			case FALSE:
				{
				alt19=20;
				}
				break;
			case NEW:
				{
				alt19=21;
				}
				break;
			case VOID:
				{
				alt19=22;
				}
				break;
			case NULL:
				{
				alt19=23;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 19, 0, input);
				throw nvae;
			}
			switch (alt19) {
				case 1 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:331:4: ^( AND tp1= expression[funcName] tp2= expression[funcName] )
					{
					match(input,AND,FOLLOW_AND_in_expression779); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression783);
					tp1=expression(funcName);
					state._fsp--;

					pushFollow(FOLLOW_expression_in_expression788);
					tp2=expression(funcName);
					state._fsp--;

					match(input, Token.UP, null); 


								if (!(tp1 instanceof Bool) || !(tp2 instanceof Bool))
								{
									error(0, "'and' operation requires bool expressions");
								}
								else
								{
									t = Type.boolType();
								}
							
					}
					break;
				case 2 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:342:4: ^( OR tp1= expression[funcName] tp2= expression[funcName] )
					{
					match(input,OR,FOLLOW_OR_in_expression800); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression804);
					tp1=expression(funcName);
					state._fsp--;

					pushFollow(FOLLOW_expression_in_expression809);
					tp2=expression(funcName);
					state._fsp--;

					match(input, Token.UP, null); 


								if (!(tp1 instanceof Bool) || !(tp2 instanceof Bool))
								{
									error(0, "'or' operation requires bool expressions");
								}
								else
								{
									t = Type.boolType();
								}
							
					}
					break;
				case 3 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:353:4: ^( EQ tp1= expression[funcName] tp2= expression[funcName] )
					{
					match(input,EQ,FOLLOW_EQ_in_expression821); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression825);
					tp1=expression(funcName);
					state._fsp--;

					pushFollow(FOLLOW_expression_in_expression830);
					tp2=expression(funcName);
					state._fsp--;

					match(input, Token.UP, null); 


								if (!((tp1 instanceof Int) && (tp2 instanceof Int)) && !((tp1 instanceof Struct) && (tp2 instanceof Struct)))
								{
									error(0, "'equals' operator requires int or struct operands");
								}
								else if (!((tp1).equals(tp2)))
								{
									error(0, "'equals' operator requires that operands both be ints or structs");
								}
								else
								{
									t = Type.boolType();
								}
							
					}
					break;
				case 4 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:368:4: ^( LT tp1= expression[funcName] tp2= expression[funcName] )
					{
					match(input,LT,FOLLOW_LT_in_expression842); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression846);
					tp1=expression(funcName);
					state._fsp--;

					pushFollow(FOLLOW_expression_in_expression851);
					tp2=expression(funcName);
					state._fsp--;

					match(input, Token.UP, null); 


								if (!(tp1 instanceof Int) || !(tp2 instanceof Int))
								{
									error(0, "'less than' operation requires int expressions");
								}
								else
								{
									t = Type.boolType();
								}
							
					}
					break;
				case 5 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:379:4: ^( GT tp1= expression[funcName] tp2= expression[funcName] )
					{
					match(input,GT,FOLLOW_GT_in_expression863); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression867);
					tp1=expression(funcName);
					state._fsp--;

					pushFollow(FOLLOW_expression_in_expression872);
					tp2=expression(funcName);
					state._fsp--;

					match(input, Token.UP, null); 


								if (!(tp1 instanceof Int) || !(tp2 instanceof Int))
								{
									error(0, "'greater than' operation requires int expressions");
								}
								else
								{
									t = Type.boolType();
								}
							
					}
					break;
				case 6 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:390:4: ^( NE tp1= expression[funcName] tp2= expression[funcName] )
					{
					match(input,NE,FOLLOW_NE_in_expression884); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression888);
					tp1=expression(funcName);
					state._fsp--;

					pushFollow(FOLLOW_expression_in_expression893);
					tp2=expression(funcName);
					state._fsp--;

					match(input, Token.UP, null); 


								if (!(tp1 instanceof Int) || !(tp2 instanceof Int))
								{
									error(0, "'not equals' operation requires int expressions");
								}
								else
								{
									t = Type.boolType();
								}
							
					}
					break;
				case 7 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:401:4: ^( LE tp1= expression[funcName] tp2= expression[funcName] )
					{
					match(input,LE,FOLLOW_LE_in_expression905); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression909);
					tp1=expression(funcName);
					state._fsp--;

					pushFollow(FOLLOW_expression_in_expression914);
					tp2=expression(funcName);
					state._fsp--;

					match(input, Token.UP, null); 


								if (!(tp1 instanceof Int) || !(tp2 instanceof Int))
								{
									error(0, "'less than or equal to' operation requires int expressions");
								}
								else
								{
									t = Type.boolType();
								}
							
					}
					break;
				case 8 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:412:4: ^( GE tp1= expression[funcName] tp2= expression[funcName] )
					{
					match(input,GE,FOLLOW_GE_in_expression926); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression930);
					tp1=expression(funcName);
					state._fsp--;

					pushFollow(FOLLOW_expression_in_expression935);
					tp2=expression(funcName);
					state._fsp--;

					match(input, Token.UP, null); 


								if (!(tp1 instanceof Int) || !(tp2 instanceof Int))
								{
									error(0, "'greater than or equal to' operation requires int expressions");
								}
								else
								{
									t = Type.boolType();
								}
							
					}
					break;
				case 9 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:423:4: ^( PLUS tp1= expression[funcName] tp2= expression[funcName] )
					{
					match(input,PLUS,FOLLOW_PLUS_in_expression947); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression951);
					tp1=expression(funcName);
					state._fsp--;

					pushFollow(FOLLOW_expression_in_expression956);
					tp2=expression(funcName);
					state._fsp--;

					match(input, Token.UP, null); 


								if (!(tp1 instanceof Int) || !(tp2 instanceof Int))
								{
									error(0, "'plus' operation requires int expressions");
								}
								else
								{
									t = Type.intType();
								}
							
					}
					break;
				case 10 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:434:4: ^( MINUS tp1= expression[funcName] tp2= expression[funcName] )
					{
					match(input,MINUS,FOLLOW_MINUS_in_expression968); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression972);
					tp1=expression(funcName);
					state._fsp--;

					pushFollow(FOLLOW_expression_in_expression977);
					tp2=expression(funcName);
					state._fsp--;

					match(input, Token.UP, null); 


								if (!(tp1 instanceof Int) || !(tp2 instanceof Int))
								{
									error(0, "'minus' operation requires int expressions");
								}
								else
								{
									t = Type.intType();
								}
							
					}
					break;
				case 11 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:445:4: ^( TIMES tp1= expression[funcName] tp2= expression[funcName] )
					{
					match(input,TIMES,FOLLOW_TIMES_in_expression989); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression993);
					tp1=expression(funcName);
					state._fsp--;

					pushFollow(FOLLOW_expression_in_expression998);
					tp2=expression(funcName);
					state._fsp--;

					match(input, Token.UP, null); 


								if (!(tp1 instanceof Int) || !(tp2 instanceof Int))
								{
									error(0, "'times' operation requires int expressions");
								}
								else
								{
									t = Type.intType();
								}
							
					}
					break;
				case 12 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:456:4: ^( DIVIDE tp1= expression[funcName] tp2= expression[funcName] )
					{
					match(input,DIVIDE,FOLLOW_DIVIDE_in_expression1010); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression1014);
					tp1=expression(funcName);
					state._fsp--;

					pushFollow(FOLLOW_expression_in_expression1019);
					tp2=expression(funcName);
					state._fsp--;

					match(input, Token.UP, null); 


								if (!(tp1 instanceof Int) || !(tp2 instanceof Int))
								{
									error(0, "'divide' operation requires int expressions");
								}
								else
								{
									t = Type.intType();
								}
							
					}
					break;
				case 13 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:467:4: ^( NOT tp= expression[funcName] )
					{
					match(input,NOT,FOLLOW_NOT_in_expression1031); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression1035);
					tp=expression(funcName);
					state._fsp--;

					match(input, Token.UP, null); 


								if (!(tp instanceof Bool))
								{
									error(0, "'not' operator requires bool expression");
								}
								else
								{
									t = Type.boolType();
								}
							
					}
					break;
				case 14 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:478:4: ^( NEG tp= expression[funcName] )
					{
					match(input,NEG,FOLLOW_NEG_in_expression1047); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression1051);
					tp=expression(funcName);
					state._fsp--;

					match(input, Token.UP, null); 


								if (!(tp instanceof Int))
								{
									error(0, "'neg' operator requires int expression");
								}
								else
								{
									t = Type.intType();
								}
							
					}
					break;
				case 15 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:489:4: ^( DOT tp= expression[funcName] id= ID )
					{
					match(input,DOT,FOLLOW_DOT_in_expression1063); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression1067);
					tp=expression(funcName);
					state._fsp--;

					id=(CommonTree)match(input,ID,FOLLOW_ID_in_expression1072); 
					match(input, Token.UP, null); 


								if (!(tp instanceof Struct))
								{
									error((id!=null?id.getLine():0), "invalid type for 'dot' operator. Requires 'struct' type.");
								}
								else if (!(sTypes.containsVal(((Struct)tp).structName(), (id!=null?id.getText():null))))
								{
									error((id!=null?id.getLine():0), "struct val ' " + ((Struct)tp).structName() + "' not defined"); 
								}
								else
								{
									t =sTypes.getValType(((Struct)tp).structName(), (id!=null?id.getText():null));
								}
							
					}
					break;
				case 16 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:504:4: ^( INVOKE id= ID args= arguments[funcName] )
					{
					match(input,INVOKE,FOLLOW_INVOKE_in_expression1083); 
					match(input, Token.DOWN, null); 
					id=(CommonTree)match(input,ID,FOLLOW_ID_in_expression1087); 
					pushFollow(FOLLOW_arguments_in_expression1091);
					args=arguments(funcName);
					state._fsp--;

					match(input, Token.UP, null); 


								if (args.size() != funcs.get((id!=null?id.getText():null)).getParams().size())
								{
									error((id!=null?id.getLine():0), "Number of args in call to '" + id + "' is incorrect");
								}
								
								t = funcs.get((id!=null?id.getText():null)).getRetType();
							
					}
					break;
				case 17 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:513:4: id= ID
					{
					id=(CommonTree)match(input,ID,FOLLOW_ID_in_expression1104); 

								if (funcs.get(funcName).containsParam((id!=null?id.getText():null)))
								{
									t =funcs.get(funcName).paramType((id!=null?id.getText():null));
								}
								else if (sTable.varPrevDefined(funcName, (id!=null?id.getText():null)))
								{
									t =sTable.getVarType(funcName, (id!=null?id.getText():null));
								}
								else if (sTable.varPrevDefined("global", (id!=null?id.getText():null)))
								{
									t =sTable.getVarType("global", (id!=null?id.getText():null));
								}
								else
								{
									error((id!=null?id.getLine():0), "variable '" + id + "' was never initialized");
								}
							
					}
					break;
				case 18 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:532:4: INTEGER
					{
					match(input,INTEGER,FOLLOW_INTEGER_in_expression1114); 
					 t = Type.intType(); 
					}
					break;
				case 19 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:533:4: TRUE
					{
					match(input,TRUE,FOLLOW_TRUE_in_expression1121); 
					 t = Type.boolType(); 
					}
					break;
				case 20 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:534:4: FALSE
					{
					match(input,FALSE,FOLLOW_FALSE_in_expression1128); 
					 t = Type.boolType(); 
					}
					break;
				case 21 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:535:4: ^( NEW id= ID )
					{
					match(input,NEW,FOLLOW_NEW_in_expression1136); 
					match(input, Token.DOWN, null); 
					id=(CommonTree)match(input,ID,FOLLOW_ID_in_expression1140); 
					match(input, Token.UP, null); 


								if (sTypes.isDefined((id!=null?id.getText():null)))
								{
									t = Type.structType((id!=null?id.getText():null));
								}
								else
								{
									error((id!=null?id.getLine():0), "'" + id + "' is not a valid struct type");
								}
							
					}
					break;
				case 22 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:546:4: VOID
					{
					match(input,VOID,FOLLOW_VOID_in_expression1151); 
					 t = Type.voidType(); 
					}
					break;
				case 23 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\TypeCheck.g:547:4: NULL
					{
					match(input,NULL,FOLLOW_NULL_in_expression1158); 
					 t = Type.nullType(); 
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return t;
	}
	// $ANTLR end "expression"

	// Delegated rules



	public static final BitSet FOLLOW_PROGRAM_in_program46 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_types_in_program48 = new BitSet(new long[]{0x0000000000402000L});
	public static final BitSet FOLLOW_declarations_in_program50 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_functions_in_program53 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_EOF_in_program56 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TYPES_in_types67 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_struct_in_types69 = new BitSet(new long[]{0x0010000000000008L});
	public static final BitSet FOLLOW_DECLS_in_declarations83 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_declaration_in_declarations85 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_FUNCS_in_functions103 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_function_in_functions105 = new BitSet(new long[]{0x0000000000200008L});
	public static final BitSet FOLLOW_STRUCT_in_struct122 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_ID_in_struct126 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_decs_in_struct134 = new BitSet(new long[]{0x0000000000000808L});
	public static final BitSet FOLLOW_DECL_in_decs156 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_TYPE_in_decs159 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_type_in_decs163 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ID_in_decs168 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_INT_in_type188 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_BOOL_in_type195 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_STRUCT_in_type203 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_ID_in_type207 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_decl_list_in_declaration224 = new BitSet(new long[]{0x0000000000001002L});
	public static final BitSet FOLLOW_DECLLIST_in_decl_list241 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_TYPE_in_decl_list244 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_type_in_decl_list248 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_id_list_in_decl_list253 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ID_in_id_list278 = new BitSet(new long[]{0x0000000002000002L});
	public static final BitSet FOLLOW_FUN_in_function295 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_ID_in_function299 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_parameters_in_function306 = new BitSet(new long[]{0x0000800000000000L});
	public static final BitSet FOLLOW_RETTYPE_in_function310 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_return_type_in_function314 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_declarations_in_function323 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_statement_list_in_function326 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_PARAMS_in_parameters340 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_decs_in_parameters343 = new BitSet(new long[]{0x0000000000000808L});
	public static final BitSet FOLLOW_type_in_return_type364 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_VOID_in_return_type371 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_STMTS_in_statement_list385 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_statement_in_statement_list388 = new BitSet(new long[]{0x04014800240040C8L});
	public static final BitSet FOLLOW_block_in_statement403 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_assignment_in_statement409 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_print_in_statement415 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_read_in_statement421 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_conditional_in_statement427 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_loop_in_statement433 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_delete_in_statement439 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ret_in_statement445 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_invocation_in_statement451 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_BLOCK_in_block464 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_statement_list_in_block466 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ASSIGN_in_assignment480 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_assignment484 = new BitSet(new long[]{0x0000000002010000L});
	public static final BitSet FOLLOW_lvalue_in_assignment489 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_DOT_in_lvalue511 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_lvalue_in_lvalue515 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_ID_in_lvalue520 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ID_in_lvalue532 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PRINT_in_print548 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_print552 = new BitSet(new long[]{0x0000000000040008L});
	public static final BitSet FOLLOW_ENDL_in_print556 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_READ_in_read575 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_lvalue_in_read579 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_IF_in_conditional597 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_conditional601 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_block_in_conditional604 = new BitSet(new long[]{0x0000000000000088L});
	public static final BitSet FOLLOW_block_in_conditional608 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_WHILE_in_loop628 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_loop632 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_block_in_loop635 = new BitSet(new long[]{0x026005FEB3998010L});
	public static final BitSet FOLLOW_expression_in_loop638 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_DELETE_in_delete656 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_delete660 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_RETURN_in_ret678 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_ret683 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_RETURN_in_ret695 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INVOKE_in_invocation715 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_ID_in_invocation719 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_arguments_in_invocation723 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ARGS_in_arguments747 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_arguments752 = new BitSet(new long[]{0x026005FEB3998018L});
	public static final BitSet FOLLOW_ARGS_in_arguments763 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_AND_in_expression779 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression783 = new BitSet(new long[]{0x026005FEB3998010L});
	public static final BitSet FOLLOW_expression_in_expression788 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_OR_in_expression800 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression804 = new BitSet(new long[]{0x026005FEB3998010L});
	public static final BitSet FOLLOW_expression_in_expression809 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_EQ_in_expression821 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression825 = new BitSet(new long[]{0x026005FEB3998010L});
	public static final BitSet FOLLOW_expression_in_expression830 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_LT_in_expression842 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression846 = new BitSet(new long[]{0x026005FEB3998010L});
	public static final BitSet FOLLOW_expression_in_expression851 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_GT_in_expression863 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression867 = new BitSet(new long[]{0x026005FEB3998010L});
	public static final BitSet FOLLOW_expression_in_expression872 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_NE_in_expression884 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression888 = new BitSet(new long[]{0x026005FEB3998010L});
	public static final BitSet FOLLOW_expression_in_expression893 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_LE_in_expression905 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression909 = new BitSet(new long[]{0x026005FEB3998010L});
	public static final BitSet FOLLOW_expression_in_expression914 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_GE_in_expression926 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression930 = new BitSet(new long[]{0x026005FEB3998010L});
	public static final BitSet FOLLOW_expression_in_expression935 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_PLUS_in_expression947 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression951 = new BitSet(new long[]{0x026005FEB3998010L});
	public static final BitSet FOLLOW_expression_in_expression956 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_MINUS_in_expression968 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression972 = new BitSet(new long[]{0x026005FEB3998010L});
	public static final BitSet FOLLOW_expression_in_expression977 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_TIMES_in_expression989 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression993 = new BitSet(new long[]{0x026005FEB3998010L});
	public static final BitSet FOLLOW_expression_in_expression998 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_DIVIDE_in_expression1010 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression1014 = new BitSet(new long[]{0x026005FEB3998010L});
	public static final BitSet FOLLOW_expression_in_expression1019 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_NOT_in_expression1031 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression1035 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_NEG_in_expression1047 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression1051 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_DOT_in_expression1063 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression1067 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_ID_in_expression1072 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_INVOKE_in_expression1083 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_ID_in_expression1087 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_arguments_in_expression1091 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ID_in_expression1104 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INTEGER_in_expression1114 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TRUE_in_expression1121 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FALSE_in_expression1128 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NEW_in_expression1136 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_ID_in_expression1140 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_VOID_in_expression1151 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NULL_in_expression1158 = new BitSet(new long[]{0x0000000000000002L});
}
