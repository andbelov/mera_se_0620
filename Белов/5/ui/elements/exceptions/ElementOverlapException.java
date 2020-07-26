package ui.elements.exceptions;

import ui.elements.Element;

public class ElementOverlapException extends ElementException{
    private final Element newElement;
    public ElementOverlapException(final Element el
            , final Element newElement){
        super(el);
        this.newElement = newElement;
    }
    @Override
    public String getMessage() {
        return getClass().getSimpleName()
                + ". New element: " + newElement.giveInfo()
                + ". MAY NOT CROSS existing: " + getElement().giveInfo();
    }
}
