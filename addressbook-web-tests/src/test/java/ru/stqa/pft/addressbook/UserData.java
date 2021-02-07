package ru.stqa.pft.addressbook;

public class UserData {
    private final String firstName;
    private final String lastName;
    private final String company;
    private final String mobile;
    private final String email;

    public UserData(String firstName, String lastName, String company, String mobile, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
        this.mobile = mobile;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCompany() {
        return company;
    }

    public String getMobile() {return mobile;}

    public String getEmail() {
        return email;
    }
}
