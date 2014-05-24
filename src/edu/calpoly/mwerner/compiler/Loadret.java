package edu.calpoly.mwerner.compiler;

public class Loadret extends Instruction
{
	private String instrName = "loadret";
	private Register targetReg;

	public Loadret(Register targetReg)
	{
		this.targetReg = targetReg;
	}

	public String getInstr()
	{
		return instrName;
	}

	public String toString()
	{
		return instrName + " " + targetReg.toString();
	}

}