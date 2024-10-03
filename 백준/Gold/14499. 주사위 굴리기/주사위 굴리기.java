//package P14499;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int dice_x;
	static int dice_y;
	static int K2;
	static int[][] map;
	static int[] dice;
	static int[] dice_copy;

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		dice_x=Integer.parseInt(st.nextToken());
		dice_y=Integer.parseInt(st.nextToken());
		K2=Integer.parseInt(st.nextToken());
		map=new int[N][M];
		dice=new int[7];
		dice_copy=new int[7];
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		st=new StringTokenizer(br.readLine());
		//System.out.println(K2);
		
		for(int test=1; test<=K2; test++) {
			//System.out.println("dicex: "+dice_x+" dicey: "+dice_y);
			int K=Integer.parseInt(st.nextToken());
			if(K==1) {
				//System.out.println("EAST");
				if(dice_y==(M-1)) continue;
				dice_y++;
				east();
			}else if(K==2) {
				//System.out.println("WEST");
				if(dice_y==0) continue;
				dice_y--;
				west();
			}else if(K==3) {
				//System.out.println("NORTH");
				if(dice_x==0) continue;
				dice_x--;
				north();
			}else if(K==4) {
				//System.out.println("SOUTH");
				if(dice_x==(N-1)) continue;
				dice_x++;
				south();
			}
			//System.out.println("dicex: "+dice_x+" dicey: "+dice_y);
			
			if(map[dice_x][dice_y]==0) {
				map[dice_x][dice_y]=dice[6];
			}else {
				dice[6]=map[dice_x][dice_y];
				map[dice_x][dice_y]=0;
			}
			System.out.println(dice[1]);
		}
		

	}
	
	static void east() {
		for(int i=1; i<=6; i++) {
			dice_copy[i]=dice[i];
		}
		dice[1]=dice_copy[4]; dice[2]=dice_copy[2]; dice[3]=dice_copy[1];
		dice[4]=dice_copy[6]; dice[5]=dice_copy[5]; dice[6]=dice_copy[3];
	}
	
	static void west() {
		for(int i=1; i<=6; i++) {
			dice_copy[i]=dice[i];
		}
		dice[1]=dice_copy[3]; dice[2]=dice_copy[2]; dice[3]=dice_copy[6];
		dice[4]=dice_copy[1]; dice[5]=dice_copy[5]; dice[6]=dice_copy[4];
	}
	
	static void north() {
		for(int i=1; i<=6; i++) {
			dice_copy[i]=dice[i];
		}
		dice[1]=dice_copy[5]; dice[2]=dice_copy[1]; dice[3]=dice_copy[3];
		dice[4]=dice_copy[4]; dice[5]=dice_copy[6]; dice[6]=dice_copy[2];
	}
	
	static void south() {
		for(int i=1; i<=6; i++) {
			dice_copy[i]=dice[i];
		}
		dice[1]=dice_copy[2]; dice[2]=dice_copy[6]; dice[3]=dice_copy[3];
		dice[4]=dice_copy[4]; dice[5]=dice_copy[1]; dice[6]=dice_copy[5];
	}

}
