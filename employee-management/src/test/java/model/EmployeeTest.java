package model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class EmployeeTest {
    @Test
    void testEquals() {
        Employee employee1 = new Employee("John", "Doe", LocalDate.of(1999, 9, 9));
        Employee employee2 = new Employee("John", "Doe", LocalDate.of(1999, 9, 9));
        assertEquals(employee1, employee2);
    }

    @Test
    void testNotEquals() {
        Employee employee1 = new Employee("John", "Doe", LocalDate.of(1999, 9, 9));
        Employee employee2 = new Employee("Jane", "Doe", LocalDate.of(1999, 9, 9));
        assertNotEquals(employee1, employee2);

    }
}