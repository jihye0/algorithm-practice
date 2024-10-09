//package S2022_2_A_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static class Node {
		int prev;
		int next;

		Node() {
			next = -1;
			prev = -1;
		}

		@Override
		public String toString() {
			return "Node [prev=" + prev + ", next=" + next + "]";
		}

	}

	static class Belt {
		int head;
		int tail;
		int size;

		Belt() {
			head = -1;
			tail = -1;
			size = 0;
		}

		@Override
		public String toString() {
			return "Belt [head=" + head + ", tail=" + tail + ", size=" + size + "]";
		}

		public void addFirst(int key) {
			if (size == 0) {
				head = key;
				tail = key;
				node_list[key].prev = -1;
				node_list[key].next = -1;
				size++;
				return;
			}
			node_list[head].prev = key;
			node_list[key].next = head;
			head = key;
			size++;
		}

		public void addLast(int key) {
			if (size == 0) {
				addFirst(key);
				return;
			}
			node_list[tail].next = key;
			node_list[key].prev = tail;
			tail = key;
			node_list[key].next = -1;
			size++;
		}

		public int popFront() {
			int key = head;
			if (size == 1) {
				tail = -1;
				head = -1;
				size--;
				return key;
			}
			int next = node_list[head].next;
			node_list[next].prev = -1;
			head = next;
			node_list[key].next = -1;
			size--;
			return key;
		}

		public int get(int n) {
			int cur = head;
			for (int i = 0; i < n; i++) {
				cur = node_list[cur].next;
			}
			return cur;
		}

	}

	static int Q, N, M;
	static Node[] node_list;
	static Belt[] belt_list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Q = Integer.parseInt(br.readLine());

		for (int q = 0; q < Q; q++) {
			String tmp = (br.readLine());
			StringTokenizer st = new StringTokenizer(tmp);
			int cmd = Integer.parseInt(st.nextToken());
			if (cmd == 100) {
				setupFactory(st);
			} else if (cmd == 200) {
				moveAll(st);
			} else if (cmd == 300) {
				swapHead(st);
			} else if (cmd == 400) {
				moveHalf(st);
			} else if (cmd == 500) {
				getPresentInfo(st);
			} else if (cmd == 600) {
				getBeltInfo(st);
			}
			// System.out.println(tmp);
			// System.out.println(Arrays.toString(node_list));
			// System.out.println(Arrays.toString(belt_list));
			// System.out.println("=============================");
		}
	}

	// 공장 설립 (cmd 100)
	static void setupFactory(StringTokenizer st) {
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		belt_list = new Belt[N + 1];
		node_list = new Node[M + 1];
		for (int i = 1; i <= N; i++)
			belt_list[i] = new Belt();
		for (int i = 1; i <= M; i++)
			node_list[i] = new Node();

		for (int i = 1; i <= M; i++) {
			int b_num = Integer.parseInt(st.nextToken());
			addPresentToBelt(i, b_num);
		}
	}

	// 벨트에 선물 추가
	static void addPresentToBelt(int present, int belt) {
		if (belt_list[belt].size == 0) {
			belt_list[belt].head = belt_list[belt].tail = present;
		} else {
			int tail = belt_list[belt].tail;
			node_list[tail].next = present;
			node_list[present].prev = tail;
			belt_list[belt].tail = present;
		}
		belt_list[belt].size++;
	}

	// 물건 모두 옮기기 (cmd 200)
	static void moveAll(StringTokenizer st) {
		int m_src = Integer.parseInt(st.nextToken());
		int m_dst = Integer.parseInt(st.nextToken());

		// m_src가 비어있으면 아무것도 하지 않음
		if (belt_list[m_src].size == 0) {
			System.out.println(belt_list[m_dst].size);
			return;
		}

		// m_dst가 비어있을 경우
		if (belt_list[m_dst].size == 0) {
			belt_list[m_dst].head = belt_list[m_src].head;
			belt_list[m_dst].tail = belt_list[m_src].tail;
			belt_list[m_dst].size = belt_list[m_src].size;
		} else {
			// m_dst에 선물이 있는 경우, m_src의 tail을 m_dst의 head 앞에 연결
			int src_tail = belt_list[m_src].tail;
			int dst_head = belt_list[m_dst].head;

			node_list[src_tail].next = dst_head;
			node_list[dst_head].prev = src_tail;
			belt_list[m_dst].head = belt_list[m_src].head;
			belt_list[m_dst].size += belt_list[m_src].size;
		}
		belt_list[m_src].head = -1;
		belt_list[m_src].tail = -1;
		belt_list[m_src].size = 0;

		// m_dst에 있는 선물 개수 출력
		System.out.println(belt_list[m_dst].size);
	}

	// 앞 물건 교체 (cmd 300)
	static void swapHead(StringTokenizer st) {
		int m_src = Integer.parseInt(st.nextToken());
		int m_dst = Integer.parseInt(st.nextToken());

		if (belt_list[m_src].size > 0 && belt_list[m_dst].size == 0) {
			belt_list[m_dst].addFirst(belt_list[m_src].popFront());
		} else if (belt_list[m_src].size == 0 && belt_list[m_dst].size > 0) {
			belt_list[m_src].addFirst(belt_list[m_dst].popFront());
		} else if (belt_list[m_src].size > 0 && belt_list[m_dst].size > 0) {
			int value1 = belt_list[m_src].popFront();
			int value2 = belt_list[m_dst].popFront();
			belt_list[m_dst].addFirst(value1);
			belt_list[m_src].addFirst(value2);
		}
		System.out.println(belt_list[m_dst].size);
	}

	// 물건 나누기 (cmd 400)
	static void moveHalf(StringTokenizer st) {
		int m_src = Integer.parseInt(st.nextToken());
		int m_dst = Integer.parseInt(st.nextToken());

		if (belt_list[m_src].size <= 1) {
			System.out.println(belt_list[m_dst].size);
			return;
		}

		int size = belt_list[m_src].size;
		int move_head = belt_list[m_src].head;
		int move_tail = belt_list[m_src].get((size / 2) - 1);
		belt_list[m_src].head = node_list[move_tail].next;
		node_list[belt_list[m_src].head].prev = -1;
		node_list[move_tail].next = -1;
		belt_list[m_src].size -= size / 2;

		if (belt_list[m_dst].size == 0) {
			belt_list[m_dst].head = move_head;
			belt_list[m_dst].tail = move_tail;
			belt_list[m_dst].size = size / 2;
		} else {
			int start2 = belt_list[m_dst].head;
			belt_list[m_dst].head = move_head;
			node_list[move_tail].next = start2;
			node_list[start2].prev = move_tail;
			belt_list[m_dst].size += size / 2;
		}

		System.out.println(belt_list[m_dst].size);
	}

	// 선물 정보 얻기 (cmd 500)
	static void getPresentInfo(StringTokenizer st) {
		int p_num = Integer.parseInt(st.nextToken());
		int a = node_list[p_num].prev;
		int b = node_list[p_num].next;
		System.out.println(a + 2 * b);
	}

	// 벨트 정보 얻기 (cmd 600)
	static void getBeltInfo(StringTokenizer st) {
		int b_num = Integer.parseInt(st.nextToken());
		int a = belt_list[b_num].head;
		int b = belt_list[b_num].tail;
		int c = belt_list[b_num].size;
		System.out.println(a + 2 * b + 3 * c);
	}
}
