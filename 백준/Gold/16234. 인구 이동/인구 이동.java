//package P16234;

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int L;
	static int R;
	static int[][] A;
	static int[][] map;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int[] sum;
	static int[] count;
	static int[] pop;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		A = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int time = 0;
		sum=new int[N*N+1];
		count=new int[N*N+1];
		pop=new int[N*N+1];
		while (true) {
			time++;
			map = new int[N][N];
			int union=1;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j]==0) {
						sum[union]=0;
						count[union]=0;
						bfs(i, j, union);
						//System.out.println(sum[union]);
						//System.out.println(count[union]);
						pop[union]=sum[union]/count[union];
						union++;
					}
				}
			}
//			System.out.println(union);
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					int tmp=map[i][j];
					A[i][j]=sum[tmp]/count[tmp];
				}
			}
//			for (int i = 0; i < N; i++)
//				System.out.println(Arrays.toString(map[i]));
//			for (int i = 0; i < N; i++)
//				System.out.println(Arrays.toString(A[i]));
			if(union==(N*N+1)) break;
		}
		System.out.println(time-1);
	}

	static void bfs(int x, int y, int u) {
		//System.out.println("x: "+x+" y: "+y+" u: "+u);
		Queue<int[]> q = new ArrayDeque<>();
		map[x][y] = u;
		sum[u]+=A[x][y];
		count[u]++;
		q.add(new int[] { x, y, u });
		while (!q.isEmpty()) {
			int[] now = q.poll();
			int now_x = now[0];
			int now_y = now[1];
			int now_u = now[2];
			for (int i = 0; i < 4; i++) {
				int new_x = now_x + dx[i];
				int new_y = now_y + dy[i];
				if (new_x < 0 || new_x >= N || new_y < 0 || new_y >= N)
					continue;
				if (map[new_x][new_y] > 0)
					continue;
				int tmp = Math.abs(A[new_x][new_y] - A[now_x][now_y]);
				if (tmp >= L && tmp <= R) {
					sum[u]+=A[new_x][new_y];
					count[u]++;
					map[new_x][new_y] = now_u;
					q.add(new int[] { new_x, new_y, now_u });
				}
			}

		}
	}

}
