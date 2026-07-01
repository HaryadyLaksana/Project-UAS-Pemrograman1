package utils;

import javax.swing.*;
import java.awt.*;

public class AppConfig {

    public static void apply(JFrame frame) {
        frame.setTitle("UNPAM E-Wallet");
        ImageIcon icon = new ImageIcon(AppConfig.class.getResource("/img/LOGO_UNPAM.png"));
        frame.setIconImage(icon.getImage());
        frame.setLocationRelativeTo(null);
    }
}