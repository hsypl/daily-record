package com.hsy.record.model.enu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by hsy on 2017/8/11.
 */
public enum WeekEnum {
	SUNDAY(0, "sunday",1<<6),
	MONDAY(1, "monday", 1),
	TUESDAY(2, "tuesday",1<<1),
	WEDNESDAY(3, "wednesday",1<<2),
	THURSDAY(4, "thursday",1<<3),
	FRIDAY(5, "friday",1<<4),
	SATURDAY(6, "saturday",1<<5),
	UNKNOWN(-1, "unknown",0);
	private Integer code;
	private String description;
	private Integer binary;

	WeekEnum(Integer code, String description, Integer binary) {
		this.code = code;
		this.description = description;
		this.binary = binary;
	}

	/**
	 * 根据code值获取对应的枚举值
	 * @param code Integer code值
	 * @return SubmitTypeEnum 枚举值，如果不存在返回 UNKNOWN
	 */
	public static WeekEnum valueOfCode(Integer code) {
		if (code != null) {
			for (WeekEnum typeEnum: values()) {
				if (typeEnum.getValue().equals(code)) {
					return typeEnum;
				}
			}
		}
		return UNKNOWN;
	}

	public Integer getValue() {
		return code;
	}

	public Integer getBinary() {
		return binary;
	}

	public String getDescription() {
		return description;
	}

	/**
	 * 根据code 直接获取描述信息
	 * @param code Integer 编码
	 * @return String 对应枚举的描述，如果不是有效枚举返回“未知”
	 */
	public static String getDescription(Integer code){
		return valueOfCode(code).getDescription();
	}

	/**
	 * 获取枚举列表，将未知对象去掉，用于列表选项
	 * @return List<SubmitTypeEnum> 学校枚举列表
	 */
	public static List<WeekEnum> getEnumList() {
		List<WeekEnum> enumList = new ArrayList<>();
		Collections.addAll(enumList, values());
		enumList.remove(UNKNOWN);
		return enumList;
	}


}
