package game;

import units.Magician;
import units.Monster;

import java.util.HashSet;

import static util.Util.*;

//	Есть сцена (Scene).
//		На сцену можно добавить до 10 Персонажей (game.unit.Character)
//!		у каждого персонажа есть позиция (от 0 до 9). На которой он стоит.
//!		На позиции с одинаковым номером может стоять только один персонаж.
//!		В позициях допускаются пропуски, например, на сцене могут быть всего
//!		два персонажа: на позиции 1 и на позиции 7.
//Создайте сцену со случайным количеством случайных персонажей, запустите игру.
public class Scene {
	public static final int POSITION_INDEX_MAX = 10;
	public static final int UNITS_MAX = POSITION_INDEX_MAX;
	private static final Unit[] units
			= new Unit[getRandomInBound(1, 1+UNITS_MAX)];
	public static Unit[] getUnits(){
		return units;
	}
	public static boolean isUnitMagician(final Unit unit){
		return unit instanceof Magician;
	}
	public static boolean isUnitMonster(final Unit unit){
		return unit instanceof Monster;
	}
	private static Integer[] randomUnitsUniquePositions(final int length){
		final var uniquePositions = new HashSet<Integer>(length);
		while(length!=uniquePositions.size()){
			uniquePositions.add(getRandom(Scene.POSITION_INDEX_MAX));
		}
		final Integer[] arr = new Integer[length];
		return uniquePositions.toArray(arr);
	}
	public static void create(){
		System.out.println("Создаю " + units.length + " юнитов");
		final var uniquePositions = randomUnitsUniquePositions(units.length);
		for(int i=0; i<units.length; ++i){
			Unit u = units[i] = (Unit) newRandomClass(Character.getClasses());
			assert u!=null;
			u.setName(u.getClass().getSimpleName() + i);
			u.setPosition(uniquePositions[i]);
			System.out.println("Created " + u.getClass().getSimpleName()
					+ " \"" + u.getName() + "\""
					+ " located " + u.getPosition()
					+ " health " + u.getHealth()
			);
		}
	}
	public static void run(){
		//если на сцене остался только один персонаж -
		//то игра завершается. And i don't let it go forever
		int turnCount = 0;
		while(isAliveUnitsToFight() && 999>++turnCount){
			System.out.println("--- game turn " + turnCount + " ---");
			//Игра - пошаговая. В каждый ход
			Scene.gameTurn();
		}
	}
	public static boolean isAliveUnitsToFight(){
		int aliveUnitsCount = 0;
		for(var u: units){
			if(!u.isAlive()){
				continue;
			}
			if(1<++aliveUnitsCount){
				return true;
			}
		}
		return false;
	}
	public static void showResult(){
		// и на экран выводится имя и тип (маг, монстр) победившего персонажа.
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
	static boolean isMovedCorrectly(final Unit unit){
		assert unit!=null;
		return true;
	}
	static boolean isActedCorrectly(final Unit unit){
		assert unit!=null;
		//todo check the unit for all his changes made himself
		for(Unit u: units){
			if(u==unit){
				continue;
			}
			assert true;
			//todo check all other unit for all his changes made for them
		}
		return true;
	}
	//Если текущее здоровье у монстра стало отрицательным - он удаляется
	//со сцены и на экран выводится текст "<имя персонажа> убит"
	public static void anonceIfUnitKilled(final Unit unit){
		if(!unit.isAlive()){
			System.out.println(unit.getName() + " убит!!!!!!!!!!!!");
		}
	}

	private static int findCharacter(final int pos){
		assert(0<=pos && Scene.POSITION_INDEX_MAX >pos);
		for(int i = 0; i < units.length; i++){
			if(pos== units[i].getPosition()){
				return i;
			}
		}
		throw new AssertionError("findCharacter: No Characters[]"
				+ "found on position" + pos);
	}
}
