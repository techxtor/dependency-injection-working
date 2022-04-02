package com.techxtor.dependencyinjectionworking;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class BeanConfig {

 /*   @Bean
    public DataSource dataSource(){
        return new DataSource();
    }

    @Bean
    public CatService catService() {
        return new CatService(dataSource());
    }*/

}
