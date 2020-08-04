package visitors;

import goods.Item;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

//- RichVisitor - который покупает каждый второй товар в магазине.
public class RichVisitor extends Visitor implements ShopVisitor{
    @Override
    public void visitShop(final Collection<? extends Item> inventory){
        AtomicInteger counter = new AtomicInteger();
        for(Iterator<? extends Item> iterator = inventory.iterator();
            iterator.hasNext(); ){
            Item item = iterator.next();
            lookAt(item);
            if(0 == counter.incrementAndGet() % 2){
                pay(item);
                take(iterator, item);
            }else{
                skip();
            }
        }
    }
}
