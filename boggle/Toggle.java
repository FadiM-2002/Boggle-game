package boggle;

import java.io.*;
import java.util.*;

import static java.lang.Character.toLowerCase;

public class Toggle {

    public boolean brailleOn;

    /**
     * Map to translate characters to Braille
     */
    Map<Character, List<String>> map;

    public Toggle() throws IOException {
        this.brailleOn = false;
        this.map = new HashMap<>();
        initializeMap();
    }

    public void setToggleState(){
        this.brailleOn = !this.brailleOn;
    }

    public boolean getToggleState(){
        return this.brailleOn;
    }

    public String translate_braille(String letters) throws BrailleLetterException {
        ArrayList<Integer> start_index = new ArrayList<>();
        ArrayList<Integer> end_index = new ArrayList<>();
        ArrayList<String> substrings_letters = new ArrayList<String>();
        ArrayList<ArrayList<String>> sub_translations = new ArrayList<ArrayList<String>>();
        String retval = "";
        double max_size = Math.sqrt(letters.length());

        for (int i = 1; i <= max_size; i++){
                start_index.add((int) ((i - 1) * max_size));
                end_index.add((int) (i * max_size));
        }
        for (int j = 0; j < max_size; j++){
            substrings_letters.add(letters.substring(start_index.get(j), end_index.get(j)));
        }
        for (String sub : substrings_letters) {
            sub_translations.add(translateLine(sub));
        }
        for (List<String> l: sub_translations) {
            retval += l.get(0) + "\n" + l.get(1) + "\n" + l.get(2); //three rows for every one line
            retval += "\n" + "\n";
        }
        return retval;
    }


    /**
     * Initialize a map to turn ASCII characters into Braille letters.
     * Read in the file "dictionary.txt" and use it to initialize
     * the map attribute.  This will be used to translate each ASCII
     * character to a list of strings (e.g. a -> {"10", "00", "00"})
     *
     * @throws IOException if file cannot be accessed
     */
    private void initializeMap() throws IOException {
        String filename = "brailledictionary.txt";
        File myObj = new File(filename);
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            String[] letters = data.split(", ", 2);
            Character let = letters[0].charAt(1);
            List<String> num = List.of(letters[1].split(", "));
            this.map.put(let, num);
        }
        myReader.close();
    }

    /**
     * Translate an ASCII line to a line of Braille.
     * To translate a line, iterate over the line and translate each character.
     * Then, concatenate translated characters to form lines of Braille as List<String> objects.
     * (e.g. The string "ab" should result in -> {"1010", "0010", "0000"}.
     * This corresponds to character a ->  {"10", "00", "00"} concatenated with b ->  {"10", "10", "00"}).
     * Store each line in this.translation (which is a List of List<String> objects).
     *
     * @param input: the ASCII line of text to be translated.
     */
    public ArrayList<String> translateLine(String input) throws BrailleLetterException {
        try {
            List<List<String>> tralets = new ArrayList<List<String>>();
            ArrayList<String> last = new ArrayList<String>();
            char[] letts = input.toCharArray();
            for (int i = 0; i < letts.length; i++) {
                List<String> c = translateChar(letts[i]);
                List<String> strings = List.of(new String[]{"e"});
                if (c.equals(strings)){
                    throw new BrailleLetterException("Invalid characters should make BrailleTranslator.translateLine() to raise BrailleLetterException.");
                }
                tralets.add(c);
            }
            for (int i = 0; i <= 2; i++) {
                String conc = "";
                for (int j = 0; j < tralets.size(); j++) {
                    conc = conc + tralets.get(j).get(i);
                }
                last.add(conc);
            }
            return last;
        } catch (BrailleLetterException e) {
            throw new BrailleLetterException("Invalid characters should make BrailleTranslator.translateLine() to raise BrailleLetterException.");
        }
    }


    /**
     * Translate an ASCII character to a single Braille letter with positions as follows
     *      0 3
     *      1 4
     *      2 5
     *
     * Each position should contain a zero or a one.
     *
     * @param c: the ASCII character to be translated.
     * @return a Braille character translation
     */
    public List<String> translateChar(char c) {
        Character let = toLowerCase(c);
        if (this.map.containsKey(let)){
            return this.map.get(let);
        }
        List<String> strings = List.of(new String[]{"e"});
        return strings;
    }
}
