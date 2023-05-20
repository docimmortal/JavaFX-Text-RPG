package uber.rpg.entities;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import uber.rpg.entities.enums.Property;

public class UberItem implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String description;
	private String roomDescription;
	private UberItem insideItem;
	private String article;
	private Map<Property, Object> properties;
	
	public UberItem(String name, String description) {
		this.name = name;
		this.description = description;
		insideItem=null;
		article="a ";
		properties = new HashMap<>();
		roomDescription = description;
	}
	
	public UberItem(String name, String description, String roomDescription) {
		this.name = name;
		this.description = description;
		insideItem=null;
		article="a ";
		properties = new HashMap<>();
		this.roomDescription = roomDescription;
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
	
	public UberItem getInsideItem() {
		return insideItem;
	}

	public void setInsideItem(UberItem insideItem) {
		this.insideItem = insideItem;
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
