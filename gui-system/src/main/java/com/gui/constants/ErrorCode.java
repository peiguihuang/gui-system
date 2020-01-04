package com.gui.constants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : peigui.huang
 * @Description: TODO
 * @date Date : 2020-01-04-12:58 12:58
 **/
public class ErrorCode {

    public static ErrorCodeMessage SUCCESS = new ErrorCodeMessage(0, "成功");

    public static ErrorCodeMessage INNER_ERROR = new ErrorCodeMessage(500, "服务内部错误");

    @Data
    @AllArgsConstructor
    public static class ErrorCodeMessage {
        private Integer errorCode;
        private String errorMsg;
    }
}

