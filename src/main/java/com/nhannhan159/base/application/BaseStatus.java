package com.nhannhan159.base.application;

import lombok.Getter;

/**
 * @author tien.tan
 */
public enum BaseStatus {
    /**
     * INVALID: 0
     */
    INVALID(0, "Invalid"),

    /**
     * VALID: 1
     */
    VALID(1, "Valid");

    @Getter
    private final int value;

    @Getter
    private final String desc;

    BaseStatus(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static BaseStatus fromValue(int value) {
        for (BaseStatus status : BaseStatus.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        return null;
    }

    public static BaseStatus fromDesc(String desc) {
        for (BaseStatus status : BaseStatus.values()) {
            if (status.getDesc().equalsIgnoreCase(desc)) {
                return status;
            }
        }
        return null;
    }
}
