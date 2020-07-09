package util;

import java.util.Random;

public class Util{
	static final Random random = new Random();
	public static <T extends Enum<T>> T randomEnum(Class<T> enumClass) {
		T[] vals = enumClass.getEnumConstants();
		return vals.length == 0 ? null : vals[getRandom(vals.length)];
	}
	public static int getRandomInBound(final int min, final int max){
		if(max <= min || max <= 0 || min <= 0)
			throw new AssertionError(
					"getRandomInBound got wrong args:"
							+ "\nmin="+ min + "\tmax=" +max);
		return random.nextInt(max-min)+min;
	}
	public static int getRandom(final int i){
		if(i<=0)
			throw new AssertionError(
					"getRandomInBound got wrong args="+ i);
		return random.nextInt(i);
	}
}
