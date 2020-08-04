package goods.electronics.inventory;

import goods.electronics.ElectronicItem;

//		- Refrigerator. Дополнительное свойство - объем морозильной камеры
public class Refrigerator extends ElectronicItem{
    private static final String TYPE = "холодильник";
    private final int volume;
    public Refrigerator(final String name
            , final int power, final int volume){
        super(name, power);
        this.volume = volume;
    }
    @Override
    public String getType(){
        return TYPE;
    }
    @Override
    public String toString(){
        return "Refrigerator{" +
                "TYPE='" + TYPE + '\'' +
                ", volume=" + volume +
                "} " + super.toString();
    }
}
