package controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXML;

import utils.TextToSpeech;
import utils.APIGoogleTranslate;
import com.jfoenix.controls.JFXTextArea;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class GoogleTranslate {
    @FXML
    private JFXTextArea englishText;

    @FXML
    private JFXTextArea translatedText;

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
}