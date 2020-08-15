package task1;

import java.util.ArrayList;
import java.util.Collections;

import static util.Util11.giveRandomInBound;
import static util.Util11.giveRandomString;

public class Hw11Task1{
	public static void main(String[] args){
		System.out.println("-- Persons coming --");
		ArrayList<Person> persons = new ArrayList<>();
		for(int i=10; 0<=--i;){
			persons.add(new Person(giveRandomString(6),giveRandomInBound(10, 100)));
		}

		System.out.println("-- Persons original order --");
		persons.forEach(p -> p.info());

		System.out.println("-- Persons sorted by name --");
		Collections.sort(persons, (p1, p2) -> p1.getName().compareTo(p2.getName()));
		persons.forEach(p -> p.info());

		System.out.println("-- Persons sorted by age --");
		Collections.sort(persons, (p1, p2) -> p1.getAge() - p2.getAge());
		persons.forEach(p -> p.info());
	}
}

/*
-- Persons coming --
-- Persons original order --
Person Uqtcoy, age 91
Person Mxmyon, age 76
Person Urwizj, age 15
Person Gqloeo, age 49
Person Gztcqc, age 82
Person Ulfkyb, age 10
Person Pghobe, age 34
Person Ntguic, age 12
Person Tsifci, age 48
Person Snadxc, age 93
-- Persons sorted by name --
Person Gqloeo, age 49
Person Gztcqc, age 82
Person Mxmyon, age 76
Person Ntguic, age 12
Person Pghobe, age 34
Person Snadxc, age 93
Person Tsifci, age 48
Person Ulfkyb, age 10
Person Uqtcoy, age 91
Person Urwizj, age 15
-- Persons sorted by age --
Person Ulfkyb, age 10
Person Ntguic, age 12
Person Urwizj, age 15
Person Pghobe, age 34
Person Tsifci, age 48
Person Gqloeo, age 49
Person Mxmyon, age 76
Person Gztcqc, age 82
Person Uqtcoy, age 91
Person Snadxc, age 93
 */