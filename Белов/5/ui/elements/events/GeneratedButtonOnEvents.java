package ui.elements.events;

import ui.elements.Button;
import ui.elements.ButtonOnEvents;

class GeneratedButtonOnEvents extends ButtonOnEvents{
	public void onClick(Button button){
		System.out.println("Нажата кнопка " + button.giveInfo());
	}
}
