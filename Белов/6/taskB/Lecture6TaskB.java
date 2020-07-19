package taskB;

import common.Triple;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;

import static taskB.Date.Month;


class Lecture6TaskB{
	public static void main(String[] args){
		taskB();
	}
	private static void taskB(){
		// Создайте 5 разных человек.
		final int PERSONS_NUMBER = 5;
		final Person[] persons = new Person[PERSONS_NUMBER];
		for(int i=persons.length; 0<=--i;){
			persons[i] = new Person(
					genRandomString(
					2+genRandomInt(3)),
					genRandomString(
					3+genRandomInt(5)));
		}
		// Один человек может посещать одно и то же место два раза в один день.
		final int PLACES_NUM_PER_DAY_MAX = 2; //may ONLY either 1 or 2
		//Пусть каждый человек посетит 300 мест в случайные даты в течение одного года.
		final int SCHEDULED_DATES_NUM_PER_YEAR = 300;
		//Определите 3 разных места, которые могут посещать люди
		// (например: дом, работа, библиотка)
		final String[] places = new String[]{
				"Lover", "Work", "Lib"
		};
		final int PLACES_NUM = places.length;

		//-----------------------------------------------------------
		final var set  = new HashSet   <Triple<Person, Date, String>>();
		final var list = new LinkedList<Triple<Person, Date, String>>();

		//-----------------------------------------------------------
		final int year = genRandomInt(2030 - 2020) + 2020;
		for(var person: persons){

			// let's create a schedule for the person for only this 1 year :
			final var dates = new HashSet<Date>(SCHEDULED_DATES_NUM_PER_YEAR);
			// loop until 300 unique days added
			while(SCHEDULED_DATES_NUM_PER_YEAR != dates.size()){
				final Month month = Month.values()[genRandomInt(Month.values().length)];
				final int day = 1+genRandomInt(month.getMaxDaysNumber());
//System.out.println("m.d = " + month + " " + day);
				Date date = new Date(day, month, year);
				dates.add(date);
			}
//System.out.println("dates" + dates.size() + "->" + dates);

			//let's fill the all people's scheduler book:
			dates.forEach((date) -> {
//System.out.println("m.d = " + date.getMonth() + " " + date.getDay());
				for(int pInd=PLACES_NUM; 0<=--pInd;){ //"String[index] places" indexed cycle
					final var triple
							= new Triple<>(person, date, places[pInd]);
					int times //how Many Times In One Place Per Day
							= genRandomInt(1+PLACES_NUM_PER_DAY_MAX);
					while(0<=--times){
						final var added = set.add(triple);
//System.out.println(" " + "for place[" + pInd + "]: " + places[pInd]
// + " " + (added?"YES":"xxx") + " Tr: " + person .getFirstName());
						list.add(triple);
					}
				}
			});
//System.out.println(person);
		}

		//-----------------------------------------------------------
		System.out.println("\nSorted scheduler only UNIQUE(Set) records = " + set.size());
		printHeader();
		set.stream().sorted((rec1, rec2) -> { //noinspection Convert2MethodRef
			return comp(rec1, rec2);
		}).forEach(Lecture6TaskB::printRecord);

		System.out.println("\nSorted scheduler all (List) records = " + list.size());
		printHeader();
		list.stream().sorted((rec1, rec2) -> { // noinspection Convert2MethodRef
			return comp(rec1, rec2);
		}).forEach(Lecture6TaskB::printRecord);

		if(set.size()==list.size()){
			System.out.println("\nНи один человек не посещал одно и то же место два раза в один день");
		}
	}
	private static int comp(final Triple<Person, Date, String> rec1
	                      , final Triple<Person, Date, String> rec2){
		int c;
		c =	rec1.getFirst().getFirstName().compareTo(
				rec2.getFirst().getFirstName()
		); if(0!=c) return c;
		c =	rec1.getFirst().getLastName().compareTo(
				rec2.getFirst().getLastName()
		); if(0!=c) return c;
		c =	rec1.getSecond().getYear() -
				rec2.getSecond().getYear()
		; if(0!=c) return c;
		c =	rec1.getSecond().getMonth().ordinal() -
				rec2.getSecond().getMonth().ordinal()
		; if(0!=c) return c;
		c =	rec1.getSecond().getDay() -
				rec2.getSecond().getDay()
		; if(0!=c) return c;
		return rec1.getThird().compareTo(
			rec2.getThird()
		);
	}
	private static String getFormatString(){
		return "|%-9s%-9s|%-5s%-9s%-2s|%-6s|\n";
	}
	private static void printLine(){
		System.out.println("--------------------------------------------");
	}
	private static void printHeader(){
		printLine();
		System.out.printf( getFormatString(), "Who:","", "When:","","", "Where:");
		printLine();
	}
	private static void printRecord(final Triple<Person, Date, String> record){
		final var firstName = record.getFirst().getFirstName();
		final var lastName  = record.getFirst().getLastName();
		final var year1     = record.getSecond().getYear();
		final var month     = record.getSecond().getMonth();
		final var day       = record.getSecond().getDay();
		final var place     = record.getThird();
		System.out.printf( getFormatString(),
				firstName,
				lastName,
				year1,
				month,
				day,
				place
		);
	}

	private static String genRandomString(int len){
		char[] ca = new char[len];
		while(0<--len){
			ca[len] = genRandomChar(false);
		}
		ca[0] = genRandomChar(true);
		return new String(ca);
	}
	private static char genRandomChar(final boolean isCapital){
		return (char)((int)(isCapital?'A':'a') + genRandomInt(26));
	}
	private static int genRandomInt(final int max){
		return new Random().nextInt(max);
	}
}

/* output:
Sorted scheduler only UNIQUE(Set) records = 3008
--------------------------------------------
|Who:              |When:           |Where:|
--------------------------------------------
|Jpt      Jtgay    |2024 JANUARY  2 |Lover |
|Jpt      Jtgay    |2024 JANUARY  2 |Work  |
|Jpt      Jtgay    |2024 JANUARY  2 |Lib   |
|Jpt      Jtgay    |2024 JANUARY  7 |Lover |
|Jpt      Jtgay    |2024 JANUARY  9 |Work  |
|Jpt      Jtgay    |2024 JANUARY  10|Lib   |
....

Sorted scheduler all (List) records=4503
--------------------------------------------
|Who:              |When:           |Where:|
--------------------------------------------
|Jpt      Jtgay    |2024 JANUARY  2 |Lover |
|Jpt      Jtgay    |2024 JANUARY  2 |Lover |
|Jpt      Jtgay    |2024 JANUARY  2 |Work  |
|Jpt      Jtgay    |2024 JANUARY  2 |Work  |
|Jpt      Jtgay    |2024 JANUARY  2 |Lib   |
|Jpt      Jtgay    |2024 JANUARY  7 |Lover |
|Jpt      Jtgay    |2024 JANUARY  7 |Lover |
|Jpt      Jtgay    |2024 JANUARY  9 |Work  |
|Jpt      Jtgay    |2024 JANUARY  10|Lib   |
|Jpt      Jtgay    |2024 JANUARY  10|Lib   |
*/
/*
[7/10 9:09 PM] andrey.tarasov@mera.com


Задание 1. Пара-Тройка кортежей
Создайте два класса для хранения кортежей - упорядоченного списка элементов определенных типов
Pair - содержит пару таких элементов.
Triple - содержит тройку таких элементов.
у каждого класса создайте методы, которые возвращают элементы на определенных позициях.
 - getFirst
 - getSecond
 - getThird (только для Triple)
У каждого класса создайте конструктор со 2(Pair) или тремя (Triple) параметрами нужного типа.
Для каждого класса определите методы equals и hashCode.
Пример использования
Pair<String, Integer> lastNameToAge = new Pair<> ("Пупкин", 18);
String lastName = lastNameToAge.geFirst();
Integer age = lastNameToAge.getSecond();
Pair<String, List<String>> lastNameToPhoneNumbers = new Pair<>("Пупкин", Arrays.asList("+7 831 2112233", "+7 920 000 22 22"))
String lastName = lastNameToPhoneNumbers.geFirst();
List<String> phoneNumbers = lastNameToPhoneNumbers.getSecond();
Аналогично  с Triple, только параметра типа и параметров констркторов должно быть 3.

Задание А. Работа с парами:
Создайте класс Animal с полями имя(String) и тип животного (тоже String)
Создайте список, состоящий из нескольких пар: животное и любимое блюдо(String) этого животного.

Создайте метод feedAnimals(List<Pair<Animal,String>>);
ЛОгика работы этого метода такая:
Для каждого животного выводится надпись:
"Животное %ИМЯ с радостью съедает %БЛЮДО
+ в этом методе сгенерируйте случайное число от 0 до размера списка с животными.
Это - номер животного, которое сегодня получит двойною порцию любимой еды.
Для такого животного выведите надпись
"Счастливое животное %имя получает двойную порцию %БЛЮДО"
Замечания: вы можете справедливо заметить, что любимое блюдо вполне можно было объявить полем класса.
Но иногда удобнее дополнительное поле вынести за рамки класса, например:
- Когда  у вас есть библиотечный класс и вы не можете его менять.
- Когда поле нужно использовать один раз, чтобы не засорять класс доп. полями.

Задание Б.Список посещений
Создайте класс Person  с именем и фамилией
Создайте класс Date с полями число, месяц, год.
Для хранения месяца используйте enum.
Для хранения максимальной даты в каждом месяце используйте поле в enum.
Високосные года не учитывайте.
Класс Date - неизменяемый, у него должен быть конструктор со всеми параметрами и геттеры.
Задача - сгенерировать случайный список посещений используя
HashSet<Triple<Person,Date,String>>
и
List<Triple<Person,Date,String>>

String в данном случае - это место посещения.
 Один человек может посещать одно и то же место два раза в один день.
 Но должна быть возможность просто вывести список, без дубликатов.

 Создайте 5 разных человек.
 Определите 3 разных места, которые могут посещать люди (например: дом, работа, библиотка)
 Пусть каждый человек посетит 300 мест в случайные даты в течение одного года.

 Запишите каждое посещение, используя тройку: человек, дата, место в HashSet и в List
 Если размеры List'a и Set'a  совпадают - выведите на экран надпись "Ни один человек не посещал одно и то же место два раза в один день".

 Иначе - ничего не выводите.
-----------

//Определите 3 разных места, которые могут посещать люди
//(например: дом, работа, библиотка)
Не совсем ясно, что значит «посещает» (для каких/скольких мест расписание посещений)
•	Т.е. чел дома не живет, и посещает свой дом (ну и работу и либу)
Таким образом: всего 4 места, где он обитает?
•	Или все же 3 места (включая дом), где чел может обитать,


// Пусть каждый человек посетит 300 мест в случайные даты в течение одного года.
Этот вопрос немного пересекается с предыдущим:
- Чел. обычно обитает вне дома, но 300 раз в год сутки обязан посетить дом, работу и/или либу?
- Чел. обычно обитает дома, но 300 раз в год отлучается на работу и/или либу, а дом считается местом проживания, но не местом посещения?
Если дома просидел  - значит ничего не посещал? А если не просидел, т.е. посещал – значит это «день посещения»?

//Один человек может посещать одно и то же место два раза в один день.
Паралельный вопрос:
Он из дома посетил работу – это посещение 1 места (работы)? Или уже 2 места посещенных? (дом  и работа)
Или
Он из дома посетил работу – это посещение 1 места? Или уже 2 места? (дом и работа)

Прим: Так то без разницы, но м.б. у тебя есть какие авто-тесты проверки
и тогда я его завалю, как не прочитавший задание внимательно :)

Лучше пример аля:
Есть 3 места для посещений (дом?) в течении года
Чел в течении 1 дня посетил:
из дома либу, потом работу и снова либу, вернулся домой
и снова хотел, но ему нельзя ехать в работу, т.к. надо потом домой (а это уже 3 раза дома)
Это значит в этот день и только для этого дня:
- он сделал сколько посещений в день (валидно, общее кол-во раз не важно, хоть 100500)
- он посетил только 2 места (валидно, без разницы сколько мест, хоть 100500)
- он посетил уникальное м.
        - либу 2 раза(валидно, 2 раза в день, третий раз он не поехал)
        - работа 1 раз(валидно)
        - дом (вернулся БЫ 3 раз домой, и это 3 посещение?)

--
 На самом деле задание немного корявое – но идея в том, что нужно составить списки посещений.
Т.е. человек зашел в какое-то место – отметился. Больше никакой логики не надо – живет он дома или не живет, это без разницы.

Если вызывает вопросы именно дом, то его можно исключить.
Оставить «Работа, библиотека, шиномонтаж»

На выходе должно быть:

Иванов                25.10.2020          Библиотека
Иванов                27.10.2020          Дом
Иванов                25.10.2020          Работа
Петров                26.10.2020          Библиотека
…


Никаких ограничений на количество посещений нет, теоретически, все 300 раз человек может зайти в одно и то же место, в один и тот же день.
Иванов                25.10.2020          Библиотека
Иванов                25.10.2020          Библиотека
Иванов                25.10.2020          Библиотека
Иванов                25.10.2020          Библиотека
…. <300 раз>

Но должна быть возможность вывести только уникальные посещения:
Иванов                25.10.2020          Библиотека
<только одна строка >

 */
