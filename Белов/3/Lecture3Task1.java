import java.util.Random;

/*
    Задание 3. счастливые часы
Создайте класс FixPriceShop - магазин, который продает товары по одной цене.
	В классе создайте поле list - массив товаров
	 (товар - это просто название, тип String)
	В каждом экземпляре магазина - случайная цена на товары от 50 до 99
	Но во всех магазинах действует акции "счастливый час" - время,
	когда действует скидка 50%
	Счастливые часы - едины для всех магазинов. Час - это случайное значение
	от 0 до 23, важно, что во всех магазинах это число одно и то же.
	в классе определите метод getPrice(), который возвращает цену в
	конкретном магазине
	В классе определите метод getItems() - который возвращает список товаров
	В классе определите метод  int checkItemPrice(String item, int hour)
	 - первый аргумент - это название товара, который покупатель
	 собирается купить, второй аргумент - время покупки.
	 Метод должен возвращать цену. Если время покупки совпадает
	  со счастливым часом - то должна быть учтена скидка 50%.

Создайте 3 магазина
	Каждый магазин заполните случайными товарами
	Из каждого магазина выберите случайный товар и проверьте его цену
	в случайное время.
	Советы: для хранение счастливого часа используйте статическую переменную
	 и статический блок инициализации
	Для заполнения магазина товарами используйте конструктор
	 и установки цены в магазине
​[1:57 PM] Nikolay Kuzmichev На каждый товар из массива своя цена?
​[1:57 PM] Andrey Tarasov  нет, цена одна на все товары в магазине
​[2:13 PM] Diana Eskina    Количество товаров в магазине случайное?
[4:33 PM] Andrey Tarasov  нет, про количество ничего программировать не надо.
Andrey Tarasov Туда можно передать несуществующий товар, Давайте догворимся, что в таком случае, нужно вернуть -1


*/
class Lecture3Task1{
	public static void main(String[] args){
		System.out.println("=======================================");
		//test2();
		task();
	}
	private static void test2(){
		final int[][] happyHours = {{3, 6}, {6, 3}};
		for(var hh: happyHours){
			System.out.println("-------------- [" + hh[0] +" and " + hh[1] + "]");
			for(int h = 0; h < 9; h++){
				final boolean resD = h>=hh[0] && h<hh[1];
				final boolean resN = !(h>=hh[1] && h<hh[0]);
				final boolean isDayHh = hh[0] < hh[1];
				final boolean res = isDayHh ? resD : resN;
				System.out.println("h=" + h + ", res=" + res);

			}
		}
	}
	private static void task(){
		FixPriceShop[] shops = new FixPriceShop[3];
		for(int i=0; i<shops.length; ++i){
			shops[i] = new FixPriceShop();
		}
		System.out.println("A story: several buyers had been noted about discount action "
				+ "\"Buy 1, Get 1 Free\" in FixPrice shops.");
		System.out.println("But they went to the shops at different hours, when the discount may not act");
		System.out.println("The happy " + (FixPriceShop.areHappyHoursInDayTherm() ? "day" : "night")
				+ " hours are from " + FixPriceShop.getHappyHours()[0] + " to " + FixPriceShop.getHappyHours()[1]);
		int buyersNum = 5;
		while(0<=--buyersNum){
			System.out.println("-------------------------------");
			final boolean isBuyerCrazy = 0==buyersNum;
			System.out.println("A new buyer" + (isBuyerCrazy?" (crazy!)":""));
			for(var sh: shops){
				final int hour = FixPriceShop.getRandomHour();
				//i understood right if all shops have the same list[] set?
				// so i use static for list and "FixPriceShop.func()"
				// if not i would use not static and a FixPriceShop instance
				// as "sh.func()" to access each shop list

				final int itemInd = Util.getRandom(FixPriceShop.getItems().length);
				final String item = isBuyerCrazy ? "\"item for a crazy last buyer\""
						: FixPriceShop.getItems()[itemInd];
				final int priceForBuyer = sh.checkItemPrice(item, hour);
				System.out.println(" went at a shop at " + (hour>=10?"":" ") + hour
						+ " and " + (0>priceForBuyer? "COULD NOT buy":"bought") + " a " + item + "\twith price " + priceForBuyer
						+ ", note: regular price for all list in this store " + sh.getPrice()
				);
			}
		}
	}
}
class FixPriceShop{
	private static final String[] ITEMS = {"Notepad", "Tv set", "Phone", "Camera"};
	private final int price;
	private static final float DISCOUNT = 50.f;
	private static final int[] HAPPY_HOURS = randomHours();

	FixPriceShop(){
		price = Util.getRandomInBound(50, 99);
	}
	int getPrice(){
		return price;
	}
	static String[] getItems(){
		return ITEMS;
	}
	boolean isItemInShop(final String item){
		for(var i : ITEMS){
			if(i.equals(item)){
				return true;
			}
		}
		return false;
	}
	int checkItemPrice(String item, int hour){
		if(!isItemInShop(item)){
			return -1;
		}
		return isHourHappy(hour)
		       ? (int) (getPrice() * (1.f - 0.01f * DISCOUNT))
		       : getPrice();
	}

	static boolean isHourHappy(final int hour){
		final int h0 = HAPPY_HOURS[0];
		final int h1 = HAPPY_HOURS[1];
		return areHappyHoursInDayTherm() ? isHourInDayThermHappy(hour) : isHourInNightThermHappy(hour);
	}
	static boolean areHappyHoursInDayTherm(){
		return HAPPY_HOURS[0]< HAPPY_HOURS[1];
	}
	private static boolean isHourInDayThermHappy(final int h){
		return h >= HAPPY_HOURS[0] && h < HAPPY_HOURS[1];
	}
	private static boolean isHourInNightThermHappy(final int h){
		return !(h>= HAPPY_HOURS[1] && h< HAPPY_HOURS[0]);
	}

	static int[] getHappyHours(){
		return HAPPY_HOURS;
	}
	static int[] randomHours(){
		int[] hours = {getRandomHour(), getRandomHour()};
		do{//we may not get happy hours with zero period
			hours[1] = getRandomHour();
		}while(hours[0] == hours[1]);
		return hours;
	}
	static int getRandomHour(){
		return Util.getRandom(24); //max is 23, 24 has to be eq. 0
	}
}

class Util{
	static final Random random = new Random();
	public static int getRandomInBound(final int min, final int max){
		if(max <= min || max <= 0 || min <= 0)
			throw new AssertionError(
					"getRandomInBound got wrong args:"
							+ "\nmin="+ min + "\tmax=" +max);
		return random.nextInt(max-min)+min;
	}
	public static int getRandom(final int i){
		if(i<=0)
			throw new AssertionError(
					"getRandomInBound got wrong args="+ i);
		return random.nextInt(i);
	}
}

/* output:
=======================================
A story: several buyers had been noted about discount action "Buy 1, Get 1 Free" in FixPrice shops.
But they went to the shops at different hours, when the discount may not act
The happy day hours are from 14 to 17
-------------------------------
A new buyer
 went at a shop at 18 and bought a Tv set	with price 96, note: regular price for all list in this store 96
 went at a shop at 17 and bought a Camera	with price 72, note: regular price for all list in this store 72
 went at a shop at 18 and bought a Tv set	with price 53, note: regular price for all list in this store 53
-------------------------------
A new buyer
 went at a shop at  6 and bought a Phone	with price 96, note: regular price for all list in this store 96
 went at a shop at 16 and bought a Camera	with price 36, note: regular price for all list in this store 72
 went at a shop at  8 and bought a Tv set	with price 53, note: regular price for all list in this store 53
-------------------------------
A new buyer
 went at a shop at 14 and bought a Camera	with price 48, note: regular price for all list in this store 96
 went at a shop at  4 and bought a Camera	with price 72, note: regular price for all list in this store 72
 went at a shop at 15 and bought a Tv set	with price 26, note: regular price for all list in this store 53
-------------------------------
A new buyer
 went at a shop at 16 and bought a Notepad	with price 48, note: regular price for all list in this store 96
 went at a shop at  8 and bought a Camera	with price 72, note: regular price for all list in this store 72
 went at a shop at  8 and bought a Phone	with price 53, note: regular price for all list in this store 53
-------------------------------
A new buyer (crazy!)
 went at a shop at 23 and COULD NOT buy a "item for a crazy last buyer"	with price -1, note: regular price for all list in this store 96
 went at a shop at 19 and COULD NOT buy a "item for a crazy last buyer"	with price -1, note: regular price for all list in this store 72
 went at a shop at  8 and COULD NOT buy a "item for a crazy last buyer"	with price -1, note: regular price for all list in this store 53
test2()
-------------- [3 and 6]
h=0, res=false
h=1, res=false
h=2, res=false
h=3, res=true
h=4, res=true
h=5, res=true
h=6, res=false
h=7, res=false
h=8, res=false
-------------- [6 and 3]
h=0, res=true
h=1, res=true
h=2, res=true
h=3, res=false
h=4, res=false
h=5, res=false
h=6, res=true
h=7, res=true
h=8, res=true
 */