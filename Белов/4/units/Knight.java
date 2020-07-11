package units;

import game.KnightCharacter;

import static util.Util.getRandom;

public class Knight extends KnightCharacter{
	@Override
	public KnightAction chooseActionToDo(){
		final var values = KnightAction.values();
		return values[getRandom(values.length)];
	}
	@SuppressWarnings("unused")
	@Override
	public void move(){}
	@SuppressWarnings({"EmptyMethod", "unused"})
	@Override
	protected void defend(){}
}
