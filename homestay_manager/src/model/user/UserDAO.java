package model.user;

import model.IDAO;
import model.user.User;
import utils.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IDAO<User> {
    private DBConnection connection;

    public UserDAO(DBConnection connection) {
        this.connection = connection;
    }

    @Override
    public List<User> getAllList() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try {
            Statement statement = this.connection.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String password = rs.getString(3);
                int role = rs.getInt(4);
                int customerNumber = rs.getInt(5);
                User user = new User(id,name,password,role,customerNumber);

                users.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Lỗi thực thi câu lệnh SQL");
        }

        return users;
    }

    public User getById(int id){
        String sql = "SELECT username, password, role FROM users WHERE id = ?";
        try {
            PreparedStatement ps = this.connection.getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String u = rs.getString(1);
                String p = rs.getString(2);
                int role = rs.getInt(3);

                return new User(u, p, role);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi thực thi lệnh SQL SELECT");
        }
        return null;
    }

    public User getByUsername(String username){
        String sql = "SELECT username, password, role FROM users WHERE username = ?";
        try {
            PreparedStatement ps = this.connection.getConnection().prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String u = rs.getString(1);
                String p = rs.getString(2);
                int role = rs.getInt(3);

                return new User(u, p, role);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi thực thi lệnh SQL SELECT");
        }
        return null;
    }

    @Override
    public void save(User user) {
        String sql = "INSERT INTO users (username,password,role,customernumber) VALUES (?,?,?,?)";

        try {
            PreparedStatement ps = this.connection.getConnection().prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2,user.getPassword());
            ps.setInt(3,user.getRole());
            ps.setInt(4,user.getCustomerNumber());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi thực thi lệnh SQL Insert Into");
        }
    }

    @Override
    public void deleteByid(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try {
            PreparedStatement ps = this.connection.getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi thực thi lệnh SQL DELETE");
        }
    }

    //=====================================


    @Override
    public void update(int id, User user) {

    }

    @Override
    public User findById(int id) {
        return null;
    }
}
