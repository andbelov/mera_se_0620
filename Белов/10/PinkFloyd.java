import static util.Util10.giveRandom;

@SuppressWarnings("SpellCheckingInspection")
public class PinkFloyd{
	private final int DX = 0;
	private final int DY = DX+1;
	private final int MX = 19; //giveRandomInBound(2, rx);
	private final int MY = 5; //giveRandomInBound(2, ry);
	private final int I0 = 0;
	private final int IX = MX-1;
	private final int IY = MY-1;
	private final boolean[][][] m = new boolean[MX][MX][DY+1];
	private final int entrX;
	private final int entrY;
	private final int exitX;
	private final int exitY;
	private int xC;
	private int yC;
	final boolean[][] hasBeen = new boolean[MX][MX];
	private final StringBuilder sb = new StringBuilder();

	PinkFloyd(){
		//if cell in maze true then it's a wall;
		initWalls();
		//testWalls();
		//randomWalls();
		boolean isEntrOnWallX = giveRandom();
		boolean isEntrWallOnZero = giveRandom();
		entrX = isEntrOnWallX ? giveRandom(IX) : isEntrWallOnZero ? I0 : IX;
		entrY = isEntrOnWallX ? isEntrWallOnZero ? I0 : IY : giveRandom(MY);
		exitX = isEntrOnWallX ? giveRandom(IX) : !isEntrWallOnZero ? I0 : IX;
		exitY = isEntrOnWallX ? !isEntrWallOnZero ? I0 : IY : giveRandom(IY);
		m[entrX][entrY][isEntrOnWallX?DX:DY] = false;
		m[exitX][exitY][isEntrOnWallX?DX:DY] = false;
		System.out.println("Entrance:["+entrX + "][" + entrY + "], Exit:[" + exitX + "][" + exitY+"]");
		genPath();
		//printWalls();
	}
	private void initWalls(){
		for(int x = I0; x < MX; x++){
			for(int y = I0; y < MY; y++){
				//final boolean b = I0 == x || I0 == y || x == IX || y == IY;
				m[x][y][0] = m[x][y][1] = true;
			}
		}
	}
	private void testWalls(){
		for(int x = I0; x < MX; x++){
			for(int y = I0; y < MY; y++){
				final boolean q = (x > 2 && x<MX-3) ^ (y > 3 && y < 8);
				m[x][y][0] = q;//x%2==I0 || y%2==I0;
				m[x][y][1] = q;//x%2==I0 || y%2==I0;
			}
		}
	}
	private void randomWalls(){
		for(int x = I0; x < MX; x++){
			for(int y = I0; y < MY; y++){
				for(int dx = 0; dx < 2; dx++){
					m[x][y][dx] = I0 == x || I0 == y || x == IX || y == IY || giveRandom();
				}
			}
		}
	}
	public void genPath(){
		xC=entrX;
		yC=entrY;
		hasBeen[xC][yC] = true;
		int xO = xC;
		int yO = yC;
		int count = 0;
		while(20>count++){
			//if(count>5 && count <10)
				printWalls(count);
			//System.out.println("===============");
			//System.out.println("BEFORE:["+xC + "][" + yC + "]");
			final boolean yyR = giveRandom();
			final boolean stepR = giveRandom();
			boolean isMoved = false;
			BreakLabel:
			for(int i=2; --i>=0; ){
				final boolean yy = (0 == i) ^ yyR;
				for(int j=2; --j>=0; ){
					final int step = ((0 == j) ^ stepR) ? 1 : -1;
					final int xN = xC + (yy?0:+step);
					final int yN = yC + (yy?step:0);
					//System.out.println("yy:"+yy + " " + "step:" + step);
					if(isOutOfBorder(xN, yN)){
						continue;
					}
					if(!hasBeen[xN][yN]){
						assert(isWall(yy,step));
						deegWall(yy,step);
						hasBeen[xN][yN] = true;
						xO = xC;
						yO = yC;
						xC = xN;
						yC = yN;
						isMoved = true;
						//System.out.println("NEW:["+xC + "][" + yC + "]");
						break BreakLabel;
					}
				}
			}
			if(!isMoved){
				xC = xO;
				yC = yO;
				//System.out.println("BACK:["+xC + "][" + yC + "]");
			}
		}
	}

	public void printWalls(int count){
		final boolean compact = false;
		for(int y = I0; y < MY; y++){
			sb.setLength(0);
			/*for(int x = I0; x < MX; x++){
				sb.append(' ').append(m[x][y]?'■':'-');
			}
			sb.append('|');*/
			for(int x = I0; x < MX; x++){
				sb.append(giveCharForPrintMap(x, false, y, false));
				if(compact || IX==x){
					continue;//
				}
				sb.append(giveCharForPrintMap(x, true, y, false));
			}
			if(I0==y) sb.append(count);
			System.out.println(sb);
			if(compact || IY==y){
				continue;//
			}
			sb.setLength(0);
			for(int x = I0; x < MX; x++){
				sb.append(giveCharForPrintMap(x, false, y, true));
				if(compact || IX==x){
					continue;//
				}
				sb.append(giveCharForPrintMap(x, true, y, true));
			}
			System.out.println(sb);
		}
	}

	boolean isOutOfBorder(final int q, final boolean yy){
		return I0>q || (yy?q>=MY:q>=MX);
	}
	boolean isOutOfBorder(final int x, final int y){
		return I0>x || x>=MX || I0>y || y>=MX;
	}
	boolean isWall(final boolean yy, final int step){
		return m[yy?xC:xC+step][yy?yC+step:yC][yy?DY:DX];
	}
	void deegWall(final boolean yy, final int step){
		m[yy?xC:xC+step][yy?yC+step:yC][yy?DY:DX] = false;
	}
	/*void isHasBeen(final boolean yy, final int step){
		m[yy?xC:xC+step][yy?yC+step:yC][yy?DY:DX] = false;
	}*/

	boolean isLefWall(final int x, final int y){
		return m[x][y][DX];
	}
	boolean isRitWall(final int x, final int y){
		return m[x+1][y][DX];
	}
	boolean isTopWall(final int x, final int y){
		return m[x][y][DY];
	}
	boolean isBotWall(final int x, final int y){
		return m[x][y+1][DY];
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
		if(xx && yy){
			if(xC==x && yC==y) return 'C';
			if(hasBeen[x][y]) return 'b';
			return m[x][y][DX]?m[x][y][DY]?'+':'-':m[x][y][DY]?'|':'∙'; //◌ free cell space
		}
		if(entrX==x && entrY==y) return 'e';
		final boolean l = I0==x ? xx && m[x][y][DX] : !yy && m[x-(xx?0:1)][y][DX];
		final boolean r = IX!=x                    && !yy && m[x         ][y][DX];
		final boolean t = I0==y ? yy && m[x][y][DY] : !xx && m[x][y-(yy?0:1)][DY];
		final boolean b = IY!=y                    && !xx && m[x]         [y][DY];
		return l ? r ? t ? b ? '┼' : '┴' : b ? '┬' : '─'
		             : t ? b ? '┤' : '┘' : b ? '┐' : '→'
		         : r ? t ? b ? '├' : '└' : b ? '┌' : '←'
		             : t ? b ? '│' : '!' : b ? '¡' : ' '//∙↑↓
				;
	}
	char giveCharForPrintMap_(final int x, final boolean xx, final int y, final boolean yy){
		if(xx && yy) return m[x][y][DX]?m[x][y][DY]?'+':'-':m[x][y][DY]?'|':'∙'; //◌ free cell space

		final boolean l = I0!=x && !yy && m[x-(xx?0:1)][y][DX];
		final boolean r = IX!=x && !yy && m[x         ][y][DX];
		final boolean t = I0==y ? yy?m[x][y][DY]:false : !xx && m[x][y-(yy?0:1)][DY];
		final boolean b = IY!=y && !xx && m[x][y         ][DY];
		return l ? r ? t ? b ? '┼' : '┴' : b ? '┬' : '─'
		             : t ? b ? '┤' : '┘' : b ? '┐' : '→'
		         : r ? t ? b ? '├' : '└' : b ? '┌' : '←'
		             : t ? b ? '│' : '!' : b ? '¡' : ' '//∙↑↓
				;
	}
}
