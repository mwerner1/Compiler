package edu.calpoly.mwerner.compiler;

public class Read extends Instruction
{
	private String instrName = "read";
	private Register targetReg;
	private String var;

	public Read(Register targetReg)
	{
		this.targetReg = targetReg;
	}
	
	public Read(String var)
	{
		this.var = var;
	}

	public String getInstr()
	{
		return instrName;
	}

	public String toString()
	{
		if (targetReg != null)
		{
			return instrName + " " + targetReg.toString();
		}
		
		return instrName + " " + var;
	}

}