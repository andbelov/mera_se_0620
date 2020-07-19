package taskA;

import common.Pair;
import common.Triple;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

class Lecture6TaskA{
	public static void main(String[] args){
		testPair();
		task();
	}
	private static void testPair(){
		Pair<String, Integer> pairNameWithAge
				= new Pair<> ("Пупкин", 18);
		String name = pairNameWithAge.getFirst();
		Integer age = pairNameWithAge.getSecond();
		Pair<String, List<String>> pairNameWithPhones
				= new Pair<>("Пупкин New", Arrays.asList(
						"+7 831 2112233",
						"+7 920 000 22 22"));
		name = pairNameWithPhones.getFirst();
		System.out.println(pairNameWithPhones);
		List<String> phones = pairNameWithPhones.getSecond();
		var triple = new Triple<>(name, phones
				, new Pair<>("Str", age));
		System.out.println(triple.getFirst() + "" + triple.getSecond() + " " + triple.getThird());

	}
	private static void task(){
		List<Pair<Animal,String>> list = new LinkedList<>();
		for(int i=0; i<3; i++){
			list.add(new Pair<>(
					new Animal("Animal_"+i, "type_" + i)
					, "food_" + (char)((int)'A'+i)));
		}
		feedAnimals(list);

	}
	private static void feedAnimals(List<Pair<Animal,String>> list){
		for(var record: list){
			final var name = record.getFirst().getName();
			final var food = record.getSecond();
			System.out.println("Животное " + name
					+ " с радостью съедает " + food);
		}
		Random random = new Random();
		final int number = random.nextInt(list.size());
		final var record = list.get(number);
		final Animal animal = record.getFirst();
		System.out.println("Счастливое животное " + animal.getName()
				+ " получает двойную порцию " + record.getSecond());
	}
}

/* output:
Pair{first=Пупкин New, second=[+7 831 2112233, +7 920 000 22 22]}
Пупкин New[+7 831 2112233, +7 920 000 22 22] Pair{first=Str, second=18}
Животное Animal_0 с радостью съедает food_A
Животное Animal_1 с радостью съедает food_B
Животное Animal_2 с радостью съедает food_C
Счастливое животное Animal_0 получает двойную порцию food_A*/
/*
Задание А. Работа с парами:
Создайте класс Animal с полями имя(String) и тип животного (тоже String)
*/