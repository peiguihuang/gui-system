package com.gui.module.device.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * 
 * 
 * @author peigui.huang
 * @email 1992lcg@163.com
 * @date 2020-01-05 15:16:43
 */
@Data
@Accessors(chain = true)
public class AppDeviceDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Boolean existFlag;


}