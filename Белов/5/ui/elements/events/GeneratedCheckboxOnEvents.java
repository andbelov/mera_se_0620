package ui.elements.events;

import ui.elements.Checkbox;
import ui.elements.CheckboxOnEvents;

class GeneratedCheckboxOnEvents extends CheckboxOnEvents{
	public void onClick(Checkbox checkbox){
		System.out.println("Нажата галка " + checkbox.giveInfo());
	}
}
