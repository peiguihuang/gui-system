package com.gui.module.bus.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author peigui.huang
 * @email 1157688065@qq.com
 * @date 2020-05-29 21:30:13
 */
@Data
@Accessors(chain = true)
public class LineDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
 	* 
 	*/
	private Long id;

	/**
 	* 起点
 	*/
	private String startPosition;

	/**
 	* 终点
 	*/
	private String endPosition;

	/**
 	* 
 	*/
	private String boardingPosition;

	/**
 	* 0有效 1删除
 	*/
	private Integer status;

	/**
 	* 创建时间
 	*/
	private Date createTime;

	/**
 	* 更新时间
 	*/
	private Date updateTime;


}