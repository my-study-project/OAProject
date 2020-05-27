package com.js.enums.program;

/**
 * @Author: 姜爽
 * @Description: 节目操作相关枚举类
 * @Date: 2020/5/10 12:05
 */
public enum ProgramEnum {
    // 节目有效
    IS_ALIVE("0", "节目有效"),
    // 节目无效
    NOT_ALIVE("1", "节目无效"),
    // 需要评估
    IS_EVALUATE("0", "需要评估"),
    // 不需要评估
    NOT_EVALUATE("1", "不需要评估"),
    // 未知类型
    UNKNOW_TYPE("unknow", "未知类型"),

    // 待审核
    PEND_REVIEW("0", "待审核"),
    // 审核通过
    EXAMINATION_PASS("1", "审核通过"),
    // 驳回
    TURN_DOWN("2", "驳回"),

    // 上学期
    LAST_SEMESTER("1", "上学期"),
    // 下学期
    NEXT_SEMESTER("2", "下学期");

    /**
     * code编码
     **/
    private String code;

    /**
     * 对应数据值
     **/
    private String msg;

    ProgramEnum(String code, String msg) {
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
