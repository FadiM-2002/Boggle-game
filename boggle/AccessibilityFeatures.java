package boggle;

import java.io.IOException;

public class AccessibilityFeatures {

    public Feature translateBrailleFeature;

    public AccessibilityFeatures() throws IOException {
        this.translateBrailleFeature = new TranslateBrailleFeature();
    }

    public String translate_to_braille(String letters) throws BrailleLetterException {
        return this.translateBrailleFeature.execute(letters);
    }

    public void setToggleState(){
        this.translateBrailleFeature.toggle.setToggleState();
    }

    public boolean getToggleState(){
        return this.translateBrailleFeature.toggle.getToggleState();
    }
}
