package units.spell;

//Исцеление - добавляет очков здоровья магу, произнесшему заклинания.
public class HealMyself extends Spell{
	private final String name = "healMyself";
	public int cast(final game.MagicianCharacter mag){
		mag.remedy(util.Util.getRandomInBound(REMEDY_MIN, REMEDY_MAX));
		return 0;
	}
}
