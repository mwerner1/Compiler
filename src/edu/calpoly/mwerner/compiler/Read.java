package edu.calpoly.mwerner.compiler;

public class Read extends Instruction
{
	private String instrName = "read";
	private Register targetReg;

	public Read(Register targetReg)
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