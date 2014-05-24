package edu.calpoly.mwerner.compiler;

import java.util.HashMap;

public class Func extends Type
{
	private String funcName;
	private HashMap<String, Type> params;
	private Type returnType;
	
	public Func(String name, HashMap<String, Type> args, Type retType) {
		funcName = name;
		params = args;
		returnType = retType;
	}
	
	public boolean containsParam(String paramName)
	{
		return params.containsKey(paramName);
	}
	
	public Type paramType(String paramName)
	{
		return params.get(paramName);
	}
	
	public Type returnType()
	{
		return returnType;
	}
	
	public boolean equals(Object obj)
	{
		return (obj instanceof Func);
	}
	
	public HashMap<String, Type> getParams()
	{
		return params;
	}
	
	public Type getRetType()
	{
		return returnType;
	}
}
