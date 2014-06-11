package edu.calpoly.mwerner.compiler;

public class Xori extends Instruction
{
	private String instrName = "xori";
	private Register srcReg1;
	private Immediate imm;
	private Register targetReg;

	public Xori(Register srcReg1, Immediate imm, Register targetReg)
	{
		this.srcReg1 = srcReg1;
		this.imm = imm;
		this.targetReg = targetReg;
	}

	public String getInstr()
	{
		return instrName;
	}

	public String toString()
	{
		return instrName + " " + srcReg1.toString() + ", " + imm.toString() + ", " + targetReg.toString();
	}

//	public Register getTarget()
//	{
//		return targetReg;
//	}
//	
//	public Register getSource()
//	{
//		return srcReg1;
//	}
	
	public Immediate getImmediate()
	{
		return imm;
	}
}