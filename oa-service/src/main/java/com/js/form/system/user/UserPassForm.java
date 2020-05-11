package com.js.form.system.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Author: jiangshuang
 * @Description: 系统用户修改密码的参数
 **/
@Data
@ToString
public class UserPassForm{
    @ApiModelProperty("学号")
    @NotBlank(message = "学号不可以为空")
    private String studentNumber;

    @ApiModelProperty("密码")
    @NotBlank(message = "密码不可以为空")
    private String password;
}