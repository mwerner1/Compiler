package edu.calpoly.mwerner.compiler;

public class Null extends Type
{
	public boolean equals(Object obj)
	{
		return (obj instanceof Null);
	}
}
