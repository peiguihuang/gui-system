package com.gui.module.system.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author : peigui.huang
 * @Description: TODO
 * @date Date : 2020-01-04-13:39 13:39
 **/
@Data
@Accessors(chain = true)
public class UserImgVO {

    /**
     * 用户头像
     */
    private String url;
}
