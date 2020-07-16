package taskB;

import java.util.Objects;

public class Person{
	private final String firstName;
	private final String lastName;
	public Person(final String firstName, final String lastName){
		this.firstName = firstName;
		this.lastName  = lastName;
	}
	public String getFirstName(){
		return firstName;
	}
	public String getLastName(){
		return lastName;
	}
	@Override
	public boolean equals(final Object o){
		if(this == o) return true;
		if(o == null
				|| getClass() != o.getClass()
		) return false;
		final Person person = (Person) o;
		return  Objects.equals(firstName, person.firstName)
			&&  Objects.equals(lastName,  person.lastName);
	}
	@Override
	public int hashCode(){
		return Objects.hash(firstName, lastName);
	}
}