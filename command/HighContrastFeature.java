package command;

public class HighContrastFeature implements Feature {

    public Toggle toggle;

    public HighContrastFeature(){
        this.toggle = new Toggle();
    }

    @Override
    public void execute() {
        toggle.high_contrast();
    }
}
