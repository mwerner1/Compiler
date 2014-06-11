package edu.calpoly.mwerner.compiler;

import java.util.Vector;

public class Cmp extends Instruction
{
	private String instrName = "cmp";
	private Register srcReg1;
	private Register srcReg2;
	private Immediate imm;

	public Cmp(Register srcReg1, Register srcReg2)
	{
		this.srcReg1 = srcReg1;
		this.srcReg2 = srcReg2;
	}
	
	public Cmp(Immediate imm, Register srcReg2)
	{
		this.imm = imm;
		this.srcReg2 = srcReg2;
	}

	public String getInstr()
	{
		return instrName;
	}

	public String toString()
	{
		if (imm != null)
		{
			return instrName + " $" + imm.toString() + ", " + srcReg2.toString();
		}
		
		return instrName + " " + srcReg1.toString() + ", " + srcReg2.toString();
	}
	
	public Register getSource(int srcReg)
	{
		if (srcReg == 1)
		{
			return srcReg1;
		}
		else
		{
			return srcReg2;
		}
	}
	
	public Vector<Register> getSrc()
	{
		Vector<Register> sources = new Vector<Register>();
		
		if (srcReg1 != null)
		{
			sources.add(srcReg1);
		}
		if (srcReg2 != null)
		{
			sources.add(srcReg2);
		}
		
		return sources;
	}
	
	public Vector<Register> getTarget()
	{
		return null;
	}
}
