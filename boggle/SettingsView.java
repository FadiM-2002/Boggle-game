package boggle;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class SettingsView {
    BoggleView boggleView;

    public SettingsView(BoggleView boggleView) {
        this.boggleView = boggleView;
    }

    public void display() {
        Font font = new Font(this.boggleView.getFontSize());
        Label difficultyLabel = new Label("Difficulty");
        difficultyLabel.setFont(font);
        ChoiceBox<String> difficultyChoice = new ChoiceBox<>();
        difficultyChoice.getItems().addAll("Easy", "Medium", "Hard");
        difficultyChoice.setOnAction((e) -> {
            if ((difficultyChoice.getValue()).equals("Easy")) {
                this.boggleView.setDifficulty(33);
            } else if ((difficultyChoice.getValue()).equals("Medium")) {
                this.boggleView.setDifficulty(67);
            } else {
                this.boggleView.setDifficulty(100);
            }

            this.display();
            difficultyChoice.setValue(difficultyChoice.getValue());
        });
        HBox difficultySetting = new HBox(25.0);
        difficultySetting.getChildren().addAll(difficultyLabel, difficultyChoice);
        Label fontLabel = new Label("Font Size");
        fontLabel.setFont(font);
        ChoiceBox<String> fontChoice = new ChoiceBox<>();
        fontChoice.getItems().addAll("Small", "Medium", "Large");
        fontChoice.setOnAction((e) -> {
            if ((fontChoice.getValue()).equals("Small")) {
                this.boggleView.setFontSize(15);
            } else if ((fontChoice.getValue()).equals("Medium")) {
                this.boggleView.setFontSize(25);
            } else {
                this.boggleView.setFontSize(35);
            }

            this.display();
            fontChoice.setValue(fontChoice.getValue());
        });
        HBox fontSetting = new HBox(25.0);
        fontSetting.getChildren().addAll(fontLabel, fontChoice);
        Label sizeLabel = new Label("Grid Size");
        sizeLabel.setFont(font);
        ChoiceBox<String> sizeChoice = new ChoiceBox<>();
        sizeChoice.getItems().addAll("4 x 4", "5 x 5");
        sizeChoice.setOnAction((e) -> {
            if (((String)sizeChoice.getValue()).equals("4 x 4")) {
                this.boggleView.setGridSize(4);
            } else {
                this.boggleView.setGridSize(5);
            }

            this.display();
            sizeChoice.setValue((String)sizeChoice.getValue());
        });
        HBox sizeSetting = new HBox(25.0);
        sizeSetting.getChildren().addAll(sizeLabel, sizeChoice);
        BorderPane borderPane = new BorderPane();
        VBox vBox = new VBox(40.0);
        vBox.getChildren().addAll(difficultySetting, fontSetting, sizeSetting);
        vBox.setAlignment(Pos.CENTER);
        borderPane.setRight(vBox);
        Button back = new Button("Back");
        back.setFont(font);
        borderPane.setTop(back);
        BorderPane.setAlignment(back, Pos.TOP_LEFT);
        borderPane.setPadding(new Insets(10.0, 275.0, 10.0, 10.0));
        this.boggleView.start(this.boggleView.getStage());
        this.boggleView.getStage().setScene(new Scene(borderPane, 750.0, 600.0));
        back.setOnAction((e) -> {
            this.boggleView.getStage().setScene(this.boggleView.getMainMenu());
        });
    }
}
