package pl.jurczak.kamil.customers.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
public class AppConfiguraction {

    private long MAX_UPLOAD_SIZE = 1000 * 1024 * 1024; // 1GB

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(MAX_UPLOAD_SIZE);
        multipartResolver.setDefaultEncoding("UTF-8");
        return multipartResolver;
    }
}
