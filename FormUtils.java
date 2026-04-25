package utils;
import javax.swing.*;
import java.awt.*;

public class FormUtils {
    public static void enableEdit(JTextField[] fields) {
        for (JTextField field : fields) {
            field.setEditable(true);
            field.setBackground(Color.WHITE);
        }
    }

   public static void disableFields(javax.swing.text.JTextComponent... fields) {
    for (javax.swing.text.JTextComponent f : fields) {
        f.setEditable(false);
        f.setBackground(java.awt.Color.LIGHT_GRAY);
        }
    }
    public static void enableFields(javax.swing.text.JTextComponent... fields) {
    for (javax.swing.text.JTextComponent f : fields) {
        f.setEditable(true);
        f.setBackground(java.awt.Color.WHITE);
        }
    }
    
}
