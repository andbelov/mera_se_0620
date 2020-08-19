package shoplist;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Shop {
    private final List<Comparator<ShopItem>> comparators = new ArrayList<>(4);
    private final List<ShopItem> shopItems;
    public Shop(final List<ShopItem> shopItems){
        this.shopItems = shopItems;
        addComparators();
    }
    public List<ShopItem> getShopItems(){return shopItems;}
    public List<Comparator<ShopItem>> getComparators(){return comparators;}
    private void addComparators(){
        comparators.add(ItemSorters::sortByCategory);
        comparators.add(ItemSorters::sortByTitle);
        comparators.add(ItemSorters::sortByPrice);
        comparators.add(ItemSorters::sortByQuantity);
    }
    public static String compIndexToString(final int compIndex){
        return switch(compIndex){
            case 0 -> "Категория";
            case 1 -> "Наименование";
            case 2 -> "Цена";
            case 3 -> "Остаток";
            default -> throw new IllegalStateException("Unexpected value: " + compIndex);
        };
    }
}
