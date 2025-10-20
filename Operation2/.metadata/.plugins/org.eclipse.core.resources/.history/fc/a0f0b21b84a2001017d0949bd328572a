package controller;

import model.Member;
import service.MemberService;
import service.impl.MemberServiceImpl;
import util.Tool;

import javax.swing.*;

public class Login extends JFrame {
    private static final long serialVersionUID = 1L;

    private JTextField txtUsername;
    private JPasswordField txtPassword;

    private final MemberService memberService = new MemberServiceImpl();

    public Login() {
        setTitle("會員登入");
        setSize(360, 240);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblU = new JLabel("帳號：");
        lblU.setBounds(40, 40, 80, 25);
        add(lblU);

        txtUsername = new JTextField();
        txtUsername.setBounds(100, 40, 200, 25);
        add(txtUsername);

        JLabel lblP = new JLabel("密碼：");
        lblP.setBounds(40, 80, 80, 25);
        add(lblP);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(100, 80, 200, 25);
        add(txtPassword);

        JButton btnLogin = new JButton("登入");
        btnLogin.setBounds(100, 130, 90, 28);
        add(btnLogin);

        JButton btnRegister = new JButton("註冊");
        btnRegister.setBounds(210, 130, 90, 28);
        add(btnRegister);

        btnLogin.addActionListener(e -> doLogin());
        btnRegister.addActionListener(e -> {
            new AddMember().setVisible(true);
            dispose();
        });
    }

    private void doLogin() {
        String u = txtUsername.getText().trim();
        String p = String.valueOf(txtPassword.getPassword());

        if (u.isEmpty() || p.isEmpty()) {
            JOptionPane.showMessageDialog(this, "請輸入帳號與密碼");
            return;
        }

        try {
            Member m = memberService.login(u, p);
            if (m != null) {
                try { Tool.saveMember(m); } catch (Exception ignore) {}
                new LoginSuccess(m).setVisible(true);
                dispose();
            } else {
                new LoginError().setVisible(true);
                dispose();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            new LoginError().setVisible(true);
            dispose();
        }
    }
}
