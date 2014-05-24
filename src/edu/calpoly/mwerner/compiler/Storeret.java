package edu.calpoly.mwerner.compiler;

public class Storeret extends Instruction
{
	private String instrName = "storeret";
	private Register srcReg1;

	public Storeret(Register srcReg1)
	{
		this.srcReg1 = srcReg1;
	}

	public String getInstr()
	{
		return instrName;
	}

	public String toString()
	{
		return instrName + " " + srcReg1.toString();
	}

}