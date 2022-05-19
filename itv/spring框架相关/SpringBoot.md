#### 启动流程

#### 核心注解


#### 自动装配
自动装配原理：
主要看主方法上面的那个@SpringBootApplication 注解
@SpringBootApplication：这是一个组合注解，里面主要有三个注解涉及到了springboot的自动装配原理
1. @SpringbootConfiguration
2. @EnableAutoConfiguration
3. @ConponentScan

1 其中，@SpringBootConfiguration也是一个组合注解，里面有一个@Configuration注解，表示这个类是spring的一个配置类，并且这个注解也是一个组合注解，里面有个@Component，表示这个配置类是spring的一个组件

2 对于@EnableAutoConfiguration，这是开启自动装配的核心注解，也是一个组合注解，里面有一个@AutoConfigurationPackage注解，这个注解是自动装配包，这里面有个@Import注解，主要是使用@Import注解给spring容器导入一个组件，这里导入的是Registrar.class，这个Registar中有一个方法可以获取扫描的包路径，就是将主配置类的所在包、以及子包里的所有组件扫描加载到spring容器中。
    在@EnableAutoConfiguration中，还有一个@Import注解，这里导入了一个组件的选择器：AutoConfigurationImportSelector，这个组件里有个selectImports方法，它会从MAETA-INFO/spring.fictories中获取EnableAutoConfiguration中指定的值，将这些值作为自动装配类导入到容器中，自动装配就生效了。



#### starter如何保证加载顺序



#### springboot是如何启动的