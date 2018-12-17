package com.zhannicholas.cpwms.util;


import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ToMapUtil {
    private ToMapUtil(){}

    /**
     * 将单个元素转为Map，用于前端显示
     * @param e
     * @param <E>
     * @return
     */
    public static <E> Map<String, Object> fromAInstance(E e){
        List<E> elementList = new ArrayList<>();
        elementList.add(e);

        // 初始化结果集
        Map<String, Object> resultSet = new HashMap<>();
        resultSet.put("rows", elementList);
        resultSet.put("total", 1);
        return resultSet;
    }

    /**
     * 将一页的所有的元素转为Map,用于前端显示
     * @param ePage
     * @param <E>
     * @return
     */
    public static <E> Map<String, Object> fromAPage(Page<E> ePage){
        List<E> eList = ePage.getContent();

        // 初始化结果集
        Map<String, Object> resultSet = new HashMap<>();
        resultSet.put("rows", eList);
        resultSet.put("total", ePage.getTotalElements());
        return resultSet;
    }

    /**
     * 将某个操作结果转为Map,用于前端显示
     * @param result
     * @return
     */
    public static Map<String, Object> fromString(String result){
        Map<String, Object> resultSet = new HashMap<>();
        resultSet.put("result", result);
        return resultSet;
    }
}
