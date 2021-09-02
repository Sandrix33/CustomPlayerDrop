package CustomPlayerDrop.Sandrix.Dev.Config;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.configuration.file.YamlConfiguration;

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
	public static ArrayList<Items> items = new ArrayList<Items>();
	
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
		
		System.out.println("[CustomPlayerDrop] Loading messages...");
		permMessage = config.getString("permissionMessage").replaceAll("&", "§");
		PlayerKillonly = config.getBoolean("PlayerKillonly");
		DropPlayerInventory = config.getBoolean("DropPlayerInventory");
		DropPlayerHead = config.getBoolean("DropPlayerHead");
		
		System.out.println("[CustomPlayerDrop] Loading ExtraDrop list...");
		for(String item : config.getStringList("ExtraDrop")) {
			
			String mat = item.split(", ")[0];
			int count = Integer.parseInt(item.split(", ")[1]);
			
			Items it = new Items(mat, count);
			
			items.add(it);
			
		}
		
		System.out.println("[CustomPlayerDrop] Loading Complete!!");
		
		return;
		
	}
}
