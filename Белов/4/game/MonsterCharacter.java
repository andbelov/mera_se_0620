package game;

import static game.Unit.PropertyType.HARM;

public abstract class MonsterCharacter extends Character{
	protected enum MonsterAction{FIGHT}
	//public abstract MonsterActionType getMonsterActionType();
	//protected AllActionType getAllActionType(){
	//	return AllActionType.valueOf(getMonsterActionType().name());
	//}
	protected abstract MonsterAction chooseActionToDo();
	protected void doSpecificAct(){
		switch(chooseActionToDo()){
			case FIGHT -> fight();
		}
	}
	public void fight(){
		final int harm = getPropertyValue(HARM);
		System.out.println("Монстр " + name + " стоит на поз. " + this.getPosition()
				+ "\nи атакует " + getOpponent().getName() + " на поз. " + getOpponent().getPosition()
				+ " на " + harm + " единиц урона");
	}
}
