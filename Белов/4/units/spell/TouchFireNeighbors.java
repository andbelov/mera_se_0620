package units.spell;

import game.MagicianCharacter;
import game.Scene;
import util.Util;

//Огненное касание - наносит урон персонажу, стоящему на соседней с магом позиции.
//Если на соседних позициях персонажей нет - никому урон не наносится.
class TouchFireNeighbors extends Spell{
	public TouchFireNeighbors(){}
	@Override
	public String getName(){
		return this.getClass().getSimpleName();
	}
	public void cast(final MagicianCharacter mag){
		final var harm = Util.getRandomInBound(HARM_MIN, HARM_MAX);
		final var units = Scene.getUnits();
		for(var u: units){
			if(1!=Math.abs(mag.getPosition()-u.getPosition())){
				continue;
			}
			u.beHarmedBy(harm);
			System.out.println("Маг " + mag.getName()
					+ " на позиции " + mag.getPosition()
					+ " нанес урон " + u.getName()
					+ " на позиции " + u.getPosition()
					+ " на " + harm
					+ ". Теперь у него " + u.getHealth() + " здоровья");
		}
	}
}
