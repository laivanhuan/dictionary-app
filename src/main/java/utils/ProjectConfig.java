package utils;

import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ProjectConfig {
    public static String dictionaryIconPath = "src/main/resources/view/image/dictionary_icon.png";
    public static Stage primaryStage = new Stage();

    public static Image loadImage(String path) {
        Image image = null;
        try {
            FileInputStream input = new FileInputStream(path);
            image = new Image(input);
        } catch (FileNotFoundException e) {
            System.out.println("Can't load image. Please retry! " + path);
            e.printStackTrace();
        }
        return image;
    }

    static {
        primaryStage.setTitle("DHQ Dictionary");
        Image dictionary_icon = loadImage(ProjectConfig.dictionaryIconPath);
        primaryStage.getIcons().add(dictionary_icon);
    }
}
