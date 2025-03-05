package de.fi.webapp.service.config;

import lombok.Setter;
import org.glassfish.jersey.internal.guava.Lists;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.List;

@Configuration
@PropertySource(value= "classpath:mail.properties")
@ConfigurationProperties(prefix = "mail")
@Setter
public class MailConfig {

    private String smtp;
    private String user;
    private String password;

    @Bean
    public List<Object> mail() {
        List<Object> mail = List.of(smtp, user, password);
        return mail;
    }
}
