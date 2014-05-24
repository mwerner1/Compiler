package edu.calpoly.mwerner.compiler;

public class Addq extends Instruction
{
	private String instrName = "addq";
	private Register srcReg;
	private Register targetReg;
	private Immediate imm;
	
	public Addq(Register srcReg, Register targetReg)
	{
		this.srcReg = srcReg;
		this.targetReg = targetReg;
	}
	
	public Addq(Immediate imm, Register targetReg)
	{
		this.imm = imm;
		this.targetReg = targetReg;
	}
	
	public String toString()
	{
		if (imm != null)
		{
			return instrName + " " + imm.toString() + ", " + targetReg.toString();
		}
			
		return instrName + " " + srcReg.toString() + ", " + targetReg.toString();
	}
}
