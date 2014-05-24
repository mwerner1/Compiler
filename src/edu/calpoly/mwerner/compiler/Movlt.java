package edu.calpoly.mwerner.compiler;

public class Movlt extends Instruction
{
	private String instrName = "movlt";
	private Immediate imm;
	private Register targetReg;

	public Movlt(Immediate imm, Register targetReg)
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
		return instrName + " " + imm.toString() + ", " + targetReg.toString();
	}

	public Immediate getImmediate()
	{
		return imm;
	}
	
	public Register getTarget()
	{
		return targetReg;
	}
}