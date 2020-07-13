package units;

import game.KnightCharacter;

import static util.Util.getRandom;

//in real game i would override this (and other) methods
public class Knight extends KnightCharacter{
	@Override
	protected FighterAction chooseActionToDo(){
		final var values = FighterAction.values();
		while(true){
			final var value = values[getRandom(values.length)];
			switch(value){
				case FIGHT  : return value;
				case SCARE  : return value;
			}
		}
	}
	@Override
	public void move(){}
	protected void fight(){ fightRandom();}
}
