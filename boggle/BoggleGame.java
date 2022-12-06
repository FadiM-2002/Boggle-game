package boggle;

import java.util.*;

/**
 * The BoggleGame class for the first Assignment in CSC207, Fall 2022
 */
public class BoggleGame {

    /**
     * scanner used to interact with the user via console
     */ 
    public Scanner scanner;
    /**
     * stores game statistics
     */ 
    private BoggleStats gameStats;
    /**
     * facilitates opponent behavior during game rounds
     */
    private BoggleGameMode gameMode;
    /**
     * the time limit imposed on each human player for each round
     */
    private Integer timeLimit;

    /**
     * dice used to randomize letter assignments for a small grid
     */ 
    private final String[] dice_small_grid= //dice specifications, for small and large grids
            {"AAEEGN", "ABBJOO", "ACHOPS", "AFFKPS", "AOOTTW", "CIMOTU", "DEILRX", "DELRVY",
                    "DISTTY", "EEGHNW", "EEINSU", "EHRTVW", "EIOSST", "ELRTTY", "HIMNQU", "HLNNRZ"};
    /**
     * dice used to randomize letter assignments for a big grid
     */ 
    private final String[] dice_big_grid =
            {"AAAFRS", "AAEEEE", "AAFIRS", "ADENNN", "AEEEEM", "AEEGMU", "AEGMNN", "AFIRSY",
                    "BJKQXZ", "CCNSTW", "CEIILT", "CEILPT", "CEIPST", "DDLNOR", "DDHNOT", "DHHLOR",
                    "DHLNOR", "EIIITT", "EMOTTT", "ENSSSU", "FIPRSY", "GORRVW", "HIPRRY", "NOOTUW", "OOOTTU"};
    private Integer difficulty = 100;

    /**
     * registry used to store boards that the user chooses to save
     */
    private GridPrototypeRegistry gridRegistry = new GridPrototypeRegistry();

    /**
     * BoggleGame constructor
     */
    public BoggleGame() {
        this.scanner = new Scanner(System.in);
        this.gameStats = new BoggleStats();
    }

    /**
     * Provide instructions to the user, so they know how to play the game.
     */
    public void giveInstructions()
    {
        System.out.println("The Boggle board contains a grid of letters that are randomly placed.");
        System.out.println("If you are playing with me (the computer), we're both going to try to");
        System.out.println("find words in this grid by joining the letters.");
        System.out.println("You can form a word by connecting adjoining letters on the grid.");
        System.out.println("Two letters adjoin if they are next to each other horizontally, ");
        System.out.println("vertically, or diagonally. The words you find must be at least 4 letters long, ");
        System.out.println("and you can't use a letter twice in any single word. Your points ");
        System.out.println("will be based on word length: a 4-letter word is worth 1 point, 5-letter");
        System.out.println("words earn 2 points, and so on. After you find as many words as you can,");
        System.out.println("I will find all the remaining words.");
        System.out.println("The game will be very similar if you are playing with a friend, except that");
        System.out.println("your friend will be finding the remaining words.");
        System.out.println("\nHit return when you're ready...");
    }


    /**
     * Gets information from the user to initialize a new Boggle game.
     * It will loop until the user indicates they are done playing.
     */
    public void playGame(){
        int boardSize;
        while(true){
            System.out.println("Choose the difficulty (1-100). This will apply if you play the computer:");
            String choiceDifficulty = scanner.nextLine();
            while (!validDifficulty(choiceDifficulty)) {
                System.out.println("Please try again");
                System.out.println("Choose the difficulty (1-100). This will apply if you play the computer:");
                choiceDifficulty = scanner.nextLine();
            }
            difficulty = Integer.parseInt(choiceDifficulty);
            System.out.println("Enter 1 to play on a big (5x5) grid; 2 to play on a small (4x4) one:");
            String choiceGrid = scanner.nextLine();

            //get grid size preference
            if(choiceGrid == "") break; //end game if user inputs nothing
            while(!choiceGrid.equals("1") && !choiceGrid.equals("2")){
                System.out.println("Please try again.");
                System.out.println("Enter 1 to play on a big (5x5) grid; 2 to play on a small (4x4) one:");
                choiceGrid = scanner.nextLine();
            }

            if(choiceGrid.equals("1")) boardSize = 5;
            else boardSize = 4;

            //get game mode preference
            System.out.println("Enter 1 to play with a friend; 2 to play with the computer:");
            String choiceMode = scanner.nextLine();

            if (choiceMode == "") break;
            while(!choiceMode.equals("1") && !choiceMode.equals("2")){
                System.out.println("Please try again.");
                System.out.println("Enter 1 to play with a friend; 2 to play with the computer:");
                choiceMode = scanner.nextLine();
            }

            if (choiceMode.equals("1")) {
                setGameMode(new TwoPlayerMode());
            }
            else {
                setGameMode(new SinglePlayerMode());
            }

            //get timer preference
            System.out.println("Enter 1 to play with no time limit; 2 to play with a 30 second time limit; 3 to play");
            System.out.println("with a 60 second time limit:");
            String choiceTime = scanner.nextLine();

            if (choiceTime == "") break;
            while(!choiceTime.equals("1") && !choiceTime.equals("2") && !choiceTime.equals("3")) {
                System.out.println("Please try again.");
                System.out.println("Enter 1 to play with no time limit; 2 to play with a 30 second time limit; 3 to play");
                System.out.println("with a 60 second time limit:");
                choiceTime = scanner.nextLine();
            }

            if (choiceTime.equals("1")) {
                timeLimit = 0;
            }
            else if (choiceTime.equals("2")) {
                timeLimit = 30;
            }
            else {
                timeLimit = 60;
            }

            //checking if there are any saved grids or not (user story 2.5-ask-user-for-saving-board)
            String choiceLetters;
            boolean savedGridExist;
            if (gridRegistry.getSavedGridNum() > 0) {
                savedGridExist = true;
                System.out.println("There exist saved grids.");
                System.out.println("Enter 1 to randomly assign letters to the grid; 2 to provide your own; 3 to play with a saved grid.");
            }
            else {
                savedGridExist = false;
                System.out.println("There is no saved grid at the moment.");
                System.out.println("You can play by randomly assigning letters to the grid or by providing your own grid");
                System.out.println("Enter 1 to randomly assign letters to the grid; 2 to provide your own.");
            }

            //get letter choice preference
            choiceLetters = scanner.nextLine();

            if(choiceLetters == "") break; //end game if user inputs nothing

            if (savedGridExist) { //if user can use saved grids
                while(!choiceLetters.equals("1") && !choiceLetters.equals("2") && !choiceLetters.equals("3")){
                    System.out.println("Please try again.");
                    System.out.println("Enter 1 to randomly assign letters to the grid; 2 to provide your own; 3 to play with a saved grid.");
                    choiceLetters = scanner.nextLine();
                }
            }
            else { //if user cannot use saved grids (no saved grids)
                while(!choiceLetters.equals("1") && !choiceLetters.equals("2")){
                    System.out.println("Please try again.");
                    System.out.println("Enter 1 to randomly assign letters to the grid; 2 to provide your own.");
                    choiceLetters = scanner.nextLine();
                }
            }

            if(choiceLetters.equals("1")){
                playRound(boardSize,randomizeLetters(boardSize));
            } else if (choiceLetters.equals("2")){
                System.out.println("Input a list of " + boardSize*boardSize + " letters:");
                choiceLetters = scanner.nextLine();
                while(!(choiceLetters.length() == boardSize*boardSize)){
                    System.out.println("Sorry, bad input. Please try again.");
                    System.out.println("Input a list of " + boardSize*boardSize + " letters:");
                    choiceLetters = scanner.nextLine();
                }
                playRound(boardSize,choiceLetters.toUpperCase());
            }
            else { //if choiceLetters is 3
                this.gridRegistry.printAllSavedGrids();
                System.out.println("Enter the name of the saved grid to play with.");
                String savedGridName = scanner.nextLine();
                while (!this.gridRegistry.gridNameExists(savedGridName)) {
                    System.out.println("The grid \"" + savedGridName + "\" does not exist.");
                    System.out.println("Please try again by choosing a grid name from the list below.");
                    this.gridRegistry.printAllSavedGrids();
                    System.out.println("Enter the name of the saved grid to play with.");
                    savedGridName = scanner.nextLine();
                }
                BoggleGrid selectedSavedGrid = this.gridRegistry.getGridByName(savedGridName);
                int gridSize = selectedSavedGrid.numCols();
                StringBuilder selectedGridString = new StringBuilder();
                for (int row = 0; row < gridSize; row++) {
                    for (int col = 0; col < gridSize; col++) {
                        selectedGridString.append(selectedSavedGrid.getCharAt(row, col));
                    }
                }
                playRound(selectedSavedGrid.numCols(), selectedGridString.toString());
            }

            //round is over! So, store the statistics, and end the round.
            this.gameStats.summarizeRound();
            this.gameStats.endRound();

            //Shall we repeat?
            System.out.println("Play again? Type 'Y' or 'N'");
            String choiceRepeat = scanner.nextLine().toUpperCase();

            if(choiceRepeat == "") break; //end game if user inputs nothing
            while(!choiceRepeat.equals("Y") && !choiceRepeat.equals("N")){
                System.out.println("Please try again.");
                System.out.println("Play again? Type 'Y' or 'N'");
                choiceRepeat = scanner.nextLine().toUpperCase();
            }

            if(choiceRepeat == "" || choiceRepeat.equals("N")) break; //end game if user inputs nothing

        }

        //we are done with the game! So, summarize all the play that has transpired and exit.
        this.gameStats.summarizeGame();
        System.out.println("Thanks for playing!");
    }

    private boolean validDifficulty(String difficulty) {
        try {
            if (1 <= Integer.parseInt(difficulty) && Integer.parseInt(difficulty) <= 100) {
                return true;
            }
        }
        catch (NumberFormatException e) {
            return false;
        }
        return false;
    }

    /**
     * Play a round of Boggle.
     * This initializes the main objects: the board, the dictionary, the map of all
     * words on the board, and the set of words found by the user. These objects are
     * passed by reference from here to many other functions.
     */
    public void playRound(int size, String letters){
        //step 1. initialize the grid
        BoggleGrid grid = new BoggleGrid(size);
        grid.initalizeBoard(letters);
        //step 2. initialize the dictionary of legal words
        Dictionary boggleDict = new Dictionary("Boggle-game/wordlist.txt"); //you may have to change the path to the wordlist, depending on where you place it.
        //step 3. find all legal words on the board, given the dictionary and grid arrangement.
        Map<String, ArrayList<Position>> allWords = new HashMap<String, ArrayList<Position>>();
        findAllWords(allWords, boggleDict, grid);
        //step 4. allow the user to try to find some words on the grid
        humanMove(grid, allWords);
        //either allows a second user to try to find words or allows the computer to identify remaining words
        gameMode.opMove(grid, allWords, gameStats, difficulty, timeLimit);
    }

    /**
     * Setter for gameMode attribute
     */
    private void setGameMode(BoggleGameMode gameMode) {
        this.gameMode = gameMode;
    }

    /**
     * This method should return a String of letters (length 16 or 25 depending on the size of the grid).
     * There will be one letter per grid position, and they will be organized left to right,
     * top to bottom. A strategy to make this string of letters is as follows:
     * -- Assign a one of the dice to each grid position (i.e. dice_big_grid or dice_small_grid)
     * -- "Shuffle" the positions of the dice to randomize the grid positions they are assigned to
     * -- Randomly select one of the letters on the given die at each grid position to determine
     *    the letter at the given position
     *
     * @return String a String of random letters (length 16 or 25 depending on the size of the grid)
     */
    private String randomizeLetters(int size){
        String[] dice_grid;
        if (size == 4) dice_grid = dice_small_grid;
        else dice_grid = dice_big_grid;
        List<String> strList = Arrays.asList(dice_grid);
        Collections.shuffle(strList);
        strList.toArray(dice_grid);
        String str = "";
        for (String string: dice_grid){
            int randomNumber = (int) (Math.random() * 6);
            str += string.charAt(randomNumber);
        }
        return str;
    }

    /**
     * This should be a recursive function that finds all valid words on the boggle board.
     * Every word should be valid (i.e. in the boggleDict) and of length 4 or more.
     * Words that are found should be entered into the allWords HashMap.  This HashMap
     * will be consulted as we play the game.
     * Note that this function will be a recursive function.  You may want to write
     * a wrapper for your recursion. Note that every legal word on the Boggle grid will correspond to
     * a list of grid positions on the board, and that the Position class can be used to represent these
     * positions. The strategy you will likely want to use when you write your recursion is as follows:
     * -- At every Position on the grid:
     * ---- add the Position of that point to a list of stored positions
     * ---- if your list of stored positions is >= 4, add the corresponding word to the allWords Map
     * ---- recursively search for valid, adjacent grid Positions to add to your list of stored positions.
     * ---- Note that a valid Position to add to your list will be one that is either horizontal, diagonal, or
     *      vertically touching the current Position
     * ---- Note also that a valid Position to add to your list will be one that, in conjunction with those
     *      Positions that precede it, form a legal PREFIX to a word in the Dictionary (this is important!)
     * ---- Use the "isPrefix" method in the Dictionary class to help you out here!!
     * ---- Positions that already exist in your list of stored positions will also be invalid.
     * ---- You'll be finished when you have checked EVERY possible list of Positions on the board, to see
     *      if they can be used to form a valid word in the dictionary.
     * ---- Food for thought: If there are N Positions on the grid, how many possible lists of positions
     *      might we need to evaluate?
     *
     * @param allWords A mutable list of all legal words that can be found, given the boggleGrid grid letters
     * @param boggleDict A dictionary of legal words
     * @param boggleGrid A boggle grid, with a letter at each position on the grid
     */
    private void findAllWords(Map<String,ArrayList<Position>> allWords, Dictionary boggleDict, BoggleGrid boggleGrid) {
        GridIterator iterator = boggleGrid.getIterator(); // Get the iterator to iterate over the grid
        while (iterator.hasNext()) {
            HashMap<String, ArrayList<Position>> root = new HashMap<>();
            ArrayList<Position> positions = new ArrayList<>();
            Position position = iterator.next();
            positions.add(position);
            String str = Character.toString(boggleGrid.getCharAt(position.getRow(), position.getCol()));
            root.put(str, positions);
            WordSearchTree tree = new WordSearchTree(root);
            buildTree(tree, str, positions, boggleGrid, boggleDict);
            findAllWordsHelper(allWords, tree, boggleDict);
        }
    }

    /**
     * This method finds all valid words in a given tree, and adds those words to the map that contains
     * all valid words.
     * @param map Hashmap mapping all valid words to their list of positions
     * @param tree A tree containing all valid prefixes of all words
     * @param dict The dictionary
     */
    private void findAllWordsHelper(Map<String, ArrayList<Position>> map, WordSearchTree tree, Dictionary dict) {
        String key = (String) tree.getRoot().keySet().toArray()[0];
        if (key.length() > 3 && dict.containsWord(key)) {
            map.putAll(tree.getRoot());
        }
        for (WordSearchTree subtree: tree.getSubtrees()) {
            findAllWordsHelper(map, subtree, dict);
        }
    }

    /**
     * Given a tree with a root node, this method builds the tree by adding valid subtrees to the tree.
     * @param tree A WordSearchTree
     * @param string the key of the root of the tree
     * @param positions the list of positions that map to each letter in the key
     * @param grid a boggle grid
     * @param dict the dictionary
     */
    private void buildTree(WordSearchTree tree, String string,
                                     ArrayList<Position> positions,
                                     BoggleGrid grid, Dictionary dict) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int row = positions.get(positions.size() - 1).getRow() + i;
                int col = positions.get(positions.size() - 1).getCol() + j;
                if (0 <= row && row < grid.numRows() && 0 <= col && col < grid.numCols()) {
                    String prefix = string + grid.getCharAt(row, col);
                    Position newPos = new Position(row, col);
                    if (isValid(newPos, positions) && dict.isPrefix(prefix)) {
                        ArrayList<Position> newPositions = new ArrayList<>(positions);
                        newPositions.add(newPos);
                        HashMap<String, ArrayList<Position>> map = new HashMap<>();
                        map.put(prefix, newPositions);
                        WordSearchTree subtree = new WordSearchTree(map);
                        tree.getSubtrees().add(subtree);
                        buildTree(subtree, prefix, newPositions, grid, dict);
                    }
                }
            }
        }
    }
    /**
     * This method returns true if the position given is not found in the list of positions, and returns
     * false otherwise.
     * @param pos A position on the grid
     * @param list A list of positions on the grid
     * @return boolean returns true if the pos is not in list
     */
    private boolean isValid(Position pos, ArrayList<Position> list) {
        for (Position position: list) {
            if (pos.getRow() == position.getRow() && pos.getCol() == position.getCol()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Gets words from the user.  As words are input, check to see that they are valid.
     * If yes, add the word to the player's word list (in boggleStats) and increment
     * the player's score (in boggleStats).
     * End the turn once the user hits return (with no word).
     *
     * @param board The boggle board
     * @param allWords A mutable list of all legal words that can be found, given the boggleGrid grid letters
     */
    private void humanMove(BoggleGrid board, Map<String,ArrayList<Position>> allWords){
        if (gameMode.getClass() == SinglePlayerMode.class) {
            System.out.println("It's your turn to find some words!");
        }
        else {
            System.out.println("It's P1's turn to find some words!");
        }
        System.out.println(board);


        //asking the user if they want to save the current board later use, user story 2.5
        System.out.println("Do you want to save current board for later play? (yes/no)");
        String gridSaveUserChoice = scanner.nextLine();
        while (!gridSaveUserChoice.equalsIgnoreCase("yes") && !gridSaveUserChoice.equalsIgnoreCase("no")) {
            System.out.println("Invalid input. Please answer by \"yes\" or \"no\".");
            System.out.println("Do you want to save current board for later play? (yes/no)");
            gridSaveUserChoice = scanner.nextLine();
        }
        if (gridSaveUserChoice.equalsIgnoreCase("yes")) { //saves the board if the user wants to
            System.out.println("Create a name for the board to save:");
            String boardNameToSave = scanner.nextLine();
            while (boardNameToSave.trim().length() == 0) {
                System.out.println("Invalid input. A board name cannot consist of only whitespaces.");
                System.out.println("Create a name for the board to save:");
                boardNameToSave = scanner.nextLine();
            }
            while (this.gridRegistry.gridNameExists(boardNameToSave)) {
                System.out.println("A grid with the name \"" + boardNameToSave + "\" already exists.");
                System.out.println("Please enter a new name: ");
                boardNameToSave = scanner.nextLine();
            }
            this.gridRegistry.addGrid(boardNameToSave, board);
            System.out.println("Saved the board: " + boardNameToSave);
        }


        //asking the user if they want to rotate the grid (user story 2.4)
        boolean rotateGrid = true;
        BoggleGrid currentGrid = board;
        while (rotateGrid) {
            System.out.println("Do you want to rotate the grid? (yes/no)");
            String rotateGridChoice = scanner.nextLine();
            if (rotateGridChoice.equalsIgnoreCase("yes")) {
                currentGrid = currentGrid.rotateGrid();
                System.out.println(currentGrid);
            }
            else if (rotateGridChoice.equalsIgnoreCase("no")){
                rotateGrid = false;
                System.out.println("\nEnter the words you have found:");
            }
            else {
                System.out.println("Invalid input. Please answer by \"yes\" or \"no\".");
            }
        }

        //checks whether user has selected to use a time limit and either runs a game without the timer or with the timer
        boolean timed = (timeLimit != 0);
        if (!timed) {
            while(true) {
                String input = scanner.nextLine();
                if (input.equals("")) break;
                for (String word: allWords.keySet()) {
                    if (word.equalsIgnoreCase(input)) {
                        gameStats.addWord(input.toUpperCase(), BoggleStats.Player.Human);
                    }
                }
            }
        }
        else {
            BackgroundTimer timer = new BackgroundTimer(timeLimit);
            int timePlayed;
            timer.start();
            while(true) {
                String input = scanner.nextLine();
                if (!timer.isAlive()) {
                    timePlayed = timeLimit - timer.getTime();
                    break;
                }
                if (input.equals("")) {
                    timePlayed = timeLimit - timer.getTime();
                    break;
                }
                for (String word: allWords.keySet()) {
                    if (word.equalsIgnoreCase(input)) {
                        gameStats.addWord(input.toUpperCase(), BoggleStats.Player.Human);
                    }
                }
            }
            gameStats.setPlayerTime(timePlayed);
            if (timer.isAlive()) {
                timer.stop();
            }
        }
    }
}


class WordSearchTree {
    private HashMap<String, ArrayList<Position>> root;
    private ArrayList<WordSearchTree> subtrees;
    public WordSearchTree(HashMap<String, ArrayList<Position>> root) {
        this.root = root;
        this.subtrees = new ArrayList<>();
    }
    public HashMap<String, ArrayList<Position>> getRoot() {
        return this.root;
    }

    public ArrayList<WordSearchTree> getSubtrees() {
        return this.subtrees;
    }
}
