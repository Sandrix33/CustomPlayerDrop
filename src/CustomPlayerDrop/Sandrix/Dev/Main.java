package CustomPlayerDrop.Sandrix.Dev;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import CustomPlayerDrop.Sandrix.Dev.Config.Config;
import CustomPlayerDrop.Sandrix.Dev.Config.Reload;

public class Main extends JavaPlugin{
	
	public void onEnable(){
	
		Config.CheckConfig();
		Config.loadConfig();
		Bukkit.getServer().getPluginManager().registerEvents(new Event(), this);
		getCommand("CustomPlayerDrop").setExecutor(new Reload());
		
	}
}
