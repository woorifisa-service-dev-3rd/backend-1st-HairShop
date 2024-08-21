package haircutmaven.model;

import java.util.Arrays;

public class Hairstyle_menu{
	private String[] hairstyles;
	private int[] costs;
	private int[] style_ids;
	
	public Hairstyle_menu(int size){
		hairstyles=new String[size];
		costs=new int[size];
		style_ids=new int[size];
	}
	public int getStyle_ids(int index) {
		return style_ids[index];
	}
	public void setStyle_ids(int index, int style_id) {
		this.style_ids[index] = style_id;
	}
	public String getHairstyle(int index) {
		return hairstyles[index];
	}
	public void setHairstyle(int index, String hairstyle) {
		this.hairstyles[index] = hairstyle;
	}
	public int getCost(int index) {
		return costs[index];
	}
	public void setCost(int index, int cost) {
		this.costs[index] = cost;
	}
	public int getSize() {
        return hairstyles.length;
    }

	public String toString() {
        return "HairstyleMenu{" +"style_ids="+Arrays.toString(style_ids)+
                "hairstyles=" + Arrays.toString(hairstyles) +
                ", costs=" + Arrays.toString(costs) +
                '}';
    }
}
