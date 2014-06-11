package edu.calpoly.mwerner.compiler;

import java.util.Vector;

public class Divq extends Instruction
{
	private String instrName = "divq";
	private Register reg;
	
	public Divq(Register reg)
	{
		this.reg = reg;
	}
	
	public String getInstr()
	{
		return instrName;
	}

	public String toString()
	{
		return instrName + " " + reg.toString();
	}
	
	public Vector<Register> getSrc()
	{
		Vector<Register> sources = new Vector<Register>();
		
		if (reg != null)
		{
			sources.add(reg);
		}
		
		sources.add(new Register("%rax"));
		sources.add(new Register("%rdx"));
		
		return sources;
	}
	
	public Vector<Register> getTarget()
	{
		Vector<Register> targets = new Vector<Register>();
		
		targets.add(new Register("%rax"));
		
		return targets;
	}
}
