// $ANTLR 3.5.2 C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g 2014-06-10 15:52:42

   package edu.calpoly.mwerner.compiler;

   import java.util.Map;
   import java.util.HashMap;
   import java.util.Iterator;
   import java.util.Vector;


import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class ControlFlow extends TreeParser {
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


	public ControlFlow(TreeNodeStream input) {
		this(input, new RecognizerSharedState());
	}
	public ControlFlow(TreeNodeStream input, RecognizerSharedState state) {
		super(input, state);
	}

	@Override public String[] getTokenNames() { return ControlFlow.tokenNames; }
	@Override public String getGrammarFileName() { return "C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g"; }

	  
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
	      Pushq pushq = new Pushq(new Register("%rbx"));
	//      Pushq pushq2 = new Pushq(new Register("%rsp"));
	//	  Pushq pushq3 = new Pushq(new Register("%rbp"));
		  Pushq pushq4 = new Pushq(new Register("%r12"));
		  Pushq pushq5 = new Pushq(new Register("%r13"));
		  Pushq pushq6 = new Pushq(new Register("%r14"));
		  Pushq pushq7 = new Pushq(new Register("%r15"));
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
	      Popq popq = new Popq(new Register("%r15"));
		  Popq popq2 = new Popq(new Register("%r14"));
		  Popq popq3 = new Popq(new Register("%r13"));
		  Popq popq4 = new Popq(new Register("%r12"));
	//	  Popq popq5 = new Popq(new Register("%rbp"));
	//	  Popq popq6 = new Popq(new Register("%rsp"));
		  Popq popq7 = new Popq(new Register("%rbx"));
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
	      Pushq pushq = new Pushq(new Register("%rax"));
	      Pushq pushq2 = new Pushq(new Register("%rcx"));
		  Pushq pushq3 = new Pushq(new Register("%rdx"));
		  Pushq pushq4 = new Pushq(new Register("%rsi"));
		  Pushq pushq5 = new Pushq(new Register("%rdi"));
		  Pushq pushq6 = new Pushq(new Register("%r8"));
		  Pushq pushq7 = new Pushq(new Register("%r9"));
		  Pushq pushq8 = new Pushq(new Register("%r10"));
		  Pushq pushq9 = new Pushq(new Register("%r11"));
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
	      Popq popq = new Popq(new Register("%r11"));
		  Popq popq2 = new Popq(new Register("%r10"));
		  Popq popq3 = new Popq(new Register("%r9"));
		  Popq popq4 = new Popq(new Register("%r8"));
		  Popq popq5 = new Popq(new Register("%rdi"));
		  Popq popq6 = new Popq(new Register("%rsi"));
		  Popq popq7 = new Popq(new Register("%rdx"));
		  Popq popq8 = new Popq(new Register("%rcx"));
		  Popq popq9 = new Popq(new Register("%rax"));
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



	// $ANTLR start "program"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:181:1: program returns [HashMap<String, BasicBlock> cfgs = null] : ^( PROGRAM types declarations[\"global\"] functions ) EOF ;
	public final HashMap<String, BasicBlock> program() throws RecognitionException {
		HashMap<String, BasicBlock> cfgs =  null;


		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:182:2: ( ^( PROGRAM types declarations[\"global\"] functions ) EOF )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:182:4: ^( PROGRAM types declarations[\"global\"] functions ) EOF
			{
			match(input,PROGRAM,FOLLOW_PROGRAM_in_program50); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_types_in_program52);
			types();
			state._fsp--;

			pushFollow(FOLLOW_declarations_in_program54);
			declarations("global");
			state._fsp--;

			pushFollow(FOLLOW_functions_in_program57);
			functions();
			state._fsp--;

			match(input, Token.UP, null); 

			 cfgs = cfgMap; 
			match(input,EOF,FOLLOW_EOF_in_program62); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return cfgs;
	}
	// $ANTLR end "program"



	// $ANTLR start "types"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:185:1: types : ^( TYPES ( struct )* ) ;
	public final void types() throws RecognitionException {
		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:186:2: ( ^( TYPES ( struct )* ) )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:186:4: ^( TYPES ( struct )* )
			{
			match(input,TYPES,FOLLOW_TYPES_in_types73); 
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); 
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:186:12: ( struct )*
				loop1:
				while (true) {
					int alt1=2;
					int LA1_0 = input.LA(1);
					if ( (LA1_0==STRUCT) ) {
						alt1=1;
					}

					switch (alt1) {
					case 1 :
						// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:186:12: struct
						{
						pushFollow(FOLLOW_struct_in_types75);
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
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:189:1: declarations[String scopeName] : ( ^( DECLS declaration[scopeName] ) |);
	public final void declarations(String scopeName) throws RecognitionException {
		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:190:2: ( ^( DECLS declaration[scopeName] ) |)
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
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:190:4: ^( DECLS declaration[scopeName] )
					{
					match(input,DECLS,FOLLOW_DECLS_in_declarations89); 
					if ( input.LA(1)==Token.DOWN ) {
						match(input, Token.DOWN, null); 
						pushFollow(FOLLOW_declaration_in_declarations91);
						declaration(scopeName);
						state._fsp--;

						match(input, Token.UP, null); 
					}

					}
					break;
				case 2 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:191:4: 
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
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:194:1: functions : ^( FUNCS ( function )* ) ;
	public final void functions() throws RecognitionException {

				labelCounter = 1;
			
		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:198:3: ( ^( FUNCS ( function )* ) )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:198:5: ^( FUNCS ( function )* )
			{
			match(input,FUNCS,FOLLOW_FUNCS_in_functions114); 
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); 
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:198:13: ( function )*
				loop3:
				while (true) {
					int alt3=2;
					int LA3_0 = input.LA(1);
					if ( (LA3_0==FUN) ) {
						alt3=1;
					}

					switch (alt3) {
					case 1 :
						// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:198:13: function
						{
						pushFollow(FOLLOW_function_in_functions116);
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
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:201:1: struct : ^( STRUCT id= ID ( decs[structVars, varNameList, ValCategory.STRUCT] )+ ) ;
	public final void struct() throws RecognitionException {
		CommonTree id=null;


				Vector<String> varNameList = new Vector<String>();
			
		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:205:3: ( ^( STRUCT id= ID ( decs[structVars, varNameList, ValCategory.STRUCT] )+ ) )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:205:5: ^( STRUCT id= ID ( decs[structVars, varNameList, ValCategory.STRUCT] )+ )
			{
			match(input,STRUCT,FOLLOW_STRUCT_in_struct134); 
			match(input, Token.DOWN, null); 
			id=(CommonTree)match(input,ID,FOLLOW_ID_in_struct138); 

						HashMap<String, Type> structVars = new HashMap<String, Type>();
						sTypes.addStruct((id!=null?id.getText():null), structVars);
						structs.put((id!=null?id.getText():null), new Struct((id!=null?id.getText():null)));
					
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:210:5: ( decs[structVars, varNameList, ValCategory.STRUCT] )+
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
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:210:6: decs[structVars, varNameList, ValCategory.STRUCT]
					{
					pushFollow(FOLLOW_decs_in_struct146);
					decs(structVars, varNameList, ValCategory.STRUCT);
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
						structs.put((id!=null?id.getText():null), new Struct((id!=null?id.getText():null)));
						structVarNames.put((id!=null?id.getText():null), varNameList);
					
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
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:218:1: decs[HashMap<String, Type> vals, Vector<String> varList, ValCategory valCat] : ^( DECL ^( TYPE tp= type ) id= ID ) ;
	public final void decs(HashMap<String, Type> vals, Vector<String> varList, ValCategory valCat) throws RecognitionException {
		CommonTree id=null;
		Type tp =null;


				Movq movq;
			
		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:222:3: ( ^( DECL ^( TYPE tp= type ) id= ID ) )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:222:5: ^( DECL ^( TYPE tp= type ) id= ID )
			{
			match(input,DECL,FOLLOW_DECL_in_decs173); 
			match(input, Token.DOWN, null); 
			match(input,TYPE,FOLLOW_TYPE_in_decs176); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_type_in_decs180);
			tp=type();
			state._fsp--;

			match(input, Token.UP, null); 

			id=(CommonTree)match(input,ID,FOLLOW_ID_in_decs185); 
			match(input, Token.UP, null); 

			 
						vals.put((id!=null?id.getText():null), tp);
						varList.add((id!=null?id.getText():null));
						if (valCat == ValCategory.PARAMS)
						{
							paramRegMap.put((id!=null?id.getText():null), new Register(regCounter));
							
							switch(argOffsetCtr)
							{
								case 0:	movq = new Movq(new Register("%rdi"), new Register(regCounter));
										currBlock.addAssembly(movq);
										break;
								case 1:	movq = new Movq(new Register("%rsi"), new Register(regCounter));
										currBlock.addAssembly(movq);
										break;
								case 2:	movq = new Movq(new Register("%rdx"), new Register(regCounter));
										currBlock.addAssembly(movq);
										break;
								case 3:	movq = new Movq(new Register("%rcx"), new Register(regCounter));
										currBlock.addAssembly(movq);
										break;
								case 4:	movq = new Movq(new Register("%r8"), new Register(regCounter));
										currBlock.addAssembly(movq);
										break;
								case 5:	movq = new Movq(new Register("%r9"), new Register(regCounter));
										currBlock.addAssembly(movq);
										break;
								default: // spill
										break;
							}
							
							Loadinargument loadinargument = new Loadinargument(new Id((id!=null?id.getText():null)), new Immediate(argOffsetCtr++), new Register(regCounter++));
							currBlock.addInstr(loadinargument);
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
	// $ANTLR end "decs"



	// $ANTLR start "type"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:260:1: type returns [Type t = null] : ( INT | BOOL | ^( STRUCT id= ID ) );
	public final Type type() throws RecognitionException {
		Type t =  null;


		CommonTree id=null;

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:261:2: ( INT | BOOL | ^( STRUCT id= ID ) )
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
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:261:4: INT
					{
					match(input,INT,FOLLOW_INT_in_type205); 
					 t = Type.intType(); 
					}
					break;
				case 2 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:262:4: BOOL
					{
					match(input,BOOL,FOLLOW_BOOL_in_type212); 
					 t = Type.boolType(); 
					}
					break;
				case 3 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:263:4: ^( STRUCT id= ID )
					{
					match(input,STRUCT,FOLLOW_STRUCT_in_type220); 
					match(input, Token.DOWN, null); 
					id=(CommonTree)match(input,ID,FOLLOW_ID_in_type224); 
					match(input, Token.UP, null); 


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
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:269:1: declaration[String scopeName] : ( decl_list[scopeName] )* ;
	public final void declaration(String scopeName) throws RecognitionException {
		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:270:2: ( ( decl_list[scopeName] )* )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:270:4: ( decl_list[scopeName] )*
			{
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:270:4: ( decl_list[scopeName] )*
			loop6:
			while (true) {
				int alt6=2;
				int LA6_0 = input.LA(1);
				if ( (LA6_0==DECLLIST) ) {
					alt6=1;
				}

				switch (alt6) {
				case 1 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:270:5: decl_list[scopeName]
					{
					pushFollow(FOLLOW_decl_list_in_declaration241);
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
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:273:1: decl_list[String scopeName] : ^( DECLLIST ^( TYPE tp= type ) line= id_list[varNames] ) ;
	public final void decl_list(String scopeName) throws RecognitionException {
		Type tp =null;
		int line =0;

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:274:2: ( ^( DECLLIST ^( TYPE tp= type ) line= id_list[varNames] ) )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:274:4: ^( DECLLIST ^( TYPE tp= type ) line= id_list[varNames] )
			{
			 ArrayList<String> varNames = new ArrayList<String>(); 
			match(input,DECLLIST,FOLLOW_DECLLIST_in_decl_list258); 
			match(input, Token.DOWN, null); 
			match(input,TYPE,FOLLOW_TYPE_in_decl_list261); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_type_in_decl_list265);
			tp=type();
			state._fsp--;

			match(input, Token.UP, null); 

			pushFollow(FOLLOW_id_list_in_decl_list270);
			line=id_list(varNames);
			state._fsp--;

			match(input, Token.UP, null); 

			 
						for (String name : varNames)
						{
							if (!sTable.scopePrevDefined(scopeName))
							{
								localRegMap.clear();
								sTable.addScope(scopeName);
								sTable.addVar(scopeName, name, tp);
								localRegMap.put(name, new Register(regCounter++));
							}	
							else if (!sTable.varPrevDefined(scopeName, name))
							{
								sTable.addVar(scopeName, name, tp);
								localRegMap.put(name, new Register(regCounter++));
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
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:294:1: id_list[ArrayList<String> varNames] returns [int line = -1] : (id= ID )+ ;
	public final int id_list(ArrayList<String> varNames) throws RecognitionException {
		int line =  -1;


		CommonTree id=null;

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:295:2: ( (id= ID )+ )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:295:4: (id= ID )+
			{
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:295:4: (id= ID )+
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
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:295:5: id= ID
					{
					id=(CommonTree)match(input,ID,FOLLOW_ID_in_id_list295); 
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
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:298:1: function : ^( FUN id= ID parameters[params] ^( RETTYPE tp= return_type ) declarations[$id.text] statement_list[$id.text] ) ;
	public final void function() throws RecognitionException {
		CommonTree id=null;
		Type tp =null;

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:299:2: ( ^( FUN id= ID parameters[params] ^( RETTYPE tp= return_type ) declarations[$id.text] statement_list[$id.text] ) )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:299:4: ^( FUN id= ID parameters[params] ^( RETTYPE tp= return_type ) declarations[$id.text] statement_list[$id.text] )
			{
			match(input,FUN,FOLLOW_FUN_in_function312); 
			match(input, Token.DOWN, null); 
			id=(CommonTree)match(input,ID,FOLLOW_ID_in_function316); 

						HashMap<String, Type> params = new HashMap<String, Type>();
						
						regCounter = 0;
						cfgMap.put((id!=null?id.getText():null), new BasicBlock((id!=null?id.getText():null)));
						
						currBlock = cfgMap.get((id!=null?id.getText():null));
						
						Register rbp = new Register("%rbp");
						Register rsp = new Register("%rsp");
						
						Pushq pushq = new Pushq(rbp);
						Movq movq = new Movq(rsp, rbp);
						currBlock.addAssembly(pushq);
						currBlock.addAssembly(movq);
						
						calleeSavePush(currBlock);
						
						exitBlock = new BasicBlock("L" + labelCounter++);
						exitBlock.addInstr(new Ret());
						
						exitBlocks.put((id!=null?id.getText():null), exitBlock);
						
						calleeSavePop(exitBlock);
						
						Movq endMovq = new Movq(rbp, rsp);
						Popq popq = new Popq(rbp);
						exitBlock.addAssembly(endMovq);
						exitBlock.addAssembly(popq);
						exitBlock.addAssembly(new Ret());
						
						
					
			pushFollow(FOLLOW_parameters_in_function323);
			parameters(params);
			state._fsp--;

			match(input,RETTYPE,FOLLOW_RETTYPE_in_function327); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_return_type_in_function331);
			tp=return_type();
			state._fsp--;

			match(input, Token.UP, null); 


					 		funcs.put((id!=null?id.getText():null), Type.funcType((id!=null?id.getText():null), params, tp));
					 		
					 		if (!sTable.scopePrevDefined((id!=null?id.getText():null)))
					 		{
					 			sTable.addScope((id!=null?id.getText():null));
					 		}
					 	
			pushFollow(FOLLOW_declarations_in_function340);
			declarations((id!=null?id.getText():null));
			state._fsp--;

			pushFollow(FOLLOW_statement_list_in_function343);
			statement_list((id!=null?id.getText():null));
			state._fsp--;


					 			currBlock.addSuccessor(exitBlock);
					 			exitBlock.addPredecessor(currBlock);
					 			
					 			Jumpi jumpi = new Jumpi(new Label(exitBlock.getLabel()));
					 			currBlock.addInstr(jumpi);
					 			
					 			Jmp jmp = new Jmp(new Label(exitBlock.getLabel()));
					 			currBlock.addAssembly(jmp);
					 			
					 			currBlock = exitBlock;
					 			
					 			maxArgsMap.put((id!=null?id.getText():null), new Integer(maxNumArgs));
					 			
			//		 			BasicBlock funcBlk = cfgMap.get((id!=null?id.getText():null));
			//		 			Subq subq = new Subq(new Immediate(8*maxNumArgs), new Register("%rsp"));
			//		 			funcBlk.addAssembly(subq, 9);
			//		 			
			//		 			Addq addq = new Addq(new Immediate(8*maxNumArgs), new Register("%rsp"));
			//		 			
			//		 			if (exitBlock.numAssemInstr() > 10)
			//		 			{
			//		 				exitBlock.addAssembly(addq, exitBlock.numAssemInstr()-11);
			//		 			}
			//		 			else
			//		 			{
			//		 				exitBlock.addAssembly(addq, 0);
			//		 			}
					 		
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
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:372:1: parameters[HashMap<String, Type> params] : ^( PARAMS ( decs[params, paramList, ValCategory.PARAMS] )* ) ;
	public final void parameters(HashMap<String, Type> params) throws RecognitionException {

				Vector<String> paramList = new Vector<String>();
				argOffsetCtr = 0;
				paramRegMap.clear();
			
		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:378:3: ( ^( PARAMS ( decs[params, paramList, ValCategory.PARAMS] )* ) )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:378:5: ^( PARAMS ( decs[params, paramList, ValCategory.PARAMS] )* )
			{
			match(input,PARAMS,FOLLOW_PARAMS_in_parameters369); 
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); 
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:378:14: ( decs[params, paramList, ValCategory.PARAMS] )*
				loop8:
				while (true) {
					int alt8=2;
					int LA8_0 = input.LA(1);
					if ( (LA8_0==DECL) ) {
						alt8=1;
					}

					switch (alt8) {
					case 1 :
						// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:378:15: decs[params, paramList, ValCategory.PARAMS]
						{
						pushFollow(FOLLOW_decs_in_parameters372);
						decs(params, paramList, ValCategory.PARAMS);
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
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:381:1: return_type returns [Type t = null] : (tp= type | VOID );
	public final Type return_type() throws RecognitionException {
		Type t =  null;


		Type tp =null;

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:382:2: (tp= type | VOID )
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
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:382:4: tp= type
					{
					pushFollow(FOLLOW_type_in_return_type393);
					tp=type();
					state._fsp--;

					t = tp; 
					}
					break;
				case 2 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:383:4: VOID
					{
					match(input,VOID,FOLLOW_VOID_in_return_type400); 
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
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:386:1: statement_list[String funcName] : ^( STMTS ( statement[funcName] )* ) ;
	public final void statement_list(String funcName) throws RecognitionException {
		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:387:2: ( ^( STMTS ( statement[funcName] )* ) )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:387:4: ^( STMTS ( statement[funcName] )* )
			{
			match(input,STMTS,FOLLOW_STMTS_in_statement_list414); 
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); 
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:387:12: ( statement[funcName] )*
				loop10:
				while (true) {
					int alt10=2;
					int LA10_0 = input.LA(1);
					if ( ((LA10_0 >= ASSIGN && LA10_0 <= BLOCK)||LA10_0==DELETE||LA10_0==IF||LA10_0==INVOKE||LA10_0==PRINT||LA10_0==READ||LA10_0==RETURN||LA10_0==WHILE) ) {
						alt10=1;
					}

					switch (alt10) {
					case 1 :
						// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:387:13: statement[funcName]
						{
						pushFollow(FOLLOW_statement_in_statement_list417);
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
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:390:1: statement[String funcName] : ( block[funcName] | assignment[funcName] | print[funcName] | read[funcName] | conditional[funcName] | loop[funcName] | delete[funcName] | ret[funcName] | invocation[funcName] );
	public final void statement(String funcName) throws RecognitionException {
		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:391:2: ( block[funcName] | assignment[funcName] | print[funcName] | read[funcName] | conditional[funcName] | loop[funcName] | delete[funcName] | ret[funcName] | invocation[funcName] )
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
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:391:4: block[funcName]
					{
					pushFollow(FOLLOW_block_in_statement432);
					block(funcName);
					state._fsp--;

					}
					break;
				case 2 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:392:4: assignment[funcName]
					{
					pushFollow(FOLLOW_assignment_in_statement438);
					assignment(funcName);
					state._fsp--;

					}
					break;
				case 3 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:393:4: print[funcName]
					{
					pushFollow(FOLLOW_print_in_statement444);
					print(funcName);
					state._fsp--;

					}
					break;
				case 4 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:394:4: read[funcName]
					{
					pushFollow(FOLLOW_read_in_statement450);
					read(funcName);
					state._fsp--;

					}
					break;
				case 5 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:395:4: conditional[funcName]
					{
					pushFollow(FOLLOW_conditional_in_statement456);
					conditional(funcName);
					state._fsp--;

					}
					break;
				case 6 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:396:4: loop[funcName]
					{
					pushFollow(FOLLOW_loop_in_statement462);
					loop(funcName);
					state._fsp--;

					}
					break;
				case 7 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:397:4: delete[funcName]
					{
					pushFollow(FOLLOW_delete_in_statement468);
					delete(funcName);
					state._fsp--;

					}
					break;
				case 8 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:398:4: ret[funcName]
					{
					pushFollow(FOLLOW_ret_in_statement474);
					ret(funcName);
					state._fsp--;

					}
					break;
				case 9 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:399:4: invocation[funcName]
					{
					pushFollow(FOLLOW_invocation_in_statement480);
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
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:402:1: block[String funcName] : ^( BLOCK statement_list[funcName] ) ;
	public final void block(String funcName) throws RecognitionException {
		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:403:2: ( ^( BLOCK statement_list[funcName] ) )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:403:4: ^( BLOCK statement_list[funcName] )
			{
			match(input,BLOCK,FOLLOW_BLOCK_in_block493); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_statement_list_in_block495);
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
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:406:1: assignment[String funcName] : ^( ASSIGN r1= expression[funcName] x= lvalue[funcName] ) ;
	public final void assignment(String funcName) throws RecognitionException {
		TreeRuleReturnScope r1 =null;
		TreeRuleReturnScope x =null;

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:407:2: ( ^( ASSIGN r1= expression[funcName] x= lvalue[funcName] ) )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:407:4: ^( ASSIGN r1= expression[funcName] x= lvalue[funcName] )
			{
			match(input,ASSIGN,FOLLOW_ASSIGN_in_assignment509); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_expression_in_assignment513);
			r1=expression(funcName);
			state._fsp--;

			pushFollow(FOLLOW_lvalue_in_assignment518);
			x=lvalue(funcName);
			state._fsp--;

			match(input, Token.UP, null); 


						if ((x!=null?((ControlFlow.lvalue_return)x).reg:0) != -1)
						{	
							Register targetReg = new Register((x!=null?((ControlFlow.lvalue_return)x).reg:0));
							
							Vector<String> valList = structVarNames.get(((Struct)(x!=null?((ControlFlow.lvalue_return)x).tp:null)).structName());
						
							int offset = valList.indexOf((x!=null?((ControlFlow.lvalue_return)x).var:null)) * 8;
									
							Movq movq = new Movq(new Register((r1!=null?((ControlFlow.expression_return)r1).reg:0)), new Immediate(offset), targetReg);
							currBlock.addAssembly(movq);
							
							Storeai storeai = new Storeai(new Register((r1!=null?((ControlFlow.expression_return)r1).reg:0)), targetReg, (x!=null?((ControlFlow.lvalue_return)x).var:null));
							currBlock.addInstr(storeai);
						}
						else
						{
							if (sTable.varPrevDefined(funcName, (x!=null?((ControlFlow.lvalue_return)x).var:null)))
							{
								Register srcReg = new Register((r1!=null?((ControlFlow.expression_return)r1).reg:0));
								Register targetReg = localRegMap.get((x!=null?((ControlFlow.lvalue_return)x).var:null));
								
								Movq movq = new Movq(srcReg, targetReg);
								currBlock.addAssembly(movq);
								
								Mov mov = new Mov(srcReg, targetReg);
								currBlock.addInstr(mov);
							}
							//Check params
							else if (paramRegMap.containsKey((x!=null?((ControlFlow.lvalue_return)x).var:null)))
							{
								Register srcReg = new Register((r1!=null?((ControlFlow.expression_return)r1).reg:0));
								Register targetReg = paramRegMap.get((x!=null?((ControlFlow.lvalue_return)x).var:null));
								
								Movq movq = new Movq(srcReg, targetReg);
								currBlock.addAssembly(movq);
								
								Mov mov = new Mov(srcReg, targetReg);
								currBlock.addInstr(mov);
							}
							//Must be global
							else
							{
								Register srcReg = new Register((r1!=null?((ControlFlow.expression_return)r1).reg:0));
								
								Movq movq = new Movq(srcReg, ("glob_" + (x!=null?((ControlFlow.lvalue_return)x).var:null)), new Register ("%rip"));
								currBlock.addAssembly(movq);
								
								Storeglobal storeglobal = new Storeglobal(srcReg, new Id((x!=null?((ControlFlow.lvalue_return)x).var:null)));
								currBlock.addInstr(storeglobal);
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
	// $ANTLR end "assignment"


	public static class lvalue_return extends TreeRuleReturnScope {
		public String var = null;
		public int reg = -1;
		public Type tp = null;
	};


	// $ANTLR start "lvalue"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:463:1: lvalue[String funcName] returns [String var = null, int reg = -1, Type tp = null] : ( ^( DOT r1= ldot[funcName] id= ID ) |id= ID );
	public final ControlFlow.lvalue_return lvalue(String funcName) throws RecognitionException {
		ControlFlow.lvalue_return retval = new ControlFlow.lvalue_return();
		retval.start = input.LT(1);

		CommonTree id=null;
		TreeRuleReturnScope r1 =null;

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:464:2: ( ^( DOT r1= ldot[funcName] id= ID ) |id= ID )
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
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:464:4: ^( DOT r1= ldot[funcName] id= ID )
					{
					match(input,DOT,FOLLOW_DOT_in_lvalue540); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_ldot_in_lvalue544);
					r1=ldot(funcName);
					state._fsp--;

					id=(CommonTree)match(input,ID,FOLLOW_ID_in_lvalue549); 
					match(input, Token.UP, null); 


								retval.var = (id!=null?id.getText():null);
								retval.reg = (r1!=null?((ControlFlow.ldot_return)r1).reg:0);
								retval.tp = (r1!=null?((ControlFlow.ldot_return)r1).tp:null);
							
					}
					break;
				case 2 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:470:4: id= ID
					{
					id=(CommonTree)match(input,ID,FOLLOW_ID_in_lvalue561); 

								retval.var = (id!=null?id.getText():null);
							
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
		return retval;
	}
	// $ANTLR end "lvalue"


	public static class ldot_return extends TreeRuleReturnScope {
		public int reg = -1;
		public Type tp = null;
	};


	// $ANTLR start "ldot"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:476:1: ldot[String funcName] returns [int reg = -1, Type tp = null] : ( ^( DOT r1= ldot[funcName] id= ID ) |id= ID );
	public final ControlFlow.ldot_return ldot(String funcName) throws RecognitionException {
		ControlFlow.ldot_return retval = new ControlFlow.ldot_return();
		retval.start = input.LT(1);

		CommonTree id=null;
		TreeRuleReturnScope r1 =null;

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:477:2: ( ^( DOT r1= ldot[funcName] id= ID ) |id= ID )
			int alt13=2;
			int LA13_0 = input.LA(1);
			if ( (LA13_0==DOT) ) {
				alt13=1;
			}
			else if ( (LA13_0==ID) ) {
				alt13=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 13, 0, input);
				throw nvae;
			}

			switch (alt13) {
				case 1 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:477:4: ^( DOT r1= ldot[funcName] id= ID )
					{
					match(input,DOT,FOLLOW_DOT_in_ldot581); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_ldot_in_ldot585);
					r1=ldot(funcName);
					state._fsp--;

					id=(CommonTree)match(input,ID,FOLLOW_ID_in_ldot590); 
					match(input, Token.UP, null); 


								retval.reg = regCounter;
								retval.tp = sTypes.getValType(((Struct)(r1!=null?((ControlFlow.ldot_return)r1).tp:null)).structName(), (id!=null?id.getText():null));
								
								Register targetReg = new Register(regCounter++);
								
								Vector<String> valList = structVarNames.get(((Struct)(r1!=null?((ControlFlow.ldot_return)r1).tp:null)).structName());
								
								int offset = valList.indexOf((id!=null?id.getText():null)) * 8;
										
								Movq movq = new Movq(new Immediate(offset), new Register((r1!=null?((ControlFlow.ldot_return)r1).reg:0)), targetReg);
								currBlock.addAssembly(movq);
								
								Loadai loadai = new Loadai(new Register((r1!=null?((ControlFlow.ldot_return)r1).reg:0)), (id!=null?id.getText():null), targetReg);
								currBlock.addInstr(loadai);
							
					}
					break;
				case 2 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:494:4: id= ID
					{
					id=(CommonTree)match(input,ID,FOLLOW_ID_in_ldot602); 

								//Check local vars
								if (sTable.varPrevDefined(funcName, (id!=null?id.getText():null)))
								{
									retval.reg = localRegMap.get((id!=null?id.getText():null)).getRegNum();
									retval.tp = sTable.getVarType(funcName, (id!=null?id.getText():null));
								}
								//Check params
								else if (paramRegMap.containsKey((id!=null?id.getText():null)))
								{
									retval.reg = paramRegMap.get((id!=null?id.getText():null)).getRegNum();
									retval.tp = funcs.get(funcName).paramType((id!=null?id.getText():null));
								}
								//Must be global
								else
								{
									retval.reg = regCounter;
									retval.tp = sTable.getVarType("global", (id!=null?id.getText():null));
									
									Register targetReg = new Register(regCounter++);
									
									Movq movq = new Movq(("glob_" + (id!=null?id.getText():null)), new Register("%rip"), targetReg);
									currBlock.addAssembly(movq);
									
									Loadglobal loadglobal = new Loadglobal(new Id((id!=null?id.getText():null)), targetReg);
									currBlock.addInstr(loadglobal);
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
		return retval;
	}
	// $ANTLR end "ldot"



	// $ANTLR start "print"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:525:1: print[String funcName] : ^( PRINT r1= expression[funcName] ( ENDL )? ) ;
	public final void print(String funcName) throws RecognitionException {
		TreeRuleReturnScope r1 =null;


				int flag = 0;
			
		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:529:3: ( ^( PRINT r1= expression[funcName] ( ENDL )? ) )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:529:5: ^( PRINT r1= expression[funcName] ( ENDL )? )
			{
			match(input,PRINT,FOLLOW_PRINT_in_print623); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_expression_in_print627);
			r1=expression(funcName);
			state._fsp--;

			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:529:37: ( ENDL )?
			int alt14=2;
			int LA14_0 = input.LA(1);
			if ( (LA14_0==ENDL) ) {
				alt14=1;
			}
			switch (alt14) {
				case 1 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:529:38: ENDL
					{
					match(input,ENDL,FOLLOW_ENDL_in_print631); 

								Register reg = new Register((r1!=null?((ControlFlow.expression_return)r1).reg:0));
								Register targetReg = new Register(regCounter++);
								
								currBlock.addInstr(new Println(reg));
								
								Movq movq = new Movq(".LLC0", new Register("%rdi"));
								Movq movq2 = new Movq(reg, new Register("%rsi"));
								Movq movq3 = new Movq(new Immediate(0), new Register("%rax"));
								
								Call call = new Call("printf");
								
								currBlock.addAssembly(movq);
								currBlock.addAssembly(movq2);
								currBlock.addAssembly(movq3);
								callerSavePush(currBlock);
								currBlock.addAssembly(call);
								callerSavePop(currBlock);
								
								flag=1;
							
					}
					break;

			}


						if (flag==0) 
						{
							Register reg = new Register((r1!=null?((ControlFlow.expression_return)r1).reg:0));
							
							currBlock.addInstr(new Print(reg));
							
							Movq movq = new Movq(".LLC1", new Register("%rdi"));
							Movq movq2 = new Movq(reg, new Register("%rsi"));
							Movq movq3 = new Movq(new Immediate(0), new Register("%rax"));
							Call call = new Call("printf");
							
							currBlock.addAssembly(movq);
							currBlock.addAssembly(movq2);
							currBlock.addAssembly(movq3);
							callerSavePush(currBlock);
							currBlock.addAssembly(call);
							callerSavePop(currBlock);
						}
					
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
	// $ANTLR end "print"



	// $ANTLR start "read"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:573:1: read[String funcName] : ^( READ x= lvalue[funcName] ) ;
	public final void read(String funcName) throws RecognitionException {
		TreeRuleReturnScope x =null;

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:574:2: ( ^( READ x= lvalue[funcName] ) )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:574:4: ^( READ x= lvalue[funcName] )
			{
			match(input,READ,FOLLOW_READ_in_read656); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_lvalue_in_read660);
			x=lvalue(funcName);
			state._fsp--;

			match(input, Token.UP, null); 

				
						Register rsi = new Register("%rsi");
						Register readReg = new Register(regCounter++);
						
						Movq movq = new Movq(".LLC2", new Register("%rdi"));
						Movq movq2 = new Movq("glob_rd", new Register("%rsi"));
						Movq movq3 = new Movq(new Immediate(0), new Register("%rax"));
						Call call = new Call("scanf");
						
						currBlock.addAssembly(movq);
						currBlock.addAssembly(movq2);
						currBlock.addAssembly(movq3);
						callerSavePush(currBlock);
						currBlock.addAssembly(call);
						callerSavePop(currBlock);
									
						Read read = new Read(readReg);
						currBlock.addInstr(read);	
						
						if ((x!=null?((ControlFlow.lvalue_return)x).reg:0) != -1)
						{	
							Register targetReg = new Register((x!=null?((ControlFlow.lvalue_return)x).reg:0));
							
							Vector<String> valList = structVarNames.get(((Struct)(x!=null?((ControlFlow.lvalue_return)x).tp:null)).structName());
						
							int offset = valList.indexOf((x!=null?((ControlFlow.lvalue_return)x).var:null)) * 8;
									
							Movq movq4 = new Movq("glob_rd", new Register("%rip"), readReg);
							Movq movq5 = new Movq(readReg, new Immediate(offset), targetReg);
							currBlock.addAssembly(movq4);
							currBlock.addAssembly(movq5);
							
							Storeai storeai = new Storeai(readReg, targetReg, (x!=null?((ControlFlow.lvalue_return)x).var:null));
							currBlock.addInstr(storeai);
						}
						else
						{
							//Check local vars
							if (sTable.varPrevDefined(funcName, (x!=null?((ControlFlow.lvalue_return)x).var:null)))
							{
								Mov mov = new Mov(readReg, localRegMap.get((x!=null?((ControlFlow.lvalue_return)x).var:null)));
								currBlock.addInstr(mov);
								
								Movq movq6 = new Movq("glob_rd", new Register("%rip"), localRegMap.get((x!=null?((ControlFlow.lvalue_return)x).var:null)));
								currBlock.addAssembly(movq6);
							}
							//Check params
							else if (paramRegMap.containsKey((x!=null?((ControlFlow.lvalue_return)x).var:null)))
							{
								Mov mov = new Mov(readReg, paramRegMap.get((x!=null?((ControlFlow.lvalue_return)x).var:null)));
								currBlock.addInstr(mov);
								
								Movq movq6 = new Movq("glob_rd", new Register("%rip"), paramRegMap.get((x!=null?((ControlFlow.lvalue_return)x).var:null)));
								currBlock.addAssembly(movq6);
							}
							//Must be global
							else
							{
								Movq movq6 = new Movq("glob_rd", new Register("%rip"), readReg);
								Movq movq7 = new Movq(readReg, ("glob_" + (x!=null?((ControlFlow.lvalue_return)x).var:null)), new Register("%rip"));
								currBlock.addAssembly(movq6);
								currBlock.addAssembly(movq7);
				
								Storeglobal storeglobal = new Storeglobal(readReg, new Id((x!=null?((ControlFlow.lvalue_return)x).var:null)));
								currBlock.addInstr(storeglobal);
								
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
	// $ANTLR end "read"



	// $ANTLR start "conditional"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:646:1: conditional[String funcName] : ^( IF r1= expression[funcName] block[funcName] ( block[funcName] )? ) ;
	public final void conditional(String funcName) throws RecognitionException {
		TreeRuleReturnScope r1 =null;

		 
					BasicBlock topBlock = currBlock;
		            BasicBlock endThen;
		            Jmp jmp; 
				  
		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:651:6: ( ^( IF r1= expression[funcName] block[funcName] ( block[funcName] )? ) )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:651:8: ^( IF r1= expression[funcName] block[funcName] ( block[funcName] )? )
			{
			match(input,IF,FOLLOW_IF_in_conditional682); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_expression_in_conditional686);
			r1=expression(funcName);
			state._fsp--;

			  
						BasicBlock thenBlock = new BasicBlock("L" + labelCounter++);
						
						topBlock.addSuccessor(thenBlock);
						thenBlock.addPredecessor(topBlock);
						
						currBlock = thenBlock;
					
			pushFollow(FOLLOW_block_in_conditional694);
			block(funcName);
			state._fsp--;


						endThen = currBlock;
						
						BasicBlock elseBlock = new BasicBlock("L" + labelCounter++);
						
						topBlock.addSuccessor(elseBlock);
						elseBlock.addPredecessor(topBlock);
						
						currBlock = elseBlock;
						
						Compi compi = new Compi(new Register((r1!=null?((ControlFlow.expression_return)r1).reg:0)), new Immediate(1), new Register("ccr"));
						topBlock.addInstr(compi);
						Cmp cmp = new Cmp(new Immediate(1), new Register((r1!=null?((ControlFlow.expression_return)r1).reg:0)));
						topBlock.addAssembly(cmp);
						
						Cbreq cbreq = new Cbreq(new Register("ccr"), new Label(thenBlock.getLabel()), new Label(elseBlock.getLabel()));
						topBlock.addInstr(cbreq);
						Je je = new Je(new Label(thenBlock.getLabel()));
						jmp = new Jmp(new Label(elseBlock.getLabel()));
						topBlock.addAssembly(je);
						topBlock.addAssembly(jmp);
						
					
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:682:5: ( block[funcName] )?
			int alt15=2;
			int LA15_0 = input.LA(1);
			if ( (LA15_0==BLOCK) ) {
				alt15=1;
			}
			switch (alt15) {
				case 1 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:682:6: block[funcName]
					{
					pushFollow(FOLLOW_block_in_conditional702);
					block(funcName);
					state._fsp--;

					}
					break;

			}

			match(input, Token.UP, null); 


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
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:731:1: loop[String funcName] : ^( WHILE r1= expression[funcName] block[funcName] r2= expression[funcName] ) ;
	public final void loop(String funcName) throws RecognitionException {
		TreeRuleReturnScope r1 =null;
		TreeRuleReturnScope r2 =null;


					BasicBlock topBlock, loopBody;
					Jmp jmp;
					Cmp cmp;
					Je je;
				  
		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:737:6: ( ^( WHILE r1= expression[funcName] block[funcName] r2= expression[funcName] ) )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:737:8: ^( WHILE r1= expression[funcName] block[funcName] r2= expression[funcName] )
			{
			match(input,WHILE,FOLLOW_WHILE_in_loop726); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_expression_in_loop730);
			r1=expression(funcName);
			state._fsp--;


						topBlock = currBlock;
						loopBody = new BasicBlock("L" + labelCounter++);
						
						topBlock.addSuccessor(loopBody);
						loopBody.addPredecessor(topBlock);
						
						currBlock = loopBody;
						
					
			pushFollow(FOLLOW_block_in_loop738);
			block(funcName);
			state._fsp--;


						BasicBlock afterBlock = new BasicBlock("L" + labelCounter++);
						
						currBlock.addSuccessor(loopBody);
						currBlock.addSuccessor(afterBlock);
						
						topBlock.addSuccessor(afterBlock);
						
						afterBlock.addPredecessor(currBlock);
						afterBlock.addPredecessor(topBlock);
						
						Compi compi = new Compi(new Register((r1!=null?((ControlFlow.expression_return)r1).reg:0)), new Immediate(1), new Register("ccr"));
						cmp = new Cmp(new Immediate(1), new Register((r1!=null?((ControlFlow.expression_return)r1).reg:0)));
						topBlock.addInstr(compi);
						topBlock.addAssembly(cmp);
						
						
						Cbreq cbreq = new Cbreq(new Register("ccr"), new Label(loopBody.getLabel()), new Label(afterBlock.getLabel()));
						topBlock.addInstr(cbreq);
						je = new Je(new Label(loopBody.getLabel()));
						jmp = new Jmp(new Label(afterBlock.getLabel()));
						topBlock.addAssembly(je);
						topBlock.addAssembly(jmp);
						
						//currBlock = afterBlock;
					
			pushFollow(FOLLOW_expression_in_loop747);
			r2=expression(funcName);
			state._fsp--;

			match(input, Token.UP, null); 


						Compi compimm = new Compi(new Register((r2!=null?((ControlFlow.expression_return)r2).reg:0)), new Immediate(1), new Register("ccr"));
						Cbreq cbrequal = new Cbreq(new Register("ccr"), new Label(loopBody.getLabel()), new Label(afterBlock.getLabel()));
						cmp = new Cmp(new Immediate(1), new Register((r2!=null?((ControlFlow.expression_return)r2).reg:0)));
						je = new Je(new Label(loopBody.getLabel()));
						jmp = new Jmp(new Label(afterBlock.getLabel()));
						
						currBlock.addInstr(compimm);
						currBlock.addInstr(cbrequal);
						
						currBlock.addAssembly(cmp);
						currBlock.addAssembly(je);
						currBlock.addAssembly(jmp);
						
						currBlock = afterBlock;
					
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
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:792:1: delete[String funcName] : ^( DELETE r1= expression[funcName] ) ;
	public final void delete(String funcName) throws RecognitionException {
		TreeRuleReturnScope r1 =null;

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:793:2: ( ^( DELETE r1= expression[funcName] ) )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:793:4: ^( DELETE r1= expression[funcName] )
			{
			match(input,DELETE,FOLLOW_DELETE_in_delete765); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_expression_in_delete769);
			r1=expression(funcName);
			state._fsp--;

			match(input, Token.UP, null); 


						Del del = new Del(new Register((r1!=null?((ControlFlow.expression_return)r1).reg:0)));
						currBlock.addInstr(del);
						
						Movq movq = new Movq(new Register((r1!=null?((ControlFlow.expression_return)r1).reg:0)), new Register("%rdi"));
						Call call = new Call("free");
						currBlock.addAssembly(movq);
						callerSavePush(currBlock);
						currBlock.addAssembly(call);
						callerSavePop(currBlock);
					
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
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:807:1: ret[String funcName] : ^( RETURN (r1= expression[funcName] )? ) ;
	public final void ret(String funcName) throws RecognitionException {
		TreeRuleReturnScope r1 =null;

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:808:2: ( ^( RETURN (r1= expression[funcName] )? ) )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:808:4: ^( RETURN (r1= expression[funcName] )? )
			{
			match(input,RETURN,FOLLOW_RETURN_in_ret787); 
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); 
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:808:13: (r1= expression[funcName] )?
				int alt16=2;
				int LA16_0 = input.LA(1);
				if ( (LA16_0==AND||(LA16_0 >= DIVIDE && LA16_0 <= DOT)||(LA16_0 >= EQ && LA16_0 <= FALSE)||(LA16_0 >= GE && LA16_0 <= ID)||(LA16_0 >= INTEGER && LA16_0 <= INVOKE)||LA16_0==LE||(LA16_0 >= LT && LA16_0 <= OR)||LA16_0==PLUS||(LA16_0 >= TIMES && LA16_0 <= TRUE)) ) {
					alt16=1;
				}
				switch (alt16) {
					case 1 :
						// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:808:14: r1= expression[funcName]
						{
						pushFollow(FOLLOW_expression_in_ret792);
						r1=expression(funcName);
						state._fsp--;

						}
						break;

				}

				match(input, Token.UP, null); 
			}


						currBlock.addSuccessor(exitBlock);
						exitBlock.addPredecessor(currBlock);
						
						if (!(funcs.get(funcName).getRetType() instanceof Void))
						{
							Storeret storeret = new Storeret(new Register((r1!=null?((ControlFlow.expression_return)r1).reg:0)));
							currBlock.addInstr(storeret);
							
							Movq movq = new Movq(new Register((r1!=null?((ControlFlow.expression_return)r1).reg:0)), new Register("%rax"));
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
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:834:1: invocation[String funcName] : ^( INVOKE id= ID argRegs= arguments[funcName] ) ;
	public final void invocation(String funcName) throws RecognitionException {
		CommonTree id=null;
		ArrayList<Register> argRegs =null;

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:835:2: ( ^( INVOKE id= ID argRegs= arguments[funcName] ) )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:835:4: ^( INVOKE id= ID argRegs= arguments[funcName] )
			{
			match(input,INVOKE,FOLLOW_INVOKE_in_invocation812); 
			match(input, Token.DOWN, null); 
			id=(CommonTree)match(input,ID,FOLLOW_ID_in_invocation816); 
			pushFollow(FOLLOW_arguments_in_invocation820);
			argRegs=arguments(funcName);
			state._fsp--;

			match(input, Token.UP, null); 

				
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
									case 0:	movq = new Movq(argRegs.get(i), new Register("%rdi"));
											currBlock.addAssembly(movq);
											break;
									case 1:	movq = new Movq(argRegs.get(i), new Register("%rsi"));
											currBlock.addAssembly(movq);
											break;
									case 2:	movq = new Movq(argRegs.get(i), new Register("%rdx"));
											currBlock.addAssembly(movq);
											break;
									case 3:	movq = new Movq(argRegs.get(i), new Register("%rcx"));
											currBlock.addAssembly(movq);
											break;
									case 4:	movq = new Movq(argRegs.get(i), new Register("%r8"));
											currBlock.addAssembly(movq);
											break;
									case 5:	movq = new Movq(argRegs.get(i), new Register("%r9"));
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
						Call call = new Call(new Label((id!=null?id.getText():null)));
						currBlock.addInstr(call);
						currBlock.addAssembly(call);
						
						
						if (!(funcs.get((id!=null?id.getText():null)).getRetType() instanceof Void))
						{
							Register targetReg = new Register(regCounter++);
							
							Movq movq = new Movq(new Register("%rax"), targetReg);
							currBlock.addAssembly(movq);
							
							Loadret loadret = new Loadret(targetReg);
							currBlock.addInstr(loadret);
						}
						
						// pop all caller-saved registers off stack
						callerSavePop(currBlock);
					
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
	// $ANTLR end "invocation"



	// $ANTLR start "arguments"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:905:1: arguments[String funcName] returns [ArrayList<Register> argRegs = null] : ( ^( ARGS (r1= expression[funcName] )+ ) | ARGS );
	public final ArrayList<Register> arguments(String funcName) throws RecognitionException {
		ArrayList<Register> argRegs =  null;


		TreeRuleReturnScope r1 =null;

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:906:2: ( ^( ARGS (r1= expression[funcName] )+ ) | ARGS )
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
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:906:4: ^( ARGS (r1= expression[funcName] )+ )
					{

						      argRegs = new ArrayList<Register>();
						  
					match(input,ARGS,FOLLOW_ARGS_in_arguments844); 
					match(input, Token.DOWN, null); 
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:908:13: (r1= expression[funcName] )+
					int cnt17=0;
					loop17:
					while (true) {
						int alt17=2;
						int LA17_0 = input.LA(1);
						if ( (LA17_0==AND||(LA17_0 >= DIVIDE && LA17_0 <= DOT)||(LA17_0 >= EQ && LA17_0 <= FALSE)||(LA17_0 >= GE && LA17_0 <= ID)||(LA17_0 >= INTEGER && LA17_0 <= INVOKE)||LA17_0==LE||(LA17_0 >= LT && LA17_0 <= OR)||LA17_0==PLUS||(LA17_0 >= TIMES && LA17_0 <= TRUE)) ) {
							alt17=1;
						}

						switch (alt17) {
						case 1 :
							// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:908:14: r1= expression[funcName]
							{
							pushFollow(FOLLOW_expression_in_arguments849);
							r1=expression(funcName);
							state._fsp--;

							 argRegs.add(new Register((r1!=null?((ControlFlow.expression_return)r1).reg:0))); 
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
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:909:4: ARGS
					{
					match(input,ARGS,FOLLOW_ARGS_in_arguments860); 
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
		return argRegs;
	}
	// $ANTLR end "arguments"


	public static class expression_return extends TreeRuleReturnScope {
		public int reg = -1;
		public Type tp = null;
	};


	// $ANTLR start "expression"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:912:1: expression[String funcName] returns [int reg = -1, Type tp = null] : ( ^( AND r1= expression[funcName] r2= expression[funcName] ) | ^( OR r1= expression[funcName] r2= expression[funcName] ) | ^( EQ r1= expression[funcName] r2= expression[funcName] ) | ^( LT r1= expression[funcName] r2= expression[funcName] ) | ^( GT r1= expression[funcName] r2= expression[funcName] ) | ^( NE r1= expression[funcName] r2= expression[funcName] ) | ^( LE r1= expression[funcName] r2= expression[funcName] ) | ^( GE r1= expression[funcName] r2= expression[funcName] ) | ^( PLUS r1= expression[funcName] r2= expression[funcName] ) | ^( MINUS r1= expression[funcName] r2= expression[funcName] ) | ^( TIMES r1= expression[funcName] r2= expression[funcName] ) | ^( DIVIDE r1= expression[funcName] r2= expression[funcName] ) | ^( NOT r1= expression[funcName] ) | ^( NEG r1= expression[funcName] ) | ^( DOT r1= expression[funcName] id= ID ) | ^( INVOKE id= ID argRegs= arguments[funcName] ) |id= ID | INTEGER | TRUE | FALSE | ^( NEW id= ID ) | NULL );
	public final ControlFlow.expression_return expression(String funcName) throws RecognitionException {
		ControlFlow.expression_return retval = new ControlFlow.expression_return();
		retval.start = input.LT(1);

		CommonTree id=null;
		CommonTree INTEGER1=null;
		TreeRuleReturnScope r1 =null;
		TreeRuleReturnScope r2 =null;
		ArrayList<Register> argRegs =null;

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:913:2: ( ^( AND r1= expression[funcName] r2= expression[funcName] ) | ^( OR r1= expression[funcName] r2= expression[funcName] ) | ^( EQ r1= expression[funcName] r2= expression[funcName] ) | ^( LT r1= expression[funcName] r2= expression[funcName] ) | ^( GT r1= expression[funcName] r2= expression[funcName] ) | ^( NE r1= expression[funcName] r2= expression[funcName] ) | ^( LE r1= expression[funcName] r2= expression[funcName] ) | ^( GE r1= expression[funcName] r2= expression[funcName] ) | ^( PLUS r1= expression[funcName] r2= expression[funcName] ) | ^( MINUS r1= expression[funcName] r2= expression[funcName] ) | ^( TIMES r1= expression[funcName] r2= expression[funcName] ) | ^( DIVIDE r1= expression[funcName] r2= expression[funcName] ) | ^( NOT r1= expression[funcName] ) | ^( NEG r1= expression[funcName] ) | ^( DOT r1= expression[funcName] id= ID ) | ^( INVOKE id= ID argRegs= arguments[funcName] ) |id= ID | INTEGER | TRUE | FALSE | ^( NEW id= ID ) | NULL )
			int alt19=22;
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
			case NULL:
				{
				alt19=22;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 19, 0, input);
				throw nvae;
			}
			switch (alt19) {
				case 1 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:913:4: ^( AND r1= expression[funcName] r2= expression[funcName] )
					{
					match(input,AND,FOLLOW_AND_in_expression876); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression880);
					r1=expression(funcName);
					state._fsp--;

					pushFollow(FOLLOW_expression_in_expression885);
					r2=expression(funcName);
					state._fsp--;

					match(input, Token.UP, null); 


								retval.reg = regCounter;
								
								if (genAssem)
								{
									Register targetReg = new Register(regCounter);
									Movq movq = new Movq(new Register((r2!=null?((ControlFlow.expression_return)r2).reg:0)), targetReg);
									Andq andq = new Andq(new Register((r1!=null?((ControlFlow.expression_return)r1).reg:0)), targetReg);
									
									currBlock.addAssembly(movq);
									currBlock.addAssembly(andq); 
								}
								
								And and = new And(new Register((r1!=null?((ControlFlow.expression_return)r1).reg:0)), new Register((r2!=null?((ControlFlow.expression_return)r2).reg:0)), new Register(regCounter++));
								currBlock.addInstr(and);
							
					}
					break;
				case 2 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:930:4: ^( OR r1= expression[funcName] r2= expression[funcName] )
					{
					match(input,OR,FOLLOW_OR_in_expression897); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression901);
					r1=expression(funcName);
					state._fsp--;

					pushFollow(FOLLOW_expression_in_expression906);
					r2=expression(funcName);
					state._fsp--;

					match(input, Token.UP, null); 


								retval.reg = regCounter;
								
								if (genAssem)
								{
									Register targetReg = new Register(regCounter);
									Movq movq = new Movq(new Register((r2!=null?((ControlFlow.expression_return)r2).reg:0)), targetReg);
									Orq orq = new Orq(new Register((r1!=null?((ControlFlow.expression_return)r1).reg:0)), targetReg);
									
									currBlock.addAssembly(movq);
									currBlock.addAssembly(orq); 
								}
								
								Or or = new Or(new Register((r1!=null?((ControlFlow.expression_return)r1).reg:0)), new Register((r2!=null?((ControlFlow.expression_return)r2).reg:0)), new Register(regCounter++));
								currBlock.addInstr(or);
							
					}
					break;
				case 3 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:947:4: ^( EQ r1= expression[funcName] r2= expression[funcName] )
					{
					match(input,EQ,FOLLOW_EQ_in_expression918); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression922);
					r1=expression(funcName);
					state._fsp--;

					pushFollow(FOLLOW_expression_in_expression927);
					r2=expression(funcName);
					state._fsp--;

					match(input, Token.UP, null); 


								retval.reg = regCounter;
								long val = 0;
								
					//			if (genAssem)
					//			{
									Register targetReg = new Register(regCounter++);
									
									Movq movq = new Movq(new Immediate(val), targetReg);
									Movq movq2 = new Movq(new Immediate(1), new Register(regCounter));
									Cmp cmp = new Cmp(new Register((r1!=null?((ControlFlow.expression_return)r1).reg:0)), new Register((r2!=null?((ControlFlow.expression_return)r2).reg:0)));			
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
								
								Comp comp = new Comp(new Register((r1!=null?((ControlFlow.expression_return)r1).reg:0)), new Register((r2!=null?((ControlFlow.expression_return)r2).reg:0)), new Register("ccr"));
								currBlock.addInstr(comp);
								
								Moveq moveq = new Moveq(new Register(regCounter), targetReg);
								currBlock.addInstr(moveq);
							
					}
					break;
				case 4 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:981:4: ^( LT r1= expression[funcName] r2= expression[funcName] )
					{
					match(input,LT,FOLLOW_LT_in_expression939); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression943);
					r1=expression(funcName);
					state._fsp--;

					pushFollow(FOLLOW_expression_in_expression948);
					r2=expression(funcName);
					state._fsp--;

					match(input, Token.UP, null); 


								retval.reg = regCounter;
								long val = 0;
								
					//			if (genAssem)
					//			{
									Register targetReg = new Register(regCounter++);
									
									Movq movq = new Movq(new Immediate(val), targetReg);
									Movq movq2 = new Movq(new Immediate(1), new Register(regCounter));
									Cmp cmp = new Cmp(new Register((r2!=null?((ControlFlow.expression_return)r2).reg:0)), new Register((r1!=null?((ControlFlow.expression_return)r1).reg:0)));
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
								
								Comp comp = new Comp(new Register((r1!=null?((ControlFlow.expression_return)r1).reg:0)), new Register((r2!=null?((ControlFlow.expression_return)r2).reg:0)), new Register("ccr"));
								currBlock.addInstr(comp);
								
								Movlt movlt = new Movlt(new Register(regCounter), targetReg);
								currBlock.addInstr(movlt);
							
					}
					break;
				case 5 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:1015:4: ^( GT r1= expression[funcName] r2= expression[funcName] )
					{
					match(input,GT,FOLLOW_GT_in_expression960); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression964);
					r1=expression(funcName);
					state._fsp--;

					pushFollow(FOLLOW_expression_in_expression969);
					r2=expression(funcName);
					state._fsp--;

					match(input, Token.UP, null); 


								retval.reg = regCounter;
								long val = 0;
								
					//			if (genAssem)
					//			{
									Register targetReg = new Register(regCounter++);
									
									Movq movq = new Movq(new Immediate(val), targetReg);
									Movq movq2 = new Movq(new Immediate(1), new Register(regCounter));
									Cmp cmp = new Cmp(new Register((r2!=null?((ControlFlow.expression_return)r2).reg:0)), new Register((r1!=null?((ControlFlow.expression_return)r1).reg:0)));
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
								
								Comp comp = new Comp(new Register((r1!=null?((ControlFlow.expression_return)r1).reg:0)), new Register((r2!=null?((ControlFlow.expression_return)r2).reg:0)), new Register("ccr"));
								currBlock.addInstr(comp);
								
								Movgt movgt = new Movgt(new Register(regCounter), targetReg);
								currBlock.addInstr(movgt);
							
					}
					break;
				case 6 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:1049:4: ^( NE r1= expression[funcName] r2= expression[funcName] )
					{
					match(input,NE,FOLLOW_NE_in_expression981); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression985);
					r1=expression(funcName);
					state._fsp--;

					pushFollow(FOLLOW_expression_in_expression990);
					r2=expression(funcName);
					state._fsp--;

					match(input, Token.UP, null); 


								retval.reg = regCounter;
								long val = 0;
								
					//			if (genAssem)
					//			{
									Register targetReg = new Register(regCounter++);
									
									Movq movq = new Movq(new Immediate(val), targetReg);
									Movq movq2 = new Movq(new Immediate(1), new Register(regCounter));
									Cmp cmp = new Cmp(new Register((r2!=null?((ControlFlow.expression_return)r2).reg:0)), new Register((r1!=null?((ControlFlow.expression_return)r1).reg:0)));
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
								
								Comp comp = new Comp(new Register((r1!=null?((ControlFlow.expression_return)r1).reg:0)), new Register((r2!=null?((ControlFlow.expression_return)r2).reg:0)), new Register("ccr"));
								currBlock.addInstr(comp);
								
								Movne movne = new Movne(new Register(regCounter), targetReg);
								currBlock.addInstr(movne);
							
					}
					break;
				case 7 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:1083:4: ^( LE r1= expression[funcName] r2= expression[funcName] )
					{
					match(input,LE,FOLLOW_LE_in_expression1002); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression1006);
					r1=expression(funcName);
					state._fsp--;

					pushFollow(FOLLOW_expression_in_expression1011);
					r2=expression(funcName);
					state._fsp--;

					match(input, Token.UP, null); 


								retval.reg = regCounter;
								long val = 0;
								
					//			if (genAssem)
					//			{
									Register targetReg = new Register(regCounter++);
									
									Movq movq = new Movq(new Immediate(val), targetReg);
									Movq movq2 = new Movq(new Immediate(1), new Register(regCounter));
									Cmp cmp = new Cmp(new Register((r2!=null?((ControlFlow.expression_return)r2).reg:0)), new Register((r1!=null?((ControlFlow.expression_return)r1).reg:0)));
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
								
								Comp comp = new Comp(new Register((r1!=null?((ControlFlow.expression_return)r1).reg:0)), new Register((r2!=null?((ControlFlow.expression_return)r2).reg:0)), new Register("ccr"));
								currBlock.addInstr(comp);
								
								Movle movle = new Movle(new Register(regCounter), targetReg);
								currBlock.addInstr(movle);
							
					}
					break;
				case 8 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:1117:4: ^( GE r1= expression[funcName] r2= expression[funcName] )
					{
					match(input,GE,FOLLOW_GE_in_expression1023); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression1027);
					r1=expression(funcName);
					state._fsp--;

					pushFollow(FOLLOW_expression_in_expression1032);
					r2=expression(funcName);
					state._fsp--;

					match(input, Token.UP, null); 


								retval.reg = regCounter;
								long val = 0;
								
					//			if (genAssem)
					//			{
									Register targetReg = new Register(regCounter++);
									
									Movq movq = new Movq(new Immediate(val), targetReg);
									Movq movq2 = new Movq(new Immediate(1), new Register(regCounter));
									Cmp cmp = new Cmp(new Register((r2!=null?((ControlFlow.expression_return)r2).reg:0)), new Register((r1!=null?((ControlFlow.expression_return)r1).reg:0)));
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
								
								Comp comp = new Comp(new Register((r1!=null?((ControlFlow.expression_return)r1).reg:0)), new Register((r2!=null?((ControlFlow.expression_return)r2).reg:0)), new Register("ccr"));
								currBlock.addInstr(comp);
								
								Movge movge = new Movge(new Register(regCounter), targetReg);
								currBlock.addInstr(movge);
							
					}
					break;
				case 9 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:1151:4: ^( PLUS r1= expression[funcName] r2= expression[funcName] )
					{
					match(input,PLUS,FOLLOW_PLUS_in_expression1044); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression1048);
					r1=expression(funcName);
					state._fsp--;

					pushFollow(FOLLOW_expression_in_expression1053);
					r2=expression(funcName);
					state._fsp--;

					match(input, Token.UP, null); 


								retval.reg = regCounter;
								
								if (genAssem)
								{
									Register targetReg = new Register(regCounter);
									Movq movq = new Movq(new Register((r1!=null?((ControlFlow.expression_return)r1).reg:0)), targetReg);
									Addq addq = new Addq(new Register((r2!=null?((ControlFlow.expression_return)r2).reg:0)), targetReg);
									
									currBlock.addAssembly(movq);
									currBlock.addAssembly(addq);
								}
								
								Add add = new Add(new Register((r1!=null?((ControlFlow.expression_return)r1).reg:0)), new Register((r2!=null?((ControlFlow.expression_return)r2).reg:0)), new Register(regCounter++));
								currBlock.addInstr(add);	
							
					}
					break;
				case 10 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:1168:4: ^( MINUS r1= expression[funcName] r2= expression[funcName] )
					{
					match(input,MINUS,FOLLOW_MINUS_in_expression1065); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression1069);
					r1=expression(funcName);
					state._fsp--;

					pushFollow(FOLLOW_expression_in_expression1074);
					r2=expression(funcName);
					state._fsp--;

					match(input, Token.UP, null); 

						
								retval.reg = regCounter;
								
								if (genAssem)
								{
									Register targetReg = new Register(regCounter);
									
									Movq movq = new Movq(new Register((r1!=null?((ControlFlow.expression_return)r1).reg:0)), targetReg);
									Subq subq = new Subq(new Register((r2!=null?((ControlFlow.expression_return)r2).reg:0)), targetReg);
									
									currBlock.addAssembly(movq);
									currBlock.addAssembly(subq); 
								}
								
								Sub sub = new Sub(new Register((r1!=null?((ControlFlow.expression_return)r1).reg:0)), new Register((r2!=null?((ControlFlow.expression_return)r2).reg:0)), new Register(regCounter++));
								currBlock.addInstr(sub);
							
					}
					break;
				case 11 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:1186:4: ^( TIMES r1= expression[funcName] r2= expression[funcName] )
					{
					match(input,TIMES,FOLLOW_TIMES_in_expression1086); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression1090);
					r1=expression(funcName);
					state._fsp--;

					pushFollow(FOLLOW_expression_in_expression1095);
					r2=expression(funcName);
					state._fsp--;

					match(input, Token.UP, null); 


								retval.reg = regCounter;
								
								if (genAssem)
								{
									Register targetReg = new Register(regCounter);
									
									Movq movq = new Movq(new Register((r1!=null?((ControlFlow.expression_return)r1).reg:0)), targetReg);
									Imulq imulq = new Imulq(new Register((r2!=null?((ControlFlow.expression_return)r2).reg:0)), targetReg);
									
									currBlock.addAssembly(movq);
									currBlock.addAssembly(imulq); 
								}
								
								Mult mult = new Mult(new Register((r1!=null?((ControlFlow.expression_return)r1).reg:0)), new Register((r2!=null?((ControlFlow.expression_return)r2).reg:0)), new Register(regCounter++));
								currBlock.addInstr(mult);
							
					}
					break;
				case 12 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:1204:4: ^( DIVIDE r1= expression[funcName] r2= expression[funcName] )
					{
					match(input,DIVIDE,FOLLOW_DIVIDE_in_expression1107); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression1111);
					r1=expression(funcName);
					state._fsp--;

					pushFollow(FOLLOW_expression_in_expression1116);
					r2=expression(funcName);
					state._fsp--;

					match(input, Token.UP, null); 


								retval.reg = regCounter;
								
								Movq movq = new Movq(new Register((r1!=null?((ControlFlow.expression_return)r1).reg:0)), new Register("%rax"));
								Cqto cqto = new Cqto();
								Divq divq = new Divq(new Register((r2!=null?((ControlFlow.expression_return)r2).reg:0)));
								Movq movq2 = new Movq(new Register("%rax"), new Register(regCounter));
								currBlock.addAssembly(movq);
								currBlock.addAssembly(cqto);
								currBlock.addAssembly(divq);
								currBlock.addAssembly(movq2);
								
								Div div = new Div(new Register((r1!=null?((ControlFlow.expression_return)r1).reg:0)), new Register((r2!=null?((ControlFlow.expression_return)r2).reg:0)), new Register(regCounter++));
								currBlock.addInstr(div);
							
					}
					break;
				case 13 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:1220:4: ^( NOT r1= expression[funcName] )
					{
					match(input,NOT,FOLLOW_NOT_in_expression1128); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression1132);
					r1=expression(funcName);
					state._fsp--;

					match(input, Token.UP, null); 


								retval.reg = regCounter;
								long val = 1;
								
								Movq movq = new Movq(new Register((r1!=null?((ControlFlow.expression_return)r1).reg:0)), new Register(regCounter));
								Xorq xorq = new Xorq(new Immediate(val), new Register(regCounter));
								
								currBlock.addAssembly(movq);
								currBlock.addAssembly(xorq);
								
								Xori xori = new Xori(new Register((r1!=null?((ControlFlow.expression_return)r1).reg:0)), new Immediate(val), new Register(regCounter++));
								currBlock.addInstr(xori);
								
							
					}
					break;
				case 14 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:1235:4: ^( NEG r1= expression[funcName] )
					{
					match(input,NEG,FOLLOW_NEG_in_expression1144); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression1148);
					r1=expression(funcName);
					state._fsp--;

					match(input, Token.UP, null); 


								retval.reg = regCounter;
								long val = 0;
								
								Movq movq = new Movq(new Immediate(0), new Register(regCounter));
								Subq subq = new Subq(new Register((r1!=null?((ControlFlow.expression_return)r1).reg:0)), new Register(regCounter));
								currBlock.addAssembly(movq);
								currBlock.addAssembly(subq);
								
								Rsubi rsubi = new Rsubi(new Register((r1!=null?((ControlFlow.expression_return)r1).reg:0)), new Immediate(val), new Register(regCounter++));
								currBlock.addInstr(rsubi);
							
					}
					break;
				case 15 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:1248:4: ^( DOT r1= expression[funcName] id= ID )
					{
					match(input,DOT,FOLLOW_DOT_in_expression1160); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression1164);
					r1=expression(funcName);
					state._fsp--;

					id=(CommonTree)match(input,ID,FOLLOW_ID_in_expression1169); 
					match(input, Token.UP, null); 


								retval.reg = regCounter;
								retval.tp = sTypes.getValType(((Struct)(r1!=null?((ControlFlow.expression_return)r1).tp:null)).structName(), (id!=null?id.getText():null));
								
								Register targetReg = new Register(regCounter++);
								
								Vector<String> valList = structVarNames.get(((Struct)(r1!=null?((ControlFlow.expression_return)r1).tp:null)).structName());
								
								int offset = valList.indexOf((id!=null?id.getText():null)) * 8;
										
								Movq movq = new Movq(new Immediate(offset), new Register((r1!=null?((ControlFlow.expression_return)r1).reg:0)), targetReg);
								currBlock.addAssembly(movq);
								
								Loadai loadai = new Loadai(new Register((r1!=null?((ControlFlow.expression_return)r1).reg:0)), (id!=null?id.getText():null), targetReg);
								currBlock.addInstr(loadai);
							
					}
					break;
				case 16 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:1265:4: ^( INVOKE id= ID argRegs= arguments[funcName] )
					{
					match(input,INVOKE,FOLLOW_INVOKE_in_expression1180); 
					match(input, Token.DOWN, null); 
					id=(CommonTree)match(input,ID,FOLLOW_ID_in_expression1184); 
					pushFollow(FOLLOW_arguments_in_expression1188);
					argRegs=arguments(funcName);
					state._fsp--;

					match(input, Token.UP, null); 


								if (argRegs != null)
								{
									Movq movq;
									
									for (int i=0; i < argRegs.size(); i++)
									{
										Storeoutargument storeoutargument = new Storeoutargument(argRegs.get(i), new Immediate(i));
										currBlock.addInstr(storeoutargument);
										
										switch(i)
										{
											case 0:	movq = new Movq(argRegs.get(i), new Register("%rdi"));
													currBlock.addAssembly(movq);
													break;
											case 1:	movq = new Movq(argRegs.get(i), new Register("%rsi"));
													currBlock.addAssembly(movq);
													break;
											case 2:	movq = new Movq(argRegs.get(i), new Register("%rdx"));
													currBlock.addAssembly(movq);
													break;
											case 3:	movq = new Movq(argRegs.get(i), new Register("%rcx"));
													currBlock.addAssembly(movq);
													break;
											case 4:	movq = new Movq(argRegs.get(i), new Register("%r8"));
													currBlock.addAssembly(movq);
													break;
											case 5:	movq = new Movq(argRegs.get(i), new Register("%r9"));
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
								Call call = new Call(new Label((id!=null?id.getText():null)));
								currBlock.addInstr(call);
								currBlock.addAssembly(call);
								
								retval.reg = regCounter;
								retval.tp = funcs.get((id!=null?id.getText():null)).returnType();
								
								if (!((funcs.get((id!=null?id.getText():null)).getRetType()) instanceof Void))
								{
									Movq movq = new Movq(new Register("%rax"), new Register(regCounter));
									currBlock.addAssembly(movq);
									
									Loadret loadret = new Loadret(new Register(regCounter++));
									currBlock.addInstr(loadret);
								}
								
								// pop all caller-saved registers off stack
								callerSavePop(currBlock);
							
					}
					break;
				case 17 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:1327:4: id= ID
					{
					id=(CommonTree)match(input,ID,FOLLOW_ID_in_expression1201); 

								//Check local vars
								if (sTable.varPrevDefined(funcName, (id!=null?id.getText():null)))
								{
									retval.reg = Integer.parseInt(localRegMap.get((id!=null?id.getText():null)).toString().substring(1));
									retval.tp = sTable.getVarType(funcName, (id!=null?id.getText():null));
								}
								//Check params
								else if (paramRegMap.containsKey((id!=null?id.getText():null)))
								{
									retval.reg = Integer.parseInt(paramRegMap.get((id!=null?id.getText():null)).toString().substring(1));
									retval.tp = funcs.get(funcName).paramType((id!=null?id.getText():null));
								}
								//Must be global
								else
								{
									retval.reg = regCounter;
									retval.tp = sTable.getVarType("global", (id!=null?id.getText():null));
									
									Register targetReg = new Register(regCounter++);
									
									Movq movq = new Movq(("glob_" + (id!=null?id.getText():null)), new Register("%rip"), targetReg);
									currBlock.addAssembly(movq);
									
									Loadglobal loadglobal = new Loadglobal(new Id((id!=null?id.getText():null)), targetReg);
									currBlock.addInstr(loadglobal);
								}
							
					}
					break;
				case 18 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:1356:4: INTEGER
					{
					INTEGER1=(CommonTree)match(input,INTEGER,FOLLOW_INTEGER_in_expression1211); 
					 
								retval.reg = regCounter;
								long val = Long.parseLong((INTEGER1!=null?INTEGER1.getText():null));
								
								if (genAssem)
								{
									Movq movq = new Movq(new Immediate(val), new Register(regCounter));
									currBlock.addAssembly(movq);
								}
								
								Loadi loadi = new Loadi(new Immediate(val), new Register(regCounter++));
								currBlock.addInstr(loadi);
							
					}
					break;
				case 19 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:1370:4: TRUE
					{
					match(input,TRUE,FOLLOW_TRUE_in_expression1220); 

								retval.reg = regCounter;
								long val = 1;
								
								if (genAssem)
								{
									Movq movq = new Movq(new Immediate(val), new Register(regCounter));
									currBlock.addAssembly(movq);
								}
								
								Loadi loadi = new Loadi(new Immediate(val), new Register(regCounter++));
								currBlock.addInstr(loadi);
							
					}
					break;
				case 20 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:1384:4: FALSE
					{
					match(input,FALSE,FOLLOW_FALSE_in_expression1230); 
					 
								retval.reg = regCounter;
								long val = 0;
								
								if (genAssem)
								{
									Movq movq = new Movq(new Immediate(val), new Register(regCounter));
									currBlock.addAssembly(movq);
								}
								
								Loadi loadi = new Loadi(new Immediate(val), new Register(regCounter++));
								currBlock.addInstr(loadi);
							
					}
					break;
				case 21 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:1398:4: ^( NEW id= ID )
					{
					match(input,NEW,FOLLOW_NEW_in_expression1241); 
					match(input, Token.DOWN, null); 
					id=(CommonTree)match(input,ID,FOLLOW_ID_in_expression1245); 
					match(input, Token.UP, null); 


								retval.reg = regCounter;
								
								Register targetReg = new Register(regCounter++);
								
								int numStrVals = sTypes.getStructTypes((id!=null?id.getText():null)).size();
								Movq movq = new Movq(new Immediate(numStrVals * 8), new Register("%rdi"));
								Call call = new Call("malloc");
								Movq afterMovq = new Movq(new Register("%rax"), targetReg);
								currBlock.addAssembly(movq);
								callerSavePush(currBlock);
								currBlock.addAssembly(call);
								currBlock.addAssembly(afterMovq);
								callerSavePop(currBlock);
								New newStruct = new New(structs.get((id!=null?id.getText():null)), sTypes.getStructTypes((id!=null?id.getText():null)), targetReg);
								currBlock.addInstr(newStruct); 
							
					}
					break;
				case 22 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\ControlFlow.g:1416:4: NULL
					{
					match(input,NULL,FOLLOW_NULL_in_expression1256); 

								retval.reg = regCounter;
								long val = 0;
								
								if (genAssem)
								{
									Movq movq = new Movq(new Immediate(val), new Register(regCounter));
									currBlock.addAssembly(movq);
								}
								
								Loadi loadi = new Loadi(new Immediate(val), new Register(regCounter++));
								currBlock.addInstr(loadi);
							
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
		return retval;
	}
	// $ANTLR end "expression"

	// Delegated rules



	public static final BitSet FOLLOW_PROGRAM_in_program50 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_types_in_program52 = new BitSet(new long[]{0x0000000000402000L});
	public static final BitSet FOLLOW_declarations_in_program54 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_functions_in_program57 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_EOF_in_program62 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TYPES_in_types73 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_struct_in_types75 = new BitSet(new long[]{0x0010000000000008L});
	public static final BitSet FOLLOW_DECLS_in_declarations89 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_declaration_in_declarations91 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_FUNCS_in_functions114 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_function_in_functions116 = new BitSet(new long[]{0x0000000000200008L});
	public static final BitSet FOLLOW_STRUCT_in_struct134 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_ID_in_struct138 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_decs_in_struct146 = new BitSet(new long[]{0x0000000000000808L});
	public static final BitSet FOLLOW_DECL_in_decs173 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_TYPE_in_decs176 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_type_in_decs180 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ID_in_decs185 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_INT_in_type205 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_BOOL_in_type212 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_STRUCT_in_type220 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_ID_in_type224 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_decl_list_in_declaration241 = new BitSet(new long[]{0x0000000000001002L});
	public static final BitSet FOLLOW_DECLLIST_in_decl_list258 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_TYPE_in_decl_list261 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_type_in_decl_list265 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_id_list_in_decl_list270 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ID_in_id_list295 = new BitSet(new long[]{0x0000000002000002L});
	public static final BitSet FOLLOW_FUN_in_function312 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_ID_in_function316 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_parameters_in_function323 = new BitSet(new long[]{0x0000800000000000L});
	public static final BitSet FOLLOW_RETTYPE_in_function327 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_return_type_in_function331 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_declarations_in_function340 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_statement_list_in_function343 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_PARAMS_in_parameters369 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_decs_in_parameters372 = new BitSet(new long[]{0x0000000000000808L});
	public static final BitSet FOLLOW_type_in_return_type393 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_VOID_in_return_type400 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_STMTS_in_statement_list414 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_statement_in_statement_list417 = new BitSet(new long[]{0x04014800240040C8L});
	public static final BitSet FOLLOW_block_in_statement432 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_assignment_in_statement438 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_print_in_statement444 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_read_in_statement450 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_conditional_in_statement456 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_loop_in_statement462 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_delete_in_statement468 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ret_in_statement474 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_invocation_in_statement480 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_BLOCK_in_block493 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_statement_list_in_block495 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ASSIGN_in_assignment509 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_assignment513 = new BitSet(new long[]{0x0000000002010000L});
	public static final BitSet FOLLOW_lvalue_in_assignment518 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_DOT_in_lvalue540 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_ldot_in_lvalue544 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_ID_in_lvalue549 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ID_in_lvalue561 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DOT_in_ldot581 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_ldot_in_ldot585 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_ID_in_ldot590 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ID_in_ldot602 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PRINT_in_print623 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_print627 = new BitSet(new long[]{0x0000000000040008L});
	public static final BitSet FOLLOW_ENDL_in_print631 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_READ_in_read656 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_lvalue_in_read660 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_IF_in_conditional682 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_conditional686 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_block_in_conditional694 = new BitSet(new long[]{0x0000000000000088L});
	public static final BitSet FOLLOW_block_in_conditional702 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_WHILE_in_loop726 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_loop730 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_block_in_loop738 = new BitSet(new long[]{0x006005FEB3998010L});
	public static final BitSet FOLLOW_expression_in_loop747 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_DELETE_in_delete765 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_delete769 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_RETURN_in_ret787 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_ret792 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_INVOKE_in_invocation812 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_ID_in_invocation816 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_arguments_in_invocation820 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ARGS_in_arguments844 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_arguments849 = new BitSet(new long[]{0x006005FEB3998018L});
	public static final BitSet FOLLOW_ARGS_in_arguments860 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_AND_in_expression876 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression880 = new BitSet(new long[]{0x006005FEB3998010L});
	public static final BitSet FOLLOW_expression_in_expression885 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_OR_in_expression897 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression901 = new BitSet(new long[]{0x006005FEB3998010L});
	public static final BitSet FOLLOW_expression_in_expression906 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_EQ_in_expression918 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression922 = new BitSet(new long[]{0x006005FEB3998010L});
	public static final BitSet FOLLOW_expression_in_expression927 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_LT_in_expression939 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression943 = new BitSet(new long[]{0x006005FEB3998010L});
	public static final BitSet FOLLOW_expression_in_expression948 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_GT_in_expression960 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression964 = new BitSet(new long[]{0x006005FEB3998010L});
	public static final BitSet FOLLOW_expression_in_expression969 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_NE_in_expression981 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression985 = new BitSet(new long[]{0x006005FEB3998010L});
	public static final BitSet FOLLOW_expression_in_expression990 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_LE_in_expression1002 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression1006 = new BitSet(new long[]{0x006005FEB3998010L});
	public static final BitSet FOLLOW_expression_in_expression1011 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_GE_in_expression1023 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression1027 = new BitSet(new long[]{0x006005FEB3998010L});
	public static final BitSet FOLLOW_expression_in_expression1032 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_PLUS_in_expression1044 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression1048 = new BitSet(new long[]{0x006005FEB3998010L});
	public static final BitSet FOLLOW_expression_in_expression1053 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_MINUS_in_expression1065 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression1069 = new BitSet(new long[]{0x006005FEB3998010L});
	public static final BitSet FOLLOW_expression_in_expression1074 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_TIMES_in_expression1086 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression1090 = new BitSet(new long[]{0x006005FEB3998010L});
	public static final BitSet FOLLOW_expression_in_expression1095 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_DIVIDE_in_expression1107 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression1111 = new BitSet(new long[]{0x006005FEB3998010L});
	public static final BitSet FOLLOW_expression_in_expression1116 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_NOT_in_expression1128 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression1132 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_NEG_in_expression1144 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression1148 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_DOT_in_expression1160 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression1164 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_ID_in_expression1169 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_INVOKE_in_expression1180 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_ID_in_expression1184 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_arguments_in_expression1188 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ID_in_expression1201 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INTEGER_in_expression1211 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TRUE_in_expression1220 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FALSE_in_expression1230 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NEW_in_expression1241 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_ID_in_expression1245 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_NULL_in_expression1256 = new BitSet(new long[]{0x0000000000000002L});
}
