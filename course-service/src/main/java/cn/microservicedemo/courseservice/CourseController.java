package cn.microservicedemo.courseservice;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CourseController {
    private static final Logger log = LoggerFactory.getLogger(CourseController.class);

    @Value("${server.port}")
    private String serverPort;

    private final CourseMapper courseMapper;

    public CourseController(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    @GetMapping("/courses")
    public List<Course> findAll() {
        return courseMapper.findAll();
    }

    @GetMapping("/courses/{id}")
    public Course findById(@PathVariable Integer id) {
        log.info("------------course-service port {} handled /courses/{}------------", serverPort, id);
        return courseMapper.findById(id);
    }
}
