package edu.calpoly.mwerner.compiler;

public class Register 
{
	private String reg;
	private String color;
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
	
	public void setColor(String color)
	{
		this.color = color;
	}
	
	public String toString()
	{
		if (color != null)
		{
			return color;
		}
		return reg;
	}
	
	public String getRegStr()
	{
		return reg;
	}
	
	public int getRegNum()
	{
		return this.regNum;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}
		
		if (obj instanceof Register)
		{
			if (reg.equals(((Register)obj).toString()))
			{
				return true;
			}
		}
		
		return false;
	}
}
