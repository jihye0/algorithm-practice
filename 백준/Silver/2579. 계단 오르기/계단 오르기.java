//package DAY03.P2579;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int N;
	static int[] stairs;
	static int[] dp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		stairs=new int[N+1];
		dp=new int[N+1];
		
		stairs[0]=0;
		for(int i=0; i<N; i++) {
			stairs[i+1]=Integer.parseInt(br.readLine());
		}
		
		dp[0]=0;
		dp[1]=stairs[1];
		if(N==1) System.out.println(stairs[1]);
		else {
			dp[2]=stairs[1]+stairs[2];
			for(int i=3; i<=N; i++) {
				dp[i]=Math.max(dp[i-2]+stairs[i], dp[i-3]+stairs[i-1]+stairs[i]);
			}
			System.out.println(dp[N]);
		}
	}

}
