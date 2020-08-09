package classesForSerialization;

import annotations.*;

@XmlClassName(className = "Cat")
public class Pet{
	@XmlAnnotation(tag = "Name")
	String name;
	@XmlAnnotation(tag = "Age")
	int age;
	@XmlAnnotationRestricted(tag = "Owner")
	String owner;

	public Pet(){
	}
	public Pet(final String name, final int age){
		this.name = name;
		this.age = age;
		this.owner = "бездомный";
	}
	public Pet(final String name, final int age, final String owner){
		this.name = name;
		this.age = age;
		this.owner= owner;
	}
	@Override
	public String toString(){
		return "Pet{" +
				"name='" + name + '\'' +
				", age=" + age +
				", owner='" + owner + '\'' +
				'}';
	}
}
