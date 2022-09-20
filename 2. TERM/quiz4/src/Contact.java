public class Contact implements Comparable<Contact>{

    private String socialSecurityNumber;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    
    

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }



    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
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



    public String getPhoneNumber() {
        return phoneNumber;
    }



    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }



    public int compareTo(Contact o) {
        // TODO Auto-generated method stub
        return 0;
    }
    
}
