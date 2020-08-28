package com.github.xujinquan.commonutils.model;

import com.github.xujinquan.commonutils.annotation.Query;
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

    @Query
    private int userId;

    @Query(type = Query.Type.INNER_LIKE)
    private String userName;

}
