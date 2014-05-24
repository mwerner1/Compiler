package edu.calpoly.mwerner.compiler;

public class Loadglobal extends Instruction
{
	private String instrName = "loadglobal";
	private Id id;
	private Register targetReg;

	public Loadglobal(Id id, Register targetReg)
	{
		this.id = id;
		this.targetReg = targetReg;
	}

	public String getInstr()
	{
		return instrName;
	}

	public String toString()
	{
		return instrName + " " + id.toString() + ", " + targetReg.toString();
	}

}