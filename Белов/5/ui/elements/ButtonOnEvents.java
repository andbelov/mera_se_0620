package ui.elements;

public abstract class ButtonOnEvents implements OnEvents{
    protected abstract void onClick(Button button);
    @Override
    public void onClick(Element element){
        if(element instanceof Button){
            onClick((Button) element);
        }
    }
    @Override
    public void onMove(Element element){}
    @Override
    public void onResize(Element element){}
}