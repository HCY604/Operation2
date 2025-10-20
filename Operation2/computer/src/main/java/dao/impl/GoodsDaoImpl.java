// src/dao/GoodsDaoImpl.java
package dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dao.GoodsDao;
import model.Goods;
import util.DbConnection;

public class GoodsDaoImpl implements GoodsDao {

    @Override
    public void insert(Goods g) {
        String sql = "insert into goods(member_id,category_name,product_name,unit_price,quantity,line_total,order_total,paid_cash,change_amount) "
                   + "values(?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = DbConnection.getDb().prepareStatement(sql);
            if (g.getMemberId() == null) ps.setNull(1, Types.INTEGER);
            else ps.setInt(1, g.getMemberId());
            ps.setString(2, g.getCategoryName());
            ps.setString(3, g.getProductName());
            ps.setInt(4, g.getUnitPrice());
            ps.setInt(5, g.getQuantity());
            ps.setInt(6, g.getLineTotal());
            ps.setInt(7, g.getOrderTotal());
            ps.setInt(8, g.getPaidCash());
            ps.setInt(9, g.getChangeAmount());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Goods> findAll() {
        List<Goods> list = new ArrayList<>();
        String sql = "select id,member_id,category_name,product_name,unit_price,quantity,line_total,order_total,paid_cash,change_amount,order_time "
                   + "from goods order by id desc";
        try {
            PreparedStatement ps = DbConnection.getDb().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Goods g = new Goods();
                g.setId(rs.getInt("id"));
                int mid = rs.getInt("member_id");
                g.setMemberId(rs.wasNull() ? null : mid);
                g.setCategoryName(rs.getString("category_name"));
                g.setProductName(rs.getString("product_name"));
                g.setUnitPrice(rs.getInt("unit_price"));
                g.setQuantity(rs.getInt("quantity"));
                g.setLineTotal(rs.getInt("line_total"));
                g.setOrderTotal(rs.getInt("order_total"));
                g.setPaidCash(rs.getInt("paid_cash"));
                g.setChangeAmount(rs.getInt("change_amount"));
                list.add(g);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
