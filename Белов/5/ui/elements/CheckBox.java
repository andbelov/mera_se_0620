package ui.elements;

public class CheckBox extends Element implements Clickable {
    private Boolean checked;

    public static Boolean DEFAULT_CHECHED;

    static {
        CheckBox.DEFAULT_CHECHED = false;
    }

    public CheckBox() {
        this(DEFAULT_CHECHED);
    }

    public CheckBox(Boolean checked) {
        this.checked = checked;
    }

    public Boolean getChecked() {
        return this.checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    @Override
    public void click() throws Element.ReadyOnlyException{
        if (!getEnable()) {
            throw new Element.ReadyOnlyException();
        }

        setChecked(!getChecked());
    }
}
