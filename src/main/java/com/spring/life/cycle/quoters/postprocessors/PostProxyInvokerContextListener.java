package com.spring.life.cycle.quoters.postprocessors;

import com.spring.life.cycle.quoters.annotations.PostProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.lang.reflect.Method;

public class PostProxyInvokerContextListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private ConfigurableListableBeanFactory factory;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext context = event.getApplicationContext();
        String[] names = context.getBeanDefinitionNames();
        for (String name: names) {
            BeanDefinition beanDefinition = factory.getBeanDefinition(name);
            String originalName = beanDefinition.getBeanClassName();
            try {
                Class<?> originalClass = Class.forName(originalName);
                Method[] methods = originalClass.getMethods();

                for (Method method: methods) {
                    if (method.isAnnotationPresent(PostProxy.class)) {
                        Object proxy = context.getBean(name);
                        Method currentMethod = proxy.getClass().getMethod(method.getName(), method.getParameterTypes());
                        currentMethod.invoke(proxy);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }
}
