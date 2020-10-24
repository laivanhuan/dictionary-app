package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import model.Word;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import service.IWordService;
import service.WordService;
import utils.TextToSpeech;
import utils.ProjectConfig;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PrimaryController implements Initializable {
    @FXML
    public Button btSearch;

    public TextField tfSearchBox;

    public Button btDelete;

    public Button btAdd;

    public Button btEdit;
    public Button btAPIGoogleTrans;

    public Button btSpeak;

    public ListView<String> lvWords;

    @FXML
    public WebView taVMeaning;

    private String eWord;
    protected static Word word;
    private List<Word> list = new ArrayList<>();
    protected static IWordService wordService = new WordService();

    public void setWord() {
        WebEngine webEngine = taVMeaning.getEngine();
        webEngine.loadContent(word.getHtml());
    }

    public void setNearWord() {
        List<Word> listNearWord = wordService.findWordsNearMeaning(eWord);
        lvWords.getItems().clear();

        if (listNearWord != null) {
            for (int i = 0; i < listNearWord.size(); ++i) {
                lvWords.getItems().add(listNearWord.get(i).getWord());
            }
        }
    }

    public void initializeWordList() {
        lvWords.getItems().clear();
        list = wordService.findAll();
        for (int i = 0; i < list.size(); ++i) {
            lvWords.getItems().add(list.get(i).getWord());
        }
    }

    public void mouseClickedHandle() {
        btSearch.setOnMouseClicked(event -> {
            eWord = tfSearchBox.getText();
            word = wordService.findExactWord(eWord);
            if (word.getId() > 0 && !word.getWord().equals("")) {
                this.setWord();
                this.initializeWordList();
            } else {
                this.setNearWord();
            }

        });

        lvWords.setOnMouseClicked(event -> {
            eWord = lvWords.getSelectionModel().getSelectedItem();
            word = wordService.findExactWord(eWord);

            if (word != null && word.getId() > 0 && !word.getWord().equals("")) {
                this.setWord();
                this.initializeWordList();
            } else {
                this.setNearWord();
            }
        });

        btEdit.setOnMouseClicked(event -> {
            try {
                setEditWordScene();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        btDelete.setOnMouseClicked(event -> {

        });

        btAdd.setOnMouseClicked(event -> {
            try {
                word = new Word();
                setAddWordScene();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        btSpeak.setOnMouseClicked(event -> {
            TextToSpeech.speak(eWord);
        });

        btAPIGoogleTrans.setOnMouseClicked(event -> {
            try {
                setGoogleTranslateScene();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    public void keyPressedHandle() {
        tfSearchBox.setOnKeyPressed(event -> {
            KeyEvent ke = (KeyEvent) event;
            if (ke.getCode().equals(KeyCode.ENTER)) {
                eWord = tfSearchBox.getText();
                word = wordService.findExactWord(eWord);

                if (word.getId() > 0 && !word.getWord().equals("")) {
                    this.setWord();
                    lvWords.getItems().clear();
                    this.initializeWordList();
                } else {
                    this.setNearWord();
                }
            }
        });
    }

    public void setGoogleTranslateScene() throws IOException {
        ProjectConfig.primaryStage.setScene(GoogleTranslateController.getScene());
    }

    public static Scene getScene() throws IOException {
        URL url = new File("src/main/resources/view/sample.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        return new Scene(root);
    }

    public void setAddWordScene() throws IOException {
        ProjectConfig.primaryStage.setScene(AddWordController.getScene());
    }

    public void setEditWordScene() throws IOException {
        ProjectConfig.primaryStage.setScene(EditWordController.getScene());
    }

    public static void updatePronounce(Document doc) {
        Elements element = doc.getElementsByTag("h3");
        word.setPronounce(element.text());
    }

    public static void updateWord(Document doc) {
        Elements element = doc.getElementsByTag("h1");
        word.setWord(element.text());
    }

    public static void updateDescription(Document doc) {
        word.setDescription(doc.text());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.initializeWordList();
        this.mouseClickedHandle();
        this.keyPressedHandle();
    }
}
