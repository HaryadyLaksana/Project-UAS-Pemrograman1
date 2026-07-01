package view;

import java.util.List;
import utils.AppConfig;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dao.UserDAO;
import dao.TransactionDAO;
import dao.InvoiceDAO;

import model.User;
import model.Transaction;
import model.Invoice;

    public class DashboardUserView extends javax.swing.JFrame {

        private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(DashboardUserView.class.getName());

        private User currentUser;

        public DashboardUserView(User currentUser) {
            initComponents();
            AppConfig.apply(this);
            this.currentUser = currentUser;
            loadUserData();
            loadTransactionHistory();
            jTable1.getTableHeader().setReorderingAllowed(false);
        }

        private void loadUserData() {
            lblWelcome.setText("Selamat datang, " + currentUser.getFullName());
            lblStudentId.setText("NIM (" + currentUser.getStudentId() + ")");
            lblBalance.setText("Rp " + String.format("%,.2f", currentUser.getBalance()));
        }

        private void loadTransactionHistory() {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);
            TransactionDAO transactionDAO = new TransactionDAO();

            List<Transaction> transactions = transactionDAO.getUserTransactions(currentUser.getId());

            for (Transaction transaction : transactions) {
                model.addRow(new Object[]{
                    transaction.getTransactionDate(),
                    transaction.getTransactionType(),
                    String.format("Rp %,.2f",
                    transaction.getAmount())});
            }
        }

        private void showHistoryPembayaran() {

            TransactionDAO dao = new TransactionDAO();

            List<String> history = dao.getPaymentHistory(currentUser.getId());

            if (history.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Belum ada riwayat pembayaran.");
                return;
            }

            StringBuilder sb = new StringBuilder();

            sb.append("===== HISTORY PEMBAYARAN =====\n\n");

            int no = 1;

            for (String data : history) {

                sb.append(no++)
                        .append(".\n")
                        .append(data)
                        .append("\n\n");

            }

            JOptionPane.showMessageDialog(
                    this,
                    sb.toString(),
                    "History Pembayaran",
                    JOptionPane.INFORMATION_MESSAGE);
        }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblDashboardUser = new javax.swing.JLabel();
        lblWelcome = new javax.swing.JLabel();
        lblStudentId = new javax.swing.JLabel();
        lblBalanceTitle = new javax.swing.JLabel();
        lblBalance = new javax.swing.JLabel();
        btnTopUp = new javax.swing.JButton();
        btnTransfer = new javax.swing.JButton();
        btnPayment = new javax.swing.JButton();
        btnHistory = new javax.swing.JButton();
        btnProfile = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblDashboardUser.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        lblDashboardUser.setText("UNPAM E-WALLET");

        lblWelcome.setFont(new java.awt.Font("Serif", 0, 12)); // NOI18N
        lblWelcome.setText("Selamat datang, ");

        lblStudentId.setFont(new java.awt.Font("Serif", 0, 12)); // NOI18N
        lblStudentId.setText("NIM: ");

        lblBalanceTitle.setFont(new java.awt.Font("Serif", 0, 12)); // NOI18N
        lblBalanceTitle.setText("Saldo anda");

        lblBalance.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N

        btnTopUp.setText("Top Up");
        btnTopUp.addActionListener(this::btnTopUpActionPerformed);

        btnTransfer.setText("Transfer");
        btnTransfer.addActionListener(this::btnTransferActionPerformed);

        btnPayment.setText("Bayar");
        btnPayment.addActionListener(this::btnPaymentActionPerformed);

        btnHistory.setText("Riwayat");
        btnHistory.addActionListener(this::btnHistoryActionPerformed);

        btnProfile.setText("Profil");

        btnLogout.setText("Keluar");
        btnLogout.addActionListener(this::btnLogoutActionPerformed);

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/LOGO_UNPAM.png"))); // NOI18N
        jLabel1.setText("jLabel1");

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tanggal", "Jenis", "Nominal"
            }
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setText("Transaksi Terakhir");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(16, 16, 16)
                        .addComponent(jSeparator1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnProfile)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLogout))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblDashboardUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblWelcome)
                                    .addComponent(lblStudentId)
                                    .addComponent(lblBalanceTitle)
                                    .addComponent(lblBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnTopUp)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnTransfer)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnHistory)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(lblDashboardUser)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblWelcome)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblStudentId)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblBalanceTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(9, 9, 9)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTopUp)
                    .addComponent(btnTransfer)
                    .addComponent(btnPayment)
                    .addComponent(btnHistory))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLogout)
                    .addComponent(btnProfile))
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistoryActionPerformed
        showHistoryPembayaran();
    }//GEN-LAST:event_btnHistoryActionPerformed

    private void btnTopUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTopUpActionPerformed
        javax.swing.JTextField txtAmount = new javax.swing.JTextField();
        Object[] message = {"Masukkan Nominal Top Up", txtAmount};

        int option = javax.swing.JOptionPane.showConfirmDialog(
                this,
                message,
                "Top Up Saldo",
                javax.swing.JOptionPane.OK_CANCEL_OPTION,
                javax.swing.JOptionPane.PLAIN_MESSAGE);

        if (option != javax.swing.JOptionPane.OK_OPTION) {
            return;
        }

        String input = txtAmount.getText().trim();

        if (input.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Nominal tidak boleh kosong");
            return;
        }

        try {
            double amount = Double.parseDouble(input);

            if (amount <= 0) {
                javax.swing.JOptionPane.showMessageDialog(
                        this,
                        "Nominal harus lebih dari 0");
                return;
            }

            TransactionDAO transactionDAO = new TransactionDAO();
            boolean success = transactionDAO.topUp(currentUser.getId(), amount);

            if (success) {
                currentUser.setBalance(currentUser.getBalance() + amount);
                lblBalance.setText("Rp " + String.format("%,.2f", currentUser.getBalance()));
                loadTransactionHistory();
                JOptionPane.showMessageDialog(
                        this,
                        "Top Up Rp "
                        + String.format("%,.0f", amount)
                        + " berhasil");

            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Top Up gagal");
            }

        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Nominal tidak valid");
        }
    }//GEN-LAST:event_btnTopUpActionPerformed

    private void btnTransferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransferActionPerformed
        javax.swing.JTextField txtStudentId = new javax.swing.JTextField();
        javax.swing.JTextField txtAmount = new javax.swing.JTextField();
        javax.swing.JPasswordField txtPin = new javax.swing.JPasswordField();
        Object[] message = {
            "NIM Tujuan", txtStudentId,
            "Nominal Transfer", txtAmount,
            "PIN Transaksi", txtPin};

        int option = JOptionPane.showConfirmDialog(
                this,
                message,
                "Transfer Saldo",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (option != JOptionPane.OK_OPTION) {
            return;
        }

        try {
            String studentId = txtStudentId.getText().trim();
            String pin = new String(txtPin.getPassword()).trim();
            double amount = Double.parseDouble(txtAmount.getText().trim());

            if (studentId.isEmpty() || pin.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Semua field wajib diisi");
                return;
            }

            UserDAO userDAO = new UserDAO();
            User receiver = userDAO.findByStudentId(studentId);

            if (receiver == null) {
                JOptionPane.showMessageDialog(
                        this,
                        "NIM tujuan tidak ditemukan");
                return;
            }

            if (receiver.getId() == currentUser.getId()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Tidak bisa transfer ke diri sendiri");
                return;
            }

            if (!pin.equals(currentUser.getTransactionPin())) {
                JOptionPane.showMessageDialog(
                        this,
                        "PIN transaksi salah");
                return;
            }

            if (amount <= 0) {
                JOptionPane.showMessageDialog(
                        this,
                        "Nominal harus lebih dari 0");
                return;
            }

            if (currentUser.getBalance() < amount) {
                JOptionPane.showMessageDialog(
                        this,
                        "Saldo tidak mencukupi");
                return;
            }

            TransactionDAO transactionDAO = new TransactionDAO();
            boolean success = transactionDAO.transfer(currentUser.getId(), receiver.getId(), amount);

            if (success) {
                currentUser.setBalance(currentUser.getBalance() - amount);
                lblBalance.setText("Rp " + String.format("%,.2f", currentUser.getBalance()));
                loadTransactionHistory();
                JOptionPane.showMessageDialog(
                        this,
                        "Transfer berhasil ke "
                        + receiver.getFullName());

            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Transfer gagal");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Nominal tidak valid");
        }
    }//GEN-LAST:event_btnTransferActionPerformed

    private void btnPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPaymentActionPerformed
        InvoiceDAO invoiceDAO = new InvoiceDAO();
        Invoice invoice = invoiceDAO.getFirstUnpaidInvoice(currentUser.getId());

        if (invoice == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Tidak ada tagihan yang belum dibayar");
            return;
        }

        String message
                = "Jenis Tagihan : "
                + invoice.getInvoiceType()
                + "\nSemester : "
                + invoice.getSemester()
                + "\nTahun Akademik : "
                + invoice.getAcademicYear()
                + "\nNominal : Rp "
                + String.format("%,.2f", invoice.getAmount())
                + "\n\nBayar tagihan ini?";

        int confirm = JOptionPane.showConfirmDialog(
                this,
                message,
                "Pembayaran Tagihan",
                JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        boolean success = invoiceDAO.payInvoice(
                invoice.getId(),
                currentUser.getId(),
                invoice.getAmount());

        if (success) {
            currentUser.setBalance(currentUser.getBalance() - invoice.getAmount());
            lblBalance.setText("Rp " + String.format("%,.2f", currentUser.getBalance()));
            loadTransactionHistory();
            JOptionPane.showMessageDialog(
                    this,
                    "Pembayaran berhasil");

        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "Saldo tidak mencukupi");
        }
    }//GEN-LAST:event_btnPaymentActionPerformed

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

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHistory;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnPayment;
    private javax.swing.JButton btnProfile;
    private javax.swing.JButton btnTopUp;
    private javax.swing.JButton btnTransfer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblBalance;
    private javax.swing.JLabel lblBalanceTitle;
    private javax.swing.JLabel lblDashboardUser;
    private javax.swing.JLabel lblStudentId;
    private javax.swing.JLabel lblWelcome;
    // End of variables declaration//GEN-END:variables
}
