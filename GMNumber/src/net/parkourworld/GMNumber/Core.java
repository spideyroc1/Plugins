package net.parkourworld.GMNumber;

import org.bukkit.plugin.java.JavaPlugin;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class Core extends JavaPlugin{
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		Player player = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("gmnumber")){
			if (player.hasPermission("gmnumber.view")){
			if (args.length == 1){
				if (args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("spectator") || args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("survival")){
					int c = 0;
					String g = args[0].toUpperCase();
					GameMode gameMode = GameMode.valueOf(g);
					for (Player p : Bukkit.getOnlinePlayers()) {
						if (p.getGameMode().equals(gameMode)){
							c++;
						}
						player.sendMessage(ChatColor.AQUA + "There are " + ChatColor.RED + c + ChatColor.AQUA + " player(s) in " + args[0] + " mode.");
						return true;
					}
				}else{
					player.sendMessage(ChatColor.YELLOW + "That is the wrong arguments.");
					return true;
				}
					
				
			}else{
				player.sendMessage(ChatColor.YELLOW + "That is not enough arguments.");
				return true;
			}
			}else{
				player.sendMessage(ChatColor.YELLOW + "You don't have permission for that command.");
				return true;
			}
		}
		
		return false;
	}
}
