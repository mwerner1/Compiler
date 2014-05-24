package edu.calpoly.mwerner.compiler;

public class Movle extends Instruction
{
	private String instrName = "movle";
	private Immediate imm;
	private Register targetReg;

	public Movle(Immediate imm, Register targetReg)
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