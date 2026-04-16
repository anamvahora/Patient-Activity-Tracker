package its340_andreas_anamv;
import javax.swing.*;
import java.awt.*;

public class FormUtils {
    public static void enableEdit(JTextField[] fields) {
        for (JTextField field : fields) {
            field.setEditable(true);
            field.setBackground(Color.WHITE);
        }
    }

    public static void clearAndEdit(JTextField[] fields) {
        for (JTextField field : fields) {
            field.setText("");
            field.setEditable(true);
            field.setBackground(Color.WHITE);
        }
    }
    
    public static void disableEdit(JTextField[] fields) {
    java.awt.Color lockedColor = new java.awt.Color(240, 240, 240);
    for (JTextField field : fields) {
        field.setEditable(false);
        field.setBackground(lockedColor);
    }
}
}
