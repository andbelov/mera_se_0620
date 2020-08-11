package units.spell;

import game.MagicianCharacter;
import util.Util;

//Исцеление - добавляет очков здоровья магу, произнесшему заклинания.
public class HealMyself extends Spell{
	public HealMyself(){}
	@Override
	public String getName(){
		return this.getClass().getSimpleName();
	}
	public void cast(final MagicianCharacter mag){
		final var remedy = Util.getRandomInBound(
				MagicianCharacter.REMEDY_MIN, MagicianCharacter.REMEDY_MAX);
		final int prevH = mag.getHealth();
		mag.remedy(remedy);
		System.out.println("Маг " + mag.getName()
				+ " исцелен на " + remedy
				+ ". Здоровье: " + prevH + " -> " + mag.getHealth());
	}
}
