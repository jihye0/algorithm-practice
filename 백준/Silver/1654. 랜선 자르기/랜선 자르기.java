//package P1654;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int K;
	static int N;
	static long[] arr;
	static long answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		K=Integer.parseInt(st.nextToken());
		N=Integer.parseInt(st.nextToken());
		arr=new long[K];
		//st=new StringTokenizer(br.readLine());
		long max=0;
		for(int i=0; i<K; i++) {
			arr[i]=Integer.parseInt(br.readLine());
			max=Math.max(max, arr[i]);
		}
		max++;
		answer=0;
		long left=0;
		long right=max;
		while(left<right) {
			long mid=(left+right)/2;
			long count=0;
			
			for(int i=0; i<arr.length; i++) {
				count+=(arr[i]/mid);
			}
			
			if(count<N) {
				right=mid;
			}
			else {
				left=mid+1;
			}
		}
		System.out.println(left-1);
	}

}
