package utils;

import java.io.IOException;
import java.rmi.UnknownHostException;

import com.darkprograms.speech.synthesiser.SynthesiserV2;

import dialog.ErrorDialog;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

public class TextToSpeech {
    public static void speak(String text) {
        SynthesiserV2 synthesizer = new SynthesiserV2("AIzaSyBOti4mM-6x9WDnZIjIeyEU21OpBXqWBgw");
        Thread thread = new Thread(() -> {
            try {
                AdvancedPlayer player = new AdvancedPlayer(synthesizer.getMP3Data(text));
                player.play();
            } catch (IOException | JavaLayerException e) {
                ErrorDialog error=new ErrorDialog();
                error.show("Lỗi mạng","Vui lòng kết nối mạng để sử dụng");
            }
        });
        thread.setDaemon(false);
        thread.start();
    }
}
