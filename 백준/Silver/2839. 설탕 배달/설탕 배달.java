//package P2839;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int N;
	static int[] dp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		if(N<=5) {
			dp=new int[6];
		}else dp=new int[N+1];
		dp[1]=-1;
		dp[2]=-1;
		dp[3]=1;
		dp[4]=-1;
		dp[5]=1;
		if(N<=5) {
			System.out.println(dp[N]);
			return;
		}
		for(int i=6; i<=N; i++) {
			int a=dp[i-3];
			int b=dp[i-5];
			if(a==-1 && b!=-1) {
				dp[i]=b+1;
			} else if(a!=-1 && b==-1) {
				dp[i]=a+1;
			}else if(a==-1 && b==-1) {
				dp[i]=-1;
			}else {
				dp[i]=Math.min(a, b)+1;
			}
		}
		//System.out.println(Arrays.toString(dp));
		System.out.println(dp[N]);

	}

}
