package edu.calpoly.mwerner.compiler;

public class Register 
{
	private String reg;
	private int regNum;
	
	public Register(int regNum)
	{
		reg = "r" + Integer.toString(regNum);
		this.regNum = regNum;
	}
	
	public Register(String regName)
	{
		reg = regName;
	}
	
	public String toString()
	{
		return reg;
	}
	
	public int getRegNum()
	{
		return this.regNum;
	}
}
