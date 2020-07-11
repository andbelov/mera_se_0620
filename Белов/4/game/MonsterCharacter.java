package game;

import static game.Unit.PropertyType.HARM;

public abstract class MonsterCharacter extends Character{
	protected enum MonsterAction{FIGHT}
	protected abstract MonsterAction chooseActionToDo();
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
		System.out.println("-- ▼ Монстр " + getName()
				+ " на поз. " + getPosition() + " --");
		harm(enemy, getPropertyValue(HARM));
	}
	@SuppressWarnings("unused")
	@Override
	protected void defend(){
	}
}
