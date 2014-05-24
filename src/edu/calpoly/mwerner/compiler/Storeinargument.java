package edu.calpoly.mwerner.compiler;

public class Storeinargument extends Instruction
{
	private String instrName = "storeinargument";
	private Register srcReg1;
	private Id id;
	private Immediate imm;

	public Storeinargument(Register srcReg1, Id id, Immediate imm)
	{
		this.srcReg1 = srcReg1;
		this.id = id;
		this.imm = imm;
	}

	public String getInstr()
	{
		return instrName;
	}

	public String toString()
	{
		return instrName + " " + srcReg1.toString() + ", " + id.toString() + ", " + imm.toString();
	}

}