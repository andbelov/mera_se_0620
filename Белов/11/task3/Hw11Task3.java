package task3;

import task3.Lecture.LectureType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
	final int NUMBER_OF_TOP_MOST_ATTENDED_LECTURES = 3;
	final int NUMBER_OF_TOP_ATTENDING_STUDENTS = 3;
	Task(){
		System.out.println("\n== Statistics list ==");
		test_cases(); // function for my debugging only
		//random_cases(); // HW release function

		System.out.println("\n== List of " + LECTURES_NUM + " lectures  ==");
		lectures.forEach(q -> System.out.println(q.info()));

		System.out.println("\n== List of " + STRUDENTS_NUM + " students ==");
		students.forEach(q -> System.out.println("\n" + q.giveLecturesAndDates()));

		System.out.println("\n== Statistics list ==");
		print_1_StudentsAttendingMathOnce();
		print_2_AttendedLecturesNumberForEachStudent();
		print_3_MostAttendedLectures(NUMBER_OF_TOP_MOST_ATTENDED_LECTURES);
		print_4_MostAttendingStudents(NUMBER_OF_TOP_ATTENDING_STUDENTS);
		print_5_AttendedLecturesNumberForEachStudent();
	}

	private void test_cases(){
		//setSeed(4); //for my debugging only

		lectures.add(new Lecture(LectureType.PHYS, LocalDate.of(2000, 1, 1)));
		lectures.add(new Lecture(LectureType.PHIL, LocalDate.of(2000, 1, 1)));
		lectures.add(new Lecture(LectureType.MATH, LocalDate.of(2000, 1, 1)));
		lectures.add(new Lecture(LectureType.HIST, LocalDate.of(2000, 1, 1)));
		lectures.add(new Lecture(LectureType.ENGL, LocalDate.of(2000, 1, 1)));

		lectures.add(new Lecture(LectureType.ENGL, LocalDate.of(2000, 1, 2)));
		lectures.add(new Lecture(LectureType.MATH, LocalDate.of(2000, 1, 3)));
		lectures.add(new Lecture(LectureType.HIST, LocalDate.of(2000, 1, 4)));

		final Student a = new Student("Аа");	students.add(a);
		final Student b = new Student("Бэ");	students.add(b);
		final Student c = new Student("Цэ");	students.add(c);
		final Student d = new Student("Дэ");	students.add(d);

		a.getLectures().add(lectures.get(0));
		a.getLectures().add(lectures.get(1));

		b.getLectures().add(lectures.get(1));
		b.getLectures().add(lectures.get(2));
		b.getLectures().add(lectures.get(4));
		b.getLectures().add(lectures.get(3));

		c.getLectures().add(lectures.get(0));
		c.getLectures().add(lectures.get(7));
		c.getLectures().add(lectures.get(6));
		c.getLectures().add(lectures.get(5));
		c.getLectures().add(lectures.get(4));
		c.getLectures().add(lectures.get(1));
		c.getLectures().add(lectures.get(3));
		c.getLectures().add(lectures.get(2));

		d.getLectures().add(lectures.get(0));
	}
	private void random_cases(){
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
			students.add(new Student(giveRandomString(2)
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
						+ student.getLectures().size() + " лекции"));
	}
	private void print_3_MostAttendedLectures(final int topNum){
		System.out.println("\n3. Выведите название дисциплин," +
				" имеющих наибольшее количество посещений.\n" +
				"Если два разных студента посещают одну лекцию" +
				" в один день, то это считается как два посещения.");
		System.out.println("\nСписок ограничен кол-вом " + topNum);
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
				.sorted(Map.Entry.<LectureType, Long>comparingByValue()
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
		System.out.println("\nСписок ограничен кол-вом " + topNum);
		//plan to map students to group of map of attended date/number
		// of their lecture date and to take max count of each
		final Map<String, Map<LocalDate, Long>> studentsGroupedDatesWithMaxCount
				= giveStudentsWithAttendedLecturesCountPerDate();
		//for debug: System.out.println(studentsGroupedDatesWithMaxCount);
		studentsGroupedDatesWithMaxCount.entrySet().stream()
				//.sorted(Map.Entry.comparingByValue().reversed())
				.limit(topNum)
				.forEach(m -> System.out.println(
						"Студент " + m.getKey() + " посетил "
								+ m.getValue()
								.entrySet().stream()
								//.sorted(Map.Entry.comparingByValue())
								.collect(toMap(Map.Entry::getKey,
										Map.Entry::getValue
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
		students.stream()
				.flatMap(student -> student.getLectures().stream())
				.collect(groupingBy(lecture -> lecture.getLectureTitle(), counting()))
				.entrySet().forEach(c-> System.out.println(c))
		;
	}
}