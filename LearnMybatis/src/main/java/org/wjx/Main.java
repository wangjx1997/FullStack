package org.wjx;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.wjx.model.User;
import org.wjx.util.SqlSessionFactoryUtils;

import java.sql.Connection;
import java.util.List;

/**
 * @Author WangJX 源码
 * @Date 2022/3/13 16:18
 * @Description
 */
public class Main {
    public static void main(String[] args) {
        SqlSessionFactory factory = SqlSessionFactoryUtils.getInstance();
        SqlSession sqlSession = factory.openSession();
        List<User> user = sqlSession.selectList("org.wjx.mapper01.UserMapper.getAllUser");
        System.out.println(user);

        Connection connection = sqlSession.getConnection();

        sqlSession.close();
    }
}
