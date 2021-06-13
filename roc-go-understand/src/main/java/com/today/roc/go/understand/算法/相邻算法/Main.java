package com.today.roc.go.understand.算法.相邻算法;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年04月26日 21:38*
 * log.info()
 */
public class Main {
    static int     m    = 5;
    static int     n    = 11;
    static int[][] flag = new int[m][n];

    {
        int[] a1 = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] a2 = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] a3 = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] a4 = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] a5 = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        flag[0] = a1;
        flag[1] = a2;
        flag[2] = a3;
        flag[3] = a4;
        flag[4] = a5;
    }

    public static void main(String[] args) {
        int[][] graph = new int[5][11];
        int[] a1 = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] a2 = new int[]{0, 0, 0, 1, 1, 1, 1, 0, 1, 0, 0};
        int[] a3 = new int[]{0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1};
        int[] a4 = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0};
        int[] a5 = new int[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
        graph[0] = a1;
        graph[1] = a2;
        graph[2] = a3;
        graph[3] = a4;
        graph[4] = a5;

        int area = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0 && graph[i - 1][j] == 1) {
                    continue;
                }
                if (j > 0 && graph[i][j - 1] == 1) {
                    continue;
                }
                if (graph[i][j] == 1) {
                    flag[i][j] = 1;
                    int a = 1 + getArea(graph, i + 1, j) + getArea(graph, i, j + 1);
                    if (a > area) {
                        area = a;
                    }
                }
            }
        }
        System.out.println(area);
    }

    public static int getArea(int[][] graph, int i, int j) {
        if (i >= m || j >= n || j < 0 || i < 0) {
            return 0;
        }
        if (graph[i][j] == 0) {
            return 0;
        }
        flag[i][j] = 1;
        return 1
                + (j + 1 < n && flag[i][j + 1] == 0 ? getArea(graph, i, j + 1) : 0)
                + (j > 0 && flag[i][j - 1] == 0 ? getArea(graph, i, j - 1) : 0)
                + (i + 1 < m && flag[i + 1][j] == 0 ? getArea(graph, i + 1, j) : 0);
    }
}

