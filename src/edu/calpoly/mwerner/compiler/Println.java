package edu.calpoly.mwerner.compiler;

public class Println extends Instruction
{
	private String instrName = "println";
	private Register targetReg;

	public Println(Register targetReg)
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