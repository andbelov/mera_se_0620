package shoplist;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class ShopItem {
    private final String id; //строка из 25 символов (английские буквы или цифры)
    private final String title;
    private final String category;
    private final double price;
    private final int quantity;
    public static final int ID_LEN = 25;
    public static final int FIELD_LEN_MAX = 10;
    private static String format = "%" + ID_LEN + "s %10s  %10s %10s %10s"; //default
    private static String formatHeader = "%" + ID_LEN + "s %10s  %10s %10s %10s"; //default
    public ShopItem(final String id, final String title,
                    final String category, final double price,
                    final int quantity) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }
    public String getId(){return id;}
    public String getTitle(){return title;}
    public String getCategory(){return category;}
    public double getPrice(){return price;}
    public int getQuantity(){return quantity;}
    public static void printfHeader(){
        System.out.printf(formatHeader + "%n",
                //ID  Категория   Наименование    Цена    Остаток
                //Длинные значения (больше 10 символов) полей сократите,
                StringUtils.abbreviate("ID", FIELD_LEN_MAX),
                StringUtils.abbreviate("Категория", FIELD_LEN_MAX),
                StringUtils.abbreviate("Наименование", FIELD_LEN_MAX),
                StringUtils.abbreviate("Цена", FIELD_LEN_MAX),
                StringUtils.abbreviate("Остаток", FIELD_LEN_MAX)
        );
    }
    public void printf(){
        // используя StringUtils.abbreviate
        System.out.printf(format + "%n",
                StringUtils.abbreviate(id, FIELD_LEN_MAX),
                StringUtils.abbreviate(category, FIELD_LEN_MAX),
                StringUtils.abbreviate(title, FIELD_LEN_MAX),
                StringUtils.abbreviate(String.valueOf(price), FIELD_LEN_MAX),
                StringUtils.abbreviate(String.valueOf(quantity), FIELD_LEN_MAX)
        );
    }
    public static void setPrintFormatHeader(final String format){
        formatHeader = format;
    }
    public static void setPrintFormat(final String format){
        ShopItem.format = format;
    }
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof ShopItem)) return false;
        ShopItem shopItem = (ShopItem) o;
        return new EqualsBuilder()
                .append(price, shopItem.price)
                .append(quantity, shopItem.quantity)
                .append(category, shopItem.category)
                .append(title, shopItem.title)
                .append(id, shopItem.id)
                .isEquals();
    }
    @Override
    public int hashCode(){
        return new HashCodeBuilder(17, 37)
                .append(category)
                .append(price)
                .append(title)
                .append(quantity)
                .append(id)
                .toHashCode();
    }
}
