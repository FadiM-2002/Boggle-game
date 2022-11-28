package command;

public class Toggle {

    public boolean enlargeOn;
    public boolean contrastOn;

    public Toggle(){
        this.enlargeOn = false;
        this.contrastOn = false;
    }

    public void enlarge_font(){
        this.enlargeOn = !this.enlargeOn;
    }

    public void high_contrast(){
        this.contrastOn = !this.contrastOn;
    }
}
