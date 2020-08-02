package goods.food;

import goods.ShopItem;

import java.util.Objects;

//Создайте два наследника:
//- FoodItem: дополнительное поля калорийность и срок годности в днях.
public abstract class FoodItem extends ShopItem{
    private static final String TYPE = "еда";
    private int calories;
    private int expiration;
    public FoodItem(final String name
            , final int calories, final int expiration){
        super(name);
        this.calories = calories;
        this.expiration = expiration;
    }
    public int getCalories(){
        return calories;
    }
    public int getExpiration(){
        return expiration;
    }
    @Override
    abstract public String toString();
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FoodItem foodItem = (FoodItem) o;
        return Objects.equals(calories, foodItem.calories) &&
                Objects.equals(expiration, foodItem.expiration);
    }
    @Override
    public int hashCode(){
        return Objects.hash(super.hashCode(), calories, expiration);
    }
}
