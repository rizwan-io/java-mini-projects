package repository;

import model.Employee;

import java.sql.*;
import java.time.LocalDate;

public class EmployeeRepository {
    public static final String INSERT_EMPLOYEE_SQL = "INSERT INTO employee(first_name, last_name, date_of_birth) VALUES(?, ?, ?)";
    public static final String FIND_BY_ID_SQL = "SELECT * FROM employee WHERE employee_id = ?";
    public static final String UPDATE_EMPLOYEE_SQL = "UPDATE employee SET first_name = ?, last_name = ?, date_of_birth = ?";
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
                savedEmployee.setId(resultSet.getLong("employee_id"));
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

    public Employee findById(Long id) {
        Employee employee = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long employeeId = resultSet.getLong("employee_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                LocalDate dateOfBirth = resultSet.getDate("date_of_birth").toLocalDate();
                employee = new Employee(employeeId, firstName, lastName, dateOfBirth);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employee;
    }

    public Employee update(Employee employee) {
        Employee updatedEmployee = employee;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMPLOYEE_SQL);
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setDate(3, Date.valueOf(employee.getDateOfBirth()));

            int rowsAffected = preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                updatedEmployee.setFirstName(resultSet.getString("first_name"));
                updatedEmployee.setLastName(resultSet.getString("last_name"));
                updatedEmployee.setDateOfBirth(resultSet.getDate("date_of_birth").toLocalDate());
            }
            System.out.println("Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return updatedEmployee;
    }
}
