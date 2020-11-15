package com.omen.learning.bean.test.support;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;

import java.util.Set;

/**
 * @author : Knight
 * @date : 2020/11/15 12:24 下午
 */
public class ClassPathComponentBeanScanner extends ClassPathBeanDefinitionScanner {
    /**
     * 自定义scanner 不使用默认的过滤
     * @param registry 注册器
     * @param environment 环境变量
     * @param resourceLoader 资源加载器
     */
    public ClassPathComponentBeanScanner(BeanDefinitionRegistry registry, Environment environment, ResourceLoader resourceLoader) {
        super(registry, false, environment, resourceLoader);
    }

    /**
     * 可以自己实现逻辑
     */
    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        return super.doScan(basePackages);
    }

    /**
     * 判定beanDefinition是否是一个合格的bean
≈     */
    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        boolean isCandidate = false;
        if (beanDefinition.getMetadata().isIndependent() && !beanDefinition.getMetadata().isAnnotation() &&
                beanDefinition.getMetadata().isConcrete()) {
            System.out.println("******注解的吗bean被注册*******");
            isCandidate = true;
        }
        return isCandidate;
    }
}
