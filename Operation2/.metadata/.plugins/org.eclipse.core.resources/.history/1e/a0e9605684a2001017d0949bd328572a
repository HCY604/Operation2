package controller;

import model.Member;
import service.MemberService;
import service.impl.MemberServiceImpl;

import javax.swing.*;

public class AddMember extends JFrame {
    private static final long serialVersionUID = 1L;

    private JTextField txtName, txtUsername, txtPassword, txtAddress, txtPhone;

    private final MemberService memberService = new MemberServiceImpl();

    public AddMember() {
        setTitle("會員註冊");
        setSize(420, 340);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel l1 = new JLabel("姓名：");
        l1.setBounds(40, 30, 80, 25); add(l1);
        txtName = new JTextField(); txtName.setBounds(120, 30, 230, 25); add(txtName);

        JLabel l2 = new JLabel("帳號：");
        l2.setBounds(40, 70, 80, 25); add(l2);
        txtUsername = new JTextField(); txtUsername.setBounds(120, 70, 230, 25); add(txtUsername);

        JLabel l3 = new JLabel("密碼：");
        l3.setBounds(40, 110, 80, 25); add(l3);
        txtPassword = new JPasswordField(); txtPassword.setBounds(120, 110, 230, 25); add(txtPassword);

        JLabel l4 = new JLabel("地址：");
        l4.setBounds(40, 150, 80, 25); add(l4);
        txtAddress = new JTextField(); txtAddress.setBounds(120, 150, 230, 25); add(txtAddress);

        JLabel l5 = new JLabel("電話：");
        l5.setBounds(40, 190, 80, 25); add(l5);
        txtPhone = new JTextField(); txtPhone.setBounds(120, 190, 230, 25); add(txtPhone);

        JButton btnSubmit = new JButton("送出註冊");
        btnSubmit.setBounds(120, 240, 110, 28); add(btnSubmit);

        JButton btnBack = new JButton("返回登入");
        btnBack.setBounds(240, 240, 110, 28); add(btnBack);

        btnSubmit.addActionListener(e -> doRegister());
        btnBack.addActionListener(e -> {
            new Login().setVisible(true);
            dispose();
        });
    }

    private void doRegister() {
        String name = txtName.getText().trim();
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText().trim();
        String address = txtAddress.getText().trim();
        String phone = txtPhone.getText().trim();

        if (name.isEmpty() || username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "姓名、帳號、密碼不可空白");
            return;
        }

        try {
            Member m = new Member();
            m.setName(name);
            m.setUsername(username);
            m.setPassword(password);
            m.setAddress(address);
            m.setPhone(phone);

            memberService.insert(m);

            new AddMemberSuccess().setVisible(true);
            dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
            new AddMemberError().setVisible(true);
            dispose();
        }
    }
}
