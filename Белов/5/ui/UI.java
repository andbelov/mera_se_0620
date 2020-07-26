package ui;

import ui.elements.Element;
import ui.elements.exceptions.ElementOverlapException;
import ui.elements.placement.*;

import java.util.LinkedList;

public class UI{
    private static final Border BORDER = new Border();
    static final LinkedList<Element> ELEMENTS = new LinkedList<>();
    public static Coord getScreenSize(){
        return BORDER.getSize();
    }
    public static void setScreenSize(Coord size){
        assert(null!= size);
        BORDER.setSize(size);
    }
    public static LinkedList<Element> getAllElements(){
        return ELEMENTS;
    }
    public static void addElement(final Element elementToAdd){
        assert(null!=elementToAdd);
        ELEMENTS.add(elementToAdd);
    }
    public static void checkCrossing(final Element elementToAdd){
        for(Element el : ELEMENTS){
            //[7/8 7:44 PM] rostislav.kolesnikov@mera.com
            //Элементы будут считаться пересекающимися при совпадении только координат X, Y, или еще надо учитывать
            // высоту/ширину?
            //Думаю, что да, я учел и пересечение и поглощение одного элемента другим
            if(el.getBorder().isCrossing(elementToAdd.getBorder())){
                throw new ElementOverlapException(el, elementToAdd);
            }
        }
    }
}
