package CustomPlayerDrop.Sandrix.Dev.Config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import CustomPlayerDrop.Sandrix.Dev.Main;

public class Config {

	public static YamlConfiguration config;
	public static File dir = new File("plugins/CustomPlayerDrop");
	public static File configFile = new File("plugins/CustomPlayerDrop/config.yml");
	public static File example = new File("plugins/CustomPlayerDrop/DefaultConfig.yml");
	public static boolean plenabled;
	public static String permMessage;
	public static boolean PlayerKillonly;
	public static boolean DropPlayerInventory;
	public static boolean DropPlayerHead;
	public static String HeadName;
	public static List<String> HeadLore;
	public static int HeadDropRate;
	public static boolean SpecificWorldOnly;
	public static boolean XpOnKill;
	public static int XpGiveCount;
	public static boolean MoneyOnKill;
	public static int MoneyGiveCount;
	public static boolean EnableExtraDrop;
	public static boolean EnableExtraCommands;
	public static List<String> worlds = new ArrayList<String>();
	public static List<String> commands = new ArrayList<String>();
	public static ArrayList<ItemStack> items = new ArrayList<ItemStack>();
	public static ArrayList<Integer> rates = new ArrayList<Integer>();
	
	public static void SaveConfig() {
		try {
			config.save(configFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void CheckConfig() {
		
		if(!dir.exists()) {
			
			dir.mkdir();
			
		}
		
		Main.getPlugin(Main.class).saveResource("DefaultConfig.yml", true);

		if(!configFile.exists()) {
			Main.getPlugin(Main.class).saveResource("config.yml", true);
			

		}else {
			config = YamlConfiguration.loadConfiguration(configFile);
			if(config.getDouble("Version") != 1.4) {
				config.set("Version", 1.4);
				SaveConfig();
			}
			if(config.get("CustomPlayerDrop") == null) {
				config.set("CustomPlayerDrop", true);
				SaveConfig();
			}
			if(config.get("permissionMessage") == null) {
				config.set("permissionMessage", "&cYou are not allowed");
				SaveConfig();
			}
			if(config.get("PlayerKillonly") == null) {
				config.set("PlayerKillonly", false);
				SaveConfig();
			}
			if(config.get("DropPlayerInventory") == null) {
				config.set("DropPlayerInventory", true);
				SaveConfig();
			}
			if(config.get("DropPlayerHead") == null) {
				config.set("DropPlayerHead", true);
				SaveConfig();
			}
			if(config.get("HeadName") == null) {
				config.set("HeadName", "&6{player}'s head");
				SaveConfig();
			}
			if(config.get("HeadLore") == null) {
				List<String> list = new ArrayList<String>();
				list.add("&6{player}'s head lore");
				list.add("{player}'s head lore 2");
				list.add("&cKilled By {killer}");
				config.set("HeadLore", list);
				SaveConfig();
			}
			if(config.get("HeadDropRate") == null) {
				config.set("HeadDropRate",100);
				SaveConfig();
			}
			if(config.get("SpecificWorldOnly") == null) {
				config.set("SpecificWorldOnly",false);
				SaveConfig();
			}
			if(config.get("Worlds") == null) {
				List<String> list = new ArrayList<String>();
				list.add("world");
				list.add("world_nether");
				config.set("Worlds", list);
				SaveConfig();
			}
			if(config.get("XpOnKill") == null) {
				config.set("XpOnKill",false);	
				SaveConfig();
			}
			if(config.get("XpGiveCount") == null) {
				config.set("XpGiveCount", 100);
				SaveConfig();
			}
			if(config.get("MoneyOnKill") == null) {
				config.set("MoneyOnKill",false);
				SaveConfig();
			}
			if(config.get("MoneyGiveCount") == null){
				config.set("MoneyGiveCount", 100);
				SaveConfig();
			}
			if(config.get("EnableExtraCommands") == null) {
				config.set("EnableExtraCommands", false);
				SaveConfig();
			}
			if(config.get("ExtraCommands") == null) {
				List<String> list = new ArrayList<String>();
				list.add("eco take {player} 100");
				list.add("eco give {killer} 20");
				config.set("ExtraCommands", list);
				SaveConfig();
			}
			if(config.get("EnableExtraDrop") == null) {
				config.set("EnableExtraDrop", true);
				SaveConfig();
			}
			if(config.get("ExtraDrop") == null) {
				config.set("ExtraDrop.20.==", "org.bukkit.inventory.ItemStack");
				config.set("ExtraDrop.20.type", "STONE");
				config.set("ExtraDrop.20.amount", 1);
				config.set("ExtraDrop.20.meta.==",  "ItemMeta");
				config.set("ExtraDrop.20.meta.meta-type",  "UNSPECIFIC");
				config.set("ExtraDrop.20.meta.display-name",  "§6Sample Item");
				List<String> list = new ArrayList<String>();
				list.add("First line of lore");
				list.add("Second line of lore");
				list.add("§1Color §2support");
				config.set("ExtraDrop.20.meta.lore",  list);
				config.set("ExtraDrop.20.meta.enchants.DAMAGE_ALL",  2);
				config.set("ExtraDrop.20.meta.enchants.KNOCKBACK",  7);
				config.set("ExtraDrop.20.meta.enchants.FIRE_ASPECT",  1);


				SaveConfig();
			}

		}
		
		config = YamlConfiguration.loadConfiguration(configFile);
	
	}
	
	public static void Reload() {
		System.out.print("[CustomPlayerDrop] Reloading config...");
		items.clear();
		CheckConfig();
		loadConfig();
		System.out.print("[CustomPlayerDrop] Config reloaded!!!");
	}
	

	
	public static void loadConfig() {
		
		System.out.print("[CustomPlayerDrop] Loading config...");
		plenabled = config.getBoolean("CustomPlayerDrop");
		
		if(plenabled == false) {
			System.out.print("[CustomPlayerDrop] Plugin set to false!");
			return;
		}
		
		permMessage = config.getString("permissionMessage").replaceAll("&", "§");
		PlayerKillonly = config.getBoolean("PlayerKillonly");
		DropPlayerInventory = config.getBoolean("DropPlayerInventory");
		DropPlayerHead = config.getBoolean("DropPlayerHead");
		if(DropPlayerHead) {
			HeadName = config.getString("HeadName");
			HeadLore = config.getStringList("HeadLore");
		}
		HeadDropRate = config.getInt("HeadDropRate");
		SpecificWorldOnly = config.getBoolean("SpecificWorldOnly");
		if(SpecificWorldOnly)
			worlds.clear();
			worlds = config.getStringList("Worlds");
		EnableExtraCommands = config.getBoolean("EnableExtraCommands");
		if(EnableExtraCommands)
			commands.clear();
			commands = config.getStringList("ExtraCommands");
        XpOnKill = config.getBoolean("XpOnKill");
	    XpGiveCount = config.getInt("XpGiveCount");
		MoneyOnKill = config.getBoolean("MoneyOnKill");
		MoneyGiveCount = config.getInt("MoneyGiveCount");
		EnableExtraDrop = config.getBoolean("EnableExtraDrop");
		if(EnableExtraDrop)
			for (String s : config.getConfigurationSection("ExtraDrop").getKeys(false)) {
				  ItemStack item = config.getItemStack("ExtraDrop." + s);
				  items.add(item);
				  rates.add(Integer.valueOf(s));
			}
		System.out.print("[CustomPlayerDrop] Loading Complete!!");
		
		return;
		
	}
}
