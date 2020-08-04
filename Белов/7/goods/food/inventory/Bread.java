package goods.food.inventory;

import goods.food.FoodItem;

//		- Bread. Дополнительное свойство - вес  в граммах
public class Bread extends FoodItem{
    private static final String TYPE = "хлеб";
    private final int weight;
    public Bread(final String name, final int calories, final int expiration
            , final int weight){
        super(name, calories, expiration);
        this.weight = weight;
    }
    @Override
    public String getType(){
        return TYPE;
    }
    @Override
    public String toString(){
        return "Bread{" +
                "TYPE='" + TYPE + '\'' +
                ", weight=" + weight +
                "} " + super.toString();
    }
}
