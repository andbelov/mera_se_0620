package visitors;

import goods.Item;
import goods.electronics.ElectronicItem;

import java.util.Collection;

//- ElectronicAddictedVisitor - который выводит на экран всю электронику.
// После вывода такой посетитель покупает электронный товар
// с самой большой потребляемой мощностью.
// "Покупает" - значит на экран выводится надпись "имя_товара куплен по " +
//        "цена_товара" и товар удаляется из коллекции.
public class ElectronicAddictedVisitor extends Visitor implements ShopVisitor{
    @Override
    public void visitShop(final Collection<? extends Item> inventory){
        ElectronicItem theMostPowerfulItem = null;
        for(Item item : inventory){
            if(!(item instanceof ElectronicItem)){
                continue;
            }
            final var electraItem = (ElectronicItem) item;
            lookAt(electraItem);
            System.out.println(item.getName() + " с мощностью " + electraItem.getPower());
            if(null == theMostPowerfulItem){
                theMostPowerfulItem = electraItem;
            }
            if(theMostPowerfulItem.getPower() < electraItem.getPower()){
                theMostPowerfulItem = electraItem;
            }
        }
        if(null != theMostPowerfulItem){
            buy(inventory, theMostPowerfulItem);
        }else{
            offense();
        }
    }
}
