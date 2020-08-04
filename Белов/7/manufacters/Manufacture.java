package manufacters;

import goods.Item;

public abstract class Manufacture{
    public void printName(){
        System.out.println("\n-- Manufacture: "
                + this.getClass().getSimpleName() + " --");
    }
    protected static void printToShopAdded(final String shopTitle
            , Item item){
        System.out.println("В " + shopTitle + " добавлено: "
                        + item.toString());
    }
}
