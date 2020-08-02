package visitors;

import goods.ShopItem;

import java.util.Collection;

//Создайте интерфейс ShopVisitor с единственным методом
//        void visitShop, на вход он принимает "магазин",т.е. какую-то коллекцию,
//        содержащую shop.items.ShopItem или его наследником.
public interface ShopVisitor {
    void visitShop(final Collection<? extends ShopItem> shop);
    static void lookAt(final ShopItem item){
        System.out.println("Осмотрен товар: " + item.getName()
                + " - цена: " + item.getPrice());
    }
    static void buy(final ShopItem item){
        System.out.println("Куплен товар: " + item.getName()
                        + " по цене: " + item.getPrice());
    }
    static void offense(){
        System.out.println("Покупатель обижен отсутствием товара");
    }
}
