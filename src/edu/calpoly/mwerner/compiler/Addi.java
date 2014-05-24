package edu.calpoly.mwerner.compiler;

public class Addi extends Instruction
{
	private String instrName = "addi";
	private Register srcReg1;
	private Immediate imm;
	private String strImm;
	private Register targetReg;

	public Addi(Register srcReg1, Immediate imm, Register targetReg)
	{
		this.srcReg1 = srcReg1;
		this.imm = imm;
		this.targetReg = targetReg;
	}
	
	public Addi(Register srcReg1, String imm, Register targetReg)
	{
		this.srcReg1 = srcReg1;
		strImm = imm;
		this.targetReg = targetReg;
	}

	public String getInstr()
	{
		return instrName;
	}

	public String toString()
	{
		if (strImm != null)
		{
			return instrName + " " + srcReg1.toString() + ", " + strImm + ", " + targetReg.toString();
		}
		
		return instrName + " " + srcReg1.toString() + ", " + imm.toString() + ", " + targetReg.toString();
	}

}