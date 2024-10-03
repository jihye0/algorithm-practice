//package P1912;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] arr;
	static int[] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		StringTokenizer st=new StringTokenizer(br.readLine());
		arr=new int[N];
		dp=new int[N];
		for(int i=0; i<N; i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		dp[0]=arr[0];
		for(int i=1; i<N; i++) {
			if((dp[i-1]+arr[i])>arr[i]) {
				dp[i]=arr[i]+dp[i-1];
			}else {
				dp[i]=arr[i];
			}
		}
		int answer=Integer.MIN_VALUE;
		for(int i=0; i<N; i++) {
			answer=Math.max(answer, dp[i]);
		}
		System.out.println(answer);

	}

}
