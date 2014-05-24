package edu.calpoly.mwerner.compiler;

public class Movq extends Instruction
{
	private String instrName = "movq";
	private String var;
	private Register srcReg;
	private Register targetReg;
	private Immediate imm;
	private Immediate disp;
	private boolean isStore = false;
	
	public Movq(Register srcReg, Register targetReg)
	{
		this.srcReg = srcReg;
		this.targetReg = targetReg;
	}
	
	public Movq(Immediate imm, Register targetReg)
	{
		this.imm = imm;
		this.targetReg = targetReg;
	}
	
	public Movq(Immediate disp, Register srcReg, Register targetReg)
	{
		this.disp = disp;
		this.srcReg = srcReg;
		this.targetReg = targetReg;
	}
	
	public Movq(String var, Register srcReg, Register targetReg)
	{
		this.var = var;
		this.srcReg = srcReg;
		this.targetReg = targetReg;
	}
	
	public Movq(Register srcReg, String var, Register targetReg)
	{
		this.srcReg = srcReg;
		this.var = var;
		this.targetReg = targetReg;
		isStore = true;
	}
	
	public Movq(Register srcReg, Immediate disp, Register targetReg)
	{
		this.srcReg = srcReg;
		this.disp = disp;
		this.targetReg = targetReg;
		isStore = true;
	}
	
	public String toString()
	{
		if (var != null)
		{
			if (isStore)
			{
				return instrName + " " + srcReg.toString() + ", " + var + "(" + targetReg.toString() + ")";
			}
			return instrName + " " + var + "(" + srcReg.toString() + "), " + targetReg.toString();
		}
		else if (disp != null)
		{
			if (isStore)
			{
				return instrName + " " + srcReg.toString() + ", $" + disp.toString() + "(" + targetReg.toString() + ")";
			}
			return instrName + " $" + disp.toString() + "(" + srcReg.toString() + "), " + targetReg.toString();
		}
		else if (imm != null)
		{
			return instrName + " $" + imm.toString() + ", " + targetReg.toString();
		}
		else
		{
			return instrName + " " + srcReg.toString() + ", " + targetReg.toString();
		}
		
		
		
	}
}
