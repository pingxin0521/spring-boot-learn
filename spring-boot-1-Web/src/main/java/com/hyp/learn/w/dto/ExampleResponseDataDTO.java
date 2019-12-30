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

/**
 * 示例类
 * @author himly z1399956473@gmail.com
 */
@Data
@ApiModel
public class ExampleResponseDataDTO {

    @ApiModelProperty(notes = "用户名")
    private String name;

    @ApiModelProperty(notes = "用户所在的部门id")
    private Long theJoinedDepartmentId;

    @ApiModelProperty(notes = "用户所在的学校id")
    private Long universityId;
}
