package org.mianshi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

/**
 * @author : nate.jiang
 * @date : 2023/4/10 13:21
 */
@SpringBootApplication
public class MianShiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MianShiApplication.class, args);
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor(){
        return new MethodValidationPostProcessor();
    }
}
