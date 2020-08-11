package units;

import game.MagicianCharacter;
import units.spell.Spell;

import static util.Util.getRandom;

//in real game i would override this (and other) methods
public class Magician extends MagicianCharacter{
	public Spell chooseSpellToCast(){
		return spells[getRandom(spells.length)];
	}
	@SuppressWarnings("unused")
	@Override
	public void move(){}
}
