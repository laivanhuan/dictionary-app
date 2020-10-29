package controllers;

import dialog.ConfirmDialog;
import dialog.ErrorDialog;
import dialog.InformationDialog;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.web.HTMLEditor;
import model.Word;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import utils.ProjectConfig;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditWordController extends PrimaryController implements Initializable {
    @FXML
    private HTMLEditor htmlEditor;

    @FXML
    private TextArea taEWord;

    private Document doc;
    private String textHtml;

    public static Scene getScene() throws IOException {
        URL url = new File("src/main/resources/view/sample.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);

        try {
            url = new File("src/main/resources/view/EditWordScene.fxml").toURI().toURL();
            root = FXMLLoader.load(url);

        } catch (IOException e) {
            ErrorDialog error = new ErrorDialog();
            error.show("Error", "Bạn cần chọn 1 từ để chỉnh sửa!");
        }
        return new Scene(root);
    }

    public void setSaveButton() throws IOException {
        ConfirmDialog confirm = new ConfirmDialog();
        boolean isConfirm = confirm.show("Edit Word", "Bạn có chắc muốn sửa từ này không ?");
        if (isConfirm) {
            boolean isSave = editWord();
            if (isSave) {
                InformationDialog inform = new InformationDialog();
                inform.show("Edit Word", "Đã sửa từ thành công !");
                setPrimaryStage();
            } else {
                ErrorDialog error = new ErrorDialog();
                error.show("Edit", "Có lỗi xảy ra. Vui lòng thực hiện lại");
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

    public boolean editWord() {
        Word word = PrimaryController.word;
        textHtml = htmlEditor.getHtmlText();
        word.setHtml(textHtml);
        word.setWord(taEWord.getText());
        word.printMeaning();
        if(word.getId()<=0)
        {
            return false;
        }
        return wordService.updateWord(word);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textHtml = PrimaryController.word.getHtml();
        doc = Jsoup.parse(textHtml);
        if (doc.text() != null && !doc.text().equals("")) {
            htmlEditor.setHtmlText(textHtml);
            taEWord.setText(PrimaryController.word.getWord());
        }
    }
}
