package edu.calpoly.mwerner.compiler;

public class Mov extends Instruction
{
	private String instrName = "mov";
	private Register srcReg1;
	private Register targetReg;

	public Mov(Register srcReg1, Register targetReg)
	{
		this.srcReg1 = srcReg1;
		this.targetReg = targetReg;
	}

	public String getInstr()
	{
		return instrName;
	}

	public String toString()
	{
		return instrName + " " + srcReg1.toString() + ", " + targetReg.toString();
	}

}