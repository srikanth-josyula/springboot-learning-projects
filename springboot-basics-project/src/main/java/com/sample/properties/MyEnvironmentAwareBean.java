package com.sample.properties;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class MyEnvironmentAwareBean implements EnvironmentAware {

    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public void printEnvironmentProperties() {
        String property = environment.getProperty("app.name");
        System.out.println("App Name: " + property);

        String activeProfiles = String.join(", ", environment.getActiveProfiles());
        System.out.println("Active Profiles: " + activeProfiles);
    }
}

