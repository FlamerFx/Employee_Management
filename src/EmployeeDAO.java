import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/your_database?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "Sitanshu@2006";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static boolean addEmployee(Employee emp) {
        String sql = "INSERT INTO employee (name, position, salary) VALUES (?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, emp.getName());
            ps.setString(2, emp.getPosition());
            ps.setInt(3, emp.getSalary());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error adding employee: " + e.getMessage());
            return false;
        }
    }

    public static boolean updateEmployee(Employee emp) {
        String sql = "UPDATE employee SET name=?, position=?, salary=? WHERE id=?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, emp.getName());
            ps.setString(2, emp.getPosition());
            ps.setInt(3, emp.getSalary());
            ps.setInt(4, emp.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating employee: " + e.getMessage());
            return false;
        }
    }

    public static boolean deleteEmployee(int id) {
        String sql = "DELETE FROM employee WHERE id=?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting employee: " + e.getMessage());
            return false;
        }
    }

    public static List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employee";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Employee emp = new Employee(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("position"),
                    rs.getInt("salary")
                );
                list.add(emp);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving employees: " + e.getMessage());
        }
        return list;
    }
}
