package main;

import config.Koneksi;
import javax.swing.SwingUtilities;
import view.LoginView;

public class MainApp {

    public static void main(String[] args) {
        Koneksi.getConnection();
        SwingUtilities.invokeLater(() -> {new LoginView().setVisible(true);});
    }
}