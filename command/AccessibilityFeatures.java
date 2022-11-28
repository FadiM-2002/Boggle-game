package command;

public class AccessibilityFeatures {

    private Feature enlargeFontFeature;

    private Feature highContrastFeature;

    public AccessibilityFeatures() {
        this.enlargeFontFeature = new EnlargeFontFeature();
        this.highContrastFeature = new HighContrastFeature();
    }

    public void enlarge_font(){
        // updates the GUI
    }

    public void high_contrast(){
        // updates the GUI
    }

}
