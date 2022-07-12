package pers.anshay.notebook.config;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

/**
 * 多数据配置
 *
 * @author machao
 * @date 2022/7/12
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "local-config")
public class LocalConfig {
    private List<String[]> ips;

    private Map<String, String> map;


    @Override
    public String toString() {
        return "LocalConfig{" +
                "ips=" + JSON.toJSONString(ips) +
                ", map=" + JSON.toJSONString(map) +
                '}';
    }


}
