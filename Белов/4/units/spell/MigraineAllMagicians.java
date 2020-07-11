package units.spell;

import game.MagicianCharacter;
import game.Scene;

//Мигрень - наносит урон всем магам.
public class MigraineAllMagicians extends Spell{
	public MigraineAllMagicians(){}
	@Override
	public String getName(){
		return this.getClass().getSimpleName();
	}
	public void cast(final MagicianCharacter mag){
		Scene.getUnits().iterator().forEachRemaining(enemy -> {
			//for all! mags, and no exception for itself!
			if(Scene.isUnitMagician(enemy)){
				mag.harmBySpell(enemy);
			}
		});
	}
}
