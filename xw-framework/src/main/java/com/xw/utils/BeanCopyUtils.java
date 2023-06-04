package com.xw.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author 小吴
 * @Date 2023/05/30 9:25
 * @Version 1.0
 * 拷贝工具类
 */
public class BeanCopyUtils {

    private BeanCopyUtils() {
    }

    /**
     *
     * @param source  源文件
     * @param clazz    目标对象类型
     * @return
     * @param <V>
     * 单个bean拷贝
     */
    public static <V> V copyBean(Object source,Class<V> clazz) {
        //创建目标对象
        V result = null;
        try {
            result = clazz.newInstance();   //默认使用无参构造   利用反射
            //实现属性copy
            BeanUtils.copyProperties(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //返回结果
        return result;
    }

    /**
     * list集合拷贝
     * @param list    源集合
     * @param clazz    目标类型集合
     * @return
     * @param <O>
     * @param <V>
     */
    public static <O,V> List<V> copyBeanList(List<O> list, Class<V> clazz){
        return list.stream()
                .map(o -> copyBean(o, clazz))
                .collect(Collectors.toList());
    }
}