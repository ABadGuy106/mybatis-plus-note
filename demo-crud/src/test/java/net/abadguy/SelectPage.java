package net.abadguy;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import net.abadguy.dao.UserMapper;
import net.abadguy.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class SelectPage {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test01(){
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        Page<User> userPage = new Page<>(1, 2);
        queryWrapper.ge("age",18);
        IPage<User> userIPage = userMapper.selectPage(userPage, queryWrapper);
        long total = userIPage.getTotal();
        log.info("总数：{}",total);
        List<User> records = userIPage.getRecords();
        records.forEach(user -> log.info("数据：{}",user));
    }
}
