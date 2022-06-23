package pers.anshay.notebook.test;

import lombok.Data;

/**
 * @author machao
 * @date 2021/6/11
 */
@Data
public class AgentInfo {

    /**
     * 业务id
     */
    private String busId;

    /**
     * vcm信息
     */
    private VcmServer vcmServer;


    /**
     * 分机号
     */
    private String extNo;

    public AgentInfo() {
    }

    public AgentInfo(String busId, VcmServer vcmServer, String extNo) {
        this.busId = busId;
        this.vcmServer = vcmServer;
        this.extNo = extNo;
    }
}
