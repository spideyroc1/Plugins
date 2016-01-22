package net.parkourworld.DoubleJump;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class Core extends JavaPlugin implements Listener{
	
	public void onJoin(PlayerJoinEvent event){
		Player p = event.getPlayer();
		if (p.hasPermission("doublejump.allow")){
			while (p.isOnline()){
				if (p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() != Material.AIR){
					p.setAllowFlight(true);
				}
			}
		}
	}
	public void onPlayerToggleFlightEvent(PlayerToggleFlightEvent event){
		Player p = event.getPlayer();
		if (p.hasPermission("doublejump.allow")){
			event.setCancelled(true);
			Vector v = p.getLocation().getDirection().setX(p.getLocation().getDirection().getX() + 1).setY(p.getLocation().getDirection().getX() + 0.5).setZ(p.getLocation().getDirection().getZ() + 1);
			p.setVelocity(v);
		}
	}

}

