package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import model.Word;
import service.IWordService;
import service.WordService;
import utils.TextToSpeech;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Samplecontroller implements Initializable {
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

    public TextArea taVMeaning;

    public Pane pnWarmingExit;

    public AnchorPane apMainScene;

    private String eWord;
    private Word word;
    private List<Word> list = new ArrayList<>();
    private IWordService wordService = new WordService();

    public void setWord() {

            String temp = word.getWord().substring(0, 1).toUpperCase()
                        + word.getWord().substring(1);

            taVMeaning.setText("/" + word.getPronounce() +"/" + "\n" + word.getDescription());
            taEWord.setText(temp);
    }

    public void setNearWord() {
        List<Word> listNearWord = new ArrayList<>();
        listNearWord = wordService.findWordsNearMeaning(eWord);

        String meassage = "Từ bạn muốn tìm là: \n";

        for (int i = 0; i < listNearWord.size(); ++i) {
            meassage  += listNearWord.get(i).getWord();
        }

        taVMeaning.setText(meassage);
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
            if (word != null && !word.getWord().equals("")) {
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

                if (word != null && !word.getWord().equals("")) {
                    this.setWord();
                }

                else {
                    this.setNearWord();
                }
            }


        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.initializeWordList();
        this.mouseClickedHandle();
        this.keyPressedHandle();
    }
}
