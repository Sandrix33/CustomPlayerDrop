package CustomPlayerDrop.Sandrix.Dev.Config;

import java.util.ArrayList;

public class Items {
	
	String mat;
	int count;
	String name;
    ArrayList<String> lore = new ArrayList<String>();
	ArrayList<String> enchant = new ArrayList<String>();
	int DropRate;
	
	public Items(String mat, int count, String name,ArrayList<String> lore, ArrayList<String> enchant,int DropRate){
		
		this.mat = mat;
		this.count = count;
		this.name = name;
		this.lore = lore;
		this.enchant = enchant;
		this.DropRate = DropRate;
		
	}
	
	public String getMat() {
		return mat;
	}
	
	public int getCount() {
		return count;
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<String> getLore(){
		return lore;
	}
	
	public ArrayList<String> getEnchant(){
		return lore;
	}
	
	public int getRate() {
		return DropRate;
	}
	
}
