package units;

import game.RobberCharacter;
import game.Scene;
import game.Unit;

import java.util.ArrayList;
import java.util.List;

import static game.Unit.PropertyType.HARM;
import static util.Util.getRandom;

//in real game i would override this (and other) methods
public class Robber extends RobberCharacter{
	//For example Robber could rob plus to he can fight
	@Override
	public FighterAction chooseActionToDo(){
		final var values = FighterAction.values();
		while(true){
			final var value = values[getRandom(values.length)];
			switch(value){
				case FIGHT  : return value;
				case ROB    : return value;
				case ESCAPE : return value;
			}
		}
	}
	protected void fight(){
		List<Unit> enemies = new ArrayList<>();
		for(Unit u : Scene.getUnits()){
			if(!u.isDead() && this != u){
				enemies.add(u);
			}
		}
		if(0==enemies.size()){
			System.out.println(", враги не найдены");
			return;
		}
		enemies.sort((u1, u2) -> {
			return
					20 * ((this.getPosition() - u1.getPosition())
							- (this.getPosition() - u2.getPosition()))
							+
					3 * ((this.getHealth() - u1.getHealth())
									- (this.getHealth() - u2.getHealth()))
					;
		});
		final var enemy = enemies.get(0);
		System.out.println(", Разбойник хитро сражается с " + enemy.getName());
		harm(enemy, getPropertyValue(HARM));
	}

	//For example Robber could rob plus to he can fight
	@SuppressWarnings({"EmptyMethod", "unused"})
	@Override
	public void rob(){}
	//For example Robber could rob plus to he can fight
	@SuppressWarnings({"EmptyMethod", "unused"})
	@Override
	public void escape(){}
	@SuppressWarnings({"EmptyMethod", "unused"})
	@Override
	public void move(){}
}
