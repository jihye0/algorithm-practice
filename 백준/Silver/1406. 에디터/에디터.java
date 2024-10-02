//package P1406;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
	static int N;
	static int L;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out));
		String S = br.readLine();
		N = Integer.parseInt(br.readLine());
		L = S.length();
		Deque<Character> left=new ArrayDeque<>();
		Deque<Character> right=new ArrayDeque<>();
		int cursor = L;
		for(int i=0; i<L; i++) {
			left.push(S.charAt(i));
		}
		
		for (int i = 0; i < N; i++) {
			String cmd = br.readLine();
			char c = cmd.charAt(0);
			if (c == 'L') {
				if (!left.isEmpty()) {
					right.push(left.pop());
				}
			} else if (c == 'D') {
				if (!right.isEmpty()) {
					left.push(right.pop());
				}
			} else if (c == 'B') {
				if(!left.isEmpty()) {
					left.pop();
				}
			} else if (c == 'P') {
				left.push(cmd.charAt(2));
			}
//			System.out.println(S);
//			System.out.println(cursor);
		}
		StringBuilder sb=new StringBuilder();
		while(!left.isEmpty()) {
			right.push(left.pop());;
		}
		while(!right.isEmpty())
			sb.append(right.pop());
		//System.out.println(S);
		bw.write(sb.toString());
		br.close();
		bw.flush();
		bw.close();

	}

}
