#### 启动流程

#### 核心注解/自动装配

自动装配原理：
主要看主方法上面的那个@SpringBootApplication 注解
@SpringBootApplication：这是一个组合注解，里面主要有三个注解涉及到了springboot的自动装配原理

- @ConponentScan：指定扫描包的路径
- @SpringbootConfiguration
    - @Configuration：表明该类是spring的一个配置类
        - @Component：表明该类是组件
- **@EnableAutoConfiguration**(核心注解)
    - @AutoConfigurationPackage：自动装配包
        - @Import(**AutoconfigurationPackages.Registrar.class**)：在Registrar的**registerBeanDefinitions**方法中，将主配置类所在的包、以及子包里所有的组件加载到spring容器中
    - @Import(**AutoConfigurationImportSelector.class**)：导入了**组件选择器**，通过对应的selectImports方法，从MAETA-INFO/spring.fictories中获取EnableAutoConfiguration中指定的值，将这些值作为自动装配类导入到容器中，自动装配就生效了。

1 其中，@SpringBootConfiguration也是一个组合注解，里面有一个@Configuration注解，表示这个类是spring的一个配置类，并且这个注解也是一个组合注解，里面有个@Component，表示这个配置类是spring的一个组件

2 对于@EnableAutoConfiguration，这是开启自动装配的核心注解，也是一个组合注解，里面有一个@AutoConfigurationPackage注解，这个注解是自动装配包，这里面有个@Import注解，主要是使用@Import注解给spring容器导入一个组件，这里导入的是Registrar.class，这个Registar中有一个方法可以获取扫描的包路径，就是将主配置类的所在包、以及子包里的所有组件扫描加载到spring容器中。
    在@EnableAutoConfiguration中，还有一个@Import注解，这里导入了一个组件的选择器：AutoConfigurationImportSelector，这个组件里有个selectImports方法，它会从MAETA-INFO/spring.factories中获取EnableAutoConfiguration中指定的值，将这些值作为自动装配类导入到容器中，自动装配就生效了。

#### 有没有写过starter

手写starter的步骤

1. 引入SpringBoot相关依赖：spring-boot-starter
2. 创建自动装配类，并注入容器
3. 在工程的resources包下创建META-INF/spring.factories文件，激活自动装配
4. 在仓库发布starter

#### starter如何保证加载顺序



#### springboot是如何启动的





#### 约定大于配置的体现

在开发中，如果没有配置某些值，程序会取一个默认值。可以减少很多配置。

比如在maven结构中，

- /src/main/java目录用来存放java源文件
- src/main/resources目录用来存放资源文件，如application.yml文件，mybatis的*mapper.xml文件
- /src/test/java目录用来存放java测试文件
- /src/test/resources目录用来存放测试资源文件
- /target目录为项目的输出位置
- pom中引入部分jar，springboot会自动的生成对应的bean