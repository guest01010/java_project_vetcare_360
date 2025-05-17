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
import models.Owner;
import models.Pet;
import models.Visit;

import java.time.LocalDate;

public class OwnerDetailUI {
    public static void display(Owner owner) {
        Stage window = new Stage();
        window.setTitle("Owner Details");

      
        Label info = new Label(
                        "👤 " + owner.getFullName() + "\n" +
                        "🏠 " + owner.getAddress() + ", " + owner.getCity() + "\n" +
                        "📞 " + owner.getTelephone()
        );
        info.setFont(Font.font("Segoe UI", FontWeight.MEDIUM, 15));
        info.setTextFill(Color.web("#2c3e50"));

        VBox petsBox = new VBox(18);
        petsBox.setStyle("-fx-background-color: #f5f7fa; -fx-padding: 20; -fx-border-radius: 12; -fx-border-color: #dcdfe3;");
        petsBox.setAlignment(Pos.TOP_CENTER);

        for (Pet pet : owner.getPets()) {
            VBox petCard = new VBox(10);
            petCard.setPadding(new Insets(15));
            petCard.setSpacing(10);
            petCard.setStyle(
                    "-fx-background-color: #ffffff;" +
                            "-fx-background-radius: 10;" +
                            "-fx-border-radius: 10;" +
                            "-fx-border-color: #e0e6ed;" +
                            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.05), 4, 0.2, 0, 2);"
            );

            Label petName = new Label("🐾 Name: " + pet.getName());
            petName.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));
            petName.setTextFill(Color.web("#34495e"));

            Label petType = new Label("📌 Type: " + pet.getType());
            petType.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 13));
            petType.setTextFill(Color.web("#555"));

           
            HBox petButtons = new HBox(10);
            petButtons.setAlignment(Pos.CENTER_LEFT);

            Button modifyBtn = new Button("Modify");
            modifyBtn.setStyle(buttonStyle("#f39c12"));
            modifyBtn.setOnAction(e -> {
                TextInputDialog nameDialog = new TextInputDialog(pet.getName());
                nameDialog.setTitle("Modify Pet");
                nameDialog.setHeaderText("New Pet Name:");
                nameDialog.setContentText("Enter name:");
                nameDialog.showAndWait().ifPresent(pet::setName);

                TextInputDialog typeDialog = new TextInputDialog(pet.getType());
                typeDialog.setTitle("Modify Pet");
                typeDialog.setHeaderText("New Pet Type:");
                typeDialog.setContentText("Enter type:");
                typeDialog.showAndWait().ifPresent(pet::setType);

                window.close();
                display(owner);
            });

            Button removeBtn = new Button("Remove");
            removeBtn.setStyle(buttonStyle("#e74c3c"));
            removeBtn.setOnAction(e -> {
                owner.getPets().remove(pet);
                window.close();
                display(owner);
            });

            Button addVisitBtn = new Button("Add Visit");
            addVisitBtn.setStyle(buttonStyle("#3498db"));
            addVisitBtn.setOnAction(e -> {
                DatePicker datePicker = new DatePicker(LocalDate.now());
                TextInputDialog descDialog = new TextInputDialog("Routine check-up");
                descDialog.setTitle("Add Visit");
                descDialog.setHeaderText("Visit Description");
                descDialog.setContentText("Enter description:");
                descDialog.showAndWait().ifPresent(description -> {
                    Visit visit = new Visit(datePicker.getValue(), description);
                    pet.addVisit(visit);
                    window.close();
                    display(owner);
                });
            });

            petButtons.getChildren().addAll(modifyBtn, removeBtn, addVisitBtn);

            VBox visitList = new VBox(5);
            for (Visit v : pet.getVisits()) {
                HBox visitRow = new HBox(10);
                visitRow.setAlignment(Pos.CENTER_LEFT);
                Label visitLabel = new Label("🗓 " + v.getDate() + " - " + v.getDescription());
                visitLabel.setFont(Font.font("Segoe UI", 13));
                Button removeVisitBtn = new Button("Remove");
                removeVisitBtn.setStyle(buttonStyle("#c0392b"));
                removeVisitBtn.setOnAction(e -> {
                    pet.getVisits().remove(v);
                    window.close();
                    display(owner);
                });
                visitRow.getChildren().addAll(visitLabel, removeVisitBtn);
                visitList.getChildren().add(visitRow);
            }

            petCard.getChildren().addAll(petName, petType, petButtons, visitList);
            petsBox.getChildren().add(petCard);
        }

        
        Button addPetBtn = new Button("➕ Add Pet");
        addPetBtn.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
        addPetBtn.setStyle(buttonStyle("#2ecc71"));
        addPetBtn.setOnAction(e -> {
            TextInputDialog nameDialog = new TextInputDialog("New Pet");
            nameDialog.setTitle("Add Pet");
            nameDialog.setHeaderText("Enter Pet Name:");
            nameDialog.setContentText("Name:");
            nameDialog.showAndWait().ifPresent(newName -> {
                TextInputDialog typeDialog = new TextInputDialog("Unknown");
                typeDialog.setTitle("Add Pet");
                typeDialog.setHeaderText("Enter Pet Type:");
                typeDialog.setContentText("Type:");
                typeDialog.showAndWait().ifPresent(newType -> {
                    owner.getPets().add(new Pet(newName, newType));
                    window.close();
                    display(owner);
                });
            });
        });

        VBox layout = new VBox(20, info, petsBox, addPetBtn);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(25));
        layout.setStyle("-fx-background-color: #ecf0f1;");

        
        layout.setOpacity(0);
        layout.setTranslateY(40);
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(800), layout);
        scaleTransition.setFromX(0.9);
        scaleTransition.setToX(1);
        scaleTransition.setFromY(0.9);
        scaleTransition.setToY(1);
        scaleTransition.setInterpolator(Interpolator.EASE_BOTH);

        FadeTransition fadeIn = new FadeTransition(Duration.millis(800), layout);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);

        new ParallelTransition(scaleTransition, fadeIn).play();

        Scene scene = new Scene(layout, 700, 700);
        window.setScene(scene);
        window.show();
    }

    private static String buttonStyle(String color) {
        return "-fx-background-color: " + color + ";" +
                "-fx-text-fill: white;" +
                "-fx-background-radius: 6;" +
                "-fx-padding: 6 14;" +
                "-fx-cursor: hand;" +
                "-fx-font-size: 13px;";
    }
}
