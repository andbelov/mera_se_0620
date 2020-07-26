package ui.elements.placement;

public class Coord{
    protected int x;
    protected int y;
    public Coord(){}
    public Coord(final int horCoordinate
               , final int verCoordinate){
        x = horCoordinate;
        y = verCoordinate;
    }
    public Coord(final String horCoordinate
               , final String verCoordinate){
        x = Integer.parseInt(horCoordinate);
        y = Integer.parseInt(verCoordinate);
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public boolean isGreaterThan(final Coord coord){
        return x > coord.x && y > coord.y;
    }
    public boolean isLessThan(final Coord coord){
        return x < coord.x && y < coord.y;
    }
    public static Coord giveRandom(final Coord coord){
        return new Coord(util.Util5.giveRandom(coord.x)
        , util.Util5.giveRandom(coord.y));
    }
    public static Coord sum(final Coord coord1, final Coord coord2){
        return new Coord(coord1.x+coord2.x, coord1.y+coord2.y);
    }
    public static Coord sub(final Coord coord1, final Coord coord2){
        return new Coord(coord1.x-coord2.x, coord1.y-coord2.y);
    }
    public Coord sum(final Coord coord){
        return sum(this, coord);
    }
    public Coord sub(final Coord coord){
        return sub(this, coord);
    }
    public boolean isInsideOf(final Border border){
        final Coord pos = border.getPos();
        final Coord posPlusSize = border.getPosPlusSize();
        return x >= pos.getX()
            && x <= posPlusSize.getX()
            && y >= pos.getY()
            && y <= posPlusSize.getY();
    }
    @Override
    public String toString(){
        return "Coord{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
