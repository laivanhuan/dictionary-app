package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import dialog.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.web.HTMLEditor;
import utils.ProjectConfig;

public class AddWordController {
    @FXML
    private HTMLEditor htmlEditor;

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

    // sua ham nay ho t :<
    public boolean addWord() {
        return true;
    }

    public static Scene getScene() throws IOException
    {
        URL url = new File("src/main/resources/view/AddWordScene.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        return new Scene(root);
    }
}
