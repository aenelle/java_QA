package ru.stqa.pft.addressbook.model;

public class ContactData {
    public   int id = Integer.MAX_VALUE;
    private  String firstName;
    private  String lastName;
    private  String company;
    private  String mobile;
    private  String email;
    private String group;

//    public ContactData(int id, String firstName, String lastName, String company, String mobile, String email, String group) {
//        this.id = id;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.company = company;
//        this.mobile = mobile;
//        this.email = email;
//        this.group = group;
//    }
//
//    public ContactData(String firstName, String lastName, String company, String mobile, String email, String group) {
//        this.id = Integer.MAX_VALUE;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.company = company;
//        this.mobile = mobile;
//        this.email = email;
//        this.group = group;
//    }

    public ContactData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ContactData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactData withCompany(String company) {
        this.company = company;
        return this;
    }

    public ContactData withMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
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

        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }
}
