//package P1806;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int S;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		arr = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int p1 = 0;
		int p2 = 0;
		int sum = 0;
		int answer = Integer.MAX_VALUE;
		while(p1<=p2 &&  p2<=N) {
			if(sum<S) {
				sum+=arr[p2];
				p2++;
			}
			else if(sum>=S) {
				answer=Math.min(answer, p2-p1);
				sum=sum-arr[p1];
				p1++;
			}
		}
		if(answer==Integer.MAX_VALUE) {
			System.out.println('0');
		}
		else System.out.println(answer);
		
	}

}
