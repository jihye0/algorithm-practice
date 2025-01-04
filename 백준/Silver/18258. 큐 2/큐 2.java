//package P18258;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Deque<Integer> q=new ArrayDeque<>();
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			switch (st.nextToken()) {
				case "push":
					q.offer(Integer.parseInt(st.nextToken()));
					break;
				case "pop":
					Integer item=q.poll();
					if(item==null) {
						sb.append(-1).append('\n');
					}
					else {
						sb.append(item).append('\n');
					}
					break;
				case "size":
					sb.append(q.size()).append('\n');
					break;
				case "empty":
					if(q.isEmpty()) {
						sb.append(1).append('\n');
					}
					else {
						sb.append(0).append('\n');
					}
					break;
				case "front":
					// peek()은 큐에 꺼낼 요소가 없을 경우 null을 반환한다.
					Integer ite = q.peek();
					if(ite == null) {
						sb.append(-1).append('\n');
					}
					else {
						sb.append(ite).append('\n');
					}
					break;
					
				case "back":
					// peekLast()은 큐에 꺼낼 요소가 없을 경우 null을 반환한다.
					Integer it = q.peekLast();	 
					if(it == null) {
						sb.append(-1).append('\n');
					}
					else {
						sb.append(it).append('\n');
					}
					break;
					
			}
		}
		System.out.println(sb);
	}

}
