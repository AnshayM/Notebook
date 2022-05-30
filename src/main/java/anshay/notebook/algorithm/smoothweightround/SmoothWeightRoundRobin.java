package anshay.notebook.algorithm.smoothweightround;

import java.util.ArrayList;
import java.util.List;


/**
 * 平滑权衡算法
 *
 * @author machao
 * @date 2022/5/30
 */
public class SmoothWeightRoundRobin {

    /**
     * 初始化所有的服务器
     **/
    List<SmoothServer> servers = new ArrayList<>();

    /**
     * 服务器权重总和
     */
    private int weightCount;

    public void init(List<SmoothServer> servers) {
        this.servers = servers;
        this.weightCount = this.servers.stream().map(SmoothServer::getWeight).reduce(0, Integer::sum);

    }

    /**
     * 获取需要执行的服务器
     **/
    public SmoothServer getServer() {
        SmoothServer tmpSv = null;

        for (SmoothServer sv : servers) {
            sv.setCurWeight(sv.getWeight() + sv.getCurWeight());
            if (tmpSv == null || tmpSv.getCurWeight() < sv.getCurWeight()) tmpSv = sv;
        }

        assert tmpSv != null;
        tmpSv.setCurWeight(tmpSv.getCurWeight() - weightCount);
        return tmpSv;
    }

}

