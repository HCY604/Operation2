package controller;

import model.Goods;
import model.Member;
import service.GoodsService;
import service.impl.GoodsServiceImpl;
import util.ChangeTool;
import util.Tool;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;


public class OrderUi extends JFrame {
    private static final long serialVersionUID = 1L;

    private JComboBox<String> cboCategory;
    private JComboBox<String> cboProduct;
    private JTextField txtUnitPrice;
    private JSpinner spQuantity;
    private JTable table;
    private JLabel lblTotal;

    private final DefaultTableModel tableModel;
    private final Order order = new Order();

    /** 商品資料 */
    private final Map<String, java.util.List<ProductItem>> productData = new LinkedHashMap<>();

    private static class ProductItem {
        String name; int price;
        ProductItem(String name, int price){ this.name=name; this.price=price; }
        public String toString(){ return name; }
    }

    public OrderUi() {
        setTitle("購買電腦");
        setSize(800, 640);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // 1) 準備商品資料
        setupProducts();

        // 2) 上方輸入區（類別 / 產品 / 單價 / 數量）
        JLabel l1 = new JLabel("類別：");
        l1.setBounds(20, 20, 60, 25);
        add(l1);

        cboCategory = new JComboBox<>(productData.keySet().toArray(new String[0]));
        cboCategory.setBounds(80, 20, 160, 25);
        add(cboCategory);

        JLabel l2 = new JLabel("產品：");
        l2.setBounds(260, 20, 60, 25);
        add(l2);

        cboProduct = new JComboBox<>();
        cboProduct.setBounds(320, 20, 220, 25);
        add(cboProduct);

        JLabel l3 = new JLabel("單價：");
        l3.setBounds(560, 20, 50, 25);
        add(l3);

        txtUnitPrice = new JTextField();
        txtUnitPrice.setBounds(610, 20, 80, 25);
        txtUnitPrice.setHorizontalAlignment(SwingConstants.RIGHT);
        txtUnitPrice.setEditable(false);
        add(txtUnitPrice);

        JLabel l4 = new JLabel("數量：");
        l4.setBounds(20, 60, 60, 25);
        add(l4);

        spQuantity = new JSpinner(new SpinnerNumberModel(1, 1, 99, 1));
        spQuantity.setBounds(80, 60, 80, 25);
        add(spQuantity);

        JButton btnAdd = new JButton("加入購物車");
        btnAdd.setBounds(180, 60, 120, 28);
        add(btnAdd);

        JButton btnDelete = new JButton("刪除選取");
        btnDelete.setBounds(310, 60, 120, 28);
        add(btnDelete);

        // 3) 下方表格
        String[] cols = {"類別", "產品名稱", "單價", "數量", "小計"};
        tableModel = new DefaultTableModel(cols, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        table = new JTable(tableModel);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 100, 750, 420);
        add(sp);

        // 4) 總金額 + 結帳
        lblTotal = new JLabel("應付總額：$0");
        lblTotal.setBounds(20, 530, 300, 30);
        lblTotal.setFont(lblTotal.getFont().deriveFont(Font.BOLD, 16f));
        add(lblTotal);

        JButton btnPaySave = new JButton("付款 / 存檔(找零)");
        btnPaySave.setBounds(620, 560, 150, 30);
        add(btnPaySave);

        // ---- 事件綁定 ----
        cboCategory.addActionListener(e -> refreshProducts());
        cboProduct.addActionListener(e -> syncUnitPrice());
        btnAdd.addActionListener(e -> addCurrentSelection());
        btnDelete.addActionListener(e -> deleteSelectedRow());
        btnPaySave.addActionListener(e -> doPayAndSave());

        // 初始化一次
        refreshProducts();
    }

    // 準備產品
    private void setupProducts() {
        productData.put("CPU", Arrays.asList(
                new ProductItem("Ryzen 5 5600", 6000),
                new ProductItem("Intel Core i5-12400F", 5200)
        ));
        productData.put("主機板", Arrays.asList(
                new ProductItem("B550M-PLUS", 3200),
                new ProductItem("B760M-PRO", 4200)
        ));
        productData.put("記憶體", Arrays.asList(
                new ProductItem("DDR4 16GB(8x2) 3200", 1200),
                new ProductItem("DDR5 32GB(16x2) 5600", 2900)
        ));
        productData.put("顯示卡", Arrays.asList(
                new ProductItem("RTX 4060 8GB", 9800),
                new ProductItem("RX 7600 8GB", 8500)
        ));
        productData.put("SSD", Arrays.asList(
                new ProductItem("NVMe 1TB", 2100),
                new ProductItem("SATA 512GB", 900)
        ));
        productData.put("電源", Arrays.asList(
                new ProductItem("550W 80+ Bronze", 1700),
                new ProductItem("650W 80+ Gold", 2600)
        ));
        productData.put("機殼", Arrays.asList(
                new ProductItem("ATX 風冷透明側板", 1500),
                new ProductItem("mATX 小機殼", 1200)
        ));
    }

    // 類別改變時，重新載入該類別的產品清單
    private void refreshProducts() {
        String cat = (String) cboCategory.getSelectedItem();
        cboProduct.removeAllItems();
        if (cat != null) {
            java.util.List<ProductItem> list = productData.getOrDefault(cat, Collections.emptyList());
            for (ProductItem p : list) {
                cboProduct.addItem(p.name);
            }
        }
        syncUnitPrice();
    }

    // 根據目前選到的產品，顯示單價
    private void syncUnitPrice() {
        String cat = (String) cboCategory.getSelectedItem();
        String prod = (String) cboProduct.getSelectedItem();
        if (cat == null || prod == null) { txtUnitPrice.setText(""); return; }
        for (ProductItem p : productData.getOrDefault(cat, Collections.emptyList())) {
            if (p.name.equals(prod)) {
                txtUnitPrice.setText(String.valueOf(p.price));
                return;
            }
        }
        txtUnitPrice.setText("");
    }

    // 加入購物車
    private void addCurrentSelection() {
        String cat = (String) cboCategory.getSelectedItem();
        String prod = (String) cboProduct.getSelectedItem();
        int price;
        int qty;
        try {
            price = Integer.parseInt(txtUnitPrice.getText().trim());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "單價格式錯誤");
            return;
        }
        qty = (int) spQuantity.getValue();
        if (qty <= 0) {
            JOptionPane.showMessageDialog(this, "數量必須大於 0");
            return;
        }
        int line = price * qty;

        tableModel.addRow(new Object[]{cat, prod, price, qty, line});
        order.addItem(new Order.Item(cat, prod, price, qty));
        updateTotalLabel();
    }

    // 刪除選取列
    private void deleteSelectedRow() {
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "請先選取要刪除的列");
            return;
        }
        tableModel.removeRow(row);
        order.removeItemAt(row);
        updateTotalLabel();
    }

    // 更新總金額標籤
    private void updateTotalLabel() {
        int total = order.getTotalAmount();
        lblTotal.setText("應付總額：$" + total);
    }

    // 付款結帳後自動開列印清單
    private void doPayAndSave() {
        int total = order.getTotalAmount();
        if (total <= 0) {
            JOptionPane.showMessageDialog(this, "尚未加入任何商品");
            return;
        }

        String s = JOptionPane.showInputDialog(this, "請輸入付款金額（整數）", String.valueOf(total));
        if (s == null) return;
        int cash;
        try { cash = Integer.parseInt(s.trim()); }
        catch (Exception ex) { JOptionPane.showMessageDialog(this, "金額格式不正確"); return; }
        if (cash < total) {
            JOptionPane.showMessageDialog(this, "付款金額不足（應付 $" + total + "）");
            return;
        }
        int change = cash - total;


        Member m = null;
        Integer memberId = null;
        try {
            m = Tool.loadMember();
            if (m != null) memberId = m.getId();
        } catch (Exception ignore) {}

        
        java.util.List<Order.Item> snapshot = new java.util.ArrayList<>(order.getItems());
        String recordTime = new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());

        // 寫入資料庫每列商品一筆goods
        GoodsService svc = new GoodsServiceImpl();
        for (Order.Item it : snapshot) {
            Goods g = new Goods();
            g.setMemberId(memberId);
            g.setCategoryName(it.getCategoryName());
            g.setProductName(it.getProductName());
            g.setUnitPrice(it.getUnitPrice());
            g.setQuantity(it.getQuantity());
            g.setLineTotal(it.getLineTotal());
            g.setOrderTotal(total);
            g.setPaidCash(cash);
            g.setChangeAmount(change);
            svc.save(g);
        }

        // 自動開啟列印清單
        new PrintReceipt(m, snapshot, total, cash, change, recordTime).setVisible(true);

        // 額外顯示找零分解
        JOptionPane.showMessageDialog(this, ChangeTool.breakdown(change), "找零明細", JOptionPane.INFORMATION_MESSAGE);

        // 清空購物車與畫面
        order.clear();
        tableModel.setRowCount(0);
        updateTotalLabel();
    }


    public static void main(String[] args) {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception ignore) {}
        EventQueue.invokeLater(() -> new OrderUi().setVisible(true));
    }
}
