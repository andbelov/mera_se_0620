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

	final static int REMEDY_MIN = 1;
	final static int REMEDY_MAX = 3;
	final static int HARM_MIN = 1;
	final static int HARM_MAX = 3;

	public abstract void cast(MagicianCharacter mag);

	public void tellAboutCast(final MagicianCharacter mag){
		System.out.println("Маг "+	mag.getName()
				+" читает заклинание "+this.getName());
	}
	public abstract String getName();
}
