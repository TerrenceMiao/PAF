package org.paradise.microservice;

import io.hawt.config.ConfigFacade;
import io.hawt.springboot.HawtPlugin;
import io.hawt.springboot.PluginService;
import io.hawt.system.ConfigManager;
import io.hawt.web.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.hawt.springboot.EnableHawtio;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

@SpringBootApplication
@EnableHawtio
public class App {

    @Autowired
    private ServletContext servletContext;

    @Bean
    JavaScriptEngine nashornEngine() {

        return new JavaScriptEngine().polyfillToNashorn()
                .loadFromClassPath("META-INF/resources/webjars/react/0.14.0/react.min.js")
                .loadFromClassPath("static/app.js");
    }

    public static void main(String[] args) {

        System.setProperty(AuthenticationFilter.HAWTIO_AUTHENTICATION_ENABLED, "false");

        SpringApplication.run(App.class, args);
    }

    @PostConstruct
    public void init() {

        final ConfigManager configManager = new ConfigManager();

        configManager.init();
        servletContext.setAttribute("ConfigManager", configManager);
    }

    /**
     * Loading an example plugin.
     *
     * @return HawtPlugin
     */
    @Bean
    public HawtPlugin samplePlugin() {

        return new HawtPlugin("sample-plugin", "/hawtio/plugins", "", new String[] { "sample-plugin/js/sample-plugin.js" });
    }

    /**
     * Set things up to be in offline mode.
     *
     * @return ConfigFacade
     * @throws Exception bean init exception
     */
    @Bean
    public ConfigFacade configFacade() throws Exception {

        ConfigFacade config = new ConfigFacade() {
            public boolean isOffline() {
                return true;
            }
        };

        config.init();

        return config;
    }

    /**
     * Register rest endpoint to handle requests for /plugin, and return all registered plugins.
     *
     * @return PluginService
     */
    @Bean
    public PluginService pluginService() {

        return new PluginService();
    }

}
