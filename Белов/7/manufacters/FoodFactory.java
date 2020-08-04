package manufacters;

import goods.food.FoodItem;
import goods.food.inventory.Apple;
import goods.food.inventory.Bread;
import supervisors.Inventory;

import static util.Util7.giveRandom;

//FoodFactory - с методом fillShopWithFood.
// На вход передается коллекция(магазин) которую нужно заполнить.
//В эту коллекцию нужно добавить немного яблок и немного хлеба.
// Вывести на экран "В магазин добавлены яблоки: список_цветов
// и хлеба, общим весом: вес_в_граммах"
public class FoodFactory extends Manufacture{
    public final Apple[] apples = new Apple[]{
            new Apple(Apple.Colors.BLACK.name(),      100, 110, Apple.Colors.BLACK),
            new Apple(Apple.Colors.WHITE.name(),      101, 111, Apple.Colors.WHITE),
            new Apple(Apple.Colors.TRANSPARENT.name(),102, 112, Apple.Colors.TRANSPARENT),
    };
    public final Bread[] loaves = new Bread[]{
            new Bread("Пшеничный",200, 210, 220),
            new Bread("Ржаной"   ,201, 211, 221),
            new Bread("Серый"    ,202, 212, 222),
            new Bread("Белый"    ,203, 213, 223),
            new Bread("Буханка"  ,204, 214, 224),
            new Bread("Батон"    ,205, 215, 225),
            new Bread("Бублик"   ,206, 216, 226),
            new Bread("Булка"    ,207, 216, 226),
            new Bread("Плюшка"   ,208, 216, 226),
            new Bread("Сдоба"    ,209, 216, 226),
    };
    public void fillShopWithFood(Inventory<? super FoodItem> inventory
        , final int min, final int max){
        for(int i = max; min <= --i; ){
            final FoodItem item = giveRandom()
                 ? apples[giveRandom(apples.length)]
                 : loaves[giveRandom(loaves.length)];
            inventory.add(item);
            printToShopAdded(inventory.getTitle(), item);
        }
    }
}

