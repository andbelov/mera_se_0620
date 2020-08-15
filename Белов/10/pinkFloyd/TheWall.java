package pinkFloyd;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

import static util.Util10.giveRandom;
import static util.Util10.giveRandomInBound;

public class TheWall{
	// 1. Понимаю, что надо было изначально все делать на enum для направлений
	// и смещений по ним, но увы, сделал ставку на
	// более компактный вариант представления стен и ячеек с шагом 2 (классика?)
	// а с шагом 1 (т.е в 4 раза экономится память , что наверняка критично на больших лабиринтах)
	// многое протестировал, переделывать уже тяжеловато с желанием
	// И баги тоже сыпались.
	// 2. Так же, все координаты делать не как x и y, а как класс с функицями обслугой,
	// иначе у меня везде x и y "ходят парами" и вносят путанницу и баги 146 %
	// (уже 42 раза словил багу, забыл поменять X на Y
	// За то надебажился, весь код - мой :-) без гугля.
    public enum Dir{
		UP(1), DOWN(3), LEFT(0), RIGHT(2);
		final private int dir;
		Dir(final int dir){
			this.dir = dir;
		}
		/*public static boolean isDirX(final int dir){
			return 0==dir%2;
		}
		public int shift(final int dir){
			return this.dir;
		}*/
	}
	private final int DX = 0;
	private final int DY = 1;
	private final int HB = 2;
	private final int MX = 3;//giveRandomInBound(12, 14);
	private final int MY = 3;//giveRandomInBound(5, 6);
	private final int I0 = 0;
	private final int IX = MX - 1;
	private final int IY = MY - 1;
	private final boolean[][][] m = new boolean[MX][MY][3];
	private final int entrX;
	private final int entrY;
	private final int exitX;
	private final int exitY;
	private int xC;
	private int yC;
	private final ArrayList<int[]> studentsPos = new ArrayList<>();
	private final Stack<int[]> path = new Stack<>();
	private final StringBuilder sb = new StringBuilder();

	public int[] giveEntrance(){
		final int[] pos = new int[2];
		pos[DX] = entrX; pos[DY] = entrY;
		return pos;
	}
	public int[] giveExit(){
		final int[] pos = new int[2];
		pos[DX] = exitX; pos[DY] = exitY;
		return pos;
	}
	//private int[] giveCurrentPosition();
	public TheWall(){
		System.out.println("-- PinkFloyd, The Wall. China Great Wall socks --");
		System.out.println("'c' - Current position of digging process");
		System.out.println("'i' - Entrance");
		System.out.println("'o' - Exit");
		//if cell in maze true then it's a wall;
		initWalls();
		//testWalls();
		//randomWalls();
		final int dEntrance = giveRandom(2);
		final boolean isEntrZeroWall = giveRandom();
		//System.out.println("dEntrance:"+dEntrance+" isEntrZeroWall:"+isEntrZeroWall);
		entrX = isDirX(dEntrance) ? giveBorder(dEntrance,  isEntrZeroWall)  : giveRandom(IX) ;
		entrY = isDirX(dEntrance) ? giveRandom(IY) : giveBorder(dEntrance,   isEntrZeroWall) ;
		exitX = isDirX(dEntrance) ? giveBorder(dEntrance,  !isEntrZeroWall) : giveRandom(IX) ;
		exitY = isDirX(dEntrance) ? giveRandom(IY) : giveBorder(dEntrance,  !isEntrZeroWall) ;
		System.out.println("Entrance:["+entrX + "][" + entrY + "], Exit:[" + exitX + "][" + exitY+"]");
		xC = exitX;
		yC = exitY;
		digWall(dEntrance, step(!isEntrZeroWall));
		xC = entrX;
		yC = entrY;
		digWall(dEntrance, step(isEntrZeroWall));
		m[xC][yC][HB] = true;
		printWalls(0);
		digPath();
	}
	private void initWalls(){
		for(int x = I0; x < MX; x++){
			for(int y = I0; y < MY; y++){
				//final boolean b = I0 == x || I0 == y || x == IX || y == IY;
				final boolean b = x == IX || y == IY;
				m[x][y][DX] = IY != y;
				m[x][y][DY] = IX != x;
			}
		}
		//printWalls(0);
	}
	private void printCurrent(final String str){ printPoint(str, xC, yC); }
	private void printPoint(final String str, final int x, final int y){
		System.out.println(str + " ["+x + "][" + y + "]"
				+ (isOutOfBorder(x,y) ? " is OutOfBorder"
				: "="+(m[x][y][DX]?'1':'0')+'/'+(m[x][y][DY]?'1':'0')+','+(m[x][y][HB]?'1':'0')));
	}
	public Queue<int[]> givePath(String password){ //for cheaters
		return "PASSWORD".equals(password) ? new ArrayDeque<>(path) : null;
	}
	public int regStudent(){
		final int[] p = new int[2];
		p[DX] = xC; p[DY]=yC;
		studentsPos.add(p);
		return studentsPos.size()-1;
	}
	public void updStudentPos(final int id, final int[] pos){
//		printWalls(0);
		System.out.println(id + " moving ");
		if(null==pos){
			throw new AssertionError(
					"Provided null coordinates");
		}
		if(isOutOfMove(pos[DX], pos[DY])){
			throw new AssertionError(
					"Provided coordinates [" + pos[DX] + "][" + pos[DY] + "]"
							+ " are Out of [" + IX + "][" + IY + "]");
		}
		studentsPos.set(id, pos);
	}
	public void digPath(){
		System.out.println("-- Starting digging The Wall, creating maze .. --");
		Stack<int[]> moves = new Stack<>();
		int count = 0;
		do{
			//if(count>10 && count <20)
			//printCurrent("Pre:");
			//printWalls(count); //before each digging
			final int dRand = giveRandom(2);
			final int sRand = -1 + 2*giveRandom(2);
			boolean isMoved = false;
			if(exitX==xC && exitY==yC){
				System.out.println("Exit found, move back");
				path.addAll(moves);
				path.remove(0);
				final int[] pos = new int[2];
				pos[0]=xC; pos[1]=yC;
				path.push(pos);
				//isMoved = false;
			}else{
				BREAK:
				for(int dInd=2; --dInd>=0; ){
					final int d = (dRand==dInd)?1:0;
					for(int sInd=-1; sInd<=1; sInd+=2){
						final int s = sInd*sRand;
						final int xN = xC + (DX==d?s:0);//11;1-1;01;0-1;
						final int yN = yC + (DY==d?s:0);
						//System.out.println("shift "+ (DX==d?'x':' ') + (DY==d?'y':' ') + " with " +s);
						//printPoint("New:", xN, yN);
						if(isOutOfMove(xN, yN)){
							//System.out.println("out of move");
							continue;
						}
						if(m[xN][yN][HB]){
							//System.out.println("has been");
							continue;
						}
						digWall(d, s);
						m[xN][yN][HB] = true;
						final int[] move1 = new int[2];
						move1[0]=xC; move1[1]=yC;
						moves.push(move1);
						xC = xN;
						yC = yN;
						isMoved = true;
						//for(int[] p : moves){
						//	System.out.println("moves " + p[0] + " " + p[1]);
						//}
						break BREAK;
					}
				}
			}
			//printCurrent("Aft:");
			if(!isMoved && moves.size()>0){
				//System.out.println("moving back");
				final int[] move1 = moves.pop();
				xC=move1[0]; yC=move1[1];
				//printCurrent("Pop:");
			}
//			printWalls(count); //after each digging
		}while(++count<99999 && moves.size()>0);
		/*for(int[] p : path){
			System.out.println(p[0] + " " + p[1]);
		}*/
//		printWalls(count); //FINALLY
		System.out.println("-- Finished digging The Wall, maze created --");
	}
	public void printWalls(int count){
		sb.setLength(0);
		sb.append(' ');
		for(int x = I0; x < MX; x++){
			if(x<10){ sb.append(' ');}
			sb.append(x);
		}
		sb.append(',').append(count);
		System.out.println(sb);
		for(int y = I0; y < MY; y++){
			sb.setLength(0);
			sb.append(' ');
			for(int x = I0; x < MX; x++){
				sb.append(giveCharForPrintMap(x, false, y, false));
				if(IX != x){
					sb.append(giveCharForPrintMap(x, true, y, false));
				}
			}
			System.out.println(sb);
			if(IY == y){
				continue;//
			}

			//-------------------------
			sb.setLength(0);
			sb.append(y);
			for(int x = I0; x < MX; x++){
				sb.append(giveCharForPrintMap(x, false, y, true));
				if(IX != x){
					sb.append(giveCharForPrintMap(x, true, y, true));
				}
			}
			System.out.println(sb);

			sb.setLength(0);
			sb.append(' ');
			for(int x = I0; x < MX; x++){
				sb.append(m[x][y][DX]?'X':'x').append(m[x][y][DY]?'Y':'y');
			}
			//System.out.println(sb);
		}
	}
	public boolean isWall(final int x, final int y, final int dir){
		return switch(dir){
			case 2 ->  m[x  ][y  ][DX];//LEFT
			case 3 ->  m[x+1][y  ][DX];//RIGHT
			case 0 ->  m[x  ][y  ][DY];//UP
			case 1 ->  m[x  ][y+1][DY];//DOWN
			default -> throw new IllegalStateException("Unexpected value: " + dir);
		};
	}
	public boolean isOutOfMove(final int x, final int y){
		return I0 > x || x >= IX || I0 > y || y >= IY;
	}
	boolean isOutOfBorder(final int x, final int y){
		return I0 > x || x >= MX || I0 > y || y >= MY;
	}
	void digWall(final int d, final int sh){
		//if(!isWall(d, sh)) throw new AssertionError("xC:"+xC+"yC:"+yC+"!isWall("+d+","+sh+")");
		if(DX == d){
			m[xC+(sh+1)/2][yC][DX] = false;
		}else{
			m[xC][yC+(sh+1)/2][DY] = false;
		}
	}
	int not(final int d){
		return 0==d ? 1 : 0;
	}
	int step(final boolean isZero){
		return isZero ? -1 : 1;
	}
	boolean isDirX(final int d){
		return DX==d;
	}
	boolean isDirY(final int d){
		return DX==d;
	}
	int giveBorder(final int d, final boolean isZero){
		return isZero ? I0 : DX==d ? IX-1 : IY-1;
	}
	char giveCharForPrintMap(final int x, final boolean xx, final int y, final boolean yy){
		final boolean b = m[x][y][DX];
		final boolean r = m[x][y][DY];
		if(xx){
			if(yy){
				for(int i=0; i<studentsPos.size(); i++){
					if(studentsPos.get(i)[DX]==x
						&& studentsPos.get(i)[DY]==y){
						return (char)(i+(int)'0');
					}
				}
				if(xC == x && yC == y) return 'c';
				if(entrX == x && entrY == y) return 'i';
				if(exitX == x && exitY == y) return 'o';
				//return (m[x][y][HB])?'B':'b';
				//return b ? (r ? '+' : '[') : (r ? 'ˉ' : '∙'); //◌ free cell space
				return '∙';
			}else{// !yy
				return r ? '─' : ' ';
			}
		}else{ //!xx
			if(yy){
				return b ? '│' : ' ';
			}else{// !yy
				final boolean l = I0 != x && m[x - 1][y][DY];
				final boolean t = I0 != y && m[x][y - 1][DX];
				return l ? (r ? (t ? (b ? '┼' : '┴') : (b ? '┬' : '─'))
				              : (t ? (b ? '┤' : '┘') : (b ? '┐' : '→')))
				         : (r ? (t ? (b ? '├' : '└') : (b ? '┌' : '←'))
				              : (t ? (b ? '│' : '↓') : (b ? '↑' : '▫')));//∙↑↓
			}
		}
	}
}
