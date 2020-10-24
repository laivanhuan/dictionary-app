package controllers;

import dialog.ConfirmDialog;
import dialog.ErrorDialog;
import dialog.InformationDialog;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.web.HTMLEditor;
import utils.ProjectConfig;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditWordController extends PrimaryController implements Initializable {
    @FXML
    private HTMLEditor htmlEditor;

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

    // sua ham nay ho t :<
    public boolean editWord() {
        return true;
    }

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.initializeWordList();
        this.mouseClickedHandle();
        this.keyPressedHandle();
        String html = PrimaryController.word.getHtml();
        htmlEditor.setHtmlText(html);
    }
}
