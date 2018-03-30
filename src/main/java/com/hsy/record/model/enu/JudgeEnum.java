package com.hsy.record.model.enu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 教师认证状态枚举类
 * Created by huangshuoying on 2017/4/1.
 */
public enum JudgeEnum {
    YES(1, "是"),
    NO(2, "否"),
    UNKNOWN(-1, "未知");

    private Integer code;
    private String description;

    JudgeEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 根据code值获取对应的枚举值
     * @param code String code值
     * @return CookieStatusEnum 枚举值，如果不存在返回 UNKNOWN
     */
    public static JudgeEnum valueOf(Integer code) {
        if (code != null) {
            for (JudgeEnum typeEnum: values()) {
                if (typeEnum.getValue().intValue() == code.intValue()) {
                    return typeEnum;
                }
            }
        }
        return UNKNOWN;
    }

    public Integer getValue() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    /**
     * 根据code 直接获取描述信息
     * @param code Integer 数字编号
     * @return String 对应枚举的描述，如果不是有效枚举返回“未知”
     */
    public static String getDescription(Integer code){
        return valueOf(code).getDescription();
    }

    public static List<JudgeEnum> getEnumList() {
        List<JudgeEnum> enumList = new ArrayList<>();
        Collections.addAll(enumList, values());
        enumList.remove(UNKNOWN);
        return enumList;
    }
}
