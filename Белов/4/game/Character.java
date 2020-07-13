package game;

import units.Knight;
import units.Magician;
import units.Monster;
import units.Robber;
import util.Util;

import static game.Unit.BoundType.MAX;
import static game.Unit.BoundType.MIN;
import static game.Unit.PropertyType.*;
import static util.Util.getRandomInBound;

@SuppressWarnings({"unused", "EmptyMethod"})
abstract class Character implements Unit{
	private static final Class<?>[] classes = {
			Monster.class,
			Magician.class,
			Knight.class,
			Robber.class,
	};
	enum FighterType{
		MONSTER,
		MAGICIAN,
		KNIGHT,
		ROBBER,
		Class;
		Class<? extends Character> getClass(final FighterType ft){
			return switch(ft){
				case MONSTER -> Monster .class;
				case MAGICIAN -> Magician.class;
				case KNIGHT -> Knight  .class;
				case ROBBER -> Robber  .class;
				default -> throw new IllegalStateException("Unexpected value: " + ft);
			};
		}
	}
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
	public static Class<?>[] getClasses(){
		return classes;
	}
	private void setProperties(){
		final int uti = getUnitTypeIndex();
		for(var ad: PropertyType.values()){
			final int adi = ad.ordinal();
			//final boolean isZeroDefense = DEFENSE==ad;
			final int min = PROPERTY_BOUND[uti][adi][MIN.ordinal()];
			final int max = PROPERTY_BOUND[uti][adi][MAX.ordinal()];
			properties[adi] = //isZeroDefense ? 0 :
			                  getRandomInBound(min, max);
			assert isPropertyInBound(ad, properties[adi]);
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
	@SuppressWarnings("unused")
	int getPropertyValue(final String propName){
		return properties[getPropertyIndex(propName)];
	}
	public void doYourTurn(){
		System.out.println("-- " + getTitle() + " " + getName()
				+ " на поз. " + getPosition() + " --");
		//isDead() has been checked before i call this method
		for(int actCount = 0; actCount<getPropertyValue(ACTIONS_PER_TURN); ++actCount){
			move();
			assert(Scene.isMovedCorrectly(this));
			doSpecificAct();
			assert(Scene.isActedCorrectly(this));
			if(isDead()){
				System.out.print('.');
				//System.out.println(",но сам уже мертв ...");
				return;
			}
		}
	}
	protected abstract String getTitle();
	// simple things which an unit HAVE to do (inherited methods):
	abstract void doSpecificAct();
	protected abstract void move();

	// simple things which an unit CAN do :
	public void beHarmedFromWith(final Unit enemy, final int loss){
		health -= loss;
	}
	//no exception: opponent may be itself!
	@SuppressWarnings("UnusedAssignment")
	public Unit getRandomOpponent(){
		////assert(Scene.isAliveUnitsToFight());
		final var units = Scene.getUnits();
		Unit u = null;
		////do{
			u = units.get(Util.getRandom(units.size()));
		////}while(!u.isDead());
		////assert u.isDead();
		return u;
	}
	protected void harm(final Unit enemy, final int harm){
		if(this==enemy){
			System.out.println(", стал МАЗОХИСТОМ");
		}
		System.out.println(", решил напасть на " + enemy.getName()
				+ ", на поз. " + enemy.getPosition());
		if(enemy.isDead()){
			System.out.println(", но враг уже мертв ...");
			return;
		}
		final int prevHealth = enemy.getHealth();
		/* final int harm = mag.getPropertyValue(HARM);
		 * If a mag has const harm - uncomment line above in Spell
		 * (like each Monster has his own fight force as a const),
		 * But for Mag, i guess he has a spell depending on random mud at each time moment */
		enemy.beHarmedFromWith(this, harm);
		System.out.println(", с уроном " + harm
				+ ". Здоровье врага было " + prevHealth
				+ ", теперь " + enemy.getHealth());
		if(enemy.isDead()){
			Scene.anonceKilled(enemy);
		}
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
	public boolean isDead(){
		return 0 > getHealth();
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

