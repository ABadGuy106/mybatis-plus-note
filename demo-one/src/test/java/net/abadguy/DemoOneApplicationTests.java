package net.abadguy;

import lombok.extern.slf4j.Slf4j;
import net.abadguy.dao.UserMapper;
import net.abadguy.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
class DemoOneApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void select() {
        List<User> users = userMapper.selectList(null);
        log.info("查询到的数据:{}", users);
        users.forEach(System.out::println);
    }

}
