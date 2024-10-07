//package S23_2M;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int K; // 탐사반복 횟수
	static int M; // 벽면에 적힌 유물 조각의 개수
	static int[][] map;
	static int[] arr;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[5][5];
		StringBuilder sb=new StringBuilder();
		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		arr = new int[M];
		for (int i = 0; i < M; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int start = 0;

		for (int test = 1; test <= K; test++) {
			int answer = 0;
			// 가장 큰 회전 좌표 + 각을 찾기
			int[][] tmp_map = find_max();
			for (int i = 0; i < 5; i++)
				map[i] = tmp_map[i].clone();
			// System.out.println("====find max====");
			// for (int i = 0; i < 5; i++)
			// 	System.out.println(Arrays.toString(map[i]));
			// System.out.println("=================");
			if (map[0][0] == 0)
				break;

			// 계속해서 없애주기
			int bomb_count = 0;
			while (true) {
				boolean[][] visited = new boolean[5][5];
				bomb_count = 0;
				boolean flag = false;
				for (int i = 0; i < 5; i++) {
					for (int j = 0; j < 5; j++) {
						if (!visited[i][j]) {
							Queue<int[]> q = new LinkedList<>();
							q.add(new int[] { i, j });
							visited[i][j] = true;
							int count = 1;
							while (!q.isEmpty()) {
								int[] cur = q.poll();
								for (int d = 0; d < 4; d++) {
									int nx = cur[0] + dx[d];
									int ny = cur[1] + dy[d];
									if (!onBoard(nx, ny))
										continue;
									if (visited[nx][ny])
										continue;
									if (map[nx][ny] == map[i][j]) {
										q.add(new int[] { nx, ny });
										count++;
										visited[nx][ny] = true;
									}
								}
							}
							if (count >= 3) {
								flag = true;
								remove(i, j);
								answer+=count;
								//System.out.println("ANS: "+answer);
							}
						}
					}
				}
				// System.out.println("====remove====");
				// for (int i = 0; i < 5; i++)
				// 	System.out.println(Arrays.toString(map[i]));
				// System.out.println("=================");
				if (flag == false)
					break;
				for(int j=0; j<5; j++) {
					for(int i=4; i>=0; i--) {
						if(map[i][j]==0) {
							map[i][j]=arr[start];
							start++;
						}
					}
				}
//				System.out.println("====fill====");
//				for (int i = 0; i < 5; i++)
//					System.out.println(Arrays.toString(map[i]));
//				System.out.println("=================");
			}
			sb.append(answer).append(" ");
		}
		System.out.println(sb);
	}

	static void remove(int x, int y) {
		boolean[][] visit = new boolean[5][5];
		visit[x][y] = true;
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { x, y });
		int n = map[x][y];
		map[x][y] = 0;

		while (!q.isEmpty()) {
			int[] pos = q.poll();
			for (int d = 0; d < 4; ++d) {
				int nx = pos[0] + dx[d];
				int ny = pos[1] + dy[d];
				if (!onBoard(nx, ny))
					continue;
				if (!visit[nx][ny] && map[nx][ny] == n) {
					visit[nx][ny] = true;
					map[nx][ny] = 0;
					q.add(new int[] { nx, ny });
				}
			}
		}
	}

	static int[][] find_max() {
		int max_count = 0;
		int[][] max_map = new int[5][5];
		for (int rotationCount = 1; rotationCount <= 3; rotationCount++) { // 회전 횟수
			for (int y = 1; y <= 3; y++) {
				for (int x = 1; x <= 3; x++) {
					// 배열 떼어내기
					int[][] test_map = new int[5][5];
					int[][] small_map = new int[3][3];
					for (int i = -1; i <= 1; i++) {
						for (int j = -1; j <= 1; j++) {
							small_map[i + 1][j + 1] = map[x + i][y + j];
						}
					}

					// rotationCount번 회전하기
					int[][] rotated_map = small_map;
					int tempRotation = rotationCount; // 회전 횟수 관리 변수
					while (tempRotation > 0) {
						rotated_map = rotate(rotated_map);
						tempRotation--;
					}

					// test할 맵 채우기
					for (int i = -1; i <= 1; i++) {
						for (int j = -1; j <= 1; j++) {
							test_map[x + i][y + j] = rotated_map[i + 1][j + 1];
						}
					}
					for (int i = 0; i < 5; i++) {
						for (int j = 0; j < 5; j++) {
							if (test_map[i][j] == 0)
								test_map[i][j] = map[i][j];
						}
					}

					// test_map에서 폭탄 얼마나?
					int test_count = bomb_count(test_map);
					if (test_count > max_count) {
						max_count = test_count;
						for (int i = 0; i < 5; i++)
							max_map[i] = test_map[i].clone();
					}
				}
			}
		}

		//System.out.println("Maximum count: " + max_count);
		return max_map;
	}

	static int bomb_count(int[][] arr) {
		boolean[][] visited = new boolean[5][5];
		int bomb_count = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (!visited[i][j]) {
					Queue<int[]> q = new LinkedList<>();
					q.add(new int[] { i, j });
					visited[i][j] = true;
					int count = 1;
					while (!q.isEmpty()) {
						int[] cur = q.poll();
						for (int d = 0; d < 4; d++) {
							int nx = cur[0] + dx[d];
							int ny = cur[1] + dy[d];
							if (!onBoard(nx, ny))
								continue;
							if (visited[nx][ny])
								continue;
							if (arr[nx][ny] == arr[i][j]) {
								q.add(new int[] { nx, ny });
								count++;
								visited[nx][ny] = true;
							}
						}
					}
					if (count >= 3) {
						bomb_count += count;
					}
				}
			}
		}
		return bomb_count;
	}

	static int[][] rotate(int[][] test_map) {
		int[][] result = new int[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				result[i][j] = test_map[2 - j][i];
			}
		}
		return result;
	}

	static boolean onBoard(int x, int y) {
		if (x < 0 || x >= 5 || y < 0 || y >= 5)
			return false;
		return true;
	}

}
