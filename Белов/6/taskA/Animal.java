package taskA;

public class Animal {
	private final String name;
	private final String type;
	public Animal(String name, String type){
		this.name = name;
		this.type = type;
	}
	public String getName(){ return name; }
	public String getType(){ return type; }
}