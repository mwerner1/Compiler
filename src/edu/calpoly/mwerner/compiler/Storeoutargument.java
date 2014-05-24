package edu.calpoly.mwerner.compiler;

public class Storeoutargument extends Instruction
{
	private String instrName = "storeoutargument";
	private Register srcReg1;
	private Immediate imm;

	public Storeoutargument(Register srcReg1, Immediate imm)
	{
		this.srcReg1 = srcReg1;
		this.imm = imm;
	}

	public String getInstr()
	{
		return instrName;
	}

	public String toString()
	{
		return instrName + " " + srcReg1.toString() + ", " + imm.toString();
	}

}