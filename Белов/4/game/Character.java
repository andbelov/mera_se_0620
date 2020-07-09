package game;

import util.Util;

import static game.MagicianCharacter.*;
import static game.Scene.anonceIfUnitKilled;
import static game.Unit.BoundType.MAX;
import static game.Unit.BoundType.MIN;
import static game.Unit.PropertyType.*;
import static util.Util.*;

abstract class Character implements Unit{
	private final int[] properties = new int[PropertyType.values().length];
	protected String name = "UNKNOWN";
	protected int health;
	private int position;
	private Unit opponent;
	Character(){
		setProperties();
		health = getPropertyValue(HEALTH);
		//position = will be set by Scene
		//enemy = will be set in every tick on unit harm()
	}
	private void setProperties(){
		final int uti = getUnitTypeIndex();
		for(var ad: PropertyType.values()){
			final int adi = ad.ordinal();
			final boolean isZeroDefense = DEFENSE==ad;
			properties[adi] = isZeroDefense ? 0
		            : getRandomInBound(
		                PROPERTY_BOUND[uti][adi][MIN.ordinal()],
		                PROPERTY_BOUND[uti][adi][MAX.ordinal()]);
		}
	}
	public boolean isPropertyInBound(final PropertyType prop
			, final int propValue){
		final int uti = getUnitTypeIndex();
		final int pi = prop.ordinal();
		return propValue >= PROPERTY_BOUND[uti][pi][MIN.ordinal()]
			&& propValue <  PROPERTY_BOUND[uti][pi][MAX.ordinal()];
	}
	public int getUnitTypeIndex(){
		return UnitType.valueOf(this.getClass().getSimpleName()).ordinal();
	}
	protected int getPropertyIndex(final String propName){
		return PropertyType.valueOf(propName).ordinal();
	}
	protected int getPropertyValue(PropertyType prop){
		return properties[prop.ordinal()];
	}
	protected int getPropertyValue(final String propName){
		return properties[getPropertyIndex(propName)];
	}

	// actions in scene which an unit MAY do for 1 step:
	/*protected abstract AllActionType getAllActionType();
	private AllActionType getActionType(){
		AllActionType actionType = this.getAllActionType();
		actionType = ((this.getClass().getEnclosingClass()) this).getAllActionType();
		if(this.getClass().isInstance(MonsterCharacter.class)){
			actionType = ((MonsterCharacter) this).getAllActionType();
		}else if(this instanceof MagicianCharacter){
			assert getAllActionType() != null;
		}
		assert actionType != null;
		return AllActionType.valueOf(actionType.name());
	}*/
	public void play(){
		for(int actCount = 0; actCount<getPropertyValue(ACTIONS_PER_PLAY); ++actCount){
			setOpponent();
			assert(Scene.isOpponentChosenCorrectly(getOpponent()));
			move();
			doSpecificAct();
			anonceIfUnitKilled(getOpponent());
			anonceIfUnitKilled(this);
		}
	}
	protected abstract void doSpecificAct();
	protected void move(){}
	protected void fight(){
		Unit unit = this;
		final Unit enemy = unit.getOpponent();
		final int uHarm = unit.harm();
		assert(unit.isPropertyInBound(Unit.PropertyType.HARM, uHarm));
		final int eDefend = enemy.defend();
		assert(enemy.isPropertyInBound(Unit.PropertyType.DEFENSE, eDefend));
		enemy.beHarmedBy(uHarm);

		if(!enemy.isAlive()){
			return;
		}
		final int eReact = enemy.react();
		assert(enemy.isPropertyInBound(Unit.PropertyType.HARM, eReact));
		final int uDefend = unit.defend();
		assert(unit.isPropertyInBound(Unit.PropertyType.DEFENSE, uDefend));
		unit.beDamagedBy(eReact);
	}
	protected void defend(final Unit unit){}

	// an unit setters/getters
	@Override
	public String getName(){
		return name;
	}
	@Override
	public void setName(String name){
		this.name = name;
	}
	@Override
	public int getHealth(){
		return health;
	}
	@Override
	public boolean isAlive(){
		return 0 <= getHealth();
	}
	@Override
	public Unit getOpponent(){
		return opponent;
	}
	@Override
	public void setOpponent(){
		final Unit[] units = Scene.getUnits();
		opponent = units[Util.getRandom(units.length)];
	}
	@Override
	public int getPosition(){
		return position;
	}
	@Override
	public void setPosition(Integer uniquePosition){
		this.position = uniquePosition;
	}

	// simple things which an unit CAN do :
	abstract int harm();
	protected abstract int defend();
	protected abstract void beHarmedBy(final int loss);
	protected abstract int react();
	protected abstract void beDamagedBy(final int loss);
}

