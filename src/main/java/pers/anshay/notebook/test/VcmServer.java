package pers.anshay.notebook.test;

import lombok.Data;

/**
 * @author machao
 * @date 2021/6/11
 */
@Data
public class VcmServer {

    /**
     * vcm 注解
     */
    private String host;

    /**
     * port
     */
    private Integer updPort;

    /**
     *
     */
    private Integer httpPort;

    /**
     * 监控检查url
     */
    private String healthUrl;


    public VcmServer(String host,Integer updPort, Integer httpPort,String healthUri) {
        this.host = host;
        this.httpPort = httpPort;
        this.updPort = updPort;
        this.healthUrl = "http://" + host + ":" + httpPort + healthUri;

    }
}
