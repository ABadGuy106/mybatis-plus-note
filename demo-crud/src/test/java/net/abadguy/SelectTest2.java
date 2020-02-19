package net.abadguy;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import net.abadguy.dao.UserMapper;
import net.abadguy.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class SelectTest2 {
    @Autowired
    private UserMapper userMapper;

    /**
     * 查询名字中包含雨，且年龄小于40.只返回字段 id name
     */
    @Test
    public void selectByWrapperSuper(){
        QueryWrapper<User> queryWrapper= Wrappers.<User>query();
        queryWrapper.select("id","name").like("name","雨").lt("age",40);
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * 查询名字中包含雨，且年龄小于40.不需要返回manager_id和email
     */
    @Test
    public void selectByWrapperSuper2(){
        QueryWrapper<User> queryWrapper= Wrappers.<User>query();
        queryWrapper.like("name","雨").lt("age",40)
                .select(User.class,info->info.getColumn().equals("manager_id")&&
                        info.getColumn().equals("email"));
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }
}
