package util;

import java.util.Random;

public class Util5{
	static final Random random = new Random();
	public static int giveRandom(final int i){
		if(i==0) return 0;
		if(i<0)
			throw new AssertionError(
					"getRandomInBound got wrong args="+ i);
		return random.nextInt(i);
	}
	public static boolean giveRandom(){
		return 0 != giveRandom(2);
	}
	public static String giveRandomString(int len){
		char[] ca = new char[len];
		while(0<=--len){
			ca[len] = giveRandom()
			          ? giveRandomChar()
			          : giveRandomNumChar();
		}
		return new String(ca);
	}
	public static char giveRandomChar(){
		return (char)((int)(giveRandom()?'a':'A')
				+ giveRandom(26));
	}
	public static char giveRandomNumChar(){
		return (char)((int)'0' + giveRandom(10));
	}
}
