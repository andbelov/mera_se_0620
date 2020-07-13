package game;

import static game.Unit.PropertyType.HARM;

public abstract class FighterCharacter extends Character{
	protected enum FighterAction{ FIGHT, ROB, ESCAPE, SCARE; }
	protected abstract FighterAction chooseActionToDo();
	protected abstract void fight();
	protected void fightRandom(){
		//opponent may NOT be itself!?
		Unit enemy;
		do{
			enemy = getRandomOpponent();
		}while(this==enemy);
		fight(enemy);
	}
	//if a unit decided itself to select an enemy
	protected void fight(final Unit enemy){
		System.out.println(", сражается с " + enemy.getName());
		harm(enemy, getPropertyValue(HARM));
	}
	protected void rob   (){
		System.out.println(", обкрадывает неизвестного, а толку то");
	}
	protected void escape(){
		System.out.println(", тихо сбегает ...");
	}
	protected void scare (){
		System.out.println(", громко кричит, но только пугает всех для храбрости ");
	}
	protected void defend(){}
}
