package edu.calpoly.mwerner.compiler;

public class Add extends Instruction
{
	private String instrName = "add";
	private Register srcReg1;
	private Register srcReg2;
	private Register targetReg;

	public Add(Register srcReg1, Register srcReg2, Register targetReg)
	{
		this.srcReg1 = srcReg1;
		this.srcReg2 = srcReg2;
		this.targetReg = targetReg;
	}

	public String getInstr()
	{
		return instrName;
	}

	public String toString()
	{
		return instrName + " " + srcReg1.toString() + ", " + srcReg2.toString() + ", " + targetReg.toString();
	}
	
	public Register getTarget()
	{
		return targetReg;
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

}