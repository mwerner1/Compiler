package edu.calpoly.mwerner.compiler;

import java.util.Vector;

public class Pushq extends Instruction
{
	private String instrName = "pushq";
	private Register reg;
	
	public Pushq(Register reg)
	{
		this.reg = reg;
	}
	
	public String toString()
	{
		return (instrName + " " + reg.toString());
	}
	
	public Vector<Register> getSrc()
	{
		Vector<Register> sources = new Vector<Register>();
		
		if (reg != null)
		{
			sources.add(reg);
		}
		
		return sources;
	}
	
	public Vector<Register> getTarget()
	{
		return null;
	}
}
