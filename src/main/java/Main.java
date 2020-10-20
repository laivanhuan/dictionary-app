import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Word;
import service.IWordService;
import service.WordService;

import java.io.File;
import java.net.URL;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        URL url = new File("src/main/resources/view/sample.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    static IWordService wordService = new WordService();

    public static void main(String[] args) {
        List<Word> list = wordService.findWordsNearMeaning("hell");
        list.forEach(w -> w.printMeaning());

//        Word w = wordService.findExactWord("hello");
//        w.printMeaning();
//        launch(args);
    }
}

