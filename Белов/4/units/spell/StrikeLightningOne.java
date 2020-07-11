package units.spell;

import game.MagicianCharacter;

// Молния - наносит урон любому 1 персонажу.
class StrikeLightningOne extends Spell{
	public StrikeLightningOne(){}
	@Override
	public String getName(){
		return this.getClass().getSimpleName();
	}
	public void cast(final MagicianCharacter mag){
		final var enemy = mag.getRandomOpponent();
		//no exception: opponent may be itself!
		mag.harmBySpell(enemy);
	}
}
