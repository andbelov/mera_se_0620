package units.spell;

import game.MagicianCharacter;

// 	Создайте абстрактный класс Spell с методом cast - произнесение заклинания
//		и полем "название заклинания".
//Создайте несколько конкретных заклинаний. Например:
public abstract class Spell{
	Spell(){}
	private static final Class[] classes = {
			ChainLightningAllOthers.class,
			ChaseAllMonsters.class,
			FireWallEvenPositions.class,
			HealMyself.class,
			MigraineAllMagicians.class,
			StrikeLightningOne.class,
			TouchFireNeighbors.class,
	};
	public static Class[] getClasses(){
		return classes;
	}

	public abstract void cast(MagicianCharacter mag);

	public abstract String getName();
}
