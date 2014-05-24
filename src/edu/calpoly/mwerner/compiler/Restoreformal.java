package edu.calpoly.mwerner.compiler;

public class Restoreformal extends Instruction
{
	private String instrName = "restoreformal";
	private Id id;
	private Immediate imm;

	public Restoreformal(Id id, Immediate imm)
	{
		this.id = id;
		this.imm = imm;
	}

	public String getInstr()
	{
		return instrName;
	}

	public String toString()
	{
		return instrName + " " + id.toString() + ", " + imm.toString();
	}

}