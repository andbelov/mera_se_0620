package units.spell;

import game.MagicianCharacter;
import game.Scene;
import util.Util;

//Цепная молния - наносит урон, всем персонажам на сцене, кроме мага,
// который произносит заклинание
class ChainLightningAllOthers extends Spell{
	@Override
	public String getName(){
		return "chainLightningAllOthers";
	}
	public void cast(final MagicianCharacter mag){
		final var harm = Util.getRandomInBound(HARM_MIN, HARM_MAX);
		final var units = Scene.getUnits();
		for(var u: units){
			if(mag==u){
				continue;
			}
			u.beHarmedBy(harm);
			System.out.println("Маг " + mag.getName()
					+ " нанес урон " + u.getName()
					+ " на " + harm
					+ ". Теперь у него " + u.getHealth() + " здоровья");
		}
	}
}
