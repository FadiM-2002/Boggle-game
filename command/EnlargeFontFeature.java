package command;

public class EnlargeFontFeature implements Feature{

    public Toggle toggle;

    public EnlargeFontFeature(){
        this.toggle = new Toggle();
    }

    @Override
    public void execute() {
        toggle.enlarge_font();
    }
}
