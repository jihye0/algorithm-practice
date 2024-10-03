//package P9205;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Node{
	int x;
	int y;

	public Node(int x, int y) {
		this.x=x;
		this.y=y;
	}

	public String toString() {
		return "x: "+x+" y: "+y;
	}
}

public class Main {
	static int T;
	static int n;
	static Node[] arr;
	static boolean[] visited;
	static boolean canGo;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		T=Integer.parseInt(br.readLine());
		for(int test=1; test<=T; test++) {
			n=Integer.parseInt(br.readLine());
			arr=new Node[n+2];
			visited=new boolean[n+2];
			StringTokenizer st=new StringTokenizer(br.readLine());
			for(int i=0; i<n+2; i++) {
				arr[i]=new Node(0, 0);
			}
			arr[0].x=Integer.parseInt(st.nextToken());
			arr[0].y=Integer.parseInt(st.nextToken());
			st=new StringTokenizer(br.readLine());
			arr[1].x=Integer.parseInt(st.nextToken());
			arr[1].y=Integer.parseInt(st.nextToken());
			
			for(int i=0; i<n; i++) {
				st=new StringTokenizer(br.readLine());
				arr[i+2].x=Integer.parseInt(st.nextToken());
				arr[i+2].y=Integer.parseInt(st.nextToken());
			}
			
			if(Math.abs(arr[1].x-arr[0].x)+Math.abs(arr[1].y-arr[0].y)<=0) {
				System.out.println("happy");
				continue;
			}
			canGo=false;
			//System.out.println(Arrays.toString(arr));
			dfs(0);
			if(canGo) System.out.println("happy");
			else System.out.println("sad");
		}

	}
	
	static void dfs(int now) {
		//System.out.println(Arrays.toString(visited));
		visited[now]=true;
		if(now==n+1) {
			canGo=true;
			return;
		}
		//주변 편의점 순
		for(int i=0; i<n+2; i++) {
			if(visited[i]) continue;
			//System.out.println(Math.abs(arr[i].x-arr[now].x)+Math.abs(arr[i].y-arr[now].y));
			if((Math.abs(arr[i].x-arr[now].x)+Math.abs(arr[i].y-arr[now].y))<=1000) {
				dfs(i);
			}
		}
		//if(now!=0) visited[now]=false;
	}

}
