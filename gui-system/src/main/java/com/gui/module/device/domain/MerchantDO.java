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
 * @date 2020-01-05 15:16:44
 */
@Data
@Accessors(chain = true)
public class MerchantDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
 	* 数据ID
 	*/
	private Long id;

	/**
 	* 商户名称
 	*/
	private String merchantName;

	/**
 	* 商户描述
 	*/
	private String merchantDesc;

	/**
 	* 创建时间
 	*/
	private Date createTime;

	/**
 	* 更新时间
 	*/
	private Date updateTime;


}