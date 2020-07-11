package game;

//[14:39] andrey.tarasov@mera.com //Если есть монстр1 и монстр2.
//То монстр1 всегда наносит 3 урона любому другому персонажу,
// а монстр2 всегда наносит 10 урона.
//Числа 3 и 10 - случайные. Но конкретный монстр - всегда
// наносит одно и то же число урона. Монстры бывают сильные
//, бывают слабые. Сильные всегда бьют сильно

public interface Unit{
	//actions which an unit may do while one step, define in abstract cpecific class
	//enum AllActionType{WAIT, FLIGHT, FIGHT
	//	, HEAL_ITSELF, HEAL_SOMEBODY
	//	, SAVE, HAND_OVER_SOMETHING}
	//enum UnitType{ MONSTER, MAGICIAN, }
	enum PropertyType{ HARM, DEFENSE, HEALTH, ACTIONS_PER_TURN}
	enum BoundType{ MIN, MAX}
	int[][][] PROPERTY_BOUND = {
			{{5,10},{4,8},{5,15},{1,1}}, //MONSTER
			{{3,25},{2,3},{0,7},{1,1}}, //MAGICIAN
	};

	//int getUnitTypeIndex();
	boolean isPropertyInBound(final PropertyType prop
			, final int propValue);

	// an unit setters/getters
	String getName();
	void setName(final String name);
	int getPosition();
	void setPosition(Integer uniquePosition);
	int getHealth();
	boolean isAlive();

	// actions in scene which an unit MAY do:
	void doYourTurn();

	// simple things which an unit CAN do :
	void beHarmedBy(final int loss);
}

