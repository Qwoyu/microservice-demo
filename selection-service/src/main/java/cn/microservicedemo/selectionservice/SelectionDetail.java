package cn.microservicedemo.selectionservice;

import java.util.List;

public class SelectionDetail {
    private UserAccount student;
    private List<Course> courses;

    public UserAccount getStudent() {
        return student;
    }

    public void setStudent(UserAccount student) {
        this.student = student;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}