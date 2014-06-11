package edu.calpoly.mwerner.compiler;

public class Movge extends Instruction
{
	private String instrName = "movge";
	private Immediate imm;
	private Register srcReg, targetReg;

	public Movge(Immediate imm, Register targetReg)
	{
		this.imm = imm;
		this.targetReg = targetReg;
	}
	
	public Movge(Register srcReg, Register targetReg)
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