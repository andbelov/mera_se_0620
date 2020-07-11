package units.spell;

import game.MagicianCharacter;
import game.Scene;
import units.Monster;

//Изгнание монстров - наносит урон всем монстрам.
class ChaseAllMonsters extends Spell{
	public ChaseAllMonsters(){}
	@Override
	public String getName(){
		return this.getClass().getSimpleName();
	}
	public void cast(final MagicianCharacter mag){
		Scene.getUnits().listIterator().forEachRemaining(enemy -> {
			if(enemy instanceof Monster){
				mag.harmBySpell(enemy);
			}
		});
	}
}
