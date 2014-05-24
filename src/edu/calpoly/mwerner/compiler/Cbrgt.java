package edu.calpoly.mwerner.compiler;

public class Cbrgt extends Instruction
{
	private String instrName = "cbrgt";
	private Register condCodeReg;
	private Label label1;
	private Label label2;

	public Cbrgt(Register condCodeReg, Label label1, Label label2)
	{
		this.condCodeReg = condCodeReg;
		this.label1 = label1;
		this.label2 = label2;
	}

	public String getInstr()
	{
		return instrName;
	}

	public String toString()
	{
		return instrName + " " + condCodeReg.toString() + ", " + label1.toString() + ", " + label2.toString();
	}

	public Label getLabel(int index)
	{
		if (index == 1)
		{
			return label1;
		}
		
		return label2;
	}
}