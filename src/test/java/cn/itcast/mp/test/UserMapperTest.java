package cn.itcast.mp.test;

import cn.itcast.mp.mapper.UserMapper;
import cn.itcast.mp.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.media.sound.SoftTuning;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.spi.DateFormatProvider;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 * @author: longzhangwei
 * @create: 2020-12-02-23:29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Resource
    private UserMapper userMapper;


    @Test
    public void testSaveOrUpdate(){
        User user = new User();
        user.setName("张三");
        user.setAge(19);
        QueryWrapper<User> wrapper = new QueryWrapper<>(user);
        wrapper.eq("name",user.getName());
        //userMapper.in

    }

    @Test
    public void testSelect(){
        List<User> users = userMapper.selectList(null);
        users.stream().forEach(u -> {
            System.out.println("u = " + u);
        });
    }

    @Test
    public void testFindById(){
        User user = userMapper.findById(1l);
        System.out.println("user = " + user);
    }

    /**
     * 通用的CRUD操作
     */

    @Test
    public void testInsert(){
        User user = new User();
        user.setAge(18);
        user.setUserName("xiaoming");
        user.setName("小明");
        user.setPassword("123456");
        user.setEmail("xiaoming@126.com");
        user.setBirthday(LocalDateTime.parse("2020-10-10 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        int insert = userMapper.insert(user);
        if(insert > 0){
            System.out.println("插入成功！" + user.getId());
        }else {
            System.out.println("插入失败");
        }
    }


    /**
     * 根据id更新
     */
    @Test
    public void testUpdateById(){
        User user = new User();
        user.setId(1l);
        user.setName("想做爱");
        user.setUserName("have sex");
        int update = userMapper.updateById(user);
        if(update > 0){
            System.out.println("更新成功！");
        }else{
            System.out.println("更新失败");
        }

    }

    /**
     * 通选择选器进行更新，只能更新user不为null的情况
     */
    @Test
    public void testConditionUpdate(){
        User user = new User();
        user.setId(1l);
        user.setName("操B");
        user.setUserName("cao b");
        user.setAge(20);

        //条件选择器
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id",1);
        //可设置多个条件

        //执行更新
        int update = userMapper.update(user,wrapper);
        if(update > 0){
            System.out.println("更新成功！");
        }else{
            System.out.println("更新失败");
        }

    }

    /**
     * 根据条件选择器进行更新，可以更新user为null的情况
     */
    @Test
    public void testUpadteWrapper(){
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",2).set("age",18).set("name","想找女人做爱").set("birthday",LocalDateTime.parse("2020-12-03 22:12:12",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:ss:mm")));
        //执行更新操作
        int update = userMapper.update(null, updateWrapper);
        if(update > 0){
            System.out.println("更新成功");
        }else{
            System.out.println("更新失败");
        }

    }


    /**
     * 删除,根据id来单个的删
     */
    @Test
    public void testDelete(){
        int rs = userMapper.deleteById(1l);
        if(rs > 0){
            System.out.println("删除成功");
        }else{
            System.out.println("删除失败");
        }
    }

    /**
     * 根据条件来删
     */
    @Test
    public void testParamsDelete(){
        User user = new User();
        user.setName("张三");
        QueryWrapper<User> wrapper = new QueryWrapper<>(user);
        int delete = userMapper.delete(wrapper);
        if(delete > 0){
            System.out.println("删除成功");
        }else{
            System.out.println("删除失败");
        }

    }

    /**
     * 根据id批量删除（单个查询）
     */
    @Test
    public void testBatchIdsDelete(){
        int rs = userMapper.deleteBatchIds(Arrays.asList(2l, 3l));
        if(rs > 0){
            System.out.println("删除成功");
        }else{
            System.out.println("删除失败");
        }

    }

    /**
     * 根据ids批量查询
     */
    @Test
    public void testBatchSelect(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(4l, 5l));
        users.stream().forEach(u -> {
            System.out.println(u);
        });
    }



    /**
     *  根据id查询
     */
    @Test
    public void testSelectById(){
        User user = userMapper.selectById(4l);
        System.out.println("查询结果："+user);

    }

    /**
     * 根据条件查询单个
     */
    @Test
    public void testSelectOne(){
        //User user = new User();
        //user.setName("赵六");
        //user.setAge(21);

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name","赵六").eq("age",22);
        User user1 = userMapper.selectOne(wrapper);
        System.out.println("查询结果：" + user1);


    }

    @Test
    public void testCount(){

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age",2).like("name","赵");

        Integer integer = userMapper.selectCount(queryWrapper);
        System.out.println("查询结果：" + integer);



    }


    @Test
    public void testSelectList(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.gt("age",10);

        List<User> users = userMapper.selectList(wrapper);
        users.stream().forEach(u -> {
            System.out.println(u);
        });

    }


    /**
     *分页测试
     */
    @Test
    public void testPage(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.gt("age",21);

        Page<User> page = new Page<>(1,1);

        //根据查询条件查询
        Page<User> ipage = userMapper.selectPage(page, wrapper);
        System.out.println("数据总条数：" + ipage.getTotal());
        System.out.println("总页数：" + ipage.getPages());

        //取出分页记录
        List<User> users = ipage.getRecords();
        users.stream().forEach(user -> {
            System.out.println("user=" + user);
        });




    }



















}
