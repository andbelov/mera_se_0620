package util;

import java.util.Random;

@SuppressWarnings("unused")
public class Util12{
	private static Random random = new Random();
	public static void setSeed(final int seed){
		 random.setSeed(seed);
	}
	public static <T extends Enum<T>> T giveRandomEnum(Class<T> enumClass) {
		T[] values = enumClass.getEnumConstants();
		return values.length == 0 ? null : values[giveRandom(values.length)];
	}
	public static <T> Class<?> giveRandomClass(Class<?>[] classes){
		return classes[giveRandom(classes.length)];
	}
	public static int giveRandomInBound(final int min, final int max){
		if(max==min){
			return max;
		}
		final boolean maxGrMin = max > min;
		final int mx = maxGrMin ? max : min;
		final int mn = maxGrMin ? min : max;
		if(0>=(mx-mn)){
			throw new AssertionError(
					"giveRandomInBound got wrong args:"
							+ "\nmin="+ min + "\tmax=" +max);
		}
		return random.nextInt(mx-mn)+mn;
	}
	public static int giveRandom(final int i){
		if(i==0) return 0;
		if(i<0)
			throw new AssertionError(
					"giveRandomInBound got wrong args="+ i);
		return random.nextInt(i);
	}
	public static boolean giveRandom(){
		return 0 != giveRandom(2);
	}
	public static String giveRandomString(int len){
		char[] ca = new char[len];
		while(0<--len){
			ca[len] = giveRandomChar(false);
		}
		ca[0] = giveRandomChar(true);
		return new String(ca);
	}
	public static char giveRandomChar(final boolean capital){
		return (char)((int)(capital?'A':'a')
				+ giveRandom(26));
	}
	public static char giveRandomNumChar(){
		return (char)((int)'0' + giveRandom(10));
	}
	private static int counter = -1;
	public static int giveIncrementedCounter(){
		return ++counter;
	}
}
