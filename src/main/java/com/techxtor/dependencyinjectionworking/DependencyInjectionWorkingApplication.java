package com.techxtor.dependencyinjectionworking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/*
public class DependencyInjectionWorkingApplication {

    public static void main(String[] args) {
        // conventional
//        CatService catService = new CatService();
//        catService.getCatCount();

        // IOC
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfig.class);
        //  gets bean of CatService
        CatService catService = applicationContext.getBean(CatService.class);
        catService.getCatCount();
    }

}
*/

@SpringBootApplication
public class DependencyInjectionWorkingApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(DependencyInjectionWorkingApplication.class, args);
        CatService catService = applicationContext.getBean(CatService.class);
        catService.getCatCount();
    }
}

