package boggle;

import java.io.IOException;

public class TranslateBrailleFeature extends Feature{

    public Toggle toggle;

    public TranslateBrailleFeature() throws IOException {
        this.toggle = new Toggle();
    }

    @Override
    public String execute(String letters) throws BrailleLetterException {
        return this.toggle.translate_braille(letters);
    }
}
