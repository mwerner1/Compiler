package edu.calpoly.mwerner.compiler;

public class Moveq extends Instruction
{
	private String instrName = "moveq";
	private Immediate imm;
	private Register srcReg, targetReg;

	public Moveq(Immediate imm, Register targetReg)
	{
		this.imm = imm;
		this.targetReg = targetReg;
	}
	
	public Moveq(Register srcReg, Register targetReg)
	{
		this.srcReg = srcReg;
		this.targetReg = targetReg;
	}

	public String getInstr()
	{
		return instrName;
	}

	public String toString()
	{
		if (srcReg != null)
		{
			return instrName + " " + srcReg.toString() + ", " + targetReg.toString();
		}
		return instrName + " " + imm.toString() + ", " + targetReg.toString();
	}
	
	public Immediate getImmediate()
	{
		return imm;
	}
	
//	public Register getTarget()
//	{
//		return targetReg;
//	}

}