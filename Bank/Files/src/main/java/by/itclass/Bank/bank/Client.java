package by.itclass.Bank.bank;

public class Client {

    private String firstName;
    private String lastName;
    private final String passport;

    public Client(String firstName, String lastName, String passport) {
        setFirstName(firstName);
        setLastName(lastName);
        if (passport.isEmpty()) {
            throw new IllegalArgumentException("Поле 'Паспорт' не может быть пусты");
        }
        this.passport = passport;
    }

    // get field
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassport() {
        return passport;
    }

    // set field
    public void setLastName(String lastName) {
        if (lastName == "" || lastName.isEmpty()) {
            throw new IllegalArgumentException("Поле 'Имя' не может быть пустым");
        }
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        if (firstName.isEmpty()) {
            throw new IllegalArgumentException("");
        }
        this.firstName = firstName;
    }

    @Override
    public int hashCode() {
        return this.passport.hashCode();
    }

    @Override
    public String toString() {
        return "Client{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", passport='" + passport + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        return this.passport.equals(client.passport);
    }
}
