package com.github.xujinquan.commonutils.model;

import lombok.Builder;
import lombok.Data;

/**
 * @program: commonutils
 * @description: 用户
 * @author: 许金泉
 * @create: 2020-08-28 10:53
 **/
@Data
@Builder
public class UserModel {

    private int userId;

    private String userName;

}
