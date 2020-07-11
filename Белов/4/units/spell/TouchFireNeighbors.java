package units.spell;

import game.*;

//Огненное касание - наносит урон персонажу, стоящему на соседней с магом позиции.
//Если на соседних позициях персонажей нет - никому урон не наносится.
class TouchFireNeighbors extends Spell{
	public TouchFireNeighbors(){}
	@Override
	public String getName(){
		return this.getClass().getSimpleName();
	}
	public void cast(final MagicianCharacter mag){
		tryHarm(mag, -1);
		tryHarm(mag, +1);
	}
	private void tryHarm(final MagicianCharacter mag
	                     , final int posOffset){
		final Unit enemy = Scene.findUnitLocated(
				mag.getPosition() + posOffset);
		if(null!=enemy){
			mag.harmBySpell(enemy);
		}
	}
}
