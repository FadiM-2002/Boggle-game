package boggle;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class BoggleView {
    private Stage stage;
    private int fontSize;
    private int gridSize;
    private int difficulty;
    private Scene menu;

    public BoggleView(Stage stage) {
        this.stage = stage;
        this.fontSize = 25;
        this.gridSize = 4;
        this.difficulty = 100;
        this.start(stage);
    }

    public void start(Stage stage) {
        stage.setTitle("Boggle");
        Label title = new Label("Boggle");
        Button playButton = new Button("Play");
        Button settingsButton = new Button("Settings");
        SettingsView settings = new SettingsView(this);
        settingsButton.setOnAction((e) -> {
            settings.display();
        });
        Font font = new Font((double)this.fontSize);
        title.setFont(font);
        settingsButton.setFont(font);
        playButton.setFont(font);
        GridView gameScene = new GridView(this);
        playButton.setOnAction((e) -> {
            gameScene.display();
        });
        VBox layout = new VBox(25.0);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(title, playButton, settingsButton);
        Scene mainMenu = new Scene(layout, 750.0, 600.0);
        stage.setScene(mainMenu);
        this.menu = mainMenu;
        stage.show();
    }

    public Stage getStage() {
            return this.stage;
        }

        public int getDifficulty() {
            return this.difficulty;
        }

        public int getFontSize() {
            return this.fontSize;
        }

        public int getGridSize() {
            return this.gridSize;
        }

        public Scene getMainMenu() {
            return this.menu;
        }

        public void setDifficulty(int d) {
            this.difficulty = d;
        }

        public void setFontSize(int f) {
            this.fontSize = f;
        }

        public void setGridSize(int g) {
            this.gridSize = g;
        }
    }
