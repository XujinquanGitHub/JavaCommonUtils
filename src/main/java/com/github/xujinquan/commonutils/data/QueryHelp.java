package com.github.xujinquan.commonutils.data;


import com.github.xujinquan.commonutils.annotation.Query;
import com.github.xujinquan.commonutils.reflection.ReflectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;

/**
 * @author xujinquan
 * @date 2019-6-4 14:59:48
 */
@Slf4j
public class QueryHelp {

    /**
     * @author :  xujinquan
     */
    @SuppressWarnings("unchecked")
    public static String getWhereSql(Object query) {
        StringBuilder sql = new StringBuilder(" where 1=1");
        if (query == null) {
            return sql.toString();
        }
        try {
            List<Field> fields = ReflectionUtil.getAllFields(query.getClass());
            for (Field field : fields) {
                boolean accessible = field.isAccessible();
                field.setAccessible(true);
                Query q = field.getAnnotation(Query.class);
                if (q == null) {
                    continue;
                }
                String propName = q.propName();
                String attributeName = StringUtils.isEmpty(propName) ? field.getName() : propName;
                Object val = field.get(query);
                if (val == null) {
                    continue;
                }
                boolean ignoreString = q.ignoreNull();
                if (ignoreString && val instanceof String && StringUtils.isEmpty(val.toString())) {
                    continue;
                }
                switch (q.type()) {
                    case EQUAL:
                        sql.append(" and ").append(attributeName).append("=").append(val.toString());
                        break;
                    case GREATER_THAN:
                        sql.append(" and ").append(attributeName).append("<=").append(val.toString());
                        break;
                    case LESS_THAN:
                        sql.append(" and ").append(attributeName).append(">=").append(val.toString());
                        break;
                    case LESS_THAN_NQ:
                        sql.append(" and ").append(attributeName).append(">").append(val.toString());
                        break;
                    case INNER_LIKE:
                        sql.append(" and ").append(attributeName).append(" like ").append("%").append(val.toString()).append("%");
                        break;
                    case LEFT_LIKE:
                        sql.append(" and ").append(attributeName).append(" like ").append("%").append(val.toString());
                        break;
                    case RIGHT_LIKE:
                        sql.append(" and ").append(attributeName).append(" like ").append(val.toString()).append("%");
                        break;
                    case IN:
                        if (val instanceof Collection && CollectionUtils.isEmpty((Collection) val)) {
                            sql.append(" and ").append(attributeName).append(" in ").append(val.toString());
                        }
                        break;
                    case MULTI_FIELD_LIKE:
                        sql.append(" and ").append(" ( ");
                        String[] attrs = attributeName.split(",");
                        final Object value = val;
                        for (int index = 0; index < attrs.length; index++) {
                            String attr = attrs[index];
                            sql.append(attr).append(" like ").append(value.toString());
                        }
                        sql.append(" ) ");
                        break;
                    default:
                        break;
                }
                field.setAccessible(accessible);
            }
        } catch (Exception e) {
            log.error(e.toString());
        }
        return sql.toString();
    }


}
