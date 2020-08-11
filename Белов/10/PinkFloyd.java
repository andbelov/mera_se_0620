import static util.Util10.giveRandom;

@SuppressWarnings("SpellCheckingInspection")
public class PinkFloyd{
	private final int DX = 0;
	private final int DY = DX+1;
	private final int MX = 11; //giveRandomInBound(2, rx);
	private final int MY = 11; //giveRandomInBound(2, ry);
	private final int I0 = 0;
	private final int IX = MX-1;
	private final int IY = MY-1;
	private final boolean[][][] m = new boolean[MX][MX][DY+1];
	private final int entrX;
	private final int entrY;
	private final int exitX;
	private final int exitY;
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
		//m[entr[X]][entr[Y]] = false;
		//m[exit[X]][exit[Y]] = false;
		System.out.println("Entrance:["+entrX + "][" + entrY + "], Exit:[" + exitX + "][" + exitY+"]");
		genPath();
		printWalls();
	}
	private void initWalls(){
		for(int x = I0; x < MX; x++){
			for(int y = I0; y < MY; y++){
				final boolean b = I0 == x || I0 == y || x == IX || y == IY;
				m[x][y][0] = m[x][y][1] = b;
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
		int x=entrX;
		int y=entrY;
		//while(true){
		System.out.println(":["+x + "][" + y + "]");
			x += -1 + giveRandom(2) << 1;
			y += -1 + giveRandom(2) << 1;
		System.out.println(":["+x + "][" + y + "]");
		//}
	}

	public void printWalls(){
		for(int y = I0; y < MY; y++){
			sb.setLength(0);
			/*for(int x = I0; x < MX; x++){
				sb.append(' ').append(m[x][y]?'■':'-');
			}
			sb.append('|');*/
			for(int x = I0; x < MX; x++){
				sb.append(giveCharForPrintMap(x, false, y, false));
				if(IX==x){
					//continue;//
				}
				sb.append(giveCharForPrintMap(x, true, y, false));
			}
			System.out.println(sb);
			if(IY==y){
				//continue;//
			}
			sb.setLength(0);
			for(int x = I0; x < MX; x++){
				sb.append(giveCharForPrintMap(x, false, y, true));
				if(IX==x){
					//continue;//
				}
				sb.append(giveCharForPrintMap(x, true, y, true));
			}
			System.out.println(sb);
		}
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
		if(xx && yy) return m[x][y][DX]?m[x][y][DY]?'+':'-':m[x][y][DY]?'|':'∙'; //◌ free cell space
		final boolean l = I0==x ? xx? m[x][y][DX]:false :!yy && m[x-(xx?0:1)][y][DX];
		final boolean r = IX!=x && !yy && m[x         ][y][DX];
		final boolean t = I0==y ? yy?m[x][y][DY]:false : !xx && m[x][y-(yy?0:1)][DY];
		final boolean b = IY!=y && !xx && m[x][y         ][DY];
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
		final boolean t =  I0==y ? yy?m[x][y][DY]:false : !xx && m[x][y-(yy?0:1)][DY];
		final boolean b = IY!=y && !xx && m[x][y         ][DY];
		return l ? r ? t ? b ? '┼' : '┴' : b ? '┬' : '─'
		             : t ? b ? '┤' : '┘' : b ? '┐' : '→'
		         : r ? t ? b ? '├' : '└' : b ? '┌' : '←'
		             : t ? b ? '│' : '!' : b ? '¡' : ' '//∙↑↓
				;
	}
}
