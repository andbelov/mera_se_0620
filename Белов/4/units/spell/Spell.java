package units.spell;

import game.MagicianCharacter;

// 	Создайте абстрактный класс Spell с методом cast - произнесение заклинания
//		и полем "название заклинания".
//Создайте несколько конкретных заклинаний. Например:
public abstract class Spell{
	//HealMyself
	final int REMEDY_MIN = 1;
	final int REMEDY_MAX = 3;
	final int HARM_MIN = 1;
	final int HARM_MAX = 3;

	public abstract void cast(MagicianCharacter mag);
	//abstract public int cast();
	//abstract public int cast(final MagicianCharacter magician);
	//abstract public int cast(final MonsterCharacter monster);
	//abstract public int cast(final Scene scene);

	public void anonce(final MagicianCharacter mag){
		System.out.println("Маг "+	mag.getName()
				+" читает заклинание "+this.getName());
	}
	public abstract String getName();
}
