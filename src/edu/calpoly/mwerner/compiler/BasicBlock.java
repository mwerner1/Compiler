package edu.calpoly.mwerner.compiler;

import java.util.Vector;

public class BasicBlock 
{
	private String label;
	private Vector<BasicBlock> successors;
	private Vector<BasicBlock> predecessors;
	private Vector<Instruction> instructions;
	private Vector<Instruction> assembly;
	
	public BasicBlock(String blockLabel)
	{
		label = blockLabel;
		successors = new Vector<BasicBlock>();
		predecessors = new Vector<BasicBlock>();
		instructions = new Vector<Instruction>();
		assembly = new Vector<Instruction>();
	}
	
	public void addSuccessor(BasicBlock block)
	{
		successors.add(block);
	}
	
	public void addPredecessor(BasicBlock block)
	{
		predecessors.add(block);
	}
	
	public int numSuccessors()
	{
		return successors.size();
	}
	
	public int numPredecessors()
	{
		return predecessors.size();
	}
	
	public String getLabel()
	{
		return label;
	}
	
	public BasicBlock getSuccessor(int index)
	{
		return successors.get(index);
	}
	
	public boolean equals(BasicBlock b)
	{
		return this.label.equals(b.getLabel());
	}
	
	public void addInstr(Instruction instr)
	{
		instructions.add(instr);
	}
	
	public void addAssembly(Instruction instr)
	{
		assembly.add(instr);
	}
	
	public Instruction getInstr(int index)
	{
		return instructions.get(index);
	}
	
	public Instruction getAssembly(int index)
	{
		return assembly.get(index);
	}
	
	public int numInstructions()
	{
		return instructions.size();
	}
	
	public int numAssemInstr()
	{
		return assembly.size();
	}
}
