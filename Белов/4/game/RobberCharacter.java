package game;

import units.Monster;

import static util.Util.getRandom;

//разбойник, который имеет некоторый шанс уклониться от атаки монстра)?
public abstract class RobberCharacter extends FighterCharacter{
	public final static int CHANCE_TO_AVOID_MONSTER_HARM_ONE_OF = 2;
	protected void doSpecificAct(){
		switch(chooseActionToDo()){
			case FIGHT  -> fight();
			case ROB    -> rob();
			case ESCAPE -> escape();
		}
	}
	protected String getTitle(){
		return "¶ Разбойник";
	}
	// final <- Robber may not scare/defend
	protected final void scare (){}
	protected final void defend(){}
	@Override
	public void beHarmedFromWith(final Unit enemy, final int loss){
		if(enemy instanceof Monster
				&& 0==getRandom(CHANCE_TO_AVOID_MONSTER_HARM_ONE_OF)){
			System.out.println(", Оппппс! разбойник от Монстра уклонился");
			return;
		}
		super.beHarmedFromWith(enemy, loss);
	}
}
