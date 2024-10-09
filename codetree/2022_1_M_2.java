//package S2022_1_M_2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

class Group {
	int group_num;
	int size;
	int number;

	Group(int group_num, int size, int number) {
		this.group_num = group_num;
		this.size = size;
		this.number = number;
	}

	@Override
	public String toString() {
		return "Group [group_num=" + group_num + ", size=" + size + ", number=" + number + "]";
	}
}

public class Main {
	static int N; //
	static int[][] map; // 색에 대한 정보
	static int[][] copy_map;
	static int[][] group_map;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static ArrayList<Group> group_list;
	static int tmp_count;
	static int total_group_num;
	static int[][] neigh;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("src/S2022_1_M_2/input.txt"));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		copy_map = new int[N][N];
		group_list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int final_answer = 0;
		for (int turn = 1; turn <= 4; turn++) {
			// 예술점수 구하기
			int tmp=art_score();
			final_answer += tmp;
			// System.out.println("====ART SCORE====");
			// System.out.println(tmp);
			// 회전하기
			// System.out.println("====BEFORE ROTATE====");
			// for (int i = 0; i < N; i++)
			// 	System.out.println(Arrays.toString(map[i]));
			rotate();
			// System.out.println("====AFTER ROTATE====");
			// for (int i = 0; i < N; i++)
			// 	System.out.println(Arrays.toString(map[i]));
		}
		System.out.println(final_answer);
	}
	
	

	static void rotate() {
		for (int i = 0; i < N; i++)
			copy_map[i] = map[i].clone();
		
		for (int j = 0; j < N; j++) {
			map[N / 2][j] = copy_map[j][N / 2];
		}
		for (int i = 0; i < N; i++) {
			map[i][N / 2] = copy_map[N / 2][N-1 - i];
		}
		small_rotate(0, 0);
		small_rotate(0, N/2+1);
		small_rotate(N/2+1, 0);
		small_rotate(N/2+1, N/2+1);
	}
	
	static void small_rotate(int sx, int sy) {
		for(int i=0; i<N/2; i++) {
			for(int j=0; j<N/2; j++) {
				map[sx+i][sy+j]=copy_map[sx+(N/2-1-j)][sy+i];
			}
		}
	}

	static int art_score() {
		int answer = 0;
		group_list=new ArrayList<>();
		fill_group_map();
//		System.out.println("====GROUP MAP====");
//		for (int i = 0; i < N; i++)
//			System.out.println(Arrays.toString(group_map[i]));
//		System.out.println("====GROUP LIST====");
//		System.out.println(group_list.toString());
		fill_neigh();
//		c
//		for(int i=0; i<=total_group_num; i++) System.out.println(Arrays.toString(neigh[i]));
		for (int i = 0; i < group_list.size(); i++) {
			for (int j = i + 1; j < group_list.size(); j++) {
				int neighs = neigh[group_list.get(i).group_num][group_list.get(j).group_num]
						+ neigh[group_list.get(j).group_num][group_list.get(i).group_num];
				if (neighs == 0)
					continue;
				Group A = group_list.get(i);
				Group B = group_list.get(j);
				answer += (A.size + B.size) * A.number * B.number * neighs;
			}
		}
		return answer;

	}

	static void fill_neigh() {
		boolean[][] visited = new boolean[N][N];
		neigh = new int[total_group_num + 1][total_group_num + 1];

		int now = group_map[0][0];
		for (int i = 0; i < N; i++) {
			now = group_map[i][0];
			for (int j = 1; j < N; j++) {
				if (group_map[i][j] != now) {
					neigh[now][group_map[i][j]]++;
					now = group_map[i][j];
				}
			}
		}
		now = group_map[0][0];
		for (int j = 0; j < N; j++) {
			now = group_map[0][j];
			for (int i = 1; i < N; i++) {
				if (group_map[i][j] != now) {
					neigh[now][group_map[i][j]]++;
					now = group_map[i][j];
				}
			}
		}
	}

	static void fill_group_map() {
		group_map = new int[N][N];
		int group_num = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (group_map[i][j] == 0) {
					tmp_count = 0;
					dfs(i, j, group_num);
					group_list.add(new Group(group_num, tmp_count, map[i][j]));
					group_num++;
				}
			}
		}
		total_group_num = group_num;
	}

	static void dfs(int x, int y, int n) {
		group_map[x][y] = n;
		tmp_count++;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (!onBoard(nx, ny))
				continue;
			if (group_map[nx][ny] > 0)
				continue;
			if (map[nx][ny] == map[x][y]) {
				dfs(nx, ny, n);
			}
		}
	}

	static boolean onBoard(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= N)
			return false;
		return true;
	}

}
