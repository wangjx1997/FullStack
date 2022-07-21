package org.wjx.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.wjx.util.SqlSessionFactoryUtils;

/**
 * @Author WangJX
 * @Date 2022/3/13 16:54
 * @Description
 */

public class Test01 {
    private SqlSession sqlSession;

    @Before
    private void before() {
        sqlSession = SqlSessionFactoryUtils.getInstance().openSession();
    }

    @Test
    private void test1() {

        sqlSession.commit();
    }

    @After
    private void after() {
        sqlSession.close();
    }
}
