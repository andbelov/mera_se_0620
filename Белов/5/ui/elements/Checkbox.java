package ui.elements;

public class Checkbox extends Element implements Clickable {
    private boolean checked;
    public Checkbox(final boolean checked){
        this.checked = checked;
    }
    public boolean isChecked(){
        return checked;
    }
    public void setChecked(final Boolean checked){
        checkReadOnlyState();
        this.checked = checked;
    }
    public void click(){
        checkReadOnlyState();
        checked = !checked;
    }
    @Override
    public String giveInfo(){
        return super.giveInfo() + ", isChecked: \"" + isChecked() + '\"';
    }
}
