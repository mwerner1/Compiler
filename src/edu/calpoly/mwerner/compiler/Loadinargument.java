package edu.calpoly.mwerner.compiler;

public class Loadinargument extends Instruction
{
	private String instrName = "loadinargument";
	private Id id;
	private Immediate imm;
	private Register targetReg;

	public Loadinargument(Id id, Immediate imm, Register targetReg)
	{
		this.id = id;
		this.imm = imm;
		this.targetReg = targetReg;
	}

	public String getInstr()
	{
		return instrName;
	}

	public String toString()
	{
		return instrName + " " + id.toString() + ", " + imm.toString() + ", " + targetReg.toString();
	}

}