//package P1446;

import java.util.*;
import java.io.*;

class Node{
	int end;
	int cost;
	
	Node(int end, int cost) {
		this.end=end;
		this.cost=cost;
	}

	@Override
	public String toString() {
		return "Node [end=" + end + ", cost=" + cost + "]";
	}
	
}

public class Main {
	static int N;
	static int D;
	static boolean[] visited;
	static int[] dist;
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		@SuppressWarnings("unchecked")
        ArrayList<Node>[] adj = (ArrayList<Node>[]) new ArrayList[D + 1];
		//visited = new boolean[D+1];
		dist=new int[D+1];
		for (int i = 0; i <= D; i++) {
			adj[i] = new ArrayList<Node>();
			dist[i] = Integer.MAX_VALUE;
		}
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			if(end<=D) {
				if(end-start>cost) {
				adj[start].add(new Node(end, cost));}
			}
			
		}
//		for (int i = 0; i <= D; i++) {
//			System.out.println(adj[i].toString());
//		}
		PriorityQueue<Node> pq=new PriorityQueue<>((a, b)->Integer.compare(a.cost, b.cost));
		pq.add(new Node(0, 0));
		while(!pq.isEmpty()) {
			//System.out.println(pq.toString());
			Node tmp=pq.poll();
			int cur=tmp.end;
			int cost=tmp.cost;
			if(dist[cur]<cost) continue;
			if(cur+1<=D && cost+1<dist[cur+1]) {
				dist[cur+1]=cost+1;
				pq.add(new Node(cur+1, cost+1));
			}
			for(Node n:adj[cur]) {
				int next=n.end;
				int n_cost=n.cost+cost;
				if(n_cost<dist[next]) {
					dist[next]=n_cost;
					pq.add(new Node(next, n_cost));
				}
			}
			
		}
		int answer = dist[D];
		System.out.println(answer);

	}

}
