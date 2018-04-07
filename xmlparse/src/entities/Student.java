package entities;

import java.util.UUID;

public class Student extends Human {
    private String group;

    public Student(String firstName, String lastName, int age, Passport passport, String group) {
        super(firstName, lastName, age, passport);
        this.group = group;
    }

    public Student(UUID uid, String firstName, String lastName, int age, Passport passport, String group) {
        super(uid, firstName, lastName, age, passport);
        this.group = group;
    }

    public Student() {
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Student{" + super.toString() +
                ", group='" + group + '\'' +
                '}';
    }
}
