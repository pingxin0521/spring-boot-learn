package com.hyp.learn.w.dto;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.w.dto
 * hyp create at 19-12-22
 **/

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * dto示例
 * 以用户注册账号为例
 * @author himly z1399956473@gmail.com
 */
@Data
@ApiModel
public class ExampleDTO {

    /**
     * message请使用国际化配置文件中的key
     * 此处方便展示直接使用中文.
     */
    @ApiModelProperty(notes = "用户名, 不能为空, 否则后端抛出异常")
    @NotNull(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(notes = "密码, 密码不能为空且不能为简单密码, 否则后端抛出异常")
    @NotNull(message = "密码不能为空")
    private String password;

    @ApiModelProperty(notes = "当前注册用户所属的国家名称, 不能为空, 否则后端抛出异常")
    @NotNull(message = "所属国家不能为空")
    private String country;
}
