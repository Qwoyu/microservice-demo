package cn.microservicedemo.selectionservice;

import org.springframework.stereotype.Component;

@Component
public class UserClientFallback implements UserClient {

    @Override
    public UserAccount findById(Integer id) {
        UserAccount user = new UserAccount();
        user.setId(id);
        user.setUsername("unknown");
        user.setPassword("");
        user.setRole("UNKNOWN");
        user.setRealName("用户服务暂时不可用");
        return user;
    }
}