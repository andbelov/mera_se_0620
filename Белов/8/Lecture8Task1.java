import persons.Person;
import persons.Pet;
import xml.XmlConvertor;

import java.util.ArrayList;
import java.util.List;

class Lecture8Task1{
	public static void main(String[] args)
			throws IllegalAccessException{
		System.out.println("==== serializing FROM CLASSES TO XML ====");
		final String xmlPersons = serializePersons();
		final String xmlPets = serializePets();
		System.out.println(xmlPersons);
		System.out.println(xmlPets);

		System.out.println("==== deserializing FROM XML TO CLASSES ====");
		System.out.println(deserialize(xmlPersons, Person.class));
		System.out.println(deserialize(xmlPets, Pet.class));
	}
	private static String serializePersons()
			throws IllegalAccessException{
		StringBuilder sb = new StringBuilder();
//1 object:
		final Person aPerson = new Person("Я", "паролллльь", 999, "Трамп", "Макаронный Бог");
		sb.append(new XmlConvertor().serialize(aPerson));
//Several objects:
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("Ты", "passA", 111));
		persons.add(new Person("Он", "passB", 222));
		for(Object o : persons){
			sb.append(new XmlConvertor().serialize(o));
		}
		return sb.toString();
	}
	private static String serializePets()
			throws IllegalAccessException{
		StringBuilder sb = new StringBuilder();
//1 object:
		final Pet cat = new Pet("Васька", 9);
		sb.append(new XmlConvertor().serialize(cat));
//Several objects:
		List<Pet> pets = new ArrayList<>();
		pets.add(new Pet("Жучка", 5, "Ты"));
		pets.add(new Pet("Бобик", 6, "Он"));
		for(Object o : pets){
			sb.append(new XmlConvertor().serialize(o));
		}
		return sb.toString();
	}
	private static String deserialize(final String serializedText
			, final Class<?> clazz) throws IllegalAccessException{
		return (new XmlConvertor()).deserialize(serializedText, clazz);
	}
}
/* output:

*/
/*
Задание 8.Аннотации и рефлексия
Аннотированная де\сериализация.
Создайте метод String serialize(Collection<?> object),
 который может записывать любые объекты в формате XML:
- Объект начинается с тэга, описывающий имя класса.
- Свойства объектов заключаются в открывающие и закрывающие тэги
- Создайте аннотацию @XmlName, чтобы определять имя свойства
- Создайте аннотацию @XmlIgnore, чтобы исключить поля из сериализации
- Создайте аннотацию @XmlTypeName, чтобы исключить поля из сериализации
Например, класс
@XmlTypeName("Человек")
class Person {
    @XmlName("Имя")
    String firstName="Vasya";
    @XmlName("Возраст")
    double age=12
    @XmlIgnore
    String password;
}
будет выглядеть так
<Человек>
    <Имя>Vasya</Имя>
    <Возраст>12</Возраст>
</Человек>

Создайте класс, который может читать любой объект в формате XML.
например ,deserialize(<пример выше>, Person.class) вернет
экземпляр Person{name="Vasya",age=12,password=null}

Создайте несколько объектов разных классов, запишите и прочитайте их из XML.
Для простоты реализации договоримся, что сериализоваться\десериализоваться
 будут только поля следующих типов: строки, числа, Boolean.
Вложенные сложные объекты можно игнорировать. Для того, чтобы класс был
сериализуемым - обязательно, чтобы у него был конструктор без параметров.

*/