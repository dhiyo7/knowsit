package com.ydhnwb.knowsit.models;

public class ScheduleModel {
    private String time;
    private String course;
    private String key;

    public ScheduleModel() {
    }

    public ScheduleModel(String time, String course, String key) {
        this.time = time;
        this.course = course;
        this.key = key;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
