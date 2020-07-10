package units.spell;

import game.MagicianCharacter;
import game.Scene;
import util.Util;

// Молния - наносит урон любому 1 персонажу.
class StrikeLightningOne extends Spell{
	public StrikeLightningOne(){}
	@Override
	public String getName(){
		return this.getClass().getSimpleName();
	}
	public void cast(final MagicianCharacter mag){
		final var harm = Util.getRandomInBound(HARM_MIN, HARM_MAX);
		final var opponent = mag.getRandomOpponent();
		//no exception: opponent may be itself!
		opponent.beHarmedBy(harm);
		System.out.println("Маг " + mag.getName()
				+ " нанес урон монстру " + opponent.getName()
				+ " на " + harm
				+ ". Теперь у него " + opponent.getHealth() + " здоровья");
		Scene.anonceIfUnitKilled(opponent);
	}
}
