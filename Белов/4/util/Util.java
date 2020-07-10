package util;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public class Util{
	static final Random random = new Random();
	public static Class getRandomClass(Class[] classes){
		return classes[getRandom(classes.length)];
	}
	public static Object newRandomClass(final Class[] classes){
		try{
			final var ctor = getRandomClass(classes).getConstructor();
			ctor.setAccessible(true);
			return ctor.newInstance();
		}catch(InstantiationException | IllegalAccessException
			  | NoSuchMethodException | InvocationTargetException e){
			e.printStackTrace();
			return null;
		}
	}
	public static <T extends Enum<T>> T randomEnum(Class<T> enumClass) {
		T[] vals = enumClass.getEnumConstants();
		return vals.length == 0 ? null : vals[getRandom(vals.length)];
	}
	public static int getRandomInBound(final int min, final int max){
		if(max==min){
			return max;
		}
		final boolean maxGrMin = max > min;
		final int mx = maxGrMin ? max : min;
		final int mn = maxGrMin ? min : max;
		if(0>=(mx-mn)){
			throw new AssertionError(
					"getRandomInBound got wrong args:"
							+ "\nmin="+ min + "\tmax=" +max);
		}
		return random.nextInt(mx-mn)+mn;
	}
	public static int getRandom(final int i){
		if(i==0) return 0;
		if(i<0)
			throw new AssertionError(
					"getRandomInBound got wrong args="+ i);
		return random.nextInt(i);
	}
}
