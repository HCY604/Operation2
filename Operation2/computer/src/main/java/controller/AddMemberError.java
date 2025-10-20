package controller;

import javax.swing.*;

public class AddMemberError extends JFrame {
    private static final long serialVersionUID = 1L;

    public AddMemberError() {
        setTitle("註冊失敗");
        setSize(300, 180);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lbl = new JLabel("註冊失敗，請稍後再試。");
        lbl.setBounds(80, 30, 200, 30);
        add(lbl);

        JButton btn = new JButton("返回註冊");
        btn.setBounds(90, 80, 120, 28);
        add(btn);

        btn.addActionListener(e -> {
            new AddMember().setVisible(true);
            dispose();
        });
    }
}
