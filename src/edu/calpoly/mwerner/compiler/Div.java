package edu.calpoly.mwerner.compiler;

public class Div extends Instruction
{
	private String instrName = "div";
	private Register srcReg1;
	private Register srcReg2;
	private Register targetReg;

	public Div(Register srcReg1, Register srcReg2, Register targetReg)
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

}