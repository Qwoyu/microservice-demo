package cn.microservicedemo.selectionservice;

import java.util.Date;

public class CourseSelection {
    private Integer id;
    private Integer studentId;
    private Integer courseId;
    private Date selectedTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Date getSelectedTime() {
        return selectedTime;
    }

    public void setSelectedTime(Date selectedTime) {
        this.selectedTime = selectedTime;
    }
}
