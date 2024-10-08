//package S2021_1_A_2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N; // 격자의 크기
	static int M; // 총 라운드 수
	static int[][] map;
	static int[][] test_map;
	static int[] arr;
	static int d;
	static int p;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static int answer;
	// 몬스터는 1,2,3번

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("src/S2021_1_A_2/input.txt"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		arr = new int[N * N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		answer = 0;

		for (int round = 1; round <= M; round++) {
			st = new StringTokenizer(br.readLine());
			d = Integer.parseInt(st.nextToken()); // 공격 방향
			p = Integer.parseInt(st.nextToken()); // 공격 칸 수

			// 공격하기
			attack();
			// System.out.println("====AFTER ATTACK====");
			// for (int i = 0; i < N; i++)
			// 	System.out.println(Arrays.toString(map[i]));

			// map to Array
			test();
			map_to_array();
			//System.out.println("====TO ARRAY====");
			//System.out.println(Arrays.toString(arr));

			while (true) {
				// 빈 공간 채우기
				arr = move();
				//System.out.println("====AFTER MOVE====");
				//System.out.println(Arrays.toString(arr));

				// 4개 이상 지우기
				boolean flag = remove();
				//System.out.println("====AFTER REMOVE====");
				//System.out.println(Arrays.toString(arr));
				if (!flag)
					break;
			}

			// 짝짓기
			arr = join();
			//System.out.println("====AFTER JOIN====");
			//System.out.println(Arrays.toString(arr));

			// 다시 맵으로
			array_to_map();
			//System.out.println("====AFTER MAP====");
			//for (int i = 0; i < N; i++)
			//	System.out.println(Arrays.toString(map[i]));
		}
		System.out.println(answer);

	}

	static void array_to_map() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int n = test_map[i][j];
				map[i][j] = arr[n];
			}
		}
	}

	static int[] join() {
		int[] result = new int[N * N];
		int pre = arr[1];
		int count = 1;
		int index = 1;
		for (int i = 2; i < N * N; i++) {
			if (pre == 0 && arr[i] == 0)
				continue;
			if (arr[i] == pre) {
				count++;
			} else {
				result[index++] = count;
				if(index>=N*N) break;
				result[index++] = pre;
				if(index>=N*N) break;
				pre = arr[i];
				count = 1;
			}
		}
		return result;
	}

	static boolean remove() {
		int pre = arr[1];
		boolean result = false;
		Queue<Integer> q = new ArrayDeque<>();
		q.add(1);
		for (int i = 2; i < N * N; i++) {
			if (arr[i] == pre) {
				q.add(i);
			} else {
				if (q.size() >= 4) {
					while (!q.isEmpty()) {
						result = true;
						int num = q.poll();
						answer += arr[num];
						arr[num] = 0;
					}
				}
				q.clear();
				pre = arr[i];
				q.add(i);
			}
		}
		return result;
	}

	static int[] move() {
		Queue<Integer> q = new ArrayDeque<>();
		for (int i = 0; i < N * N; i++) {
			if (arr[i] != 0)
				q.add(arr[i]);
		}
		int[] result = new int[N * N];
		int index = 1;
		while (!q.isEmpty()) {
			result[index] = q.poll();
			index++;
		}
		return result;
	}

	static void map_to_array() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				arr[test_map[i][j]] = map[i][j];
			}
		}
	}

	static void test() {
		test_map = new int[N][N];
		int nowx = N / 2;
		int nowy = N / 2;
		int num = 1;
		for (int i = 1; i <= (N - 1); i++) {
			if (i % 2 == 1) {
				for (int j = 1; j <= i; j++) {
					nowx = nowx + dx[2];
					nowy = nowy + dy[2];
					test_map[nowx][nowy] = num++;
				}
				for (int j = 1; j <= i; j++) {
					nowx = nowx + dx[1];
					nowy = nowy + dy[1];
					test_map[nowx][nowy] = num++;
				}
			} else {
				for (int j = 1; j <= i; j++) {
					nowx = nowx + dx[0];
					nowy = nowy + dy[0];
					test_map[nowx][nowy] = num++;
				}
				for (int j = 1; j <= i; j++) {
					nowx = nowx + dx[3];
					nowy = nowy + dy[3];
					test_map[nowx][nowy] = num++;
				}
			}
		}
		for (int j = 1; j < N; j++) {
			nowx = nowx + dx[2];
			nowy = nowy + dy[2];
			test_map[nowx][nowy] = num++;
		}
	}

	static void attack() {
		int mid = N / 2;
		for (int i = 1; i <= p; i++) {
			int nx = mid + dx[d] * i;
			int ny = mid + dy[d] * i;
			answer += map[nx][ny];
			map[nx][ny] = 0;
		}
	}

}
