package com.omen.learning.common.support;

import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractBeanFactoryPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

/**
 * @author zhang.suxing
 * @date 2020/11/10 22:28
 **/
public class TokenValidateBeanFactoryPointcutAdvisor extends AbstractBeanFactoryPointcutAdvisor {


    private StaticMethodMatcherPointcut pc;

    public void setPc(StaticMethodMatcherPointcut pc) {
        this.pc = pc;
    }


    @Override
    public Pointcut getPointcut() {
        return pc;
    }
}
