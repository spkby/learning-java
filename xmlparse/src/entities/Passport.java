package entities;

public class Passport {

    private String number;
    private String dateOfExpiry;
    private String dateOfIssue;
    private String authority;


    public Passport() {
    }

    @Override
    public String toString() {
        return number + "/" + dateOfIssue + "/" + authority;
    }

    public Passport(String number, String dateOfIssue, String authority, String dateOfExpiry) {

        this.authority = authority;
        this.dateOfIssue = dateOfIssue;
        this.dateOfExpiry = dateOfExpiry;
        this.number = number;
    }

    public String getAuthority() {
        return authority;
    }

    public String getDateOfIssue() {
        return dateOfIssue;
    }

    public String getDateOfExpiry() {
        return dateOfExpiry;
    }

    public String getNumber() {
        return number;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public void setDateOfExpiry(String dateOfExpiry) {
        this.dateOfExpiry = dateOfExpiry;
    }

    public void setDateOfIssue(String dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
