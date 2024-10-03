//package P10844;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static long[] num;
	static long[] tmp;
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		num=new long[10];
		num[0]=0;
		for(int i=1; i<10; i++) {
			num[i]=1;
		}
		for(int loop=1; loop<N; loop++) {
			tmp=new long[10];
			for(int i=0; i<10; i++) tmp[i]=num[i];
			num[0]=tmp[1];
			num[9]=tmp[8];
			for(int i=1; i<=8; i++) {
				num[i]=(tmp[i-1]+tmp[i+1])%1000000000;
			}
		}
		long answer=0;
		for(int i=0; i<10; i++) answer=(answer+num[i])%1000000000;
		System.out.println(answer%1000000000);

	}

}
