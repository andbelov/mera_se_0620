package units;

import game.MagicianCharacter;
import units.spell.Spell;

import static util.Util.getRandom;

public class Magician extends MagicianCharacter{
	public void move(){}
	public Spell chooseSpellToCast(){
		return spells[getRandom(spells.length)];
	}
}
