package units;

import game.MonsterCharacter;

//!	Монстры. Могут атаковать любого персонажа, напрямую нанося урон здоровью.
//		Количество урона для каждого конкретного монстра одинаковое, но разные
//		экземпляры монстров могут наносить разное количество урона.
//		Если монстр атакует любого персонажа, - на экран должен выводится текст
//		"Монстр <имя> атакует <имя, цели> на <количество> единиц урона урона "
//in real game i would override this (and other) methods
public class Monster extends MonsterCharacter{
	protected FighterAction chooseActionToDo(){
		return FighterAction.FIGHT;
	}
	@Override
	public void move(){}
	protected void fight(){ fightRandom();}
}
