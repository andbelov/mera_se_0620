package units;

import game.MagicianCharacter;
import units.spell.Spell;

import static util.Util.getRandom;

public class Magician extends MagicianCharacter{
	public Spell chooseSpellToCast(){
		return spells[getRandom(spells.length)];
	}
	@SuppressWarnings("unused")
	@Override
	public void move(){}
	@SuppressWarnings({"EmptyMethod", "unused"})
	@Override
	protected void defend(){}
}
