package com.github.xujinquan.commonutils.model.data;

import com.github.xujinquan.commonutils.model.UserModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: commonutils
 * @description: 生成测试用户数据
 * @author: 许金泉
 * @create: 2020-08-28 10:56
 **/
public class UserDataGenerator {

    public static List<UserModel> generateUserList() {
        List<UserModel> userModelList = new ArrayList<>();
        userModelList.add(UserModel.builder().userId(1).userName("张三").build());
        userModelList.add(UserModel.builder().userId(2).userName("李四").build());
        userModelList.add(UserModel.builder().userId(3).userName("王五").build());
        return userModelList;
    }

}
