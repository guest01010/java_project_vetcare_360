package views;

import javafx.animation.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.Pet;
import models.Visit;
import java.time.LocalDate;

public class AddVisitUI {
    public static void display(Pet pet) {
        Stage window = new Stage();
        window.setTitle("Add Visit for " + pet.getName());

        // Background with soft gradient
        StackPane backgroundLayer = new StackPane();
        backgroundLayer.setStyle("-fx-background-color: #f0f2f5;"); // Light gray background like Facebook

        // Input fields for Date and Description
        DatePicker date = new DatePicker();
        TextField desc = new TextField();

        // Submit Button with gradient color and hover effect
        Button submit = new Button("Add Visit");
        submit.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        submit.setStyle(
                "-fx-background-color: linear-gradient(to right, #1877f2, #0366d6);" +
                        "-fx-text-fill: white;" +
                        "-fx-background-radius: 5;" +
                        "-fx-padding: 10 20;"
        );

        // Hover effect for submit button
        submit.setOnMouseEntered(e -> submit.setStyle(
                "-fx-background-color: linear-gradient(to right, #0366d6, #1877f2);" +
                        "-fx-text-fill: white;" +
                        "-fx-background-radius: 5;" +
                        "-fx-padding: 10 20;"
        ));

        submit.setOnMouseExited(e -> submit.setStyle(
                "-fx-background-color: linear-gradient(to right, #1877f2, #0366d6);" +
                        "-fx-text-fill: white;" +
                        "-fx-background-radius: 5;" +
                        "-fx-padding: 10 20;"
        ));

        // Button action to add visit and close window
        submit.setOnAction(e -> {
            pet.addVisit(new Visit(date.getValue(), desc.getText()));
            window.close();
        });

        // Layout for the form with padding and spacing
        VBox layout = new VBox(15,
                new Label("Date:"),
                date,
                new Label("Description:"),
                desc,
                submit
        );
        layout.setPadding(new Insets(20));
        layout.setMaxWidth(350);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: white;" +
                "-fx-border-radius: 8;" +
                "-fx-border-color: #ddd;" +
                "-fx-border-width: 1;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0.5, 0, 0);"
        );

        // Smooth entrance animation for form
        layout.setOpacity(0);
        layout.setTranslateY(40);
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(800), layout);
        scaleTransition.setFromX(0.8);
        scaleTransition.setToX(1);
        scaleTransition.setFromY(0.8);
        scaleTransition.setToY(1);
        scaleTransition.setInterpolator(Interpolator.EASE_BOTH);

        FadeTransition fadeIn = new FadeTransition(Duration.millis(800), layout);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);

        ParallelTransition parallelTransition = new ParallelTransition(scaleTransition, fadeIn);
        parallelTransition.play();

        // Set scene with the layout and show window
        Scene scene = new Scene(layout, 350, 250);
        window.setScene(scene);
        window.show();
    }
}
