import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import service.IWordService;
import service.WordService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;



public class Main extends Application {
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

    @Override
    public void start(Stage primaryStage) throws Exception{
        URL url = new File("src/main/resources/view/sample.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        primaryStage.setTitle("DHQ Dictionary");
        Image dictionary_icon=loadImage("src/main/resources/view/image/dictionary_icon.png");
        primaryStage.getIcons().add(dictionary_icon);
        primaryStage.setScene(new Scene(root, 968, 747));
        primaryStage.show();
    }

    static IWordService wordService = new WordService();

    public static void main(String[] args) {
        // List<Word> list = wordService.findWordsNearMeaning("hell");
        //list.forEach(w -> w.printMeaning());

//        Word w = wordService.findExactWord("hello");
//        w.printMeaning();
        launch(args);
    }
}

