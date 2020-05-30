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
 * @date 2020-05-30 09:50:30
 */
@Data
@Accessors(chain = true)
public class UserDO implements Serializable {
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
         * 
         */
        private String realname;

            /**
         * 
         */
        private String sex;

            /**
         * 
         */
        private String email;

            /**
         * 
         */
        private String mobile;

            /**
         * 
         */
        private String weixin;

            /**
         * 
         */
        private String qq;

            /**
         * 
         */
        private String csrq;

            /**
         * 
         */
        private String mz;

            /**
         * 
         */
        private String photo;

            /**
         * 
         */
        private String avatar;

            /**
         * 
         */
        private Long roleid;

            /**
         * 
         */
        private String number;

            /**
         * 
         */
        private String identity;

            /**
         * 
         */
        private Long identityId;

            /**
         * 
         */
        private String sfzx;

            /**
         * 
         */
        private String extra;

            /**
         * 创建时间
         */
        private Date createTime;

            /**
         * 更新时间
         */
        private Date updateTime;

    
}