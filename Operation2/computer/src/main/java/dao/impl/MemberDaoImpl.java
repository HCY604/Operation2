package dao.impl;

import model.Member;
import util.DbConnection;

import java.sql.*;

import dao.MemberDao;

public class MemberDaoImpl implements MemberDao {

    @Override
    public void insert(Member m) {
        String sql = "insert into member(name,username,password,address,phone) values(?,?,?,?,?)";
        try {
            PreparedStatement ps = DbConnection.getDb().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, m.getName());
            ps.setString(2, m.getUsername());
            ps.setString(3, m.getPassword()); 
            ps.setString(4, m.getAddress());
            ps.setString(5, m.getPhone());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) m.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Member login(String username, String password) {
        String sql = "select id,name,username,password,address,phone from member where username=? and password=?";
        try {
            PreparedStatement ps = DbConnection.getDb().prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Member m = new Member();
                m.setId(rs.getInt("id"));
                m.setName(rs.getString("name"));
                m.setUsername(rs.getString("username"));
                m.setPassword(rs.getString("password"));
                m.setAddress(rs.getString("address"));
                m.setPhone(rs.getString("phone"));
                return m;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // 查無即登入失敗
    }
}
