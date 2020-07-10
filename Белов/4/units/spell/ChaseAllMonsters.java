package units.spell;

import game.MagicianCharacter;
import game.Scene;
import util.Util;

//Изгнание монстров - наносит урон всем монстрам.
class ChaseAllMonsters extends Spell{
	public ChaseAllMonsters(){}
	@Override
	public String getName(){
		return this.getClass().getSimpleName();
	}
	public void cast(final MagicianCharacter mag){
		final var harm = Util.getRandomInBound(HARM_MIN, HARM_MAX);
		final var units = Scene.getUnits();
		for(var u: units){
			if(!Scene.isUnitMonster(u)){
				continue;
			}
			u.beHarmedBy(harm);
			System.out.println("Маг " + mag.getName()
					+ " нанес урон монстру " + u.getName()
					+ " на " + harm
					+ ". Теперь у него " + u.getHealth() + " здоровья");
			Scene.anonceIfUnitKilled(u);
		}
	}
}
