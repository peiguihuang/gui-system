package com.gui.module.bus.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 
 *
 * @author peigui.huang
 * @email 1157688065@qq.com
 * @date 2020-05-29 22:17:27
 */
@Data
@Accessors(chain = true)
public class TimeDO implements Serializable {
    private static final long serialVersionUID = 1L;

            /**
         * 
         */
        private Long id;

            /**
         * 路线
         */
        private String busLineId;

            /**
         * 出发路线
         */
        private Date departTime;

            /**
         * 0有效 1删除
         */
        private Integer status;

            /**
         * 金额
         */
        private BigDecimal amount;

            /**
         * 
         */
        private Integer orderNumber;

            /**
         * 0无锁 1有锁
         */
        private Integer lock;

            /**
         * 创建时间
         */
        private Date createTime;

            /**
         * 更新时间
         */
        private Date updateTime;

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

    
}