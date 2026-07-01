package dao;

import config.Koneksi;

import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.User;

public class UserDAO {

    private Connection connection = Koneksi.getConnection();

    public User login(String username, String password) {
        User user = null;

        try {
            String sql = "SELECT * FROM users " + "WHERE username = ? " + "AND password = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setFullName(rs.getString("full_name"));
                user.setStudentId(rs.getString("student_id"));
                user.setEmail(rs.getString("email"));
                user.setPhoneNumber(rs.getString("phone_number"));
                user.setTransactionPin(rs.getString("transaction_pin"));
                user.setRole(rs.getString("role"));
                user.setBalance(rs.getDouble("balance"));
                updateLastLogin(rs.getInt("id"));
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return user;
    }

    public boolean register(User user) {
        try {
            if (isUsernameExists(user.getUsername())) {
                return false;
            }

            if (isStudentIdExists(user.getStudentId())) {
                return false;
            }

            if (isEmailExists(user.getEmail())) {
                return false;
            }

            String sql
                    = "INSERT INTO users "
                    + "(username,password,"
                    + "full_name,student_id,"
                    + "email,phone_number,"
                    + "transaction_pin,role,balance)"
                    + " VALUES "
                    + "(?,?,?,?,?,?,?,?,?)";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFullName());
            ps.setString(4, user.getStudentId());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getPhoneNumber());
            ps.setString(7, user.getTransactionPin());
            ps.setString(8, "user");
            ps.setDouble(9, 0);
            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean isUsernameExists(String username) {
        try {
            String sql = "SELECT id FROM users " + "WHERE username = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            return rs.next();
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return false;
    }

    public boolean isStudentIdExists(String studentId) {
        try {
            String sql = "SELECT id FROM users " + "WHERE student_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, studentId);
            ResultSet rs = ps.executeQuery();
            return rs.next();
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return false;
    }

    public boolean isEmailExists(String email) {
        try {
            String sql = "SELECT id FROM users " + "WHERE email = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            System.out.println(e);
        }
        
        return false;
    }

    public User findByStudentId(String studentId) {
        User user = null;

        try {
            String sql = "SELECT * FROM users "+ "WHERE student_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, studentId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setFullName(rs.getString("full_name"));
                user.setStudentId(rs.getString("student_id"));
                user.setEmail(rs.getString("email"));
                user.setPhoneNumber(rs.getString("phone_number"));
                user.setTransactionPin(rs.getString("transaction_pin"));
                user.setRole(rs.getString("role"));
                user.setBalance(rs.getDouble("balance"));
            }

        } catch (Exception e) {
            System.out.println(e);

        }

        return user;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try {
            String sql = "SELECT * FROM users " + "ORDER BY full_name";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setStudentId(rs.getString("student_id"));
                user.setFullName(rs.getString("full_name"));
                user.setUsername(rs.getString("username"));
                user.setBalance(rs.getDouble("balance"));
                users.add(user);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return users;
    }

    public void updateLastLogin(int userId) {
        try {
            String sql = "UPDATE users " + "SET last_login = NOW() " + "WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.executeUpdate();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<User> searchUsers(String keyword) {
        List<User> users = new ArrayList<>();

        try {
            String sql
                    = "SELECT * FROM users "
                    + "WHERE student_id LIKE ? "
                    + "OR full_name LIKE ? "
                    + "OR username LIKE ? "
                    + "ORDER BY full_name";

            PreparedStatement ps = connection.prepareStatement(sql);
            String search = "%" + keyword + "%";

            ps.setString(1, search);
            ps.setString(2, search);
            ps.setString(3, search);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setStudentId(rs.getString("student_id"));
                user.setFullName(rs.getString("full_name"));
                user.setUsername(rs.getString("username"));
                user.setBalance(rs.getDouble("balance"));
                users.add(user);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return users;
    }
}