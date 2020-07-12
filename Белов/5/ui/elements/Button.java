package ui.elements;

public class Button extends Element implements Clickable{
    private final ButtonOnEvents events;
    public Button(ButtonOnEvents events){
        this.events = events;
    }
    public ButtonOnEvents getEvents(){
        return events;
    }
    @Override
    public void click(){
        checkReadOnlyState();
        events.onClick(this);
    }
}
