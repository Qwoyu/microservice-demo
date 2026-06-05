package cn.microservicedemo.userservice;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select id, username, password, role, real_name as realName from user_account where id = #{id}")
    UserAccount findById(Integer id);

    @Select("select id, username, password, role, real_name as realName from user_account where username = #{username}")
    UserAccount findByUsername(String username);
}
