package pers.anshay.notebook.algorithm.smoothweightround;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 服务器实体类
 *
 * @author machao
 * @date 2022/5/30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmoothServer {

    private String ip;

    private int weight;

    private int curWeight;

}

