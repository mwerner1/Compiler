package edu.calpoly.mwerner.compiler;

public class Compi extends Instruction
{
	private String instrName = "compi";
	private Register srcReg1;
	private Immediate imm;
	private Register condCodeReg;

	public Compi(Register srcReg1, Immediate imm, Register condCodeReg)
	{
		this.srcReg1 = srcReg1;
		this.imm = imm;
		this.condCodeReg = condCodeReg;
	}

	public String getInstr()
	{
		return instrName;
	}

	public String toString()
	{
		return instrName + " " + srcReg1.toString() + ", " + imm.toString() + ", " + condCodeReg.toString();
	}

}