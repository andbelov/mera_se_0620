package units.spell;

import game.MagicianCharacter;
import game.Scene;

//Цепная молния - наносит урон, всем персонажам на сцене, кроме мага,
// который произносит заклинание
class ChainLightningAllOthers extends Spell{
	public ChainLightningAllOthers(){}
	@Override
	public String getName(){
		return this.getClass().getSimpleName();
	}
	public void cast(final MagicianCharacter mag){
		Scene.getUnits().listIterator().forEachRemaining(enemy -> {
			if(mag!=enemy){
				mag.harmBySpell(enemy);
			}
		});
	}
}
