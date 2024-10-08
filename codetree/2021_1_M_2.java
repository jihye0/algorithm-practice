//package S2021_1_M_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Queue;
import java.util.StringTokenizer;

class Bomb {
	int size;
	int red_count;
	int point_x;
	int point_y;

	public Bomb(int size, int red_count, int point_x, int point_y) {
		this.size = size;
		this.red_count = red_count;
		this.point_x = point_x;
		this.point_y = point_y;
	}

	@Override
	public String toString() {
		return "Bomb [size=" + size + ", red_count=" + red_count + ", point_x=" + point_x + ", point_y=" + point_y
				+ "]";
	}

	public static Comparator<Bomb> comparator = new Comparator<Bomb>() {
		public int compare(Bomb b1, Bomb b2) {
			// size가 큰 순서대로
			if (b1.size != b2.size) {
				return b2.size - b1.size;
			}
			// redcount가 작은 순서대로
			if (b1.red_count != b2.red_count) {
				return b1.red_count - b2.red_count;
			}
			// x가 큰 순서대로
			if (b1.point_x != b2.point_x) {
				return b2.point_x - b1.point_x;
			}
			return b1.point_y - b2.point_y;
		}
	};
}

public class Main {
	static int N; // 격자의 크기
	static int M; // 빨간색 이외의 서로 다른 폭탄의 종류
	static int[][] map; // -1 검은색 돌 0 빨간색 폭탄
	static boolean[][] visited;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static ArrayList<Bomb> bomb_list;
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		bomb_list = new ArrayList<Bomb>();
		// int round = 1;
		while (true) {
			// round--;
			// 가장 크기가 큰 것 찾기
			bomb_list.clear();
			find_bomb();
			// System.out.println(bomb_list.toString());
			if (bomb_list.size() == 0) {
				//System.out.println("====finish====");
				break;
			}
			// bomb_list 정렬하기
			Collections.sort(bomb_list, Bomb.comparator);
			//System.out.println(bomb_list.toString());

			// 없애기
			remove();
			// System.out.println("===AFTER REMOVE=====");
			// for (int i = 0; i < N; i++)
			// 	System.out.println(Arrays.toString(map[i]));

			// 중력 작용
			gravity();
			// System.out.println("===AFTER GRAVITY=====");
			// for (int i = 0; i < N; i++)
			// 	System.out.println(Arrays.toString(map[i]));

			rotate();
			// 반시계 반향으로
			// System.out.println("===AFTER ROTATE=====");
			// for (int i = 0; i < N; i++)
			// 	System.out.println(Arrays.toString(map[i]));
			// 중력 작용
			gravity();
			// System.out.println("===AFTER GRAVITY=====");
			// for (int i = 0; i < N; i++)
			// 	System.out.println(Arrays.toString(map[i]));
			// System.out.println("===finish===");
			// System.out.println(answer);
		}
		System.out.println(answer);
	}

	static void rotate() {
		int[][] copy_map = new int[N][N];
		for (int i = 0; i < N; i++)
			copy_map[i] = map[i].clone();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = copy_map[j][(N - 1) - i];
			}
		}
	}

	static void gravity() {
		for (int j = 0; j < N; j++) {
			int floor = -1;
			for (int i = (N - 1); i >= 0; i--) {
				if (map[i][j] == -2) {
					if (floor == -1)
						floor = i;
				} else if (map[i][j] == -1) {
					floor = -1;
				} else {
					if (floor != -1) {
						map[floor][j] = map[i][j];
						map[i][j] = -2;
						floor--;
					}
				}
			}
		}
	}

	static void remove() {
		// visited = new boolean[N][N];
		for (int i = 0; i < N; i++)
			Arrays.fill(visited[i], false);
		Bomb target_bomb = bomb_list.get(0);
		int target_num = map[target_bomb.point_x][target_bomb.point_y];
		Queue<int[]> q = new ArrayDeque();
		q.add(new int[] { target_bomb.point_x, target_bomb.point_y });
		visited[target_bomb.point_x][target_bomb.point_y] = true;
		while (!q.isEmpty()) {
			int[] now = q.poll();
			map[now[0]][now[1]] = -2;
			for (int d = 0; d < 4; d++) {
				int nx = now[0] + dx[d];
				int ny = now[1] + dy[d];
				if (!onBoard(nx, ny))
					continue;
				if (visited[nx][ny])
					continue;
				if (map[nx][ny] < 0)
					continue;
				if (map[nx][ny] > 0 && map[nx][ny] != target_num)
					continue;
				visited[nx][ny] = true;
				q.add(new int[] { nx, ny });
			}
		}
		answer += target_bomb.size * target_bomb.size;
	}

	static void find_bomb() {
		// visited = new boolean[N][N];
		for (int i = 0; i < N; i++)
			Arrays.fill(visited[i], false);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j] && map[i][j] > 0) {
					int[] result = bfs(i, j);
					if (result[0] >= 2) {
						bomb_list.add(new Bomb(result[0], result[1], result[2], result[3]));
					}
				}
			}
		}
	}

	static int[] bfs(int x, int y) {
		Queue<int[]> q = new ArrayDeque();
		ArrayList<int[]> red_list=new ArrayList<>();
		int size = 1;
		int red_count = 0;
		int point_x = x;
		int point_y = y;
		q.offer(new int[] { x, y });
		visited[x][y] = true;
		while (!q.isEmpty()) {
			int[] now = q.poll();
			for (int d = 0; d < 4; d++) {
				int nx = now[0] + dx[d];
				int ny = now[1] + dy[d];
				if (!onBoard(nx, ny))
					continue;
				if (visited[nx][ny])
					continue;
				if (map[nx][ny] == -1 || map[nx][ny] == -2)
					continue;
				if (map[nx][ny] > 0 && map[nx][ny] != map[x][y])
					continue;
				if (map[nx][ny] == 0) {
					red_count++;
					red_list.add(new int[] {nx, ny});
				}
				// 기준점 찾기
				if (map[nx][ny] != 0) {
					if (nx > point_x) {
						point_x = nx;
						point_y = ny;
					} else if (nx == point_x) {
						if (ny < point_y) {
							point_x = nx;
							point_y = ny;
						}
					}
				}
				size++;
				visited[nx][ny] = true;
				q.offer(new int[] { nx, ny });
			}
		}
		for(int i=0; i<red_list.size(); i++) {
			visited[red_list.get(i)[0]][red_list.get(i)[1]]=false;
		}
		int[] result = new int[4];
		result[0] = size;
		result[1] = red_count;
		result[2] = point_x;
		result[3] = point_y;
		return result;
	}

	static boolean onBoard(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= N)
			return false;
		return true;
	}

}
