///package P12865;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int K;
	static int[][] arr;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		arr=new int[N+1][2];
		dp=new int[K+1][N+1];
		for(int i=1; i<=N; i++) {
			st=new StringTokenizer(br.readLine());
			int w=Integer.parseInt(st.nextToken());
			int v=Integer.parseInt(st.nextToken());
			arr[i][0]=w;
			arr[i][1]=v;
		}
		
		for(int i=1; i<=K; i++) {
			for(int j=1; j<=N; j++) {
				if(i>=arr[j][0]) {
					dp[i][j]=Math.max(dp[i][j-1], dp[i-arr[j][0]][j-1]+arr[j][1]);
				}
				else dp[i][j]=dp[i][j-1];
			}
		}
		System.out.println(dp[K][N]);
	}

}
