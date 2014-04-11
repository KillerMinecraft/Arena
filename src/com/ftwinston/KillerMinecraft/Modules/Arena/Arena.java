package com.ftwinston.KillerMinecraft.Modules.Arena;

import java.util.List;

import com.ftwinston.KillerMinecraft.GameMode;
import com.ftwinston.KillerMinecraft.Helper;
import com.ftwinston.KillerMinecraft.KillerMinecraft;
import com.ftwinston.KillerMinecraft.Option;
import com.ftwinston.KillerMinecraft.PlayerFilter;
import com.ftwinston.KillerMinecraft.Configuration.ChoiceOption;
import com.ftwinston.KillerMinecraft.Configuration.TeamInfo;
import com.ftwinston.KillerMinecraft.Configuration.ToggleOption;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.Material;

public class Arena extends GameMode
{
	@Override
	public int getMinPlayers() { return 1; }
	
	@Override
	public Option[] setupOptions() { return new Option[] { }; }

	@Override
	public String getHelpMessage(int num, TeamInfo team)
	{
		switch ( num )
		{
			case 0:
				return "Welcome to the arena! There are 4 arenas; red, green, blue & yellow. Each can play its own game, independent of the others.";
			case 1:
				return "The game mode for each arena can be selected using buttons before you start it.";
			case 2:
				return "PVP games last until all players but one are dead. Survival games last until all players are dead.";
			default:
				return null;
		}
	}
	
	@Override
	public boolean allowWorldGeneratorSelection() { return false; }
	
	@Override
	public Environment[] getWorldsToGenerate() { return new Environment[] { Environment.END }; }
	
	@Override
	public boolean isLocationProtected(Location l, Player p)
	{
		// the central spawn area is always protected
		return l.getX() <= 16 && 
	           l.getX() > -16 &&
			   l.getZ() <= 16 &&
			   l.getZ() > 16;
	} 

	int gameUpdateProcessID = -1;
	Location spawn = null;
	PhysicalArena[] arenas;
	
	@Override
	public Location getSpawnLocation(Player player)
	{
		if ( spawn == null )
			spawn = new Location(getWorld(0), 0, 0, 0);
		return spawn;
	}
	
	@Override
	public void gameStarted()
	{
		arenas = new PhysicalArena[]
		{ 
			new PhysicalArena(-15, 16, 32, 64, "red"),
			new PhysicalArena(-15, 16, -63, -31, "yellow"),
			new PhysicalArena(32, 64, -15, 16, "green"),
			new PhysicalArena(-63, -31, -15, 16, "blue")
		};
		// gameUpdateProcessID = ...
	}
	
	@Override
	public void gameFinished()
	{
		// stop our scheduled processes
		if ( gameUpdateProcessID != -1 )
		{
			getPlugin().getServer().getScheduler().cancelTask(gameUpdateProcessID);
			gameUpdateProcessID = -1;
		}
	}
	
	@Override
	public void playerQuit(OfflinePlayer player)
	{
		playerKilledOrQuit(player);
	}
	
	void playerKilledOrQuit(OfflinePlayer player)
	{
		// check if this should end an arena game
	}

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onPlayerKilled(PlayerDeathEvent event)
	{	
		playerKilledOrQuit(event.getEntity());
	}
}