package visitors;

import goods.ShopItem;

import java.util.Collection;

//- ImJustLookingVisitor - который просто смотрит товары и выводит их
//        на консоль в формате Название - цена
public class ImJustLookingVisitor implements ShopVisitor{
    @Override
    public void visitShop(final Collection<? extends ShopItem> shopItems){
        for(ShopItem item: shopItems){
            ShopVisitor.lookAt(item);
        }
    }
}
