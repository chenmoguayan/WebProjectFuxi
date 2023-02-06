package org.qingqiao.dao;

import org.qingqiao.bean.User;
import org.qingqiao.utils.JDBCUtil;
import org.qingqiao.utils.PageUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Administrator
 */
public class UserDao {
    Connection conn = JDBCUtil.getConn();

    public ArrayList<User> queryAll(PageUtil pageUtil, String mohu) {
        ArrayList<User> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from user where username like ? limit ?,?");
            ps.setString(1,"%"+mohu+"%");
            ps.setInt(2, pageUtil.getStartPage());
            ps.setInt(3, pageUtil.getPageCount());
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                list.add(new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public User login(String username,String password){
        User user = null;
        try {
            PreparedStatement ps = conn.prepareStatement("select * from user where username = ? and password = ?");
            ps.setString(1,username);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
    public User queryById(int id){
        User user = new User();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from user where id = ?");
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                user = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
    public int update(User user) {
        int i;
        try {
            PreparedStatement ps = conn.prepareStatement("update user set username = ?,password = ?,sex = ?,birthday = ?,address = ?,hobby = ? where id=?");
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getSex());
            ps.setString(4, user.getBirthday());
            ps.setString(5, user.getAddress());
            ps.setString(6, user.getHobby());
            ps.setInt(7, user.getId());
            i = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return i;
    }
    public int insert(User user){
        int i;
        try {
            PreparedStatement ps = conn.prepareStatement("insert into user values(null,?,?,?,?,?,?)");
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getSex());
            ps.setString(4, user.getBirthday());
            ps.setString(5, user.getAddress());
            ps.setString(6, user.getHobby());
            i = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return i;
    }
    public int delete(int id) {
        int i;
        try {
            PreparedStatement ps = conn.prepareStatement("delete from user where id = ?");
            ps.setInt(1, id);
            i = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return i;
    }

    public int count(String mohu) {
        int i = 0;
        try {
            PreparedStatement ps = conn.prepareStatement("select count(*) from user where user.username like ?");
            ps.setString(1,"%"+mohu+"%");
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                i = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return i;
    }
}
