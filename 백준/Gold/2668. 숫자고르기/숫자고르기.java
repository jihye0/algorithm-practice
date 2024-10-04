//package P2668;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class Main {
	static int N;
	static int[] arr;
	static boolean[] visited;
	static int answer;
	static HashSet<Integer> answer_list;
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		
		N=Integer.parseInt(br.readLine());
		arr=new int[N+1];
		answer_list=new HashSet<>();
		visited=new boolean[N+1];
		
		for(int i=1; i<=N; i++) {
			int a=Integer.parseInt(br.readLine());
			arr[i]=a;
		}
		//System.out.println(Arrays.toString(arr));
		
		answer=0;
		//dfs(1,  1, 1);
		for(int i=1; i<=N; i++) {
			if(!answer_list.contains(i)) {
				dfs(i, i ,1);
			}
		}
		//System.out.println(Arrays.toString(visited));
		System.out.println(answer);
		for(int i=1; i<=N; i++) {
			if(answer_list.contains(i))
				System.out.println(i);
		}
	}
	
	static void dfs(int n, int target, int count) {
		//System.out.println("n: "+n+" target: "+target+ " count: "+count+ " arr[n]: "+ arr[n]);
		visited[n]=true;
		
		if(arr[n]==target) {
//			System.out.println(Arrays.toString(visited));
//			System.out.println("hello");
			answer+=count;
			//System.out.println(answer);
			//visited[n]=false;
			for(int i=1; i<=N; i++) {
				if(visited[i]) answer_list.add(i);
			}
			return;
		}
		
		if(visited[arr[n]]==false) {
			dfs(arr[n], target, count+1);
		}
		visited[n]=false;
		
		return;
	}

}
