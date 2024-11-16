package com.demo.html2pdf.pojo;

public class Project {
    private String name;
    private String time;
    private String role;
    private String description;

    public Project(String name, String time, String role, String description) {
        this.name = name;
        this.time = time;
        this.role = role;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
