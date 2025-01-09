//package P1620;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static HashMap<Integer, String> numbermap;
	static HashMap<String, Integer> namemap;

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		numbermap=new HashMap<>();
		namemap=new HashMap<>();
		for(int i=1; i<=N; i++) {
			String name=br.readLine();
			numbermap.put(i, name);
			namemap.put(name, i);
		}
		for(int i=1; i<=M; i++) {
			String cmd=br.readLine();
			if(namemap.containsKey(cmd)) {
				System.out.println(namemap.get(cmd));
			}else {
				int tmp=Integer.parseInt(cmd);
				System.out.println(numbermap.get(tmp));
			}
		}
		
		

	}

}
