//package P11053;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//10:16
public class Main {
	static int N;
	static int[] arr;
	static int[] dp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		arr=new int[N];
		dp=new int[N];
		
		int answer=1;
		StringTokenizer st=new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		Arrays.fill(dp, 0);
		dp[0]=1;
		for(int i=1; i<N; i++) {
			int dp_max=dp[i];
			for(int j=0; j<i; j++) {
				if(arr[j]<arr[i]) {
					dp_max=Math.max(dp_max, dp[j]);
				}
				dp[i]=dp_max+1;
			}
			answer=Math.max(answer, dp[i]);
		}
		//System.out.println(Arrays.toString(dp));
		
		System.out.println(answer);
	}

}
