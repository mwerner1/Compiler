package edu.calpoly.mwerner.compiler;

public class Computeglobaladdress extends Instruction
{
	private String instrName = "computeglobaladdress";
	private Id id;
	private Register targetReg;

	public Computeglobaladdress(Id id, Register targetReg)
	{
		this.id = id;
		this.targetReg = targetReg;
	}

	public String getInstr()
	{
		return instrName;
	}

	public String toString()
	{
		return instrName + " " + id.toString() + ", " + targetReg.toString();
	}

}