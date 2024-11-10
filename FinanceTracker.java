import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.*;
import java.util.*;

public class FinanceTracker {
    
    // JDBC URL, username, and password for MySQL
    private static final String DB_URL = "jdbc:mysql://localhost:3306/FinanceTracker";
    private static final String USER = "root";  // Change this according to your MySQL username
    private static final String PASSWORD = "root";  // Change this according to your MySQL password

    // GUI components
    private JFrame frame;
    private JTextField descriptionField;
    private JTextField amountField;
    private JButton saveButton;
    private JTable transactionTable;
    private DefaultTableModel tableModel;

    public static void main(String[] args) {
        // Run the UI on the Event Dispatch Thread for thread safety
        SwingUtilities.invokeLater(() -> new FinanceTracker().createAndShowGUI());
    }

    public FinanceTracker() {
        frame = new JFrame("Personal Finance Tracker");
        descriptionField = new JTextField(20);
        amountField = new JTextField(20);
        saveButton = new JButton("Save Transaction");
        tableModel = new DefaultTableModel(new Object[]{"ID", "Description", "Amount", "Date"}, 0);
        transactionTable = new JTable(tableModel);
    }

    public void createAndShowGUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Top panel for inputs
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.add(new JLabel("Description:"));
        inputPanel.add(descriptionField);
        inputPanel.add(new JLabel("Amount:"));
        inputPanel.add(amountField);
        inputPanel.add(saveButton);
        frame.add(inputPanel, BorderLayout.NORTH);

        // Center panel for displaying transactions
        JScrollPane tableScrollPane = new JScrollPane(transactionTable);
        frame.add(tableScrollPane, BorderLayout.CENTER);

        // Load existing transactions from database
        loadTransactions();

        // Add Action Listener for the Save Button
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveTransaction();
            }
        });

        frame.setSize(600, 400);
        frame.setVisible(true);
    }

    private void loadTransactions() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            stmt = conn.createStatement();
            String query = "SELECT * FROM transactions";
            rs = stmt.executeQuery(query);

            // Clear existing rows in the table
            tableModel.setRowCount(0);

            while (rs.next()) {
                int id = rs.getInt("id");
                String description = rs.getString("description");
                double amount = rs.getDouble("amount");
                Date date = rs.getDate("date");

                // Add transaction to the table
                tableModel.addRow(new Object[]{id, description, amount, date});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void saveTransaction() {
        String description = descriptionField.getText();
        String amountText = amountField.getText();

        if (description.isEmpty() || amountText.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please fill in both fields.");
            return;
        }

        try {
            double amount = Double.parseDouble(amountText);

            // Get current date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String currentDate = sdf.format(new Date());

            // Insert the transaction into the database
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            String query = "INSERT INTO transactions (description, amount, date) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, description);
            pstmt.setDouble(2, amount);
            pstmt.setString(3, currentDate);
            pstmt.executeUpdate();

            // Clear the input fields
            descriptionField.setText("");
            amountField.setText("");

            // Reload transactions to update the table
            loadTransactions();

            JOptionPane.showMessageDialog(frame, "Transaction saved successfully!");

        } catch (NumberFormatException | SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Error saving transaction: " + ex.getMessage());
        }
    }
}
