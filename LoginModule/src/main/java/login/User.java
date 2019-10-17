package login;

public class User {
    private Integer id;
    private String userName;
    private String password;
    private levelProvided accessType;

    User() {
    }

    public User(Integer id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    Integer getId() {
        return id;
    }

    void setId(Integer id) {
        this.id = id;
    }

    String getUserName() {
        return userName;
    }


    void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    levelProvided getAccessType() {
        return accessType;
    }

    void setAccessType(levelProvided accessType) {
        this.accessType = accessType;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", accessType=" + accessType +
                '}';
    }
}
