package ui.elements.events;

import ui.elements._Checkbox;
import ui.elements.CheckboxOnEvents;

class GeneratedCheckboxOnEvents extends CheckboxOnEvents{
	public void onClick(_Checkbox checkbox){
		System.out.println("Нажата галка " + checkbox.giveInfo());
	}
}
