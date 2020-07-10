package game;

import units.spell.Spell;
import static util.Util.*;

public abstract class MagicianCharacter extends Character{
	private final int SPELL_MAX_NUM = 3;
	protected final Spell[] spells = new Spell[getRandomInBound(1, SPELL_MAX_NUM)];
	protected MagicianCharacter(){
		System.out.println("У нового мага будет "
				+ spells.length + " страничная книга заклинаний:");
		for(int i=0; i<spells.length ; i++){
			spells[i] = (Spell) newRandomClass(Spell.getClasses());
			assert spells[i] != null;
			System.out.println(spells[i].getName());
		}
	}

	protected abstract Spell chooseSpellToCast();
	protected void doSpecificAct(){
		final var spell = chooseSpellToCast();
		spell.tellAboutCast(this);
		spell.cast(this);
	}
	@Override
	protected void defend(){

	}
	public void remedy(final int remedy){
		health += remedy;
	}
}
