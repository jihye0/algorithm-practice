//package P11724;

import java.io.*;
import java.util.*;

public class Main {
	static int N; //정점의 개수 
	static int M; //간선의 개수 
	static boolean[][] map;
	static int[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		visited=new int[N+1];
		map=new boolean[N+1][N+1];
		for(int i=0; i<M; i++) {
			st=new StringTokenizer(br.readLine());
			int u=Integer.parseInt(st.nextToken());
			int v=Integer.parseInt(st.nextToken());
			map[u][v]=true;
			map[v][u]=true;
		}
		int group=1;
		for(int i=1; i<=N; i++) {
			if(visited[i]==0) {
				dfs(i, group);
				group++;
			}
		}
		System.out.println(group-1);
	}
	
	static void dfs(int n, int group) {
		visited[n]=group;
		for(int i=1; i<=N; i++) {
			if(visited[i]!=0) continue;
			if(map[n][i]) {
				dfs(i, group);
			}
		}
	}

}
