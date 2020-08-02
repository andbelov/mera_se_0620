package manufacters;

import goods.electronics.ElectronicItem;
import goods.electronics.items.Refrigerator;
import goods.electronics.items.TV;

import java.util.Collection;

import static util.Util7.giveRandom;

//ElectronicFabric - с методом fillShopWithElectronicGoods.
//На вход передается коллекция(магазин) которую нужно заполнить.
//В эту коллекцию нужно добавить несколько холодильников
// или телевизоров с разными свойствами и вывести на экран
public class ElectronicFabric extends Manufacture{
    private final TV[] tvs = new TV[]{
        new TV("",    100,10),
        new TV("",    101,11),
        new TV("",    102,12),
        new TV("",    103,13),
        new TV("",    104,14),
        new TV("",    105,15),
        new TV("",    106,16),
        new TV("",    107,17),
        new TV("",    108,18),
        new TV("",    109,19),
    };
    private final Refrigerator[] refrigerators = new Refrigerator[]{
        new Refrigerator("Свияга",     1000,20),
        new Refrigerator("Газоаппарат",1001,21),
        new Refrigerator("Север",      1002,22),
        new Refrigerator("Морозко",    1003,23),
        new Refrigerator("ЗИС-Москва", 1004,24),
        new Refrigerator("Саратов-2",  1005,25),
        new Refrigerator("Атлант",     1006,26),
        new Refrigerator("Айсберг",    1007,27),
        new Refrigerator("Ока",        1008,28),
        new Refrigerator("ЗИЛ",        1009,29),
        new Refrigerator("Бирюса",     1010,30),
        new Refrigerator("Юрюзань",    1011,31),
        new Refrigerator("Орск",       1012,32),
        new Refrigerator("Апшерон",    1013,33),
    };
    public void fillShopWithElectronicGoods(Collection<? super ElectronicItem> items
            , final String shopTitle, final int min, final int max){
        for(int i=max; min<=--i; ){
            addElectronicGoodsTo(items);
        }
    }
    private void addElectronicGoodsTo(Collection<? super ElectronicItem> items){
        switch(giveRandom(new Class[]{Refrigerator.class, TV.class}.length)){
            case 0 -> items.add(refrigerators[giveRandom(refrigerators.length)]);
            case 1 -> items.add(tvs[giveRandom(tvs.length)]);
        }
    }

/* This function moved to abstract class Manufacture, this class extends it.
    public static void fillShopWithElectronicGoods(Collection<? super ElectronicItem> shop
            , final int min, final int max){
        for(int i=max; min<=--i; ){
            switch(getRandom(new Class[]{Refrigerator.class, TV.class}.length)){
                case 0 -> shop.add(refrigerators[giveRandom(refrigerators.length)]);
                case 1 -> shop.add(tvs[giveRandom(tvs.length)]);
            }
        }
        shop.forEach(item ->
            System.out.println("Электронная фабрика отправила в магаз " + item.toString())
        );
    }
 */
}
