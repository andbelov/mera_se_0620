package visitors;

import goods.Item;

import java.util.Collection;

//- ImJustLookingVisitor - который просто смотрит товары и выводит их
//        на консоль в формате Название - цена
public class ImJustLookingVisitor extends Visitor implements ShopVisitor{
    @Override
    public void visitShop(final Collection<? extends Item> inventory){
        for(Item item: inventory){
            lookAt(item);
        }
    }
}
