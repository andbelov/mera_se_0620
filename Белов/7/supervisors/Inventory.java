package supervisors;

import java.util.ArrayList;

public class Inventory<T> extends ArrayList<T>{
	final String title;
	public Inventory(final String title){
		this.title = title;
	}
	public String getTitle(){
		return title;
	}
}
