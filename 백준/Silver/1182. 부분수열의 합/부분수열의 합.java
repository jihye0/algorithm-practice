//package P1182;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int S;
	static int[] arr;
	static boolean[] visited;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		S=Integer.parseInt(st.nextToken());
		arr=new int[N];
		visited=new boolean[N];
		st=new StringTokenizer(br.readLine());
		answer=0;
		for(int i=0; i<N; i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		dfs(0, 0);
		if(S==0) {
			System.out.println(answer-1);
			return;
		}
		System.out.println(answer);

	}
	
	static void dfs(int index, int sum) {
		if(index==N) {
			if(sum==S){
				answer++;
			}
			return;
		}
		dfs(index+1, sum+arr[index]);
		dfs(index+1, sum);
		
	}

}
