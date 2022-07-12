package pers.anshay.notebook.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 本地用户配置
 *
 * @author machao
 * @date 2022/7/12
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "local-user")
public class LocalUserConfig {
    private String user;
    private String password;

    @Override
    public String toString() {
        return "LocalUserConfig{" +
                "user='" + user + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
