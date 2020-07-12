import ui.UI;
import ui.elements.*;
import ui.elements.events.*;
import ui.elements.exceptions.*;
import ui.elements.placement.*;

import static util.Util5.giveRandom;

class Lecture5Task1{
	static final int SCENE_WIDTH = 100;
	static final int SCENE_HEIGHT = 100;
	static final int ATTEMPTS_NUM = 10;
	public static void main(String[] args){
		System.out.println("\n== 1. INIT MAIN ELEMENTS ==");
		UI.setScreenSize(new Coord(SCENE_WIDTH, SCENE_HEIGHT));
// 1. Создайте кнопку и поместите её в любое место интерфейса
//  назовите её "Добавить элемент".
//При клике на кнопку выполняется действие для этой кнопки.
		final Coord uiSize = UI.getScreenSize();
		Button theMainButton = new Button(new TheMainButtonOnEvents());
		theMainButton.setCaption("Добавить элемент");
		UI.addElement(theMainButton);
//2. Создайте два текстовых поля "x" и "y"
		Textfield tfX = new Textfield();
		tfX.setCaption("x");
		UI.addElement(tfX);
		Textfield tfY = new Textfield();
		tfY.setCaption("y");
		UI.addElement(tfY);
		System.out.println("== MAIN ELEMENTS ADDED ==");
		for(Element element: UI.getAllElements()){
			element.setPosition(new Coord(1,1));
			element.setSize(new Coord(1,1));
			System.out.println(element.giveInfo());
		}

		System.out.println("\n== 2. CLICK THE MAIN BUTTON SEVERAL TIMES, IT LIKES TO ADD ELEMENTS ==");
//6. Повторите предыдущие два пункта 10 раз. таким образом,
//у вас будет 10 попыток добавить элементы на случайные позиции.
		for(int i=ATTEMPTS_NUM; 0<=--i;){
//4. Поместите в текстовые поля x,y случайные числа от 0 до 100.
			tfX.setText(String.valueOf(giveRandom(UI.getScreenSize().getX())));
			tfY.setText(String.valueOf(giveRandom(UI.getScreenSize().getY())));
//5. Нажмите на кнопку. Должен добавится какой-то элемент.
			theMainButton.click();
			final Element newEl = ((TheMainButtonOnEvents)
					theMainButton.getEvents()).getGeneratedElement();
			//в указанные координаты x и y (из текстовых полей)
			final var coord = convertCoord(tfX.getText(), tfY.getText());
			newEl.setPosition(coord);
			newEl.setReadOnly(giveRandom());
			try{
				UI.checkCrossing(newEl);
				UI.addElement(newEl);
			}catch (ElementOverlapException elementOverlapException){
				System.out.println("New element has not been added. "
						+ elementOverlapException.getMessage());
			}
		}
//7. Выведите все элементы, которые существуют на интерфесе в формате:
// [Галка\Кнопка\Текстовое поле] в координатах <x,y>, ширина <w>,
//				высота <h>, Название: <caption>
		System.out.println("\n== 3. TRY CLICK GENERATED ELEMENTS ==");
		for(Element element: UI.getAllElements()){
			System.out.println("BEFORE " + element.giveInfo());
			if(!(element instanceof Clickable)
					|| theMainButton==element){//don't click on the same button ref
				System.out.println("Skipping ........");
				continue;
			}
			try{
				((Clickable) element).click();
			}catch(ReadyOnlyException readyOnlyException){
				System.out.println(readyOnlyException.getMessage());
			}
			System.out.println("AFTER " + element.giveInfo());
		}
	}
	private static Coord convertCoord(final String x, final String y){
		try{
			return new Coord(x, y);
		}catch(NumberFormatException numberFormatException){
			System.out.println("Tried to set from text not Integer type coord"
					+ " for a new element. Will be zeroed."
					+ numberFormatException.getMessage());
			return new Coord();
		}
	}
}

/* output:
== 1. INIT MAIN ELEMENTS ==
== MAIN ELEMENTS ADDED ==
"Button", pos <1,1>, size <1,1>, caption: "Добавить элемент", read only: "false"
"Textfield", pos <1,1>, size <1,1>, caption: "x", read only: "false", Text: "null"
"Textfield", pos <1,1>, size <1,1>, caption: "y", read only: "false", Text: "null"

== 2. CLICK THE MAIN BUTTON SEVERAL TIMES, IT LIKES TO ADD ELEMENTS ==
New element has not been added. ElementOverlapException. New element: "Textfield", pos <36,19>, size <21,10>, caption: "h15", read only: "false", Text: "e08PZ9AC". MAY NOT CROSS existing: "Button", pos <57,29>, size <1,39>, caption: "d2cy4lEG", read only: "true"
New element has not been added. ElementOverlapException. New element: "Checkbox", pos <6,47>, size <18,24>, caption: "w3o3M", read only: "true", isChecked: "false". MAY NOT CROSS existing: "Checkbox", pos <5,29>, size <15,25>, caption: "Dg3281985", read only: "true", isChecked: "false"

== 3. TRY CLICK GENERATED ELEMENTS ==
BEFORE "Button", pos <1,1>, size <1,1>, caption: "Добавить элемент", read only: "false"
Skipping ........
BEFORE "Textfield", pos <1,1>, size <1,1>, caption: "x", read only: "false", Text: "6"
Skipping ........
BEFORE "Textfield", pos <1,1>, size <1,1>, caption: "y", read only: "false", Text: "47"
Skipping ........
BEFORE "Button", pos <69,22>, size <2,34>, caption: "01", read only: "false"
Нажата кнопка "Button", pos <69,22>, size <2,34>, caption: "01", read only: "false"
AFTER "Button", pos <69,22>, size <2,34>, caption: "01", read only: "false"
BEFORE "Checkbox", pos <16,95>, size <15,36>, caption: "fm1y", read only: "true", isChecked: "true"
ReadyOnlyException: Element "Checkbox", pos <16,95>, size <15,36>, caption: "fm1y", read only: "true", isChecked: "true". READ ONLY state!
AFTER "Checkbox", pos <16,95>, size <15,36>, caption: "fm1y", read only: "true", isChecked: "true"
BEFORE "Button", pos <57,29>, size <1,39>, caption: "d2cy4lEG", read only: "true"
ReadyOnlyException: Element "Button", pos <57,29>, size <1,39>, caption: "d2cy4lEG", read only: "true". READ ONLY state!
AFTER "Button", pos <57,29>, size <1,39>, caption: "d2cy4lEG", read only: "true"
BEFORE "Checkbox", pos <5,29>, size <15,25>, caption: "Dg3281985", read only: "true", isChecked: "false"
ReadyOnlyException: Element "Checkbox", pos <5,29>, size <15,25>, caption: "Dg3281985", read only: "true", isChecked: "false". READ ONLY state!
AFTER "Checkbox", pos <5,29>, size <15,25>, caption: "Dg3281985", read only: "true", isChecked: "false"
BEFORE "Checkbox", pos <11,65>, size <10,1>, caption: "ZI29Y", read only: "true", isChecked: "true"
ReadyOnlyException: Element "Checkbox", pos <11,65>, size <10,1>, caption: "ZI29Y", read only: "true", isChecked: "true". READ ONLY state!
AFTER "Checkbox", pos <11,65>, size <10,1>, caption: "ZI29Y", read only: "true", isChecked: "true"
BEFORE "Checkbox", pos <78,11>, size <8,26>, caption: "H4uF", read only: "true", isChecked: "true"
ReadyOnlyException: Element "Checkbox", pos <78,11>, size <8,26>, caption: "H4uF", read only: "true", isChecked: "true". READ ONLY state!
AFTER "Checkbox", pos <78,11>, size <8,26>, caption: "H4uF", read only: "true", isChecked: "true"
BEFORE "Textfield", pos <25,25>, size <2,3>, caption: "gWi7", read only: "false", Text: "a"
Skipping ........
BEFORE "Textfield", pos <82,69>, size <20,20>, caption: "0182", read only: "false", Text: "165Z3X8fw4"
Skipping ........
*/
/*
[7/8 9:33 AM] andrey.tarasov@mera.com
Задание 5. Исключительный интерфейс.
Сегодня создаем элементы интерфейса и их взаимодействие.
Элементов у нас будет три:
- Button - кнопка. По ней можно кликнуть.
 При клике по кнопке возможно любое действие
 (определяется создателем кнопки)
- CheckBox - галка. По ней тоже можно кликнуть.
 У Галки есть состояние - нажата или не нажата.
 При клике на галку у нее меняется состояние.
!!- TextField - поле для ввода текста.
 У поле есть состояние - введенный текст.

Кроме того, у каждого элемента есть:
- координаты левого верхнего угла: x,y
- ширина, высота. Положительные числа.
- название элемента (caption)
- Состояние включен\выключен.
!Если элемент выключен, и с ним производится какое-то действие,
 то должно быть выкинуто ReadOnlyException
!- На каждый элемент можно кликнуть.
 При клике на кнопку выполняется действие для этой кнопки.

!Есть класс UI,который хранит в себе массив элементов. В UI всего два метода:
- getAllElements - возвращает все элементы
- addElement - добавляет новый элемент в интерфейс.
! Если новый элемент пересекается с любым уже существующим
   элементом в интерфейсе - он не добавляется
   и выкидывается ELementsOverlapException
    с описанием, какой элемент не может быть добавлен
    и с каким элементом он пересекается.
- размер UI сцены - 100х100

 Задание:
 1. Создайте кнопку и поместите её в любое место интерфейса
  назовите её "Добавить элемент".
 2. Создайте два текстовых поля "x" и "y"
 3. При клике на кнопку в указанные координаты x и y (из текстовых полей)
  добавляется случайный элемент со случайными шириной и высотой:
   - Кнопка при клике на которую в консоль пишется сообщение
   "Нажата кнопка в <x,y>" с названием "Кнопка в <x,y>"
   - Галка со случайным начальным состоянием
   - Текстовое поле со случайным текстом длины от 1 до 10.
   - Если в любое тексовое поле введено не число - должно быть
    выкинуто NumberFormatException
 4. Поместите в текстовые поля x,y случайные числа от 0 до 100.
 5. Нажмите на кнопку. Должен добавится какой-то элемент.
 6. Повторите предыдущие два пункта 10 раз. таким образом,
    у вас будет 10 попыток добавить элементы на случайные позиции.
 Если какой-то элемент не добавился из-за пересечений
   с существующими элементами - в консоль выведите надпись,
    с каким элементом произошло пересечение.
 7. Выведите все элементы, которые существуют на интерфесе в формате:
 [Галка\Кнопка\Текстовое поле] в координатах <x,y>, ширина <w>,
  высота <h>, Название: <caption>
 - Если на элемент можно кликнуть - кликните.
 Но не кликайте на кнопку из пункта 1.
 - Для всех галок выведите состояние (после клика)
 - Для всех текстовых полей выведите текст
 Рекомендации:
 - Создайте базовый класс Rectangle, описывающий свойства,
    иденые для всех фигур.
 - Создайте интерфейс Clickable с методом click() для фигур,
    которые поддерижвают клик (кнопка, галка).
 - Для определения поведения кнопки создайте
   интерфейс ButtonClickCallback с методом onButtonClick()
 - Кнопка должна хранить экземпляр объекта, реализующего этот
  интерфейс и вызывать метод onButtonClick, при клике на кнопку

 Например
 class AddElementOnClick implements ButtonClickCallback {
      UI screen;
      AddElementOnClick(UI screenToAddElements){
             screen = screenToAddElements;
      }
      @Override
      void onButtonClick(){
          Element toAdd = generateRandomElement();
          screen.add(toAdd);
      }
 }
 }
 class Button implements Clickable{
     ButtonClickCallback callback;
     Button(String caption, ButtonClickCallback callback){
     ...
     }
     @Override
     void click(){
        callback.onButtonClick();
     }
 }
 main {
    Button b = new Button("Добавить элемент", new AddElementOnClick())

 }



​[7/8 5:55 PM] nikolay.kuzmichev@mera.com
    По TextField можно кликать?
​[7/8 5:55 PM] Andrey Tarasov
    нет, только установить текст и получить его значение
​[7/8 6:17 PM] rostislav.kolesnikov@mera.com
- Текстовое поле со случайным текстом длины от 1 до 10.
 - Если в любое тексовое поле введено не число - должно быть
  выкинуто NumberFormatException
    Только я вижу тут противоречие?
​[7/8 6:18 PM] Andrey Tarasov
    имеется ввиду, что для получения координат х у из двух полей
    там должны быть числа, а в остальных полях любой текст
[7/8 6:19 PM] rostislav.kolesnikov@mera.com
    В смысле, полей из п.2?
    Т.е. поля из п.2 по поведению должны отличаться от полей из п.3?
​[7/8 6:25 PM] Andrey Tarasov
    ох понял. при клике на первую кнопку кидается исключение, если в полях не число
    а при вводе в само поле исключений не кидается
​[7/8 7:44 PM] rostislav.kolesnikov@mera.com
    Элементы будут считаться пересекающимися при совпадении только координат X, Y, или еще надо учитывать высоту/ширину?
Edited​[7/8 7:45 PM] nikolay.kuzmichev@mera.com
    Думаю, что да, я учел и пересечение и поглощение одного элемента другим
*/