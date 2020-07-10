package units.spell;

import game.MagicianCharacter;
import util.Util;

// Молния - наносит урон любому 1 персонажу.
class StrikeLightningOne extends Spell{
	@Override
	public String getName(){
		return "strikeLightningOne";
	}
	public void cast(final MagicianCharacter mag){
		final var harm = Util.getRandomInBound(HARM_MIN, HARM_MAX);
		final var opponent = mag.getOpponent();
		opponent.beHarmedBy(harm);
		System.out.println("Маг " + mag.getName()
				+ " нанес урон монстру " + opponent.getName()
				+ " на " + harm
				+ ". Теперь у него " + opponent.getHealth() + " здоровья");
	}
}
