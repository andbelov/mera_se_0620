package goods.food.items;

import goods.food.FoodItem;

import java.util.Objects;

//		- Bread. Дополнительное свойство - вес  в граммах
public class Bread extends FoodItem{
    private static final String TYPE = "хлеб";
    private final int weight;
    public Bread(final String name, final int calories, final int expiration
            , final int weight){
        super(name, calories, expiration);
        this.weight = weight;
    }
    public int getWeight(){
        return weight;
    }
    @Override
    public String toString(){
        return getName();
    }
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Bread bread = (Bread) o;
        return Objects.equals(weight, bread.weight);
    }
    @Override
    public int hashCode(){
        return Objects.hash(super.hashCode(), weight);
    }
}
