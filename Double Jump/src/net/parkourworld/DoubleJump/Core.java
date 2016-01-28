package net.parkourworld.DoubleJump;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class Core extends JavaPlugin implements Listener{
	
	Map<UUID, Integer> runnableMap = new HashMap<UUID, Integer>();
	
	public void onJoin(PlayerJoinEvent event){
		final Player p = event.getPlayer();
		
		if (!runnableMap.containsKey(p.getUniqueId())){
			int taskID = this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){

				@Override
				public void run() {
						setFlightState(p);
					}
					
				}, 5, 5);
			runnableMap.put(p.getUniqueId(), taskID);
			}
		}
	
	public void onQuit(PlayerQuitEvent event){
		cancelRunnable(event.getPlayer());
	}
	
	public void setFlightState(Player p){
		GameMode c = p.getGameMode();
		if (c != GameMode.CREATIVE && p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() != Material.AIR){
			p.setAllowFlight(true);
		}
	}
	
	public void cancelRunnable(Player player){
		if (runnableMap.containsKey(player.getUniqueId())){
			Bukkit.getScheduler().cancelTask(runnableMap.get(player.getUniqueId()));
			runnableMap.remove(player.getUniqueId());
		}
	}
		
		

	public void onPlayerToggleFlightEvent(PlayerToggleFlightEvent event){
		Player p = event.getPlayer();
			event.setCancelled(true);
			Vector v = p.getLocation().getDirection().setX(p.getLocation().getDirection().getX() + 1).setY(p.getLocation().getDirection().getX() + 0.5).setZ(p.getLocation().getDirection().getZ() + 1);
			p.setVelocity(v);
		}
	}

