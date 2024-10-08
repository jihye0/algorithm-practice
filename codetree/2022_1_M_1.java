//package S2022_1_M_1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Domangja {
	int x;
	int y;
	int d;
	boolean isAlive;

	public Domangja(int x, int y, int d, boolean isAlive) {
		this.x = x;
		this.y = y;
		this.d = d;
		this.isAlive = isAlive;
	}

	@Override
	public String toString() {
		return "Domangja [x=" + x + ", y=" + y + ", d=" + d + ", isAlive=" + isAlive + "]";
	}
	
	
}

public class Main {
	static int N; // 칸 수
	static int M; // 도망자의 수
	static int H; // 나무의 수
	static int K; // 턴의 수
	static int sulae_x;
	static int sulae_y;
	static int[][] dir_map;
	static int[][] reverse_dir_map;
	static boolean[][] tree_map;
	static Domangja[] domang_list;
	static int[] dx = { 0, 0, -1, 0, 1 };
	static int[] dy = { 0, 1, 0, -1, 0 };
	static boolean reverse;

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("src/S2022_1_M_1/input.txt"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		sulae_x = N / 2;
		sulae_y = N / 2;
		dir_map = new int[N][N];
		tree_map = new boolean[N][N];
		reverse_dir_map = new int[N][N];
		fill_dir_map();
		fill_reverse_map();
//		for (int j = 0; j < N; j++)
//			System.out.println(Arrays.toString(dir_map[j]));
//		System.out.println("-----------------");
//		for (int j = 0; j < N; j++)
//			System.out.println(Arrays.toString(reverse_dir_map[j]));
//		System.out.println("-----------------");
		domang_list = new Domangja[M];
		// M개의 줄에서 도망자의 위치 x, y, d
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			if(d==2) d=4;
			domang_list[i] = new Domangja(x-1, y-1, d, true);
		}
		// hㅈ줄에 걸쳐 나무의 위치
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			tree_map[x-1][y-1] = true;
		}
		reverse=false;
		int answer=0;
		// 술래가 k번턴 동안 얻게 되는 총 점수 출력하기
		for (int turn = 1; turn <= K; turn++) {
			// 도망자 움직이기
			//System.out.println(Arrays.toString(domang_list));
			move_domangja();
			//System.out.println("-----AFTER MOVE DOMANG-------");
			//System.out.println(Arrays.toString(domang_list));
			// 술래 움직이기
			move_sulae();
			//System.out.println("-----AFTER MOVE SULAE--");
			//System.out.println("sulae x: "+sulae_x+" sulae_y: "+sulae_y);
			// 시야 내 도망자가 잡기
			int count=catch_domangja();
			answer+=count*turn;
		}
		System.out.println(answer);

	}
	
	static int catch_domangja() {
		int answer=0;
		int nd;
		if(!reverse) {
			nd=dir_map[sulae_x][sulae_y];
		}else {
			nd=reverse_dir_map[sulae_x][sulae_y];
		}
		for(int i=0; i<M; i++) {
			if (domang_list[i].isAlive) {
				int d_x=domang_list[i].x;
				int d_y=domang_list[i].y;
				if(tree_map[d_x][d_y]) continue;
				for(int j=0; j<3; j++) {
					int c_x=sulae_x+dx[nd]*j;
					int c_y=sulae_y+dy[nd]*j;
					if(d_x==c_x && d_y==c_y) {
						answer++;
						domang_list[i].isAlive=false;
						break;
					}
				}
			}
		}
		return answer;
	}
	
	static void move_sulae() {
		int nd;
		if(!reverse) {
			nd=dir_map[sulae_x][sulae_y];
		}else {
			nd=reverse_dir_map[sulae_x][sulae_y];
		}
		int nx=sulae_x+dx[nd];
		int ny=sulae_y+dy[nd];
		if(reverse==false && nx==0 && ny==0) {
			reverse=true;
		}
		if(reverse==true && nx==(N/2) && ny==(N/2)) {
			reverse=false;
		}
		sulae_x=nx;
		sulae_y=ny;
	}

	static void move_domangja() {
		for (int i = 0; i < M; i++) {
			if (domang_list[i].isAlive) {
				if (distance_from_sulae(i) <= 3) {
					int d = domang_list[i].d;
					int nx = domang_list[i].x + dx[d];
					int ny = domang_list[i].y + dy[d];
					if (onBoard(nx, ny)) {
						if (nx != sulae_x || ny != sulae_y) {
							domang_list[i].x = nx;
							domang_list[i].y = ny;
						}
					} else {
						domang_list[i].d = reverse_direction(d);
						d = domang_list[i].d;
						nx = domang_list[i].x + dx[d];
						ny = domang_list[i].y + dy[d];
						if (onBoard(nx, ny)) {
							if (nx != sulae_x || ny != sulae_y) {
								domang_list[i].x = nx;
								domang_list[i].y = ny;
							}
						}
					}
				}
			}
		}
	}

	static int reverse_direction(int i) {
		if (i == 1)
			return 3;
		else if (i == 2)
			return 4;
		else if (i == 3)
			return 1;
		else
			return 2;
	}

	static boolean onBoard(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= N)
			return false;
		return true;
	}

	static int distance_from_sulae(int i) {
		return Math.abs(sulae_x - domang_list[i].x) + Math.abs(sulae_y - domang_list[i].y);
	}

	static void fill_reverse_map() {
		reverse_dir_map[N / 2][N / 2] = 2;
		int nowx = 0;
		int nowy = 0;
		reverse_dir_map[nowx][nowy] = 4;
		for (int k = 1; k <= N - 2; k++) {
			int d = reverse_dir_map[nowx][nowy];
			nowx = nowx + dx[d];
			nowy = nowy + dy[d];
			reverse_dir_map[nowx][nowy] = 4;
		}
//		for (int j = 0; j < N; j++)
//			System.out.println(Arrays.toString(reverse_dir_map[j]));
//		System.out.println("-----------------");
//		System.out.println("nowx " + nowx + " nowy " + nowy);
		for (int i = (N - 1); i >= 1; i--) {
			if (i % 2 == 0) {
				for (int k = 1; k <= i; k++) {
					int d = reverse_dir_map[nowx][nowy];
					nowx = nowx + dx[d];
					nowy = nowy + dy[d];
					reverse_dir_map[nowx][nowy] = 1;
				}
				for (int k = 1; k <= i; k++) {
					int d = reverse_dir_map[nowx][nowy];
					nowx = nowx + dx[d];
					nowy = nowy + dy[d];
					reverse_dir_map[nowx][nowy] = 2;
				}
//				for (int j = 0; j < N; j++)
//					System.out.println(Arrays.toString(reverse_dir_map[j]));
//				System.out.println("-----------------");
			} else {
				for (int k = 1; k <= i; k++) {
					int d = reverse_dir_map[nowx][nowy];
					nowx = nowx + dx[d];
					nowy = nowy + dy[d];
					reverse_dir_map[nowx][nowy] = 3;
				}
				for (int k = 1; k <= i; k++) {
					int d = reverse_dir_map[nowx][nowy];
					nowx = nowx + dx[d];
					nowy = nowy + dy[d];
					reverse_dir_map[nowx][nowy] = 4;
				}
//				for (int j = 0; j < N; j++)
//					System.out.println(Arrays.toString(reverse_dir_map[j]));
//				System.out.println("-----------------");
			}
		}
	}

	static void fill_dir_map() {
		int nowx = N / 2;
		int nowy = N / 2;
		dir_map[nowx][nowy] = 2;
		for (int i = 1; i <= (N - 1); i++) {
			if (i % 2 == 0) {
				for (int k = 1; k <= i; k++) {
					int d = dir_map[nowx][nowy];
					nowx = nowx + dx[d];
					nowy = nowy + dy[d];
					dir_map[nowx][nowy] = 4;
				}
				for (int k = 1; k <= i; k++) {
					int d = dir_map[nowx][nowy];
					nowx = nowx + dx[d];
					nowy = nowy + dy[d];
					dir_map[nowx][nowy] = 3;
				}
			} else {
				if (i != 1) {
					for (int k = 1; k <= i; k++) {
						int d = dir_map[nowx][nowy];
						nowx = nowx + dx[d];
						nowy = nowy + dy[d];
						dir_map[nowx][nowy] = 2;
					}
				}
				for (int k = 1; k <= i; k++) {
					int d = dir_map[nowx][nowy];
					nowx = nowx + dx[d];
					nowy = nowy + dy[d];
					dir_map[nowx][nowy] = 1;
				}
			}
		}
		for (int k = 1; k <= N - 1; k++) {
			int d = dir_map[nowx][nowy];
			nowx = nowx + dx[d];
			nowy = nowy + dy[d];
			dir_map[nowx][nowy] = 2;
		}
		dir_map[0][0] = 4;
	}

}
