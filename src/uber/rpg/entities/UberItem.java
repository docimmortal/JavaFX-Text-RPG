package uber.rpg.entities;

import java.io.Serializable;

public class UberItem implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String description;
	private boolean edible;
	private boolean key;
	private UberItem insideItem;
	private String article;
	
	public UberItem(String name, String description) {
		super();
		this.name = name;
		this.description = description;
		edible=false;
		key=false;
		insideItem=null;
		article="a ";
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
	public void setEdible() {
		this.edible=true;
	}
	public boolean isEdible() {
		return edible;
	}
	
	public UberItem getInsideItem() {
		return insideItem;
	}

	public void setInsideItem(UberItem insideItem) {
		this.insideItem = insideItem;
	}

	public boolean isKey() {
		return key;
	}
	public void setKey() {
		this.key = true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(name);
		builder.append("\ndescription:");
		builder.append(description+"\n");
		if (edible) {
			builder.append("[edible]");
		}
		if (key) {
			builder.append("[key]");
		}
		if (insideItem != null) {
			builder.append("\nIs inside "+insideItem.getName());
		}
		return builder.toString();
	}

}
