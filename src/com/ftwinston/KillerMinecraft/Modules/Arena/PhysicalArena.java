package com.ftwinston.KillerMinecraft.Modules.Arena;

import org.bukkit.Location;

public class PhysicalArena
{
	private int xMin, xMax, zMin, zMax;
	String name;
	
	public PhysicalArena(int xMin, int xMax, int zMin, int zMax, String color)
	{
		this.xMin = xMin;
		this.xMax = xMax;
		this.zMin = zMin;
		this.zMax = zMax;
		this.name = color;
	}
	
	public boolean containsLocation(Location l)
	{
		return l.getBlockX() >= xMin && 
			   l.getBlockX() <= xMax &&
			   l.getBlockZ() >= zMin && 
			   l.getBlockZ() <= zMax;
	}
}