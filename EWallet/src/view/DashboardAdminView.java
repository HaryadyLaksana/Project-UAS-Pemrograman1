package view;

import java.util.List;
import utils.AppConfig;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dao.UserDAO;
import dao.TransactionDAO;
import dao.InvoiceDAO;

import model.User;
import model.Invoice;

public class DashboardAdminView extends javax.swing.JFrame {

    private void loadUsers(String keyword) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        UserDAO userDAO = new UserDAO();

        List<User> users = userDAO.searchUsers(keyword);

        for (User user : users) {
            model.addRow(new Object[]{
                user.getId(),
                user.getStudentId(),
                user.getFullName(),
                user.getUsername(),
                String.format("Rp %,.2f", user.getBalance())});
        }
    }

    private void loadTransactions(String keyword) {
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0);
        TransactionDAO transactionDAO = new TransactionDAO();

        List<Object[]> transactions = transactionDAO.searchTransactions(keyword);

        for (Object[] row : transactions) {
            model.addRow(new Object[]{
                row[0],
                row[1],
                row[2] == null ? "-" : row[2],
                row[3],
                String.format("Rp %,.2f",
                (Double) row[4])});
        }
    }

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(DashboardAdminView.class.getName());

    public DashboardAdminView() {
        initComponents();
        AppConfig.apply(this);
        loadUsers("");
        loadTransactions("");
        
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(130);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(180);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(120);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(120);
        
        jTable2.getTableHeader().setReorderingAllowed(false);
        jTable2.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable2.getColumnModel().getColumn(0).setPreferredWidth(150);
        jTable2.getColumnModel().getColumn(1).setPreferredWidth(150);
        jTable2.getColumnModel().getColumn(2).setPreferredWidth(150);
        jTable2.getColumnModel().getColumn(3).setPreferredWidth(100);
        jTable2.getColumnModel().getColumn(4).setPreferredWidth(150);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblDashboardUser = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblWelcome = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnTopUp = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnManageInvoice = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        lblWelcome1 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txtSearchUser = new javax.swing.JTextField();
        txtSearchTransaction = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblDashboardUser.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        lblDashboardUser.setText("UNPAM E-WALLET");

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        lblWelcome.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        lblWelcome.setText("ADMIN PANEL");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/LOGO_UNPAM.png"))); // NOI18N
        jLabel1.setText("jLabel1");

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "ID", "NIM", "Nama", "Username", "Saldo"
            }
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        jScrollPane1.setViewportView(jTable1);

        btnTopUp.setText("Kelola User");

        jLabel2.setText("Data User");

        btnManageInvoice.setText("Kelola Tagihan");
        btnManageInvoice.addActionListener(this::btnManageInvoiceActionPerformed);

        btnRefresh.setText("Perbaharui");
        btnRefresh.addActionListener(this::btnRefreshActionPerformed);

        btnLogout.setText("Keluar");
        btnLogout.addActionListener(this::btnLogoutActionPerformed);

        lblWelcome1.setFont(new java.awt.Font("Serif", 0, 12)); // NOI18N
        lblWelcome1.setText("Selamat datang, Administrator.");

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Tanggal", "Pengirim", "Penerima", "Jenis", "Nominal"
            }
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jLabel3.setText("Data Transaksi");

        txtSearchUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchUserKeyReleased(evt);
            }
        });

        txtSearchTransaction.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchTransactionKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDashboardUser)
                                    .addComponent(lblWelcome)
                                    .addComponent(lblWelcome1)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnTopUp)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnManageInvoice)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRefresh)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLogout)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSearchUser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSearchTransaction, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(lblDashboardUser)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblWelcome)
                        .addGap(40, 40, 40)
                        .addComponent(lblWelcome1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTopUp)
                    .addComponent(btnManageInvoice)
                    .addComponent(btnRefresh)
                    .addComponent(btnLogout))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtSearchUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtSearchTransaction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        int confirm = JOptionPane.showConfirmDialog(
                        this,
                        "Yakin ingin keluar?",
                        "Konfirmasi",
                        JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            new LoginView().setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void txtSearchUserKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchUserKeyReleased
        loadUsers(txtSearchUser.getText().trim());
    }//GEN-LAST:event_txtSearchUserKeyReleased

    private void txtSearchTransactionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchTransactionKeyReleased
        loadTransactions(txtSearchTransaction.getText().trim());
    }//GEN-LAST:event_txtSearchTransactionKeyReleased

    private void btnManageInvoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageInvoiceActionPerformed
        javax.swing.JTextField txtStudentId = new javax.swing.JTextField();
        javax.swing.JTextField txtSemester = new javax.swing.JTextField();
        javax.swing.JTextField txtAcademicYear = new javax.swing.JTextField();
        javax.swing.JTextField txtAmount = new javax.swing.JTextField();
        javax.swing.JTextField txtDueDate = new javax.swing.JTextField();
        javax.swing.JComboBox<String> cmbType = new javax.swing.JComboBox<>(
                        new String[]{
                            "UKT",
                            "Praktikum",
                            "Seminar",
                            "Wisuda"});

        Object[] message = {
            "NIM Mahasiswa",
            txtStudentId,
            "Jenis Tagihan",
            cmbType,
            "Semester",
            txtSemester,
            "Tahun Akademik",
            txtAcademicYear,
            "Nominal",
            txtAmount,
            "Jatuh Tempo (yyyy-mm-dd)",
            txtDueDate};

        int option = javax.swing.JOptionPane.showConfirmDialog(
                        this,
                        message,
                        "Tambah Tagihan",
                        javax.swing.JOptionPane.OK_CANCEL_OPTION,
                        javax.swing.JOptionPane.PLAIN_MESSAGE);

        if (option != javax.swing.JOptionPane.OK_OPTION) {
            return;
        }

        try {
            UserDAO userDAO = new UserDAO();
            User user = userDAO.findByStudentId(txtStudentId.getText().trim());

            if (user == null) {
                javax.swing.JOptionPane.showMessageDialog(
                        this,
                        "NIM tidak ditemukan");
                return;
            }

            Invoice invoice = new Invoice();
            invoice.setUserId(user.getId());
            invoice.setInvoiceType(cmbType.getSelectedItem().toString());
            invoice.setSemester(txtSemester.getText().trim());
            invoice.setAcademicYear(txtAcademicYear.getText().trim());
            invoice.setAmount(Double.parseDouble(txtAmount.getText().trim()));
            invoice.setDueDate(java.sql.Date.valueOf(txtDueDate.getText().trim()));
            InvoiceDAO invoiceDAO = new InvoiceDAO();
            boolean success = invoiceDAO.addInvoice(invoice);

            if (success) {
                javax.swing.JOptionPane.showMessageDialog(
                        this,
                        "Tagihan berhasil dibuat");
            } else {
                javax.swing.JOptionPane.showMessageDialog(
                        this,
                        "Tagihan gagal dibuat");
            }

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Data tidak valid");
        }
    }//GEN-LAST:event_btnManageInvoiceActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        loadUsers("");
        loadTransactions("");
        txtSearchUser.setText("");
        txtSearchTransaction.setText("");
        JOptionPane.showMessageDialog(
                this,
                "Data berhasil diperbaharui");
    }//GEN-LAST:event_btnRefreshActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new DashboardAdminView().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnManageInvoice;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnTopUp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel lblDashboardUser;
    private javax.swing.JLabel lblWelcome;
    private javax.swing.JLabel lblWelcome1;
    private javax.swing.JTextField txtSearchTransaction;
    private javax.swing.JTextField txtSearchUser;
    // End of variables declaration//GEN-END:variables
}
