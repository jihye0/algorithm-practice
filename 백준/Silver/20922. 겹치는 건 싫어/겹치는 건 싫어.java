//package P20922;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int K;
	static int[] arr;
	static int ans;
	static int[] num;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N];
		//num = new int[1000001];
		ans = 0;
		int max=0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			max=Math.max(max, arr[i]);
		}
		num = new int[max+1];
		if (N == 1)
			ans = 1;
		else {
			int p1 = 0;
			int p2 = 0;
			while( p2 < N ) {
				while(p2<(N) && num[arr[p2]]+1<=K) {
					num[arr[p2]]++;
					p2++;
					//System.out.println(Arrays.toString(num));
					//System.out.println("p1: "+p1+" p2: "+p2);
				}
				ans=Math.max(ans, p2-p1);
				num[arr[p1]]--;
				p1++;
				
			}
			//System.out.println(Arrays.toString(num));
		}
		System.out.println(ans);
	}

}
