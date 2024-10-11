//package S2024_1_A_1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

class Golem {
	int x;
	int y;
	int d;

	public Golem(int x, int y, int d) {
		super();
		this.x = x;
		this.y = y;
		this.d = d;
	}

	@Override
	public String toString() {
		return "Golem [x=" + x + ", y=" + y + ", d=" + d + "]";
	}
}

public class Main {
	static int R; // 숲의 크기
	static int C;
	static int K; // 정령의 수
	static int[][] map;
	static Golem[] golem_list;
	static boolean[][] exit_map;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("src/S2024_1_A_1/input.txt"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[R + 1][C + 1];
		golem_list = new Golem[K + 1];
		exit_map = new boolean[R + 1][C + 1];
		int answer = 0;
		for (int k = 1; k <= K; k++) {
			//System.out.println("====" + k + "번째 정령 출발=====");
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int nowX = -1;
			int nowY = -1;
			golem_list[k] = new Golem(-1, c, d);
			// golem_list[k].x++;

			while (canSouth(golem_list[k].x, golem_list[k].y) || canWest(golem_list[k].x, golem_list[k].y)
					|| canEast(golem_list[k].x, golem_list[k].y)) {
				if (canSouth(golem_list[k].x, golem_list[k].y)) {
					golem_list[k].x++;
				}else if (canWest(golem_list[k].x, golem_list[k].y)) {
					golem_list[k].x++;
					golem_list[k].y--;
					if (golem_list[k].d == 0) {
						golem_list[k].d = 3;
					} else if (golem_list[k].d == 1) {
						golem_list[k].d = 0;
					} else if (golem_list[k].d == 2) {
						golem_list[k].d = 1;
					} else if (golem_list[k].d == 3) {
						golem_list[k].d = 2;
					}
				}else if(canEast(golem_list[k].x, golem_list[k].y)) {
					golem_list[k].x++;
					golem_list[k].y++;
					if (golem_list[k].d == 0) {
						golem_list[k].d = 1;
					} else if (golem_list[k].d == 1) {
						golem_list[k].d = 2;
					} else if (golem_list[k].d == 2) {
						golem_list[k].d = 3;
					} else if (golem_list[k].d == 3) {
						golem_list[k].d = 0;
					}
				}
				
			}
			if (golem_list[k].x <= 1) {
				//System.out.println("reset~~~~");
				map = new int[R + 1][C + 1];
				exit_map = new boolean[R + 1][C + 1];
				continue;
			}
			// map에 그리기
			drawMap(k);
			// for (int i = 1; i <= R; i++) {
			// 	System.out.println(Arrays.toString(map[i]));
			// }
			// for (int i = 1; i <= R; i++) {
			// 	System.out.println(Arrays.toString(exit_map[i]));
			// }
			answer += bfs(k);
		}
		System.out.println(answer);
	}

	static int bfs(int k) {
		visited = new boolean[R + 1][C + 1];
		int x = golem_list[k].x;
		int y = golem_list[k].y;
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { x, y });
		visited[x][y] = true;
		int max_x = x;
		while (!q.isEmpty()) {
			int[] now = q.poll();
			max_x = Math.max(now[0], max_x);
			for (int i = 0; i < 4; i++) {
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];
				if (!onBoard(nx, ny))
					continue;
				if (visited[nx][ny])
					continue;
				if (map[nx][ny] == 0)
					continue;
				if (map[nx][ny] == map[now[0]][now[1]]) {
					q.add(new int[] { nx, ny });
					// max_x=Math.max(nx, max_x);
					visited[nx][ny] = true;
				} else {
					if (exit_map[now[0]][now[1]]) {
						q.add(new int[] { nx, ny });
						// max_x=Math.max(nx, max_x);
						visited[nx][ny] = true;
					}
				}
			}
		}
		//System.out.println("max_x  " + max_x);

		return max_x;
	}

	static void drawMap(int k) {
		int x = golem_list[k].x;
		int y = golem_list[k].y;
		int d = golem_list[k].d;
		map[x][y] = k;
		map[x + 1][y] = k;
		map[x - 1][y] = k;
		map[x][y + 1] = k;
		map[x][y - 1] = k;
		exit_map[x + dx[d]][y + dy[d]] = true;
	}

	static boolean canEast(int x, int y) {
		if (isAvail(x - 1, y + 1) && isAvail(x, y + 2) && isAvail(x + 1, y + 1) && isAvail(x + 1, y + 2)
				&& isAvail(x + 2, y + 1))
			return true;
		return false;
	}


	static boolean canWest(int x, int y) {
		if (isAvail(x - 1, y - 1) && isAvail(x, y - 2) && isAvail(x + 1, y - 1) && isAvail(x + 1, y - 2)
				&& isAvail(x + 2, y - 1))
			return true;
		return false;
	}


	static boolean canSouth(int x, int y) {
		if (isAvail(x + 1, y - 1) && isAvail(x + 1, y + 1) && isAvail(x + 2, y))
			return true;
		return false;
	}
 
	static boolean isAvail(int x, int y) {
		if (x < 0 && y >= 1 && y <= C)
			return true;
		if (onBoard(x, y) && map[x][y] == 0)
			return true;
		return false;
	}

	static boolean onBoard(int x, int y) {
		if (x < 0 || x > R || y <= 0 || y > C)
			return false;
		return true;
	}

}
