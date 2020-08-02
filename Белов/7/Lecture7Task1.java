import goods.ShopItem;
import goods.electronics.ElectronicItem;
import goods.food.FoodItem;
import manufacters.AppleGarden;
import manufacters.ElectronicFabric;
import manufacters.FoodFactory;
import supervisors.Supervisor;
import visitors.ElectronicAddictedVisitor;
import visitors.ImJustLookingVisitor;
import visitors.ShopVisitor;

import java.util.ArrayList;
import java.util.Collection;

class Lecture7Task1{
//- Гипермаркет - хранит любые товары. Collection<shop.items.ShopItem>
//- Магазин электронной продукции - торгует только электроникой. Collection<ElectronicItem>
//- Продуктовый магазин - торгует продуктами Collection<FoodItem>
//-  - торгует только яблоками.  Collection<Apple>
	public static void main(String[] args){
		task();
	}
	private static void task(){
		final Supervisor supervisor = new Supervisor();
		final Collection<ShopItem>       hippItems = supervisor.HIPPER_MARKET_ITEMS; // (!) just for short name var
		final Collection<ElectronicItem> elecItems = supervisor.ELECTRA_SHOP_ITEMS;
		final Collection<FoodItem>       foodItems = supervisor.FOOD_SHOP_ITEMS;
		final Collection<FoodItem>       nearItems = supervisor.NEAR_STALL_ITEMS;
		ArrayList<Collection<? extends ShopItem>> shops = new ArrayList<>();
		shops.add(hippItems);
		shops.add(elecItems);
		shops.add(foodItems);
		shops.add(nearItems);
		final String hippTitle = Supervisor.Shops.HIPPER_MARKET.getShopTitle();
		final String elecTitle = Supervisor.Shops.ELECTRA_SHOP.getShopTitle();
		final String foodTitle = Supervisor.Shops.FOOD_SHOP.getShopTitle();
		final String nearTitle = Supervisor.Shops.NEAR_STALL.getShopTitle();
		final var electronicFabric = new ElectronicFabric();
		final var      appleGarden = new AppleGarden();
		final var      foodFactory = new FoodFactory();

		//Заполните следующие магазины товарами, используя ElectronicFabric:
		//- Гипермаркет
		//-Магазин электронной продукции
		electronicFabric.fillShopWithElectronicGoods(hippItems, hippTitle, 3, 5);
		electronicFabric.fillShopWithElectronicGoods(elecItems, elecTitle, 2, 3);
		//Заполните следующие магазины товарами, используя AppleGarden:
		//- Продуктовый магазин
		//- Ларек с яблоками у дома
		//- Гипермаркет
		appleGarden.fillShopWithApples(foodItems, foodTitle,2,3);
		appleGarden.fillShopWithApples(nearItems, nearTitle,2, 5);
		appleGarden.fillShopWithApples(hippItems, hippTitle,2,3);
		//Заполните следующие магазины товарами, используя FoodFactory:
		//- Продуктовый магазин
		//- Гипермаркет
		foodFactory.fillShopWithFood(foodItems, foodTitle, 1,2);
		foodFactory.fillShopWithFood(hippItems, hippTitle, 1,2);

		// supervisors.Supervisor manage the items with prices for the all shops
		shops.forEach(shop ->
			shop.forEach(item ->
				item.setPrice(supervisor.givePriceFor(item))));

		//Создайте трех посетителей разных классов
		//и пусть они погуляют по всем магазинам.
		ArrayList<ShopVisitor> visitors = new ArrayList<>();
		visitors.add(new ImJustLookingVisitor());
		visitors.add(new ElectronicAddictedVisitor());
		visitors.add(new ImJustLookingVisitor());
		visitors.forEach(visitor ->
			shops.forEach(shop ->
				visitor.visitShop(shop)));
	}

}

/* output:
*/
/*
Задание 7. Магазинное обобщение
В этом задании будем описывать магазин с товарами и складами.
Создайте абстрактный класс shop.items.ShopItem с полями:
 название товара и цена продажи.
Создайте два наследника:
- ElectronicItem: дополнительное поле потребляемая мощность.
- FoodItem: дополнительное поля калорийность и срок годности в днях.

Создайте классы, описывающие конкретные товары, с дополнительными свойствами:
- TV. Дополнительное свойство -  громкость
- Refrigerator. Дополнительное свойство - объем морозильной камеры
- Apple.Дополнительное свойство - цвет
- Bread. Дополнительное свойство - вес  в граммах

Создайте четыре магазина магазина. Магазин - это просто типизированная
 коллекция, которая хранит товары определенного типа:
- Гипермаркет - хранит любые товары. Collection<shop.items.ShopItem>
- Магазин электронной продукции - торгует только электроникой.
 Collection<ElectronicItem>
- Продуктовый магазин - торгует продуктами Collection<FoodItem>
- Ларек с яблоками у дома - торгует только яблоками.  Collecion<Apple>
Обратите внимание, магазины нужно создавать именно коллекцией,
 а не отдельным классом (у нас же задание на generics)
Создайте интерфейс ShopVisitor с единственным методом
void visitShop, на вход он принимает "магазин",т.е. какую-то коллекцию,
 содержащую shop.items.ShopItem или его наследником.
Создайте три конкретных посетителя:
- ImJustLookingVisitor - который просто смотрит товары и выводит их
на консоль в формате Название - цена
- ElectronicAddictedVisitor - который выводит на экран всю электронику.
После вывода такой посетитель покупает электронный товар с самой большой
потребляемой мощностью.
"Покупает" - значит на экран выводится надпись "имя_товара куплен
 по цена_товара" и товар удаляется из коллекции.
- RichVisitor - который покупает каждый второй товар в магазине.
Теперь у нас есть магазины, есть покупатели, но нет товаров, только их описания.
Создадим несколько фабрик по производству товаров.
ElectronicFabric - с методом fillShopWithElectronicGoods.
На вход передается коллекция(магазин) которую нужно заполнить.
В эту коллекцию нужно добавить несколько холодильников
или телевизоров с разными свойствами и вывести на экран
"В магазин добавлена электроника: список_товаров"
Заполните следующие магазины товарами, используя ElectronicFabric:
- Гипермаркет
-Магазин электронной продукции
Обратите внимание, что если попытаться вызвать
ElectronicFabric.fillShopWithElectronicGoods(магазин_с_яблоками
или продуктовый_магазин) - ошибка компиляции


AppleGarden - с методом fillShopWithApples. На вход передается
коллекция(магазин) которую нужно заполнить.
В эту коллекцию нужно добавить яблок разных цветов. Вывести на экран
"В магазин добавлены яблоки: список_цветов"
Заполните следующие магазины товарами, используя AppleGarden:
- Продуктовый магазин
- Ларек с яблоками у дома
- Гипермаркет
Обратите внимание, что если попытаться вызвать
AppleGarden.fillShopWithApples(магазин_электроники) - ошибка компиляции


FoodFactory - с методом fillShopWithFood. На вход передается коллекция(магазин) которую нужно заполнить.
В эту коллекцию нужно добавить немного яблок и немного хлеба. Вывести на экран
"В магазин добавлены яблоки: список_цветов
и хлеба, общим весом: вес_в_граммах"
Заполните следующие магазины товарами, используя FoodFactory:
- Продуктовый магазин
- Гипермаркет
Обратите внимание, что если попытаться вызвать
FoodFactory.fillShopWithFood(магазин_электроники) - ошибка компиляции
FoodFactory.fillShopWithFood(ларек_с_яблоками) - ошибка компиляции
(Владелец ларька очень расстроится, если под видом яблок купят хлеб)

Создайте трех посетителей разных классов
и пусть они погуляют по всем магазинам.
Задание на подумать: почему метод remove(Object o)
 - принимает на вход любой объект, без типизации?

*/