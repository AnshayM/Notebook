
package pers.anshay.notebook.test;

import lombok.Data;

import java.io.Serializable;

@Data
public class YugaoSubscribe implements Serializable
{
    /**
     * 系统时间
     */
    private long sysTime;
    /**
     * 坐席Id
     */
    private String agentid;
    /**
     * 分机号
     */
    private String mobno;
    /**
     * 订阅状态 订阅/取消订阅
     */
    private String pstatus;
    /**
     * 业务Id
     */
    private String busId;
}