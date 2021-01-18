package com.omen.learning.common.support;

import com.omen.learning.common.annotation.EnableTokenValidate;
import org.springframework.beans.factory.annotation.Value;
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
public class TokenValidateConfig implements ImportAware {
    private AnnotationAttributes enableCompensate;

    @Value("${jwt.token.key:jeremy}")
    private String issuer;

    @Override
    public void setImportMetadata(AnnotationMetadata importMetadata) {
        enableCompensate = AnnotationAttributes.fromMap(
                importMetadata.getAnnotationAttributes(EnableTokenValidate.class.getName(), false));
    }

    @Bean
    public TokenInfoParser tokenInfoParser() {
        return new TokenInfoParser(tokenAnnotationMetaDataHolder());
    }

    @Bean
    public AnnotationMetaDataHolder tokenAnnotationMetaDataHolder() {
        return new AnnotationMetaDataHolder();
    }

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public TokenValidateBeanFactoryPointcutAdvisor serviceExceptionBeanFactoryPointcutAdvisor() {
        TokenValidateBeanFactoryPointcutAdvisor advisor = new TokenValidateBeanFactoryPointcutAdvisor();
        advisor.setPc(new TokenValidatePointcut(tokenAnnotationMetaDataHolder()));
        advisor.setOrder(enableCompensate.<Integer>getNumber("order"));
        advisor.setAdvice(new TokenValidateAspect(tokenInfoParser(),issuer));
        return advisor;
    }

}
