package repository;

import model.Employee;

import java.sql.*;

public class EmployeeRepository {
    public static final String INSERT_EMPLOYEE_SQL = "INSERT INTO employee(first_name, last_name, date_of_birth) VALUES(?, ?, ?)";
    private final Connection connection;
    public EmployeeRepository(Connection connection) {
        this.connection = connection;
    }

    public Employee save(Employee employee) {
        Employee savedEmployee = new Employee();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE_SQL, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setDate(3, Date.valueOf(employee.getDateOfBirth()));

            int rowsAffected = preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                savedEmployee.setFirstName(resultSet.getString("first_name"));
                savedEmployee.setLastName(resultSet.getString("last_name"));
                savedEmployee.setDateOfBirth(resultSet.getDate("date_of_birth").toLocalDate());
            }
            System.out.println("Rows Affected: " + rowsAffected);
            return savedEmployee;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
