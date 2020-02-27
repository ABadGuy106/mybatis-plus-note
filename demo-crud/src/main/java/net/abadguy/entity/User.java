package net.abadguy.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("user")
//指定表名称，默认情况下表面和实体名称必须相同，若表名称中有下划线，默认使用驼峰命名法推断
// 若表名和实体名称不同可以使用@TableName注解指定该实体对应的表名称
public class User {
    //主键
    @TableId//指定表中的主键，默认情况下插入是不填写id,mp会使用雪花算法生成一个id赋值给主键。如果主键名称不叫id,需要用这个注解指定
    private Long id;
    //姓名
    @TableField("name")//指定数据库中对应表的哪一列
    private String name;
    //年龄
    private Integer age;
    //邮箱
    private String email;
    //直属上级
    private Long managerId;
    //创建时间
    private LocalDateTime createTime;
    //备注
    private transient String remark;//transient关键字，声明该字段不对应数据表中的任何字段。

    private static String remark2;//使用static关键字标识属性也可达到transient关键字的效果
    @TableField(exist = false)
    private String remark3;//使用@TableField(exist = false)也可达到transient关键字的效果
}
