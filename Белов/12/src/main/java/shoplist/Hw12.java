package shoplist;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.*;

import static shoplist.ShopItem.FIELD_LEN_MAX;
import static shoplist.ShopItem.ID_LEN;
import static util.Util12.*;

public class Hw12{
    public static void main(String[] args) {
        //setSeed(1);
        final int ITEMS_NUM = 10;
        Shop shop = new Shop(genItems(ITEMS_NUM));

        //.. используя случайный компаратор ..

        final int compIndex = giveRandom(shop.getComparators().size());
        Comparator<ShopItem> comparator = shop.getComparators().get(compIndex);
        System.out.println("Сортировка по " + Shop.compIndexToString(compIndex));

        final int SHOP_ITEMS_NUM_FOR_PRINTING = 5;
        setFormatPrinting(SHOP_ITEMS_NUM_FOR_PRINTING);
        ShopItem.printfHeader();
        shop.getShopItems().stream()//Выведите все товары из списка
                .sorted(comparator)//отсортированную
                .forEach(ShopItem::printf);
    }
    private static void setFormatPrinting(final int SHOP_ITEMS_NUM_FOR_PRINTING){
        StringBuilder format = new StringBuilder();
        for(int i = 0; i<SHOP_ITEMS_NUM_FOR_PRINTING; i++){
            format.append("%-" + ShopItem.FIELD_LEN_MAX + "s ");
        }
        ShopItem.setPrintFormatHeader(format.toString());
        ShopItem.setPrintFormat(
                          "%-" + FIELD_LEN_MAX + "s "
                        + "%-" + FIELD_LEN_MAX + "s "
                        + "%-" + FIELD_LEN_MAX + "s "
                        + "%"  + FIELD_LEN_MAX + "s "
                        + "%"  + FIELD_LEN_MAX + "s ");
    }
    private static List<ShopItem> genItems( @SuppressWarnings("SameParameterValue")
            final int itemsNum){
        final List<ShopItem> shopItems = new ArrayList<>();
        final Map<String, String[]> goods = genGoods();
        for(int i=itemsNum; 0<=--i; ){
            final String id = RandomStringUtils.randomAlphanumeric(ID_LEN);
            final String category = (String) goods.keySet().toArray()[giveRandom(goods.size())];
            final String title = goods.get(category)[giveRandom(goods.get(category).length)];
            final double price = giveRandomInBound(10000, 100000)/100d;
            final int quantity = giveRandomInBound(10, 100);
            shopItems.add(new ShopItem(id, title, category, price, quantity));
        }
        return shopItems;
    }
    private static Map<String, String[]> genGoods(){
        final Map<String, String[]> goods = new HashMap<>();
        goods.put("Convenience", new String[]{"food", "drink",
                "laundry detergent", "toilet paper",
                "deodorant", "toothpaste"});
        goods.put("Consumer", new String[]{"microwave", "fridge",
                "t-shirt", "washing machine"});
        goods.put("Intermediate", new String[]{"copper", "coal",
                "iron", "oil", "gaz"});
        goods.put("Speciality", new String[]{"sports cars",
                "rare paintings", "high-spec laptops",
                "rare ornaments", "designer clothing"});
        return goods;
    }
}
/* output:
Сортировка по Остаток
ID         Категория  Наимено... Цена       Остаток
El4L1di... Consumer   microwave      174.19         10
WaNmp7C... Conveni... laundry...     705.98         18
PS22A0S... Interme... oil            859.85         24
2jawwhS... Speciality rare pa...     202.62         30
ObsBI3N... Interme... iron           924.43         40
novoSdY... Consumer   microwave      219.38         48
UsghlnH... Consumer   microwave      412.61         81
bte3brY... Conveni... food           397.54         84
KMJUqOb... Interme... oil            448.59         88
yywIIaS... Conveni... food           901.36         92
 */
