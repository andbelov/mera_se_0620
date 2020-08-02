package manufacters;

import goods.food.FoodItem;
import goods.food.items.Apple;

import java.util.Collection;

import static util.Util7.giveRandom;

//FoodFactory - с методом fillShopWithFood.
// На вход передается коллекция(магазин) которую нужно заполнить.
//В эту коллекцию нужно добавить немного яблок и немного хлеба.
// Вывести на экран "В магазин добавлены яблоки: список_цветов
// и хлеба, общим весом: вес_в_граммах"
public class FoodFactory extends Manufacture{
    public final Apple[] apples = new Apple[]{
            new Apple(Apple.Colors.BLACK.name(),      200, 20, Apple.Colors.BLACK),
            new Apple(Apple.Colors.WHITE.name(),      201, 21, Apple.Colors.WHITE),
            new Apple(Apple.Colors.TRANSPARENT.name(),202, 22, Apple.Colors.TRANSPARENT),
    };
    public void fillShopWithFood(Collection<? super FoodItem> items
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

