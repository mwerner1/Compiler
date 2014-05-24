package edu.calpoly.mwerner.compiler;

import java.util.HashMap;
import java.util.Vector;

public abstract class Type 
{
	public static Int intType()
	{
		return new Int();
	}
	
	public static Bool boolType()
	{
		return new Bool();
	}
	
	public static Void voidType()
	{
		return new Void();
	}
	
	public static Struct structType(String name)
	{
		return new Struct(name);
	}
	
	public static Func funcType(String funcName, HashMap<String, Type> params, Type returnType)
	{
		return new Func(funcName, params, returnType);
	}
	
	public static Null nullType()
	{
		return new Null();
	}
	
	public static String typeStr(Type type)
	{
		if (type instanceof Int)
		{
			return "Int";
		}
		else if (type instanceof Bool)
		{
			return "Bool";
		}
		else if (type instanceof Void)
		{
			return "Void";
		}
		else if (type instanceof Struct)
		{
			return "Struct";
		}
		else if (type instanceof Func)
		{
			return "Func";
		}
		else
		{
			return "Null";
		}
	}
}
