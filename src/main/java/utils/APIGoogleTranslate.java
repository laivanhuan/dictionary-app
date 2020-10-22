package utils;

import java.io.IOException;

import com.darkprograms.speech.translator.GoogleTranslate;

public class APIGoogleTranslate {

    public static String translate(String text) {
        try {
            return GoogleTranslate.translate("vi", text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
