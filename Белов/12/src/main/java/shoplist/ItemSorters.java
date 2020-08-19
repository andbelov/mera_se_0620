package shoplist;

public class ItemSorters {
    static int sortByCategory(ShopItem i1, ShopItem i2){
        checkNotNull(i1, i2);
        return i1.getCategory().compareTo(i2.getCategory());
    }
    static int sortByTitle(ShopItem i1, ShopItem i2){
        checkNotNull(i1, i2);
        return i1.getTitle().compareTo(i2.getTitle());
    }
    static int sortByPrice(ShopItem i1, ShopItem i2){
        checkNotNull(i1, i2);
        return (int)(i1.getPrice() - i2.getPrice());
    }
    static int sortByQuantity(ShopItem i1, ShopItem i2){
        checkNotNull(i1, i2);
        return i1.getQuantity() - i2.getQuantity();
    }
    private static void checkNotNull(ShopItem i1, ShopItem i2){
        if(null== i1 || null== i2) {
            throw new IllegalArgumentException("Parameters: i1==" + i1 + ", i2=" + i2);
        }
    }
}
