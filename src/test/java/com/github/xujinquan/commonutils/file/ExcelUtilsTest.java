package com.github.xujinquan.commonutils.file;

import com.github.xujinquan.commonutils.model.UserModel;
import com.github.xujinquan.commonutils.model.data.UserDataGenerator;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class ExcelUtilsTest {

    @Test
    void generateExcelFile() throws IOException {
        List<UserModel> userModelList = UserDataGenerator.generateUserList();

        Map<String, Function<UserModel, String>> dataHead = new LinkedHashMap<>();
        dataHead.put("用户ID", r -> String.valueOf(r.getUserId()));
        dataHead.put("用户名", r -> r.getUserName());

        String fileName = "D:\\" + System.currentTimeMillis() + "_UserList.xlsx";

        ExcelUtils.generateExcelFile(fileName, "用户列表", userModelList, dataHead);
    }
}