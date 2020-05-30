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
 * @date 2020-05-30 21:59:36
 */
@Data
@Accessors(chain = true)
public class OrderStatisticsDO implements Serializable {
    private static final long serialVersionUID = 1L;

            /**
         * 
         */
        private Long id;

            /**
         * 
         */
        private BigDecimal totalAmount;

            /**
         * 
         */
        private Integer cancelCount;

            /**
         * 
         */
        private Integer payCount;

            /**
         * 
         */
        private Integer waitPayCount;

            /**
         * 
         */
        private String statisticsDay;

            /**
         * 创建时间
         */
        private Date createTime;

            /**
         * 更新时间
         */
        private Date updateTime;

    
}