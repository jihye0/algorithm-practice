//package S2021_1_A_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Nutrition {
	int x;
	int y;

	public Nutrition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "Nutrition [x=" + x + ", y=" + y + "]";
	}

}

public class Main {
	static int N; // 격자의 크기
	static int M; // 리브로스를 키우는 년수
	static int[][] map;
	static boolean[][] visited;
	static ArrayList<Nutrition> nutrition_list;
	static int d;
	static int p;
	static int[] n_dx = { 100, 0, -1, -1, -1, 0, 1, 1, 1 };
	static int[] n_dy = { 100, 1, 1, 0, -1, -1, -1, 0, 1 };
	static int[] dx = { -1, -1, 1, 1 };
	static int[] dy = { -1, 1, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		nutrition_list = new ArrayList<>();
		visited=new boolean[N][N];
		// map 입력받기
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 초기 영양제
		nutrition_list.add(new Nutrition(N - 1, 0));
		nutrition_list.add(new Nutrition(N - 1, 1));
		nutrition_list.add(new Nutrition(N - 2, 0));
		nutrition_list.add(new Nutrition(N - 2, 1));

		for (int year = 1; year <= M; year++) {
			st = new StringTokenizer(br.readLine());
			d = Integer.parseInt(st.nextToken()); // 이동 방향
			p = Integer.parseInt(st.nextToken()); // 이동 칸수
			// System.out.println("===NEW YEAR====");
			// System.out.println("d: "+d+" p: "+p);

			// 특수 영양제 이동
			//System.out.println(nutrition_list.toString());
			nutrition_move();
			// System.out.println("====AFTER MOVE====");
			// System.out.println(nutrition_list.toString());

			// 투입 -> 대각선 인접 1이상 개수 세서 성장시키기
			nutrition_insert();
			//System.out.println("====AFTER INSERT====");
			//for(int i=0; i<N; i++) System.out.println(Arrays.toString(map[i]));
			
			// 투입한 곳 제외하고 2이상인 것들 찾기 ->2만큼 베고 그 자리에 특수 영양제
			new_nutrition();
			//System.out.println("====AFTER NEW====");
			//for(int i=0; i<N; i++) System.out.println(Arrays.toString(map[i]));
			//System.out.println(nutrition_list.toString());
		}
		int answer=0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				answer+=map[i][j];
			}
		}
		System.out.println(answer);
		// 남아 있는 리브로수의 높이들의 총합 구하기
	}
	
	static void new_nutrition() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visited[i][j]) {
					if(map[i][j]>=2) {
						map[i][j]-=2;
						nutrition_list.add(new Nutrition(i, j));
					}
				}
			}
		}
	}

	static void nutrition_insert() {
		visited=new boolean[N][N];
		int size = nutrition_list.size();
		for(int i=0; i<size; i++) {
			Nutrition now = nutrition_list.get(i);
			map[now.x][now.y]++;
			visited[now.x][now.y]=true;
		}
		while (size > 0) {
			size--;
			Nutrition now = nutrition_list.get(0);
			int count = 0;
			for (int d = 0; d < 4; d++) {
				int nx = now.x + dx[d];
				int ny = now.y + dy[d];
				if (!onBoard(nx, ny))
					continue;
				if (map[nx][ny] >= 1)
					count++;
			}
			map[now.x][now.y]+=count;
			nutrition_list.remove(0);
		}
	}

	static boolean onBoard(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= N)
			return false;
		return true;
	}

	static void nutrition_move() {
		int size = nutrition_list.size();
		while (size > 0) {
			size--;
			Nutrition now = nutrition_list.get(0);
			int nx = now.x + n_dx[d] * p;
			int ny = now.y + n_dy[d] * p;

			if (nx >= N)
				nx = nx % N;
			else if (nx < 0)
				nx = nx + N;

			if (ny >= N)
				ny = ny % N;
			else if (ny < 0)
				ny = ny + N;
			nutrition_list.remove(0);
			nutrition_list.add(new Nutrition(nx, ny));
		}
	}

}
