package com.ftwinston.KillerMinecraft.Modules.Arena;

import org.bukkit.Material;

import com.ftwinston.KillerMinecraft.GameMode;
import com.ftwinston.KillerMinecraft.GameModePlugin;

public class Plugin extends GameModePlugin
{
	@Override
	public Material getMenuIcon() { return Material.IRON_HELMET; }
	
	@Override
	public String[] getDescriptionText() { return new String[] {"Contains several quick PVP and survival", "game modes. Can run several at once."}; }
	
	@Override
	public GameMode createInstance()
	{
		return new Arena();
	}
}