package com.gui.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author vinsonhuang
 * @date 2018/12/24 10:47
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BaseResponse<T> implements Serializable {

    @ApiModelProperty(value = "0成功，非0失败", required = true)
    protected int code =  0;

    @ApiModelProperty(value = "提示信息", required = true)
    protected String msg = "成功";

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "content")
    protected T data;

    public BaseResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 只打印code、message，日志级别在info(含)以上使用
     *
     * @return
     */
    public String toShortString() {
        final StringBuilder sb = new StringBuilder("BaseResponse(");
        sb.append("code=").append(code);
        sb.append(", message=").append(msg);
        sb.append(')');
        return sb.toString();
    }
}
