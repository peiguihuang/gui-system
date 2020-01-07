package com.gui.module.device.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author peigui.huang
 * @email 1992lcg@163.com
 * @date 2020-01-05 15:16:46
 */
@Data
@Accessors(chain = true)
public class MerchantDeviceDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
 	* 
 	*/
	private Long id;

	/**
 	* 商户ID
 	*/
	private Long merchantId;

	private String merchantName;


	/**
 	* 机器序列号
 	*/
	private String deviceSn;

	/**
 	* 机器名称
 	*/
	private String deviceName;

	/**
 	* 创建时间
 	*/
	private Date createTime;

	/**
 	* 更新时间
 	*/
	private Date updateTime;


}