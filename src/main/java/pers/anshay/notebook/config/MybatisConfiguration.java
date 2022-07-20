package pers.anshay.notebook.config;

import com.baomidou.mybatisplus.core.config.GlobalConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pers.anshay.notebook.handler.MetaHandler;

/**
 * @author machao
 * @date 2022/7/20
 */
@Configuration
public class MybatisConfiguration {

    /*@Bean
    public GlobalConfig globalConfig() {
        // todo 验证一下不加有没有问题
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setMetaObjectHandler(new MetaHandler());
        return globalConfig;
    }*/

}
