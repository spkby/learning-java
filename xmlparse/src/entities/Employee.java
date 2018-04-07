package entities;

import java.util.UUID;

public class Employee extends Human {
    private int experience;

    public Employee(String firstName, String lastName, int age, Passport passport, int experience) {
        super(firstName, lastName, age, passport);
        this.experience = experience;
    }

    public Employee(UUID uid, String firstName, String lastName, int age, Passport passport, int experience) {
        super(uid, firstName, lastName, age, passport);
        this.experience = experience;
    }

    public Employee() {
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        return "Employee{" + super.toString() +
                ", experience=" + experience +
                '}';
    }
}
