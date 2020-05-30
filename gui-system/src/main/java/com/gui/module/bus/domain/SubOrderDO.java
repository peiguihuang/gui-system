package com.gui.module.bus.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * @author peigui.huang
 * @email 1157688065@qq.com
 * @date 2020-05-30 17:45:33
 */
@Data
@Accessors(chain = true)
public class SubOrderDO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private Long id;

    /**
     *
     */
    private String uid;

    /**
     * 0:待支付 1:已支付 2:已取消
     */
    private Integer payStatus;
    private String payStatusStr;

    /**
     *
     */
    private BigDecimal amount;

    /**
     *
     */
    private Date payTime;

    /**
     *
     */
    private Long totalOrderId;

    /**
     *
     */
    private Long busTimeId;

    /**
     *
     */
    private String boardingPosition;

    /**
     * 起点
     */
    private String startPosition;

    /**
     * 终点
     */
    private String endPosition;

    /**
     * 出发路线
     */
    private Date departTime;

    /**
     *
     */
    private Integer orderNumber;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private String realname;

}