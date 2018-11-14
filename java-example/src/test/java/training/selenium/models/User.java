package training.selenium.models;

public class User {
    String first_name;
    String last_name;
    String postal_code;
    String country;
    String state;
    String email;
    String password;






    public String getFirst_name() {
        return first_name;
    }

    public User withFirst_name(String first_name) {
        this.first_name = first_name;
        return this;
    }

    public String getLast_name() {
        return last_name;
    }

    public User withLast_name(String last_name) {
        this.last_name = last_name;
        return this;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public User withPostal_code(String postal_code) {
        this.postal_code = postal_code;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public User withCountry(String country) {
        this.country = country;
        return this;
    }

    public String getState() {
        return state;
    }

    public User withState(String state) {
        this.state = state;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User withEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", postal_code='" + postal_code + '\'' +
                ", country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public User withPassword(String password) {
        this.password = password;
        return this;
    }
}
