package goods;

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
    public String toString(){
        return "Item{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
