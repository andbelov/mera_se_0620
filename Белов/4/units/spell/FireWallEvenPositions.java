package units.spell;

import game.MagicianCharacter;
import game.Scene;
import util.Util;

//Стена огня - наносит урон всем персонажам на четных позициях.
class FireWallEvenPositions extends Spell{
	@Override
	public String getName(){
		return "fireWallEvenPositions";
	}
	public void cast(final MagicianCharacter mag){
		final var harm = Util.getRandomInBound(HARM_MIN, HARM_MAX);
		final var units = Scene.getUnits();
		for(var u: units){
			if(0==u.getPosition()%2){
				continue;
			}
			u.beHarmedBy(harm);
			System.out.println("Маг " + mag.getName()
					+ " нанес урон " + u.getName()
					+ " на позиции " + u.getPosition()
					+ " на " + harm
					+ ". Теперь у него " + u.getHealth() + " здоровья");
		}
	}
}
