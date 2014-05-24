package edu.calpoly.mwerner.compiler;

import java.util.HashMap;

public class SymbolTable 
{
	private HashMap<String, HashMap<String, Type>> vars;
	
	public SymbolTable()
	{
		vars = new HashMap<String, HashMap<String, Type>>();
	}
	
	public void addVar(String scope, String varName, Type type)
	{
		vars.get(scope).put(varName, type);
	}
	
	public boolean scopePrevDefined(String scope)
	{
		return vars.containsKey(scope);
	}
	
	public boolean varPrevDefined(String scope, String varName)
	{
		return vars.get(scope).containsKey(varName);
	}
	
	public void addScope(String scope)
	{
		vars.put(scope, new HashMap<String, Type>());
	}
	
	public Type getVarType(String scope, String varName)
	{
		return vars.get(scope).get(varName);
	}
	
	public HashMap<String, Type> getMapForScope(String scope)
	{
		return vars.get(scope);
	}
	
	public void printTable()
	{
		System.out.println(vars.toString());
	}
	
}
