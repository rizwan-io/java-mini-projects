package repository;

import model.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeRepositoryTest {

    private Connection connection;
    private EmployeeRepository repository;

    @BeforeEach
    void setUp() throws SQLException {
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/employee","rizwan", "admin");
        connection.setAutoCommit(false);
        repository = new EmployeeRepository(connection);
    }

    @AfterEach
    void tearDown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    @Test
    public void testSaveOneEmployee() {
        Employee employee = new Employee("John", "Doe", LocalDate.of(1999, 9, 9));
        Employee savedEmployee = repository.save(employee);
        assertEquals(employee, savedEmployee);
    }

    @Test
    public void testFindEmployeeById() {
        Employee employee = new Employee("John", "Doe", LocalDate.of(1999, 9, 9));
        Employee savedEmployee = repository.save(employee);
        Employee foundEmployee = repository.findById(savedEmployee.getId());
        assertEquals(employee, foundEmployee);
    }

    @Test
    public void testUpdateEmployee() {
        Employee employee = new Employee("John", "Doe", LocalDate.of(1999, 9, 9));
        Employee savedEmployee = repository.save(employee);
        employee.setLastName("Dijkstra");
        Employee updatedEmployee = repository.update(employee);
        assertEquals(employee, updatedEmployee);
    }

    @Test
    public void deleteOneEmployee() {
        Employee employee = new Employee("John", "Doe", LocalDate.of(1999, 9, 9));
        Employee savedEmployee = repository.save(employee);
        repository.delete(savedEmployee);
    }
}
