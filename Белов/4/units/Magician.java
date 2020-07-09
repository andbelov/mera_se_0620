package units;

import game.MagicianCharacter;
import game.MonsterCharacter;
import units.spell.Spell;

import static util.Util.getRandom;

class Magician extends MagicianCharacter{
	@Override
	public Spell chooseSpellToCast(){
		return spells[getRandom(spells.length)];
	}
}
