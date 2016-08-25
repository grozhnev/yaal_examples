package ru;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@EnableConfigurationProperties
@Import({PropertiesSettings.class, YamlSettings.class})
public class Application {

    @Autowired
    private PropertiesSettings propertiesSettings;
    @Autowired
    private YamlSettings yamlSettings;

    /**
     * http://localhost:8080/
     */
    @RequestMapping("/")
    String home() {
        return String.format("%s %s %s %s",
                yamlSettings.getMessage().getPrefix(),
                propertiesSettings.getMessage(),
                propertiesSettings.getSystem().getStatus(),
                yamlSettings.getMessage().getSuffix()
        );
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}