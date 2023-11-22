package com.wjx.factory;

import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;

public abstract class MyApplicationContext implements ApplicationContext {

    private final ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

    @Override
    public Resource[] getResources(String locationPattern) throws IOException {
        return resourcePatternResolver.getResources(locationPattern);
    }
    
}