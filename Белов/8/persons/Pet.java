package persons;

import xml.annotations.XmlAnnotation;
import xml.annotations.XmlAnnotationRestricted;
import xml.annotations.XmlClassName;

@XmlClassName(className = "Cat")
public class Pet{
	@XmlAnnotation(tag = "Name")
	String name;
	@XmlAnnotation(tag = "Age")
	int age;
	@XmlAnnotationRestricted(tag = "Owner")
	String owner;

	Pet(){
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
}
