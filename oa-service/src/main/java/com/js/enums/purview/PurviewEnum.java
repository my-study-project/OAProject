package com.js.enums.purview;

/**
 * @Author: 姜爽
 * @Description: 节目操作相关枚举类
 * @Date: 2020/5/10 12:05
 */
public enum PurviewEnum {
    //无权限
    NO_PERMISSION ("0","无权限"),
    //有权限
    HAVING_PERMISSION("1","有权限"),
    ;
    /**
     * code编码
     **/
    private String code;

    /**
     * 对应数据值
     **/
    private String msg;

    PurviewEnum(String code, String msg) {
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
