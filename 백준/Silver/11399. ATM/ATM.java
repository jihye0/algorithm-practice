//package P11399;

import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static int[] time;
	static int[] dp;

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		time=new int[N];
		StringTokenizer st=new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			time[i]=Integer.parseInt(st.nextToken());
		}
		Arrays.sort(time);
		dp=new int[N];
		dp[0]=time[0];
		for(int i=1; i<N; i++) {
			dp[i]=dp[i-1]+time[i];
		}
		int answer=0;
		for(int i=0; i<N; i++) {
			answer+=dp[i];
		}
		System.out.println(answer);

	}

}
