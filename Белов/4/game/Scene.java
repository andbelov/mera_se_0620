package game;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;

import static util.Util.getRandom;

//	Есть сцена (Scene).
//		На сцену можно добавить до 10 Персонажей (game.unit.Character)
//!		у каждого персонажа есть позиция (от 0 до 9). На которой он стоит.
//!		На позиции с одинаковым номером может стоять только один персонаж.
//!		В позициях допускаются пропуски, например, на сцене могут быть всего
//!		два персонажа: на позиции 1 и на позиции 7.
//Создайте сцену со случайным количеством случайных персонажей, запустите игру.
public class Scene {
	public static final int POSITION_NUM = 9;
	public static final int UNITS_MAX_NUM = 10;
	private static final Unit[] units
			= new Unit[getRandom(UNITS_MAX_NUM)];
	private static int aliveUnitsCount = units.length; //all units are alive from start
	public static Unit[] getUnits(){
		return units;
	}
	public static void create(){
		Class<?>[] interfaces = Character.class.getInterfaces();
		final var uniquePositions = giveUnitsPositions(units.length);
		for(int i=0; i<units.length; ++i){
			Unit u = units[i];
			final String className = interfaces[getRandom(interfaces.length)].getCanonicalName();
			try{
				 u = (Unit) Class.forName(className).getDeclaredConstructors()[0].newInstance();
			}catch(IllegalAccessException | InstantiationException | ClassNotFoundException | InvocationTargetException e){
				e.printStackTrace();
			}
			u.setName(className + i);
			u.setPosition(uniquePositions[i]);
		}
	}
	/*public static void createXXX(){
		final Unit.UnitType[] values = Unit.UnitType.values();
		final var uniquePositions = giveUnitsPositions(units.length);
		for(int i=0; i<units.length; ++i){
			Unit u = units[i];
			final Unit.UnitType ut = values[getRandom(values.length)];
			try{
				u = (Unit) Class.forName(ut.name()).getDeclaredConstructors()[0].newInstance();
			}catch(IllegalAccessException | InstantiationException | ClassNotFoundException | InvocationTargetException e){
				e.printStackTrace();
			}
			u.setName(ut.name() + i);
			u.setPosition(uniquePositions[i]);
		}
	}*/
	private static Integer[] giveUnitsPositions(final int length){
		final var uniquePositions = new HashSet<Integer>(length);
		while(length!=uniquePositions.size()){
			uniquePositions.add(getRandom(Scene.POSITION_NUM));
		}
		final Integer[] arr = new Integer[length];
		return uniquePositions.toArray(arr);
	}
	public static void run(){
		aliveUnitsCount = units.length; //all units are alive from start
		do{
			//Игра - пошаговая. В каждый ход
			assert(isUnitsCountConsistence());
			Scene.gameTurn();
		}while(1==aliveUnitsCount); //если на сцене остался только один персонаж -
		//то игра завершается и на экран выводится имя и тип (маг, монстр) победившего персонажа.
		for(var u: units){
			if(0<=u.getHealth()){
				System.out.println("Победил " + u.getName()
						+ " (тип " + u.getClass().getSimpleName() + ")");
				break;
			}
		}
	}
	//Игра - пошаговая. В каждый ход все персонажи со сцены делают одно действие:
	//если на сцене остался только один персонаж -
	//то игра завершается и на экран выводится имя и тип (маг, монстр) победившего персонажа.		do{
	//монстр атакует кого-то, а маг читает любое известное ему заклинание.
	//Порядок, в котором персонажи выполняют действия может быть любым.
	//Каждый персонаж сам за себя,
	private static void gameTurn(){
		for(var u: units){
			//В каждый ход все персонажи со сцены делают одно действие:
			//монстр атакует кого-то, а маг читает любое известное ему заклинание.
			if(!u.isAlive()){
				continue;
			}
			u.play();
		}
	}
	static boolean isOpponentChosenCorrectly(final Unit opponent){
		for(var u: units){
			if(opponent==u){ //compare ref, not equals(), because we check if opponent exists in units
				return true;
			}
		}
		return false;
	}
	//Если текущее здоровье у монстра стало отрицательным - он удаляется
	//со сцены и на экран выводится текст "<имя персонажа> убит"
	public static void anonceIfUnitKilled(final Unit unit){
		if(!unit.isAlive()){
			System.out.println("Монстр " + unit.getName() + " убит!");
		}
	}


	/*private static boolean isOnlyOneTheWinner(){
		for(var u: units){
			if(u.isAlive()){
				continue;
			}
			if(0 == --aliveUnitsCount){
				return false;
			}
		}
		return false;
	}*/
	private static boolean isUnitsCountConsistence(){
		I i = new C();
		i.f1();
		i.f2();
		i.f3();
		((C) i).f4();
		i = new D();
		i.f1();
		i.f2();
		i.f3();
		((D) i).f4();
		A a = new C();
		a.f1();
		a.f2();
		a.f3();
		a.f4();

		int count = 0;
		for(var u: units){
			count += u.isAlive() ? 1 : 0;
		}
		return aliveUnitsCount == count;
	}
	private static int findCharacter(final int pos){
		assert(0<=pos && Scene.POSITION_NUM>pos);
		for(int i = 0; i < units.length; i++){
			if(pos== units[i].getPosition()){
				return i;
			}
		}
		throw new AssertionError("findCharacter: No Characters[]"
				+ "found on position" + pos);
	}
	public static void showResult(){
	}
}

interface I{
	int i = 0;
	void f1();
	void f2();
	void f3();
	//void f4();
}
abstract class A implements I{
	public void f1(){}
	abstract void f4();
}
class C extends A{
	public void f1(){}
	public void f2(){}
	public void f3(){}
	public void f4(){}
}
class D extends A{
	public void f1(){}
	public void f2(){}
	public void f3(){}
	public void f4(){}
}
