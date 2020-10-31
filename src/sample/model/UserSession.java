package sample.model;

public final class UserSession {

    private static UserSession instance;

    private Integer id;
    private String userName;
    private String email;

    private UserSession(Integer id, String userName, String email) {
        this.userName = userName;
        this.id = id;
        this.email = email;
    }

    public static UserSession getInstace(Integer id, String userName, String email) {
        if(instance == null) {
            instance = new UserSession(id, userName, email);
        }
        return instance;
    }

    public static UserSession getInstace() {
        return instance;
    }

    public Integer getID() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public void cleanUserSession() {
        id = null;
        userName = null;
        email = null;
        instance = null;
    }

    @Override
    public String toString() {
        return "UserSession{" +
                " ID='" + String.valueOf(id) + '\'' +
                ", userName='" + String.valueOf(userName) + '\'' +
                ", email='" + String.valueOf(email) + '\'' +
                '}';
    }
}