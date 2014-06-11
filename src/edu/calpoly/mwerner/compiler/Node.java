package edu.calpoly.mwerner.compiler;

import java.util.ArrayList;

public class Node 
{
	private String label;
	private String color;
	private ArrayList<Node> adjacencyList;
	
	public Node(String label)
	{
		this.label = label;
		this.adjacencyList = new ArrayList<Node>();
	}
	
	public void addNeighbor(Node neighbor)
	{
		adjacencyList.add(neighbor);
	}
	
	public void removeNeighbor(Node neighbor)
	{
		for (int i=0; i<adjacencyList.size(); i++)
		{
			if (adjacencyList.get(i).getLabel().equals(neighbor.getLabel()))
			{
				adjacencyList.remove(i);
			}
		}
	}
	
	public String getLabel()
	{
		return this.label;
	}
	
	public String getColor()
	{
		return this.color;
	}
	
	public boolean isNeighbor(Node neighbor)
	{
		for (int i=0; i<adjacencyList.size(); i++)
		{
			if (adjacencyList.get(i).getLabel().equals(neighbor.getLabel()))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public ArrayList<Node> getNeighbors()
	{
		return this.adjacencyList;
	}
	
	public void setColor(String color)
	{
		this.color = color;
	}
}
