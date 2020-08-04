package goods;

import java.util.Objects;

//оздайте абстрактный класс shop.inventory.Item с полями: название товара и цена продажи.
public abstract class Item{
    private final String name;
    private int price;
    protected Item(final String name){
        this.name = getType() + " «" + name + "»";
    }
    public abstract String getType();
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
        Item item = (Item)o;
        return Objects.equals(name, item.name)
            && Objects.equals(price, item.price);
    }
    @Override
    public int hashCode(){
        return Objects.hash(name, price);
    }
}
