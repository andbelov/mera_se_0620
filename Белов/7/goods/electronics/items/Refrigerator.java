package goods.electronics.items;

import goods.electronics.ElectronicItem;

import java.util.Objects;

//		- Refrigerator. Дополнительное свойство - объем морозильной камеры
public class Refrigerator extends ElectronicItem{
    private static final String TYPE = "холодильник";
    private int volume;
    public Refrigerator(final String name
            , final int power, final int volume){
        super(name, power);
        this.volume = volume;
    }
    public int getVolume(){
        return volume;
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
        Refrigerator that = (Refrigerator) o;
        return Objects.equals(volume, that.volume);
    }
    @Override
    public int hashCode(){
        return Objects.hash(super.hashCode(), volume);
    }
}
