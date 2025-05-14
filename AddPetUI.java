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
import models.Owner;

public class AddPetUI {

    public static void display(Owner owner) {
        Stage window = new Stage();
        window.setTitle("Add New Pet");

        // Simple color background
        StackPane backgroundLayer = new StackPane();
        backgroundLayer.setStyle("-fx-background-color: #f0f2f5;"); // Light gray background like Facebook

        // Title with animation
        Label title = new Label("Add New Pet");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        title.setTextFill(Color.web("#1877f2")); // Facebook blue color
        title.setOpacity(0);
        FadeTransition fadeInTitle = new FadeTransition(Duration.seconds(1), title);
        fadeInTitle.setFromValue(0);
        fadeInTitle.setToValue(1);
        fadeInTitle.play();

        // TextFields with focus effects
        TextField name = createTextField("Pet Name");
        TextField type = createTextField("Type");

        // Submit Button with hover effect and gradient color
        Button submit = new Button("Add");
        submit.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        submit.setStyle(
                "-fx-background-color: linear-gradient(to right, #1877f2, #0366d6);" +
                        "-fx-text-fill: white;" +
                        "-fx-background-radius: 5;" +
                        "-fx-padding: 10 20;"
        );

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

        submit.setOnAction(e -> {
            Pet p = new Pet(name.getText(), type.getText());
            owner.addPet(p);
            window.close();
        });

        // Layout
        VBox formBox = new VBox(16,
                title,
                createFieldGroup("Pet Name", name),
                createFieldGroup("Type", type),
                submit
        );
        formBox.setPadding(new Insets(30));
        formBox.setMaxWidth(400);
        formBox.setAlignment(Pos.CENTER);
        formBox.setStyle("-fx-background-color: white;" +
                "-fx-border-radius: 8;" +
                "-fx-border-color: #ddd;" +
                "-fx-border-width: 1;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0.5, 0, 0);"
        );

        // Animate the form appearance
        formBox.setOpacity(0);
        formBox.setTranslateY(40);
        ScaleTransition formPop = new ScaleTransition(Duration.millis(800), formBox);
        formPop.setFromX(0.8);
        formPop.setToX(1);
        formPop.setFromY(0.8);
        formPop.setToY(1);
        formPop.setInterpolator(Interpolator.EASE_BOTH);

        FadeTransition fadeIn = new FadeTransition(Duration.millis(800), formBox);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);

        ParallelTransition formAnim = new ParallelTransition(formPop, fadeIn);
        formAnim.play();

        // Centered container with rounded corners
        StackPane container = new StackPane(formBox);
        container.setAlignment(Pos.CENTER);
        container.setStyle("-fx-background-color: #f0f2f5;");

        // Create Scene
        Scene scene = new Scene(container, 450, 400);
        window.setScene(scene);
        window.show();
    }

    // TextField with focus and hover effects
    private static TextField createTextField(String placeholder) {
        TextField tf = new TextField();
        tf.setPromptText(placeholder);
        tf.setStyle(
                "-fx-background-color: white;" +
                        "-fx-border-color: #ccc;" +
                        "-fx-border-radius: 5;" +
                        "-fx-padding: 10 15;" +
                        "-fx-font-size: 14px;"
        );

        tf.setOnMouseEntered(e -> tf.setStyle(
                "-fx-background-color: white;" +
                        "-fx-border-color: #1877f2;" +
                        "-fx-border-radius: 5;" +
                        "-fx-padding: 10 15;" +
                        "-fx-font-size: 14px;"
        ));

        tf.setOnMouseExited(e -> tf.setStyle(
                "-fx-background-color: white;" +
                        "-fx-border-color: #ccc;" +
                        "-fx-border-radius: 5;" +
                        "-fx-padding: 10 15;" +
                        "-fx-font-size: 14px;"
        ));

        tf.setOnMousePressed(e -> tf.setStyle(
                "-fx-background-color: #e9f2fd;" +
                        "-fx-border-color: #1877f2;" +
                        "-fx-border-radius: 5;" +
                        "-fx-padding: 10 15;" +
                        "-fx-font-size: 14px;"
        ));

        tf.setPrefWidth(280);
        return tf;
    }

    // Field group with label and text field
    private static VBox createFieldGroup(String labelText, TextField field) {
        Label label = new Label(labelText);
        label.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        label.setTextFill(Color.web("#333"));
        return new VBox(8, label, field);
    }
}
