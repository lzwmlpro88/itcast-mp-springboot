package cn.itcast.mp.mapper;

import cn.itcast.mp.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author: longzhangwei
 * @create: 2020-12-02-23:24
 */
public interface UserMapper extends BaseMapper<User>{

    public User findById(Long id);
}
