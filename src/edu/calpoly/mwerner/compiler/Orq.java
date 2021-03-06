package edu.calpoly.mwerner.compiler;

import java.util.Vector;

public class Orq extends Instruction
{
	private String instrName = "orq";
	private Register srcReg;
	private Register targetReg;
	private Immediate imm;
	
	public Orq(Register srcReg, Register targetReg)
	{
		this.srcReg = srcReg;
		this.targetReg = targetReg;
	}
	
	public Orq(Immediate imm, Register targetReg)
	{
		this.imm = imm;
		this.targetReg = targetReg;
	}
	
	public String toString()
	{
		if (imm != null)
		{
			return instrName + " $" + imm.toString() + ", " + targetReg.toString();
		}
		
		return instrName + " " + srcReg.toString() + ", " + targetReg.toString();
	}
	
	public Vector<Register> getSrc()
	{
		Vector<Register> sources = new Vector<Register>();
		
		if (srcReg != null)
		{
			sources.add(srcReg);
		}
		
		if (targetReg != null)
		{
			sources.add(targetReg);
		}
		
		return sources;
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
