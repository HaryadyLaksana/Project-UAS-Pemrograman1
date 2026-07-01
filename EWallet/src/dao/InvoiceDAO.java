package dao;

import config.Koneksi;

import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Invoice;

public class InvoiceDAO {

    private Connection connection = Koneksi.getConnection();

    public boolean addInvoice(Invoice invoice) {
        try {
            String sql
                    = "INSERT INTO invoices "
                    + "("
                    + "user_id,"
                    + "invoice_type,"
                    + "semester,"
                    + "academic_year,"
                    + "amount,"
                    + "due_date"
                    + ") "
                    + "VALUES "
                    + "(?,?,?,?,?,?)";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, invoice.getUserId());
            ps.setString(2, invoice.getInvoiceType());
            ps.setString(3, invoice.getSemester());
            ps.setString(4, invoice.getAcademicYear());
            ps.setDouble(5, invoice.getAmount());
            ps.setDate(6, invoice.getDueDate());
            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public List<Invoice> getUserInvoices(int userId) {
        List<Invoice> invoices = new ArrayList<>();

        try {
            String sql
                    = "SELECT * FROM invoices "
                    + "WHERE user_id = ? "
                    + "ORDER BY created_at DESC";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Invoice invoice = new Invoice();
                invoice.setId(rs.getInt("id"));
                invoice.setUserId(rs.getInt("user_id"));
                invoice.setInvoiceType(rs.getString("invoice_type"));
                invoice.setSemester(rs.getString("semester"));
                invoice.setAcademicYear(rs.getString("academic_year"));
                invoice.setAmount(rs.getDouble("amount"));
                invoice.setInvoiceStatus(rs.getString("invoice_status"));
                invoice.setDueDate(rs.getDate("due_date"));
                invoices.add(invoice);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return invoices;
    }

    public boolean payInvoice(
            int invoiceId,
            int userId,
            double amount) {

        try {
            connection.setAutoCommit(false);
            String checkSql
                    = "SELECT balance "
                    + "FROM users "
                    + "WHERE id = ?";

            PreparedStatement psCheck = connection.prepareStatement(checkSql);
            psCheck.setInt(1, userId);
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
            psDeduct.setInt(2, userId);
            psDeduct.executeUpdate();
            
            String invoiceSql
                    = "UPDATE invoices "
                    + "SET invoice_status = 'paid' "
                    + "WHERE id = ?";

            PreparedStatement psInvoice = connection.prepareStatement(invoiceSql);
            psInvoice.setInt(1, invoiceId);
            psInvoice.executeUpdate();

            String transactionSql
                    = "INSERT INTO transactions "
                    + "("
                    + "sender_id,"
                    + "receiver_id,"
                    + "transaction_type,"
                    + "transaction_status,"
                    + "amount"
                    + ") "
                    + "VALUES "
                    + "(?,?,?,?,?)";

            PreparedStatement psTransaction = connection.prepareStatement(transactionSql);
            psTransaction.setInt(1, userId);
            psTransaction.setNull(2, java.sql.Types.INTEGER);
            psTransaction.setString(3, "payment");
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

    public Invoice getFirstUnpaidInvoice(int userId) {
        Invoice invoice = null;

        try {
            String sql
                    = "SELECT * FROM invoices "
                    + "WHERE user_id = ? "
                    + "AND invoice_status = 'unpaid' "
                    + "ORDER BY due_date ASC "
                    + "LIMIT 1";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                invoice = new Invoice();
                invoice.setId(rs.getInt("id"));
                invoice.setUserId(rs.getInt("user_id"));
                invoice.setInvoiceType(rs.getString("invoice_type"));
                invoice.setSemester(rs.getString("semester"));
                invoice.setAcademicYear(rs.getString("academic_year"));
                invoice.setAmount(rs.getDouble("amount"));
                invoice.setInvoiceStatus(rs.getString("invoice_status"));
                invoice.setDueDate(rs.getDate("due_date"));
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return invoice;
    }
}
