package edu.calpoly.mwerner.compiler;

public class Del extends Instruction
{
	private String instrName = "del";
	private Register reg;

	public Del(Register reg)
	{
		this.reg = reg;
	}

	public String getInstr()
	{
		return instrName;
	}

	public String toString()
	{
		return instrName + " " + reg.toString();
	}

}