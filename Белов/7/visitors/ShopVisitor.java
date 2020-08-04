package visitors;

import goods.Item;

import java.util.Collection;
import java.util.Iterator;

//Создайте интерфейс ShopVisitor с единственным методом
//        void visitShop, на вход он принимает "магазин",т.е. какую-то коллекцию,
//        содержащую shop.inventory.Item или его наследником.
public interface ShopVisitor{
    void visitShop(final Collection<? extends Item> inventory);
    String getName();
}
abstract class Visitor{
    public String getName(){
        return getClass().getSimpleName().substring(0,6);
    }
    private String getShoperName(){
        return "Покупатель " + getName();
    }
    void lookAt(final Item item){
        System.out.println(getShoperName()
                + " осмотрел: " + item.getName()
                + " - цена: " + item.getPrice());
    }
    void pay(final Item item){
        System.out.println(getShoperName()
                + " оплатил с доставкой: " + item.getName()
                + " по цене: " + item.getPrice());
    }
    void take(final Iterator<? extends Item> iterator, final Item item){
        System.out.println(getShoperName()
                + " получил от доставщика: " + item.getName());
        iterator.remove();
    }
    void buy(final Collection<? extends Item> inventory, final Item item){
        System.out.println(getShoperName()
                + " купил: " + item.getName()
                + " по цене: " + item.getPrice()
                + " и унес с собой");
        inventory.remove(item); // и унес с собой
    }
    void offense(){
        System.out.println(getShoperName()
                + " обижен отсутствием нужного товара");
    }
    void skip(){
        System.out.println(getShoperName()
                + " .. не понравилась дешевка");
    }
}