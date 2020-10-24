import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.ProjectConfig;
import java.io.IOException;
import java.net.URL;
import java.io.File;

import utils.WifiConnect;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        URL url = new File("src/main/resources/view/sample.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        ProjectConfig.primaryStage.setScene(scene);
        ProjectConfig.primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

