import java.util.Arrays;
import java.util.Collections;

import static util.Util10.*;

@SuppressWarnings("SpellCheckingInspection")
class PinkFloyd{
	private final int DX = 0;
	private final int DY = 1;
	private final int HB = 2;
	private final int MX = 10; //giveRandomInBound(2, rx);
	private final int MY = 5; //giveRandomInBound(2, ry);
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

	private enum DIR{
		N(1, 0, -1),
		S(2, 0, 1),
		E(4, 1, 0),
		W(8, -1, 0);
		private final int bit;
		private final int dx;
		private final int dy;
		private DIR opposite;
		// use the static initializer to resolve forward references
		static{
			N.opposite = S;
			S.opposite = N;
			E.opposite = W;
			W.opposite = E;
		}
		private DIR(int bit, int dx, int dy){
			this.bit = bit;
			this.dx = dx;
			this.dy = dy;
		}
	}
	public void display(){
		for(int i = 0; i < MY; i++){
			// draw the north edge
			for(int j = 0; j < MX; j++){
				System.out.print((maze[j][i] & 1) == 0 ? "+---" : "+   ");
			}
			System.out.println("+");
			// draw the west edge
			for(int j = 0; j < MX; j++){
				System.out.print((maze[j][i] & 8) == 0 ? "|   " : "    ");
			}
			System.out.println("|");
		}
		// draw the bottom line
		for(int j = 0; j < MX; j++){
			System.out.print("+---");
		}
		System.out.println("+");
	}
	private void generateMaze(int cx, int cy){
		DIR[] dirs = DIR.values();
		Collections.shuffle(Arrays.asList(dirs));
		for(DIR dir : dirs){
			int nx = cx + dir.dx;
			int ny = cy + dir.dy;
			if(between(nx, MX) && between(ny, MY)
					&& (maze[nx][ny] == 0)){
				maze[cx][cy] |= dir.bit;
				maze[nx][ny] |= dir.opposite.bit;
				generateMaze(nx, ny);
			}
		}
	}
	private boolean between(int v, int upper){
		return (v >= 0) && (v < upper);
	}

	PinkFloyd(){
		//generateMaze(0, 0);
		//display();
		//if cell in maze true then it's a wall;
		initWalls();
		//testWalls();
		//randomWalls();	printWalls(0);
		final boolean isEntrOnWallX = giveRandom();
		final boolean isEntrWallOnZero = giveRandom();
		entrX = isEntrOnWallX ? giveRandom(IX) : isEntrWallOnZero ? I0 : IX;
		entrY = isEntrOnWallX ? isEntrWallOnZero ? I0 : IY : giveRandom(MY);
		exitX = isEntrOnWallX ? giveRandom(IX) : !isEntrWallOnZero ? I0 : IX;
		exitY = isEntrOnWallX ? !isEntrWallOnZero ? I0 : IY : giveRandom(IY);
		m[entrX][entrY][isEntrOnWallX ? DX : DY] = false;
		m[exitX][exitY][isEntrOnWallX ? DX : DY] = false;
		//System.out.println("Entrance:["+entrX + "][" + entrY + "], Exit:[" + exitX + "][" + exitY+"]");
		genPath();
	}
	private void initWalls(){
		for(int x = I0; x < MX; x++){
			for(int y = I0; y < MY; y++){
				//final boolean b = I0 == x || I0 == y || x == IX || y == IY;
				m[x][y][DX] = m[x][y][DY] = true;
			}
		}
	}
	private void testWalls(){
		for(int x = I0; x < MX; x++){
			for(int y = I0; y < MY; y++){
				final boolean q = (x > 2 && x < MX - 3) ^ (y > 3 && y < 8);
				m[x][y][DX] = q;//x%2==I0 || y%2==I0;
				m[x][y][DY] = q;//x%2==I0 || y%2==I0;
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
		xC = entrX;
		yC = entrY;
		m[xC][yC][HB] = true;
		int xO = xC;
		int yO = yC;
		int count = 0;
		while(3 > count++){
			//if(count>10 && count <20)
			printWalls(count);
			//System.out.println("===============");
			System.out.println("BEF:" + m[xC][yC][DX] +"/"+ m[xC][yC][DY]  + "["+xC + "][" + yC + "]");
			final boolean dyRand = giveRandom();
			final boolean stepRand = giveRandom();
			boolean isMoved = false;
			BreakLabel:
			for(int i = 2; --i >= 0; ){
				final boolean dy = (0 == i) ^ dyRand;
				for(int j = 2; --j >= 0; ){
					final int step = ((0 == j) ^ stepRand) ? 1 : -1;
					final int xN = xC + (!dy ? step : 0);
					final int yN = yC + ( dy ? step : 0);
					//System.out.println("yy:"+yy + " " + "step:" + step);
					if(isOutOfBorder(xN, yN)){
						continue;
					}
					if(!m[xN][yN][HB]){
						System.out.println("UPD:" + m[xC][yC][DX] +"/"+ m[xC][yC][DY]  + "["+xC + "][" + yC + "]");
						digWall(dy, step);
						System.out.println("UPD:" + m[xC][yC][DX] +"/"+ m[xC][yC][DY]  + "["+xC + "][" + yC + "]");
						m[xC][yC][HB] = true;
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
		sb.setLength(0);
		sb.append(' ');
		for(int x = I0; x < MX; x++){
			if(x<10){ sb.append(' ');}
			sb.append(x);
		}
		sb.append('>').append(count);
		System.out.println(sb);
		final boolean compact = false;
		for(int y = I0; y < MY; y++){
			sb.setLength(0);
			sb.append(' ');
			for(int x = I0; x < MX; x++){
				sb.append(giveCharForPrintMap(x, false, y, false));
				if(compact || IX == x){
					continue;//
				}
				sb.append(giveCharForPrintMap(x, true, y, false));
			}
			System.out.println(sb);
			if(compact || IY == y){
				continue;//
			}

			sb.setLength(0);
			sb.append(y);
			for(int x = I0; x < MX; x++){
				sb.append(giveCharForPrintMap(x, false, y, true));
				if(compact || IX == x){
					continue;//
				}
				sb.append(giveCharForPrintMap(x, true, y, true));
			}
			System.out.println(sb);
		}
	}

	boolean isOutOfBorder(final int q, final boolean yy){
		return I0 > q || (yy ? q >= MY : q >= MX);
	}
	boolean isOutOfBorder(final int x, final int y){
		return I0 > x || x >= MX || I0 > y || y >= MY;
	}
	boolean isWall(final boolean dy, final int step){
		return m[dy ? xC : (xC + step)][dy ? (yC + step) : yC][dy ? DY : DX];
	}
	void digWall(final boolean dy, final int step){
		if(!isWall(dy, step)) throw new AssertionError("xC:"+xC+"yC:"+yC+"!isWall("+dy+","+step+")");
		m[dy ? xC : (xC + step)][dy ? (yC + step) : yC][dy ? DY : DX] = false;
	}
	/*void isHasBeen(final boolean yy, final int step){
		m[yy?xC:xC+step][yy?yC+step:yC][yy?DY:DX] = false;
	}*/

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
		if(xx && yy){
			if(xC == x && yC == y) return 'C';
			//if(m[x][y][HB]) return 'b';
			final boolean y1 = m[x][y][DY];
			return m[x][y][DX] ? (y1?'+':'[') : (y1? 'ˉ' :'∙'); //◌ free cell space
		}
		//if(entrX==x && entrY==y) return 'e';
		final boolean l = I0 == x ? xx && m[x][y][DX] : !yy && m[x - (xx ? 0 : 1)][y][DX];
		final boolean r = IX != x && !yy && m[x][y][DX];
		final boolean t = I0 == y ? yy && m[x][y][DY] : !xx && m[x][y - (yy ? 0 : 1)][DY];
		final boolean b = IY != y && !xx && m[x][y][DY];
		return l ? r ? t ? b ? '┼' : '┴' : b ? '┬' : '─'
		             : t ? b ? '┤' : '┘' : b ? '┐' : '→'
		         : r ? t ? b ? '├' : '└' : b ? '┌' : '←'
		             : t ? b ? '│' : 'ꜝ' : b ? '¡' : ' '//∙↑↓
				;
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
