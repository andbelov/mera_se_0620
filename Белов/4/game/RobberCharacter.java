package game;

import units.Monster;

import static game.Unit.PropertyType.HARM;
import static util.Util.*;

//разбойник, который имеет некоторый шанс уклониться от атаки монстра)?
public abstract class RobberCharacter extends Character{
	public final static int CHANCE_TO_AVOID_MONSTER_HARM_ONE_OF = 2;
	protected enum RobberAction{FIGHT}
	protected abstract RobberAction chooseActionToDo();
	@SuppressWarnings({"SwitchStatementWithTooFewBranches", "unused"})
	protected void doSpecificAct(){
		switch(chooseActionToDo()){
			case FIGHT -> fight();
		}
	}
	public void fight(){
		//opponent may NOT be itself!?
		Unit enemy;
		do{
			enemy = getRandomOpponent();
		}while(this==enemy);
		System.out.println("-- ¶ Разбойник " + getName()
				+ " на поз. " + getPosition() + " --");
		harm(enemy, getPropertyValue(HARM));
	}
	@SuppressWarnings("unused")
	@Override
	public void beHarmedFromWith(final Unit enemy, final int loss){
		if(enemy instanceof Monster
				&& 0==getRandom(CHANCE_TO_AVOID_MONSTER_HARM_ONE_OF)){
			System.out.println(", Оппппс! разбойник от Монстра уклонился");
			return;
		}
		super.beHarmedFromWith(enemy, loss);
	}
	@SuppressWarnings("unused")
	@Override
	protected void defend(){
	}
}
