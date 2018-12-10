package com.yichu.十一个行为型模式.strategy.strategy2;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

/**
 * *
 * Author: WenBin Zhuang
 * Date: 2018/12/6
 * Desc: 简单工厂
 */
@Component
public class PromotionCalculationFactory implements BeanFactoryAware {

    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    //通过简单工厂方式： 根据promotion 获取对应的 bean ； 这里把 promotion == beanName;
    //陪选方案：也可以通过map来做对应，或者存数据库中，或者存properties配置文件中
    public PromotionCalculation getPromotionCalculation(String promotion) {
        PromotionCalculation bean = (PromotionCalculation) beanFactory.getBean(promotion);
        return bean;
    }
}
