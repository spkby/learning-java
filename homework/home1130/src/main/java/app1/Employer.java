package app1;

public class Employer {

    private String firstName;
    private String lastName;
    private String department;
    private double salary;

    public Employer(String firstName, String lastName, String department, double salary){

        setFirstName(firstName);
        setLastName(lastName);
        setDepartment(department);
        setSalary(salary);
    }

    public void setFirstName(String firstName) {
        if(firstName.isEmpty()){
            throw new IllegalArgumentException("Поле Имя не должно быть пустым");
        }
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        if(lastName.isEmpty()){
            throw new IllegalArgumentException("Поле Фамилия не должно быть пустым");
        }
        this.lastName = lastName;
    }

    public void setDepartment(String department) {
        if(department.isEmpty()){
            throw new IllegalArgumentException("Поле Отдел не должно быть пустым");
        }
        this.department = department;
    }

    public void setSalary(double salary) {
        if(salary <= 0){
            throw new IllegalArgumentException("Поле Зарплата не должно быть пустым");
        }
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

}
