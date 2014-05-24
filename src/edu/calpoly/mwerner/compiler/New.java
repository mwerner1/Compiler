package edu.calpoly.mwerner.compiler;

import java.util.Vector;

public class New extends Instruction
{
	private String instrName = "new";
	private Struct struct;
	private Vector<Type> structTypes;
	private Register targetReg;

	public New(Struct struct, Vector<Type> structTypes,Register targetReg)
	{
		this.struct = struct;
		this.structTypes = structTypes;
		this.targetReg = targetReg;
	}

	public String getInstr()
	{
		return instrName;
	}

	public String toString()
	{
		String structValsStr = "";
		
		for (int i=0; i<structTypes.size(); i++)
		{
			if (i < (structTypes.size() - 1))
			{
				structValsStr += Type.typeStr(structTypes.get(i)) + ", ";
			}
			else
			{
				structValsStr += Type.typeStr(structTypes.get(i));
			}
		}
		
		return instrName + " " + struct.structName()+ ", " + "[" + structValsStr + "], " + targetReg.toString();
	}

}