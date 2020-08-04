package manufacters;

import goods.electronics.ElectronicItem;
import goods.electronics.inventory.Refrigerator;
import goods.electronics.inventory.TV;
import supervisors.Inventory;

import static util.Util7.giveRandom;

//ElectronicFabric - с методом fillShopWithElectronicGoods.
//На вход передается коллекция(магазин) которую нужно заполнить.
//В эту коллекцию нужно добавить несколько холодильников
// или телевизоров с разными свойствами и вывести на экран
public class ElectronicFabric extends Manufacture{
    private final TV[] tvs = new TV[]{
        new TV("Электроника", 300,310),
        new TV("Шилялис",     301,311),
        new TV("Сапфир",      302,312),
        new TV("Кварц",       303,313),
        new TV("Рекорд",      304,314),
        new TV("Чайка",       305,315),
        new TV("Рубин",       306,316),
        new TV("Фотон",       307,317),
        new TV("Горизонт",    308,318),
        new TV("Электрон",    309,319),
    };
    private final Refrigerator[] refrigerators = new Refrigerator[]{
        new Refrigerator("Свияга",     400,410),
        new Refrigerator("Газоаппарат",401,411),
        new Refrigerator("Север",      402,412),
        new Refrigerator("Морозко",    403,413),
        new Refrigerator("ЗИС-Москва", 404,414),
        new Refrigerator("Атлант",     405,415),
        new Refrigerator("Айсберг",    406,416),
        new Refrigerator("Ока",        407,417),
        new Refrigerator("ЗИЛ",        408,418),
        new Refrigerator("Бирюса",     409,419),
    };
    public void fillShopWithElectronicGoods(Inventory<? super ElectronicItem> inventory
            , final int min, final int max){
        for(int i=max; min<=--i; ){
            final ElectronicItem item= produce();
            inventory.add(item);
            printToShopAdded(inventory.getTitle(), item);
        }
    }
    private ElectronicItem produce(){
        return switch(giveRandom(new Class[]{Refrigerator.class, TV.class}.length)){
            case 0 -> refrigerators[giveRandom(refrigerators.length)];
            case 1 -> tvs[giveRandom(tvs.length)];
            default -> throw new IllegalStateException("Unexpected case value");
        };
    }
}
