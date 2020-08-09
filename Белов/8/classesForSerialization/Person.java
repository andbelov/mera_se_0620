package classesForSerialization;

import annotations.*;

@XmlClassName(className = "Leader")
public class Person{
	@XmlAnnotation(tag = "Name")
	String name;
	@XmlAnnotationPassword(
// I may use several of them. Which one is used undefined until
// I put them firstly in sorted order password.annotationType().getMethods())
// and only then I run for(sorted array) with method.invoke()
			//removed = "Password removed",
			//underStars = "Password under stars", redundant if default is defined
			//encryptedAes128 = ";-)",
			//asis = "Don't write open password in xml",
			encryptedAes256 = "Password encrypted Aes-256"
			)
	String pass;
	@XmlAnnotationRestricted(tag = "Age")
	int age;
	@XmlAnnotationRestricted(tag = "Politic party") // in USA/Russia is TABU !!!
	String party;
	@XmlAnnotationRestricted() //religion!!!
	String religion;

	public Person(){
		age = -1;
		religion = "верю/не верю";
		pass = "---";
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
				", pass='" + pass + '\'' +
				", age=" + age +
				", party='" + party + '\'' +
				", religion='" + religion + '\'' +
				'}';
	}
}
