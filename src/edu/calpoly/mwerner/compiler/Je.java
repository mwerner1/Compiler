package edu.calpoly.mwerner.compiler;

public class Je extends Instruction
{
	private String instrName = "je";
	private Label label1;

	public Je(Label label1)
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
