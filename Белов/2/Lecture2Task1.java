import java.util.Random;

/*
Задание 2.

Магия чисел.
Создайте массив целых чисел на 100 элементов.
Заполните их случайными числами от - 100 до 100.
Создайте функцию boolean isMagicNumber(int number)
Функция возвращает true, если число "магическое" - состоит из одинаковых цифр (например 22, или -33)
Используя цикл for, проверьте каждое число в массиве, является ли оно "магическим".
Если число "магическое" - выведите на экран надпись "Число <число> - магическое!"

*/
class Lecture2Task1{
	public static void main(String[] args){
		System.out.println("=======================================");
		//test();
		Random random = new Random();
		final int NUMS = 100;
		int[] numbers = new int[NUMS];
		for(int i = NUMS; --i >= 0; ){
			numbers[i] = random.nextInt(2*NUMS) - NUMS;
		}
		for(int i = numbers.length; --i >= 0; ){
			if(isMagicNumber(numbers[i])){
				System.out.println("Число " + numbers[i] + " - магическое!");
			}
		}
	}
	private static void test(){
		for(int i = -100; i<100; ++i){
			System.out.print((isNumberMagicByString(i) ? i + "; " : ""));
		}
		System.out.println();
		for(int i = -100; i<100; ++i){
			System.out.print((isNumberMagicByInt   (i) ? i + "; " : ""));
		}
	}
	private static boolean isMagicNumber(int number){
		return isNumberMagicByString(number); // version by String
		// return isNumberMagicByInt(number); // version by Int
	}
	private static boolean isNumberMagicByString(int number){
		final int i = Math.abs(number);
		final String s = String.valueOf(i);
		return 2==s.length() && s.charAt(0)==s.charAt(1);
	}
	private static boolean isNumberMagicByInt(int number){
		final int i = Math.abs(number);
		return 0<i/10%10 && number/10==number%10;
	}
}
/* output:
Число -44 - магическое!
Число 88 - магическое!
Число -11 - магическое!
 */