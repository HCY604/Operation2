package controller;

import model.Member;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PrintReceipt extends JFrame {
    private static final long serialVersionUID = 1L;

    private final JTextArea txtReceipt = new JTextArea();

    public PrintReceipt(Member member,
                        List<Order.Item> items,
                        int total,
                        int cash,
                        int change,
                        String recordTime) {
        setTitle("結帳清單");
        setSize(520, 640);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        txtReceipt.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
        txtReceipt.setEditable(false);

        JScrollPane sp = new JScrollPane(txtReceipt);
        add(sp, BorderLayout.CENTER);

        JPanel bottom = new JPanel(null);
        bottom.setPreferredSize(new Dimension(520, 56));
        JButton btnPrint = new JButton("列印");
        btnPrint.setBounds(300, 12, 90, 32);
        bottom.add(btnPrint);

        JButton btnClose = new JButton("關閉");
        btnClose.setBounds(400, 12, 90, 32);
        bottom.add(btnClose);

        add(bottom, BorderLayout.SOUTH);

        txtReceipt.setText(buildReceipt(member, items, total, cash, change, recordTime));

        btnPrint.addActionListener(e -> {
            try {
                boolean ok = txtReceipt.print();
                if (ok) JOptionPane.showMessageDialog(this, "已送出列印。");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "列印失敗：" + ex.getMessage());
            }
        });
        btnClose.addActionListener(e -> dispose());
    }

    private String buildReceipt(Member member, List<Order.Item> items,
                                int total, int cash, int change, String recordTime) {

        String store = "Computer 線上門市";
        String now = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now());
        String userLine;
        if (member != null) {
            String nn = (member.getName() != null && !member.getName().isEmpty()) ? member.getName() : member.getUsername();
            String idStr = (member.getId() == null) ? "-" : String.valueOf(member.getId());
            userLine = String.format("會員：%s (ID:%s)", nn, idStr);
        } else {
            userLine = "會員：未登入";
        }

        List<String> lines = new ArrayList<>();
        lines.add("======== " + store + " ========");
        lines.add("開立時間：" + now);
        lines.add("紀錄時間：" + (recordTime == null ? now : recordTime));
        lines.add(userLine);
        lines.add("----------------------------------------");
        lines.add(String.format("%-10s %-16s %6s %4s %8s", "類別", "產品", "單價", "數量", "小計"));
        lines.add("----------------------------------------");

        int idx = 1;
        for (Order.Item it : items) {
            String cat = safe(it.getCategoryName());
            String prod = safe(it.getProductName());
            String row = String.format("%02d. %-8s %-16.16s %6d %4d %8d",
                    idx++, cat, prod, it.getUnitPrice(), it.getQuantity(), it.getLineTotal());
            lines.add(row);
        }

        lines.add("----------------------------------------");
        lines.add(String.format("%-22s %s %10d", "", "應付總額：", total));
        lines.add(String.format("%-22s %s %10d", "", "實收金額：", cash));
        lines.add(String.format("%-22s %s %10d", "", "找零金額：", change));
        lines.add("----------------------------------------");
        lines.add("感謝您的購買");

        return String.join("\n", lines);
    }

    private static String safe(String s) { return s == null ? "" : s; }
}
