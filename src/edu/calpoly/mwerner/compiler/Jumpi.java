package edu.calpoly.mwerner.compiler;

public class Jumpi extends Instruction
{
	private String instrName = "jumpi";
	private Label label1;

	public Jumpi(Label label1)
	{
		this.label1 = label1;
	}

	public String getInstr()
	{
		return instrName;
	}

	public String toString()
	{
		return instrName + " " + label1.toString();
	}

}