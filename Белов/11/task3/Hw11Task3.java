package task3;

import task3.Lecture.LectureType;

import java.time.LocalDate;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;
import static util.Util11.*;

public class Hw11Task3{
	public static void main(String[] args){
		new Task();
	}
}

class Task{
	final int LECTURES_NUM = 7;
	final List<Lecture> lectures = new ArrayList<>();
	final int STRUDENTS_NUM = 10;
	List<Student> students = new ArrayList<>();
	// нет времени на "хотелку": всех студентов брать по id из мапы,
	// что бы не гонять String вместо int..  пардон Integer..
	// но наверное это экономия на спичках опять :-)
	// Map<Integer, Student> students = new HashMap<>();
	final int NUMBER_OF_TOP_MOST_ATTENDED_LECTURES = 3;
	final int NUMBER_OF_TOP_ATTENDING_STUDENTS = 3;
	Task(){
		System.out.println("\n== COURSES ==");
		test_cases(); // function for my debugging only
		//random_cases(); // HW release function

		System.out.println("\n== List of " + LECTURES_NUM + " lectures  ==");
		lectures.forEach(q -> System.out.println(q.info()));

		System.out.println("\n== List of " + STRUDENTS_NUM + " students ==");
		students.forEach(q -> System.out.println("-- " + q.giveLecturesAndDates()));

		System.out.println("\n== Statistics list ==");
		print_1_StudentsAttendingMathOnce();
		print_2_AttendedLecturesNumberForEachStudent();
		print_3_MostAttendedLectures(NUMBER_OF_TOP_MOST_ATTENDED_LECTURES);
		print_4_MostAttendingStudents(NUMBER_OF_TOP_ATTENDING_STUDENTS);
		print_5_AttendedLecturesNumberForEachStudent();
	}

	private void test_cases(){
		lectures.add(new Lecture(LectureType.PHYS, LocalDate.of(2000, 1, 1)));
		lectures.add(new Lecture(LectureType.PHIL, LocalDate.of(2000, 1, 1)));
		lectures.add(new Lecture(LectureType.MATH, LocalDate.of(2000, 1, 1)));
		lectures.add(new Lecture(LectureType.HIST, LocalDate.of(2000, 1, 1)));
		lectures.add(new Lecture(LectureType.ENGL, LocalDate.of(2000, 1, 1)));

		lectures.add(new Lecture(LectureType.ENGL, LocalDate.of(2000, 1, 2)));
		lectures.add(new Lecture(LectureType.MATH, LocalDate.of(2000, 1, 3)));
		lectures.add(new Lecture(LectureType.HIST, LocalDate.of(2000, 1, 4)));

		int id = 0;
		final Student a = new Student(id++, "Аа");	students.add(a);
		final Student b = new Student(id++, "Бэ");	students.add(b);
		final Student c = new Student(id++, "Цэ");	students.add(c);
		final Student d = new Student(id++, "Дэ");	students.add(d);

		a.getLectures().add(lectures.get(0));
		a.getLectures().add(lectures.get(1));
		b.getLectures().add(lectures.get(3));

		b.getLectures().add(lectures.get(0));
		b.getLectures().add(lectures.get(2));
		b.getLectures().add(lectures.get(3));

		c.getLectures().add(lectures.get(0));
		c.getLectures().add(lectures.get(3));
		c.getLectures().add(lectures.get(5));
		c.getLectures().add(lectures.get(6));
		c.getLectures().add(lectures.get(7));

		d.getLectures().add(lectures.get(0));
	}
	private void random_cases(){
		//setSeed(4); //for my debugging only
		createLectures();
		createStudents();
	}
	private void createLectures(){
		for(int i = LECTURES_NUM; 0 <= --i; ){
			lectures.add(new Lecture(
					giveRandomEnum(LectureType.class)
					, LocalDate.now().plusDays(giveRandom(2))));
		}
	}
	private void createStudents(){
		for(int i = STRUDENTS_NUM; 0 <= --i; ){
			students.add(new Student(i,giveRandomString(2)
					+ '-' + giveRandomString(2)
					+ '-' + giveRandomInBound(10, 100)
					, lectures));
		}
	}
	private void print_1_StudentsAttendingMathOnce(){
		System.out.println("\n1. Выведите список студентов, " +
				"которые хоть раз посещали матанализ.");
		students.stream().filter(student -> student.getLectures().stream()
				.anyMatch(lecture ->
						LectureType.MATH == lecture.getLectureType()
				)
		).forEach(student -> System.out.println(student.getName() + " посетил "
				+ LectureType.MATH.getLectureTitle()));
	}
	private void print_2_AttendedLecturesNumberForEachStudent(){
		System.out.println("\n2. Выведите статистику посещений для каждого" +
				" студентам в формате: имя - количество посщенных лекций.");
		students.forEach(student -> System.out.println(
				student.getName() + " посетил "
						+ giveLecturesSet(student).size() + " лекции"));
	}
	private void print_3_MostAttendedLectures(final int topNum){
		System.out.println("\n3. Выведите название дисциплин," +
				" имеющих наибольшее количество посещений.\n" +
				"Если два разных студента посещают одну лекцию" +
				" в один день, то это считается как два посещения.");
		System.out.println("Список ограничен кол-вом " + topNum);
		//take all lectures:
		final Stream<Lecture> lectureStream
				= students.stream()
				.flatMap(student -> student.getLectures().stream());
		//group lectures by type and count them in the each group:
		final Map<LectureType, Long> grouped
				= lectureStream.collect(
				groupingBy(Lecture::getLectureType, counting()));
		//for debug: grouped.forEach((type,count)->System.out.println(type.name() + " " + count));
		//sort by count, limit by 3:
		grouped.entrySet().stream()
				.sorted(Entry.<LectureType, Long>comparingByValue()
						.reversed())
				.limit(topNum)
				.forEach(lectureType -> System.out.println(
						"Лекция \"" + lectureType.getKey().getLectureTitle()
								+ "\" посещена " + lectureType.getValue()
								+ " раз"));
	}
	private void print_4_MostAttendingStudents(final int topNum){
		System.out.println("\n4. Выведите имена студентов," +
				" которые посетили наибольшее количество лекций в день.");
		//System.out.println("Список ограничен кол-вом " + topNum);
		//plan to map students to group of map of attended date/number
		// of their lecture date and to take max count of each
		//for debug: System.out.println(giveStudentsWithAttendedLecturesCountPerDate());
		giveStudentsWithAttendedLecturesCountPerDate().entrySet().stream()
				// по идее, надо отсортировать в обр порядке и ограничить 3 поз.
				//.sorted(Map.Entry.comparingByValue().reversed())
				//.limit(topNum)
				.forEach(studMap -> System.out.println(
						"Студент " + studMap.getKey() + " посетил "
								+ studMap.getValue()
								.entrySet().stream()
								//.sorted(Map.Entry.comparingByValue())
								.collect(toMap(Entry::getKey,
										Entry::getValue
										, (e1, e2) -> e1, LinkedHashMap::new
								))
								.values()
								//Student A attended [2]
								//Student B attended [4]
								//Student C attended [1, 1, 1, 5]
								.stream()
								.reduce(0L, Long::max)
				));
	}
	private Map<String, Map<LocalDate, Long>> giveStudentsWithAttendedLecturesCountPerDate(){
		// put gotten map to map with student name
		return students.stream().collect(
				toMap(Student::getName, this::giveStudentAttendedLecturesCountPerDate));
	}
	private Map<LocalDate, Long> giveStudentAttendedLecturesCountPerDate(final Student student){
		// give (for a student) attended lectures count per a date
		return student.getLectures().stream().collect(
				groupingBy(Lecture::getDate, counting()));
	}
	private void print_5_AttendedLecturesNumberForEachStudent(){
		System.out.println("\n5. Выведите статистику по курсам в формате:\n" +
				"название курсов - количество разных студентов, которые\n" +
				" посетили хотя бы одно занятие. (т.е. в лучше случае это будет 10).");
		//debug: map students with lectures as Map<stud, Set<lectType>>
		//{Аа=[PHYS, PHIL], Цэ=[MATH, PHYS, HIST, ENGL],
		// Дэ=[PHYS], Бэ=[MATH, PHYS, HIST]}
		final Map<String, Set<LectureType>> studNamesWithLectures = students.stream()
				.collect(toMap(Student::getName, this::giveLecturesSet));
		//--------------------
		//final Map<LectureType, Set<String>> lecturesWithStudNames =
		//at first collect all lectures in set as Set<lectType>
				students.stream()
				// all lectures in plain list
				.flatMap(student -> giveLecturesSet(student).stream())
				.collect(toSet())
				// debug [MATH, PHYS, PHIL, HIST, ENGL]
				//debug System.out.println(lectures);
				//Map<String, Set<String>> lectNamesWithStudNames =
				.stream()
				.collect(toMap(lectNames -> lectNames, val ->
						students.stream()
								.collect(toMap(Student::getName, this::giveLecturesSet))
								.entrySet()
								.stream()
								.filter(mapEnt -> mapEnt.getValue().contains(val))
								.map(Entry::getKey)
								.collect(toSet())
				))
				// debug {MATH=[Цэ, Бэ], PHYS=[Аа, Цэ, Дэ, Бэ],
				// PHIL=[Аа], HIST=[Цэ, Бэ], ENGL=[Цэ]}
				//System.out.println(lecturesWithStudNames);
				.forEach((lecture, students) ->
						System.out.println(lecture.getLectureTitle()
								+ " - " + students.size()));
				//		МатАнализ - 2
				//		Физ-Культ - 4
				//		ФилософиЯ - 1
				//		ИсторияНН - 2
				//		Английскй - 1
	}
	private Set<LectureType> giveLecturesSet(Student student){
		return student.getLectures().stream()
				.map(Lecture::getLectureType)
				.collect(toSet());
	}
}

/* TEST HARDCODED CASES output:
== COURSES ==

== List of 7 lectures  ==
Lecture Физ-Культ scheduled at 2000-01-01
Lecture ФилософиЯ scheduled at 2000-01-01
Lecture МатАнализ scheduled at 2000-01-01
Lecture ИсторияНН scheduled at 2000-01-01
Lecture Английскй scheduled at 2000-01-01
Lecture Английскй scheduled at 2000-01-02
Lecture МатАнализ scheduled at 2000-01-03
Lecture ИсторияНН scheduled at 2000-01-04

== List of 10 students ==
-- Student Аа takes 777 lectures
Lecture ФилософиЯ scheduled at 2000-01-01
Lecture Физ-Культ scheduled at 2000-01-01
-- Student Бэ takes 777 lectures
Lecture Физ-Культ scheduled at 2000-01-01
Lecture МатАнализ scheduled at 2000-01-01
Lecture ИсторияНН scheduled at 2000-01-01
-- Student Цэ takes 777 lectures
Lecture Физ-Культ scheduled at 2000-01-01
Lecture МатАнализ scheduled at 2000-01-03
Lecture Английскй scheduled at 2000-01-02
Lecture ИсторияНН scheduled at 2000-01-01
Lecture ИсторияНН scheduled at 2000-01-04
-- Student Дэ takes 777 lectures
Lecture Физ-Культ scheduled at 2000-01-01

== Statistics list ==

1. Выведите список студентов, которые хоть раз посещали матанализ.
Бэ посетил МатАнализ
Цэ посетил МатАнализ

2. Выведите статистику посещений для каждого студентам в формате: имя - количество посщенных лекций.
Аа посетил 2 лекции
Бэ посетил 3 лекции
Цэ посетил 4 лекции
Дэ посетил 1 лекции

3. Выведите название дисциплин, имеющих наибольшее количество посещений.
Если два разных студента посещают одну лекцию в один день, то это считается как два посещения.
Список ограничен кол-вом 3
Лекция "Физ-Культ" посещена 4 раз
Лекция "ИсторияНН" посещена 3 раз
Лекция "МатАнализ" посещена 2 раз

4. Выведите имена студентов, которые посетили наибольшее количество лекций в день.
Студент Аа посетил 2
Студент Цэ посетил 2
Студент Дэ посетил 1
Студент Бэ посетил 3

5. Выведите статистику по курсам в формате:
название курсов - количество разных студентов, которые
 посетили хотя бы одно занятие. (т.е. в лучше случае это будет 10).
ИсторияНН - 2
Физ-Культ - 4
МатАнализ - 2
ФилософиЯ - 1
Английскй - 1
 */
/* RANDOM CASES output:

== COURSES ==

== List of 7 lectures  ==
Lecture ИсторияНН scheduled at 2020-08-20
Lecture ФилософиЯ scheduled at 2020-08-19
Lecture ФилософиЯ scheduled at 2020-08-20
Lecture Английскй scheduled at 2020-08-19
Lecture МатАнализ scheduled at 2020-08-20
Lecture ИсторияНН scheduled at 2020-08-20
Lecture Физ-Культ scheduled at 2020-08-19

== List of 10 students ==
-- Student Ah-Ce-76 takes 0 lectures
-- Student Hb-Im-48 takes 2 lectures
Lecture ИсторияНН scheduled at 2020-08-20
Lecture Английскй scheduled at 2020-08-19
-- Student Jg-Rk-17 takes 3 lectures
Lecture МатАнализ scheduled at 2020-08-20
Lecture Физ-Культ scheduled at 2020-08-19
Lecture ФилософиЯ scheduled at 2020-08-20
-- Student Sy-Md-44 takes 2 lectures
Lecture ИсторияНН scheduled at 2020-08-20
Lecture Физ-Культ scheduled at 2020-08-19
-- Student Gm-Pq-79 takes 2 lectures
Lecture ИсторияНН scheduled at 2020-08-20
Lecture Физ-Культ scheduled at 2020-08-19
-- Student Co-Wh-66 takes 4 lectures
Lecture ИсторияНН scheduled at 2020-08-20
Lecture Английскй scheduled at 2020-08-19
Lecture ФилософиЯ scheduled at 2020-08-20
-- Student Pj-Zv-57 takes 1 lectures
Lecture ИсторияНН scheduled at 2020-08-20
-- Student Ai-Oh-40 takes 5 lectures
Lecture МатАнализ scheduled at 2020-08-20
Lecture ИсторияНН scheduled at 2020-08-20
Lecture Английскй scheduled at 2020-08-19
-- Student Hj-Mb-60 takes 0 lectures
-- Student Aq-Oe-37 takes 4 lectures
Lecture МатАнализ scheduled at 2020-08-20
Lecture ИсторияНН scheduled at 2020-08-20
Lecture Физ-Культ scheduled at 2020-08-19
Lecture Английскй scheduled at 2020-08-19

== Statistics list ==

1. Выведите список студентов, которые хоть раз посещали матанализ.
Jg-Rk-17 посетил МатАнализ
Ai-Oh-40 посетил МатАнализ
Aq-Oe-37 посетил МатАнализ

2. Выведите статистику посещений для каждого студентам в формате: имя - количество посщенных лекций.
Ah-Ce-76 посетил 0 лекции
Hb-Im-48 посетил 2 лекции
Jg-Rk-17 посетил 3 лекции
Sy-Md-44 посетил 2 лекции
Gm-Pq-79 посетил 2 лекции
Co-Wh-66 посетил 3 лекции
Pj-Zv-57 посетил 1 лекции
Ai-Oh-40 посетил 3 лекции
Hj-Mb-60 посетил 0 лекции
Aq-Oe-37 посетил 4 лекции

3. Выведите название дисциплин, имеющих наибольшее количество посещений.
Если два разных студента посещают одну лекцию в один день, то это считается как два посещения.
Список ограничен кол-вом 3
Лекция "ИсторияНН" посещена 7 раз
Лекция "Английскй" посещена 4 раз
Лекция "Физ-Культ" посещена 4 раз

4. Выведите имена студентов, которые посетили наибольшее количество лекций в день.
Студент Aq-Oe-37 посетил 2
Студент Hb-Im-48 посетил 1
Студент Ai-Oh-40 посетил 2
Студент Sy-Md-44 посетил 1
Студент Gm-Pq-79 посетил 1
Студент Pj-Zv-57 посетил 1
Студент Ah-Ce-76 посетил 0
Студент Hj-Mb-60 посетил 0
Студент Jg-Rk-17 посетил 2
Студент Co-Wh-66 посетил 2

5. Выведите статистику по курсам в формате:
название курсов - количество разных студентов, которые
 посетили хотя бы одно занятие. (т.е. в лучше случае это будет 10).
Английскй - 4
ФилософиЯ - 2
ИсторияНН - 7
МатАнализ - 3
Физ-Культ - 4
 */