package supervisors;

import goods.ShopItem;
import goods.electronics.ElectronicItem;
import goods.electronics.items.Refrigerator;
import goods.electronics.items.TV;
import goods.food.FoodItem;
import goods.food.items.Apple;
import goods.food.items.Bread;

import java.util.ArrayList;
import java.util.Collection;

import static util.Util7.giveRandomInBound;

public class Supervisor{
	public enum Shops{ //
		HIPPER_MARKET("Гипермаркет #1"),
		ELECTRA_SHOP("Магазин электронной продукции #2"),
		FOOD_SHOP("Продуктовый магазин #3"),
		NEAR_STALL("Ларек с яблоками у дома #4");
		private final String shopTitle;
		Shops(final String shopTitle){ this.shopTitle = shopTitle;}
		public String getShopTitle(){ return shopTitle; }
	}
	// (!) Since from the task description I have to create not a class(s),
	// but several Collections, class(s) and the enum are separated from Collection(s)
	public final Collection<ShopItem> HIPPER_MARKET_ITEMS = new ArrayList<>();
	public final Collection<ElectronicItem> ELECTRA_SHOP_ITEMS = new ArrayList<>();
	public final Collection<FoodItem>          FOOD_SHOP_ITEMS = new ArrayList<>();
	public final Collection<FoodItem>         NEAR_STALL_ITEMS = new ArrayList<FoodItem>();
	public int givePriceFor(final ShopItem item){
		if(item instanceof Apple) return giveRandomInBound(5, 10);
		if(item instanceof Bread) return giveRandomInBound(10, 50);
		if(item instanceof TV) return giveRandomInBound(10_000, 20_000);
		if(item instanceof Refrigerator) return giveRandomInBound(20_000, 100_000);
		return -1;
	}
}
