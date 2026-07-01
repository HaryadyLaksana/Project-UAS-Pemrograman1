package dao;

import config.Koneksi;

import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Transaction;

/**
 *
 * @author Rafi Kamaly
 */
public class TransactionDAO {

    private Connection connection = Koneksi.getConnection();

    public boolean topUp(int userId, double amount) {
        try {
            connection.setAutoCommit(false);
            String updateBalanceSql
                    = "UPDATE users "
                    + "SET balance = balance + ? "
                    + "WHERE id = ?";

            PreparedStatement psBalance = connection.prepareStatement(updateBalanceSql);
            psBalance.setDouble(1, amount);
            psBalance.setInt(2, userId);
            int balanceUpdated = psBalance.executeUpdate();

            if (balanceUpdated == 0) {
                connection.rollback();
                return false;
            }

            String insertTransactionSql
                    = "INSERT INTO transactions "
                    + "(sender_id,"
                    + "receiver_id,"
                    + "transaction_type,"
                    + "transaction_status,"
                    + "amount) "
                    + "VALUES "
                    + "(?,?,?,?,?)";

            PreparedStatement psTransaction = connection.prepareStatement(insertTransactionSql);
            psTransaction.setInt(1, userId);
            psTransaction.setNull(2, java.sql.Types.INTEGER);
            psTransaction.setString(3, "topup");
            psTransaction.setString(4, "success");
            psTransaction.setDouble(5, amount);
            psTransaction.executeUpdate();
            connection.commit();
            return true;

        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (Exception ex) {
            }
            System.out.println(e);
            return false;

        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (Exception e) {
            }
        }
    }

    public boolean transfer(
            int senderId,
            int receiverId,
            double amount) {

        try {
            connection.setAutoCommit(false);
            String checkBalanceSql
                    = "SELECT balance "
                    + "FROM users "
                    + "WHERE id = ?";

            PreparedStatement psCheck = connection.prepareStatement(checkBalanceSql);
            psCheck.setInt(1, senderId);
            ResultSet rs = psCheck.executeQuery();

            if (!rs.next()) {
                connection.rollback();
                return false;
            }

            double balance = rs.getDouble("balance");

            if (balance < amount) {
                connection.rollback();
                return false;
            }

            String deductSql
                    = "UPDATE users "
                    + "SET balance = balance - ? "
                    + "WHERE id = ?";

            PreparedStatement psDeduct = connection.prepareStatement(deductSql);
            psDeduct.setDouble(1, amount);
            psDeduct.setInt(2, senderId);
            psDeduct.executeUpdate();

            String addSql
                    = "UPDATE users "
                    + "SET balance = balance + ? "
                    + "WHERE id = ?";

            PreparedStatement psAdd = connection.prepareStatement(addSql);
            psAdd.setDouble(1, amount);
            psAdd.setInt(2, receiverId);
            psAdd.executeUpdate();

            String trxSql
                    = "INSERT INTO transactions "
                    + "(sender_id,"
                    + "receiver_id,"
                    + "transaction_type,"
                    + "transaction_status,"
                    + "amount)"
                    + " VALUES "
                    + "(?,?,?,?,?)";

            PreparedStatement psTrx = connection.prepareStatement(trxSql);
            psTrx.setInt(1, senderId);
            psTrx.setInt(2, receiverId);
            psTrx.setString(3, "transfer");
            psTrx.setString(4, "success");
            psTrx.setDouble(5, amount);
            psTrx.executeUpdate();
            connection.commit();
            return true;

        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (Exception ex) {
            }
            System.out.println(e);
            return false;

        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (Exception e) {
            }
        }
    }

    public List<Transaction> getUserTransactions(int userId) {
        List<Transaction> transactions = new ArrayList<>();

        try {
            String sql
                    = "SELECT * FROM transactions "
                    + "WHERE sender_id = ? "
                    + "ORDER BY transaction_date DESC";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Transaction transaction = new Transaction();
                transaction.setId(rs.getInt("id"));
                transaction.setSenderId(rs.getInt("sender_id"));
                transaction.setTransactionType(rs.getString("transaction_type"));
                transaction.setTransactionStatus(rs.getString("transaction_status"));
                transaction.setAmount(rs.getDouble("amount"));
                transaction.setTransactionDate(rs.getTimestamp("transaction_date"));
                transactions.add(transaction);
            }

        } catch (Exception e) {
            System.out.println(e);

        }

        return transactions;
    }

    public List<Object[]> getAllTransactions() {
        List<Object[]> transactions = new ArrayList<>();

        try {
            String sql
                    = "SELECT "
                    + "t.transaction_date,"
                    + "s.full_name AS sender_name,"
                    + "r.full_name AS receiver_name,"
                    + "t.transaction_type,"
                    + "t.amount "
                    + "FROM transactions t "
                    + "JOIN users s "
                    + "ON t.sender_id = s.id "
                    + "LEFT JOIN users r "
                    + "ON t.receiver_id = r.id "
                    + "ORDER BY t.transaction_date DESC";

            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                transactions.add(new Object[]{
                    rs.getTimestamp(
                    "transaction_date"),
                    rs.getString(
                    "sender_name"),
                    rs.getString(
                    "receiver_name"),
                    rs.getString(
                    "transaction_type"),
                    rs.getDouble(
                    "amount")});
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return transactions;
    }

    public List<Object[]> searchTransactions(String keyword) {
        List<Object[]> transactions = new ArrayList<>();

        try {
            String sql
                    = "SELECT "
                    + "t.transaction_date, "
                    + "s.full_name AS sender_name, "
                    + "r.full_name AS receiver_name, "
                    + "t.transaction_type, "
                    + "t.amount "
                    + "FROM transactions t "
                    + "JOIN users s "
                    + "ON t.sender_id = s.id "
                    + "LEFT JOIN users r "
                    + "ON t.receiver_id = r.id "
                    + "WHERE "
                    + "s.full_name LIKE ? "
                    + "OR r.full_name LIKE ? "
                    + "OR t.transaction_type LIKE ? "
                    + "OR CAST(t.transaction_date AS CHAR) LIKE ? "
                    + "ORDER BY t.transaction_date DESC";

            PreparedStatement ps = connection.prepareStatement(sql);
            String search = "%" + keyword + "%";
            ps.setString(1, search);
            ps.setString(2, search);
            ps.setString(3, search);
            ps.setString(4, search);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                transactions.add(new Object[]{
                    rs.getTimestamp(
                    "transaction_date"),
                    rs.getString(
                    "sender_name"),
                    rs.getString(
                    "receiver_name"),
                    rs.getString(
                    "transaction_type"),
                    rs.getDouble(
                    "amount")});
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return transactions;
    }

    public List<String> getPaymentHistory(int userId) {

        List<String> list = new ArrayList<>();

        String sql
                = "SELECT transaction_date, amount, transaction_status "
                + "FROM transactions "
                + "WHERE sender_id=? "
                + "AND transaction_type='payment' "
                + "ORDER BY transaction_date DESC";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                String data
                        = "Tanggal : " + rs.getTimestamp("transaction_date")
                        + "\nNominal : Rp "
                        + String.format("%,.2f", rs.getDouble("amount"))
                        + "\nStatus : "
                        + rs.getString("transaction_status");

                list.add(data);

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return list;
    }
}
