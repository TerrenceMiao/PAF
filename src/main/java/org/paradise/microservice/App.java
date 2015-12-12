package org.paradise.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {

        return builder.sources(App.class);
    }

    @Bean
    JavaScriptEngine nashornEngine() {

        return new JavaScriptEngine().polyfillToNashorn()
                .loadFromClassPath("META-INF/resources/webjars/react/0.14.0/react.min.js")
                .loadFromClassPath("static/app.js");
    }

    public static void main(String[] args) {

        SpringApplication.run(App.class, args);
    }

}
