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
import org.bukkit.Location;

/**
 * 
 * @author Chrisbotcom
 *
 */
public class SignTimer {

		private Location location;
		private String preset;
		private Integer minutes = 0;
		private Integer seconds = 0;
		private String name;
		private Integer line;
		private String command;
		private boolean auto;
				
		public SignTimer(String name) {
			this.name = name;
		}
		
		public Location getLocation() {
			return location;
		}

		public void setLocation(Location location) {
			this.location = location;
		}

		/**
		 * Get the X coordinate of the sign.
		 * @return Double
		 */
		public Double getX() {
			return this.location.getX();
		}
		
		/**
		 * Set the X coordinate of the sign.
		 * @param x - Double
		 */
		public void setX(Double x) {
			this.location.setX(x);
		}
		
		/**
		 * Get the Y coordinate of the sign.
		 * @return Double
		 */
		public Double getY() {
			return this.location.getX();
		}
		
		/**
		 * Set the Y coordinate of the sign.
		 * @param y - Double
		 */
		public void setY(Double y) {
			this.location.setY(y);;
		}
		
		/**
		 * Get the Z coordinate of the sign.
		 * @return Double
		 */
		public Double getZ() {
			return this.location.getZ();
		}
		
		/**
		 * Set the Z coordinate of the sign.
		 * @param z - Double
		 */
		public void setZ(Double z) {
			this.location.setZ(z);
		}
		
		/**
		 * Get the world of the sign.
		 * @return String
		 */
		public String getWorld() {
			return this.location.getWorld().getName();
		}
		
		/**
		 * Set the world of the sign.
		 * @param world - String
		 */
		public void setWorld(String world) {
			this.location.setWorld(Bukkit.getServer().getWorld(world));
		}

		/**
		 * Generate and return the serialized value of the sign in the format: name_X_Y_Z_World_line_preset_command.
		 * @return String
		 */
		public String toString() {
			return this.name + "|" +
				   Double.toString(this.location.getX()) + "|" + 
				   Double.toString(this.location.getY()) + "|" + 
				   Double.toString(this.location.getZ()) + "|" + 
				   this.location.getWorld().getName() + "|" +
				   Integer.toString(this.line) + "|" +
				   this.preset + "|" +
				   this.command.replace(' ', '_');
		}
		
		/**
		 * Parse and set name, X, Y, Z, World, line, preset and command in the format: name_X_Y_Z_World_line_preset_command.
		 * @param id - String
		 */
		public void parse(String s) {
			String[] _id = s.split("|");
			this.name = _id[0];
			this.location.setX(Double.parseDouble(_id[1]));
			this.location.setY(Double.parseDouble(_id[2]));
			this.location.setZ(Double.parseDouble(_id[3]));
			this.location.setWorld(Bukkit.getServer().getWorld(_id[4]));
			this.line = Integer.parseInt(_id[5]);
			this.preset = _id[6];
			this.command = _id[7].replace('_', ' ');
		}

		/**
		 * Return to string value of sign's timer using the format specified using setFormat().
		 * @return String
		 */
		public String getPreset() {
			return preset;
		}

		/**
		 * Parse and set the timer from string using the format specified using setFormat(). 
		 * @param timer - String
		 */
		public void setPreset(String preset) {
			this.preset = preset;
			String[] time = preset.split(":");
			this.minutes = Integer.parseInt(time[0]);
			this.seconds = Integer.parseInt(time[1]);
		}

		/**
		 * Get minutes.
		 * @return Integer
		 */
		public Integer getMinutes() {
			return minutes;
		}

		/**
		 * Set minutes.
		 * @param minutes - Integer
		 */
		public void setMinutes(Integer minutes) {
			this.minutes = minutes;
		}

		/**
		 * Get seconds.
		 * @return Integer
		 */
		public Integer getSeconds() {
			return seconds;
		}

		/**
		 * Set seconds.
		 * @param seconds - Integer
		 */
		public void setSeconds(Integer seconds) {
			this.seconds = seconds;
		}
		
		/**
		 * Increment timer by 1 second. Return timer in format specified by setFormat().
		 * @return String
		 */
		public String increment() {
			seconds++;
			
        	if (seconds > 59) {
        		seconds = 0;
        		minutes++;
        	}
        	if (minutes > 59) {
        		minutes = 0;
        	}
        	return getTime();
		}

		/**
		 * Decrement timer by 1 second. Return timer in format specified by setFormat().
		 * @return String
		 */
		public String decrement() {
        	if (minutes + seconds == 0)
        		minutes = 5;
        	else
        		if (seconds == 0) {
        			seconds = 59;
        			minutes--;
        		}
        		else
        			seconds--;
        	return getTime();
		}
		
		public String getTime() {
			return String.format("[%02d:%02d]", minutes, seconds);
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getLine() {
			return line;
		}

		public void setLine(Integer line) {
			this.line = line;
		}

		public String getCommand() {
			return command;
		}

		public void setCommand(String command) {
			this.command = command;
		}

		public boolean isAuto() {
			return auto;
		}

		public void setAuto(boolean auto) {
			this.auto = auto;
		}
}
