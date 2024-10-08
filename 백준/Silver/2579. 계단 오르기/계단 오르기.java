//package P2579;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] score;
	static int[][] dp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		score = new int[N+1];
		dp=new int[N+1][2];
		for (int i = 0; i < N; i++) {
			score[i+1] = Integer.parseInt(br.readLine());
		}
		if(N==1) {
			System.out.println(score[1]);
			return;
		}
		dp[1][0]=score[1];
		dp[1][1]=0;
		dp[2][0]=score[2];
		dp[2][1]=score[1]+score[2];
		for(int i=3; i<=N; i++) {
			dp[i][1]=dp[i-1][0]+score[i];
			dp[i][0]=score[i]+Math.max(dp[i-2][0], dp[i-2][1]);
		}
		System.out.println(Math.max(dp[N][0], dp[N][1]));

	}
}
