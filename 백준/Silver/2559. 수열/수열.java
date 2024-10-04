//package P2559;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int K;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		arr=new int[N];
		st=new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		int p1=0;
		int p2=K-1;
		int sum=0;
		int answer=Integer.MIN_VALUE;
		while(p1<=p2 && p2<N) {
			sum=0;
			for(int i=p1; i<=p2; i++) {
				sum+=arr[i];
			}
			answer=Math.max(answer, sum);
			p1++;
			p2++;
		}
		System.out.println(answer);

	}

}
