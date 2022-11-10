package com.example.SpringTrySample.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class ValidationConfig implements WebMvcConfigurer {
    @Bean
    public LocalValidatorFactoryBean validator() {
        final LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
        validatorFactoryBean.setValidationMessageSource(messageSource());
        return validatorFactoryBean;

    }

    /**
     * Native to Asciiを実施しないでも日本語を扱えるように、
     * {@link ReloadableResourceBundleMessageSource}を使用して、ValidationMessages.propertiesをロードします。
     */
    private MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:ValidationMessages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

}
