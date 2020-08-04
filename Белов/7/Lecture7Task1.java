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
В «Гипермаркет #1» добавлено: телевизор «Фотон»
В «Гипермаркет #1» добавлено: холодильник «Ока»
В «Магазин электронной продукции #2» добавлено: холодильник «Север»

-- Manufacture: AppleGarden --
В «Продуктовый магазин #3» добавлено: яблоко «COOPER»
В «Ларек с яблоками у дома #4» добавлено: яблоко «SILVER»
В «Ларек с яблоками у дома #4» добавлено: яблоко «GOLD»
В «Ларек с яблоками у дома #4» добавлено: яблоко «GOLD»
В «Гипермаркет #1» добавлено: яблоко «GOLD»

-- Manufacture: FoodFactory --
В «Продуктовый магазин #3» добавлено: хлеб «Бублик»
В «Гипермаркет #1» добавлено: хлеб «Сдоба»

==== РЕВИЗИЯ ПЕРЕД ОТКРЫТИЕМ ====
«Гипермаркет #1»
телевизор «Фотон»
холодильник «Ока»
яблоко «GOLD»
хлеб «Сдоба»
«Магазин электронной продукции #2»
холодильник «Север»
«Продуктовый магазин #3»
яблоко «COOPER»
хлеб «Бублик»
«Ларек с яблоками у дома #4»
яблоко «SILVER»
яблоко «GOLD»
яблоко «GOLD»

==== ПОКУПАТЕЛИ ====
Покупатель ImJust зашел в «Гипермаркет #1»
Покупатель ImJust осмотрел: телевизор «Фотон» - цена: 17991
Покупатель ImJust осмотрел: холодильник «Ока» - цена: 54668
Покупатель ImJust осмотрел: яблоко «GOLD» - цена: 7
Покупатель ImJust осмотрел: хлеб «Сдоба» - цена: 22
Покупатель ImJust зашел в «Магазин электронной продукции #2»
Покупатель ImJust осмотрел: холодильник «Север» - цена: 75195
Покупатель ImJust зашел в «Продуктовый магазин #3»
Покупатель ImJust осмотрел: яблоко «COOPER» - цена: 7
Покупатель ImJust осмотрел: хлеб «Бублик» - цена: 44
Покупатель ImJust зашел в «Ларек с яблоками у дома #4»
Покупатель ImJust осмотрел: яблоко «SILVER» - цена: 9
Покупатель ImJust осмотрел: яблоко «GOLD» - цена: 7
Покупатель ImJust осмотрел: яблоко «GOLD» - цена: 7
Покупатель Electr зашел в «Гипермаркет #1»
Покупатель Electr осмотрел: телевизор «Фотон» - цена: 17991
Покупатель Electr осмотрел: холодильник «Ока» - цена: 54668
Покупатель Electr купил: холодильник «Ока» по цене: 54668 и унес с собой
Покупатель Electr зашел в «Магазин электронной продукции #2»
Покупатель Electr осмотрел: холодильник «Север» - цена: 75195
Покупатель Electr купил: холодильник «Север» по цене: 75195 и унес с собой
Покупатель Electr зашел в «Продуктовый магазин #3»
Покупатель Electr обижен отсутствием нужного товара
Покупатель Electr зашел в «Ларек с яблоками у дома #4»
Покупатель Electr обижен отсутствием нужного товара
Покупатель RichVi зашел в «Гипермаркет #1»
Покупатель RichVi осмотрел: телевизор «Фотон» - цена: 17991
Покупатель RichVi осмотрел: яблоко «GOLD» - цена: 7
Покупатель RichVi оплатил с доставкой: яблоко «GOLD» по цене: 7
Покупатель RichVi получил от доставщика: яблоко «GOLD»
Покупатель RichVi осмотрел: хлеб «Сдоба» - цена: 22
Покупатель RichVi зашел в «Магазин электронной продукции #2»
Покупатель RichVi зашел в «Продуктовый магазин #3»
Покупатель RichVi осмотрел: яблоко «COOPER» - цена: 7
Покупатель RichVi осмотрел: хлеб «Бублик» - цена: 44
Покупатель RichVi оплатил с доставкой: хлеб «Бублик» по цене: 44
Покупатель RichVi получил от доставщика: хлеб «Бублик»
Покупатель RichVi зашел в «Ларек с яблоками у дома #4»
Покупатель RichVi осмотрел: яблоко «SILVER» - цена: 9
Покупатель RichVi осмотрел: яблоко «GOLD» - цена: 7
Покупатель RichVi оплатил с доставкой: яблоко «GOLD» по цене: 7
Покупатель RichVi получил от доставщика: яблоко «GOLD»
Покупатель RichVi осмотрел: яблоко «GOLD» - цена: 7

==== РЕВИЗИЯ ПОСЛЕ ПОКУПАТЕЛЕЙ ====
«Гипермаркет #1»
телевизор «Фотон»
хлеб «Сдоба»
«Магазин электронной продукции #2»
«Продуктовый магазин #3»
яблоко «COOPER»
«Ларек с яблоками у дома #4»
яблоко «SILVER»
яблоко «GOLD»
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