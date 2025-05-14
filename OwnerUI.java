package views;

import models.*;
import main.*;
import javafx.animation.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class OwnerUI {
    public static void display(Stage primaryStage) {
        Stage window = new Stage();
        window.setTitle("Find Owners");

        TextField searchField = new TextField();
        searchField.setPromptText("Enter last name...");
        searchField.setStyle("-fx-font-size: 14px;");

        Button searchButton = new Button("Search");
        Button addOwnerButton = new Button("Add New Owner");

        String buttonStyle = "-fx-background-color: #007bff; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 6;";
        searchButton.setStyle(buttonStyle);
        addOwnerButton.setStyle(buttonStyle);

        searchButton.setOnMouseEntered(e -> searchButton.setStyle("-fx-background-color: #0056b3; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 6;"));
        searchButton.setOnMouseExited(e -> searchButton.setStyle(buttonStyle));
        addOwnerButton.setOnMouseEntered(e -> addOwnerButton.setStyle("-fx-background-color: #0056b3; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 6;"));
        addOwnerButton.setOnMouseExited(e -> addOwnerButton.setStyle(buttonStyle));

        VBox ownerList = new VBox(15);
        ownerList.setPadding(new Insets(10));
        ownerList.setStyle("-fx-background-color: #ffffff; -fx-border-color: #ccc; -fx-border-radius: 8; -fx-padding: 10;");

        searchButton.setOnAction(e -> {
            ownerList.getChildren().clear();
            for (Owner o : DataStorage.searchOwnersByLastName(searchField.getText())) {
                Label nameLabel = new Label(o.getFullName());
                nameLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

                Button viewBtn = new Button("View");
                Button editBtn = new Button("Edit");
                Button deleteBtn = new Button("Delete");

                String btnBase = "-fx-font-size: 12px; -fx-text-fill: white; -fx-background-radius: 5;";
                viewBtn.setStyle(btnBase + "-fx-background-color: #28a745;");
                editBtn.setStyle(btnBase + "-fx-background-color: #ffc107;");
                deleteBtn.setStyle(btnBase + "-fx-background-color: #dc3545;");

                viewBtn.setOnMouseEntered(ev -> viewBtn.setStyle(btnBase + "-fx-background-color: #218838;"));
                viewBtn.setOnMouseExited(ev -> viewBtn.setStyle(btnBase + "-fx-background-color: #28a745;"));

                editBtn.setOnMouseEntered(ev -> editBtn.setStyle(btnBase + "-fx-background-color: #e0a800;"));
                editBtn.setOnMouseExited(ev -> editBtn.setStyle(btnBase + "-fx-background-color: #ffc107;"));

                deleteBtn.setOnMouseEntered(ev -> deleteBtn.setStyle(btnBase + "-fx-background-color: #c82333;"));
                deleteBtn.setOnMouseExited(ev -> deleteBtn.setStyle(btnBase + "-fx-background-color: #dc3545;"));

                viewBtn.setOnAction(ev -> OwnerDetailUI.display(o));
                editBtn.setOnAction(ev -> AddOwnerUI.display(o));
                deleteBtn.setOnAction(ev -> {
                    DataStorage.removeOwner(o.getFullName());
                    ownerList.getChildren().removeIf(node -> node.getUserData() == o);
                });

                HBox actions = new HBox(10, viewBtn, editBtn, deleteBtn);
                actions.setAlignment(Pos.CENTER_LEFT);

                VBox card = new VBox(8, nameLabel, actions);
                card.setUserData(o);
                card.setPadding(new Insets(10));
                card.setStyle("-fx-background-color: #f8f9fa; -fx-border-color: #dee2e6; -fx-border-radius: 8; -fx-background-radius: 8;");
                ownerList.getChildren().add(card);
            }
        });

        addOwnerButton.setOnAction(e -> AddOwnerUI.display(null));

        VBox layout = new VBox(20,
                new Label("Find owner by last name:"),
                searchField,
                searchButton,
                new Separator(),
                ownerList,
                addOwnerButton
        );
        layout.setStyle("-fx-background-color: #f0f8ff; -fx-padding: 20px;");
        layout.setPrefWidth(500);

        layout.setOpacity(0);
        FadeTransition fadeIn = new FadeTransition(Duration.millis(800), layout);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.play();

        window.setScene(new Scene(layout, 600, 600));
        window.show();
    }
}
