package edu.calpoly.mwerner.compiler;

public class Immediate 
{
	private long val;
	
	public Immediate(long immVal)
	{
		val = immVal;
	}
	
	public long getImmediate()
	{
		return val;
	}
	
	public String toString()
	{
		return Long.toString(val);
	}
}
