package students;
import pinkFloyd.TheWall;

import java.util.Arrays;

public abstract class Student implements Runnable{
    protected int id;
    protected final String name;
    protected final TheWall theWall;
    protected int[] curPos;
    protected int steps;
    protected long time;

    protected Student(final int id, final String name, final TheWall theWall){
        this.id = id;
        this.name = name;
        this.theWall = theWall;
    }
    public int getId(){return id;}
    public String getName(){return name;}
    public int getSteps(){return steps;}
    public long getTime(){return time;}
    @Override
    public void run() {
        final long startTime = System.currentTimeMillis();
        curPos = theWall.giveEntrance();
        //System.out.println("Entr " + curPos[0] + " " + curPos[1]);
        while(!isExitFound()){
            calcMove();
            //System.out.println("calc " + p[0] + " " + p[1]);
            theWall.updStudentPos(id, curPos);
            steps++;
        }
        time = System.currentTimeMillis() - startTime;
        //System.out.println("time:"+time);
    }
    protected boolean isExitFound(){
        //System.out.println("--------");
        //if("Imqfm".equals(getName())){
            //System.out.println(getName() + " curr " + curPos[0] + " " + curPos[1]);
        //}
        //System.out.println("exit " + pinkFloyd.giveExit()[0] + " " + pinkFloyd.giveExit()[1]);
        return Arrays.equals(curPos, theWall.giveExit());
    }
    abstract protected void calcMove();
}