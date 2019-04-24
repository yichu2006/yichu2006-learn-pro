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

    @Bean("/dispatchService")   // BeanNameUrlHandlerMapping 以“/”开头的Bean进行对外提供服务
    public HttpInvokerServiceExporter testServer(DispatchService dispatchService) {
        HttpInvokerServiceExporter httpInvokerServiceExporter = new HttpInvokerServiceExporter();
        httpInvokerServiceExporter.setService(dispatchService);
        httpInvokerServiceExporter.setServiceInterface(DispatchService.class);
        return httpInvokerServiceExporter;
    }

}
