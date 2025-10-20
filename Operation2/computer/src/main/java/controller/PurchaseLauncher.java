package controller;

import model.Member;
import util.Tool;

public class PurchaseLauncher {
    private final Member loginMember;

    public PurchaseLauncher(Member m) {
        this.loginMember = m;
    }

    public void open() {
        try {
            if (loginMember != null) Tool.saveMember(loginMember);
        } catch (Exception ignore) {}


        new OrderUi().setVisible(true);
    }
}
