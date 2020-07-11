package game;

import units.Magician;

import static game.Unit.PropertyType.HARM;

//рыцарь, который от всех заклинаний получает
// только половину урона
public abstract class KnightCharacter extends Character{
	protected enum KnightAction{FIGHT}
	protected abstract KnightAction chooseActionToDo();
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
		System.out.println("-- Ꚗ Рыцарь " + getName()
				+ " на поз. " + getPosition() + " --");
		harm(enemy, getPropertyValue(HARM));
	}
	@SuppressWarnings("unused")
	@Override
	public void beHarmedFromWith(final Unit enemy, int loss){
		if(enemy instanceof Magician){
			loss /=2;
			System.out.println(", Оппппс! Рыцарь от Мага получает только половину урона " + loss);
		}
		super.beHarmedFromWith(enemy, loss);
	}
	@SuppressWarnings("unused")
	@Override
	protected void defend(){
	}
}
