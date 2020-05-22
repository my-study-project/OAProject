package com.js.enums.group;

/**
 * @Author: 姜爽
 * @Description: 小组相关枚举类
 * @Date: 2020/5/10 11:15
 */
public enum  GroupEnum {
    //当前小组有节目
    HAVING_PROGRAM("0","有节目"),
    //当前小组无节目
    NO_HAVING_PROGRAM("1","无节目"),
    //正在运营
    IS_ALIVE("0","正在运营"),
    //已停用
    NOT_ALIVE("1","已停用")
    ;
    /**
     * code编码
     **/
    private String code;

    /**
     * 对应数据值
     **/
    private String msg;

    GroupEnum(String code, String msg) {
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
