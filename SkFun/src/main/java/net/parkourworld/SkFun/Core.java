package net.parkourworld.SkFun;
import org.bukkit.plugin.java.JavaPlugin;

import ch.njol.skript.Skript;

public class Core extends JavaPlugin{
	public static JavaPlugin plugin;
	public void onEnable(){
		plugin = this;
		Skript.registerAddon(this);
	}
	public void onDisable(){
		plugin = null;
	}
	
}
