package CustomPlayerDrop.Sandrix.Dev;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import CustomPlayerDrop.Sandrix.Dev.Config.Config;

public class Event implements Listener{

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		Player p = e.getEntity().getPlayer();
		Location l = p.getLocation();
		
		if(Config.plenabled) {
			
			if(p.hasPermission("CustomPlayerDrop.enabled")) {
				
				if(Config.PlayerKillonly) {
					if(e.getEntity().getKiller() instanceof Player) {
						if(!Config.DropPlayerInventory) {
							e.getDrops().clear();
						}
						if(Config.DropPlayerHead) {
							DropPlayerHead(p, l);
						}
						DropExtraDrop(p, l);
						
						
						
					}else {
						return;
					}
					
				}
				else {
					if(!Config.DropPlayerInventory) {
						e.getDrops().clear();
					}
					if(Config.DropPlayerHead) {
						DropPlayerHead(p, l);
					}
					DropExtraDrop(p, l);
					
					
					
				}

				
			}
			
		}
		
	}
	
	public void DropPlayerHead(Player p, Location l){
		
		boolean NewVersion = Arrays.stream(Material.values()).map(Material::name).collect(Collectors.toList()).contains("PLAYER_HEAD");
		
		Material type = Material.matchMaterial(NewVersion ? "PLAYER_HEAD" : "SKULL_ITEM");
		ItemStack item = new ItemStack(type, 1);
		
		if(!NewVersion)
			item.setDurability((short)3);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setOwner(p.getName());
		
		item.setItemMeta(meta);
		
		p.getWorld().dropItemNaturally(l, item);
		
	}
	
	public void DropExtraDrop(Player p, Location l) {

		for(int i=0; i<Config.items.size();i++) {	
			
			ItemStack item = new ItemStack(Material.valueOf(Config.items.get(i).getMat()), Config.items.get(i).getCount());
			
			p.getWorld().dropItemNaturally(l, item);
		}
	
	}
}
