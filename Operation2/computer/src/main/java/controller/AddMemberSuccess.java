package controller;

import javax.swing.*;

public class AddMemberSuccess extends JFrame {
    private static final long serialVersionUID = 1L;

    public AddMemberSuccess() {
        setTitle("註冊成功");
        setSize(300, 180);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lbl = new JLabel("註冊成功，請登入。");
        lbl.setBounds(90, 30, 200, 30);
        add(lbl);

        JButton btn = new JButton("返回登入");
        btn.setBounds(90, 80, 120, 28);
        add(btn);

        btn.addActionListener(e -> {
            new Login().setVisible(true);
            dispose();
        });
    }
}
