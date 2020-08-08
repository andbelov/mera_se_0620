package persons;

import xml.annotations.XmlAnnotationPassword;
import xml.annotations.XmlClassName;
import xml.annotations.XmlAnnotationRestricted;
import xml.annotations.XmlAnnotation;

@XmlClassName(className = "Person")
public class Person{
	@XmlAnnotation(tag = "Name")
	String name;
	@XmlAnnotationPassword(
			removed = "Password removed" //any
			//, underStars = "Password under stars"
			)
	String pass;
	@XmlAnnotationRestricted(tag = "Age")
	int age;
	@XmlAnnotationRestricted(tag = "Politic party") // in USA/Russia is TABU !!!
	String party;
	@XmlAnnotationRestricted() //religion!!!
	String religion;

	Person(){
		name = "Dead";
		age = -1;
		pass = "***";
		religion = "atheist";
	}
	public Person(final String name, final String pass, final int age){
		this.name = name;
		this.pass = pass;
		this.age = age;
		this.party = "no";
		//this.religion = null // especial case with null
	}
	public Person(final String name, final String pass
			, final int age, final String party, final String religion
	){
		this.name = name;
		this.pass = pass;
		this.age = age;
		this.party = party;
		this.religion = religion;
	}
	@Override
	public String toString(){
		return "Person{" +
				"name='" + name + '\'' +
				", age=" + age +
				", pass='" + pass + '\'' +
				", religion='" + religion + '\'' +
				", politics='" + party + '\'' +
				'}';
	}
}
