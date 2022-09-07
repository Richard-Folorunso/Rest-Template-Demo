package dev.folorunso.models;

public class User {

    private int id;
    private String user_data;

    public User() {
    }

    public User(int id, String user_data) {
        this.id = id;
        this.user_data = user_data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_data() {
        return user_data;
    }

    public void setUser_data(String user_data) {
        this.user_data = user_data;
    }
}
