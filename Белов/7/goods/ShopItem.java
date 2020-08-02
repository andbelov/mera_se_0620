package goods;

import java.util.Objects;

//оздайте абстрактный класс shop.items.ShopItem с полями: название товара и цена продажи.
public abstract class ShopItem{
    private static final String TYPE = "товар";
    private final String name;
    private int price;
    protected ShopItem(final String name){
        this.name = name;
    }
    public static String getType(){
        return TYPE;
    }
    public String getName(){
        return name;
    }
    public int getPrice(){
        return price;
    }
    public void setPrice(int price){
        this.price = price;
    }
    @Override
    public abstract String toString();
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShopItem shopItem = (ShopItem)o;
        return Objects.equals(name, shopItem.name)
            && Objects.equals(price, shopItem.price);
    }
    @Override
    public int hashCode(){
        return Objects.hash(name, price);
    }
}
