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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class SignOfTheTimes  extends JavaPlugin implements Listener {

	int seconds;
	int minutes;
	boolean rightClickPending = false;
	List<SignTimer>signs;
	Map<String, Interaction>interactions = new HashMap<String, Interaction>();
	Pattern regexPattern = Pattern.compile("\\[[0-5][0-9]:[0-5][0-9]\\]");
	Matcher regexMatcher;
	
	@Override
	public void onLoad() {

	}

	@Override
    public void onEnable() {
		
		this.getServer().getPluginManager().registerEvents(this, this);
		this.saveDefaultConfig();
		
		@SuppressWarnings("unchecked")
		List<String> configSigns = (List<String>) this.getConfig().getList("signs");
		if (configSigns != null) { 
			for (String configSign: configSigns) {
				signs.add(new SignTimer(configSign));
			}
		}
		
	    runSignTimer();
	}

	@Override
    public void onDisable() {
		
		this.getServer().getScheduler().cancelTasks(this);
    }
    
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
    	
    	//getLogger().info(event.getPlayer().getName() + " joined.");
    }
    
	private void runSignTimer() {
		
        this.getServer().getScheduler().cancelTasks(this);

        getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
        	
            public void run() {
            	            	
            	for (SignTimer signTimer: signs) {
            		signTimer.decrement();
            		Sign sign = (Sign) getServer().getWorld(signTimer.getWorld()).getBlockAt(signTimer.getLocation()).getState();
            		sign.setLine(signTimer.getLine(), signTimer.getTime());
            		sign.update();
            	}
            }
        }, 20L, 20L);
    }
	
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
    	
    	// If interaction pending and action is right-click.
    	if (interactions.containsKey(event.getPlayer()) && event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
        
    		Material block = event.getClickedBlock().getType();
   	    	
	        if (block == Material.SIGN_POST || block == Material.WALL_SIGN) {
	
	         	event.setCancelled(true);
	        	
	        	Sign newSign = (Sign) event.getClickedBlock().getState();
	         	Location location = newSign.getLocation();
	         	World world = newSign.getWorld();
	         	Interaction interaction = interactions.get(event.getPlayer());
	         	
	        	switch (interaction.command.toLowerCase()) {
	        	
		    	 	case "new":		// /sott new <name>
			        	// verify sign is not already named
			        	for (SignTimer sign: signs) {
			        		if (sign.getLocation().equals(location)) {
			        			sendMessage((CommandSender) event.getPlayer(), "Sign alreay exists as name: " + sign.getName());
			        			return;
			        		}
			        	}
			        	
			        	SignTimer signTimer = new SignTimer(interaction.name);
			        	signTimer.setLocation(location);
			        	signTimer.setWorld(world.getName());
			        	
			        	// find line with time on it.
			        	String[] lines = newSign.getLines();
			        	for (int i = 0; i < 5; i++) {
			        		if (i == 4) {
			        			sendMessage((CommandSender) event.getPlayer(), "Sign does not contain row with time in format [mm:ss].");
			        			return;
			        		}
			        		regexMatcher = regexPattern.matcher(lines[i]);
			        		if (regexMatcher.matches()) {
			        			signTimer.setLine(i);
			        			signTimer.setPreset(regexMatcher.group());
			        			break;
			        		}
			        	}
	
			        	break;
	
		    	 	case "auto":	// /sott auto [name]
		    	 		break;
		    	 	case "reset":	// /sott reset [name]
		    	 		break;
		    	 	case "start":	// /sott start [name]
		    	 		break;
		    	 	case "stop":	// /sott stop [name]
		    	 		break;
		    	 	case "preset":	// /sott preset MM:SS [name]
		    	 		break;
		    	 	case "remove":	// /sott remove [name]
		    	 		break;
		    	 	case "action":	// /sott action [name] <action>
		    	 		break;
		    	 	case "list":	// /sott list [name]
		    	 		break;
		    	 	case "tp":		// /sott tp <name>
		    	 		break;
		    	 	default:

	        	}
	        	
	        	event.getPlayer().sendMessage("[SignOfTheTimes] Sign added.");
	        }
    	}
    }
    
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[]) {
    	
    	// Debug to see message
    	sender.sendMessage(sender.getName());
    	
    	if (args.length == 0)
    		return false;
    	
    	switch (args[0].toLowerCase()) {
    	 	case "new":		// /sott new <name>
    	 		if (senderIsPlayer(sender)) {
    	 			if (args.length != 2) {
    	 				sendMessage(sender, "Required parameter <name> missing.");
    	 			} else {
    	 			interactions.put(sender.getName(), new Interaction(args[0], args[1], null));
    	 			sendMessage(sender, "Right-click new sign.");
    	 			}
    	 		}
    	 		break;
    	 	case "auto":	// /sott auto [name]
    	 		break;
    	 	case "reset":	// /sott reset [name]
    	 		break;
    	 	case "start":	// /sott start [name]
    	 		break;
    	 	case "stop":	// /sott stop [name]
    	 		break;
    	 	case "preset":	// /sott preset MM:SS [name]
    	 		break;
    	 	case "remove":	// /sott remove [name]
    	 		break;
    	 	case "action":	// /sott action [name] <action>
    	 		break;
    	 	case "list":	// /sott list [name]
    	 		break;
    	 	case "tp":		// /sott tp <name>
    	 		break;
    	 	default:
    			 return false;
    	}
    	
		return true;
    }
    
    public boolean senderIsPlayer(CommandSender sender) {
    	if (sender.getName().equalsIgnoreCase("CONSOLE")) {
    		sendMessage(sender, "Command may be used in-game only.");
    		return false;
    	}
    	return true;
    }
    
    public void sendMessage(CommandSender sender, String message) {
    	sender.sendMessage("[SignOfTheTimes] " + message);
    }
}
