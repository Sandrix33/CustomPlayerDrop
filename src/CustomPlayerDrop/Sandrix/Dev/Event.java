package CustomPlayerDrop.Sandrix.Dev;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
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
		Player k = e.getEntity().getKiller();
		Location l = p.getLocation();
		
		if(Config.plenabled) {
			
			if(p.hasPermission("CustomPlayerDrop.enabled")) {
				if(Config.SpecificWorldOnly) {
					
					if(Config.worlds.contains(p.getWorld().getName())) {
						if(Config.PlayerKillonly) {
							if(k instanceof Player) {
								if(!Config.DropPlayerInventory) {
									e.getDrops().clear();
								}
								if(Config.DropPlayerHead) {
									DropPlayerHead(p, l, k);
								}
								if(Config.EnableExtraDrop) {
									DropExtraDrop(p, l);
								}
								if(Config.XpOnKill) {
									e.setDroppedExp(Config.XpGiveCount);
								}
								if(Config.MoneyOnKill) {
									Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give "+k.getName()+" "+Config.MoneyGiveCount);
								}
							}else {
								return;
							}
							
						}
						else {
							if(!Config.DropPlayerInventory) {
								e.getDrops().clear();
							}
							if(Config.DropPlayerHead) {
								DropPlayerHead(p, l, k);
							}
							if(Config.EnableExtraDrop) {
								DropExtraDrop(p, l);
							}
							if(Config.XpOnKill) {
								e.setDroppedExp(Config.XpGiveCount);
							}
						}
					}
					
				}else {
					if(Config.PlayerKillonly) {
						if(k instanceof Player) {
							if(!Config.DropPlayerInventory) {
								e.getDrops().clear();
							}
							if(Config.DropPlayerHead) {
								DropPlayerHead(p, l, k);
							}
							if(Config.EnableExtraDrop) {
								DropExtraDrop(p, l);
							}
							if(Config.XpOnKill) {
								e.setDroppedExp(Config.XpGiveCount);
							}
							if(Config.MoneyOnKill) {
								Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give "+k.getName()+" "+Config.MoneyGiveCount);
							}
						}else {
							return;
						}
						
					}
					else {
						if(!Config.DropPlayerInventory) {
							e.getDrops().clear();
						}
						if(Config.DropPlayerHead) {
							DropPlayerHead(p, l, k);
						}
						if(Config.EnableExtraDrop) {
							DropExtraDrop(p, l);
						}
						if(Config.XpOnKill) {
							e.setDroppedExp(Config.XpGiveCount);
						}
					}
				}
			}
			
		}
		
	}
	
	public void DropPlayerHead(Player p, Location l, Player k){
		
		boolean NewVersion = Arrays.stream(Material.values()).map(Material::name).collect(Collectors.toList()).contains("PLAYER_HEAD");
		
		Material type = Material.matchMaterial(NewVersion ? "PLAYER_HEAD" : "SKULL_ITEM");
		ItemStack item = new ItemStack(type, 1);
		
		if(!NewVersion)
			item.setDurability((short)3);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setOwner(p.getName());
		if(k==null) {
			meta.setDisplayName(Config.HeadName.replaceAll("&", "§").replaceAll("\\{player}", p.getName()).replaceAll("\\{killer}", "CONSOLE"));

		}
		else {
			meta.setDisplayName(Config.HeadName.replaceAll("&", "§").replaceAll("\\{player}", p.getName()).replaceAll("\\{killer}", k.getName()));

		}		
		for(int i=0; i<Config.HeadLore.size(); i++) {
			
			
			if(k==null) {
				Config.HeadLore.set(i, Config.HeadLore.get(i).replaceAll("&", "§").replaceAll("\\{player}", p.getName()).replaceAll("\\{killer}","CONSOLE"));

			}
			else {
				Config.HeadLore.set(i, Config.HeadLore.get(i).replaceAll("&", "§").replaceAll("\\{player}", p.getName()).replaceAll("\\{killer}", k.getName()));

			}			
		}
		
		meta.setLore(Config.HeadLore);
		
		item.setItemMeta(meta);
		
		int v = (int) (Math.random() * 101);
		
		if(v<=Config.HeadDropRate)
			p.getWorld().dropItemNaturally(l, item);
		
	}
	
	public void DropExtraDrop(Player p, Location l) {
		
		int v = (int) (Math.random() * 101);
		
		
		for(int i=0; i<Config.items.size();i++) {
			if(v<=Config.rates.get(i))
				p.getWorld().dropItemNaturally(l, Config.items.get(i));
		}
	
	}
}
