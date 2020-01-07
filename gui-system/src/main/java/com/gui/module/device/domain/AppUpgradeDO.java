package com.gui.module.device.domain;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author : peigui.huang
 * @Description: TODO
 * @date Date : 2020-01-07-9:44 09:44
 **/
@Data
@Accessors(chain = true)
public class AppUpgradeDO {
    /**
     * 商户ID
     */
    private String merchantId;

    /**
     * 设备ID
     */
    private String terminalId;

}
