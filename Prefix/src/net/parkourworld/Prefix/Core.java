package net.parkourworld.Prefix;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin implements Listener{
	public void onEnable(){
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		saveDefaultConfig();
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event){
		Player player = event.getPlayer();
		StringBuilder str = new StringBuilder();
		for(String key : getConfig().getConfigurationSection("Prefixes").getKeys(false)){
			String value = getConfig().getString("Prefixes." + key);
			if (player.hasPermission("prefix." + key)){
				value = value.replaceAll("&", "§");
				str.append(value + ChatColor.RESET);
			}
		}
		String pref = str.toString();
		event.setCancelled(true);
		player.setDisplayName(pref + player.getName());
		event.setCancelled(false);
	}
}
