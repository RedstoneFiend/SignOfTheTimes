/* 
 * SignOfTheTimes - Sign timer plugin for Bukkit
 * Copyright (C) 2014 Chris Courson http://www.github.com/Chrisbotcom
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/gpl-3.0.html.
 */

package io.github.chrisbotcom.signofthetimes;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class SignOfTheTimes  extends JavaPlugin implements Listener {

	Scoreboard scoreboard1;
	Objective objective1;

	@Override
	public void onLoad() {

	}

	@Override
    public void onEnable() {
		
		this.getServer().getPluginManager().registerEvents(this, this);
		
		ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
		scoreboard1 = scoreboardManager.getNewScoreboard();
		objective1 = scoreboard1.registerNewObjective("test", "dummy");
		objective1.setDisplaySlot(DisplaySlot.SIDEBAR);
		objective1.setDisplayName("Time Remaining"); // 32 characters max.
		
	    Score minutes = objective1.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + "Minutes:"));
	    minutes.setScore(0);
	    Score seconds = objective1.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + "Seconds:"));
	    seconds.setScore(0);

	    runSignTimer();
	}

	@Override
    public void onDisable() {
		this.getServer().getScheduler().cancelTasks(this);
    }
    
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
    	
    	event.getPlayer().setScoreboard(scoreboard1);
		
		getLogger().info(event.getPlayer().getName() + " joined.");
    }
    
	private void runSignTimer() {
        this.getServer().getScheduler().cancelTasks(this);

        getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            public void run() {
            	
            	int seconds = objective1.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + "Seconds:")).getScore();
            	int minutes = objective1.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + "Minutes:")).getScore();

            	if (minutes + seconds == 0)
            		minutes = 5;
            	else
            		if (seconds == 0) {
            			seconds = 59;
            			minutes--;
            		}
            		else
            			seconds--;
            			
            	objective1.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + "Seconds:")).setScore(seconds);
            	objective1.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + "Minutes:")).setScore(minutes);
            	
            }
        }, 20L, 20L);
    }
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[]) {
    	
		return false;
    }
}
