package com.ftwinston.KillerMinecraft.Modules.Arena;

import com.ftwinston.KillerMinecraft.GameMode;
import com.ftwinston.KillerMinecraft.Option;
import com.ftwinston.KillerMinecraft.Configuration.TeamInfo;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World.Environment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;

public class Arena extends GameMode
{
	@Override
	public int getMinPlayers() { return 1; }
	
	@Override
	public Option[] setupOptions() { return new Option[] { }; }

	@Override
	public List<String> getHelpMessages(TeamInfo team)
	{
		LinkedList<String> messages = new LinkedList<String>();
		messages.add("Welcome to the arena! There are 4 arenas; red, green, blue & yellow. Each can play its own game, independent of the others.");
		messages.add("The game mode for each arena can be selected using buttons before you start it.");
		messages.add("PVP games last until all players but one are dead. Survival games last until all players are dead.");
		return messages;
	}
	
	@Override
	public boolean allowWorldGeneratorSelection() { return false; }
	
	@Override
	public Environment[] getWorldsToGenerate() { return new Environment[] { Environment.THE_END }; }
	
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