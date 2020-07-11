package game;

import units.spell.Spell;

import static util.Util.getRandomInBound;
import static util.Util.newRandomClass;

public abstract class MagicianCharacter extends Character{
	public final static int REMEDY_MIN = 1;
	public final static int REMEDY_MAX = 3;
	public final static int HARM_MIN = 1;
	public final static int HARM_MAX = 3;
	private final int SPELL_MAX_NUM_INCLUSIVELY = 3;
	protected final Spell[] spells = new Spell[getRandomInBound(1, 1+ SPELL_MAX_NUM_INCLUSIVELY)];
	protected MagicianCharacter(){
		System.out.println("У нового мага будет "
				+ spells.length + " страничная книга заклинаний:");
		for(int i=0; i<spells.length ; i++){
			spells[i] = (Spell) newRandomClass(Spell.getClasses());
			//debug spells[i] = new MigraineAllMagicians();
			assert spells[i] != null;
			System.out.println(spells[i].getName());
		}
	}

	protected abstract Spell chooseSpellToCast();
	protected void doSpecificAct(){
		final var spell = chooseSpellToCast();
		System.out.println("-- ☻ Маг " + getName()
				+ " на поз. " + getPosition()
				+ " -- читает заклинание "+spell.getName());
		spell.cast(this);
	}
	@Override
	protected void defend(){

	}
	public void remedy(final int remedy){
		health += remedy;
	}
	//i call this harm from Spell class, so "this" here is another
	// and it's ok, just by "happy case" when i call Spell from Mag
	// spell return it back with call
	public void harmBySpell(final Unit enemy){
		harm(enemy, getRandomInBound(HARM_MIN, HARM_MAX));
	}
}
