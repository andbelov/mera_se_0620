package supervisors;

import goods.Item;
import goods.electronics.ElectronicItem;
import goods.electronics.inventory.Refrigerator;
import goods.electronics.inventory.TV;
import goods.food.FoodItem;
import goods.food.inventory.Apple;
import goods.food.inventory.Bread;

import static util.Util7.giveRandomInBound;

public class Supervisor{
	public enum Shops{ //
		HIPPER_MARKET("«Гипермаркет #1»"),
		ELECTRA_SHOP("«Магазин электронной продукции #2»"),
		FOOD_SHOP("«Продуктовый магазин #3»"),
		NEAR_STALL("«Ларек с яблоками у дома #4»");
		private final String shopTitle;
		Shops(final String shopTitle){ this.shopTitle = shopTitle;}
		public String getShopTitle(){ return shopTitle; }
	}
	// (!) Since from the task description I have to create not a class(s),
	// but several Collections, class(s) and the enum are separated from Collection(s)
	public final Inventory<Item>          HIPPER_MARKET_INVENTORY = new Inventory<>(Shops.HIPPER_MARKET.getShopTitle());
	public final Inventory<ElectronicItem> ELECTRA_SHOP_INVENTORY = new Inventory<>(Shops.ELECTRA_SHOP .getShopTitle());
	public final Inventory<FoodItem>          FOOD_SHOP_INVENTORY = new Inventory<>(Shops.FOOD_SHOP    .getShopTitle());
	public final Inventory<FoodItem>         NEAR_STALL_INVENTORY =	new Inventory<>(Shops.NEAR_STALL   .getShopTitle());
	public int givePriceFor(final Item item){
		if(item instanceof Apple)        return giveRandomInBound(5, 10);
		if(item instanceof Bread)        return giveRandomInBound(10, 50);
		if(item instanceof TV)           return giveRandomInBound(10_000, 20_000);
		if(item instanceof Refrigerator) return giveRandomInBound(20_000, 100_000);
		return -1;
	}
}
