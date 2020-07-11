package units;

import game.MonsterCharacter;

import static util.Util.getRandom;

//!	Монстры. Могут атаковать любого персонажа, напрямую нанося урон здоровью.
//		Количество урона для каждого конкретного монстра одинаковое, но разные
//		экземпляры монстров могут наносить разное количество урона.
//		Если монстр атакует любого персонажа, - на экран должен выводится текст
//		"Монстр <имя> атакует <имя, цели> на <количество> единиц урона урона "
public class Monster extends MonsterCharacter{
	@Override
	public MonsterAction chooseActionToDo(){
		final var values = MonsterAction.values();
		return values[getRandom(values.length)];
	}
	@SuppressWarnings("unused")
	@Override
	public void move(){}
	@SuppressWarnings({"EmptyMethod", "unused"})
	@Override
	protected void defend(){}
}
