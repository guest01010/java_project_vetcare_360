package views;

import javafx.animation.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.Veterinarian;
import main.DataStorage;

public class VeterinarianUI {
    public static void display(Stage primaryStage) {
        Stage window = new Stage();
        window.setTitle("Veterinarians");

        VBox layout = new VBox(16);
        layout.setStyle("-fx-background-color: #eaeff5; -fx-padding: 25;");
        layout.setAlignment(Pos.TOP_CENTER);

        Label header = new Label("Veterinarian Directory");
        header.setFont(Font.font("System", FontWeight.BOLD, 22));
        header.setTextFill(Color.web("#2c3e50"));

        layout.getChildren().add(header);

        for (Veterinarian v : DataStorage.veterinarians) {
            VBox card = new VBox(8);
            card.setPadding(new Insets(12));
            card.setSpacing(6);
            card.setAlignment(Pos.CENTER_LEFT);
            card.setStyle(
                    "-fx-background-color: white;" +
                            "-fx-border-color: #d1d9e0;" +
                            "-fx-border-radius: 6;" +
                            "-fx-background-radius: 6;" +
                            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.07), 8, 0.4, 0, 2);"
            );

            Label name = new Label(v.getFullName());
            name.setFont(Font.font("System", FontWeight.BOLD, 14));
            name.setTextFill(Color.web("#333"));

            Label specialty = new Label("Specialty: " + v.getSpecialty());
            specialty.setFont(Font.font("System", FontWeight.NORMAL, 13));
            specialty.setTextFill(Color.web("#555"));

            Button viewBtn = new Button("View Details");
            viewBtn.setFont(Font.font("System", FontWeight.SEMI_BOLD, 12));
            viewBtn.setStyle(
                    "-fx-background-color: #2c7be5;" +
                            "-fx-text-fill: white;" +
                            "-fx-background-radius: 4;" +
                            "-fx-padding: 6 14;"
            );

            viewBtn.setOnMouseEntered(e -> viewBtn.setStyle(
                    "-fx-background-color: #1a5dc9;" +
                            "-fx-text-fill: white;" +
                            "-fx-background-radius: 4;" +
                            "-fx-padding: 6 14;"
            ));

            viewBtn.setOnMouseExited(e -> viewBtn.setStyle(
                    "-fx-background-color: #2c7be5;" +
                            "-fx-text-fill: white;" +
                            "-fx-background-radius: 4;" +
                            "-fx-padding: 6 14;"
            ));

            
            viewBtn.setOnAction(e -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Veterinarian Info");
                alert.setHeaderText(v.getFullName());
                alert.setContentText("Specialty: " + v.getSpecialty());
                alert.showAndWait();
            });

            card.getChildren().addAll(name, specialty, viewBtn);
            layout.getChildren().add(card);
        }

        
        layout.setOpacity(0);
        layout.setTranslateY(30);

        FadeTransition fadeIn = new FadeTransition(Duration.millis(600), layout);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);

        TranslateTransition slideUp = new TranslateTransition(Duration.millis(600), layout);
        slideUp.setFromY(30);
        slideUp.setToY(0);
        slideUp.setInterpolator(Interpolator.EASE_OUT);

        new ParallelTransition(fadeIn, slideUp).play();

        Scene scene = new Scene(layout, 480, 500);
        window.setScene(scene);
        window.setMinWidth(460);
        window.setMinHeight(500);
        window.show();
    }
}
