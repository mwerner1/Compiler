package edu.calpoly.mwerner.compiler;

public class Rsubi extends Instruction
{
	private String instrName = "rsubi";
	private Register srcReg1;
	private Immediate imm;
	private Register targetReg;

	public Rsubi(Register srcReg1, Immediate imm, Register targetReg)
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

}