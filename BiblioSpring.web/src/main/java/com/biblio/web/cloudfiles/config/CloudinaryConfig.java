package com.biblio.web.cloudfiles.config;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Value("${cloudinary.cloud.Url}")
    private String cloudUrl;

    @Bean
    Cloudinary cloudinary(){
        return new Cloudinary(cloudUrl);
    }
}
