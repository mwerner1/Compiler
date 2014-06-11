// $ANTLR 3.5.2 C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g 2014-06-10 15:52:41

   package edu.calpoly.mwerner.compiler;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class MiniLexer extends Lexer {
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
	// delegators
	public Lexer[] getDelegates() {
		return new Lexer[] {};
	}

	public MiniLexer() {} 
	public MiniLexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}
	public MiniLexer(CharStream input, RecognizerSharedState state) {
		super(input,state);
	}
	@Override public String getGrammarFileName() { return "C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g"; }

	// $ANTLR start "BOOL"
	public final void mBOOL() throws RecognitionException {
		try {
			int _type = BOOL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:6:6: ( 'bool' )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:6:8: 'bool'
			{
			match("bool"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "BOOL"

	// $ANTLR start "DELETE"
	public final void mDELETE() throws RecognitionException {
		try {
			int _type = DELETE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:7:8: ( 'delete' )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:7:10: 'delete'
			{
			match("delete"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DELETE"

	// $ANTLR start "ELSE"
	public final void mELSE() throws RecognitionException {
		try {
			int _type = ELSE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:8:6: ( 'else' )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:8:8: 'else'
			{
			match("else"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ELSE"

	// $ANTLR start "ENDL"
	public final void mENDL() throws RecognitionException {
		try {
			int _type = ENDL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:9:6: ( 'endl' )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:9:8: 'endl'
			{
			match("endl"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ENDL"

	// $ANTLR start "FALSE"
	public final void mFALSE() throws RecognitionException {
		try {
			int _type = FALSE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:10:7: ( 'false' )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:10:9: 'false'
			{
			match("false"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FALSE"

	// $ANTLR start "FUN"
	public final void mFUN() throws RecognitionException {
		try {
			int _type = FUN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:11:5: ( 'fun' )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:11:7: 'fun'
			{
			match("fun"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FUN"

	// $ANTLR start "IF"
	public final void mIF() throws RecognitionException {
		try {
			int _type = IF;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:12:4: ( 'if' )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:12:6: 'if'
			{
			match("if"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "IF"

	// $ANTLR start "INT"
	public final void mINT() throws RecognitionException {
		try {
			int _type = INT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:13:5: ( 'int' )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:13:7: 'int'
			{
			match("int"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "INT"

	// $ANTLR start "NEW"
	public final void mNEW() throws RecognitionException {
		try {
			int _type = NEW;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:14:5: ( 'new' )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:14:7: 'new'
			{
			match("new"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NEW"

	// $ANTLR start "NULL"
	public final void mNULL() throws RecognitionException {
		try {
			int _type = NULL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:15:6: ( 'null' )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:15:8: 'null'
			{
			match("null"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NULL"

	// $ANTLR start "PRINT"
	public final void mPRINT() throws RecognitionException {
		try {
			int _type = PRINT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:16:7: ( 'print' )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:16:9: 'print'
			{
			match("print"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PRINT"

	// $ANTLR start "READ"
	public final void mREAD() throws RecognitionException {
		try {
			int _type = READ;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:17:6: ( 'read' )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:17:8: 'read'
			{
			match("read"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "READ"

	// $ANTLR start "RETURN"
	public final void mRETURN() throws RecognitionException {
		try {
			int _type = RETURN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:18:8: ( 'return' )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:18:10: 'return'
			{
			match("return"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "RETURN"

	// $ANTLR start "STRUCT"
	public final void mSTRUCT() throws RecognitionException {
		try {
			int _type = STRUCT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:19:8: ( 'struct' )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:19:10: 'struct'
			{
			match("struct"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "STRUCT"

	// $ANTLR start "TRUE"
	public final void mTRUE() throws RecognitionException {
		try {
			int _type = TRUE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:20:6: ( 'true' )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:20:8: 'true'
			{
			match("true"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "TRUE"

	// $ANTLR start "VOID"
	public final void mVOID() throws RecognitionException {
		try {
			int _type = VOID;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:21:6: ( 'void' )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:21:8: 'void'
			{
			match("void"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "VOID"

	// $ANTLR start "WHILE"
	public final void mWHILE() throws RecognitionException {
		try {
			int _type = WHILE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:22:7: ( 'while' )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:22:9: 'while'
			{
			match("while"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WHILE"

	// $ANTLR start "LBRACE"
	public final void mLBRACE() throws RecognitionException {
		try {
			int _type = LBRACE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:75:10: ( '{' )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:75:13: '{'
			{
			match('{'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LBRACE"

	// $ANTLR start "RBRACE"
	public final void mRBRACE() throws RecognitionException {
		try {
			int _type = RBRACE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:76:10: ( '}' )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:76:13: '}'
			{
			match('}'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "RBRACE"

	// $ANTLR start "SEMI"
	public final void mSEMI() throws RecognitionException {
		try {
			int _type = SEMI;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:77:10: ( ';' )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:77:13: ';'
			{
			match(';'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SEMI"

	// $ANTLR start "COMMA"
	public final void mCOMMA() throws RecognitionException {
		try {
			int _type = COMMA;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:78:10: ( ',' )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:78:13: ','
			{
			match(','); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "COMMA"

	// $ANTLR start "LPAREN"
	public final void mLPAREN() throws RecognitionException {
		try {
			int _type = LPAREN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:79:10: ( '(' )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:79:13: '('
			{
			match('('); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LPAREN"

	// $ANTLR start "RPAREN"
	public final void mRPAREN() throws RecognitionException {
		try {
			int _type = RPAREN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:80:10: ( ')' )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:80:13: ')'
			{
			match(')'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "RPAREN"

	// $ANTLR start "ASSIGN"
	public final void mASSIGN() throws RecognitionException {
		try {
			int _type = ASSIGN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:81:10: ( '=' )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:81:13: '='
			{
			match('='); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ASSIGN"

	// $ANTLR start "DOT"
	public final void mDOT() throws RecognitionException {
		try {
			int _type = DOT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:82:10: ( '.' )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:82:13: '.'
			{
			match('.'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DOT"

	// $ANTLR start "AND"
	public final void mAND() throws RecognitionException {
		try {
			int _type = AND;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:83:10: ( '&&' )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:83:13: '&&'
			{
			match("&&"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "AND"

	// $ANTLR start "OR"
	public final void mOR() throws RecognitionException {
		try {
			int _type = OR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:84:10: ( '||' )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:84:13: '||'
			{
			match("||"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "OR"

	// $ANTLR start "EQ"
	public final void mEQ() throws RecognitionException {
		try {
			int _type = EQ;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:85:10: ( '==' )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:85:13: '=='
			{
			match("=="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EQ"

	// $ANTLR start "LT"
	public final void mLT() throws RecognitionException {
		try {
			int _type = LT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:86:10: ( '<' )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:86:13: '<'
			{
			match('<'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LT"

	// $ANTLR start "GT"
	public final void mGT() throws RecognitionException {
		try {
			int _type = GT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:87:10: ( '>' )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:87:13: '>'
			{
			match('>'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "GT"

	// $ANTLR start "NE"
	public final void mNE() throws RecognitionException {
		try {
			int _type = NE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:88:10: ( '!=' )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:88:13: '!='
			{
			match("!="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NE"

	// $ANTLR start "LE"
	public final void mLE() throws RecognitionException {
		try {
			int _type = LE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:89:10: ( '<=' )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:89:13: '<='
			{
			match("<="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LE"

	// $ANTLR start "GE"
	public final void mGE() throws RecognitionException {
		try {
			int _type = GE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:90:10: ( '>=' )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:90:13: '>='
			{
			match(">="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "GE"

	// $ANTLR start "PLUS"
	public final void mPLUS() throws RecognitionException {
		try {
			int _type = PLUS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:91:10: ( '+' )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:91:13: '+'
			{
			match('+'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PLUS"

	// $ANTLR start "MINUS"
	public final void mMINUS() throws RecognitionException {
		try {
			int _type = MINUS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:92:10: ( '-' )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:92:13: '-'
			{
			match('-'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "MINUS"

	// $ANTLR start "TIMES"
	public final void mTIMES() throws RecognitionException {
		try {
			int _type = TIMES;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:93:10: ( '*' )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:93:13: '*'
			{
			match('*'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "TIMES"

	// $ANTLR start "DIVIDE"
	public final void mDIVIDE() throws RecognitionException {
		try {
			int _type = DIVIDE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:94:10: ( '/' )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:94:13: '/'
			{
			match('/'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DIVIDE"

	// $ANTLR start "NOT"
	public final void mNOT() throws RecognitionException {
		try {
			int _type = NOT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:95:10: ( '!' )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:95:13: '!'
			{
			match('!'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NOT"

	// $ANTLR start "ID"
	public final void mID() throws RecognitionException {
		try {
			int _type = ID;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:97:10: ( ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )* )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:97:13: ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )*
			{
			if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:97:34: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )*
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( ((LA1_0 >= '0' && LA1_0 <= '9')||(LA1_0 >= 'A' && LA1_0 <= 'Z')||(LA1_0 >= 'a' && LA1_0 <= 'z')) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop1;
				}
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ID"

	// $ANTLR start "INTEGER"
	public final void mINTEGER() throws RecognitionException {
		try {
			int _type = INTEGER;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:99:10: ( '0' | ( '1' .. '9' ) ( '0' .. '9' )* )
			int alt3=2;
			int LA3_0 = input.LA(1);
			if ( (LA3_0=='0') ) {
				alt3=1;
			}
			else if ( ((LA3_0 >= '1' && LA3_0 <= '9')) ) {
				alt3=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 3, 0, input);
				throw nvae;
			}

			switch (alt3) {
				case 1 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:99:13: '0'
					{
					match('0'); 
					}
					break;
				case 2 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:99:19: ( '1' .. '9' ) ( '0' .. '9' )*
					{
					if ( (input.LA(1) >= '1' && input.LA(1) <= '9') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:99:30: ( '0' .. '9' )*
					loop2:
					while (true) {
						int alt2=2;
						int LA2_0 = input.LA(1);
						if ( ((LA2_0 >= '0' && LA2_0 <= '9')) ) {
							alt2=1;
						}

						switch (alt2) {
						case 1 :
							// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:
							{
							if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

						default :
							break loop2;
						}
					}

					}
					break;

			}
			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "INTEGER"

	// $ANTLR start "WS"
	public final void mWS() throws RecognitionException {
		try {
			int _type = WS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:101:10: ( ( ' ' | '\\t' | '\\f' | '\\r' | '\\n' )+ )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:101:13: ( ' ' | '\\t' | '\\f' | '\\r' | '\\n' )+
			{
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:101:13: ( ' ' | '\\t' | '\\f' | '\\r' | '\\n' )+
			int cnt4=0;
			loop4:
			while (true) {
				int alt4=2;
				int LA4_0 = input.LA(1);
				if ( ((LA4_0 >= '\t' && LA4_0 <= '\n')||(LA4_0 >= '\f' && LA4_0 <= '\r')||LA4_0==' ') ) {
					alt4=1;
				}

				switch (alt4) {
				case 1 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:
					{
					if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||(input.LA(1) >= '\f' && input.LA(1) <= '\r')||input.LA(1)==' ' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt4 >= 1 ) break loop4;
					EarlyExitException eee = new EarlyExitException(4, input);
					throw eee;
				}
				cnt4++;
			}

			 skip(); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WS"

	// $ANTLR start "COMMENT"
	public final void mCOMMENT() throws RecognitionException {
		try {
			int _type = COMMENT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:110:10: ( '#' (~ '\\n' )* '\\n' )
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:110:13: '#' (~ '\\n' )* '\\n'
			{
			match('#'); 
			// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:110:17: (~ '\\n' )*
			loop5:
			while (true) {
				int alt5=2;
				int LA5_0 = input.LA(1);
				if ( ((LA5_0 >= '\u0000' && LA5_0 <= '\t')||(LA5_0 >= '\u000B' && LA5_0 <= '\uFFFF')) ) {
					alt5=1;
				}

				switch (alt5) {
				case 1 :
					// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:
					{
					if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\uFFFF') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop5;
				}
			}

			match('\n'); 
			 skip(); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "COMMENT"

	@Override
	public void mTokens() throws RecognitionException {
		// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:1:8: ( BOOL | DELETE | ELSE | ENDL | FALSE | FUN | IF | INT | NEW | NULL | PRINT | READ | RETURN | STRUCT | TRUE | VOID | WHILE | LBRACE | RBRACE | SEMI | COMMA | LPAREN | RPAREN | ASSIGN | DOT | AND | OR | EQ | LT | GT | NE | LE | GE | PLUS | MINUS | TIMES | DIVIDE | NOT | ID | INTEGER | WS | COMMENT )
		int alt6=42;
		alt6 = dfa6.predict(input);
		switch (alt6) {
			case 1 :
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:1:10: BOOL
				{
				mBOOL(); 

				}
				break;
			case 2 :
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:1:15: DELETE
				{
				mDELETE(); 

				}
				break;
			case 3 :
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:1:22: ELSE
				{
				mELSE(); 

				}
				break;
			case 4 :
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:1:27: ENDL
				{
				mENDL(); 

				}
				break;
			case 5 :
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:1:32: FALSE
				{
				mFALSE(); 

				}
				break;
			case 6 :
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:1:38: FUN
				{
				mFUN(); 

				}
				break;
			case 7 :
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:1:42: IF
				{
				mIF(); 

				}
				break;
			case 8 :
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:1:45: INT
				{
				mINT(); 

				}
				break;
			case 9 :
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:1:49: NEW
				{
				mNEW(); 

				}
				break;
			case 10 :
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:1:53: NULL
				{
				mNULL(); 

				}
				break;
			case 11 :
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:1:58: PRINT
				{
				mPRINT(); 

				}
				break;
			case 12 :
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:1:64: READ
				{
				mREAD(); 

				}
				break;
			case 13 :
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:1:69: RETURN
				{
				mRETURN(); 

				}
				break;
			case 14 :
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:1:76: STRUCT
				{
				mSTRUCT(); 

				}
				break;
			case 15 :
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:1:83: TRUE
				{
				mTRUE(); 

				}
				break;
			case 16 :
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:1:88: VOID
				{
				mVOID(); 

				}
				break;
			case 17 :
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:1:93: WHILE
				{
				mWHILE(); 

				}
				break;
			case 18 :
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:1:99: LBRACE
				{
				mLBRACE(); 

				}
				break;
			case 19 :
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:1:106: RBRACE
				{
				mRBRACE(); 

				}
				break;
			case 20 :
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:1:113: SEMI
				{
				mSEMI(); 

				}
				break;
			case 21 :
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:1:118: COMMA
				{
				mCOMMA(); 

				}
				break;
			case 22 :
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:1:124: LPAREN
				{
				mLPAREN(); 

				}
				break;
			case 23 :
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:1:131: RPAREN
				{
				mRPAREN(); 

				}
				break;
			case 24 :
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:1:138: ASSIGN
				{
				mASSIGN(); 

				}
				break;
			case 25 :
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:1:145: DOT
				{
				mDOT(); 

				}
				break;
			case 26 :
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:1:149: AND
				{
				mAND(); 

				}
				break;
			case 27 :
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:1:153: OR
				{
				mOR(); 

				}
				break;
			case 28 :
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:1:156: EQ
				{
				mEQ(); 

				}
				break;
			case 29 :
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:1:159: LT
				{
				mLT(); 

				}
				break;
			case 30 :
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:1:162: GT
				{
				mGT(); 

				}
				break;
			case 31 :
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:1:165: NE
				{
				mNE(); 

				}
				break;
			case 32 :
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:1:168: LE
				{
				mLE(); 

				}
				break;
			case 33 :
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:1:171: GE
				{
				mGE(); 

				}
				break;
			case 34 :
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:1:174: PLUS
				{
				mPLUS(); 

				}
				break;
			case 35 :
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:1:179: MINUS
				{
				mMINUS(); 

				}
				break;
			case 36 :
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:1:185: TIMES
				{
				mTIMES(); 

				}
				break;
			case 37 :
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:1:191: DIVIDE
				{
				mDIVIDE(); 

				}
				break;
			case 38 :
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:1:198: NOT
				{
				mNOT(); 

				}
				break;
			case 39 :
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:1:202: ID
				{
				mID(); 

				}
				break;
			case 40 :
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:1:205: INTEGER
				{
				mINTEGER(); 

				}
				break;
			case 41 :
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:1:213: WS
				{
				mWS(); 

				}
				break;
			case 42 :
				// C:\\eclipse-workspaces\\workspace1\\Compiler\\src\\edu\\calpoly\\mwerner\\compiler\\Mini.g:1:216: COMMENT
				{
				mCOMMENT(); 

				}
				break;

		}
	}


	protected DFA6 dfa6 = new DFA6(this);
	static final String DFA6_eotS =
		"\1\uffff\14\36\6\uffff\1\63\3\uffff\1\65\1\67\1\71\10\uffff\6\36\1\100"+
		"\11\36\10\uffff\5\36\1\120\1\uffff\1\121\1\122\10\36\1\133\1\36\1\135"+
		"\1\136\1\36\3\uffff\1\140\1\36\1\142\2\36\1\145\1\146\1\36\1\uffff\1\36"+
		"\2\uffff\1\151\1\uffff\1\152\1\uffff\2\36\2\uffff\1\155\1\156\2\uffff"+
		"\1\157\1\160\4\uffff";
	static final String DFA6_eofS =
		"\161\uffff";
	static final String DFA6_minS =
		"\1\11\1\157\1\145\1\154\1\141\1\146\1\145\1\162\1\145\1\164\1\162\1\157"+
		"\1\150\6\uffff\1\75\3\uffff\3\75\10\uffff\1\157\1\154\1\163\1\144\1\154"+
		"\1\156\1\60\1\164\1\167\1\154\1\151\1\141\1\162\1\165\2\151\10\uffff\1"+
		"\154\2\145\1\154\1\163\1\60\1\uffff\2\60\1\154\1\156\1\144\2\165\1\145"+
		"\1\144\1\154\1\60\1\164\2\60\1\145\3\uffff\1\60\1\164\1\60\1\162\1\143"+
		"\2\60\1\145\1\uffff\1\145\2\uffff\1\60\1\uffff\1\60\1\uffff\1\156\1\164"+
		"\2\uffff\2\60\2\uffff\2\60\4\uffff";
	static final String DFA6_maxS =
		"\1\175\1\157\1\145\1\156\1\165\1\156\1\165\1\162\1\145\1\164\1\162\1\157"+
		"\1\150\6\uffff\1\75\3\uffff\3\75\10\uffff\1\157\1\154\1\163\1\144\1\154"+
		"\1\156\1\172\1\164\1\167\1\154\1\151\1\164\1\162\1\165\2\151\10\uffff"+
		"\1\154\2\145\1\154\1\163\1\172\1\uffff\2\172\1\154\1\156\1\144\2\165\1"+
		"\145\1\144\1\154\1\172\1\164\2\172\1\145\3\uffff\1\172\1\164\1\172\1\162"+
		"\1\143\2\172\1\145\1\uffff\1\145\2\uffff\1\172\1\uffff\1\172\1\uffff\1"+
		"\156\1\164\2\uffff\2\172\2\uffff\2\172\4\uffff";
	static final String DFA6_acceptS =
		"\15\uffff\1\22\1\23\1\24\1\25\1\26\1\27\1\uffff\1\31\1\32\1\33\3\uffff"+
		"\1\42\1\43\1\44\1\45\1\47\1\50\1\51\1\52\20\uffff\1\34\1\30\1\40\1\35"+
		"\1\41\1\36\1\37\1\46\6\uffff\1\7\17\uffff\1\6\1\10\1\11\10\uffff\1\1\1"+
		"\uffff\1\3\1\4\1\uffff\1\12\1\uffff\1\14\2\uffff\1\17\1\20\2\uffff\1\5"+
		"\1\13\2\uffff\1\21\1\2\1\15\1\16";
	static final String DFA6_specialS =
		"\161\uffff}>";
	static final String[] DFA6_transitionS = {
			"\2\40\1\uffff\2\40\22\uffff\1\40\1\31\1\uffff\1\41\2\uffff\1\25\1\uffff"+
			"\1\21\1\22\1\34\1\32\1\20\1\33\1\24\1\35\12\37\1\uffff\1\17\1\27\1\23"+
			"\1\30\2\uffff\32\36\6\uffff\1\36\1\1\1\36\1\2\1\3\1\4\2\36\1\5\4\36\1"+
			"\6\1\36\1\7\1\36\1\10\1\11\1\12\1\36\1\13\1\14\3\36\1\15\1\26\1\16",
			"\1\42",
			"\1\43",
			"\1\44\1\uffff\1\45",
			"\1\46\23\uffff\1\47",
			"\1\50\7\uffff\1\51",
			"\1\52\17\uffff\1\53",
			"\1\54",
			"\1\55",
			"\1\56",
			"\1\57",
			"\1\60",
			"\1\61",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\62",
			"",
			"",
			"",
			"\1\64",
			"\1\66",
			"\1\70",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\72",
			"\1\73",
			"\1\74",
			"\1\75",
			"\1\76",
			"\1\77",
			"\12\36\7\uffff\32\36\6\uffff\32\36",
			"\1\101",
			"\1\102",
			"\1\103",
			"\1\104",
			"\1\105\22\uffff\1\106",
			"\1\107",
			"\1\110",
			"\1\111",
			"\1\112",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\113",
			"\1\114",
			"\1\115",
			"\1\116",
			"\1\117",
			"\12\36\7\uffff\32\36\6\uffff\32\36",
			"",
			"\12\36\7\uffff\32\36\6\uffff\32\36",
			"\12\36\7\uffff\32\36\6\uffff\32\36",
			"\1\123",
			"\1\124",
			"\1\125",
			"\1\126",
			"\1\127",
			"\1\130",
			"\1\131",
			"\1\132",
			"\12\36\7\uffff\32\36\6\uffff\32\36",
			"\1\134",
			"\12\36\7\uffff\32\36\6\uffff\32\36",
			"\12\36\7\uffff\32\36\6\uffff\32\36",
			"\1\137",
			"",
			"",
			"",
			"\12\36\7\uffff\32\36\6\uffff\32\36",
			"\1\141",
			"\12\36\7\uffff\32\36\6\uffff\32\36",
			"\1\143",
			"\1\144",
			"\12\36\7\uffff\32\36\6\uffff\32\36",
			"\12\36\7\uffff\32\36\6\uffff\32\36",
			"\1\147",
			"",
			"\1\150",
			"",
			"",
			"\12\36\7\uffff\32\36\6\uffff\32\36",
			"",
			"\12\36\7\uffff\32\36\6\uffff\32\36",
			"",
			"\1\153",
			"\1\154",
			"",
			"",
			"\12\36\7\uffff\32\36\6\uffff\32\36",
			"\12\36\7\uffff\32\36\6\uffff\32\36",
			"",
			"",
			"\12\36\7\uffff\32\36\6\uffff\32\36",
			"\12\36\7\uffff\32\36\6\uffff\32\36",
			"",
			"",
			"",
			""
	};

	static final short[] DFA6_eot = DFA.unpackEncodedString(DFA6_eotS);
	static final short[] DFA6_eof = DFA.unpackEncodedString(DFA6_eofS);
	static final char[] DFA6_min = DFA.unpackEncodedStringToUnsignedChars(DFA6_minS);
	static final char[] DFA6_max = DFA.unpackEncodedStringToUnsignedChars(DFA6_maxS);
	static final short[] DFA6_accept = DFA.unpackEncodedString(DFA6_acceptS);
	static final short[] DFA6_special = DFA.unpackEncodedString(DFA6_specialS);
	static final short[][] DFA6_transition;

	static {
		int numStates = DFA6_transitionS.length;
		DFA6_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA6_transition[i] = DFA.unpackEncodedString(DFA6_transitionS[i]);
		}
	}

	protected class DFA6 extends DFA {

		public DFA6(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 6;
			this.eot = DFA6_eot;
			this.eof = DFA6_eof;
			this.min = DFA6_min;
			this.max = DFA6_max;
			this.accept = DFA6_accept;
			this.special = DFA6_special;
			this.transition = DFA6_transition;
		}
		@Override
		public String getDescription() {
			return "1:1: Tokens : ( BOOL | DELETE | ELSE | ENDL | FALSE | FUN | IF | INT | NEW | NULL | PRINT | READ | RETURN | STRUCT | TRUE | VOID | WHILE | LBRACE | RBRACE | SEMI | COMMA | LPAREN | RPAREN | ASSIGN | DOT | AND | OR | EQ | LT | GT | NE | LE | GE | PLUS | MINUS | TIMES | DIVIDE | NOT | ID | INTEGER | WS | COMMENT );";
		}
	}

}
