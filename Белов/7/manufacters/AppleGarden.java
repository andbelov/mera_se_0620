package manufacters;

import goods.food.FoodItem;
import goods.food.items.Apple;

import java.util.Collection;

import static util.Util7.giveRandom;

//AppleGarden - с методом fillShopWithApples. На вход передается коллекция(магазин)
//        которую нужно заполнить.
//        В эту коллекцию нужно добавить яблок разных цветов. Вывести на экран
public class AppleGarden extends Manufacture{
    public final Apple[] apples = new Apple[]{
            new Apple(Apple.Colors.GOLD.name(),  100, 10, Apple.Colors.GOLD),
            new Apple(Apple.Colors.COOPER.name(),101, 11, Apple.Colors.COOPER),
            new Apple(Apple.Colors.SILVER.name(),102, 12, Apple.Colors.SILVER),
    };
    public void fillShopWithApples(Collection<? super FoodItem> items
            , final String shopTitle, final int min, final int max){
        for(int i = max; min <= --i; ){
            addFoodTo(items);
        }
    }
    private void addFoodTo(Collection<? super FoodItem> items){
        //switch(giveRandom(new Class[]{Apple.class, ...}.length)){ case 0 ->
        items.add(apples[giveRandom(apples.length)]);
        //}
    }
}
