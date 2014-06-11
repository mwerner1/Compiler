package edu.calpoly.mwerner.compiler;

import java.util.Vector;

public class Popq extends Instruction
{
	private String instrName = "popq";
	private Register reg;
	
	public Popq(Register reg)
	{
		this.reg = reg;
	}
	
	public String toString()
	{
		return instrName + " " + reg.toString();
	}
	
	public Vector<Register> getSrc()
	{
		return null;
	}
	
	public Vector<Register> getTarget()
	{
		Vector<Register> targets = new Vector<Register>();
		
		if (reg != null)
		{
			targets.add(reg);
		}
		
		return targets;
	}
}
