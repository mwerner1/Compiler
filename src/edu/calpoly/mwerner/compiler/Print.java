package edu.calpoly.mwerner.compiler;

public class Print extends Instruction
{
	private String instrName = "print";
	private Register targetReg;

	public Print(Register targetReg)
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