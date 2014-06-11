package edu.calpoly.mwerner.compiler;

import java.util.Vector;

public class Movqaddr extends Instruction
{
	private String var;
	private Register targetReg;
	private String instrName = "movq";
	
	public Movqaddr(String var, Register targetReg)
	{
		this.var = var;
		this.targetReg = targetReg;
	}
	
	public String toString()
	{
		return instrName + " &" + var + ", " + targetReg.toString();
	}
	
	public Vector<Register> getSrc()
	{
		return null;
	}
	
	public Vector<Register> getTarget()
	{
		Vector<Register> targets = new Vector<Register>();
		
		if (targetReg != null)
		{
			targets.add(targetReg);
		}
		
		return targets;
	}
}
