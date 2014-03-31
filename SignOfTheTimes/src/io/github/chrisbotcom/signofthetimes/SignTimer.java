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

/**
 * 
 * @author Chrisbotcom
 *
 */
public class SignTimer {

		private Integer x;
		private Integer y;
		private Integer z;
		private String world;
		private String timer;
		private Integer hours;
		private Integer minutes;
		private Integer seconds;
		private String format;
		
		/**
		 * Clear and initialize timer.
		 */
		public void clear() {
			this.x = 0;
			this.y = 0;
			this.z = 0;
			this.world = "";
			this.timer = "";
			this.hours = 0;
			this.minutes = 0;
			this.seconds = 0;
			this.format = "";
		}
		
		/**
		 * Get the X coordinate of the sign.
		 * @return Integer
		 */
		public Integer getX() {
			return x;
		}
		
		/**
		 * Set the X coordinate of the sign.
		 * @param x - Integer
		 */
		public void setX(Integer x) {
			this.x = x;
		}
		
		/**
		 * Get the Y coordinate of the sign.
		 * @return Integer
		 */
		public Integer getY() {
			return y;
		}
		
		/**
		 * Set the Y coordinate of the sign.
		 * @param y - Integer
		 */
		public void setY(Integer y) {
			this.y = y;
		}
		
		/**
		 * Get the Z coordinate of the sign.
		 * @return Integer
		 */
		public Integer getZ() {
			return z;
		}
		
		/**
		 * Set the Z coordinate of the sign.
		 * @param z - Integer
		 */
		public void setZ(Integer z) {
			this.z = z;
		}
		
		/**
		 * Get the world of the sign.
		 * @return String
		 */
		public String getWorld() {
			return world;
		}
		
		/**
		 * Set the world of the sign.
		 * @param world - String
		 */
		public void setWorld(String world) {
			this.world = world;
		}

		/**
		 * Generate and return the ID of the sign. ID in the format X_Y_Z_World.
		 * @return String
		 */
		public String getId() {
			return x.toString() + "_" + y.toString() + "_" + z.toString() + "_" + world;
		}
		
		/**
		 * Parse and set X, Y, Z and World of the sign from a sign ID having format X_Y_Z_World.
		 * @param id - String
		 */
		public void setId(String id) {
			String[] _id = id.split("_");
			this.x = Integer.parseInt(_id[0]);
			this.y = Integer.parseInt(_id[1]);
			this.x = Integer.parseInt(_id[2]);
			this.world = _id[3];
		}

		/**
		 * Return to string value of sign's timer using the format specified using setFormat().
		 * @return String
		 */
		public String getTimer() {
			return timer;
		}

		/**
		 * Parse and set the timer from string using the format specified using setFormat(). 
		 * @param timer - String
		 */
		public void setTimer(String timer) {
			this.timer = timer;
		}

		/**
		 * Get hours.
		 * @return Integer
		 */
		public Integer getHours() {
			return hours;
		}

		/**
		 * Set hours.
		 * @param hours - Integer
		 */
		public void setHours(Integer hours) {
			this.hours = hours;
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
			
			return "";			
		}

		/**
		 * Decrement timer by 1 second. Return timer in format specified by setFormat().
		 * @return String
		 */
		public String decrement() {
			
			return "";			
		}

		/**
		 * Get time format. HH:MM:SS
		 * @return String
		 */
		public String getFormat() {
			return format;
		}

		/**
		 * Set time format. HH:MM:SS
		 * @param format - String
		 */
		public void setFormat(String format) {
			this.format = format;
		}
}
