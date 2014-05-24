package edu.calpoly.mwerner.compiler;

import java.util.HashMap;
import java.util.Vector;

public class StructTypes 
{
	private HashMap<String, HashMap<String, Type>> structs;
	
	public StructTypes()
	{
		structs = new HashMap<String, HashMap<String, Type>>();
	}
	
	public boolean isDefined(String structName)
	{
		return structs.containsKey(structName);
	}
	
	public void addStruct(String structName, HashMap<String, Type> structVals)
	{
		structs.put(structName, structVals);
	}
	
	public boolean containsVal(String structName, String valName)
	{
		return structs.get(structName).containsKey(valName);
	}
	
	public Type getValType(String structName, String valName)
	{
		return structs.get(structName).get(valName);
	}
	
	public Vector<Type> getStructTypes(String structName)
	{
		Vector<Type> structTypes = new Vector<Type>();
		
		for (Type type : structs.get(structName).values())
		{
			structTypes.add(type);
		}
		
		return structTypes;
	}
}
