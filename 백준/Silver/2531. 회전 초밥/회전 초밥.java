//package P2531;

import java.io.*;
import java.util.*;

public class Main {
	static int N; // 벨트에 놓인 접시의 수
	static int d; // 초밥의 가짓수
	static int k; // 연속해서 먹는 접시의 수
	static int c; // 쿠폰 번호
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		arr = new int[N + k];

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		for (int i = N; i < (N + k); i++) {
			arr[i] = arr[i - N];
		}
		//System.out.println(Arrays.toString(arr));
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < k; i++) {
			if (map.containsKey(arr[i])) {
				map.replace(arr[i], map.get(arr[i]) + 1);
			} else {
				map.put(arr[i], 1);
			}
		}
		if (map.containsKey(c)) {
			map.replace(c, map.get(c) + 1);
		} else {
			map.put(c, 1);
		}
		int p1 = 0;
		int p2 = k;
		int answer = map.size();

		while (p2 < (N + k)) {
			//System.out.println("p1: " + p1 + " p2: " + p2);
			//System.out.println(map.toString());
			
			if (map.containsKey(arr[p2])) {
				map.replace(arr[p2], map.get(arr[p2]) + 1);
			} else {
				map.put(arr[p2], 1);
			}
			p2++;
			// p2는 새로운 값!
			if (map.get(arr[p1]) == 1) {
				map.remove(arr[p1]);
			} else {
				map.replace(arr[p1], map.get(arr[p1]) - 1);
			}
			p1++;
			answer = Math.max(map.size(), answer);
		}
		System.out.println(answer);

	}

}
