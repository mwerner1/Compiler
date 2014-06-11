package edu.calpoly.mwerner.compiler;

import java.util.Vector;

public class Cmovlq extends Instruction
{
	private String instrName = "cmovlq";
	private Immediate imm;
	private Register srcReg;
	private Register targetReg;

	public Cmovlq(Immediate imm, Register targetReg)
	{
		this.imm = imm;
		this.targetReg = targetReg;
	}
	
	public Cmovlq(Register srcReg, Register targetReg)
	{
		this.srcReg = srcReg;
		this.targetReg = targetReg;
	}

	public String getInstr()
	{
		return instrName;
	}

	public String toString()
	{
		if (imm != null)
		{
			return instrName + " $" + imm.toString() + ", " + targetReg.toString();
		}
		
		return instrName + " " + srcReg.toString() + ", " + targetReg.toString();
	}
	
	public Immediate getImmediate()
	{
		return imm;
	}
	
//	public Register getTarget()
//	{
//		return targetReg;
//	}
//	
//	public Register getSrc()
//	{
//		return srcReg;
//	}
	
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
