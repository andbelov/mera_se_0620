package goods.food.inventory;

import goods.food.FoodItem;

import java.util.Objects;
//		- Apple.Дополнительное свойство - цвет
public class Apple extends FoodItem{
    private static final String TYPE = "яблоко";
    public enum Colors{GOLD, SILVER, COOPER, BLACK, WHITE, TRANSPARENT}
    private final Colors color;
    public Apple(final String name, final int calories, final int expiration
            , final Colors color){
        super(name, calories, expiration);
        this.color = color;
    }
    @Override
    public String getType(){
        return TYPE;
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
        Apple apple = (Apple) o;
        return Objects.equals(color, apple.color);
    }
    @Override
    public int hashCode(){
        return Objects.hash(super.hashCode(), color);
    }
}
