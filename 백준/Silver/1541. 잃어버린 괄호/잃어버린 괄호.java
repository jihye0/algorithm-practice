//package P1541;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static String str;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine(), "-");
		int sum=0;
		boolean flag=false;
		while(st.hasMoreTokens()) {
			StringTokenizer addition=new StringTokenizer(st.nextToken(), "+");
			int temp=0;
			while(addition.hasMoreTokens()) {
				temp+=Integer.parseInt(addition.nextToken());
			}
			if(!flag) {
				sum=temp;
				flag=true;
			}else {
				sum=sum-temp;
			}
			
			
		}
		System.out.println(sum);

	}

}
