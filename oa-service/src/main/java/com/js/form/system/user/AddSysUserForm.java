package com.js.form.system.user;

import com.js.form.system.BasePageForm;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Author: jiangshuang
 * @Description: 系统用户
 **/
@Data
@ToString
public class AddSysUserForm extends BasePageForm {
    @ApiModelProperty("学号")
    @NotBlank(message = "学号不可以为空")
    private String studentNumber;

    @ApiModelProperty("姓名")
    @NotBlank(message = "姓名不可以为空")
    private String name;

    @ApiModelProperty("学院")
    private String college;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("联系方式")
    private String phoneNumber;

    @ApiModelProperty("所属组别")
    @NotBlank(message = "所属组别不可以为空")
    private String groupId;

    @ApiModelProperty("0：正常1：离职账号已被停用2：退休 3：账号未激活")
    private String isAlive;

    @ApiModelProperty("0：不是小组组长 1：是小组组长")
    private String isSuper;

    @ApiModelProperty("对应师傅id")
    private String master;
}