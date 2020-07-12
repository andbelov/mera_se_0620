package ui.elements.placement;

public class Border{
    Coord pos;
    Coord size;
    public Border(){
        pos  = new Coord();
        size = new Coord();
    }
    public Border(Coord size){
        this();
        setSize(size);
    }
    public Coord getPos(){
        return pos;
    }
    public void setPos(final Coord pos){
        this.pos = pos;
    }
    public Coord getSize(){
        return size;
    }
    public void setSize(final Coord size){
        assert(0<size.x && 0<size.y);
        this.size = size;
    }
    public Coord getPosPlusSize(){
        return Coord.sum(pos, size);
    }
    /*public boolean isInsideOf(final Border border){
        return pos.isGreaterThan(border.pos)
            && !(pos.+size).isGreaterThan(border.size);
    }*/
    public boolean isCrossing(final Border border){
        return pos.isInsideOf(border)
            || pos.sum(size).isInsideOf(border);
    }
    /*public int getX(){
        return topLeft.x;
    }
    public int getY(){
        return topLeft.y;
    }
    public int getWidth(){
        return botRght.x - topLeft.x;
    }
    public int getHeight(){
        return botRght.y - topLeft.y;
    }*/
    @Override
    public String toString(){
        return "Rectangle{" +
                "topLeft=" + pos +
                ", bottRht=" + size +
                '}';
    }
}

