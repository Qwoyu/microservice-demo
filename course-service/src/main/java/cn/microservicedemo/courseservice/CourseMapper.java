package cn.microservicedemo.courseservice;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CourseMapper {

    @Select("select id, course_name as courseName, teacher_name as teacherName, credit, capacity from course")
    List<Course> findAll();

    @Select("select id, course_name as courseName, teacher_name as teacherName, credit, capacity from course where id = #{id}")
    Course findById(Integer id);
}
