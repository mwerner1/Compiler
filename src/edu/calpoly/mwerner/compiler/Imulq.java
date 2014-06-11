package edu.calpoly.mwerner.compiler;

import java.util.Vector;

public class Imulq extends Instruction
{
	private String instrName = "imulq";
	private Register srcReg;
	private Register targetReg;
	private Immediate imm;

	public Imulq(Register srcReg, Register targetReg)
	{
		this.srcReg = srcReg;
		this.targetReg = targetReg;
	}
	
	public Imulq(Immediate imm, Register srcReg, Register targetReg)
	{
		this.imm = imm;
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
			if (srcReg != null)
			{
				return instrName + " $" + imm.toString() + ", " + srcReg.toString() + ", " + targetReg.toString();
			}
		}
		
		return instrName + " " + srcReg.toString() + ", " + targetReg.toString();
	}

//	public Register getTarget()
//	{
//		return targetReg;
//	}
//	
//	public Register getSource()
//	{
//		return srcReg;
//	}
	
	public Immediate getImm()
	{
		return imm;
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
