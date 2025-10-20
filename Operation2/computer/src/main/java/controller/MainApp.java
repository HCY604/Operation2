package controller;

import javax.swing.UIManager;

public class MainApp {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignore) {}

        java.awt.EventQueue.invokeLater(() -> new Login().setVisible(true));
    }
}
