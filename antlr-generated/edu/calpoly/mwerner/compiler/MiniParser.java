// $ANTLR 3.5.2 C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g 2014-06-10 15:52:40

   package edu.calpoly.mwerner.compiler;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.tree.*;


@SuppressWarnings("all")
public class MiniParser extends Parser {
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
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public MiniParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public MiniParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
	}

	protected TreeAdaptor adaptor = new CommonTreeAdaptor();

	public void setTreeAdaptor(TreeAdaptor adaptor) {
		this.adaptor = adaptor;
	}
	public TreeAdaptor getTreeAdaptor() {
		return adaptor;
	}
	@Override public String[] getTokenNames() { return MiniParser.tokenNames; }
	@Override public String getGrammarFileName() { return "C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g"; }


	   private boolean _errors = false;
	   public boolean hasErrors()
	   {
	      return _errors;
	   }


	public static class program_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "program"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:119:1: program : t= types d= declarations f= functions EOF -> ^( PROGRAM $t $d $f) ;
	public final MiniParser.program_return program() throws RecognitionException {
		MiniParser.program_return retval = new MiniParser.program_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token EOF1=null;
		ParserRuleReturnScope t =null;
		ParserRuleReturnScope d =null;
		ParserRuleReturnScope f =null;

		Object EOF1_tree=null;
		RewriteRuleTokenStream stream_EOF=new RewriteRuleTokenStream(adaptor,"token EOF");
		RewriteRuleSubtreeStream stream_functions=new RewriteRuleSubtreeStream(adaptor,"rule functions");
		RewriteRuleSubtreeStream stream_types=new RewriteRuleSubtreeStream(adaptor,"rule types");
		RewriteRuleSubtreeStream stream_declarations=new RewriteRuleSubtreeStream(adaptor,"rule declarations");

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:120:4: (t= types d= declarations f= functions EOF -> ^( PROGRAM $t $d $f) )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:120:7: t= types d= declarations f= functions EOF
			{
			pushFollow(FOLLOW_types_in_program1055);
			t=types();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_types.add(t.getTree());
			pushFollow(FOLLOW_declarations_in_program1059);
			d=declarations();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_declarations.add(d.getTree());
			pushFollow(FOLLOW_functions_in_program1063);
			f=functions();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_functions.add(f.getTree());
			EOF1=(Token)match(input,EOF,FOLLOW_EOF_in_program1065); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_EOF.add(EOF1);

			// AST REWRITE
			// elements: t, f, d
			// token labels: 
			// rule labels: f, retval, d, t
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_f=new RewriteRuleSubtreeStream(adaptor,"rule f",f!=null?f.getTree():null);
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
			RewriteRuleSubtreeStream stream_d=new RewriteRuleSubtreeStream(adaptor,"rule d",d!=null?d.getTree():null);
			RewriteRuleSubtreeStream stream_t=new RewriteRuleSubtreeStream(adaptor,"rule t",t!=null?t.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 121:7: -> ^( PROGRAM $t $d $f)
			{
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:121:10: ^( PROGRAM $t $d $f)
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(PROGRAM, "PROGRAM"), root_1);
				adaptor.addChild(root_1, stream_t.nextTree());
				adaptor.addChild(root_1, stream_d.nextTree());
				adaptor.addChild(root_1, stream_f.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		   catch (RecognitionException re)
		   {
		      reportError(re);
		      recover(input, re);
		      _errors = true;
		   }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "program"


	public static class types_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "types"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:123:1: types : ( ( STRUCT ID LBRACE )=> types_sub -> ^( TYPES types_sub ) | -> TYPES );
	public final MiniParser.types_return types() throws RecognitionException {
		MiniParser.types_return retval = new MiniParser.types_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope types_sub2 =null;

		RewriteRuleSubtreeStream stream_types_sub=new RewriteRuleSubtreeStream(adaptor,"rule types_sub");

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:124:4: ( ( STRUCT ID LBRACE )=> types_sub -> ^( TYPES types_sub ) | -> TYPES )
			int alt1=2;
			switch ( input.LA(1) ) {
			case STRUCT:
				{
				int LA1_1 = input.LA(2);
				if ( (synpred1_Mini()) ) {
					alt1=1;
				}
				else if ( (true) ) {
					alt1=2;
				}

				}
				break;
			case INT:
				{
				int LA1_2 = input.LA(2);
				if ( (synpred1_Mini()) ) {
					alt1=1;
				}
				else if ( (true) ) {
					alt1=2;
				}

				}
				break;
			case BOOL:
				{
				int LA1_3 = input.LA(2);
				if ( (synpred1_Mini()) ) {
					alt1=1;
				}
				else if ( (true) ) {
					alt1=2;
				}

				}
				break;
			case FUN:
				{
				int LA1_4 = input.LA(2);
				if ( (synpred1_Mini()) ) {
					alt1=1;
				}
				else if ( (true) ) {
					alt1=2;
				}

				}
				break;
			case EOF:
				{
				int LA1_5 = input.LA(2);
				if ( (synpred1_Mini()) ) {
					alt1=1;
				}
				else if ( (true) ) {
					alt1=2;
				}

				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 1, 0, input);
				throw nvae;
			}
			switch (alt1) {
				case 1 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:124:7: ( STRUCT ID LBRACE )=> types_sub
					{
					pushFollow(FOLLOW_types_sub_in_types1111);
					types_sub2=types_sub();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_types_sub.add(types_sub2.getTree());
					// AST REWRITE
					// elements: types_sub
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 124:39: -> ^( TYPES types_sub )
					{
						// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:124:42: ^( TYPES types_sub )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(TYPES, "TYPES"), root_1);
						adaptor.addChild(root_1, stream_types_sub.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 2 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:125:7: 
					{
					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 125:7: -> TYPES
					{
						adaptor.addChild(root_0, (Object)adaptor.create(TYPES, "TYPES"));
					}


					retval.tree = root_0;
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		   catch (RecognitionException re)
		   {
		      reportError(re);
		      recover(input, re);
		      _errors = true;
		   }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "types"


	public static class types_sub_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "types_sub"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:127:1: types_sub : ( ( STRUCT ID LBRACE )=> type_declaration types_sub |);
	public final MiniParser.types_sub_return types_sub() throws RecognitionException {
		MiniParser.types_sub_return retval = new MiniParser.types_sub_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope type_declaration3 =null;
		ParserRuleReturnScope types_sub4 =null;


		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:128:4: ( ( STRUCT ID LBRACE )=> type_declaration types_sub |)
			int alt2=2;
			int LA2_0 = input.LA(1);
			if ( (LA2_0==STRUCT) ) {
				int LA2_1 = input.LA(2);
				if ( (LA2_1==ID) ) {
					int LA2_3 = input.LA(3);
					if ( (LA2_3==LBRACE) && (synpred2_Mini())) {
						alt2=1;
					}
					else if ( (LA2_3==ID) ) {
						alt2=2;
					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 2, 3, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 2, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}
			else if ( (LA2_0==EOF||LA2_0==BOOL||LA2_0==FUN||LA2_0==INT) ) {
				alt2=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 2, 0, input);
				throw nvae;
			}

			switch (alt2) {
				case 1 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:128:7: ( STRUCT ID LBRACE )=> type_declaration types_sub
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_type_declaration_in_types_sub1154);
					type_declaration3=type_declaration();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, type_declaration3.getTree());

					pushFollow(FOLLOW_types_sub_in_types_sub1156);
					types_sub4=types_sub();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, types_sub4.getTree());

					}
					break;
				case 2 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:130:4: 
					{
					root_0 = (Object)adaptor.nil();


					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		   catch (RecognitionException re)
		   {
		      reportError(re);
		      recover(input, re);
		      _errors = true;
		   }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "types_sub"


	public static class type_declaration_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "type_declaration"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:131:1: type_declaration : STRUCT ^ ID LBRACE ! nested_decl RBRACE ! SEMI !;
	public final MiniParser.type_declaration_return type_declaration() throws RecognitionException {
		MiniParser.type_declaration_return retval = new MiniParser.type_declaration_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token STRUCT5=null;
		Token ID6=null;
		Token LBRACE7=null;
		Token RBRACE9=null;
		Token SEMI10=null;
		ParserRuleReturnScope nested_decl8 =null;

		Object STRUCT5_tree=null;
		Object ID6_tree=null;
		Object LBRACE7_tree=null;
		Object RBRACE9_tree=null;
		Object SEMI10_tree=null;

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:132:4: ( STRUCT ^ ID LBRACE ! nested_decl RBRACE ! SEMI !)
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:132:7: STRUCT ^ ID LBRACE ! nested_decl RBRACE ! SEMI !
			{
			root_0 = (Object)adaptor.nil();


			STRUCT5=(Token)match(input,STRUCT,FOLLOW_STRUCT_in_type_declaration1176); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			STRUCT5_tree = (Object)adaptor.create(STRUCT5);
			root_0 = (Object)adaptor.becomeRoot(STRUCT5_tree, root_0);
			}

			ID6=(Token)match(input,ID,FOLLOW_ID_in_type_declaration1179); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			ID6_tree = (Object)adaptor.create(ID6);
			adaptor.addChild(root_0, ID6_tree);
			}

			LBRACE7=(Token)match(input,LBRACE,FOLLOW_LBRACE_in_type_declaration1181); if (state.failed) return retval;
			pushFollow(FOLLOW_nested_decl_in_type_declaration1184);
			nested_decl8=nested_decl();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, nested_decl8.getTree());

			RBRACE9=(Token)match(input,RBRACE,FOLLOW_RBRACE_in_type_declaration1186); if (state.failed) return retval;
			SEMI10=(Token)match(input,SEMI,FOLLOW_SEMI_in_type_declaration1189); if (state.failed) return retval;
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		   catch (RecognitionException re)
		   {
		      reportError(re);
		      recover(input, re);
		      _errors = true;
		   }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "type_declaration"


	public static class nested_decl_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "nested_decl"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:134:1: nested_decl : ( decl SEMI !)+ ;
	public final MiniParser.nested_decl_return nested_decl() throws RecognitionException {
		MiniParser.nested_decl_return retval = new MiniParser.nested_decl_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token SEMI12=null;
		ParserRuleReturnScope decl11 =null;

		Object SEMI12_tree=null;

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:135:4: ( ( decl SEMI !)+ )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:135:7: ( decl SEMI !)+
			{
			root_0 = (Object)adaptor.nil();


			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:135:7: ( decl SEMI !)+
			int cnt3=0;
			loop3:
			while (true) {
				int alt3=2;
				int LA3_0 = input.LA(1);
				if ( (LA3_0==BOOL||LA3_0==INT||LA3_0==STRUCT) ) {
					alt3=1;
				}

				switch (alt3) {
				case 1 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:135:8: decl SEMI !
					{
					pushFollow(FOLLOW_decl_in_nested_decl1206);
					decl11=decl();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, decl11.getTree());

					SEMI12=(Token)match(input,SEMI,FOLLOW_SEMI_in_nested_decl1208); if (state.failed) return retval;
					}
					break;

				default :
					if ( cnt3 >= 1 ) break loop3;
					if (state.backtracking>0) {state.failed=true; return retval;}
					EarlyExitException eee = new EarlyExitException(3, input);
					throw eee;
				}
				cnt3++;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		   catch (RecognitionException re)
		   {
		      reportError(re);
		      recover(input, re);
		      _errors = true;
		   }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "nested_decl"


	public static class decl_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "decl"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:137:1: decl : t= type i= ID -> ^( DECL ^( TYPE $t) $i) ;
	public final MiniParser.decl_return decl() throws RecognitionException {
		MiniParser.decl_return retval = new MiniParser.decl_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token i=null;
		ParserRuleReturnScope t =null;

		Object i_tree=null;
		RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
		RewriteRuleSubtreeStream stream_type=new RewriteRuleSubtreeStream(adaptor,"rule type");

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:138:4: (t= type i= ID -> ^( DECL ^( TYPE $t) $i) )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:138:7: t= type i= ID
			{
			pushFollow(FOLLOW_type_in_decl1228);
			t=type();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_type.add(t.getTree());
			i=(Token)match(input,ID,FOLLOW_ID_in_decl1232); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_ID.add(i);

			// AST REWRITE
			// elements: t, i
			// token labels: i
			// rule labels: retval, t
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleTokenStream stream_i=new RewriteRuleTokenStream(adaptor,"token i",i);
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
			RewriteRuleSubtreeStream stream_t=new RewriteRuleSubtreeStream(adaptor,"rule t",t!=null?t.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 139:7: -> ^( DECL ^( TYPE $t) $i)
			{
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:139:10: ^( DECL ^( TYPE $t) $i)
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(DECL, "DECL"), root_1);
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:139:17: ^( TYPE $t)
				{
				Object root_2 = (Object)adaptor.nil();
				root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(TYPE, "TYPE"), root_2);
				adaptor.addChild(root_2, stream_t.nextTree());
				adaptor.addChild(root_1, root_2);
				}

				adaptor.addChild(root_1, stream_i.nextNode());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		   catch (RecognitionException re)
		   {
		      reportError(re);
		      recover(input, re);
		      _errors = true;
		   }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "decl"


	public static class type_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "type"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:141:1: type : ( INT | BOOL | STRUCT ^ ID );
	public final MiniParser.type_return type() throws RecognitionException {
		MiniParser.type_return retval = new MiniParser.type_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token INT13=null;
		Token BOOL14=null;
		Token STRUCT15=null;
		Token ID16=null;

		Object INT13_tree=null;
		Object BOOL14_tree=null;
		Object STRUCT15_tree=null;
		Object ID16_tree=null;

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:142:4: ( INT | BOOL | STRUCT ^ ID )
			int alt4=3;
			switch ( input.LA(1) ) {
			case INT:
				{
				alt4=1;
				}
				break;
			case BOOL:
				{
				alt4=2;
				}
				break;
			case STRUCT:
				{
				alt4=3;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 4, 0, input);
				throw nvae;
			}
			switch (alt4) {
				case 1 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:142:7: INT
					{
					root_0 = (Object)adaptor.nil();


					INT13=(Token)match(input,INT,FOLLOW_INT_in_type1269); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					INT13_tree = (Object)adaptor.create(INT13);
					adaptor.addChild(root_0, INT13_tree);
					}

					}
					break;
				case 2 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:143:7: BOOL
					{
					root_0 = (Object)adaptor.nil();


					BOOL14=(Token)match(input,BOOL,FOLLOW_BOOL_in_type1277); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					BOOL14_tree = (Object)adaptor.create(BOOL14);
					adaptor.addChild(root_0, BOOL14_tree);
					}

					}
					break;
				case 3 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:144:7: STRUCT ^ ID
					{
					root_0 = (Object)adaptor.nil();


					STRUCT15=(Token)match(input,STRUCT,FOLLOW_STRUCT_in_type1285); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					STRUCT15_tree = (Object)adaptor.create(STRUCT15);
					root_0 = (Object)adaptor.becomeRoot(STRUCT15_tree, root_0);
					}

					ID16=(Token)match(input,ID,FOLLOW_ID_in_type1288); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ID16_tree = (Object)adaptor.create(ID16);
					adaptor.addChild(root_0, ID16_tree);
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		   catch (RecognitionException re)
		   {
		      reportError(re);
		      recover(input, re);
		      _errors = true;
		   }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "type"


	public static class declarations_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "declarations"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:146:1: declarations : ( declaration )* -> ^( DECLS ( declaration )* ) ;
	public final MiniParser.declarations_return declarations() throws RecognitionException {
		MiniParser.declarations_return retval = new MiniParser.declarations_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope declaration17 =null;

		RewriteRuleSubtreeStream stream_declaration=new RewriteRuleSubtreeStream(adaptor,"rule declaration");

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:147:4: ( ( declaration )* -> ^( DECLS ( declaration )* ) )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:147:7: ( declaration )*
			{
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:147:7: ( declaration )*
			loop5:
			while (true) {
				int alt5=2;
				int LA5_0 = input.LA(1);
				if ( (LA5_0==BOOL||LA5_0==INT||LA5_0==STRUCT) ) {
					alt5=1;
				}

				switch (alt5) {
				case 1 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:147:8: declaration
					{
					pushFollow(FOLLOW_declaration_in_declarations1304);
					declaration17=declaration();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_declaration.add(declaration17.getTree());
					}
					break;

				default :
					break loop5;
				}
			}

			// AST REWRITE
			// elements: declaration
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 147:22: -> ^( DECLS ( declaration )* )
			{
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:147:25: ^( DECLS ( declaration )* )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(DECLS, "DECLS"), root_1);
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:147:33: ( declaration )*
				while ( stream_declaration.hasNext() ) {
					adaptor.addChild(root_1, stream_declaration.nextTree());
				}
				stream_declaration.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		   catch (RecognitionException re)
		   {
		      reportError(re);
		      recover(input, re);
		      _errors = true;
		   }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "declarations"


	public static class declaration_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "declaration"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:149:1: declaration : t= type ilist= id_list SEMI -> ^( DECLLIST ^( TYPE $t) $ilist) ;
	public final MiniParser.declaration_return declaration() throws RecognitionException {
		MiniParser.declaration_return retval = new MiniParser.declaration_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token SEMI18=null;
		ParserRuleReturnScope t =null;
		ParserRuleReturnScope ilist =null;

		Object SEMI18_tree=null;
		RewriteRuleTokenStream stream_SEMI=new RewriteRuleTokenStream(adaptor,"token SEMI");
		RewriteRuleSubtreeStream stream_type=new RewriteRuleSubtreeStream(adaptor,"rule type");
		RewriteRuleSubtreeStream stream_id_list=new RewriteRuleSubtreeStream(adaptor,"rule id_list");

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:150:4: (t= type ilist= id_list SEMI -> ^( DECLLIST ^( TYPE $t) $ilist) )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:150:7: t= type ilist= id_list SEMI
			{
			pushFollow(FOLLOW_type_in_declaration1332);
			t=type();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_type.add(t.getTree());
			pushFollow(FOLLOW_id_list_in_declaration1336);
			ilist=id_list();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_id_list.add(ilist.getTree());
			SEMI18=(Token)match(input,SEMI,FOLLOW_SEMI_in_declaration1338); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_SEMI.add(SEMI18);

			// AST REWRITE
			// elements: ilist, t
			// token labels: 
			// rule labels: retval, t, ilist
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
			RewriteRuleSubtreeStream stream_t=new RewriteRuleSubtreeStream(adaptor,"rule t",t!=null?t.getTree():null);
			RewriteRuleSubtreeStream stream_ilist=new RewriteRuleSubtreeStream(adaptor,"rule ilist",ilist!=null?ilist.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 151:7: -> ^( DECLLIST ^( TYPE $t) $ilist)
			{
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:151:10: ^( DECLLIST ^( TYPE $t) $ilist)
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(DECLLIST, "DECLLIST"), root_1);
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:151:21: ^( TYPE $t)
				{
				Object root_2 = (Object)adaptor.nil();
				root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(TYPE, "TYPE"), root_2);
				adaptor.addChild(root_2, stream_t.nextTree());
				adaptor.addChild(root_1, root_2);
				}

				adaptor.addChild(root_1, stream_ilist.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		   catch (RecognitionException re)
		   {
		      reportError(re);
		      recover(input, re);
		      _errors = true;
		   }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "declaration"


	public static class id_list_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "id_list"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:153:1: id_list : ID ( COMMA ! ID )* ;
	public final MiniParser.id_list_return id_list() throws RecognitionException {
		MiniParser.id_list_return retval = new MiniParser.id_list_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token ID19=null;
		Token COMMA20=null;
		Token ID21=null;

		Object ID19_tree=null;
		Object COMMA20_tree=null;
		Object ID21_tree=null;

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:154:4: ( ID ( COMMA ! ID )* )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:154:7: ID ( COMMA ! ID )*
			{
			root_0 = (Object)adaptor.nil();


			ID19=(Token)match(input,ID,FOLLOW_ID_in_id_list1375); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			ID19_tree = (Object)adaptor.create(ID19);
			adaptor.addChild(root_0, ID19_tree);
			}

			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:154:10: ( COMMA ! ID )*
			loop6:
			while (true) {
				int alt6=2;
				int LA6_0 = input.LA(1);
				if ( (LA6_0==COMMA) ) {
					alt6=1;
				}

				switch (alt6) {
				case 1 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:154:11: COMMA ! ID
					{
					COMMA20=(Token)match(input,COMMA,FOLLOW_COMMA_in_id_list1378); if (state.failed) return retval;
					ID21=(Token)match(input,ID,FOLLOW_ID_in_id_list1381); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ID21_tree = (Object)adaptor.create(ID21);
					adaptor.addChild(root_0, ID21_tree);
					}

					}
					break;

				default :
					break loop6;
				}
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		   catch (RecognitionException re)
		   {
		      reportError(re);
		      recover(input, re);
		      _errors = true;
		   }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "id_list"


	public static class functions_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "functions"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:156:1: functions : ( function )* -> ^( FUNCS ( function )* ) ;
	public final MiniParser.functions_return functions() throws RecognitionException {
		MiniParser.functions_return retval = new MiniParser.functions_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope function22 =null;

		RewriteRuleSubtreeStream stream_function=new RewriteRuleSubtreeStream(adaptor,"rule function");

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:157:4: ( ( function )* -> ^( FUNCS ( function )* ) )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:157:7: ( function )*
			{
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:157:7: ( function )*
			loop7:
			while (true) {
				int alt7=2;
				int LA7_0 = input.LA(1);
				if ( (LA7_0==FUN) ) {
					alt7=1;
				}

				switch (alt7) {
				case 1 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:157:7: function
					{
					pushFollow(FOLLOW_function_in_functions1398);
					function22=function();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_function.add(function22.getTree());
					}
					break;

				default :
					break loop7;
				}
			}

			// AST REWRITE
			// elements: function
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 157:17: -> ^( FUNCS ( function )* )
			{
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:157:20: ^( FUNCS ( function )* )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(FUNCS, "FUNCS"), root_1);
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:157:28: ( function )*
				while ( stream_function.hasNext() ) {
					adaptor.addChild(root_1, stream_function.nextTree());
				}
				stream_function.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		   catch (RecognitionException re)
		   {
		      reportError(re);
		      recover(input, re);
		      _errors = true;
		   }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "functions"


	public static class function_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "function"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:159:1: function : FUN id= ID p= parameters r= return_type LBRACE d= declarations s= statement_list RBRACE -> ^( FUN $id $p ^( RETTYPE $r) $d $s) ;
	public final MiniParser.function_return function() throws RecognitionException {
		MiniParser.function_return retval = new MiniParser.function_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token id=null;
		Token FUN23=null;
		Token LBRACE24=null;
		Token RBRACE25=null;
		ParserRuleReturnScope p =null;
		ParserRuleReturnScope r =null;
		ParserRuleReturnScope d =null;
		ParserRuleReturnScope s =null;

		Object id_tree=null;
		Object FUN23_tree=null;
		Object LBRACE24_tree=null;
		Object RBRACE25_tree=null;
		RewriteRuleTokenStream stream_RBRACE=new RewriteRuleTokenStream(adaptor,"token RBRACE");
		RewriteRuleTokenStream stream_FUN=new RewriteRuleTokenStream(adaptor,"token FUN");
		RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
		RewriteRuleTokenStream stream_LBRACE=new RewriteRuleTokenStream(adaptor,"token LBRACE");
		RewriteRuleSubtreeStream stream_statement_list=new RewriteRuleSubtreeStream(adaptor,"rule statement_list");
		RewriteRuleSubtreeStream stream_parameters=new RewriteRuleSubtreeStream(adaptor,"rule parameters");
		RewriteRuleSubtreeStream stream_return_type=new RewriteRuleSubtreeStream(adaptor,"rule return_type");
		RewriteRuleSubtreeStream stream_declarations=new RewriteRuleSubtreeStream(adaptor,"rule declarations");

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:160:4: ( FUN id= ID p= parameters r= return_type LBRACE d= declarations s= statement_list RBRACE -> ^( FUN $id $p ^( RETTYPE $r) $d $s) )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:160:7: FUN id= ID p= parameters r= return_type LBRACE d= declarations s= statement_list RBRACE
			{
			FUN23=(Token)match(input,FUN,FOLLOW_FUN_in_function1423); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_FUN.add(FUN23);

			id=(Token)match(input,ID,FOLLOW_ID_in_function1427); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_ID.add(id);

			pushFollow(FOLLOW_parameters_in_function1431);
			p=parameters();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_parameters.add(p.getTree());
			pushFollow(FOLLOW_return_type_in_function1435);
			r=return_type();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_return_type.add(r.getTree());
			LBRACE24=(Token)match(input,LBRACE,FOLLOW_LBRACE_in_function1437); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_LBRACE.add(LBRACE24);

			pushFollow(FOLLOW_declarations_in_function1441);
			d=declarations();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_declarations.add(d.getTree());
			pushFollow(FOLLOW_statement_list_in_function1445);
			s=statement_list();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_statement_list.add(s.getTree());
			RBRACE25=(Token)match(input,RBRACE,FOLLOW_RBRACE_in_function1447); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_RBRACE.add(RBRACE25);

			// AST REWRITE
			// elements: d, r, s, p, id, FUN
			// token labels: id
			// rule labels: retval, d, s, r, p
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleTokenStream stream_id=new RewriteRuleTokenStream(adaptor,"token id",id);
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
			RewriteRuleSubtreeStream stream_d=new RewriteRuleSubtreeStream(adaptor,"rule d",d!=null?d.getTree():null);
			RewriteRuleSubtreeStream stream_s=new RewriteRuleSubtreeStream(adaptor,"rule s",s!=null?s.getTree():null);
			RewriteRuleSubtreeStream stream_r=new RewriteRuleSubtreeStream(adaptor,"rule r",r!=null?r.getTree():null);
			RewriteRuleSubtreeStream stream_p=new RewriteRuleSubtreeStream(adaptor,"rule p",p!=null?p.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 161:7: -> ^( FUN $id $p ^( RETTYPE $r) $d $s)
			{
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:161:10: ^( FUN $id $p ^( RETTYPE $r) $d $s)
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot(stream_FUN.nextNode(), root_1);
				adaptor.addChild(root_1, stream_id.nextNode());
				adaptor.addChild(root_1, stream_p.nextTree());
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:161:23: ^( RETTYPE $r)
				{
				Object root_2 = (Object)adaptor.nil();
				root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(RETTYPE, "RETTYPE"), root_2);
				adaptor.addChild(root_2, stream_r.nextTree());
				adaptor.addChild(root_1, root_2);
				}

				adaptor.addChild(root_1, stream_d.nextTree());
				adaptor.addChild(root_1, stream_s.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		   catch (RecognitionException re)
		   {
		      reportError(re);
		      recover(input, re);
		      _errors = true;
		   }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "function"


	public static class parameters_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "parameters"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:163:1: parameters : LPAREN ( decl ( COMMA decl )* )? RPAREN -> ^( PARAMS ( decl )* ) ;
	public final MiniParser.parameters_return parameters() throws RecognitionException {
		MiniParser.parameters_return retval = new MiniParser.parameters_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token LPAREN26=null;
		Token COMMA28=null;
		Token RPAREN30=null;
		ParserRuleReturnScope decl27 =null;
		ParserRuleReturnScope decl29 =null;

		Object LPAREN26_tree=null;
		Object COMMA28_tree=null;
		Object RPAREN30_tree=null;
		RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
		RewriteRuleSubtreeStream stream_decl=new RewriteRuleSubtreeStream(adaptor,"rule decl");

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:164:4: ( LPAREN ( decl ( COMMA decl )* )? RPAREN -> ^( PARAMS ( decl )* ) )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:164:7: LPAREN ( decl ( COMMA decl )* )? RPAREN
			{
			LPAREN26=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_parameters1493); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN26);

			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:164:14: ( decl ( COMMA decl )* )?
			int alt9=2;
			int LA9_0 = input.LA(1);
			if ( (LA9_0==BOOL||LA9_0==INT||LA9_0==STRUCT) ) {
				alt9=1;
			}
			switch (alt9) {
				case 1 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:164:15: decl ( COMMA decl )*
					{
					pushFollow(FOLLOW_decl_in_parameters1496);
					decl27=decl();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_decl.add(decl27.getTree());
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:164:20: ( COMMA decl )*
					loop8:
					while (true) {
						int alt8=2;
						int LA8_0 = input.LA(1);
						if ( (LA8_0==COMMA) ) {
							alt8=1;
						}

						switch (alt8) {
						case 1 :
							// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:164:21: COMMA decl
							{
							COMMA28=(Token)match(input,COMMA,FOLLOW_COMMA_in_parameters1499); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_COMMA.add(COMMA28);

							pushFollow(FOLLOW_decl_in_parameters1501);
							decl29=decl();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_decl.add(decl29.getTree());
							}
							break;

						default :
							break loop8;
						}
					}

					}
					break;

			}

			RPAREN30=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_parameters1507); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN30);

			// AST REWRITE
			// elements: decl
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 165:7: -> ^( PARAMS ( decl )* )
			{
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:165:10: ^( PARAMS ( decl )* )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(PARAMS, "PARAMS"), root_1);
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:165:19: ( decl )*
				while ( stream_decl.hasNext() ) {
					adaptor.addChild(root_1, stream_decl.nextTree());
				}
				stream_decl.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		   catch (RecognitionException re)
		   {
		      reportError(re);
		      recover(input, re);
		      _errors = true;
		   }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "parameters"


	public static class return_type_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "return_type"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:167:1: return_type : ( type | VOID );
	public final MiniParser.return_type_return return_type() throws RecognitionException {
		MiniParser.return_type_return retval = new MiniParser.return_type_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token VOID32=null;
		ParserRuleReturnScope type31 =null;

		Object VOID32_tree=null;

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:168:4: ( type | VOID )
			int alt10=2;
			int LA10_0 = input.LA(1);
			if ( (LA10_0==BOOL||LA10_0==INT||LA10_0==STRUCT) ) {
				alt10=1;
			}
			else if ( (LA10_0==VOID) ) {
				alt10=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 10, 0, input);
				throw nvae;
			}

			switch (alt10) {
				case 1 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:168:7: type
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_type_in_return_type1537);
					type31=type();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, type31.getTree());

					}
					break;
				case 2 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:169:7: VOID
					{
					root_0 = (Object)adaptor.nil();


					VOID32=(Token)match(input,VOID,FOLLOW_VOID_in_return_type1545); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					VOID32_tree = (Object)adaptor.create(VOID32);
					adaptor.addChild(root_0, VOID32_tree);
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		   catch (RecognitionException re)
		   {
		      reportError(re);
		      recover(input, re);
		      _errors = true;
		   }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "return_type"


	public static class statement_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "statement"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:171:1: statement : ( block | ( lvalue ASSIGN )=> assignment | print | read | conditional | loop | delete | ret | ( ID LPAREN )=> invocation );
	public final MiniParser.statement_return statement() throws RecognitionException {
		MiniParser.statement_return retval = new MiniParser.statement_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope block33 =null;
		ParserRuleReturnScope assignment34 =null;
		ParserRuleReturnScope print35 =null;
		ParserRuleReturnScope read36 =null;
		ParserRuleReturnScope conditional37 =null;
		ParserRuleReturnScope loop38 =null;
		ParserRuleReturnScope delete39 =null;
		ParserRuleReturnScope ret40 =null;
		ParserRuleReturnScope invocation41 =null;


		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:172:4: ( block | ( lvalue ASSIGN )=> assignment | print | read | conditional | loop | delete | ret | ( ID LPAREN )=> invocation )
			int alt11=9;
			switch ( input.LA(1) ) {
			case LBRACE:
				{
				alt11=1;
				}
				break;
			case ID:
				{
				int LA11_2 = input.LA(2);
				if ( (LA11_2==DOT) && (synpred3_Mini())) {
					alt11=2;
				}
				else if ( (LA11_2==ASSIGN) && (synpred3_Mini())) {
					alt11=2;
				}
				else if ( (LA11_2==LPAREN) && (synpred4_Mini())) {
					alt11=9;
				}

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
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 11, 0, input);
				throw nvae;
			}
			switch (alt11) {
				case 1 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:172:7: block
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_block_in_statement1560);
					block33=block();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, block33.getTree());

					}
					break;
				case 2 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:173:7: ( lvalue ASSIGN )=> assignment
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_assignment_in_statement1576);
					assignment34=assignment();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, assignment34.getTree());

					}
					break;
				case 3 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:174:7: print
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_print_in_statement1584);
					print35=print();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, print35.getTree());

					}
					break;
				case 4 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:175:7: read
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_read_in_statement1592);
					read36=read();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, read36.getTree());

					}
					break;
				case 5 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:176:7: conditional
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_conditional_in_statement1600);
					conditional37=conditional();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, conditional37.getTree());

					}
					break;
				case 6 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:177:7: loop
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_loop_in_statement1608);
					loop38=loop();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, loop38.getTree());

					}
					break;
				case 7 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:178:7: delete
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_delete_in_statement1616);
					delete39=delete();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, delete39.getTree());

					}
					break;
				case 8 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:179:7: ret
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_ret_in_statement1624);
					ret40=ret();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, ret40.getTree());

					}
					break;
				case 9 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:180:7: ( ID LPAREN )=> invocation
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_invocation_in_statement1640);
					invocation41=invocation();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, invocation41.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		   catch (RecognitionException re)
		   {
		      reportError(re);
		      recover(input, re);
		      _errors = true;
		   }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "statement"


	public static class block_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "block"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:182:1: block : LBRACE s= statement_list RBRACE -> ^( BLOCK $s) ;
	public final MiniParser.block_return block() throws RecognitionException {
		MiniParser.block_return retval = new MiniParser.block_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token LBRACE42=null;
		Token RBRACE43=null;
		ParserRuleReturnScope s =null;

		Object LBRACE42_tree=null;
		Object RBRACE43_tree=null;
		RewriteRuleTokenStream stream_RBRACE=new RewriteRuleTokenStream(adaptor,"token RBRACE");
		RewriteRuleTokenStream stream_LBRACE=new RewriteRuleTokenStream(adaptor,"token LBRACE");
		RewriteRuleSubtreeStream stream_statement_list=new RewriteRuleSubtreeStream(adaptor,"rule statement_list");

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:183:4: ( LBRACE s= statement_list RBRACE -> ^( BLOCK $s) )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:183:7: LBRACE s= statement_list RBRACE
			{
			LBRACE42=(Token)match(input,LBRACE,FOLLOW_LBRACE_in_block1655); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_LBRACE.add(LBRACE42);

			pushFollow(FOLLOW_statement_list_in_block1659);
			s=statement_list();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_statement_list.add(s.getTree());
			RBRACE43=(Token)match(input,RBRACE,FOLLOW_RBRACE_in_block1661); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_RBRACE.add(RBRACE43);

			// AST REWRITE
			// elements: s
			// token labels: 
			// rule labels: retval, s
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
			RewriteRuleSubtreeStream stream_s=new RewriteRuleSubtreeStream(adaptor,"rule s",s!=null?s.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 184:7: -> ^( BLOCK $s)
			{
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:184:10: ^( BLOCK $s)
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(BLOCK, "BLOCK"), root_1);
				adaptor.addChild(root_1, stream_s.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		   catch (RecognitionException re)
		   {
		      reportError(re);
		      recover(input, re);
		      _errors = true;
		   }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "block"


	public static class statement_list_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "statement_list"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:186:1: statement_list : ( statement )* -> ^( STMTS ( statement )* ) ;
	public final MiniParser.statement_list_return statement_list() throws RecognitionException {
		MiniParser.statement_list_return retval = new MiniParser.statement_list_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope statement44 =null;

		RewriteRuleSubtreeStream stream_statement=new RewriteRuleSubtreeStream(adaptor,"rule statement");

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:187:4: ( ( statement )* -> ^( STMTS ( statement )* ) )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:187:7: ( statement )*
			{
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:187:7: ( statement )*
			loop12:
			while (true) {
				int alt12=2;
				int LA12_0 = input.LA(1);
				if ( (LA12_0==DELETE||(LA12_0 >= ID && LA12_0 <= IF)||LA12_0==LBRACE||LA12_0==PRINT||LA12_0==READ||LA12_0==RETURN||LA12_0==WHILE) ) {
					alt12=1;
				}

				switch (alt12) {
				case 1 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:187:8: statement
					{
					pushFollow(FOLLOW_statement_in_statement_list1692);
					statement44=statement();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_statement.add(statement44.getTree());
					}
					break;

				default :
					break loop12;
				}
			}

			// AST REWRITE
			// elements: statement
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 187:20: -> ^( STMTS ( statement )* )
			{
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:187:23: ^( STMTS ( statement )* )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(STMTS, "STMTS"), root_1);
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:187:31: ( statement )*
				while ( stream_statement.hasNext() ) {
					adaptor.addChild(root_1, stream_statement.nextTree());
				}
				stream_statement.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		   catch (RecognitionException re)
		   {
		      reportError(re);
		      recover(input, re);
		      _errors = true;
		   }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "statement_list"


	public static class assignment_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "assignment"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:189:1: assignment : l= lvalue ASSIGN e= expression SEMI -> ^( ASSIGN $e $l) ;
	public final MiniParser.assignment_return assignment() throws RecognitionException {
		MiniParser.assignment_return retval = new MiniParser.assignment_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token ASSIGN45=null;
		Token SEMI46=null;
		ParserRuleReturnScope l =null;
		ParserRuleReturnScope e =null;

		Object ASSIGN45_tree=null;
		Object SEMI46_tree=null;
		RewriteRuleTokenStream stream_SEMI=new RewriteRuleTokenStream(adaptor,"token SEMI");
		RewriteRuleTokenStream stream_ASSIGN=new RewriteRuleTokenStream(adaptor,"token ASSIGN");
		RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
		RewriteRuleSubtreeStream stream_lvalue=new RewriteRuleSubtreeStream(adaptor,"rule lvalue");

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:190:4: (l= lvalue ASSIGN e= expression SEMI -> ^( ASSIGN $e $l) )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:190:7: l= lvalue ASSIGN e= expression SEMI
			{
			pushFollow(FOLLOW_lvalue_in_assignment1720);
			l=lvalue();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_lvalue.add(l.getTree());
			ASSIGN45=(Token)match(input,ASSIGN,FOLLOW_ASSIGN_in_assignment1722); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_ASSIGN.add(ASSIGN45);

			pushFollow(FOLLOW_expression_in_assignment1726);
			e=expression();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_expression.add(e.getTree());
			SEMI46=(Token)match(input,SEMI,FOLLOW_SEMI_in_assignment1728); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_SEMI.add(SEMI46);

			// AST REWRITE
			// elements: e, ASSIGN, l
			// token labels: 
			// rule labels: retval, e, l
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
			RewriteRuleSubtreeStream stream_e=new RewriteRuleSubtreeStream(adaptor,"rule e",e!=null?e.getTree():null);
			RewriteRuleSubtreeStream stream_l=new RewriteRuleSubtreeStream(adaptor,"rule l",l!=null?l.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 191:7: -> ^( ASSIGN $e $l)
			{
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:191:10: ^( ASSIGN $e $l)
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot(stream_ASSIGN.nextNode(), root_1);
				adaptor.addChild(root_1, stream_e.nextTree());
				adaptor.addChild(root_1, stream_l.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		   catch (RecognitionException re)
		   {
		      reportError(re);
		      recover(input, re);
		      _errors = true;
		   }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "assignment"


	public static class print_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "print"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:193:1: print : PRINT ^ expression ( ENDL )? SEMI !;
	public final MiniParser.print_return print() throws RecognitionException {
		MiniParser.print_return retval = new MiniParser.print_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token PRINT47=null;
		Token ENDL49=null;
		Token SEMI50=null;
		ParserRuleReturnScope expression48 =null;

		Object PRINT47_tree=null;
		Object ENDL49_tree=null;
		Object SEMI50_tree=null;

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:194:4: ( PRINT ^ expression ( ENDL )? SEMI !)
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:194:7: PRINT ^ expression ( ENDL )? SEMI !
			{
			root_0 = (Object)adaptor.nil();


			PRINT47=(Token)match(input,PRINT,FOLLOW_PRINT_in_print1761); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			PRINT47_tree = (Object)adaptor.create(PRINT47);
			root_0 = (Object)adaptor.becomeRoot(PRINT47_tree, root_0);
			}

			pushFollow(FOLLOW_expression_in_print1764);
			expression48=expression();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, expression48.getTree());

			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:194:25: ( ENDL )?
			int alt13=2;
			int LA13_0 = input.LA(1);
			if ( (LA13_0==ENDL) ) {
				alt13=1;
			}
			switch (alt13) {
				case 1 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:194:26: ENDL
					{
					ENDL49=(Token)match(input,ENDL,FOLLOW_ENDL_in_print1767); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ENDL49_tree = (Object)adaptor.create(ENDL49);
					adaptor.addChild(root_0, ENDL49_tree);
					}

					}
					break;

			}

			SEMI50=(Token)match(input,SEMI,FOLLOW_SEMI_in_print1771); if (state.failed) return retval;
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		   catch (RecognitionException re)
		   {
		      reportError(re);
		      recover(input, re);
		      _errors = true;
		   }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "print"


	public static class read_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "read"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:196:1: read : READ ^ lvalue SEMI !;
	public final MiniParser.read_return read() throws RecognitionException {
		MiniParser.read_return retval = new MiniParser.read_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token READ51=null;
		Token SEMI53=null;
		ParserRuleReturnScope lvalue52 =null;

		Object READ51_tree=null;
		Object SEMI53_tree=null;

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:197:4: ( READ ^ lvalue SEMI !)
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:197:7: READ ^ lvalue SEMI !
			{
			root_0 = (Object)adaptor.nil();


			READ51=(Token)match(input,READ,FOLLOW_READ_in_read1787); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			READ51_tree = (Object)adaptor.create(READ51);
			root_0 = (Object)adaptor.becomeRoot(READ51_tree, root_0);
			}

			pushFollow(FOLLOW_lvalue_in_read1790);
			lvalue52=lvalue();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, lvalue52.getTree());

			SEMI53=(Token)match(input,SEMI,FOLLOW_SEMI_in_read1792); if (state.failed) return retval;
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		   catch (RecognitionException re)
		   {
		      reportError(re);
		      recover(input, re);
		      _errors = true;
		   }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "read"


	public static class conditional_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "conditional"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:199:1: conditional : IF ^ LPAREN ! expression RPAREN ! block ( ELSE ! block )? ;
	public final MiniParser.conditional_return conditional() throws RecognitionException {
		MiniParser.conditional_return retval = new MiniParser.conditional_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token IF54=null;
		Token LPAREN55=null;
		Token RPAREN57=null;
		Token ELSE59=null;
		ParserRuleReturnScope expression56 =null;
		ParserRuleReturnScope block58 =null;
		ParserRuleReturnScope block60 =null;

		Object IF54_tree=null;
		Object LPAREN55_tree=null;
		Object RPAREN57_tree=null;
		Object ELSE59_tree=null;

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:200:4: ( IF ^ LPAREN ! expression RPAREN ! block ( ELSE ! block )? )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:200:7: IF ^ LPAREN ! expression RPAREN ! block ( ELSE ! block )?
			{
			root_0 = (Object)adaptor.nil();


			IF54=(Token)match(input,IF,FOLLOW_IF_in_conditional1808); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			IF54_tree = (Object)adaptor.create(IF54);
			root_0 = (Object)adaptor.becomeRoot(IF54_tree, root_0);
			}

			LPAREN55=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_conditional1811); if (state.failed) return retval;
			pushFollow(FOLLOW_expression_in_conditional1814);
			expression56=expression();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, expression56.getTree());

			RPAREN57=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_conditional1816); if (state.failed) return retval;
			pushFollow(FOLLOW_block_in_conditional1819);
			block58=block();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, block58.getTree());

			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:200:44: ( ELSE ! block )?
			int alt14=2;
			int LA14_0 = input.LA(1);
			if ( (LA14_0==ELSE) ) {
				alt14=1;
			}
			switch (alt14) {
				case 1 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:200:45: ELSE ! block
					{
					ELSE59=(Token)match(input,ELSE,FOLLOW_ELSE_in_conditional1822); if (state.failed) return retval;
					pushFollow(FOLLOW_block_in_conditional1825);
					block60=block();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, block60.getTree());

					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		   catch (RecognitionException re)
		   {
		      reportError(re);
		      recover(input, re);
		      _errors = true;
		   }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "conditional"


	public static class loop_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "loop"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:202:1: loop : WHILE LPAREN e= expression RPAREN b= block -> ^( WHILE $e $b $e) ;
	public final MiniParser.loop_return loop() throws RecognitionException {
		MiniParser.loop_return retval = new MiniParser.loop_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token WHILE61=null;
		Token LPAREN62=null;
		Token RPAREN63=null;
		ParserRuleReturnScope e =null;
		ParserRuleReturnScope b =null;

		Object WHILE61_tree=null;
		Object LPAREN62_tree=null;
		Object RPAREN63_tree=null;
		RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
		RewriteRuleTokenStream stream_WHILE=new RewriteRuleTokenStream(adaptor,"token WHILE");
		RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
		RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
		RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:203:4: ( WHILE LPAREN e= expression RPAREN b= block -> ^( WHILE $e $b $e) )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:203:7: WHILE LPAREN e= expression RPAREN b= block
			{
			WHILE61=(Token)match(input,WHILE,FOLLOW_WHILE_in_loop1842); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_WHILE.add(WHILE61);

			LPAREN62=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_loop1844); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN62);

			pushFollow(FOLLOW_expression_in_loop1848);
			e=expression();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_expression.add(e.getTree());
			RPAREN63=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_loop1850); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN63);

			pushFollow(FOLLOW_block_in_loop1854);
			b=block();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_block.add(b.getTree());
			// AST REWRITE
			// elements: b, e, e, WHILE
			// token labels: 
			// rule labels: retval, e, b
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
			RewriteRuleSubtreeStream stream_e=new RewriteRuleSubtreeStream(adaptor,"rule e",e!=null?e.getTree():null);
			RewriteRuleSubtreeStream stream_b=new RewriteRuleSubtreeStream(adaptor,"rule b",b!=null?b.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 204:7: -> ^( WHILE $e $b $e)
			{
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:204:10: ^( WHILE $e $b $e)
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot(stream_WHILE.nextNode(), root_1);
				adaptor.addChild(root_1, stream_e.nextTree());
				adaptor.addChild(root_1, stream_b.nextTree());
				adaptor.addChild(root_1, stream_e.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		   catch (RecognitionException re)
		   {
		      reportError(re);
		      recover(input, re);
		      _errors = true;
		   }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "loop"


	public static class delete_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "delete"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:206:1: delete : DELETE ^ expression SEMI !;
	public final MiniParser.delete_return delete() throws RecognitionException {
		MiniParser.delete_return retval = new MiniParser.delete_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token DELETE64=null;
		Token SEMI66=null;
		ParserRuleReturnScope expression65 =null;

		Object DELETE64_tree=null;
		Object SEMI66_tree=null;

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:207:4: ( DELETE ^ expression SEMI !)
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:207:7: DELETE ^ expression SEMI !
			{
			root_0 = (Object)adaptor.nil();


			DELETE64=(Token)match(input,DELETE,FOLLOW_DELETE_in_delete1890); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			DELETE64_tree = (Object)adaptor.create(DELETE64);
			root_0 = (Object)adaptor.becomeRoot(DELETE64_tree, root_0);
			}

			pushFollow(FOLLOW_expression_in_delete1893);
			expression65=expression();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, expression65.getTree());

			SEMI66=(Token)match(input,SEMI,FOLLOW_SEMI_in_delete1895); if (state.failed) return retval;
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		   catch (RecognitionException re)
		   {
		      reportError(re);
		      recover(input, re);
		      _errors = true;
		   }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "delete"


	public static class ret_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "ret"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:209:1: ret : RETURN ^ ( expression )? SEMI !;
	public final MiniParser.ret_return ret() throws RecognitionException {
		MiniParser.ret_return retval = new MiniParser.ret_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token RETURN67=null;
		Token SEMI69=null;
		ParserRuleReturnScope expression68 =null;

		Object RETURN67_tree=null;
		Object SEMI69_tree=null;

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:210:4: ( RETURN ^ ( expression )? SEMI !)
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:210:7: RETURN ^ ( expression )? SEMI !
			{
			root_0 = (Object)adaptor.nil();


			RETURN67=(Token)match(input,RETURN,FOLLOW_RETURN_in_ret1911); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			RETURN67_tree = (Object)adaptor.create(RETURN67);
			root_0 = (Object)adaptor.becomeRoot(RETURN67_tree, root_0);
			}

			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:210:15: ( expression )?
			int alt15=2;
			int LA15_0 = input.LA(1);
			if ( (LA15_0==FALSE||LA15_0==ID||LA15_0==INTEGER||LA15_0==LPAREN||LA15_0==MINUS||(LA15_0 >= NEW && LA15_0 <= NULL)||LA15_0==TRUE) ) {
				alt15=1;
			}
			switch (alt15) {
				case 1 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:210:16: expression
					{
					pushFollow(FOLLOW_expression_in_ret1915);
					expression68=expression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, expression68.getTree());

					}
					break;

			}

			SEMI69=(Token)match(input,SEMI,FOLLOW_SEMI_in_ret1919); if (state.failed) return retval;
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		   catch (RecognitionException re)
		   {
		      reportError(re);
		      recover(input, re);
		      _errors = true;
		   }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "ret"


	public static class invocation_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "invocation"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:212:1: invocation : id= ID a= arguments SEMI -> ^( INVOKE $id $a) ;
	public final MiniParser.invocation_return invocation() throws RecognitionException {
		MiniParser.invocation_return retval = new MiniParser.invocation_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token id=null;
		Token SEMI70=null;
		ParserRuleReturnScope a =null;

		Object id_tree=null;
		Object SEMI70_tree=null;
		RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
		RewriteRuleTokenStream stream_SEMI=new RewriteRuleTokenStream(adaptor,"token SEMI");
		RewriteRuleSubtreeStream stream_arguments=new RewriteRuleSubtreeStream(adaptor,"rule arguments");

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:213:4: (id= ID a= arguments SEMI -> ^( INVOKE $id $a) )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:213:7: id= ID a= arguments SEMI
			{
			id=(Token)match(input,ID,FOLLOW_ID_in_invocation1937); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_ID.add(id);

			pushFollow(FOLLOW_arguments_in_invocation1941);
			a=arguments();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_arguments.add(a.getTree());
			SEMI70=(Token)match(input,SEMI,FOLLOW_SEMI_in_invocation1943); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_SEMI.add(SEMI70);

			// AST REWRITE
			// elements: id, a
			// token labels: id
			// rule labels: retval, a
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleTokenStream stream_id=new RewriteRuleTokenStream(adaptor,"token id",id);
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
			RewriteRuleSubtreeStream stream_a=new RewriteRuleSubtreeStream(adaptor,"rule a",a!=null?a.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 214:7: -> ^( INVOKE $id $a)
			{
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:214:10: ^( INVOKE $id $a)
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(INVOKE, "INVOKE"), root_1);
				adaptor.addChild(root_1, stream_id.nextNode());
				adaptor.addChild(root_1, stream_a.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		   catch (RecognitionException re)
		   {
		      reportError(re);
		      recover(input, re);
		      _errors = true;
		   }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "invocation"


	public static class lvalue_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "lvalue"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:216:1: lvalue : ID ( DOT ^ ID )* ;
	public final MiniParser.lvalue_return lvalue() throws RecognitionException {
		MiniParser.lvalue_return retval = new MiniParser.lvalue_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token ID71=null;
		Token DOT72=null;
		Token ID73=null;

		Object ID71_tree=null;
		Object DOT72_tree=null;
		Object ID73_tree=null;

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:217:4: ( ID ( DOT ^ ID )* )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:217:7: ID ( DOT ^ ID )*
			{
			root_0 = (Object)adaptor.nil();


			ID71=(Token)match(input,ID,FOLLOW_ID_in_lvalue1976); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			ID71_tree = (Object)adaptor.create(ID71);
			adaptor.addChild(root_0, ID71_tree);
			}

			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:217:10: ( DOT ^ ID )*
			loop16:
			while (true) {
				int alt16=2;
				int LA16_0 = input.LA(1);
				if ( (LA16_0==DOT) ) {
					alt16=1;
				}

				switch (alt16) {
				case 1 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:217:11: DOT ^ ID
					{
					DOT72=(Token)match(input,DOT,FOLLOW_DOT_in_lvalue1979); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					DOT72_tree = (Object)adaptor.create(DOT72);
					root_0 = (Object)adaptor.becomeRoot(DOT72_tree, root_0);
					}

					ID73=(Token)match(input,ID,FOLLOW_ID_in_lvalue1982); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ID73_tree = (Object)adaptor.create(ID73);
					adaptor.addChild(root_0, ID73_tree);
					}

					}
					break;

				default :
					break loop16;
				}
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		   catch (RecognitionException re)
		   {
		      reportError(re);
		      recover(input, re);
		      _errors = true;
		   }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "lvalue"


	public static class expression_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "expression"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:219:1: expression : boolterm ( ( AND ^| OR ^) boolterm )* ;
	public final MiniParser.expression_return expression() throws RecognitionException {
		MiniParser.expression_return retval = new MiniParser.expression_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token AND75=null;
		Token OR76=null;
		ParserRuleReturnScope boolterm74 =null;
		ParserRuleReturnScope boolterm77 =null;

		Object AND75_tree=null;
		Object OR76_tree=null;

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:220:4: ( boolterm ( ( AND ^| OR ^) boolterm )* )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:220:7: boolterm ( ( AND ^| OR ^) boolterm )*
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_boolterm_in_expression1999);
			boolterm74=boolterm();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, boolterm74.getTree());

			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:220:16: ( ( AND ^| OR ^) boolterm )*
			loop18:
			while (true) {
				int alt18=2;
				int LA18_0 = input.LA(1);
				if ( (LA18_0==AND||LA18_0==OR) ) {
					alt18=1;
				}

				switch (alt18) {
				case 1 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:220:17: ( AND ^| OR ^) boolterm
					{
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:220:17: ( AND ^| OR ^)
					int alt17=2;
					int LA17_0 = input.LA(1);
					if ( (LA17_0==AND) ) {
						alt17=1;
					}
					else if ( (LA17_0==OR) ) {
						alt17=2;
					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						NoViableAltException nvae =
							new NoViableAltException("", 17, 0, input);
						throw nvae;
					}

					switch (alt17) {
						case 1 :
							// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:220:18: AND ^
							{
							AND75=(Token)match(input,AND,FOLLOW_AND_in_expression2003); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							AND75_tree = (Object)adaptor.create(AND75);
							root_0 = (Object)adaptor.becomeRoot(AND75_tree, root_0);
							}

							}
							break;
						case 2 :
							// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:220:25: OR ^
							{
							OR76=(Token)match(input,OR,FOLLOW_OR_in_expression2008); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							OR76_tree = (Object)adaptor.create(OR76);
							root_0 = (Object)adaptor.becomeRoot(OR76_tree, root_0);
							}

							}
							break;

					}

					pushFollow(FOLLOW_boolterm_in_expression2012);
					boolterm77=boolterm();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, boolterm77.getTree());

					}
					break;

				default :
					break loop18;
				}
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		   catch (RecognitionException re)
		   {
		      reportError(re);
		      recover(input, re);
		      _errors = true;
		   }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "expression"


	public static class boolterm_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "boolterm"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:222:1: boolterm : simple ( ( EQ ^| LT ^| GT ^| NE ^| LE ^| GE ^) simple )? ;
	public final MiniParser.boolterm_return boolterm() throws RecognitionException {
		MiniParser.boolterm_return retval = new MiniParser.boolterm_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token EQ79=null;
		Token LT80=null;
		Token GT81=null;
		Token NE82=null;
		Token LE83=null;
		Token GE84=null;
		ParserRuleReturnScope simple78 =null;
		ParserRuleReturnScope simple85 =null;

		Object EQ79_tree=null;
		Object LT80_tree=null;
		Object GT81_tree=null;
		Object NE82_tree=null;
		Object LE83_tree=null;
		Object GE84_tree=null;

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:223:4: ( simple ( ( EQ ^| LT ^| GT ^| NE ^| LE ^| GE ^) simple )? )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:223:7: simple ( ( EQ ^| LT ^| GT ^| NE ^| LE ^| GE ^) simple )?
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_simple_in_boolterm2029);
			simple78=simple();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, simple78.getTree());

			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:223:14: ( ( EQ ^| LT ^| GT ^| NE ^| LE ^| GE ^) simple )?
			int alt20=2;
			int LA20_0 = input.LA(1);
			if ( (LA20_0==EQ||(LA20_0 >= GE && LA20_0 <= GT)||LA20_0==LE||LA20_0==LT||LA20_0==NE) ) {
				alt20=1;
			}
			switch (alt20) {
				case 1 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:223:15: ( EQ ^| LT ^| GT ^| NE ^| LE ^| GE ^) simple
					{
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:223:15: ( EQ ^| LT ^| GT ^| NE ^| LE ^| GE ^)
					int alt19=6;
					switch ( input.LA(1) ) {
					case EQ:
						{
						alt19=1;
						}
						break;
					case LT:
						{
						alt19=2;
						}
						break;
					case GT:
						{
						alt19=3;
						}
						break;
					case NE:
						{
						alt19=4;
						}
						break;
					case LE:
						{
						alt19=5;
						}
						break;
					case GE:
						{
						alt19=6;
						}
						break;
					default:
						if (state.backtracking>0) {state.failed=true; return retval;}
						NoViableAltException nvae =
							new NoViableAltException("", 19, 0, input);
						throw nvae;
					}
					switch (alt19) {
						case 1 :
							// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:223:16: EQ ^
							{
							EQ79=(Token)match(input,EQ,FOLLOW_EQ_in_boolterm2033); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							EQ79_tree = (Object)adaptor.create(EQ79);
							root_0 = (Object)adaptor.becomeRoot(EQ79_tree, root_0);
							}

							}
							break;
						case 2 :
							// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:223:22: LT ^
							{
							LT80=(Token)match(input,LT,FOLLOW_LT_in_boolterm2038); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							LT80_tree = (Object)adaptor.create(LT80);
							root_0 = (Object)adaptor.becomeRoot(LT80_tree, root_0);
							}

							}
							break;
						case 3 :
							// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:223:28: GT ^
							{
							GT81=(Token)match(input,GT,FOLLOW_GT_in_boolterm2043); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							GT81_tree = (Object)adaptor.create(GT81);
							root_0 = (Object)adaptor.becomeRoot(GT81_tree, root_0);
							}

							}
							break;
						case 4 :
							// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:223:34: NE ^
							{
							NE82=(Token)match(input,NE,FOLLOW_NE_in_boolterm2048); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							NE82_tree = (Object)adaptor.create(NE82);
							root_0 = (Object)adaptor.becomeRoot(NE82_tree, root_0);
							}

							}
							break;
						case 5 :
							// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:223:40: LE ^
							{
							LE83=(Token)match(input,LE,FOLLOW_LE_in_boolterm2053); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							LE83_tree = (Object)adaptor.create(LE83);
							root_0 = (Object)adaptor.becomeRoot(LE83_tree, root_0);
							}

							}
							break;
						case 6 :
							// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:223:46: GE ^
							{
							GE84=(Token)match(input,GE,FOLLOW_GE_in_boolterm2058); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							GE84_tree = (Object)adaptor.create(GE84);
							root_0 = (Object)adaptor.becomeRoot(GE84_tree, root_0);
							}

							}
							break;

					}

					pushFollow(FOLLOW_simple_in_boolterm2062);
					simple85=simple();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, simple85.getTree());

					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		   catch (RecognitionException re)
		   {
		      reportError(re);
		      recover(input, re);
		      _errors = true;
		   }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "boolterm"


	public static class simple_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "simple"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:225:1: simple : term ( ( PLUS ^| MINUS ^) term )* ;
	public final MiniParser.simple_return simple() throws RecognitionException {
		MiniParser.simple_return retval = new MiniParser.simple_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token PLUS87=null;
		Token MINUS88=null;
		ParserRuleReturnScope term86 =null;
		ParserRuleReturnScope term89 =null;

		Object PLUS87_tree=null;
		Object MINUS88_tree=null;

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:226:4: ( term ( ( PLUS ^| MINUS ^) term )* )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:226:7: term ( ( PLUS ^| MINUS ^) term )*
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_term_in_simple2079);
			term86=term();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, term86.getTree());

			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:226:12: ( ( PLUS ^| MINUS ^) term )*
			loop22:
			while (true) {
				int alt22=2;
				int LA22_0 = input.LA(1);
				if ( (LA22_0==MINUS||LA22_0==PLUS) ) {
					alt22=1;
				}

				switch (alt22) {
				case 1 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:226:13: ( PLUS ^| MINUS ^) term
					{
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:226:13: ( PLUS ^| MINUS ^)
					int alt21=2;
					int LA21_0 = input.LA(1);
					if ( (LA21_0==PLUS) ) {
						alt21=1;
					}
					else if ( (LA21_0==MINUS) ) {
						alt21=2;
					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						NoViableAltException nvae =
							new NoViableAltException("", 21, 0, input);
						throw nvae;
					}

					switch (alt21) {
						case 1 :
							// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:226:14: PLUS ^
							{
							PLUS87=(Token)match(input,PLUS,FOLLOW_PLUS_in_simple2083); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							PLUS87_tree = (Object)adaptor.create(PLUS87);
							root_0 = (Object)adaptor.becomeRoot(PLUS87_tree, root_0);
							}

							}
							break;
						case 2 :
							// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:226:22: MINUS ^
							{
							MINUS88=(Token)match(input,MINUS,FOLLOW_MINUS_in_simple2088); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							MINUS88_tree = (Object)adaptor.create(MINUS88);
							root_0 = (Object)adaptor.becomeRoot(MINUS88_tree, root_0);
							}

							}
							break;

					}

					pushFollow(FOLLOW_term_in_simple2092);
					term89=term();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, term89.getTree());

					}
					break;

				default :
					break loop22;
				}
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		   catch (RecognitionException re)
		   {
		      reportError(re);
		      recover(input, re);
		      _errors = true;
		   }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "simple"


	public static class term_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "term"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:228:1: term : unary ( ( TIMES ^| DIVIDE ^) unary )* ;
	public final MiniParser.term_return term() throws RecognitionException {
		MiniParser.term_return retval = new MiniParser.term_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token TIMES91=null;
		Token DIVIDE92=null;
		ParserRuleReturnScope unary90 =null;
		ParserRuleReturnScope unary93 =null;

		Object TIMES91_tree=null;
		Object DIVIDE92_tree=null;

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:229:4: ( unary ( ( TIMES ^| DIVIDE ^) unary )* )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:229:7: unary ( ( TIMES ^| DIVIDE ^) unary )*
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_unary_in_term2109);
			unary90=unary();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, unary90.getTree());

			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:229:13: ( ( TIMES ^| DIVIDE ^) unary )*
			loop24:
			while (true) {
				int alt24=2;
				int LA24_0 = input.LA(1);
				if ( (LA24_0==DIVIDE||LA24_0==TIMES) ) {
					alt24=1;
				}

				switch (alt24) {
				case 1 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:229:14: ( TIMES ^| DIVIDE ^) unary
					{
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:229:14: ( TIMES ^| DIVIDE ^)
					int alt23=2;
					int LA23_0 = input.LA(1);
					if ( (LA23_0==TIMES) ) {
						alt23=1;
					}
					else if ( (LA23_0==DIVIDE) ) {
						alt23=2;
					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						NoViableAltException nvae =
							new NoViableAltException("", 23, 0, input);
						throw nvae;
					}

					switch (alt23) {
						case 1 :
							// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:229:15: TIMES ^
							{
							TIMES91=(Token)match(input,TIMES,FOLLOW_TIMES_in_term2113); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							TIMES91_tree = (Object)adaptor.create(TIMES91);
							root_0 = (Object)adaptor.becomeRoot(TIMES91_tree, root_0);
							}

							}
							break;
						case 2 :
							// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:229:24: DIVIDE ^
							{
							DIVIDE92=(Token)match(input,DIVIDE,FOLLOW_DIVIDE_in_term2118); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							DIVIDE92_tree = (Object)adaptor.create(DIVIDE92);
							root_0 = (Object)adaptor.becomeRoot(DIVIDE92_tree, root_0);
							}

							}
							break;

					}

					pushFollow(FOLLOW_unary_in_term2122);
					unary93=unary();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, unary93.getTree());

					}
					break;

				default :
					break loop24;
				}
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		   catch (RecognitionException re)
		   {
		      reportError(re);
		      recover(input, re);
		      _errors = true;
		   }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "term"


	public static class unary_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "unary"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:231:1: unary : ( NOT ! odd_not | MINUS ! odd_neg | selector );
	public final MiniParser.unary_return unary() throws RecognitionException {
		MiniParser.unary_return retval = new MiniParser.unary_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token NOT94=null;
		Token MINUS96=null;
		ParserRuleReturnScope odd_not95 =null;
		ParserRuleReturnScope odd_neg97 =null;
		ParserRuleReturnScope selector98 =null;

		Object NOT94_tree=null;
		Object MINUS96_tree=null;

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:232:4: ( NOT ! odd_not | MINUS ! odd_neg | selector )
			int alt25=3;
			switch ( input.LA(1) ) {
			case NOT:
				{
				alt25=1;
				}
				break;
			case MINUS:
				{
				alt25=2;
				}
				break;
			case FALSE:
			case ID:
			case INTEGER:
			case LPAREN:
			case NEW:
			case NULL:
			case TRUE:
				{
				alt25=3;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 25, 0, input);
				throw nvae;
			}
			switch (alt25) {
				case 1 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:232:7: NOT ! odd_not
					{
					root_0 = (Object)adaptor.nil();


					NOT94=(Token)match(input,NOT,FOLLOW_NOT_in_unary2139); if (state.failed) return retval;
					pushFollow(FOLLOW_odd_not_in_unary2142);
					odd_not95=odd_not();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, odd_not95.getTree());

					}
					break;
				case 2 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:233:7: MINUS ! odd_neg
					{
					root_0 = (Object)adaptor.nil();


					MINUS96=(Token)match(input,MINUS,FOLLOW_MINUS_in_unary2150); if (state.failed) return retval;
					pushFollow(FOLLOW_odd_neg_in_unary2153);
					odd_neg97=odd_neg();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, odd_neg97.getTree());

					}
					break;
				case 3 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:234:7: selector
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_selector_in_unary2161);
					selector98=selector();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, selector98.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		   catch (RecognitionException re)
		   {
		      reportError(re);
		      recover(input, re);
		      _errors = true;
		   }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "unary"


	public static class odd_not_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "odd_not"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:236:1: odd_not : ( NOT ! even_not |s= selector -> ^( NOT $s) );
	public final MiniParser.odd_not_return odd_not() throws RecognitionException {
		MiniParser.odd_not_return retval = new MiniParser.odd_not_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token NOT99=null;
		ParserRuleReturnScope s =null;
		ParserRuleReturnScope even_not100 =null;

		Object NOT99_tree=null;
		RewriteRuleSubtreeStream stream_selector=new RewriteRuleSubtreeStream(adaptor,"rule selector");

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:237:4: ( NOT ! even_not |s= selector -> ^( NOT $s) )
			int alt26=2;
			int LA26_0 = input.LA(1);
			if ( (LA26_0==NOT) ) {
				alt26=1;
			}
			else if ( (LA26_0==FALSE||LA26_0==ID||LA26_0==INTEGER||LA26_0==LPAREN||LA26_0==NEW||LA26_0==NULL||LA26_0==TRUE) ) {
				alt26=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 26, 0, input);
				throw nvae;
			}

			switch (alt26) {
				case 1 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:237:7: NOT ! even_not
					{
					root_0 = (Object)adaptor.nil();


					NOT99=(Token)match(input,NOT,FOLLOW_NOT_in_odd_not2176); if (state.failed) return retval;
					pushFollow(FOLLOW_even_not_in_odd_not2179);
					even_not100=even_not();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, even_not100.getTree());

					}
					break;
				case 2 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:238:7: s= selector
					{
					pushFollow(FOLLOW_selector_in_odd_not2189);
					s=selector();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_selector.add(s.getTree());
					// AST REWRITE
					// elements: s
					// token labels: 
					// rule labels: retval, s
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
					RewriteRuleSubtreeStream stream_s=new RewriteRuleSubtreeStream(adaptor,"rule s",s!=null?s.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 239:7: -> ^( NOT $s)
					{
						// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:239:10: ^( NOT $s)
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(NOT, "NOT"), root_1);
						adaptor.addChild(root_1, stream_s.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		   catch (RecognitionException re)
		   {
		      reportError(re);
		      recover(input, re);
		      _errors = true;
		   }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "odd_not"


	public static class even_not_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "even_not"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:241:1: even_not : ( NOT ! odd_not | selector );
	public final MiniParser.even_not_return even_not() throws RecognitionException {
		MiniParser.even_not_return retval = new MiniParser.even_not_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token NOT101=null;
		ParserRuleReturnScope odd_not102 =null;
		ParserRuleReturnScope selector103 =null;

		Object NOT101_tree=null;

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:242:4: ( NOT ! odd_not | selector )
			int alt27=2;
			int LA27_0 = input.LA(1);
			if ( (LA27_0==NOT) ) {
				alt27=1;
			}
			else if ( (LA27_0==FALSE||LA27_0==ID||LA27_0==INTEGER||LA27_0==LPAREN||LA27_0==NEW||LA27_0==NULL||LA27_0==TRUE) ) {
				alt27=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 27, 0, input);
				throw nvae;
			}

			switch (alt27) {
				case 1 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:242:7: NOT ! odd_not
					{
					root_0 = (Object)adaptor.nil();


					NOT101=(Token)match(input,NOT,FOLLOW_NOT_in_even_not2219); if (state.failed) return retval;
					pushFollow(FOLLOW_odd_not_in_even_not2222);
					odd_not102=odd_not();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, odd_not102.getTree());

					}
					break;
				case 2 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:243:7: selector
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_selector_in_even_not2230);
					selector103=selector();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, selector103.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		   catch (RecognitionException re)
		   {
		      reportError(re);
		      recover(input, re);
		      _errors = true;
		   }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "even_not"


	public static class odd_neg_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "odd_neg"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:245:1: odd_neg : ( MINUS ! even_neg |s= selector -> ^( NEG $s) );
	public final MiniParser.odd_neg_return odd_neg() throws RecognitionException {
		MiniParser.odd_neg_return retval = new MiniParser.odd_neg_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token MINUS104=null;
		ParserRuleReturnScope s =null;
		ParserRuleReturnScope even_neg105 =null;

		Object MINUS104_tree=null;
		RewriteRuleSubtreeStream stream_selector=new RewriteRuleSubtreeStream(adaptor,"rule selector");

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:246:4: ( MINUS ! even_neg |s= selector -> ^( NEG $s) )
			int alt28=2;
			int LA28_0 = input.LA(1);
			if ( (LA28_0==MINUS) ) {
				alt28=1;
			}
			else if ( (LA28_0==FALSE||LA28_0==ID||LA28_0==INTEGER||LA28_0==LPAREN||LA28_0==NEW||LA28_0==NULL||LA28_0==TRUE) ) {
				alt28=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 28, 0, input);
				throw nvae;
			}

			switch (alt28) {
				case 1 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:246:7: MINUS ! even_neg
					{
					root_0 = (Object)adaptor.nil();


					MINUS104=(Token)match(input,MINUS,FOLLOW_MINUS_in_odd_neg2245); if (state.failed) return retval;
					pushFollow(FOLLOW_even_neg_in_odd_neg2248);
					even_neg105=even_neg();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, even_neg105.getTree());

					}
					break;
				case 2 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:247:7: s= selector
					{
					pushFollow(FOLLOW_selector_in_odd_neg2258);
					s=selector();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_selector.add(s.getTree());
					// AST REWRITE
					// elements: s
					// token labels: 
					// rule labels: retval, s
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
					RewriteRuleSubtreeStream stream_s=new RewriteRuleSubtreeStream(adaptor,"rule s",s!=null?s.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 248:7: -> ^( NEG $s)
					{
						// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:248:10: ^( NEG $s)
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(NEG, "NEG"), root_1);
						adaptor.addChild(root_1, stream_s.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		   catch (RecognitionException re)
		   {
		      reportError(re);
		      recover(input, re);
		      _errors = true;
		   }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "odd_neg"


	public static class even_neg_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "even_neg"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:250:1: even_neg : ( MINUS ! odd_neg | selector );
	public final MiniParser.even_neg_return even_neg() throws RecognitionException {
		MiniParser.even_neg_return retval = new MiniParser.even_neg_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token MINUS106=null;
		ParserRuleReturnScope odd_neg107 =null;
		ParserRuleReturnScope selector108 =null;

		Object MINUS106_tree=null;

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:251:4: ( MINUS ! odd_neg | selector )
			int alt29=2;
			int LA29_0 = input.LA(1);
			if ( (LA29_0==MINUS) ) {
				alt29=1;
			}
			else if ( (LA29_0==FALSE||LA29_0==ID||LA29_0==INTEGER||LA29_0==LPAREN||LA29_0==NEW||LA29_0==NULL||LA29_0==TRUE) ) {
				alt29=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 29, 0, input);
				throw nvae;
			}

			switch (alt29) {
				case 1 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:251:7: MINUS ! odd_neg
					{
					root_0 = (Object)adaptor.nil();


					MINUS106=(Token)match(input,MINUS,FOLLOW_MINUS_in_even_neg2288); if (state.failed) return retval;
					pushFollow(FOLLOW_odd_neg_in_even_neg2291);
					odd_neg107=odd_neg();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, odd_neg107.getTree());

					}
					break;
				case 2 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:252:7: selector
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_selector_in_even_neg2299);
					selector108=selector();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, selector108.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		   catch (RecognitionException re)
		   {
		      reportError(re);
		      recover(input, re);
		      _errors = true;
		   }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "even_neg"


	public static class selector_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "selector"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:254:1: selector : factor ( DOT ^ ID )* ;
	public final MiniParser.selector_return selector() throws RecognitionException {
		MiniParser.selector_return retval = new MiniParser.selector_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token DOT110=null;
		Token ID111=null;
		ParserRuleReturnScope factor109 =null;

		Object DOT110_tree=null;
		Object ID111_tree=null;

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:255:4: ( factor ( DOT ^ ID )* )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:255:7: factor ( DOT ^ ID )*
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_factor_in_selector2314);
			factor109=factor();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, factor109.getTree());

			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:255:14: ( DOT ^ ID )*
			loop30:
			while (true) {
				int alt30=2;
				int LA30_0 = input.LA(1);
				if ( (LA30_0==DOT) ) {
					alt30=1;
				}

				switch (alt30) {
				case 1 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:255:15: DOT ^ ID
					{
					DOT110=(Token)match(input,DOT,FOLLOW_DOT_in_selector2317); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					DOT110_tree = (Object)adaptor.create(DOT110);
					root_0 = (Object)adaptor.becomeRoot(DOT110_tree, root_0);
					}

					ID111=(Token)match(input,ID,FOLLOW_ID_in_selector2320); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ID111_tree = (Object)adaptor.create(ID111);
					adaptor.addChild(root_0, ID111_tree);
					}

					}
					break;

				default :
					break loop30;
				}
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		   catch (RecognitionException re)
		   {
		      reportError(re);
		      recover(input, re);
		      _errors = true;
		   }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "selector"


	public static class factor_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "factor"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:257:1: factor : ( LPAREN ! expression RPAREN !|id= ID a= arguments -> ^( INVOKE $id $a) | ID | INTEGER | TRUE | FALSE | NEW ^ ID | NULL );
	public final MiniParser.factor_return factor() throws RecognitionException {
		MiniParser.factor_return retval = new MiniParser.factor_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token id=null;
		Token LPAREN112=null;
		Token RPAREN114=null;
		Token ID115=null;
		Token INTEGER116=null;
		Token TRUE117=null;
		Token FALSE118=null;
		Token NEW119=null;
		Token ID120=null;
		Token NULL121=null;
		ParserRuleReturnScope a =null;
		ParserRuleReturnScope expression113 =null;

		Object id_tree=null;
		Object LPAREN112_tree=null;
		Object RPAREN114_tree=null;
		Object ID115_tree=null;
		Object INTEGER116_tree=null;
		Object TRUE117_tree=null;
		Object FALSE118_tree=null;
		Object NEW119_tree=null;
		Object ID120_tree=null;
		Object NULL121_tree=null;
		RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
		RewriteRuleSubtreeStream stream_arguments=new RewriteRuleSubtreeStream(adaptor,"rule arguments");

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:258:4: ( LPAREN ! expression RPAREN !|id= ID a= arguments -> ^( INVOKE $id $a) | ID | INTEGER | TRUE | FALSE | NEW ^ ID | NULL )
			int alt31=8;
			switch ( input.LA(1) ) {
			case LPAREN:
				{
				alt31=1;
				}
				break;
			case ID:
				{
				int LA31_2 = input.LA(2);
				if ( (LA31_2==LPAREN) ) {
					alt31=2;
				}
				else if ( (LA31_2==AND||LA31_2==COMMA||(LA31_2 >= DIVIDE && LA31_2 <= DOT)||(LA31_2 >= ENDL && LA31_2 <= EQ)||(LA31_2 >= GE && LA31_2 <= GT)||LA31_2==LE||(LA31_2 >= LT && LA31_2 <= NE)||LA31_2==OR||LA31_2==PLUS||(LA31_2 >= RPAREN && LA31_2 <= SEMI)||LA31_2==TIMES) ) {
					alt31=3;
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 31, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case INTEGER:
				{
				alt31=4;
				}
				break;
			case TRUE:
				{
				alt31=5;
				}
				break;
			case FALSE:
				{
				alt31=6;
				}
				break;
			case NEW:
				{
				alt31=7;
				}
				break;
			case NULL:
				{
				alt31=8;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 31, 0, input);
				throw nvae;
			}
			switch (alt31) {
				case 1 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:258:7: LPAREN ! expression RPAREN !
					{
					root_0 = (Object)adaptor.nil();


					LPAREN112=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_factor2337); if (state.failed) return retval;
					pushFollow(FOLLOW_expression_in_factor2340);
					expression113=expression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, expression113.getTree());

					RPAREN114=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_factor2342); if (state.failed) return retval;
					}
					break;
				case 2 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:259:7: id= ID a= arguments
					{
					id=(Token)match(input,ID,FOLLOW_ID_in_factor2353); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_ID.add(id);

					pushFollow(FOLLOW_arguments_in_factor2357);
					a=arguments();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_arguments.add(a.getTree());
					// AST REWRITE
					// elements: id, a
					// token labels: id
					// rule labels: retval, a
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleTokenStream stream_id=new RewriteRuleTokenStream(adaptor,"token id",id);
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
					RewriteRuleSubtreeStream stream_a=new RewriteRuleSubtreeStream(adaptor,"rule a",a!=null?a.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 260:7: -> ^( INVOKE $id $a)
					{
						// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:260:10: ^( INVOKE $id $a)
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(INVOKE, "INVOKE"), root_1);
						adaptor.addChild(root_1, stream_id.nextNode());
						adaptor.addChild(root_1, stream_a.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 3 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:261:7: ID
					{
					root_0 = (Object)adaptor.nil();


					ID115=(Token)match(input,ID,FOLLOW_ID_in_factor2383); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ID115_tree = (Object)adaptor.create(ID115);
					adaptor.addChild(root_0, ID115_tree);
					}

					}
					break;
				case 4 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:262:7: INTEGER
					{
					root_0 = (Object)adaptor.nil();


					INTEGER116=(Token)match(input,INTEGER,FOLLOW_INTEGER_in_factor2391); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					INTEGER116_tree = (Object)adaptor.create(INTEGER116);
					adaptor.addChild(root_0, INTEGER116_tree);
					}

					}
					break;
				case 5 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:263:7: TRUE
					{
					root_0 = (Object)adaptor.nil();


					TRUE117=(Token)match(input,TRUE,FOLLOW_TRUE_in_factor2399); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					TRUE117_tree = (Object)adaptor.create(TRUE117);
					adaptor.addChild(root_0, TRUE117_tree);
					}

					}
					break;
				case 6 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:264:7: FALSE
					{
					root_0 = (Object)adaptor.nil();


					FALSE118=(Token)match(input,FALSE,FOLLOW_FALSE_in_factor2407); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					FALSE118_tree = (Object)adaptor.create(FALSE118);
					adaptor.addChild(root_0, FALSE118_tree);
					}

					}
					break;
				case 7 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:265:7: NEW ^ ID
					{
					root_0 = (Object)adaptor.nil();


					NEW119=(Token)match(input,NEW,FOLLOW_NEW_in_factor2415); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					NEW119_tree = (Object)adaptor.create(NEW119);
					root_0 = (Object)adaptor.becomeRoot(NEW119_tree, root_0);
					}

					ID120=(Token)match(input,ID,FOLLOW_ID_in_factor2418); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ID120_tree = (Object)adaptor.create(ID120);
					adaptor.addChild(root_0, ID120_tree);
					}

					}
					break;
				case 8 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:266:7: NULL
					{
					root_0 = (Object)adaptor.nil();


					NULL121=(Token)match(input,NULL,FOLLOW_NULL_in_factor2426); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					NULL121_tree = (Object)adaptor.create(NULL121);
					adaptor.addChild(root_0, NULL121_tree);
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		   catch (RecognitionException re)
		   {
		      reportError(re);
		      recover(input, re);
		      _errors = true;
		   }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "factor"


	public static class arguments_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "arguments"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:268:1: arguments : LPAREN ! arg_list RPAREN !;
	public final MiniParser.arguments_return arguments() throws RecognitionException {
		MiniParser.arguments_return retval = new MiniParser.arguments_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token LPAREN122=null;
		Token RPAREN124=null;
		ParserRuleReturnScope arg_list123 =null;

		Object LPAREN122_tree=null;
		Object RPAREN124_tree=null;

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:269:4: ( LPAREN ! arg_list RPAREN !)
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:269:7: LPAREN ! arg_list RPAREN !
			{
			root_0 = (Object)adaptor.nil();


			LPAREN122=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_arguments2441); if (state.failed) return retval;
			pushFollow(FOLLOW_arg_list_in_arguments2444);
			arg_list123=arg_list();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, arg_list123.getTree());

			RPAREN124=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_arguments2446); if (state.failed) return retval;
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		   catch (RecognitionException re)
		   {
		      reportError(re);
		      recover(input, re);
		      _errors = true;
		   }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "arguments"


	public static class arg_list_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "arg_list"
	// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:271:1: arg_list : ( expression ( COMMA expression )* -> ^( ARGS ( expression )+ ) | -> ARGS );
	public final MiniParser.arg_list_return arg_list() throws RecognitionException {
		MiniParser.arg_list_return retval = new MiniParser.arg_list_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token COMMA126=null;
		ParserRuleReturnScope expression125 =null;
		ParserRuleReturnScope expression127 =null;

		Object COMMA126_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");

		try {
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:272:4: ( expression ( COMMA expression )* -> ^( ARGS ( expression )+ ) | -> ARGS )
			int alt33=2;
			int LA33_0 = input.LA(1);
			if ( (LA33_0==FALSE||LA33_0==ID||LA33_0==INTEGER||LA33_0==LPAREN||LA33_0==MINUS||(LA33_0 >= NEW && LA33_0 <= NULL)||LA33_0==TRUE) ) {
				alt33=1;
			}
			else if ( (LA33_0==RPAREN) ) {
				alt33=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 33, 0, input);
				throw nvae;
			}

			switch (alt33) {
				case 1 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:272:7: expression ( COMMA expression )*
					{
					pushFollow(FOLLOW_expression_in_arg_list2462);
					expression125=expression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_expression.add(expression125.getTree());
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:272:18: ( COMMA expression )*
					loop32:
					while (true) {
						int alt32=2;
						int LA32_0 = input.LA(1);
						if ( (LA32_0==COMMA) ) {
							alt32=1;
						}

						switch (alt32) {
						case 1 :
							// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:272:19: COMMA expression
							{
							COMMA126=(Token)match(input,COMMA,FOLLOW_COMMA_in_arg_list2465); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_COMMA.add(COMMA126);

							pushFollow(FOLLOW_expression_in_arg_list2467);
							expression127=expression();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_expression.add(expression127.getTree());
							}
							break;

						default :
							break loop32;
						}
					}

					// AST REWRITE
					// elements: expression
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 273:7: -> ^( ARGS ( expression )+ )
					{
						// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:273:10: ^( ARGS ( expression )+ )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(ARGS, "ARGS"), root_1);
						if ( !(stream_expression.hasNext()) ) {
							throw new RewriteEarlyExitException();
						}
						while ( stream_expression.hasNext() ) {
							adaptor.addChild(root_1, stream_expression.nextTree());
						}
						stream_expression.reset();

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 2 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:275:7: 
					{
					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 275:7: -> ARGS
					{
						adaptor.addChild(root_0, (Object)adaptor.create(ARGS, "ARGS"));
					}


					retval.tree = root_0;
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		   catch (RecognitionException re)
		   {
		      reportError(re);
		      recover(input, re);
		      _errors = true;
		   }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "arg_list"

	// $ANTLR start synpred1_Mini
	public final void synpred1_Mini_fragment() throws RecognitionException {
		// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:124:7: ( STRUCT ID LBRACE )
		// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:124:8: STRUCT ID LBRACE
		{
		match(input,STRUCT,FOLLOW_STRUCT_in_synpred1_Mini1102); if (state.failed) return;

		match(input,ID,FOLLOW_ID_in_synpred1_Mini1104); if (state.failed) return;

		match(input,LBRACE,FOLLOW_LBRACE_in_synpred1_Mini1106); if (state.failed) return;

		}

	}
	// $ANTLR end synpred1_Mini

	// $ANTLR start synpred2_Mini
	public final void synpred2_Mini_fragment() throws RecognitionException {
		// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:128:7: ( STRUCT ID LBRACE )
		// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:128:8: STRUCT ID LBRACE
		{
		match(input,STRUCT,FOLLOW_STRUCT_in_synpred2_Mini1145); if (state.failed) return;

		match(input,ID,FOLLOW_ID_in_synpred2_Mini1147); if (state.failed) return;

		match(input,LBRACE,FOLLOW_LBRACE_in_synpred2_Mini1149); if (state.failed) return;

		}

	}
	// $ANTLR end synpred2_Mini

	// $ANTLR start synpred3_Mini
	public final void synpred3_Mini_fragment() throws RecognitionException {
		// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:173:7: ( lvalue ASSIGN )
		// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:173:8: lvalue ASSIGN
		{
		pushFollow(FOLLOW_lvalue_in_synpred3_Mini1569);
		lvalue();
		state._fsp--;
		if (state.failed) return;

		match(input,ASSIGN,FOLLOW_ASSIGN_in_synpred3_Mini1571); if (state.failed) return;

		}

	}
	// $ANTLR end synpred3_Mini

	// $ANTLR start synpred4_Mini
	public final void synpred4_Mini_fragment() throws RecognitionException {
		// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:180:7: ( ID LPAREN )
		// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:180:8: ID LPAREN
		{
		match(input,ID,FOLLOW_ID_in_synpred4_Mini1633); if (state.failed) return;

		match(input,LPAREN,FOLLOW_LPAREN_in_synpred4_Mini1635); if (state.failed) return;

		}

	}
	// $ANTLR end synpred4_Mini

	// Delegated rules

	public final boolean synpred2_Mini() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred2_Mini_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred1_Mini() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred1_Mini_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred3_Mini() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred3_Mini_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred4_Mini() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred4_Mini_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}



	public static final BitSet FOLLOW_types_in_program1055 = new BitSet(new long[]{0x0010000008200100L});
	public static final BitSet FOLLOW_declarations_in_program1059 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_functions_in_program1063 = new BitSet(new long[]{0x0000000000000000L});
	public static final BitSet FOLLOW_EOF_in_program1065 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_types_sub_in_types1111 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_type_declaration_in_types_sub1154 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_types_sub_in_types_sub1156 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_STRUCT_in_type_declaration1176 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_ID_in_type_declaration1179 = new BitSet(new long[]{0x0000000040000000L});
	public static final BitSet FOLLOW_LBRACE_in_type_declaration1181 = new BitSet(new long[]{0x0010000008000100L});
	public static final BitSet FOLLOW_nested_decl_in_type_declaration1184 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_RBRACE_in_type_declaration1186 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_SEMI_in_type_declaration1189 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_decl_in_nested_decl1206 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_SEMI_in_nested_decl1208 = new BitSet(new long[]{0x0010000008000102L});
	public static final BitSet FOLLOW_type_in_decl1228 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_ID_in_decl1232 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INT_in_type1269 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_BOOL_in_type1277 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_STRUCT_in_type1285 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_ID_in_type1288 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_declaration_in_declarations1304 = new BitSet(new long[]{0x0010000008000102L});
	public static final BitSet FOLLOW_type_in_declaration1332 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_id_list_in_declaration1336 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_SEMI_in_declaration1338 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_id_list1375 = new BitSet(new long[]{0x0000000000000202L});
	public static final BitSet FOLLOW_COMMA_in_id_list1378 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_ID_in_id_list1381 = new BitSet(new long[]{0x0000000000000202L});
	public static final BitSet FOLLOW_function_in_functions1398 = new BitSet(new long[]{0x0000000000200002L});
	public static final BitSet FOLLOW_FUN_in_function1423 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_ID_in_function1427 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_parameters_in_function1431 = new BitSet(new long[]{0x0210000008000100L});
	public static final BitSet FOLLOW_return_type_in_function1435 = new BitSet(new long[]{0x0000000040000000L});
	public static final BitSet FOLLOW_LBRACE_in_function1437 = new BitSet(new long[]{0x041168004E004100L});
	public static final BitSet FOLLOW_declarations_in_function1441 = new BitSet(new long[]{0x0401680046004000L});
	public static final BitSet FOLLOW_statement_list_in_function1445 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_RBRACE_in_function1447 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LPAREN_in_parameters1493 = new BitSet(new long[]{0x0012000008000100L});
	public static final BitSet FOLLOW_decl_in_parameters1496 = new BitSet(new long[]{0x0002000000000200L});
	public static final BitSet FOLLOW_COMMA_in_parameters1499 = new BitSet(new long[]{0x0010000008000100L});
	public static final BitSet FOLLOW_decl_in_parameters1501 = new BitSet(new long[]{0x0002000000000200L});
	public static final BitSet FOLLOW_RPAREN_in_parameters1507 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_type_in_return_type1537 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_VOID_in_return_type1545 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_block_in_statement1560 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_assignment_in_statement1576 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_print_in_statement1584 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_read_in_statement1592 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_conditional_in_statement1600 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_loop_in_statement1608 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_delete_in_statement1616 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ret_in_statement1624 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_invocation_in_statement1640 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LBRACE_in_block1655 = new BitSet(new long[]{0x0401680046004000L});
	public static final BitSet FOLLOW_statement_list_in_block1659 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_RBRACE_in_block1661 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_statement_in_statement_list1692 = new BitSet(new long[]{0x0401480046004002L});
	public static final BitSet FOLLOW_lvalue_in_assignment1720 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_ASSIGN_in_assignment1722 = new BitSet(new long[]{0x004000E512100000L});
	public static final BitSet FOLLOW_expression_in_assignment1726 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_SEMI_in_assignment1728 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PRINT_in_print1761 = new BitSet(new long[]{0x004000E512100000L});
	public static final BitSet FOLLOW_expression_in_print1764 = new BitSet(new long[]{0x0004000000040000L});
	public static final BitSet FOLLOW_ENDL_in_print1767 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_SEMI_in_print1771 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_READ_in_read1787 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_lvalue_in_read1790 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_SEMI_in_read1792 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IF_in_conditional1808 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_LPAREN_in_conditional1811 = new BitSet(new long[]{0x004000E512100000L});
	public static final BitSet FOLLOW_expression_in_conditional1814 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_RPAREN_in_conditional1816 = new BitSet(new long[]{0x0000000040000000L});
	public static final BitSet FOLLOW_block_in_conditional1819 = new BitSet(new long[]{0x0000000000020002L});
	public static final BitSet FOLLOW_ELSE_in_conditional1822 = new BitSet(new long[]{0x0000000040000000L});
	public static final BitSet FOLLOW_block_in_conditional1825 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_WHILE_in_loop1842 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_LPAREN_in_loop1844 = new BitSet(new long[]{0x004000E512100000L});
	public static final BitSet FOLLOW_expression_in_loop1848 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_RPAREN_in_loop1850 = new BitSet(new long[]{0x0000000040000000L});
	public static final BitSet FOLLOW_block_in_loop1854 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DELETE_in_delete1890 = new BitSet(new long[]{0x004000E512100000L});
	public static final BitSet FOLLOW_expression_in_delete1893 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_SEMI_in_delete1895 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_RETURN_in_ret1911 = new BitSet(new long[]{0x004400E512100000L});
	public static final BitSet FOLLOW_expression_in_ret1915 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_SEMI_in_ret1919 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_invocation1937 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_arguments_in_invocation1941 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_SEMI_in_invocation1943 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_lvalue1976 = new BitSet(new long[]{0x0000000000010002L});
	public static final BitSet FOLLOW_DOT_in_lvalue1979 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_ID_in_lvalue1982 = new BitSet(new long[]{0x0000000000010002L});
	public static final BitSet FOLLOW_boolterm_in_expression1999 = new BitSet(new long[]{0x0000010000000012L});
	public static final BitSet FOLLOW_AND_in_expression2003 = new BitSet(new long[]{0x004000E512100000L});
	public static final BitSet FOLLOW_OR_in_expression2008 = new BitSet(new long[]{0x004000E512100000L});
	public static final BitSet FOLLOW_boolterm_in_expression2012 = new BitSet(new long[]{0x0000010000000012L});
	public static final BitSet FOLLOW_simple_in_boolterm2029 = new BitSet(new long[]{0x0000000A81880002L});
	public static final BitSet FOLLOW_EQ_in_boolterm2033 = new BitSet(new long[]{0x004000E512100000L});
	public static final BitSet FOLLOW_LT_in_boolterm2038 = new BitSet(new long[]{0x004000E512100000L});
	public static final BitSet FOLLOW_GT_in_boolterm2043 = new BitSet(new long[]{0x004000E512100000L});
	public static final BitSet FOLLOW_NE_in_boolterm2048 = new BitSet(new long[]{0x004000E512100000L});
	public static final BitSet FOLLOW_LE_in_boolterm2053 = new BitSet(new long[]{0x004000E512100000L});
	public static final BitSet FOLLOW_GE_in_boolterm2058 = new BitSet(new long[]{0x004000E512100000L});
	public static final BitSet FOLLOW_simple_in_boolterm2062 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_term_in_simple2079 = new BitSet(new long[]{0x0000040400000002L});
	public static final BitSet FOLLOW_PLUS_in_simple2083 = new BitSet(new long[]{0x004000E512100000L});
	public static final BitSet FOLLOW_MINUS_in_simple2088 = new BitSet(new long[]{0x004000E512100000L});
	public static final BitSet FOLLOW_term_in_simple2092 = new BitSet(new long[]{0x0000040400000002L});
	public static final BitSet FOLLOW_unary_in_term2109 = new BitSet(new long[]{0x0020000000008002L});
	public static final BitSet FOLLOW_TIMES_in_term2113 = new BitSet(new long[]{0x004000E512100000L});
	public static final BitSet FOLLOW_DIVIDE_in_term2118 = new BitSet(new long[]{0x004000E512100000L});
	public static final BitSet FOLLOW_unary_in_term2122 = new BitSet(new long[]{0x0020000000008002L});
	public static final BitSet FOLLOW_NOT_in_unary2139 = new BitSet(new long[]{0x004000E112100000L});
	public static final BitSet FOLLOW_odd_not_in_unary2142 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_MINUS_in_unary2150 = new BitSet(new long[]{0x004000A512100000L});
	public static final BitSet FOLLOW_odd_neg_in_unary2153 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_selector_in_unary2161 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NOT_in_odd_not2176 = new BitSet(new long[]{0x004000E112100000L});
	public static final BitSet FOLLOW_even_not_in_odd_not2179 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_selector_in_odd_not2189 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NOT_in_even_not2219 = new BitSet(new long[]{0x004000E112100000L});
	public static final BitSet FOLLOW_odd_not_in_even_not2222 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_selector_in_even_not2230 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_MINUS_in_odd_neg2245 = new BitSet(new long[]{0x004000A512100000L});
	public static final BitSet FOLLOW_even_neg_in_odd_neg2248 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_selector_in_odd_neg2258 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_MINUS_in_even_neg2288 = new BitSet(new long[]{0x004000A512100000L});
	public static final BitSet FOLLOW_odd_neg_in_even_neg2291 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_selector_in_even_neg2299 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_factor_in_selector2314 = new BitSet(new long[]{0x0000000000010002L});
	public static final BitSet FOLLOW_DOT_in_selector2317 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_ID_in_selector2320 = new BitSet(new long[]{0x0000000000010002L});
	public static final BitSet FOLLOW_LPAREN_in_factor2337 = new BitSet(new long[]{0x004000E512100000L});
	public static final BitSet FOLLOW_expression_in_factor2340 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_RPAREN_in_factor2342 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_factor2353 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_arguments_in_factor2357 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_factor2383 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INTEGER_in_factor2391 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TRUE_in_factor2399 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FALSE_in_factor2407 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NEW_in_factor2415 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_ID_in_factor2418 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NULL_in_factor2426 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LPAREN_in_arguments2441 = new BitSet(new long[]{0x004200E512100000L});
	public static final BitSet FOLLOW_arg_list_in_arguments2444 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_RPAREN_in_arguments2446 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_expression_in_arg_list2462 = new BitSet(new long[]{0x0000000000000202L});
	public static final BitSet FOLLOW_COMMA_in_arg_list2465 = new BitSet(new long[]{0x004000E512100000L});
	public static final BitSet FOLLOW_expression_in_arg_list2467 = new BitSet(new long[]{0x0000000000000202L});
	public static final BitSet FOLLOW_STRUCT_in_synpred1_Mini1102 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_ID_in_synpred1_Mini1104 = new BitSet(new long[]{0x0000000040000000L});
	public static final BitSet FOLLOW_LBRACE_in_synpred1_Mini1106 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_STRUCT_in_synpred2_Mini1145 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_ID_in_synpred2_Mini1147 = new BitSet(new long[]{0x0000000040000000L});
	public static final BitSet FOLLOW_LBRACE_in_synpred2_Mini1149 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_lvalue_in_synpred3_Mini1569 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_ASSIGN_in_synpred3_Mini1571 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_synpred4_Mini1633 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_LPAREN_in_synpred4_Mini1635 = new BitSet(new long[]{0x0000000000000002L});
}
