package cn.microservicedemo.selectionservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SelectionController {

    private final CourseSelectionMapper courseSelectionMapper;
    private final CourseClient courseClient;
    private final UserClient userClient;

    public SelectionController(CourseSelectionMapper courseSelectionMapper,
                               CourseClient courseClient,
                               UserClient userClient) {
        this.courseSelectionMapper = courseSelectionMapper;
        this.courseClient = courseClient;
        this.userClient = userClient;
    }

    @GetMapping("/selections")
    public List<CourseSelection> findAll() {
        return courseSelectionMapper.findAll();
    }

    @GetMapping("/selections/student/{studentId}")
    public List<CourseSelection> findByStudentId(@PathVariable Integer studentId) {
        return courseSelectionMapper.findByStudentId(studentId);
    }

    @GetMapping("/selections/student/{studentId}/detail")
    public SelectionDetail findStudentSelectionDetail(@PathVariable Integer studentId) {
        UserAccount student = userClient.findById(studentId);

        SelectionDetail detail = new SelectionDetail();
        detail.setStudent(student);

        if ("UNKNOWN".equals(student.getRole())) {
            detail.setCourses(new ArrayList<Course>());
            return detail;
        }

        List<CourseSelection> selections = courseSelectionMapper.findByStudentId(studentId);
        List<Course> courses = new ArrayList<Course>();

        for (CourseSelection selection : selections) {
            Course course = courseClient.findById(selection.getCourseId());
            courses.add(course);
        }


        detail.setStudent(student);
        detail.setCourses(courses);
        return detail;
    }
}