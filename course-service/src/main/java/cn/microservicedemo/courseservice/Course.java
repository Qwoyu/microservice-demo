package cn.microservicedemo.courseservice;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Course {
    private Integer id;
    private String courseName;
    private String teacherName;
    private Double credit;
    private Integer capacity;

}
