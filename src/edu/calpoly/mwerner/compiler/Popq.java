package edu.calpoly.mwerner.compiler;

public class Popq extends Instruction
{
	private String instrName = "popq";
	private Register reg;
	
	public Popq(Register reg)
	{
		this.reg = reg;
	}
	
	public String toString()
	{
		return instrName + " " + reg.toString();
	}
}
