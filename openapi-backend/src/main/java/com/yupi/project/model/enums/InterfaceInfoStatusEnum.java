package com.yupi.project.model.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author :lhy
 * @description :接口信息枚举值
 * @date :2023/04/07 下午 09:03
 */
public enum InterfaceInfoStatusEnum {
    OFFINE("关闭",0),
    ONLINE("上线",1);

    private final String text;

    private final int value;


    InterfaceInfoStatusEnum(String text, int value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 获取值列表
     *
     * @return
     */
    public static List<Integer> getValues() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    public int getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
