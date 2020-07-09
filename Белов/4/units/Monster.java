package units;

import game.MonsterCharacter;

import static game.Unit.PropertyType.HARM;
import static util.Util.getRandom;

//!	Монстры. Могут атаковать любого персонажа, напрямую нанося урон здоровью.
//		Количество урона для каждого конкретного монстра одинаковое, но разные
//		экземпляры монстров могут наносить разное количество урона.
//		Если монстр атакует любого персонажа, - на экран должен выводится текст
//		"Монстр <имя> атакует <имя, цели> на <количество> единиц урона урона "
class Monster extends MonsterCharacter{
	@Override
	public MonsterAction chooseActionToDo(){
		final var values = MonsterAction.values();
		return values[getRandom(values.length)];
	}
	//@Override
	//protected MonsterActionType getActionType(){
	//	final var values = MonsterActionType.values();
	//	return values[getRandom(values.length)];
	//}
	@Override
	public int harm(){
		final int harm = getPropertyValue(HARM);
		System.out.println("Монстр " + name + " стоит на поз. " + this.getPosition()
				+ "\nи атакует " + getOpponent().getName() + " на поз. " + getOpponent().getPosition()
				+ " на " + harm + " единиц урона");
		return harm;
	}
	@Override
	protected int defend(){
		return 0;
	}
	@Override
	protected void beHarmedBy(int loss){

	}
	@Override
	protected int react(){
		return 0;
	}
	@Override
	protected void beDamagedBy(int loss){

	}
}
