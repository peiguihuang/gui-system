package com.gui.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author lifei
 * @date 2019/01/03
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class BasePageResponse implements Serializable {
    @ApiModelProperty(value = "0成功，非0失败", required = true)
    protected int code =  0;

    @ApiModelProperty(value = "提示信息", required = true)
    protected String msg = "成功";

    /**
     * 总条数
     */
    private Integer total;

    /**
     * 列表数据
     */
    private List<?> rows;

}
