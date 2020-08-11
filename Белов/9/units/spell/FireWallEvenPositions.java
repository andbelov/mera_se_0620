package units.spell;

import game.MagicianCharacter;
import game.Scene;

//Стена огня - наносит урон всем персонажам на четных позициях.
class FireWallEvenPositions extends Spell{
	public FireWallEvenPositions(){}
	@Override
	public String getName(){
		return this.getClass().getSimpleName();
	}
	public void cast(final MagicianCharacter mag){
		Scene.getUnits().listIterator().forEachRemaining(enemy -> {
			//for all, and no exception for itself!
			if(0==enemy.getPosition()%2){
				mag.harmBySpell(enemy);
			}
		});
	}
}
