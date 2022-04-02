*dependency-injection-working*
---
Dependency Injection
---
### Introduction:
* Dependency Injection is a fundamental aspect of the Spring framework, through which the Spring container “injects” objects into other objects or “dependencies”.
[Details on IoC and Dependency Injection](https://www.baeldung.com/inversion-control-and-dependency-injection-in-spring)

### Working
* Conventional way of creating instance (_as below_) can be replaced with Dependency Injection.
`
CatService catService = new CatService();
catService.getCatCount();
`
Different ways of Implementing:
 - XML Configuration
 - Annotation Based Configuration
 
#### XML Configuration
 - [Refer this Repo](https://github.com/techxtor/spring-core-ioc)

#### AnnotationBased Configuration

#### WAY 1:
- Create a BeanConfig class:
---
    @Configuration
    public class BeanConfig {
    
        @Bean
        public DataSource dataSource(){
            return new DataSource();
        }
    
        @Bean
        public CatService catService() {
            return new CatService(dataSource());
        }
    
    }
---

- Create ApplicationContext:
---
    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfig.class);
---

- Get Beans:
---
    applicationContext.getBean(CatService.class);
    applicationContext.getBean(DataSource.class);
---

#### WAY 2:
- Add @Component annotation to Class:
---
    @Component
    public class DataSource {
    
        public List getCats() {
            return Arrays.asList("Leo", "Oliver", "Max");
        }
    }

---
- Create a BeanConfig class:
---
    @Configuration
    @ComponentScan
    public class BeanConfig {
    
    }
---

- Create ApplicationContext:
---
    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfig.class);
---

- Get Beans:
---
    applicationContext.getBean(DataSource.class);
---

*NOTE* : Adding "@SpringBootApplication" to main method does not require BeanConfig class.

---
    @SpringBootApplication
    public class DependencyInjectionWorkingApplication {    
        public static void main(String[] args) {
            ApplicationContext applicationContext = SpringApplication.run(DependencyInjectionWorkingApplication.class, args);
            CatService catService = applicationContext.getBean(CatService.class);
            catService.getCatCount();
        }
    }
---

### Annotations:
 - *@Bean*:
    - It tells Spring that this method will return an instance that should be managed by ApplicationContext.
    - Bean, in spring is an instance of class created by Spring ApplicationContext.
        - Spring ApplicationContext is where Spring holds instances of objects that it has identified to be managed and distributed automatically. These are called beans

 - *@Component*:
    - It allows Spring to automatically detect our custom beans
    - In other words, without having to write any explicit code, Spring will:
        - Scan our application for classes annotated with @Component
        - Instantiate them and inject any specified dependencies into them
        - Inject them wherever needed
    - @Controller, @Service and @Repository. They all provide the same function as @Component.
 - @ComponentScan:
    - Spring uses the @ComponentScan annotation to  search and gather all @Component in that package into its ApplicationContext

### SAMPLE DEBUG OUTPUT:
`
22:28:02.082 [main] DEBUG org.springframework.context.annotation.AnnotationConfigApplicationContext - Refreshing org.springframework.context.annotation.AnnotationConfigApplicationContext@351d00c0
22:28:02.105 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'org.springframework.context.annotation.internalConfigurationAnnotationProcessor'
22:28:02.347 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'org.springframework.context.event.internalEventListenerProcessor'
22:28:02.350 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'org.springframework.context.event.internalEventListenerFactory'
22:28:02.352 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'org.springframework.context.annotation.internalAutowiredAnnotationProcessor'
22:28:02.354 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'org.springframework.context.annotation.internalCommonAnnotationProcessor'
22:28:02.367 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'beanConfig'
22:28:02.377 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'dataSource'
22:28:02.399 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'catService'
22:28:02.414 [main] DEBUG org.springframework.core.env.PropertySourcesPropertyResolver - Found key 'spring.liveBeansView.mbeanDomain' in PropertySource 'systemProperties' with value of type String
Number of Cats: 3
`

### @Component vs @Bean

* @Bean is also an annotation that Spring uses to gather beans at runtime, but it's not used at the class level. 
Instead, we annotate methods with @Bean so that Spring can store the method's result as a Spring bean

* @Component is a class-level annotation, but @Bean is at the method level, so @Component is only an option when a class's source code is editable. @Bean can always be used, but it's more verbose.
* @Component is compatible with Spring's auto-detection, but @Bean requires manual class instantiation
* Using @Bean decouples the instantiation of the bean from its class definition. This is why we can use it to make even third-party classes into Spring beans. It also means we can introduce logic to decide which of several possible instance options for a bean to use.