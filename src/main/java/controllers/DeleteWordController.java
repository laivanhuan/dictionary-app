package controllers;

import dialog.ConfirmDialog;
import dialog.ErrorDialog;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DeleteWordController extends PrimaryController {
    private String delWord;
    public Button btYesDel;
    public TextField tfDeleteWord;

    public void eventHandle() {
        btYesDel.setOnMouseClicked(e -> {
            deleteHandle();
        });

        tfDeleteWord.setOnKeyPressed(event -> {
            KeyEvent ke = (KeyEvent) event;
            if (ke.getCode().equals(KeyCode.ENTER)) {
                deleteHandle();
            }
        });
    }
    public void deleteHandle() {
        ConfirmDialog cf = new ConfirmDialog();
        ErrorDialog errorDialog = new ErrorDialog();
        delWord = tfDeleteWord.getText();
        if (delWord != null && !delWord.equals("")) {
            if (wordService.findExactWord(delWord).getWord() == null) {
                errorDialog.show("Error", "Không có từ này trong từ điển!");
            }

            else if (cf.show("Confirm", "Bạn muốn xóa từ " + delWord + " ra khỏi từ điển?")) {
                delStage.close();
                delStage = null;
                long id = wordService.findExactWord(delWord).getId();
                wordService.deleteWordById(id);
            }
        }
    }

    public static Scene getDeleteScene() throws IOException {
        URL url = new File("src/main/resources/view/sample.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene deleteScene = new Scene(root);

        try {
            url = new File("src/main/resources/view/DeleteWordScene.fxml").toURI().toURL();
            root = FXMLLoader.load(url);
            deleteScene = new Scene(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return deleteScene;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.eventHandle();
    }
}
