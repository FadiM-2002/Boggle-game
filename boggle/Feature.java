package boggle;

import java.io.IOException;

public abstract class Feature {

    public Toggle toggle;

    public Feature() throws IOException {
        this.toggle = new Toggle();
    }
    public abstract String execute(String letters) throws BrailleLetterException;
}
