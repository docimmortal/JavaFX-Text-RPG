package application.rpg.entities;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import uber.rpg.entities.enums.Property;

public class Item implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String description;
	private String roomDescription;
	private Item insideItem;
	private String article;
	private String filename;
	private int xLoc;
	private int yLoc;
	private Map<Property, Object> properties;
	
	public Item(String name, String description) {
		this.name = name;
		this.description = description;
		insideItem=null;
		article="a ";
		properties = new HashMap<>();
		roomDescription = description;
		filename="";
	}
	
	public Item(String name, String description, String filename, int xLoc, int yLoc) {
		this.name = name;
		this.description = description;
		insideItem=null;
		article="a ";
		properties = new HashMap<>();
		this.roomDescription = description;
		this.filename=filename;
		this.xLoc=xLoc;
		this.yLoc=yLoc;
	}
	
	public Item(String name, String description, String roomDescription) {
		this.name = name;
		this.description = description;
		insideItem=null;
		article="a ";
		properties = new HashMap<>();
		this.roomDescription = roomDescription;
		filename="";
	}
	
	public Item(String name, String description, String roomDescription, String filename, int xLoc, int yLoc) {
		this.name = name;
		this.description = description;
		insideItem=null;
		article="a ";
		properties = new HashMap<>();
		this.roomDescription = roomDescription;
		this.filename=filename;
		this.xLoc=xLoc;
		this.yLoc=yLoc;
	}
		
	public String getRoomDescription() {
		return roomDescription;
	}

	public void setRoomDescription(String roomDescription) {
		this.roomDescription = roomDescription;
	}

	public void addProperty(Property property, Object details) {
		properties.put(property, details);
	}
	
	public boolean hasProperty(String name) {
		Property p = Property.valueOf(name.toUpperCase());
		return properties.containsKey(p);
	}
	
	public Object getPropertyDetails(String name) {
		Property p = Property.valueOf(name.toUpperCase());
		return properties.get(p);
	}
	
	public void setArticle(String article) {
		this.article=article.trim()+" ";
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getArticleAndName() {
		return article+name;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Item getInsideItem() {
		return insideItem;
	}

	public void setInsideItem(Item insideItem) {
		this.insideItem = insideItem;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public int getxLoc() {
		return xLoc;
	}

	public void setxLoc(int xLoc) {
		this.xLoc = xLoc;
	}

	public int getyLoc() {
		return yLoc;
	}

	public void setyLoc(int yLoc) {
		this.yLoc = yLoc;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(name);
		builder.append("\ndescription:");
		builder.append(description+"\n");
		for (Property property: properties.keySet()) {
			builder.append("["+property+"]");
		}
		if (insideItem != null) {
			builder.append("\nIs inside "+insideItem.getName());
		}
		return builder.toString();
	}

}
