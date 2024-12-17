package guliveer.lab12.model;

public class User {
    private String firstName;
    private String lastName;
    private String company;
    private String position;
    private String street;
    private String city;
    private String zipCode;
    private String region;
    private String country;
    private String phoneNumber;
    private String email;
    private String website;
    private String notes;

    // Constructor
    public User(String firstName, String lastName, String company, String position, String street, String city,
                String zipCode, String region, String country, String phoneNumber, String email, String website,
                String notes) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
        this.position = position;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.region = region;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.website = website;
        this.notes = notes;
    }

    // Getters and Setters
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getZipCode() { return zipCode; }
    public void setZipCode(String zipCode) { this.zipCode = zipCode; }

    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getWebsite() { return website; }
    public void setWebsite(String website) { this.website = website; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}