package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static Scene scene;
    private static Stage primaryStage;
    public static Stage secondStage;
    @Override
    public void start(Stage Stage) {
        this.primaryStage = Stage;
        try {
            scene = new Scene(loadFXML("GameTicTac"));
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static void setFXML(String fxml, String tittle) throws IOException {
        scene.setRoot(loadFXML(fxml));
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.centerOnScreen();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/"+ fxml + ".fxml"));
        return fxmlLoader.load();
    }
    public static void newStage(String fxml, String title) {
        try {
            Parent node = loadFXML(fxml);
            secondStage = new Stage();
            Scene scene = new Scene(node);
            secondStage.setScene(scene);
            secondStage.setTitle(title);
            secondStage.initOwner(primaryStage);
            secondStage.initModality(Modality.WINDOW_MODAL);
            secondStage.centerOnScreen();
            secondStage.showAndWait();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
