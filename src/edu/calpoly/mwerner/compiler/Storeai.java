package edu.calpoly.mwerner.compiler;

public class Storeai extends Instruction
{
	private String instrName = "storeai";
	private Register srcReg1;
	private Register targetReg;
	private Immediate imm;
	private String strImm;

	public Storeai(Register srcReg1, Register targetReg, Immediate imm)
	{
		this.srcReg1 = srcReg1;
		this.targetReg = targetReg;
		this.imm = imm;
	}
	
	public Storeai(Register srcReg1, Register targetReg, String imm)
	{
		this.srcReg1 = srcReg1;
		this.targetReg = targetReg;
		strImm = imm;
	}

	public String getInstr()
	{
		return instrName;
	}

	public String toString()
	{
		if (strImm != null)
		{
			return instrName + " " + srcReg1.toString() + ", " + targetReg.toString() + ", " + strImm;
		}
		
		return instrName + " " + srcReg1.toString() + ", " + targetReg.toString() + ", " + imm.toString();
	}

}