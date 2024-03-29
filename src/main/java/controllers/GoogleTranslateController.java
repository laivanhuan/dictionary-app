package controllers;

import controllers.PrimaryController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import utils.TextToSpeech;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import utils.APIGoogleTranslate;
import utils.ProjectConfig;
import dialog.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GoogleTranslateController extends PrimaryController {
    @FXML
    private JFXTextArea englishText;

    @FXML
    private JFXTextArea translatedText;

    public void setBackButton() throws IOException {
        ConfirmDialog cancelConfirm = new ConfirmDialog();
        boolean isConfirm = cancelConfirm.show("Back",
                "Bạn có chắc muốn quay lại ?");
        if (isConfirm) {
            ProjectConfig.primaryStage.setScene(PrimaryController.getScene());
        }
    }

    public void translate() {
        String text = englishText.getText();
        String translated = APIGoogleTranslate.translate(text);
        translatedText.setText(translated);
    }

    public void englishSpeak() {
        String text = englishText.getText();
        TextToSpeech.speak(text);
    }

    public void translatedSpeak() {
        String text = translatedText.getText();
        TextToSpeech.speak(text);
    }

    public static Scene getScene() throws IOException {
        URL url = new File("src/main/resources/view/GoogleTranslate.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        return new Scene(root);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}