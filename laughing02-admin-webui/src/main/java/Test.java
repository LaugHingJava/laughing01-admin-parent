
import com.laughing.crowd.entity.Admin;
import com.laughing.crowd.mapper.AdminMapper;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


// 指定Spring 给Junit 提供的运行器类
@RunWith(SpringJUnit4ClassRunner.class)
// 加载Spring 配置文件的注解
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml"})
public class Test {

    private Logger logger = LoggerFactory.getLogger(Test.class);
    @Autowired
    private DataSource dataSource;
    @Resource
    private AdminMapper adminMapper;
    @org.junit.Test
    public void insert(){
        Admin test = new Admin(null,"test","123456","testname","test@qq.com",null);
        adminMapper.insert(test);
        logger.debug(test.toString());
    }
    @org.junit.Test
    public void t() throws SQLException {
        Connection connection  =dataSource.getConnection();
        System.out.println(connection);
    }

}
