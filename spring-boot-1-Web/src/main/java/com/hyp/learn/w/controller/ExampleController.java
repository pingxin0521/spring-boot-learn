package com.hyp.learn.w.controller;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.w.controller
 * hyp create at 19-12-22
 **/

import com.hyp.learn.w.dto.ExampleDTO;
import com.hyp.learn.w.dto.ExampleResponseDataDTO;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author himly z1399956473@gmail.com
 */

@RestController
@RequestMapping("/swagger-examples")
@Api(tags = "swagger使用规范", description = "用于展示swagger使用规范的接口列表")
public class ExampleController {

    @ApiOperation(value = "url及form参数使用规范", response = ResponseEntity.class)
    @ApiResponses({
            //http code
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "成功"),
            @ApiResponse(code = HttpServletResponse.SC_CONFLICT, message = "当前用户没有做到swagger使用规范")
    })
    @GetMapping(value = "/path-and-form-params-standard/{path}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "path", value = "路径参数",required = true, format = "path"),
            @ApiImplicitParam(name = "form", value = "表单参数", required = true, format = "form")
    })
    public ResponseEntity pathAndFormParamsStandard(String form) {
        boolean complianceWithTheSpecifications = false;

        if (complianceWithTheSpecifications) {
            return new ResponseEntity(HttpStatus.OK);
        }else {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }

    @ApiOperation(value = "使用对象接收参数的使用规范", response = ResponseEntity.class)
    @ApiResponses({
            //http code
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "成功"),
            @ApiResponse(code = HttpServletResponse.SC_CONFLICT, message = "当前用户没有做到swagger使用规范")
    })
    @GetMapping(value = "/object-params-standard")
    public ResponseEntity objectParamsStandard(@Valid @RequestBody ExampleDTO exampleDTO) {
        if (exampleDTO.getCountry() == null) {
            return new ResponseEntity(HttpStatus.OK);
        }else {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }

    /**
     *swagger还是无法针对响应数据做出准确的描述, 因为ApiOperation中只能存在一个Model
     * 但是在实际接口中, 可能会存在List<ExampleResponseDataDTO>或Map<String, ExampleResponseDataDTO>
     * 及ResponseEntity<ExampleResponseDataDTo, OK>等数据格式.
     *
     * 无法准确地对每个参数做出描述, 因此在ApiOperation注解中的response属性设置为真正返回的类, 并在该类中对每个属性使用
     * ApiProperty注解完成说明.
     *
     * @return response entity
     */
    @ApiOperation(value = "swagger响应数据使用规范", response = ExampleResponseDataDTO.class)
    @ApiResponses({
            //http code
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "成功"),
            @ApiResponse(code = HttpServletResponse.SC_CONFLICT, message = "当前用户没有做到swagger使用规范")
    })
    @GetMapping(value = "/response-data-standard")
    public ResponseEntity<ExampleResponseDataDTO> responseDataStandard() {
        ExampleResponseDataDTO responseDataDTO = new ExampleResponseDataDTO();
        responseDataDTO.setName("test");
        responseDataDTO.setTheJoinedDepartmentId(3L);
        responseDataDTO.setUniversityId(3L);

        return new ResponseEntity<>(responseDataDTO, HttpStatus.OK);
    }
}
