package manufacters;

import goods.ShopItem;

import java.util.Collection;

public abstract class Manufacture{
/* (!) These functions actually were located in their initial classes,
 but i created this abstract Manufacture class, so the classes extend it */
/*
    public void fillShopWithApplesALTER(Collection<ShopItem> items
            , final int min, final int max, final String store, final String goods){
        for(int i=max; min<=--i; ){
            items.add(apples[giveRandom(apples.length)]);
        }
        items.forEach(item -> System.out.println("В "
                + store + " добавлены " + goods + ": "
                + item.toString()));
    }
    public static void fillShopWithElectronicGoodsALTER(Collection<? super ElectronicItem> items
            , final int min, final int max){
        for(int i=max; min<=--i; ){
            switch(getRandom(new Class[]{Refrigerator.class, TV.class}.length)){
                case 0 -> items.add(refrigerators[giveRandom(refrigerators.length)]);
                case 1 -> items.add(tvs[giveRandom(tvs.length)]);
            }
        }
        items.forEach(item ->
                System.out.println("Электронная фабрика отправила в магаз " + item.toString())
        );
    }
 */
/*
    public void fillShopWithFood(Collection<? super FoodItem> items
            , final String shopTitle, final int min, final int max
    ){
        for(int i=max; min<=--i; ){
            switch(giveRandom(new Class[]{Apple.class, Bread.class}.length)){
                case 0 -> items.add(FoodFactory.apples[giveRandom(FoodFactory.apples.length)]);
                case 1 -> items.add(FoodFactory.[
                        giveRandom(manufacters.ElectronicFabric.tvs.length)]);
            }
        }
    }
    public static void fillShopWithApples(Collection<? super Apple> items
            , final String shopTitle, final int min, final int max){
        fillShopWith(items, shopTitle, Apple.getType(), (new AppleGarden()).getItems()
                , min, max);
    }
    public static <ItemType> void fillShopWith(Collection<ItemType> items
            , final String shopTitle, final String itemType
            , final List<ItemType> array, final int min, final int max){
        for(int i=max; min<=--i; ){
            items.add(array.get(giveRandom(array.size())));
        }
        printToShopAdded(items, shopTitle, itemType);
    }
    public static void fillShopWith(Collection<? extends ShopItem> items
            , final String shopTitle, final String itemType
            , Collection<ShopItem> toAdd){
        items.addAll(toAdd);
        printToShopAdded(items, shopTitle, itemType);
    }
*/
    private static void printToShopAdded(Collection<? extends ShopItem> shop
            , final String shopTitle, final String itemType){
        shop.forEach(item -> System.out.println(
                "В " + shopTitle + " добавлены " + itemType + ": "
                        + item.toString()));
    }
}
