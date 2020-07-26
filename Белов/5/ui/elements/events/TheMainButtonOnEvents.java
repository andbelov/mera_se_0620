package ui.elements.events;

import ui.UI;
import ui.elements.*;
import ui.elements.placement.Coord;

import static util.Util5.giveRandom;
import static util.Util5.giveRandomString;

public class TheMainButtonOnEvents extends ButtonOnEvents{
	final Coord NEW_ELEMENT_SIZE_MAX = new Coord(24, 42);
	Element newElement;
	public Element getGeneratedElement(){
		return newElement;
	}
	@Override
	public void onClick(Button button){
		newElement = setCommonProperties(newRandomElement());
	}
	enum Elements{BUTTON, CHECKBOX, TEXTFIELD}
	private Element newRandomElement(){
		return switch(Elements.values()
				[giveRandom(Elements.values().length)]){
			//   - Кнопка при клике на которую в консоль пишется сообщение
			//"Нажата кнопка в <x,y>" с названием "Кнопка в <x,y>"
			case BUTTON    -> new Button(new GeneratedButtonOnEvents());
			//Галка со случайным начальным состоянием			case CheckBox.class:
			case CHECKBOX  -> new Checkbox(giveRandom());
			//Текстовое поле со случайным текстом длины от 1 до 10.
			//Если в любое тексовое поле введено не число
			// должно быть выкинуто NumberFormatException
			case TEXTFIELD -> new Textfield(giveRandomString(1+giveRandom(10)));
		};
	}
	private Element setCommonProperties(Element newElement){
		newElement.setCaption(giveRandomString(1+giveRandom(10)));
		//При клике на кнопку в указанные координаты x и y (из текстовых полей)
		//добавляется случайный элемент со случайными шириной и высотой:
		final var screenSize = UI.getScreenSize();
		assert(NEW_ELEMENT_SIZE_MAX.isLessThan(screenSize));
		newElement.setSize(Coord.giveRandom(NEW_ELEMENT_SIZE_MAX));
		/* в указанные координаты x и y (из текстовых полей)
		   elem has to be placed within UI screen size:
		newElement.setPosition(Coord.giveRandom(Coord
				.sub(screenSize, NEW_ELEMENT_SIZE_MAX)));*/
		return newElement;
	}
}
