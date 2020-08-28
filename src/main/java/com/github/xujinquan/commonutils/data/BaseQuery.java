package com.github.xujinquan.commonutils.data;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @program: commonutils
 * @description:
 * @author: 许金泉
 **/
@Data
@Accessors(chain = true)
public class BaseQuery {

    private long size = 10;

    private long current = 1;

    private List<OrderItem> orders = new ArrayList<>();

    private Set<String> allowOrderField = new HashSet<>();

    public Set<String> getAllowOrderField() {
        return allowOrderField;
    }

    public void setAllowOrderField(Set<String> allowOrderField) {
        this.allowOrderField = allowOrderField;
    }


    public Page getPage() {
        Page page = new Page(current, size);
        page.addOrder(orders);
        return page;
    }

}
