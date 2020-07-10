package game;

import static game.Unit.PropertyType.HARM;

public abstract class MonsterCharacter extends Character{
	protected enum MonsterAction{FIGHT}
	protected abstract MonsterAction chooseActionToDo();
	protected void doSpecificAct(){
		switch(chooseActionToDo()){
			case FIGHT -> fight();
		}
	}
	public void fight(){
		final int harm = getPropertyValue(HARM);
		//opponent may NOT be itself!?
		Unit enemy;
		do{
			enemy = getRandomOpponent();
		}while(this==enemy);
		enemy.beHarmedBy(harm);
		System.out.println(name + " стоит на поз. " + this.getPosition()
				+ " и атакует " + enemy.getName() + " на поз. " + enemy.getPosition()
				+ " на " + harm + " единиц урона"
				+ ". Теперь у него " + enemy.getHealth() + " здоровья");
		Scene.anonceIfUnitKilled(this);
		Scene.anonceIfUnitKilled(enemy);
	}
	@Override
	protected void defend(){

	}
}
