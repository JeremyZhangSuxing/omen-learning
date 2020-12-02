package com.omen.learning.sample.support;

import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 动态刷新配置bean中的apollo配置
 *
 * @author : Knight
 * @date : 2020/12/1 10:19 上午
 */
@Slf4j
@Component
public class TaskSchedulePropertiesRefresher implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @ApolloConfigChangeListener
    public void onChange(ConfigChangeEvent changeEvent) {
        refreshTaskScheduleProperties(changeEvent);
    }

    private void refreshTaskScheduleProperties(ConfigChangeEvent changeEvent) {
        log.info("Refreshing TaskSchedule properties!");
        this.applicationContext.publishEvent(new EnvironmentChangeEvent(changeEvent.changedKeys()));
        log.info("TaskSchedule properties refreshed!");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;

    }
}
