package uber.rpg.entities.enums;

/* 
   USES - Can be used for potions (how many drinks of that potion are left)
   or arrows (how many arrows are left)
   		In UberItem: 
   			UberItem arrows = new UberItem("arrow","fine-crafted arrow");
   			arrows.addProperty(Property.USES, Integer.valueOf(40));
   		
   EDIBLE - Can be eaten. If value is integer, how many HP it heals.
   		In UberItem: 
   			UberItem apple = new UberItem("apple","red apple");
   			apple.addProperty(Property.EDIBLE, Integer.valueOf(5));
  
   CONSUMABLE - Is not edible but is used up when it is used
   
   WORTH - Worth something (in gold), if sold
   		In UberItem: 
   			UberItem gem = new UberItem("gem","red gem");
   			gem.addProperty(Property.WORTH, Integer.valueOf(10));
   			
   	LOCKED - Needs a key to unlock it.
   		In UberItem:
   			UberItem key = new UberItem("key","copper key");
   			key.addProperty(Property.KEY, null);
   			UberItem chest = new UberItem("chest","wooden chest");
   			chest.addProperty(Property.LOCKED, key); // UberItem key is used to unlock it
   			
   	WEIGHT - If you want to give an item a weight in pounds. This is optional (of course).
   		In UberItem:
   			UberItem goblet = new UberItem("goblet","copper goblet");
   			goblet.addProperty(Property.WEIGHT, Integer.valueOf(2)); // weighs 2 pounds
   			goblet.addProperty(Property.WORTH, Integer.valueOf(10)); // worth 10 gold
   			
*/
public enum Property {
	EDIBLE, KEY, CONSUMABLE, LOCKED, ARMOR, WEAPON, WORTH, USES, WEIGHT
}
