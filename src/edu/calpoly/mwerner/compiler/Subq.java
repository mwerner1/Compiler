package edu.calpoly.mwerner.compiler;

public class Subq extends Instruction
{
	private String instrName = "subq";
	private Register srcReg;
	private Register targetReg;
	private Immediate imm;
	
	public Subq(Register srcReg, Register targetReg)
	{
		this.srcReg = srcReg;
		this.targetReg = targetReg;
	}
	
	public Subq(Immediate imm, Register targetReg)
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
