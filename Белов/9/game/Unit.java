package game;

//[14:39] andrey.tarasov@mera.com //Если есть монстр1 и монстр2.
//То монстр1 всегда наносит 3 урона любому другому персонажу,
// а монстр2 всегда наносит 10 урона.
//Числа 3 и 10 - случайные. Но конкретный монстр - всегда
// наносит одно и то же число урона. Монстры бывают сильные
//, бывают слабые. Сильные всегда бьют сильно

public interface Unit{
	//actions which an unit may do while one step, define in abstract specific class
	//enum AllActionType{WAIT, FLIGHT, FIGHT
	//	, HEAL_ITSELF, HEAL_SOMEBODY
	//	, SAVE, HAND_OVER_SOMETHING}
	//enum UnitType{ MONSTER, MAGICIAN, }
	enum PropertyType{ HARM, DEFENSE, HEALTH, ACTIONS_PER_TURN}
	enum BoundType{ MIN, MAX}
	int[][][] PROPERTY_BOUND = {
			{{10,15},{0,0},{16,24},{1,1}}, //MONSTER
			{{20,25},{0,0},{0,8},{1,1}}, //MAGICIAN
			{{5,10},{3,5},{24,32},{1,2}}, //KNIGHT
			{{15,20},{1,2},{8,16},{2,3}}, //ROBBER
	};

	// an unit setters/getters
	String getName();
	void setName(final String name);
	int getPosition();
	void setPosition(Integer uniquePosition);
	int getHealth();
	boolean isDead();

	// actions in scene which an unit MAY do:
	void doYourTurn();

	// simple things which an unit CAN do :
	//be harmed from enemy with loss
	void beHarmedFromWith(final Unit enemy, final int loss);

}

