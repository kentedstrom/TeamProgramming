package userInterface;

import javafx.geometry.Insets;
import javafx.scene.layout.StackPane;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox {

    static boolean ANSWER;

    public static boolean save(){

        Stage window = new Stage();
        window.setTitle("Exit Program");
        window.setMinWidth(250);
        window.initModality(Modality.APPLICATION_MODAL);


        Button yesButton = new Button("Save and Exit");
        Button noButton = new Button("Exit without Saving");

        Label label = new Label();
        label.setText(" -Choose Option- ");

        yesButton.setOnAction(e -> {
            ANSWER = true;
            window.close();
        });

        noButton.setOnAction(e ->{
            ANSWER = false;
            window.close();
        });

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20,20,20,20));
        layout.getChildren().addAll(label, yesButton, noButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);

        window.showAndWait();

        return ANSWER;



    }
}
