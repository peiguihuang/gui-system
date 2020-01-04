package com.gui.utils;



import com.gui.constants.ErrorCode;
import com.gui.dtos.BasePageResponse;
import com.gui.dtos.BaseResponse;
import com.sun.corba.se.impl.oa.toa.TOA;

import java.util.List;
import java.util.Objects;

/**
 * @author : peigui.huang
 * @Description: TODO
 * @date Date : 2019-09-23-21:26 21:26
 **/
public class ResponseUtils {


    /**
     * 返回错误码及提示
     *
     * @param errorCodeMessage
     * @return
     */
    public static <T> BaseResponse<T> fail(ErrorCode.ErrorCodeMessage errorCodeMessage) {
        BaseResponse response = new BaseResponse();
        response.setCode(errorCodeMessage.getErrorCode());
        response.setMsg(errorCodeMessage.getErrorMsg());
        return response;
    }

    public static <T> BaseResponse<T> fail(Integer code, String message) {
        BaseResponse response = new BaseResponse();
        response.setCode(code);
        response.setMsg(message);
        return response;
    }

    public static <T> BaseResponse<T> fail( String message) {
        BaseResponse response = new BaseResponse();
        response.setCode(ErrorCode.INNER_ERROR.getErrorCode());
        response.setMsg(message);
        return response;
    }

    public static <T> BaseResponse<T> fail( ) {
        BaseResponse response = new BaseResponse();
        response.setCode(ErrorCode.INNER_ERROR.getErrorCode());
        response.setMsg(ErrorCode.INNER_ERROR.getErrorMsg());
        return response;
    }

    public static <T> BaseResponse<T> fail(ErrorCode.ErrorCodeMessage errorCodeMessage, Object object) {
        BaseResponse response = new BaseResponse();
        response.setCode(errorCodeMessage.getErrorCode());
        response.setMsg(errorCodeMessage.getErrorMsg());
        response.setData(object);
        return response;
    }


    /**
     * 返回成功数据
     *
     * @param tls
     * @param <T>
     * @return
     */
    public static <T> BaseResponse<T> success(T tls) {
        BaseResponse response = new BaseResponse();
        response.setCode(ErrorCode.SUCCESS.getErrorCode());
        response.setMsg(ErrorCode.SUCCESS.getErrorMsg());
        response.setData(tls);
        return response;
    }

    public static <T> BaseResponse<T> success(String msg) {
        BaseResponse response = new BaseResponse();
        response.setCode(ErrorCode.SUCCESS.getErrorCode());
        response.setMsg(msg);
        return response;
    }

    public static <T> BaseResponse<T> success() {
        BaseResponse response = new BaseResponse();
        response.setCode(ErrorCode.SUCCESS.getErrorCode());
        response.setMsg(ErrorCode.SUCCESS.getErrorMsg());
        return response;
    }

    public static BasePageResponse buildPageSuccess(Integer total, List<?> rows) {
        BasePageResponse response = new BasePageResponse();
        response.setCode(ErrorCode.SUCCESS.getErrorCode());
        response.setMsg(ErrorCode.SUCCESS.getErrorMsg());
        response.setTotal(total);
        response.setRows(rows);
        return response;
    }
}
