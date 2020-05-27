package com.js.enums.system;

/**
 * @Author: 姜爽
 * @Description: 系统用户枚举类
 * @Date: 2020/5/10 19:11
 */
public enum SysUserEnum {

    // 系统用户相关
    IS_ALIVE("0", "在职"), NOT_ALIVE("1", "离职账号已被停用"), INACTIVATED("2", "账号未激活"), IS_SUPER("0", "不是小组组长"),
    NOT_SUPER("1", "是小组组长"), UNKONW_TYPE("3", "未知类型");

    /**
     * code编码
     **/
    private String code;

    /**
     * 对应数据值
     **/
    private String msg;

    SysUserEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 获取标记码
     **/
    public String getCode() {
        return code;
    }

    /**
     * 对应信息
     **/
    public String getMsg() {
        return msg;
    }

}
