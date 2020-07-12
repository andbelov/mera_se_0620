package ui.elements.exceptions;

import ui.elements.Element;

abstract class ElementException extends RuntimeException{
    private final Element element;
    ElementException(final Element el){
        this.element = el;
    }
    public Element getElement(){
        return element;
    }
    @Override
    public abstract String getMessage();
}

