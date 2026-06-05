package cn.microservicedemo.userservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserMapper userMapper;

    public UserController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @GetMapping("/users/{id}")
    public UserAccount findById(@PathVariable Integer id) {
        return userMapper.findById(id);
    }

    @GetMapping("/users/name/{username}")
    public UserAccount findByUsername(@PathVariable String username) {
        return userMapper.findByUsername(username);
    }
}
