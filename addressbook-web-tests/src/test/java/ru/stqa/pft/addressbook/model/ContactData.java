package ru.stqa.pft.addressbook.model;

public class ContactData {
    private  int id;
    private final String firstName;
    private final String lastName;
    private final String company;
    private final String mobile;
    private final String email;
    private String group;

    public ContactData(int id, String firstName, String lastName, String company, String mobile, String email, String group) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
        this.mobile = mobile;
        this.email = email;
        this.group = group;
    }

    public ContactData(String firstName, String lastName, String company, String mobile, String email, String group) {
        this.id = 0;
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
        this.mobile = mobile;
        this.email = email;
        this.group = group;
    }

//    public ContactData( String company, String mobile, String email, String group) {
//        this.id = null;
//        this.firstName = null;
//        this.lastName = null;
//        this.company = company;
//        this.mobile = mobile;
//        this.email = email;
//        this.group = group;
//    }


    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getId(){return id;}

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

    public String getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }
}
