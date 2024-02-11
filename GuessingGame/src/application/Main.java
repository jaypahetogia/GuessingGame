package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.Random;

public class Main extends Application {
    private int randomNumber;
    private int guessCount = 0;
    private ImageView hintImageView;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        randomNumber = new Random().nextInt(100) + 1;
        hintImageView = new ImageView();
        hintImageView.setFitWidth(150);  
        hintImageView.setFitHeight(150);
        

        VBox root = new VBox(10);
        root.setAlignment(Pos.BOTTOM_CENTER);
       //creating our background image
        Image backgroundImage = new Image(getClass().getResourceAsStream("/images/citylandscape.jpeg"));
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        root.setBackground(new Background(background));
        
        
        Label label = new Label("Guess a number between 1 and 100:");//a label title for our guessing game
        TextField guessField = new TextField();//textfield for inputs
        
        //buttons and their labels
        Button submitButton = new Button("Submit Guess");
        Button hintButton = new Button("Hint");
        Button newGameButton = new Button("New Game");
        Label feedbackLabel = new Label();//another label for feedback to user

        submitButton.setOnAction(e -> {//setup of each button actions when clicked
            int userGuess;
            try {
                userGuess = Integer.parseInt(guessField.getText());
                guessCount++;

                if (userGuess == randomNumber) {
                	feedbackLabel.setFont(Font.font("System", 24));  
                    feedbackLabel.setStyle("-fx-text-fill: black;"); 
                    feedbackLabel.setText("Congratulations! You've guessed the number in " + guessCount + " attempts.");
                    hintImageView.setImage(new Image("/images/congrats.jpeg"));
                } else if (userGuess < randomNumber) {
                	feedbackLabel.setFont(Font.font("System", 24));  
                    feedbackLabel.setStyle("-fx-text-fill: red;"); 
                    feedbackLabel.setText("Guess higher!");
                    hintImageView.setImage(new Image("/images/toohigh.png"));
                } else {
                	feedbackLabel.setFont(Font.font("System", 24));  
                    feedbackLabel.setStyle("-fx-text-fill: blue;"); 
                    feedbackLabel.setText("Guess lower!");
                    hintImageView.setImage(new Image("/images/toolow.jpeg"));
                }
            		} catch (NumberFormatException ex) {
            	feedbackLabel.setFont(Font.font("System", 34)); 
                feedbackLabel.setStyle("-fx-text-fill: red;"); 
                feedbackLabel.setText("Please enter a valid number.");
            	}
        });

        hintButton.setOnAction(e -> {
            if (randomNumber <= 50) {
                feedbackLabel.setText("Hint: The number is 50 or below.");
            } else {
                feedbackLabel.setText("Hint: The number is above 50.");
           }
        		});

        newGameButton.setOnAction(e -> {
            randomNumber = new Random().nextInt(100) + 1;
            feedbackLabel.setText("");
            guessField.setText("");
            guessCount = 0;
        });

  root.getChildren().addAll(label, guessField, submitButton, hintButton, newGameButton, hintImageView, feedbackLabel);//add everything to our scene

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Guessing Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
