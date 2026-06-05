package cn.microservicedemo.selectionservice;

import org.springframework.stereotype.Component;

@Component
public class CourseClientFallback implements CourseClient {

    @Override
    public Course findById(Integer id) {
        Course course = new Course();
        course.setId(id);
        course.setCourseName("课程服务暂时不可用");
        course.setTeacherName("未知");
        course.setCredit(0.0);
        course.setCapacity(0);
        return course;
    }
}