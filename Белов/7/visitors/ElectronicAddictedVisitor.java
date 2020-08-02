package visitors;

import goods.ShopItem;
import goods.electronics.ElectronicItem;

import java.util.Collection;

//- ElectronicAddictedVisitor - который выводит на экран всю электронику.
// После вывода такой посетитель покупает электронный товар
// с самой большой потребляемой мощностью.
// "Покупает" - значит на экран выводится надпись "имя_товара куплен по " +
//        "цена_товара" и товар удаляется из коллекции.
public class ElectronicAddictedVisitor implements ShopVisitor{
    @Override
    public void visitShop(final Collection<? extends ShopItem> shopItems){
        ElectronicItem theMostPowerfulItem = null;
        for(ShopItem item : shopItems){
            if(!(item instanceof ElectronicItem)){
                continue;
            }
            final var electraItem = (ElectronicItem) item;
            ShopVisitor.lookAt(electraItem);
            if(null == theMostPowerfulItem){
                theMostPowerfulItem = electraItem;
            }
            if(theMostPowerfulItem.getPrice() < electraItem.getPower()){
                theMostPowerfulItem = electraItem;
            }
        }
        if(null != theMostPowerfulItem){
            ShopVisitor.buy(theMostPowerfulItem);
            shopItems.remove(theMostPowerfulItem);
        }else{
            ShopVisitor.offense();
        }
    }
}
