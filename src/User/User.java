package User;
public class User {
    private String username;
    private String password;

    private String location;

    public User(String username, String password, String location) {
        this.username = username;
        this.password = password;
        this.location = location;
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
