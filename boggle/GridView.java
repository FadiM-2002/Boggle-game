package boggle;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.*;

public class GridView {
    BoggleView boggleView;
    int gridSize;
    BoggleStats gameStats;
    private final String[] dice_small_grid = new String[]{"AAEEGN", "ABBJOO", "ACHOPS", "AFFKPS", "AOOTTW", "CIMOTU", "DEILRX", "DELRVY", "DISTTY", "EEGHNW", "EEINSU", "EHRTVW", "EIOSST", "ELRTTY", "HIMNQU", "HLNNRZ"};
    private final String[] dice_big_grid = new String[]{"AAAFRS", "AAEEEE", "AAFIRS", "ADENNN", "AEEEEM", "AEEGMU", "AEGMNN", "AFIRSY", "BJKQXZ", "CCNSTW", "CEIILT", "CEILPT", "CEIPST", "DDLNOR", "DDHNOT", "DHHLOR", "DHLNOR", "EIIITT", "EMOTTT", "ENSSSU", "FIPRSY", "GORRVW", "HIPRRY", "NOOTUW", "OOOTTU"};
    static Map<String, ArrayList<Position>> words;

    public GridView(BoggleView boggleView) {
        this.boggleView = boggleView;
        this.gridSize = boggleView.getGridSize();
        this.gameStats = new BoggleStats();
    }

    public void display() {
        Font font = new Font(this.boggleView.getFontSize());
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(50.0));
        GridPane gridPane = new GridPane();
        borderPane.setCenter(gridPane);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(25.0);
        gridPane.setHgap(25.0);
        BoggleGrid grid = new BoggleGrid(this.gridSize);
        grid.initalizeBoard(this.randomizeLetters(this.gridSize));
        HashMap<String, ArrayList<Position>> allWords = new HashMap<>();
        this.findAllWords(allWords, new Dictionary("Boggle-game/wordlist.txt"), grid);
        words = allWords;

        for(int row = 0; row < this.gridSize; ++row) {
            for(int col = 0; col < this.gridSize; ++col) {
                Label letterLabel = new Label(Character.toString(grid.getCharAt(row, col)));
                letterLabel.setFont(font);
                GridPane.setConstraints(letterLabel, col, row);
                gridPane.getChildren().add(letterLabel);
            }
        }

        HBox hBox = new HBox(25.0);
        borderPane.setBottom(hBox);
        hBox.setAlignment(Pos.CENTER);
        ListView<String> wordBox = new ListView<>();
        borderPane.setRight(wordBox);
        BorderPane.setAlignment(wordBox, Pos.CENTER);
        wordBox.setMaxHeight(200.0);
        TextField textField = new TextField();
        textField.setPromptText("Enter words");
        textField.setFocusTraversable(false);
        Button doneButton = new Button("Done");
        textField.setFont(font);
        doneButton.setFont(font);
        hBox.getChildren().addAll(textField, doneButton);
        wordBox.getItems().add("Words Found");
        textField.setOnAction((e) -> {
            if (this.humanMove(textField.getText(), allWords)) {
                wordBox.getItems().add(textField.getText());
            }
            textField.setText("");
        });
        Scene gameScene = new Scene(borderPane, 750.0, 600.0);
        this.boggleView.getStage().setScene(gameScene);
        doneButton.setOnAction((e) -> this.boggleView.getStage().setScene(this.displayStats()));
    }

    private Scene displayStats() {
        this.computerMove(words);
        Font font = new Font(this.boggleView.getFontSize());
        Label statsTitle = new Label("Game Statistics");
        statsTitle.setFont(font);
        Label playerWords = new Label("Player's Words:");
        playerWords.setFont(font);
        Label playerScore = new Label("Player's Score: " + this.gameStats.getScore());
        playerScore.setFont(font);
        Label computerWords = new Label("Computer's Words:");
        computerWords.setFont(font);
        Label cScore = new Label("Computer's Score: " + this.gameStats.getComputerScore());
        cScore.setFont(font);
        VBox player = new VBox(25.0);
        ListView<String> pWords = new ListView<>();
        pWords.getItems().addAll(this.gameStats.getPlayerWords());
        player.getChildren().addAll(playerScore, playerWords, pWords);
        VBox computer = new VBox(25.0);
        ListView<String> cWords = new ListView<>();
        cWords.getItems().addAll(this.gameStats.getComputerWords());
        computer.getChildren().addAll(cScore, computerWords, cWords);
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(50.0));
        borderPane.setTop(statsTitle);
        BorderPane.setAlignment(statsTitle, Pos.CENTER);
        borderPane.setLeft(player);
        borderPane.setRight(computer);
        return new Scene(borderPane, 750.0, 600.0);
    }

    private String randomizeLetters(int size) {
        String[] dice_grid;
        if (size == 4) {
            dice_grid = this.dice_small_grid;
        } else {
            dice_grid = this.dice_big_grid;
        }

        List<String> strList = Arrays.asList(dice_grid);
        Collections.shuffle(strList);
        strList.toArray(dice_grid);
        String str = "";
        String[] var5 = dice_grid;
        int var6 = dice_grid.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            String string = var5[var7];
            int randomNumber = (int)(Math.random() * 6.0);
            str = str + string.charAt(randomNumber);
        }

        return str;
    }

    private void findAllWords(Map<String, ArrayList<Position>> allWords, Dictionary boggleDict, BoggleGrid boggleGrid) {
        GridIterator iterator = boggleGrid.getIterator();

        while(iterator.hasNext()) {
            HashMap<String, ArrayList<Position>> root = new HashMap();
            ArrayList<Position> positions = new ArrayList<>();
            Position position = iterator.next();
            positions.add(position);
            String str = Character.toString(boggleGrid.getCharAt(position.getRow(), position.getCol()));
            root.put(str, positions);
            WordSearchTree tree = new WordSearchTree(root);
            this.buildTree(tree, str, positions, boggleGrid, boggleDict);
            this.findAllWordsHelper(allWords, tree, boggleDict);
        }

    }

    private void findAllWordsHelper(Map<String, ArrayList<Position>> map, WordSearchTree tree, Dictionary dict) {
        String key = (String)tree.getRoot().keySet().toArray()[0];
        if (key.length() > 3 && dict.containsWord(key)) {
            map.putAll(tree.getRoot());
        }

        Iterator var5 = tree.getSubtrees().iterator();

        while(var5.hasNext()) {
            WordSearchTree subtree = (WordSearchTree)var5.next();
            this.findAllWordsHelper(map, subtree, dict);
        }

    }

    private void buildTree(WordSearchTree tree, String string, ArrayList<Position> positions, BoggleGrid grid, Dictionary dict) {
        for(int i = -1; i <= 1; ++i) {
            for(int j = -1; j <= 1; ++j) {
                int row = (positions.get(positions.size() - 1)).getRow() + i;
                int col = (positions.get(positions.size() - 1)).getCol() + j;
                if (0 <= row && row < grid.numRows() && 0 <= col && col < grid.numCols()) {
                    String prefix = string + grid.getCharAt(row, col);
                    Position newPos = new Position(row, col);
                    if (this.isValid(newPos, positions) && dict.isPrefix(prefix)) {
                        ArrayList<Position> newPositions = new ArrayList<>(positions);
                        newPositions.add(newPos);
                        HashMap<String, ArrayList<Position>> map = new HashMap<>();
                        map.put(prefix, newPositions);
                        WordSearchTree subtree = new WordSearchTree(map);
                        tree.getSubtrees().add(subtree);
                        this.buildTree(subtree, prefix, newPositions, grid, dict);
                    }
                }
            }
        }

    }

    private boolean isValid(Position pos, ArrayList<Position> list) {
        Iterator var3 = list.iterator();

        Position position;
        do {
            if (!var3.hasNext()) {
                return true;
            }

            position = (Position)var3.next();
        } while(pos.getRow() != position.getRow() || pos.getCol() != position.getCol());

        return false;
    }

    private boolean humanMove(String input, Map<String, ArrayList<Position>> allWords) {
        Iterator var3 = allWords.keySet().iterator();

        String word;
        do {
            if (!var3.hasNext()) {
                return false;
            }

            word = (String)var3.next();
        } while(!word.equalsIgnoreCase(input));

        this.gameStats.addWord(input.toUpperCase(), BoggleStats.Player.Human);
        return true;
    }

    private void computerMove(Map<String, ArrayList<Position>> allWords) {
        double difficultyRate = (double)this.boggleView.getDifficulty() * 0.01;
        int numberOfWords = (int)(difficultyRate * (double)allWords.size());
        int counter = 0;
        Iterator var6 = allWords.keySet().iterator();

        while(var6.hasNext()) {
            String word = (String)var6.next();
            if (!this.gameStats.getPlayerWords().contains(word.toUpperCase())) {
                ++counter;
                this.gameStats.addWord(word.toUpperCase(), BoggleStats.Player.Computer);
            }

            if (counter == numberOfWords) {
                break;
            }
        }

    }
}
