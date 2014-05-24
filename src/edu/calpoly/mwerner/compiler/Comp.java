package edu.calpoly.mwerner.compiler;

public class Comp extends Instruction
{
	private String instrName = "comp";
	private Register srcReg1;
	private Register srcReg2;
	private Register condCodeReg;

	public Comp(Register srcReg1, Register srcReg2, Register condCodeReg)
	{
		this.srcReg1 = srcReg1;
		this.srcReg2 = srcReg2;
		this.condCodeReg = condCodeReg;
	}

	public String getInstr()
	{
		return instrName;
	}

	public String toString()
	{
		return instrName + " " + srcReg1.toString() + ", " + srcReg2.toString() + ", " + condCodeReg.toString();
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