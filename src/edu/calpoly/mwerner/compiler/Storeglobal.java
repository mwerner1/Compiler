package edu.calpoly.mwerner.compiler;

public class Storeglobal extends Instruction
{
	private String instrName = "storeglobal";
	private Register srcReg1;
	private Id id;

	public Storeglobal(Register srcReg1, Id id)
	{
		this.srcReg1 = srcReg1;
		this.id = id;
	}

	public String getInstr()
	{
		return instrName;
	}

	public String toString()
	{
		return instrName + " " + srcReg1.toString() + ", " + id.toString();
	}

}