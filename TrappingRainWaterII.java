import java.util.*;
class Solution {
    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length, n = heightMap[0].length;
        if (m <= 2 || n <= 2) return 0;
        PriorityQueue<Cell> pq = new PriorityQueue<>(Comparator.comparingInt(c -> c.height));
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            pq.offer(new Cell(i, 0, heightMap[i][0]));
            pq.offer(new Cell(i, n - 1, heightMap[i][n - 1]));
            visited[i][0] = visited[i][n - 1] = true;
        }
        for (int j = 0; j < n; j++) {
            pq.offer(new Cell(0, j, heightMap[0][j]));
            pq.offer(new Cell(m - 1, j, heightMap[m - 1][j]));
            visited[0][j] = visited[m - 1][j] = true;
        }
        int water = 0;
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!pq.isEmpty()) {
            Cell cell = pq.poll();
            for (int[] d : dirs) {
                int nx = cell.x + d[0];
                int ny = cell.y + d[1];
                if (nx < 0 || ny < 0 || nx >= m || ny >= n || visited[nx][ny]) continue;
                visited[nx][ny] = true;
                if (heightMap[nx][ny] < cell.height) {
                    water += cell.height - heightMap[nx][ny];
                }
                pq.offer(new Cell(nx, ny, Math.max(heightMap[nx][ny], cell.height)));
            }
        }

        return water;
    }
    static class Cell {
        int x, y, height;
        Cell(int x, int y, int height) {
            this.x = x;
            this.y = y;
            this.height = height;
        }
    }
}
