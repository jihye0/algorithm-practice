//package P1697;

import java.util.*;
import java.io.*;

public class Main {
	
	static int N; //수빈의 위치 
	static int K; //동생의 위치 
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		visited=new boolean[100001];
		Queue<int[]> q=new ArrayDeque<int[]>();
		q.add(new int[] {N, 0});
		visited[N]=true;
		int answer=Integer.MAX_VALUE;
		while(!q.isEmpty()) {
			int[] now=q.poll();
			//visited[now[0]]=true;
			if(now[0]==K) {
				answer=Math.min(answer, now[1]);
				continue;
			}
			if((now[0]*2)<=100000 && !visited[2*now[0]]) {
				visited[now[0]*2]=true;
				q.add(new int[] {now[0]*2, now[1]});
			}
			
			if((now[0]-1)>=0 && !visited[now[0]-1]){
				visited[now[0]-1]=true;
				q.add(new int[] {now[0]-1, now[1]+1});
				
			}
			if((now[0]+1)<=100000 && !visited[now[0]+1]){
				visited[now[0]+1]=true;
				q.add(new int[] {now[0]+1, now[1]+1});
				
			}
			
		}
		System.out.println(answer);
		
	}

}
