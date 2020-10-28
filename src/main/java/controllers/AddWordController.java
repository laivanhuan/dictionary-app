package controllers;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dialog.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.web.HTMLEditor;
import model.Word;
import org.jsoup.nodes.Document;
import utils.ProjectConfig;

public class AddWordController extends PrimaryController {
    @FXML
    private HTMLEditor htmlEditor;

    @FXML
    private TextArea tWord;

    private Document doc;
    private String textHtml;

    public void setSaveButton() throws IOException {
        ConfirmDialog confirm = new ConfirmDialog();
        boolean isConfirm = confirm.show("Add New Word", "Bạn có chắc muốn thêm từ này không ?");
        if (isConfirm) {
            boolean isSave = addWord();
            if (isSave) {
                InformationDialog inform = new InformationDialog();
                inform.show("Add New Word", "Đã thêm từ thành công !");
                setPrimaryStage();
            } else {
                ErrorDialog error = new ErrorDialog();
                error.show("Add New Word", "Lỗi: Từ này đã có rồi :<");
            }
        }
    }

    public void setBackButton() throws IOException {
        ConfirmDialog confirm = new ConfirmDialog();
        boolean isConfirm = confirm.show("Back", "Bạn có chắc muốn quay lại ?");
        if (isConfirm) {
            setPrimaryStage();
        }
    }

    public void setPrimaryStage() throws IOException {
        ProjectConfig.primaryStage.setScene(PrimaryController.getScene());
    }

    public boolean addWord() {
        textHtml = htmlEditor.getHtmlText();
        String textWord = tWord.getText();
        Word word = new Word(textWord, textHtml, "", "");

        return wordService.addNewWord(word);

//        PrimaryController.word.setHtml(textHtml);
//        doc = Jsoup.parse(textHtml);
//
//        PrimaryController.updateWord(doc);
//        PrimaryController.updatePronounce(doc);
//        PrimaryController.updateDescription(doc);
//
//        wordService.addNewWord(word);
//        initializeWordList();
//
//        return true;
    }

    public static Scene getScene() throws IOException {
        URL url = new File("src/main/resources/view/AddWordScene.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        return new Scene(root);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
