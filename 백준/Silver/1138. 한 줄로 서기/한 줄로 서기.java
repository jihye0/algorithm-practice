//package P1138;

import java.io.*;
import java.util.*;


public class Main {
	static int N;
	static int[] arr;
	static int[] ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		N=Integer.parseInt(br.readLine());
		arr=new int[N+1];
		ans=new int[N+1];
		StringTokenizer st=new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		for(int i=1; i<=N; i++) {
			int p=0;
			int count=0;
			while(true) {
				if(count==arr[i]) {
					while(ans[p]!=0) p++;
					ans[p]=i;
					break;
				}
				if(ans[p]==0) {
					count++;
				}
				p++;
			}
			//System.out.println(Arrays.toString(ans));
		}
		StringBuilder sb=new StringBuilder();
		for(int i=0; i<N; i++) sb.append(ans[i]).append(' ');
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		

	}

}
