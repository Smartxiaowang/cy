package com.cybs.bs.modules.sys.enums;

/**
 * 用户状态
 *
 * @since 1.0.0
 */
public enum UserStatusEnum {
    DISABLE(0),
    ENABLED(1),
    LOCKTENMIN(2);

    private int value;

    UserStatusEnum(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}
