package goods.electronics;

import goods.Item;

import java.util.Objects;

//Создайте два наследника:
//- ElectronicItem: дополнительное поле потребляемая мощность.
public abstract class ElectronicItem extends Item{
    private final int power;
    public ElectronicItem(final String name, final int power){
        super(name);
        this.power = power;
    }
    public int getPower(){
        return power;
    }
    @Override
    abstract public String toString();
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ElectronicItem that = (ElectronicItem) o;
        return Objects.equals(power, that.power);
    }
    @Override
    public int hashCode(){
        return Objects.hash(power);
    }
}
