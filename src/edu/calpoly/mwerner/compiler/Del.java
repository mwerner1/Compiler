package edu.calpoly.mwerner.compiler;

public class Del extends Instruction
{
	private String instrName = "del";
	private Register targetReg;

	public Del(Register targetReg)
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