package ui.elements;

public abstract class CheckboxOnEvents implements OnEvents{
    protected abstract void onClick(_Checkbox checkbox);
    @Override
    public void onClick(Element element){
        if(element instanceof _Checkbox){
            onClick((_Checkbox) element);
        }
    }
    @Override
    public void onMove(Element element){}
    @Override
    public void onResize(Element element){}
}