package edu.calpoly.mwerner.compiler;

import java.util.Vector;

public class Struct extends Type {
	private String structName;
	
	public Struct(String name)
	{
		structName = name;
	}
	
	public String structName()
	{
		return structName;
	}
	
	public boolean equals(Object obj)
	{
		if (!(obj instanceof Struct))
		{
			return false;
		}
		
		return this.structName.equals(((Struct)obj).structName());
	}
}
