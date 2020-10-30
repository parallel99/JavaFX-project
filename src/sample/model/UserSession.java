package sample.model;

import java.util.HashSet;
import java.util.Set;

public final class UserSession {

    private static UserSession instance;

    private String userName;
    private Set<String> privileges;

    private UserSession(String userName, Set<String> privileges) {
        this.userName = userName;
        this.privileges = privileges;
    }

    public static UserSession getInstace(String userName, Set<String> privileges) {
        if(instance == null) {
            instance = new UserSession(userName, privileges);
        }
        return instance;
    }

    public static UserSession getInstace() {
        return instance;
    }

    public String getUserName() {
        return userName;
    }

    public Set<String> getPrivileges() {
        return privileges;
    }

    public void cleanUserSession() {
        userName = "";// or null
        privileges = new HashSet<>();// or null
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "userName='" + userName + '\'' +
                ", privileges=" + privileges +
                '}';
    }
}