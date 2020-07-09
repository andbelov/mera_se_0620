package game;

import units.spell.*;
import util.Util;

public abstract class MagicianCharacter extends Character{
	abstract Spell chooseSpellToCast();
	protected void doSpecificAct(){
		chooseSpellToCast().cast(this);
	}

	private final int SPELL_MAX_NUM = 3;
	protected final Spell[] spells = new Spell[Util.getRandom(SPELL_MAX_NUM)];
	MagicianCharacter(){
		for(int i=0; i<spells.length ; i++){
			final Spell spell = new HealMyself();
			spells[i] = spell;
		}
	}

	public void remedy(final int remedy){
		health += remedy;
	}
	//	Маги. Могут колдовать заклинания. Заклинания могут делать что угодно
	//	со сценой и персонажами на ней.
	//	У каждого мага есть список заклинаний, которые он может использовать,
	//	но не больше 3.
	public void heal(final Unit unit){}

	@Override
	public int harm(){
		for(var sp: spells){
			final Unit enemy = getOpponent();
		}
		return 0;
	}
/*
			final Unit enemy = getOpponent();
			final String simpleName = enemy.getClass().getSimpleName();
			final UnitType unitType = UnitType.valueOf(simpleName);
			switch(unitType){
				case MAGICIAN: sp.cast((MagicianCharacter)enemy);
				case MONSTER:  sp.cast((MonsterCharacter) enemy);
			}
 */
	@Override
	public int defend(){
		return 0;
	}
	@Override
	public void beHarmedBy(final int loss){
		health -= loss;
	}
	@Override
	public int react(){
		return 0;
	}
	@Override
	public void beDamagedBy(final int loss){}
}
