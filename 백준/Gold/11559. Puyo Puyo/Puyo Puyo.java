//package P11559;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Queue;

public class Main {
	static char[][] map;
	static boolean[][] visited;
	static int[] dx= {-1, 1, 0, 0};
	static int[] dy= {0, 0, -1, 1};
	static int count=0;

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		map=new char[12][6];
		for(int i=0; i<12; i++) {
			String str=br.readLine();
			for(int j=0; j<6; j++) {
				map[i][j]=str.charAt(j);
			}
		}
		int answer=0;
		while(true) {
			//다 터뜨리기 
			if(pop()==0) break;
			//중력으로 떨어지
			gravity();
			//System.out.println("---------------");
			//for(int i=0; i<12; i++) System.out.println(Arrays.toString(map[i]));
			answer++;
			
		}
		
		System.out.println(answer);
	}
	
	static void gravity() {
		for(int j=0; j<6; j++) {
			Queue<Character> q= new ArrayDeque<>();
			for(int i=11; i>=0; i--) {
				if(map[i][j]!='.') {
					q.offer(map[i][j]);
				}
			}
			if(!q.isEmpty()) {
				for(int i=11; i>=0; i--) {
					if(!q.isEmpty()) map[i][j]=q.poll();
					else map[i][j]='.';
				}
			}
			
		}
	}
	
	static int pop() {
		visited=new boolean[12][6];
		int pop_count=0;
		for(int i=0; i<12; i++) {
			for(int j=0; j<6; j++) {
				if(visited[i][j]) continue;
				if(map[i][j]=='.') continue;
				count=0;
				dfs(i, j);
				if(count>=4) {
					remove(i, j, map[i][j]);
					pop_count++;
				}
			}
		}
		//for(int i=0; i<12; i++) System.out.println(Arrays.toString(visited[i]));
		//for(int i=0; i<12; i++) System.out.println(Arrays.toString(map[i]));
		return pop_count;
	}
	
	static void remove(int i, int j, char c) {
		map[i][j]='.';
		for(int d=0; d<4; d++) {
			int nx=i+dx[d];
			int ny=j+dy[d];
			if (nx<0 || nx>11 || ny<0 || ny>5) continue;
			if(map[nx][ny]==c) {
				map[nx][ny]='.';
				remove(nx, ny, c);
			}
		}
		
	}
	
	static void dfs(int i, int j) {
		visited[i][j]=true;
		count++;
		for(int d=0; d<4; d++) {
			int nx=i+dx[d];
			int ny=j+dy[d];
			if (nx<0 || nx>11 || ny<0 || ny>5) continue;
			if (visited[nx][ny]) continue;
			if (map[nx][ny]!=map[i][j]) continue;
			dfs(nx, ny);
			//visited[i][j]=false;
		}
	}

}
