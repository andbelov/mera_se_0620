package game;

import units.Magician;
import units.Monster;
import util.Util;

import static game.Unit.BoundType.MAX;
import static game.Unit.BoundType.MIN;
import static game.Unit.PropertyType.*;
import static util.Util.getRandomInBound;

abstract class Character implements Unit{
	private static final Class[] classes = {Monster.class, Magician.class};
	private final int[] properties = new int[PropertyType.values().length];
	protected String name = "UNKNOWN";
	protected int health;
	private int position;
	Character(){
		setProperties();
		health = getPropertyValue(HEALTH);
		//position = will be set by Scene
		//enemy = will be set in every tick on unit acts()
	}
	public static Class[] getClasses(){
		return classes;
	}
	private void setProperties(){
		final int uti = getUnitTypeIndex();
		for(var ad: PropertyType.values()){
			final int adi = ad.ordinal();
			final boolean isZeroDefense = DEFENSE==ad;
			final int min = PROPERTY_BOUND[uti][adi][MIN.ordinal()];
			final int max = PROPERTY_BOUND[uti][adi][MAX.ordinal()];
			properties[adi] = isZeroDefense ? 0
		            : getRandomInBound(min, max);
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
		for(int i=0; i<classes.length; ++i){
			if(classes[i] == this.getClass()){
				return i;
			}
		}
		return -1;
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
	public void play(){
		for(int actCount = 0; actCount<getPropertyValue(ACTIONS_PER_PLAY); ++actCount){
			move();
			assert(Scene.isMovedCorrectly(this));
			doSpecificAct();
			assert(Scene.isActedCorrectly(this));
			if(!isAlive()){
				return;
			}
		}
	}
	// simple things which an unit HAVE to do (inherited methods):
	protected abstract void doSpecificAct();
	protected abstract void move();
	protected abstract void defend();

	// simple things which an unit CAN do :
	public void beHarmedBy(final int loss){
		health -= loss;
	}
	//no exception: opponent may be itself!
	public Unit getRandomOpponent(){
		assert(Scene.isAliveUnitsToFight());
		final Unit[] units = Scene.getUnits();
		Unit u = null;
		do{
			u = units[Util.getRandom(units.length)];
		}while(!u.isAlive());
		return u;
	}

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
	public int getPosition(){
		return position;
	}
	@Override
	public void setPosition(Integer uniquePosition){
		this.position = uniquePosition;
	}
}

