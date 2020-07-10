package units.spell;

import game.MagicianCharacter;
import game.Scene;
import util.Util;

//Мигрень - наносит урон всем магам.
public class MigraineAllMagicians extends Spell{
	public MigraineAllMagicians(){}
	@Override
	public String getName(){
		return this.getClass().getSimpleName();
	}
	public void cast(final MagicianCharacter mag){
		final var harm = Util.getRandomInBound(HARM_MIN, HARM_MAX);
		final var units = Scene.getUnits();
		for(var u: units){
			if(!Scene.isUnitMagician(u)){
				continue; //for all! mags, and no exception for itself!
			}
			u.beHarmedBy(harm);
			System.out.println("Маг " + mag.getName()
					+ " нанес урон магу " + u.getName()
					+ " на " + harm
					+ ". Теперь у него " + u.getHealth() + " здоровья");
			Scene.anonceIfUnitKilled(u);
		}
	}
}
