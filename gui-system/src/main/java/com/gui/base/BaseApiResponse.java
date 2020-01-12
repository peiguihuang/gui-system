package com.gui.base;

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
public class BaseApiResponse<T> implements Serializable {

    @ApiModelProperty(value = "0000成功，非0000失败", required = true)
    protected String code =  "0000";

    @ApiModelProperty(value = "提示信息", required = true)
    protected String message = "成功";

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "data")
    protected T data;

    public BaseApiResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 只打印code、message，日志级别在info(含)以上使用
     *
     * @return
     */
    public String toShortString() {
        final StringBuilder sb = new StringBuilder("BaseResponse(");
        sb.append("code=").append(code);
        sb.append(", message=").append(message);
        sb.append(')');
        return sb.toString();
    }
}
