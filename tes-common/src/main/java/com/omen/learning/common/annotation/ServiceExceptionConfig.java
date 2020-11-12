package com.omen.learning.common.annotation;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportAware;
import org.springframework.context.annotation.Role;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author zhang.suxing
 * @date 2020/11/10 21:53
 **/
@Configuration
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
public class ServiceExceptionConfig implements ImportAware {
    private AnnotationAttributes enableCompensate;


    @Override
    public void setImportMetadata(AnnotationMetadata importMetadata) {
        enableCompensate = AnnotationAttributes.fromMap(
                importMetadata.getAnnotationAttributes(EnableServiceException.class.getName(), false));
    }

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public ServiceExceptionBeanFactoryPointcutAdvisor serviceExceptionBeanFactoryPointcutAdvisor() {
        ServiceExceptionBeanFactoryPointcutAdvisor advisor = new ServiceExceptionBeanFactoryPointcutAdvisor();
        advisor.setPc(new ServiceExceptionPointcut());
        advisor.setOrder(enableCompensate.<Integer>getNumber("order"));
        advisor.setAdvice(new ServiceExceptionAspect());
        return advisor;
    }
}
