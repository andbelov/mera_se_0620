import java.util.Stack;

import static util.Util10.*;

@SuppressWarnings("SpellCheckingInspection")
class PinkFloyd{
	private final int DX = 0;
	private final int DY = 1;
	private final int HB = 2;
	private final int MX = 13; //giveRandomInBound(2, rx);
	private final int MY = 10; //giveRandomInBound(2, ry);
	private final int I0 = 0;
	private final int IX = MX - 1;
	private final int IY = MY - 1;
	private final boolean[][][] m = new boolean[MX][MY][3];
	private final int[][] maze = new int[MX][MY];
	private final int entrX;
	private final int entrY;
	private final int exitX;
	private final int exitY;
	private int xC;
	private int yC;
	private final StringBuilder sb = new StringBuilder();

	PinkFloyd(){
		setSeed(1);
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
		printWalls(0);
	}
	private void testWalls(){
		for(int x = I0; x < MX; x++){
			for(int y = I0; y < MY; y++){
				final boolean q = (x > 2 && x < MX - 3) ^ (y > 3 && y < 8);
				m[x][y][DX] = q;//x%2==I0 || y%2==I0;
				m[x][y][DY] = q;//x%2==I0 || y%2==I0;
			}
		}
		printWalls(0);
	}
	private void randomWalls(){
		for(int x = I0; x < MX; x++){
			for(int y = I0; y < MY; y++){
				for(int dx = 0; dx < 2; dx++){
					m[x][y][dx] = giveRandom();
				}
			}
		}
		printWalls(0);
	}
	private void printCurrent(final String str){ printPoint(str, xC, yC); }
	private void printPoint(final String str, final int x, final int y){
		System.out.println(str + " ["+x + "][" + y + "]"
				+ (isOutOfBorder(x,y) ? " is OutOfBorder"
				: "="+(m[x][y][DX]?'1':'0')+'/'+(m[x][y][DY]?'1':'0')+','+(m[x][y][HB]?'1':'0')));
	}
	public void digPath(){
		Stack<int[]> moves = new Stack<>();
		int count = 0;
		do{
			//if(count>10 && count <20)
			//System.out.println("===============");
			//printCurrent("Beg:");
			final int dRand = giveRandom(2);
			final int sRand = -1 + 2*giveRandom(2);
			boolean isMoved = false;
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
					if(exitX==xN && exitY==yN){
						System.out.println("Exit found, move back");
						isMoved = false;
					}else{
						final int[] move1 = new int[2];
						move1[0]=xC; move1[1]=yC;
						moves.push(move1);
						isMoved = true;
						xC = xN;
						yC = yN;
					}
					break BREAK;
				}
			}
			if(!isMoved && moves.size()>0){
				System.out.println("moving back");
				final int[] move1 = moves.pop();
				xC=move1[0]; yC=move1[1];
			}
			printWalls(count);
		}while(++count<99999 && moves.size()>0);
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

	boolean isOutOfMove(final int x, final int y){
		return I0 > x || x >= IX || I0 > y || y >= IY;
	}
	boolean isOutOfBorder(final int x, final int y){
		return I0 > x || x >= MX || I0 > y || y >= MY;
	}
	/*boolean isWall(final int d, final int sh){
		return m[xWall(d, sh)][yWall(d, sh)][d];
	}*/
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

	boolean isLefWall(final int x, final int y){
		return m[x][y][DX];
	}
	boolean isRitWall(final int x, final int y){
		return m[x + 1][y][DX];
	}
	boolean isTopWall(final int x, final int y){
		return m[x][y][DY];
	}
	boolean isBotWall(final int x, final int y){
		return m[x][y + 1][DY];
	}

	/*	boolean isBorder0(final int q){
			return q == I0;
		}
		boolean isBorderM(final int q, final boolean dx){
			return q == (dx ? IX : IY);
		}
		boolean isXBorder(final int x){
			return I0==x || isBorderM(x, DX);
		}
		boolean isYBorder(final int y){
			return I0==y || isBorderM(y, !DX);
		}
		boolean isLefWall(final int x, final int y){
			return !isBorder0(x) && m[IX][y];
		}
		boolean isRitWall(final int x, final int y){
			return !isBorderM(x, DX) && m[x + 1][y];
		}
		boolean isTopWall(final int x, final int y){
			return !isBorder0(y) && m[x][IY];
		}
		boolean isBotWall(final int x, final int y){
			return !isBorderM(y, !DX) && m[x][y + 1];
		}*/
	/*boolean isLessFree(final int x, final int y, final int Q){
		return isLeft(x) || m[x-1][y];
	}*/
	/*boolean isMostFree(final int q, final int Q){
		return isBorder0(q) || m[q-1];
	}*/
	char giveCharForPrintMap(final int x, final boolean xx, final int y, final boolean yy){
		final boolean b = m[x][y][DX];
		final boolean r = m[x][y][DY];
		if(xx){
			if(yy){
				if(xC == x && yC == y) return 'c';
				if(entrX == x && entrY == y) return 'i';
				if(exitX == x && exitY == y) return 'o';
				//return (m[x][y][HB])?'B':'b';
				return b ? (r ? '+' : '[') : (r ? 'ˉ' : '∙'); //◌ free cell space
				//if(entrX==x && entrY==y) return 'e';
				//return '∙';//∙∙
			}else{// !yy
				return r ? '─' : ' ';
			}
		}else{ //!xx
			if(yy){
				return b ? '│' : ' ';
			}else{// !yy
				final boolean l = I0 != x && m[x - 1][y][DY];
				final boolean t = I0 != y && m[x][y - 1][DX];
				//return t?'Q':'q';
				return l ? (r ? (t ? (b ? '┼' : '┴') : (b ? '┬' : '─'))
				              : (t ? (b ? '┤' : '┘') : (b ? '┐' : '→')))
				         : (r ? (t ? (b ? '├' : '└') : (b ? '┌' : '←'))
				              : (t ? (b ? '│' : '↓') : (b ? '↑' : '▫')));//∙↑↓
			}
		}/*
		final boolean l = xx?(m[x][y][DY]):(yy?(m[x][y][DX]):(true));//m[x][y][DX] : m[x][y][DX];
		final boolean r = IX != x && !yy && m[x][y][DX];
		final boolean t = I0==y ? yy && m[x][y][DY] : m[x][y][DY];
		final boolean b = IY != y && !xx && m[x][y][DY];
		final boolean l_= I0 == x ? xx && m[x][y][DX] : !yy && m[x - (xx ? 0 : 1)][y][DX];
		final boolean r_= IX != x && !yy && m[x][y][DX];
		final boolean t_= I0 == y ? yy && m[x][y][DY] : !xx && m[x][y - (yy ? 0 : 1)][DY];
		final boolean b_= IY != y && !xx && m[x][y][DY];
		return l ? r ? t ? b ? '┼' : '┴' : b ? '┬' : '─'
		             : t ? b ? '┤' : '┘' : b ? '┐' : '→'
		         : r ? t ? b ? '├' : '└' : b ? '┌' : '←'
		             : t ? b ? '│' : 'ˈ' : b ? '˻' : ' '//∙↑↓
				;*/
	}
	char giveCharForPrintMap__(final int x, final boolean xx, final int y, final boolean yy){
		if(xx && yy){
			if(xC == x && yC == y) return 'C';
			//if(m[x][y][HB]) return 'b';
			return m[x][y][DX] ? m[x][y][DY] ? '+' : '-' : m[x][y][DY] ? '|' : '∙'; //◌ free cell space
		}
		//if(entrX==x && entrY==y) return 'e';
		final boolean l = I0 == x ? xx && m[x][y][DX] : !yy && m[x - (xx ? 0 : 1)][y][DX];
		final boolean r = IX != x && !yy && m[x][y][DX];
		final boolean t = I0 == y ? yy && m[x][y][DY] : !xx && m[x][y - (yy ? 0 : 1)][DY];
		final boolean b = IY != y && !xx && m[x][y][DY];
		return l ? r ? t ? b ? '┼' : '┴' : b ? '┬' : '─'
		             : t ? b ? '┤' : '┘' : b ? '┐' : '→'
		         : r ? t ? b ? '├' : '└' : b ? '┌' : '←'
		             : t ? b ? '│' : '!' : b ? '¡' : ' '//∙↑↓
				;
	}
	char giveCharForPrintMap_(final int x, final boolean xx, final int y, final boolean yy){
		if(xx && yy) return m[x][y][DX] ? m[x][y][DY] ? '+' : '-' : m[x][y][DY] ? '|' : '∙'; //◌ free cell space

		final boolean l = I0 != x && !yy && m[x - (xx ? 0 : 1)][y][DX];
		final boolean r = IX != x && !yy && m[x][y][DX];
		final boolean t = I0 == y ? yy ? m[x][y][DY] : false : !xx && m[x][y - (yy ? 0 : 1)][DY];
		final boolean b = IY != y && !xx && m[x][y][DY];
		return l ? r ? t ? b ? '┼' : '┴' : b ? '┬' : '─'
		             : t ? b ? '┤' : '┘' : b ? '┐' : '→'
		         : r ? t ? b ? '├' : '└' : b ? '┌' : '←'
		             : t ? b ? '│' : '!' : b ? '¡' : ' '//∙↑↓
				;
	}
}
