package com.yichu.demo2;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.ScheduledReporter;
import com.codahale.metrics.health.HealthCheckRegistry;
import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import com.ryantenney.metrics.spring.config.annotation.MetricsConfigurer;
import metrics_influxdb.InfluxdbReporter;
import metrics_influxdb.api.protocols.InfluxdbProtocols;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

/**
 * *
 * Author: WenBin Zhuang
 * Date: 2018/12/14
 * Desc: Metrics配置
 */
@Configurable
@EnableMetrics  //启用度量器功能，支持注解
public class MetricsConfig implements MetricsConfigurer {

    @Override
    public MetricRegistry getMetricRegistry() {
        return new MetricRegistry();
    }

    @Override
    public void configureReporters(MetricRegistry metricRegistry) {
        //将结果输出到console
        ConsoleReporter consoleReporter = ConsoleReporter.forRegistry(metricRegistry).
                convertRatesTo(TimeUnit.SECONDS).convertDurationsTo(TimeUnit.SECONDS).build();
        consoleReporter.start(5,TimeUnit.SECONDS);

        //这段可以注释掉，如果不需要存储到数据库中的话
        //将结果输出到influx数据库进行存储
/*        ScheduledReporter scheduledReporter = InfluxdbReporter.forRegistry(metricRegistry)
                //指定数据库地址，用户名和密码
                .protocol(InfluxdbProtocols.http("192.168.200.121", 8086, "root", "root", "mydb"))
                .convertRatesTo(TimeUnit.SECONDS).convertDurationsTo(TimeUnit.SECONDS).skipIdleMetrics(false)
                //指定数据来自哪个机器
                .tag("host","zwb_data_source").build();
        scheduledReporter.start(5,TimeUnit.SECONDS);*/
    }

    @Override
    public HealthCheckRegistry getHealthCheckRegistry() {
        return null;
    }
}
