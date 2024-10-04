//package P16928;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N; // 사다리의 수
	static int M; // 뱀의 수
	static int[] adj;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		adj = new int[101];
		visited = new boolean[101];
		for (int i = 0; i < (N + M); i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adj[a] = b;
		}
		// for(int i=1; i<=100; i++) System.out.println(adj[i].toString());

		int answer = Integer.MAX_VALUE;
		Queue<int[]> q = new ArrayDeque<>();
		visited[1] = true;
		q.add(new int[] { 1, 0 });

		while (!q.isEmpty()) {

			int[] now = q.poll();
			// System.out.println(Arrays.toString(now));
			if (now[0] == 100) {
				//System.out.println(now[1]);
				answer = Math.min(answer, now[1]);
				// break;
			}
			if (adj[now[0]] == 0) {
				for (int i = 1; i <= 6; i++) {
					int next = now[0] + i;
					if (next > 100)
						continue;
					if (visited[next])
						continue;
					visited[next] = true;
					if(adj[next]!=0) {
						next=adj[next];
					}
					q.add(new int[] { next, now[1]+1});
				}
			}
		}
		System.out.println(answer);
	}

}
