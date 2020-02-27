package net.abadguy;

import lombok.extern.slf4j.Slf4j;
import net.abadguy.dao.UserMapper;
import net.abadguy.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Slf4j
class DemoOneApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void insert() {
        User user=new User();
        user.setName("张三");
        user.setAge(30);
        user.setManagerId(1088248166370832385L);
        user.setCreateTime(LocalDateTime.now());
        userMapper.insert(user);
    }

}
