package CustomPlayerDrop.Sandrix.Dev.Config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import CustomPlayerDrop.Sandrix.Dev.Main;

public class Config {

	public static YamlConfiguration config;
	public static File dir = new File("plugins/CustomPlayerDrop");
	public static File configFile = new File("plugins/CustomPlayerDrop/config.yml");
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
	public static ArrayList<String> worlds = new ArrayList<String>();
	public static ArrayList<ItemStack> items = new ArrayList<ItemStack>();
	public static ArrayList<Integer> rates = new ArrayList<Integer>();
	
	public static void CheckConfig() {
		
		if(!dir.exists()) {
			
			dir.mkdir();
			
		}
		
		if(!configFile.exists()) {
			Main.getPlugin(Main.class).saveResource("config.yml", true);
			
		}
		
		config = YamlConfiguration.loadConfiguration(configFile);
		
	}
	
	public static void Reload() {
		System.out.println("[CustomPlayerDrop] Reloading config...");
		items.clear();
		CheckConfig();
		loadConfig();
		System.out.println("[CustomPlayerDrop] Config reloaded!!!");
	}
	

	
	public static void loadConfig() {
		
		System.out.println("[CustomPlayerDrop] Loading config...");
		plenabled = config.getBoolean("CustomPlayerDrop");
		
		if(plenabled == false) {
			System.out.println("[CustomPlayerDrop] Plugin set to false!");
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
			for(String world : config.getStringList("Worlds")) {
				worlds.add(world);
			}
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
		System.out.println("[CustomPlayerDrop] Loading Complete!!");
		
		return;
		
	}
}
