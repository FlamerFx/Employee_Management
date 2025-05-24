import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class EmployeeManagementSystem extends JFrame {
    private JTextField tfId, tfName, tfPosition, tfSalary;
    private JTable table;
    private DefaultTableModel tableModel;

    public EmployeeManagementSystem() {
        setTitle("Employee Management System");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initUI();
        loadEmployees();
    }

    private void initUI() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel lblId = new JLabel("ID:");
        JLabel lblName = new JLabel("Name:");
        JLabel lblPosition = new JLabel("Position:");
        JLabel lblSalary = new JLabel("Salary:");

        tfId = new JTextField(10);
        tfName = new JTextField(10);
        tfPosition = new JTextField(10);
        tfSalary = new JTextField(10);

        JButton btnAdd = new JButton("Add");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");
        JButton btnRefresh = new JButton("Refresh");

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0; gbc.gridy = 0; panel.add(lblId, gbc);
        gbc.gridx = 1; gbc.gridy = 0; panel.add(tfId, gbc);

        gbc.gridx = 0; gbc.gridy = 1; panel.add(lblName, gbc);
        gbc.gridx = 1; gbc.gridy = 1; panel.add(tfName, gbc);

        gbc.gridx = 0; gbc.gridy = 2; panel.add(lblPosition, gbc);
        gbc.gridx = 1; gbc.gridy = 2; panel.add(tfPosition, gbc);

        gbc.gridx = 0; gbc.gridy = 3; panel.add(lblSalary, gbc);
        gbc.gridx = 1; gbc.gridy = 3; panel.add(tfSalary, gbc);

        gbc.gridx = 0; gbc.gridy = 4; panel.add(btnAdd, gbc);
        gbc.gridx = 1; gbc.gridy = 4; panel.add(btnUpdate, gbc);
        gbc.gridx = 0; gbc.gridy = 5; panel.add(btnDelete, gbc);
        gbc.gridx = 1; gbc.gridy = 5; panel.add(btnRefresh, gbc);

        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Position", "Salary"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        getContentPane().add(panel, BorderLayout.WEST);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        btnAdd.addActionListener(e -> addEmployee());
        btnUpdate.addActionListener(e -> updateEmployee());
        btnDelete.addActionListener(e -> deleteEmployee());
        btnRefresh.addActionListener(e -> loadEmployees());
    }

    private void addEmployee() {
        String name = tfName.getText().trim();
        String position = tfPosition.getText().trim();
        String salaryStr = tfSalary.getText().trim();

        if (name.isEmpty() || position.isEmpty() || !salaryStr.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Enter valid name, position, and numeric salary.");
            return;
        }

        Employee emp = new Employee(name, position, Integer.parseInt(salaryStr));
        if (EmployeeDAO.addEmployee(emp)) {
            JOptionPane.showMessageDialog(this, "Employee added successfully.");
            loadEmployees();
        }
    }

    private void updateEmployee() {
        String idStr = tfId.getText().trim();
        String name = tfName.getText().trim();
        String position = tfPosition.getText().trim();
        String salaryStr = tfSalary.getText().trim();

        if (!idStr.matches("\\d+") || name.isEmpty() || position.isEmpty() || !salaryStr.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Enter valid ID, name, position, and salary.");
            return;
        }

        Employee emp = new Employee(Integer.parseInt(idStr), name, position, Integer.parseInt(salaryStr));
        if (EmployeeDAO.updateEmployee(emp)) {
            JOptionPane.showMessageDialog(this, "Employee updated successfully.");
            loadEmployees();
        }
    }

    private void deleteEmployee() {
        String idStr = tfId.getText().trim();
        if (!idStr.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Enter valid numeric ID.");
            return;
        }

        int id = Integer.parseInt(idStr);
        if (EmployeeDAO.deleteEmployee(id)) {
            JOptionPane.showMessageDialog(this, "Employee deleted successfully.");
            loadEmployees();
        }
    }

    private void loadEmployees() {
        tableModel.setRowCount(0);
        List<Employee> list = EmployeeDAO.getAllEmployees();
        for (Employee emp : list) {
            tableModel.addRow(new Object[]{emp.getId(), emp.getName(), emp.getPosition(), emp.getSalary()});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EmployeeManagementSystem().setVisible(true));
    }
}
