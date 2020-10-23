package utils;

import java.io.IOException;

import com.darkprograms.speech.translator.GoogleTranslate;
import dialog.ErrorDialog;

public class APIGoogleTranslate {

    public static String translate(String text) {
        try {
            return GoogleTranslate.translate("vi", text);
        } catch (IOException e) {
            ErrorDialog error=new ErrorDialog();
            error.show("Lỗi mạng","Vui lòng kiểm tra lại mạng");
        }
        return "";
    }
}
