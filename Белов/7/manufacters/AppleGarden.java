package manufacters;

import goods.food.FoodItem;
import goods.food.inventory.Apple;
import supervisors.Inventory;

import static util.Util7.giveRandom;

//AppleGarden - с методом fillShopWithApples. На вход передается коллекция(магазин)
//        которую нужно заполнить.
//        В эту коллекцию нужно добавить яблок разных цветов. Вывести на экран
public class AppleGarden extends Manufacture{
    public final Apple[] apples = new Apple[]{
            new Apple(Apple.Colors.GOLD.name(),  10, 10, Apple.Colors.GOLD),
            new Apple(Apple.Colors.COOPER.name(),11, 11, Apple.Colors.COOPER),
            new Apple(Apple.Colors.SILVER.name(),12, 12, Apple.Colors.SILVER),
    };
    public void fillShopWithApples(Inventory<? super FoodItem> inventory
            , final int min, final int max){
        for(int i = max; min <= --i; ){
            final Apple item = apples[giveRandom(apples.length)];
            inventory.add(item);
            printToShopAdded(inventory.getTitle(),item);
        }
    }
}
