package cn.microservicedemo.selectionservice;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CourseSelectionMapper {

    @Select("select id, student_id as studentId, course_id as courseId, selected_time as selectedTime from course_selection")
    List<CourseSelection> findAll();

    @Select("select id, student_id as studentId, course_id as courseId, selected_time as selectedTime from course_selection where student_id = #{studentId}")
    List<CourseSelection> findByStudentId(Integer studentId);
}
