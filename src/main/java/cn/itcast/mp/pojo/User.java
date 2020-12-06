package cn.itcast.mp.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author: longzhangwei
 * @create: 2020-12-02-23:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_user")
public class User {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    //@TableField("user_name")
    private String userName;

    //@TableField("password")
    private String password;

    //@TableField("name")
    private String name;

    //@TableField("age")
    private Integer age;

    //@TableField("email")
    private String email;

    //@TableField("birthday")
    private LocalDateTime birthday;
}
