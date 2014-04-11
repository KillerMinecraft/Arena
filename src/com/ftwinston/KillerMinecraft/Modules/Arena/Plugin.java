package com.ftwinston.KillerMinecraft.Modules.Arena;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.material.SpawnEgg;

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