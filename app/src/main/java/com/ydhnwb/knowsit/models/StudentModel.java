package com.ydhnwb.knowsit.models;

public class StudentModel {
    private String nim;
    private String password;
    private String email;
    private String name;
    private String key;
    private int semester;

    public StudentModel() { }

    public StudentModel(String nim, String password, String email, String name, String key, int semester) {
        this.nim = nim;
        this.password = password;
        this.email = email;
        this.name = name;
        this.key = key;
        this.semester = semester;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }
}
