package ru.anovikov.learning.otusbooklib.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

@Component
@ConfigurationProperties("application")
public class AppConfig {

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("/i18n/messages");
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }

    @Bean
    public BufferedReader bufferedReader() throws FileNotFoundException {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}