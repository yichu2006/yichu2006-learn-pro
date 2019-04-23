package com.yichu.dispatch.configuration;

import com.yichu.api.service.DispatchService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

/**
 * *
 * Author: WenBin Zhuang
 * Date: 2019/4/23
 * Desc:
 */
@Configuration
public class ServerConfiguration {

    @Bean("/dispatchService")   //要加
    public HttpInvokerServiceExporter dispatchService(DispatchService dispatchService) {
        HttpInvokerServiceExporter httpInvokerServiceExporter = new HttpInvokerServiceExporter();
        httpInvokerServiceExporter.setService(dispatchService);
        httpInvokerServiceExporter.setServiceInterface(DispatchService.class);
        return httpInvokerServiceExporter;
    }

}
