package utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class WifiConnect {
    public static boolean isAvailable() throws IOException {
        try {
            final URL url = new URL("http://www.google.com");
            final URLConnection con = url.openConnection();
            con.connect();
            con.getInputStream().close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
