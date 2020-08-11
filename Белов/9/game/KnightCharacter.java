package game;

import units.Magician;
import static util.Util.*;

//рыцарь, который от всех заклинаний получает
// только половину урона
public abstract class KnightCharacter extends FighterCharacter{
	final static int SHIELD = 1;
	protected void doSpecificAct(){
		switch(chooseActionToDo()){
			case FIGHT  -> fight ();
			case SCARE  -> scare ();
		}
	}
	protected String getTitle(){
		return "▼ Рыцарь";
	}
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
		if(0==getRandom(2)){
			System.out.println(", Оппппс! Рыцарь активно защищается на " + SHIELD);
			health += SHIELD;
		}
	}
}
