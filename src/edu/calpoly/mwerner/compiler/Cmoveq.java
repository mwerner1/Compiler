package edu.calpoly.mwerner.compiler;

public class Cmoveq extends Instruction
{
	private String instrName = "cmoveq";
	private Immediate imm;
	private Register srcReg;
	private Register targetReg;

	public Cmoveq(Immediate imm, Register targetReg)
	{
		this.imm = imm;
		this.targetReg = targetReg;
	}
	
	public Cmoveq(Register srcReg, Register targetReg)
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
		if (imm != null)
		{
			return instrName + " $" + imm.toString() + ", " + targetReg.toString();
		}
		
		return instrName + " " + srcReg.toString() + ", " + targetReg.toString();
	}
	
	public Immediate getImmediate()
	{
		return imm;
	}
	
	public Register getSrc()
	{
		return srcReg;
	}
	
	public Register getTarget()
	{
		return targetReg;
	}
}
