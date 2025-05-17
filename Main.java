package main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import views.*;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        Label welcome = new Label("Bienvenue dans VetCare 360");
        welcome.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #0d6efd;");

        Button btnOwners = new Button("🔍 Rechercher un Propriétaire");
        Button btnVets = new Button("👨‍⚕️ Liste des Vétérinaires");

    
        String baseBtnStyle = "-fx-background-color: #0d6efd; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10 20; -fx-background-radius: 8;";
        String hoverBtnStyle = "-fx-background-color: #0b5ed7; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10 20; -fx-background-radius: 8;";

        btnOwners.setStyle(baseBtnStyle);
        btnOwners.setOnMouseEntered(e -> btnOwners.setStyle(hoverBtnStyle));
        btnOwners.setOnMouseExited(e -> btnOwners.setStyle(baseBtnStyle));

        btnVets.setStyle(baseBtnStyle);
        btnVets.setOnMouseEntered(e -> btnVets.setStyle(hoverBtnStyle));
        btnVets.setOnMouseExited(e -> btnVets.setStyle(baseBtnStyle));

        
        btnOwners.setOnAction(e -> OwnerUI.display(primaryStage));
        btnVets.setOnAction(e -> VeterinarianUI.display(primaryStage));

        VBox card = new VBox(20, welcome, btnOwners, btnVets);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(30));
        card.setStyle("-fx-background-color: #ffffff; -fx-border-color: #dee2e6; -fx-border-radius: 10; -fx-background-radius: 10; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 4);");

        StackPane root = new StackPane(card);
        root.setStyle("-fx-background-color: #f8f9fa;");

        Scene scene = new Scene(root, 500, 350);
        primaryStage.setTitle("VetCare 360 - Accueil");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        DataStorage.initVeterinarians();
        launch(args);
    }
}
