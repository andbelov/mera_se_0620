package game;

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

	@Override
	public int harm(){
	}
}
