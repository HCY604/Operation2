package controller;

import javax.swing.*;

public class LoginError extends JFrame {
    private static final long serialVersionUID = 1L;

    public LoginError() {
        setTitle("登入失敗");
        setSize(300, 180);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lbl = new JLabel("帳號或密碼錯誤。");
        lbl.setBounds(90, 30, 200, 30);
        add(lbl);

        JButton btnBack = new JButton("返回登入");
        btnBack.setBounds(90, 80, 120, 28);
        add(btnBack);

        btnBack.addActionListener(e -> {
            new Login().setVisible(true);
            dispose();
        });
    }
}
