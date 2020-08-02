package visitors;

import goods.ShopItem;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

//- RichVisitor - который покупает каждый второй товар в магазине.
public class RichVisitor implements ShopVisitor{
    @Override
    public void visitShop(final Collection<? extends ShopItem> shopItems){
        AtomicInteger counter = new AtomicInteger();
        shopItems.removeIf(item -> {
            ShopVisitor.lookAt(item);
            return 0==counter.incrementAndGet()%2;
        });
    }
}
