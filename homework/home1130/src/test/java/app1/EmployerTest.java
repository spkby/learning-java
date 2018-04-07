package app1;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;



public class EmployerTest {

    @Test
    public void testEmployerConstructor(){

        String firstName = "First Name";
        String lastName = "Last Name";
        String department = "Department";
        double salary = 100.00;

        Employer employer = new Employer(firstName,lastName,department,salary);

        assertEquals(firstName,employer.getFirstName());
        assertEquals(lastName,employer.getLastName());
        assertEquals(department,employer.getDepartment());
        assertThat(salary,closeTo(employer.getSalary(),0.001));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmployerSetFirstNameExceptions(){

        String firstName = "First Name";
        String lastName = "Last Name";
        String department = "Department";
        double salary = 100.00;

        Employer employer = new Employer(firstName,lastName,department,salary);

        employer.setFirstName("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmployerSetLastNameExceptions(){

        String firstName = "First Name";
        String lastName = "Last Name";
        String department = "Department";
        double salary = 100.00;

        Employer employer = new Employer(firstName,lastName,department,salary);

        employer.setLastName("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmployerSetDepartmentExceptions(){

        String firstName = "First Name";
        String lastName = "Last Name";
        String department = "Department";
        double salary = 100.00;

        Employer employer = new Employer(firstName,lastName,department,salary);

        employer.setDepartment("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmployerSetSalaryExceptions(){

        String firstName = "First Name";
        String lastName = "Last Name";
        String department = "Department";
        double salary = 100.00;

        Employer employer = new Employer(firstName,lastName,department,salary);

        employer.setSalary(0);
    }



}
