package units;

import game.MagicianCharacter;
import units.spell.Spell;

import static util.Util.getRandom;

public class Magician extends MagicianCharacter{
	public Spell chooseSpellToCast(){
		return spells[getRandom(spells.length)];
	}
	@Override
	public void move(){}
	@Override
	protected void defend(){}
}
