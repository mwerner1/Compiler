package edu.calpoly.mwerner.compiler;

public class Loadi extends Instruction
{
	private String instrName = "loadi";
	private Immediate imm;
	private Register targetReg;

	public Loadi(Immediate imm, Register targetReg)
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

}