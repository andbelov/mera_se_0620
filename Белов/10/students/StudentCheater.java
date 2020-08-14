package students;

import pinkFloyd.TheWall;

import java.rmi.AccessException;
import java.util.Queue;

public class StudentCheater extends Student implements Runnable {
    private final Queue<int[]> path;
    public StudentCheater(final int id, final String name, final TheWall theWall) throws AccessException{
        super(id, name, theWall);
        path = theWall.givePath("PASSWORD");
        if(null==this) throw new AccessException("I don't know the password");
    }
    @Override
    protected void calcMove(){
        curPos = path.poll();
    }
}
