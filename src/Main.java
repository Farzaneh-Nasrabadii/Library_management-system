import view.LoginView;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Thread-safe way to launch Java Swing GUIs
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginView loginWindow = new LoginView();
                loginWindow.setVisible(true); // Makes the window visible to the user
            }
        });
    }
}