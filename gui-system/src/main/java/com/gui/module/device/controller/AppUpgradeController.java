package com.gui.module.device.controller;

import com.gui.base.BaseApiResponse;
import com.gui.module.device.domain.AppUpgradeDO;
import com.gui.module.device.domain.AppVerisonDO;
import com.gui.module.device.domain.AppVerisonDTO;
import com.gui.module.device.service.AppVerisonService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

/**
 * @author : peigui.huang
 * @Description: TODO
 * @date Date : 2020-01-07-9:43 09:43
 **/

@Controller
@RequestMapping("/app/upgrade")
public class AppUpgradeController {
    @Autowired
    AppVerisonService appVerisonService;

    @ResponseBody
    @RequestMapping("/check")
    public BaseApiResponse<AppVerisonDTO> update(AppUpgradeDO appUpgradeDO) {
        AppVerisonDTO appVerisonDTO = new AppVerisonDTO();
        BaseApiResponse<AppVerisonDTO> baseApiResponse = new BaseApiResponse<>();

        AppVerisonDO appVerisonDO = appVerisonService.getUpgradeVersion(appUpgradeDO);
        if (Objects.nonNull(appUpgradeDO)){
            BeanUtils.copyProperties(appVerisonDO,appVerisonDTO);
            baseApiResponse.setData(appVerisonDTO);

        }
        return baseApiResponse;
    }
}
