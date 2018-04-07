package entities;

import java.util.UUID;

public class Human {
    private UUID uid;
    private String firstName;
    private String lastName;
    private int age;
    private Passport passport;

    public Human(String firstName, String lastName, int age, Passport passport) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.uid = UUID.randomUUID();
        this.passport = passport;
    }

    public Human(UUID uid, String firstName, String lastName, int age, Passport passport) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.uid = uid;
        this.passport = passport;
    }

    public Human() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public UUID getUid() {
        return uid;
    }

    public void setUid(UUID uid) {
        this.uid = uid;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Human human = (Human) o;

        if (age != human.age) return false;
        if (firstName != null ? !firstName.equals(human.firstName) : human.firstName != null) return false;
        return lastName != null ? lastName.equals(human.lastName) : human.lastName == null;

    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + age;
        return result;
    }

    @Override
    public String toString() {
        return "Human{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", passport=" + passport;
    }
}
