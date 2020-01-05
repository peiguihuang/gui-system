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
 * @date 2020-01-05 15:16:43
 */
@Data
@Accessors(chain = true)
public class AppVerisonDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
 	* 
 	*/
	private Long id;

	/**
 	* 实际用来升级比对，app版本小于该版本就升级
 	*/
	private Long appVersion;

	/**
 	* app显示版本号
 	*/
	private String appShowVersion;

	/**
 	* apk下载地址
 	*/
	private String apkUrl;

	/**
 	* app版本描述
 	*/
	private String appDesc;

	/**
 	* 创建时间
 	*/
	private Date createTime;

	/**
 	* 更新时间
 	*/
	private Date updateTime;


}