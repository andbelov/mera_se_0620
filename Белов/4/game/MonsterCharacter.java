package game;

public abstract class MonsterCharacter extends FighterCharacter{
	@SuppressWarnings({"SwitchStatementWithTooFewBranches", "unused"})
	protected void doSpecificAct(){
		switch(chooseActionToDo()){
			case FIGHT -> fight();
		}
	}
	protected String getTitle(){
		return "Ꚗ Монстр";
	}
}
