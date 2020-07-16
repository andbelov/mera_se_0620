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
