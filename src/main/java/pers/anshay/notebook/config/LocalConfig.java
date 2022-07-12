package pers.anshay.notebook.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 多数据配置
 *
 * @author machao
 * @date 2022/7/12
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "local.config")
public class LocalConfig {
    private List<String[]> ips;
}
