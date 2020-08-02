package goods.electronics.items;

import goods.electronics.ElectronicItem;

import java.util.Objects;
//		- TV. Дополнительное свойство -  громкость
public class TV extends ElectronicItem{
    private static final String TYPE = "телевизор";
    private int volume;
    public TV(final String name, final int power, final int volume){
        super(name, power);
        this.volume = volume;
    }
    public int getVolume(){
        return volume;
    }
    public void setVolume(int volume){
        this.volume = volume;
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
        TV tv = (TV) o;
        return Objects.equals(volume, tv.volume);
    }
    @Override
    public int hashCode(){
        return Objects.hash(super.hashCode(), volume);
    }
}
