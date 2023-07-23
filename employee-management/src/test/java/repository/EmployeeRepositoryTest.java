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
import static org.junit.jupiter.api.Assertions.assertNull;

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
        savedEmployee.setLastName("Dijkstra");
        Employee updatedEmployee = repository.update(employee);
        assertEquals(employee, updatedEmployee);
    }

    @Test
    public void testDeleteOneEmployee() {
        Employee employee = new Employee("John", "Doe", LocalDate.of(1999, 9, 9));
        Employee savedEmployee = repository.save(employee);
        repository.delete(savedEmployee);
        Employee foundEmployee = repository.findById(savedEmployee.getId());
        assertNull(foundEmployee);

    }

    @Test
    public void testDeleteManyEmployees() {
        Employee employee1 = new Employee("John", "Doe", LocalDate.of(1999, 9, 9));
        Employee employee2 = new Employee("Jane", "Doe", LocalDate.of(1999, 9, 9));
        Employee employee3 = new Employee("Big", "Lebrowski", LocalDate.of(1999, 9, 9));
        Employee savedEmployee1 = repository.save(employee1);
        Employee savedEmployee2 = repository.save(employee2);
        Employee savedEmployee3 = repository.save(employee3);

        repository.deleteMany(savedEmployee1, savedEmployee2, savedEmployee3);

        Employee foundEmployee1 = repository.findById(savedEmployee1.getId());
        Employee foundEmployee2 = repository.findById(savedEmployee2.getId());
        Employee foundEmployee3 = repository.findById(savedEmployee3.getId());

        assertNull(foundEmployee1);
        assertNull(foundEmployee2);
        assertNull(foundEmployee3);
    }
}
