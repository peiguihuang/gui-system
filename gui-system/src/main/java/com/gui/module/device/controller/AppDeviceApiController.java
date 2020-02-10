package com.gui.module.device.controller;

import com.gui.base.BaseApiResponse;
import com.gui.module.device.domain.AppDeviceDTO;
import com.gui.module.device.domain.CheckAppDeviceDO;
import com.gui.module.device.service.MerchantDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : peigui.huang
 * @Description: TODO
 * @date Date : 2020-01-07-9:43 09:43
 **/

@Controller
@RequestMapping("/app/device")
public class AppDeviceApiController {
    @Autowired
    MerchantDeviceService merchantDeviceService;

    @PostMapping("/check")
    @ResponseBody
    public BaseApiResponse<AppDeviceDTO> update(@RequestBody CheckAppDeviceDO checkAppDeviceDO) {
        BaseApiResponse<AppDeviceDTO> baseApiResponse = new BaseApiResponse<>();
        Map<String,Object> params = new HashMap<>();
        params.put("deviceSn",checkAppDeviceDO.getTerminalId());
        List list =  merchantDeviceService.list(params);
        AppDeviceDTO appDeviceDTO = new AppDeviceDTO();
        if (CollectionUtils.isEmpty(list)){
            appDeviceDTO.setExistFlag(false);
        }else {
            appDeviceDTO.setExistFlag(true);
        }
        baseApiResponse.setData(appDeviceDTO);
        return baseApiResponse;
    }
}
