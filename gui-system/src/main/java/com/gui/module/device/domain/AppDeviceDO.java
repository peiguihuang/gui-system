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
 * @date 2020-01-05 15:16:40
 */
@Data
@Accessors(chain = true)
public class AppDeviceDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
 	* 数据ID
 	*/
	private Long id;

	/**
 	* APP ID
 	*/
	private Long appId;

	/**
 	* 0 默认全部升级 1 针对商户升级  2针对单台设备升级
 	*/
	private Integer type;

	private String typeName;


	/**
 	* 商户ID
 	*/
	private Long merchantId;

	/**
 	* 设备ID
 	*/
	private Long deviceId;

	/**
 	* 0生效 1失效
 	*/
	private Integer status;
	private String statusName;

	/**
 	* 创建时间
 	*/
	private Date createTime;

	/**
 	* 更新时间
 	*/
	private Date updateTime;

	/**
	 * 实际用来升级比对，app版本小于该版本就升级
	 */
	private String appVersion;

	/**
	 * app显示版本号
	 */
	private String appShowVersion;

	/**
	 * 机器名称
	 */
	private String deviceName;

	/**
	 * 商户名称
	 */
	private String merchantName;

}