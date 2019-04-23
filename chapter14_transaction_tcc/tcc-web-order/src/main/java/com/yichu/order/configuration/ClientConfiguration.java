package com.yichu.order.configuration;

import com.yichu.api.service.DispatchService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

/**
 * *
 * Author: WenBin Zhuang
 * Date: 2019/4/23
 * Desc:
 */
@Configuration
public class ClientConfiguration {

    @Value("${test.service.url}")
    private String testServiceUrl;

    @Bean
    public HttpInvokerProxyFactoryBean dispatchService() {
        HttpInvokerProxyFactoryBean httpInvokerProxyFactoryBean = new HttpInvokerProxyFactoryBean();
        httpInvokerProxyFactoryBean.setServiceUrl(testServiceUrl);
        httpInvokerProxyFactoryBean.setServiceInterface(DispatchService.class);
        return httpInvokerProxyFactoryBean;
    }

}

