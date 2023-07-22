package repository;

import model.Employee;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeRepositoryTest {

    @Test
    public void testSaveOneEmployee() throws SQLException {
        Employee employee = new Employee("John", "Doe", LocalDate.of(1999, 9, 9));

        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/employee","rizwan", "admin");
        connection.setAutoCommit(false);

        EmployeeRepository repository = new EmployeeRepository(connection);
        Employee savedEmployee = repository.save(employee);

        assertEquals(employee, savedEmployee);
    }
}
