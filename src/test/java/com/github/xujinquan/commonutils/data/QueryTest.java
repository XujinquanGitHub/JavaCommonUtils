package com.github.xujinquan.commonutils.data;

import com.github.xujinquan.commonutils.model.UserModel;
import org.junit.jupiter.api.Test;

/**
 * @program: commonutils
 * @description:
 * @author: 许金泉
 * @create: 2020-08-28 11:42
 **/
public class QueryTest {

    @Test
    public void userQueryTest()  {
        UserModel userInfo=UserModel.builder().userId(1).userName("张三").build();
        System.out.println(QueryHelp.getWhereSql(userInfo));
    }

}
