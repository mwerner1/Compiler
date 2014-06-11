package edu.calpoly.mwerner.compiler;

import java.util.Vector;

public class Xorq extends Instruction
{
	private String instrName = "xorq";
	private Register srcReg1;
	private Immediate imm;
	private Register targetReg;

	public Xorq(Register srcReg1, Register targetReg)
	{
		this.srcReg1 = srcReg1;
		this.targetReg = targetReg;
	}
	
	public Xorq(Immediate imm, Register targetReg)
	{
		this.imm = imm;
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
		
		return instrName + " " + srcReg1.toString() + ", " + targetReg.toString();
	}

//	public Register getTarget()
//	{
//		return targetReg;
//	}
//	
//	public Register getSource()
//	{
//		return srcReg1;
//	}
	
	public Immediate getImmediate()
	{
		return imm;
	}
	
	public Vector<Register> getSrc()
	{
		Vector<Register> sources = new Vector<Register>();
		
		if (srcReg1 != null)
		{
			sources.add(srcReg1);
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
