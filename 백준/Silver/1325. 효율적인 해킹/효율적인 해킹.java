//package P1325;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {
	static int N; //N개의 컴퓨터 
	static int M; //신뢰관게 
	static ArrayList<Integer>[] adj;
	static boolean[] visited;
	static int count;
	static int[] computers;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		adj=new ArrayList[N+1];
		for(int i=1; i<=N; i++) {
			adj[i]=new ArrayList<>();
		}
		for(int i=0; i<M; i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			adj[a].add(b);
		}
		computers=new int[N+1];

		for(int i=1; i<=N; i++) {
			visited=new boolean[N+1];
			dfs(i);
		}
		
		StringBuilder sb=new StringBuilder();
		int max=0;
		for(int i=1; i<=N; i++) {
			max=Math.max(computers[i], max);
		}
		for(int i=1; i<=N; i++) {
			if(computers[i]==max) {
				sb.append(i).append(' ');
			}
		}
		//System.out.println(Arrays.toString(computers));
		System.out.println(sb);

	}
	static void dfs(int n) {
		visited[n]=true;
		for(int i: adj[n]) {
			if(!visited[i]) {
				computers[i]++;
				dfs(i);
			}
		}
	}

}
