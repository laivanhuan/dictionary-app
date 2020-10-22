package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import model.Word;
import service.IWordService;
import service.WordService;
import utils.TextToSpeech;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Samplecontroller implements Initializable {
    @FXML
    private BorderPane borderPane;
    @FXML
    public Button btSearch;


    public TextField tfSearchBox;

    public Button btYes;

    public Button btCancel;

    public Button btDelete;

    public Button btAdd;

    public Button btEdit;
    public Button btAPIGoogleTrans;

    public TextArea taEWord;

    public Button btSpeak;

    public ListView<String> lvWords;

//    public TextArea taVMeaning;

    @FXML
    public WebView taVMeaning;
    public Pane pnWarmingExit;

    public AnchorPane apMainScene;

    private String eWord;
    private Word word;
    private List<Word> list = new ArrayList<>();
    private IWordService wordService = new WordService();

    public void setWord() {
        WebEngine webEngine = taVMeaning.getEngine();
            String temp = word.getWord().substring(0, 1).toUpperCase()
                        + word.getWord().substring(1);

            webEngine.loadContent(word.getHtml());
//            taVMeaning.setText("/" + word.getPronounce() +"/" + "\n" + word.getDescription());
            taEWord.setText(temp);
    }

    public void setNearWord() {
        List<Word> listNearWord = wordService.findWordsNearMeaning(eWord);
        lvWords.getItems().clear();

        for (int i = 0; i < listNearWord.size(); ++i) {
            lvWords.getItems().add(listNearWord.get(i).getWord());
        }
    }

    public void initializeWordList() {
        list = wordService.findAll();
        for (int i = 0; i < list.size(); ++i) {
            lvWords.getItems().add(list.get(i).getWord());
        }
    }

    public void mouseClickedHandle() {
        btSearch.setOnMouseClicked(event -> {
            eWord = tfSearchBox.getText();
            word = wordService.findExactWord(eWord);
            if (word.getId() > 0  && !word.getWord().equals("")) {
                this.setWord();
            }
            else {
                this.setNearWord();
            }

        });

        lvWords.setOnMouseClicked(event -> {
            eWord = lvWords.getSelectionModel().getSelectedItem();
            word = wordService.findExactWord(eWord);

            if (word != null && !word.getWord().equals("")) {
                this.setWord();
            }

            else {
                this.setNearWord();
            }
        });

        btEdit.setOnMouseClicked(event -> {

        });

        btDelete.setOnMouseClicked(event -> {

        });

        btAdd.setOnMouseClicked(event -> {

        });

        btSpeak.setOnMouseClicked(event -> {
            TextToSpeech.speak(eWord);
        });

        btAPIGoogleTrans.setOnMouseClicked(event -> {

        });

    }

    public void keyPressedHandle() {
        tfSearchBox.setOnKeyPressed(event -> {
            KeyEvent ke = (KeyEvent) event;
            if (ke.getCode().equals(KeyCode.ENTER)) {
                eWord = tfSearchBox.getText();
                word = wordService.findExactWord(eWord);

                if (word.getId() > -1 && !word.getWord().equals("")) {
                    this.setWord();
                }

                else {
                    this.setNearWord();
                }
            }


        });
    }
    public void setGoogleTranslateScene() {
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.initializeWordList();
        this.mouseClickedHandle();
        this.keyPressedHandle();
    }
}
