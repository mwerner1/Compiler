package edu.calpoly.mwerner.compiler;

public class Loadai extends Instruction
{
	private String instrName = "loadai";
	private Register srcReg1;
	private Immediate imm;
	private Register targetReg;
	private String strImm;

	public Loadai(Register srcReg1, Immediate imm, Register targetReg)
	{
		this.srcReg1 = srcReg1;
		this.imm = imm;
		this.targetReg = targetReg;
	}
	
	public Loadai(Register srcReg1, String imm, Register targetReg)
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