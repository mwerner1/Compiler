package edu.calpoly.mwerner.compiler;

public class Pushq extends Instruction
{
	private String instrName = "pushq";
	private Register reg;
	
	public Pushq(Register reg)
	{
		this.reg = reg;
	}
	
	public String toString()
	{
		return (instrName + " " + reg.toString());
	}
}
