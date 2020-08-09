import classesForSerialization.Person;
import classesForSerialization.Pet;
import serialization.xml.XmlConvertor;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

class Lecture8Task1{
	public static void main(String[] args)
			throws IllegalAccessException, InvocationTargetException, InstantiationException{
		System.out.println("==== serializing FROM CLASSES TO XML ====");
		final String xmlPersons = serializePersons();
		final String xmlPets = serializePets();
		System.out.println(xmlPersons);
		System.out.println(xmlPets);

		System.out.println("==== deserializing FROM XML TO CLASSES ====");
		for(Object obj: deserialize (xmlPersons, Person.class)){
			System.out.println(obj);
		}
		System.out.println(deserialize(xmlPets, Pet.class).toString());
	}
	private static String serializePersons()
			throws IllegalAccessException{
		StringBuilder sb = new StringBuilder();
//1 object:
		sb.append(new XmlConvertor().serialize(new Person(
				"Я", "ТРОЛЛЬ", 999,
				"Трамп", "Макаронный Бог")));
//Several objects:
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("Ты", "НИКОМУ НЕ СКАЖУ", 111, "Против Трампа", "Бабтист"));
		persons.add(new Person("Он", "ПАРОЛЬ ДОЛЖЕН БЫТЬ ОЧЕНЬ ДЛИННЫЙ", 222));
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
	private static ArrayList<Object> deserialize(final String serializedText
			, final Class<?> clazz)
			throws IllegalAccessException, InvocationTargetException, InstantiationException{
		return (new XmlConvertor()).deserialize(serializedText, clazz);
	}
}
/* output:
==== serializing FROM CLASSES TO XML ====
<Leader>
<Name>Я</Name>
<Password encrypted Aes-256>ԢԠԞԛԛԬ</Password encrypted Aes-256>
<Age>999</Age>
<Politic party>Трамп</Politic party>
</Leader><Leader>
<Name>Ты</Name>
<Password encrypted Aes-256>ԝԘԚԞԜԣĠԝԕĠԡԚԐԖԣ</Password encrypted Aes-256>
<Age>111</Age>
<Politic party>Против Трампа</Politic party>
</Leader><Leader>
<Name>Он</Name>
<Password encrypted Aes-256>ԟԐԠԞԛԬĠԔԞԛԖԕԝĠԑԫԢԬĠԞԧԕԝԬĠԔԛԘԝԝԫԙ</Password encrypted Aes-256>
<Age>222</Age>
<Politic party>no</Politic party>
</Leader>
<Cat>
<Name>Васька</Name>
<Age>9</Age>
<Owner>бездомный</Owner>
</Cat><Cat>
<Name>Жучка</Name>
<Age>5</Age>
<Owner>Ты</Owner>
</Cat><Cat>
<Name>Бобик</Name>
<Age>6</Age>
<Owner>Он</Owner>
</Cat>
==== deserializing FROM XML TO CLASSES ====
Person{name='Я', pass='ТРОЛЛЬ', age=999, party='Трамп', religion='верю/не верю'}
Person{name='Ты', pass='НИКОМУ НЕ СКАЖУ', age=111, party='Против Трампа', religion='верю/не верю'}
Person{name='Он', pass='ПАРОЛЬ ДОЛЖЕН БЫТЬ ОЧЕНЬ ДЛИННЫЙ', age=222, party='no', religion='верю/не верю'}
[Pet{name='Васька', age=9, owner='бездомный'}, Pet{name='Жучка', age=5, owner='Ты'}, Pet{name='Бобик', age=6, owner='Он'}]
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