package units;

import game.RobberCharacter;

import static util.Util.getRandom;

public class Robber extends RobberCharacter{
	@Override
	public RobberAction chooseActionToDo(){
		final var values = RobberAction.values();
		return values[getRandom(values.length)];
	}
	@SuppressWarnings("unused")
	@Override
	public void move(){}
	@SuppressWarnings({"EmptyMethod", "unused"})
	@Override
	protected void defend(){}
}
