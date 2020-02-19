package net.abadguy;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import net.abadguy.dao.UserMapper;
import net.abadguy.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Slf4j
public class SelectTest {
    @Autowired
    private UserMapper userMapper;

    @Test//根据主键查询
    void selectById() {
        User user = userMapper.selectById(1094592041087729666L);
        log.info("获取到的用户信息{}",user);
    }

    /**
     * 查询（根据ID 批量查询）
     *
     * @param idList 主键ID列表(不能为 null 以及 empty)
     */
    @Test
    void selectByIds(){
        List<Long> ids = Arrays.asList(1087982257332887553L, 1088248166370832385L, 1088250446457389058L);
        List<User> users = userMapper.selectBatchIds(ids);
        users.forEach(System.out::println);
    }

    /**
     * 查询（根据 columnMap 条件）
     *
     * @param columnMap 表字段 map 对象
     */
    @Test
    void selectByMap(){
        Map<String,Object> map= Maps.newHashMap();
        map.put("manager_id",1088248166370832385L);
        map.put("age",28);

        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
        //ps:map的key值一定要与数据库字段一致
        
    }

    /**
     * 名字中包含“%雨%”且年龄小于40
     * nake like '%雨%' and age<40
     */
    @Test
    public void selectByWrapper(){
//        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        QueryWrapper<User> queryWrapper= Wrappers.<User>query();
        queryWrapper.like("name","雨").lt("age",40);
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * 名字中包含雨并且年龄大于20小于等于40并且email不为空
     * name like '%雨%' and age between 20 and 40 and email is not null
     */
    @Test
    public void selectByWrapper1(){
        QueryWrapper<User> queryWrapper= Wrappers.<User>query();
        queryWrapper.like("name","雨").between("age",20,40).isNotNull("email");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * 名字为王或者年龄大于等于25，按照年龄降序排列，年龄相同按照id升序排列
     * name like '王%' or age>=25 order by age desc,id asc
     */
    @Test
    public void selectByWrapper2(){
        QueryWrapper<User> queryWrapper= Wrappers.<User>query();
        queryWrapper.likeRight("name","王").or()
                .ge("age",25).orderByDesc("age").orderByAsc("id");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * 创建日期为2019/2/14且直属上级的名字为王姓
     * date_format(create_time,'%Y-%m-%d') and manager_id in(
     * select id from user where name like '王%')
     */
    @Test
    public void selectByWrapper3(){
        QueryWrapper<User> queryWrapper= Wrappers.<User>query();
        queryWrapper.apply("date_format(create_time,'%Y-%m-%d')={0}","2019-02-14")
                .inSql("manager_id","select id from user where name like '王%'");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * 名字为王姓并且(年龄小于40或邮箱不为空)
     * name like '王%' and (age<40 or email is not null)
     */
    @Test
    public void selectByWrapper4(){
        QueryWrapper<User> queryWrapper= Wrappers.<User>query();
        queryWrapper.likeRight("name","王")
                .and(wq->wq.lt("age",40).or().isNotNull("email"));
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * 名字为王姓或者(年龄钓鱼40并且年龄大于20并且邮箱不为空)
     * name liek '王%' or (age <40 and age >20 and email is not null)
     */
    @Test
    public void selectByWrapper5(){
        QueryWrapper<User> queryWrapper= Wrappers.<User>query();
        queryWrapper.likeRight("name","王")
                .or(wq->wq.lt("age",40).gt("age",20).isNotNull("email"));
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * 年龄小于40或邮箱不为空并且名字为王姓
     * (age <40 or email is not null) and name like '王%'
     */
    @Test
    public void selectByWrapper6(){
        QueryWrapper<User> queryWrapper= Wrappers.<User>query();
        queryWrapper.nested(wq->wq.lt("age",40).or().isNotNull("email"))
                .likeRight("name","王");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * 年龄为30、31、34、35
     * age in(30，31，34，35)
     */
    @Test
    public void selectByWrapper7(){
        QueryWrapper<User> queryWrapper= Wrappers.<User>query();
        queryWrapper.in("age",Arrays.asList(30,31,34,35));
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }
}
