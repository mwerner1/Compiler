package edu.calpoly.mwerner.compiler;

public class Call extends Instruction
{
	private String instrName = "call";
	private Label label1;
	private String funcStr;

	public Call(Label label1)
	{
		this.label1 = label1;
	}
	
	public Call(String str)
	{
		this.funcStr = str;
	}

	public String getInstr()
	{
		return instrName;
	}

	public String toString()
	{
		if (this.funcStr != null)
		{
			return instrName + " " + funcStr;
		}
		
		return instrName + " " + label1.toString();
	}

}