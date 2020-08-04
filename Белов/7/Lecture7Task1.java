import goods.Item;
import goods.electronics.ElectronicItem;
import goods.food.FoodItem;
import manufacters.AppleGarden;
import manufacters.ElectronicFabric;
import manufacters.FoodFactory;
import supervisors.Inventory;
import supervisors.Supervisor;
import visitors.ElectronicAddictedVisitor;
import visitors.ImJustLookingVisitor;
import visitors.RichVisitor;
import visitors.ShopVisitor;

import java.util.ArrayList;

class Lecture7Task1{
//- Гипермаркет - хранит любые товары. Collection<shop.inventory.Item>
//- Магазин электронной продукции - торгует только электроникой. Collection<ElectronicItem>
//- Продуктовый магазин - торгует продуктами Collection<FoodItem>
//-  - торгует только яблоками.  Collection<Apple>
	public static void main(String[] args){
		task();
	}
	@SuppressWarnings("SpellCheckingInspection")
	private static void task(){
		final Supervisor supervisor = new Supervisor();

		// (!) just for short name, NOTE(!) class Inventory<T> extends ArrayList<T>
		// i would use Collection<**Item> collect = new ArrayList<>();
		// but I like the "class extends collection" with "title" of my collection
		// instead of: String title = Supervisor.Shops.ELECTRA_SHOP.getShopTitle();
		//Shop/Invertory:
		final Inventory<Item>           hippInv = supervisor.HIPPER_MARKET_INVENTORY;
		final Inventory<ElectronicItem> elecInv = supervisor.ELECTRA_SHOP_INVENTORY;
		final Inventory<FoodItem>       foodInv = supervisor.FOOD_SHOP_INVENTORY;
		final Inventory<FoodItem>       nearInv = supervisor.NEAR_STALL_INVENTORY;
		ArrayList<Inventory<? extends Item>> shops = new ArrayList<>();
		shops.add(hippInv);
		shops.add(elecInv);
		shops.add(foodInv);
		shops.add(nearInv);
		//Manufactures:
		final var electronicFabric = new ElectronicFabric();
		final var      appleGarden = new AppleGarden();
		final var      foodFactory = new FoodFactory();

		//Заполните следующие магазины товарами, используя ElectronicFabric:
		//- Гипермаркет
		//-Магазин электронной продукции
		electronicFabric.printName();
		electronicFabric.fillShopWithElectronicGoods(hippInv, 3, 5);
		electronicFabric.fillShopWithElectronicGoods(elecInv, 2, 3);
		//Заполните следующие магазины товарами, используя AppleGarden:
		//- Продуктовый магазин
		//- Ларек с яблоками у дома
		//- Гипермаркет
		appleGarden.printName();
		appleGarden.fillShopWithApples(foodInv,2,3);
		appleGarden.fillShopWithApples(nearInv,2, 5);
		appleGarden.fillShopWithApples(hippInv,2,3);
		//Заполните следующие магазины товарами, используя FoodFactory:
		//- Продуктовый магазин
		//- Гипермаркет
		foodFactory.printName();
		foodFactory.fillShopWithFood(foodInv, 1,2);
		foodFactory.fillShopWithFood(hippInv, 1,2);

		// supervisors.Supervisor manage the inventory with prices for the all shops
		shops.forEach(inventory ->
			inventory.forEach(item ->
				item.setPrice(supervisor.givePriceFor(item))));

		revise("\n==== РЕВИЗИЯ ПЕРЕД ОТКРЫТИЕМ ====", shops);
		System.out.println("\n==== ПОКУПАТЕЛИ ====");
		//Создайте трех посетителей разных классов
		//и пусть они погуляют по всем магазинам.
		ArrayList<ShopVisitor> visitors = new ArrayList<>();
		visitors.add(new ImJustLookingVisitor());
		visitors.add(new ElectronicAddictedVisitor());
		visitors.add(new RichVisitor());
		visitors.forEach(visitor ->	shops.forEach(shop -> {
				System.out.println("Покупатель " + visitor.getName()
					+ " зашел в " + shop.getTitle());
				visitor.visitShop(shop);
		}));
		revise("\n==== РЕВИЗИЯ ПОСЛЕ ПОКУПАТЕЛЕЙ ====", shops);
	}
	private static void revise(final String reviseName, final ArrayList<Inventory<? extends Item>> shops){
		System.out.println(reviseName);
		shops.forEach(shop -> {
			System.out.println(shop.getTitle());
			shop.forEach(item ->
					System.out.println(item.toString()));
		});
	}
}

/* output:
-- Manufacture: ElectronicFabric --
В «Гипермаркет #1» добавлено: телевизор «Горизонт»
В «Гипермаркет #1» добавлено: телевизор «Кварц»
В «Магазин электронной продукции #2» добавлено: холодильник «Айсберг»

-- Manufacture: AppleGarden --
В «Продуктовый магазин #3» добавлено: яблоко «GOLD»
В «Ларек с яблоками у дома #4» добавлено: яблоко «GOLD»
В «Ларек с яблоками у дома #4» добавлено: яблоко «COOPER»
В «Ларек с яблоками у дома #4» добавлено: яблоко «COOPER»
В «Гипермаркет #1» добавлено: яблоко «SILVER»

-- Manufacture: FoodFactory --
В «Продуктовый магазин #3» добавлено: яблоко «BLACK»
В «Гипермаркет #1» добавлено: хлеб «Плюшка»

==== РЕВИЗИЯ ПЕРЕД ОТКРЫТИЕМ ====
«Гипермаркет #1»
TV{TYPE='телевизор', volume=318} ElectronicItem{power=308} Item{name='телевизор «Горизонт»', price=12416}
TV{TYPE='телевизор', volume=313} ElectronicItem{power=303} Item{name='телевизор «Кварц»', price=13353}
Apple{TYPE='яблоко', color=SILVER} FoodItem{calories=12, expiration=12} Item{name='яблоко «SILVER»', price=8}
Bread{TYPE='хлеб', weight=226} FoodItem{calories=208, expiration=216} Item{name='хлеб «Плюшка»', price=43}
«Магазин электронной продукции #2»
Refrigerator{TYPE='холодильник', volume=416} ElectronicItem{power=406} Item{name='холодильник «Айсберг»', price=96458}
«Продуктовый магазин #3»
Apple{TYPE='яблоко', color=GOLD} FoodItem{calories=10, expiration=10} Item{name='яблоко «GOLD»', price=6}
Apple{TYPE='яблоко', color=BLACK} FoodItem{calories=100, expiration=110} Item{name='яблоко «BLACK»', price=5}
«Ларек с яблоками у дома #4»
Apple{TYPE='яблоко', color=GOLD} FoodItem{calories=10, expiration=10} Item{name='яблоко «GOLD»', price=6}
Apple{TYPE='яблоко', color=COOPER} FoodItem{calories=11, expiration=11} Item{name='яблоко «COOPER»', price=9}
Apple{TYPE='яблоко', color=COOPER} FoodItem{calories=11, expiration=11} Item{name='яблоко «COOPER»', price=9}

==== ПОКУПАТЕЛИ ====
Покупатель ImJust зашел в «Гипермаркет #1»
Покупатель ImJust осмотрел: телевизор «Горизонт» - цена: 12416
Покупатель ImJust осмотрел: телевизор «Кварц» - цена: 13353
Покупатель ImJust осмотрел: яблоко «SILVER» - цена: 8
Покупатель ImJust осмотрел: хлеб «Плюшка» - цена: 43
Покупатель ImJust зашел в «Магазин электронной продукции #2»
Покупатель ImJust осмотрел: холодильник «Айсберг» - цена: 96458
Покупатель ImJust зашел в «Продуктовый магазин #3»
Покупатель ImJust осмотрел: яблоко «GOLD» - цена: 6
Покупатель ImJust осмотрел: яблоко «BLACK» - цена: 5
Покупатель ImJust зашел в «Ларек с яблоками у дома #4»
Покупатель ImJust осмотрел: яблоко «GOLD» - цена: 6
Покупатель ImJust осмотрел: яблоко «COOPER» - цена: 9
Покупатель ImJust осмотрел: яблоко «COOPER» - цена: 9
Покупатель Electr зашел в «Гипермаркет #1»
Покупатель Electr осмотрел: телевизор «Горизонт» - цена: 12416
телевизор «Горизонт» с мощностью 308
Покупатель Electr осмотрел: телевизор «Кварц» - цена: 13353
телевизор «Кварц» с мощностью 303
Покупатель Electr купил: телевизор «Горизонт» по цене: 12416 и унес с собой
Покупатель Electr зашел в «Магазин электронной продукции #2»
Покупатель Electr осмотрел: холодильник «Айсберг» - цена: 96458
холодильник «Айсберг» с мощностью 406
Покупатель Electr купил: холодильник «Айсберг» по цене: 96458 и унес с собой
Покупатель Electr зашел в «Продуктовый магазин #3»
Покупатель Electr обижен отсутствием нужного товара
Покупатель Electr зашел в «Ларек с яблоками у дома #4»
Покупатель Electr обижен отсутствием нужного товара
Покупатель RichVi зашел в «Гипермаркет #1»
Покупатель RichVi осмотрел: телевизор «Кварц» - цена: 13353
Покупатель RichVi .. не понравилась дешевка
Покупатель RichVi осмотрел: яблоко «SILVER» - цена: 8
Покупатель RichVi оплатил с доставкой: яблоко «SILVER» по цене: 8
Покупатель RichVi получил от доставщика: яблоко «SILVER»
Покупатель RichVi осмотрел: хлеб «Плюшка» - цена: 43
Покупатель RichVi .. не понравилась дешевка
Покупатель RichVi зашел в «Магазин электронной продукции #2»
Покупатель RichVi зашел в «Продуктовый магазин #3»
Покупатель RichVi осмотрел: яблоко «GOLD» - цена: 6
Покупатель RichVi .. не понравилась дешевка
Покупатель RichVi осмотрел: яблоко «BLACK» - цена: 5
Покупатель RichVi оплатил с доставкой: яблоко «BLACK» по цене: 5
Покупатель RichVi получил от доставщика: яблоко «BLACK»
Покупатель RichVi зашел в «Ларек с яблоками у дома #4»
Покупатель RichVi осмотрел: яблоко «GOLD» - цена: 6
Покупатель RichVi .. не понравилась дешевка
Покупатель RichVi осмотрел: яблоко «COOPER» - цена: 9
Покупатель RichVi оплатил с доставкой: яблоко «COOPER» по цене: 9
Покупатель RichVi получил от доставщика: яблоко «COOPER»
Покупатель RichVi осмотрел: яблоко «COOPER» - цена: 9
Покупатель RichVi .. не понравилась дешевка

==== РЕВИЗИЯ ПОСЛЕ ПОКУПАТЕЛЕЙ ====
«Гипермаркет #1»
TV{TYPE='телевизор', volume=313} ElectronicItem{power=303} Item{name='телевизор «Кварц»', price=13353}
Bread{TYPE='хлеб', weight=226} FoodItem{calories=208, expiration=216} Item{name='хлеб «Плюшка»', price=43}
«Магазин электронной продукции #2»
«Продуктовый магазин #3»
Apple{TYPE='яблоко', color=GOLD} FoodItem{calories=10, expiration=10} Item{name='яблоко «GOLD»', price=6}
«Ларек с яблоками у дома #4»
Apple{TYPE='яблоко', color=GOLD} FoodItem{calories=10, expiration=10} Item{name='яблоко «GOLD»', price=6}
Apple{TYPE='яблоко', color=COOPER} FoodItem{calories=11, expiration=11} Item{name='яблоко «COOPER»', price=9}
*/
/*
Задание 7. Магазинное обобщение
В этом задании будем описывать магазин с товарами и складами.
Создайте абстрактный класс shop.inventory.Item с полями:
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
- Гипермаркет - хранит любые товары. Collection<shop.inventory.Item>
- Магазин электронной продукции - торгует только электроникой.
 Collection<ElectronicItem>
- Продуктовый магазин - торгует продуктами Collection<FoodItem>
- Ларек с яблоками у дома - торгует только яблоками.  Collecion<Apple>
Обратите внимание, магазины нужно создавать именно коллекцией,
 а не отдельным классом (у нас же задание на generics)
Создайте интерфейс ShopVisitor с единственным методом
void visitShop, на вход он принимает "магазин",т.е. какую-то коллекцию,
 содержащую shop.inventory.Item или его наследником.
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