package anshay.notebook.stackandqueen;

/**
 * 图像渲染
 * <p>
 * 有一幅以二维整数数组表示的图画，每一个整数表示该图画的像素值大小，数值在 0 到 65535 之间。
 * 给你一个坐标 (sr, sc) 表示图像渲染开始的像素值（行 ，列）和一个新的颜色值 newColor，让你重新上色这幅图像。
 * 为了完成上色工作，从初始坐标开始，记录初始坐标的上下左右四个方向上像素值与初始坐标相同的相连像素点，接着再记录这四个方向上符合条件的像素点与他们对应四个方向上像素值与初始坐标相同的相连像素点，……，重复该过程。将所有有记录的像素点的颜色值改为新的颜色值。
 * 最后返回经过上色渲染后的图像
 * <p>
 * 思路，和
 *
 * @author: Anshay
 * @date: 2019/5/3
 */
public class Solution8 {

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image == null) {
            return image;
        }
        boolean[][] visited = new boolean[image.length][image[0].length];
        int oldColor = image[sr][sc];
        if (oldColor == newColor) {
            return image;
        }
        floodFill(image, sr, sc, newColor, oldColor, visited);
        return image;
    }

    public void floodFill(int[][] image, int sr, int sc, int newColor, int oldColor, boolean[][] visited) {
        if (sr < 0 || sr >= image.length || sc < 0 || sc >= image[0].length ||
                image[sr][sc] != oldColor || visited[sr][sc]) {
            return;
        }
        image[sr][sc] = newColor;
        visited[sr][sc] = true;
        floodFill(image, sr + 1, sc, newColor, oldColor, visited);
        floodFill(image, sr - 1, sc, newColor, oldColor, visited);
        floodFill(image, sr, sc + 1, newColor, oldColor, visited);
        floodFill(image, sr, sc - 1, newColor, oldColor, visited);
    }
}
