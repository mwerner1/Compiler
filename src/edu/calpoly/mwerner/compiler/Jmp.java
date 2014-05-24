package edu.calpoly.mwerner.compiler;

public class Jmp extends Instruction
{
	private String instrName = "jmp";
	private Label label1;

	public Jmp(Label label1)
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
