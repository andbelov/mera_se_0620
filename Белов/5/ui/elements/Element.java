package ui.elements;

import ui.elements.exceptions.ReadyOnlyException;
import ui.elements.placement.*;

abstract public class Element {
    private Border border = new Border();
    private String caption;
    private boolean isReadOnly;
    public Element(){}
    public Border getBorder(){
        return border;
    }
    public void setBorder(final Border border){
        checkReadOnlyState();
        this.border = border;
    }
    public Coord getPosition(){
        return border.getPos();
    }
    public void setPosition(final Coord position){
        checkReadOnlyState();
        border.setPos(position);
    }
    public Coord getSize(){
        return border.getSize();
    }
    public void setSize(final Coord size){
        checkReadOnlyState();
        border.setSize(size);
    }
    public String getCaption(){
        if(null==caption){
            return "NO NAME";
        }
        return caption;
    }
    public void setCaption(String caption){
        this.caption = caption;
    }
    public boolean isReadOnly(){
        return isReadOnly;
    }
    public void setReadOnly(boolean state){
        this.isReadOnly = state;
    }
    void checkReadOnlyState() throws ReadyOnlyException{
        if (isReadOnly){
            throw new ReadyOnlyException(this);
        }
    }
    public String giveInfo(){
        return "\"" + getClass().getSimpleName() + '\"'
            +	", pos <" + getPosition().getX()
            +   ',' + getPosition().getY() + '>'
            +   ", size <" + getSize().getX()
            +	',' + getSize().getY() + '>'
            +	", caption: " + '\"' + getCaption() + '\"'
            +   ", read only: " + '\"' + isReadOnly + '\"'
            ;
    }
    @Override
    public String toString(){
        return "Element{" +
                "rectangle=" + border +
                ", caption='" + caption + '\'' +
                ", isReadOnly=" + isReadOnly +
                '}';
    }
}
interface OnEvents{
    void onClick(Element element);
    void onMove(Element element);
    void onResize(Element element);
}
