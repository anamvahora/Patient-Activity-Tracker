package its340_andreas_anamv;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class LoggerUtil {
    public static void log(String message) {
        try {
            FileWriter fw = new FileWriter("app_log.txt", true);
            fw.write(LocalDateTime.now() + " - " + message + "\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
