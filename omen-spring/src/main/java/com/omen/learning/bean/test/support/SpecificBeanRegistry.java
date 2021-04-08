package com.omen.learning.bean.test.support;

import com.omen.learning.bean.test.annotattion.ComponentBean;
import com.omen.learning.bean.test.annotattion.EnableBeanRegistry;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author : Knight
 * @date : 2020/11/14 4:42 下午
 */
public class SpecificBeanRegistry implements ImportBeanDefinitionRegistrar, ResourceLoaderAware, EnvironmentAware {

    private ResourceLoader resourceLoader;

    private Environment environment;

    /**
     * beanName生成策略
     */
    BeanNameGenerator beanNameGenerator = AnnotationBeanNameGenerator.INSTANCE;


    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata,
                                        BeanDefinitionRegistry registry) {
        //构建扫描过滤器
        AnnotationTypeFilter annotationTypeFilter = new AnnotationTypeFilter(ComponentBean.class);
        ClassPathComponentBeanScanner scanner = new ClassPathComponentBeanScanner(registry, environment, resourceLoader);
        scanner.addIncludeFilter(annotationTypeFilter);
        Set<String> basePackages = getBasePackages(metadata);
        basePackages.stream()
                .map(scanner::findCandidateComponents)
                .flatMap(Collection::stream)
                .forEach(v -> buildBeanDefinition(v, registry));
    }

    /**
     * registry beanDefinition into spring container
     */
    private void buildBeanDefinition(BeanDefinition beanDefinition, BeanDefinitionRegistry registry) {
        if (beanDefinition instanceof AnnotatedBeanDefinition) {
            AnnotatedBeanDefinition annotatedBeanDefinition = (AnnotatedBeanDefinition) beanDefinition;
            AnnotationMetadata metadata = annotatedBeanDefinition.getMetadata();
            Map<String, Object> attributes = metadata.getAnnotationAttributes(ComponentBean.class.getCanonicalName());
            //todo 校验逻辑断言，不符合规则不注册
//            validateAnnotationMetadata(attributes);
            String beanName = beanNameGenerator.generateBeanName(beanDefinition, registry);
//            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(InitialBean.class)
//                    .addPropertyValue("name", beanName)
//                    .addPropertyValue("type", annotatedBeanDefinition.getBeanClassName())
//
//                    .setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
           //build a beanDefinition holder
            BeanDefinitionHolder holder = new BeanDefinitionHolder(beanDefinition, beanName);
            BeanDefinitionReaderUtils.registerBeanDefinition(holder, registry);
        }
    }

    /**
     * specific rules to scan basePackages,normally this value = root path
     * Parser for the @{@link EnableBeanRegistry} annotation.
     */
    private Set<String> getBasePackages(AnnotationMetadata importDate) {
        Map<String, Object> attributes = importDate.getAnnotationAttributes(EnableBeanRegistry.class.getCanonicalName());
        Set<String> basePackages = new HashSet<>();
        Assert.notNull(attributes, "attributes should be a not null value");
        for (String pkg : (String[]) attributes.get("value")) {
            if (StringUtils.isNotBlank(pkg)) {
                basePackages.add(pkg);
            }
        }
        for (String pkg : (String[]) attributes.get("basePackages")) {
            if (StringUtils.isNotBlank(pkg)) {
                basePackages.add(pkg);
            }
        }
        for (Class<?> clazz : (Class<?>[]) attributes.get("basePackageClasses")) {
            basePackages.add(ClassUtils.getPackageName(clazz));
        }
        if (basePackages.isEmpty()) {
            basePackages.add(ClassUtils.getPackageName(importDate.getClassName()));
        }
        return basePackages;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;

    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
