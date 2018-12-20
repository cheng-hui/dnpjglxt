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
        // 初始化结果集
        Map<String, Object> resultSet = new HashMap<>();
        if(e != null){
            elementList.add(e);
            resultSet.put("total", 1);
        }
        else{
            resultSet.put("total", 0);
        }
        resultSet.put("rows", elementList);
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
        return fromAList(eList);
    }


    /**
     * 将一个列表的所有元素转为Map, 用于前端显示
     * @param eList
     * @param <E>
     * @return
     */
    public static <E> Map<String, Object> fromAList(List<E> eList){
        // 初始化结果集
        Map<String, Object> resultSet = new HashMap<>();
        resultSet.put("rows", eList);
        resultSet.put("total", eList.size());
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
