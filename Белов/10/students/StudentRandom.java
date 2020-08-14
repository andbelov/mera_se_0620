package students;

import pinkFloyd.TheWall;
import pinkFloyd.TheWall.Dir;

import static util.Util10.giveRandom;

public class StudentRandom extends Student implements Runnable {
    private final int DX = 0;
    private final int DY = 1;
    public StudentRandom(final int id, final String name, final TheWall theWall) {
        super(id, name, theWall);
    }
    @Override
    public void calcMove(){
        while(true){
            final int dir = giveRandom(Dir.values().length);
            //System.out.println("dir " + dir);
            //System.out.println("Dir " + Dir.values()[dir]);
            if(theWall.isWall(curPos[DX], curPos[DY], dir)){ // Dir.values()[dir]
                //System.out.println("isOutOfWall");
                continue;
            }
            final int xN = curPos[DX] + (dir>=2?(dir-2)*2-1  :0);
            final int yN = curPos[DY] + (dir< 2?(dir   *2-1) :0);
            //System.out.println("new " + " ["+xN + "][" + yN + "]");
            if(theWall.isOutOfMove(xN, yN)){
                //System.out.println("isOutOfMove");
                continue;
            }
            curPos[DX] = xN;
            curPos[DY] = yN;
            break;
        }
    }
}
