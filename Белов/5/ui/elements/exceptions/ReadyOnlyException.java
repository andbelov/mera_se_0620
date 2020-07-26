package ui.elements.exceptions;

import ui.elements.Element;

public class ReadyOnlyException extends ElementException{
    public ReadyOnlyException(final Element el){
        super(el);
    }
    @Override
    public String getMessage(){
        return getClass().getSimpleName()
            + ": Element " + getElement().giveInfo() + ". READ ONLY state!";
    }
}
