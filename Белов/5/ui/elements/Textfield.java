package ui.elements;

public class Textfield extends Element {
    private String text;
    public Textfield(){}
    public Textfield(final String text){
        this.text = text;
    }
    public void setText(String text){
        checkReadOnlyState();
        this.text = text;
    }
    public String getText(){
        if(null==text){
            return "";
        }
        return text;
    }
    @Override
    public String giveInfo(){
        return super.giveInfo() + ", Text: \"" + text + '\"';
    }
}
