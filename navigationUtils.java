
package its340_andreas_anamv;


public class navigationUtils {
    /**
     * Swaps the current window for a new one.
     * @param currentFrame The frame you are closing (use 'this')
     * @param nextFrame The new frame instance to open
     */
    //How to use it in your button: NavigationUtils.switchForm(this, new MedicalHistoryGUI());
    public static void switchForm(javax.swing.JFrame currentFrame, javax.swing.JFrame nextFrame) {
        nextFrame.setVisible(true);
        nextFrame.pack();
        nextFrame.setLocationRelativeTo(null); // Centers the new window
        currentFrame.dispose();
    }
}