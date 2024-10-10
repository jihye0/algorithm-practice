//package S2023_2_A_1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Santa {
	int x;
	int y;
	boolean isDead;
	boolean isSleep;
	int awake_turn;
	int score;

	public Santa(int x, int y, boolean isDead, boolean isSleep, int awake_turn, int score) {
		super();
		this.x = x;
		this.y = y;
		this.isDead = isDead;
		this.isSleep = isSleep;
		this.awake_turn = awake_turn;
		this.score = score;
	}

	@Override
	public String toString() {
		return "Santa [x=" + x + ", y=" + y + ", isDead=" + isDead + ", isSleep=" + isSleep + ", awake_turn="
				+ awake_turn + ", score=" + score + "]";
	}

}

public class Main {
	static int N; // 게임판의 크기
	static int M; // 게임 턴 수
	static int P; // 산타의 수
	static int C; // 루돌프의 힘
	static int D; // 산타의 힘
	static int ru_x;
	static int ru_y;
	static Santa[] santa_list;

	static int[] dx = { -1, 0, 1, 0, -1, -1, 1, 1 };
	static int[] dy = { 0, 1, 0, -1, -1, 1, -1, 1 };
	static int now_turn;

	public static void main(String[] args) throws IOException {
		//BufferedReader br = new BufferedReader(new FileReader("src/S2023_2_A_1/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		ru_x = Integer.parseInt(st.nextToken());
		ru_y = Integer.parseInt(st.nextToken());
		santa_list = new Santa[P + 1];
		for (int i = 1; i <= P; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			santa_list[n] = new Santa(x, y, false, false, -1, 0);
		}
		
		int[][] map=new int[N+1][N+1];
		for (int i = 1; i <= P; i++) {
			if(santa_list[i].isDead) continue;
			map[santa_list[i].x][santa_list[i].y]=i;
		}
		map[ru_x][ru_y]=-1;
		// for(int i=0; i<=N; i++) System.out.println(Arrays.toString(map[i]));
		// System.out.println("===================================\n");

		for (int turn = 1; turn <= M; turn++) {
			now_turn = turn;
			// System.out.println("============"+now_turn+"==============");
			//  for(int i=0; i<=N; i++) System.out.println(Arrays.toString(map[i]));
			//  System.out.println("===================================\n");
			//System.out.println(santa_list[3].score);
			// 기절한 산타 살리기
			awake_santas();
			// 루돌 이동
			int d = ru_move();
			check_ru_attack(d);
			//  System.out.println("====AFTER RU ATTACK====");
			//  System.out.println("ru_x: " + ru_x + " ry_y: " + ru_y +" d: "+d);
			//  for (int i = 1; i <= P; i++) {
			//  	System.out.println(i+santa_list[i].toString());
			//  }
			// 산 이동
			santa_move();
			//  System.out.println("====AFTER SANTA MOVE====");
			//  for (int i = 1; i <= P; i++) {
			//  	System.out.println(i+santa_list[i].toString());
			//  }
			// 살아남은산타 점수 ++
			score_up();
			boolean alive_santa=false;
			for(int i=1; i<=P; i++) {
				if(!santa_list[i].isDead) {
					alive_santa=true;
					break;
				}
			}
			// System.out.println("====AFTER TURN"+turn+"====");
			// for (int i = 1; i <= P; i++) {
			// 	System.out.println(santa_list[i].toString());
			// }
			
			map=new int[N+1][N+1];
			for (int i = 1; i <= P; i++) {
				if(santa_list[i].isDead) continue;
				map[santa_list[i].x][santa_list[i].y]=i;
			}
			map[ru_x][ru_y]=-1;
			if(!alive_santa) {
				break;
			}
		}
		StringBuilder sb=new StringBuilder();
		for(int i=1; i<=P; i++) {
			sb.append(santa_list[i].score).append(" ");
		}
		System.out.println(sb);

	}
	
	static int reverse_dir(int i) {
		if(i==0) return 2;
		if(i==1) return 3;
		if(i==2) return 0;
		return 1;
	}

	static void santa_move() {
		for (int i = 1; i <= P; i++) {
			if (santa_list[i].isDead || santa_list[i].isSleep)
				continue;
			int now_dist = dist_from_ru(i);
			int move_dir = -1;
			for (int d = 0; d < 4; d++) {
				int nx = santa_list[i].x + dx[d];
				int ny = santa_list[i].y + dy[d];
				if (!onBoard(nx, ny)) {
					continue;
				}
				if (check_santa(nx, ny)) {
					continue;
				}
				int new_dist = dist_from_ru(nx, ny);
				if (new_dist < now_dist) {
					now_dist = new_dist;
					move_dir = d;
				}
			}
			if(move_dir!=-1) {
				santa_list[i].x += dx[move_dir];
				santa_list[i].y += dy[move_dir];
			}
			if (santa_list[i].x == ru_x && santa_list[i].y == ru_y) {
				santa_list[i].score += D;
				int attack_dir=reverse_dir(move_dir);
				int new_x = santa_list[i].x + dx[attack_dir] * D;
				int new_y = santa_list[i].y + dy[attack_dir] * D;
				if (!onBoard(new_x, new_y)) {
					santa_list[i].isDead = true;
					continue;
				}
				santa_move(i, attack_dir, new_x, new_y);
				santa_list[i].isSleep = true;
				santa_list[i].awake_turn = now_turn + 2;
			}
//			System.out.println("====AFTER SANTA"+i+" MOVE====");
//			for (int j = 1; j <= P; j++) {
//				System.out.println(santa_list[j].toString());
//			}
		}
	}
	

	static void ru_attack(int i, int d) {
		//System.out.println("===RU ATTACK== i: "+i+" d:"+d+"====");
		santa_list[i].score += C;
		int new_x = santa_list[i].x + dx[d] * C;
		int new_y = santa_list[i].y + dy[d] * C;
		if (!onBoard(new_x, new_y)) {
			santa_list[i].isDead = true;
			return;
		}
		// 상호작용 확인
		santa_move(i, d, new_x, new_y);
		// 기절
		santa_list[i].isSleep = true;
		santa_list[i].awake_turn = now_turn + 2;
	}

	static void santa_move(int n, int d, int new_x, int new_y) {
		//System.out.println("n: "+n+" d: "+d+" new_x: "+new_x+" new_y: "+new_y);
		if(!onBoard(new_x, new_y)) {
			santa_list[n].isDead=true;
			return;
		}
		santa_list[n].x = new_x;
		santa_list[n].y = new_y;
		for (int i = 1; i <= P; i++) {
			if(i==n) continue;
			if(santa_list[i].isDead) continue;
			if (santa_list[i].x == new_x && santa_list[i].y == new_y) {
				int nx = new_x + dx[d];
				int ny = new_y + dy[d];
				santa_move(i, d, nx, ny);
			}
		}
	}


	static boolean check_santa(int x, int y) {
		for (int i = 1; i <= P; i++) {
			if (santa_list[i].isDead)
				continue;
			if (santa_list[i].x == x && santa_list[i].y == y)
				return true;
		}
		return false;
	}

	static int dist_from_ru(int x, int y) {
		return (ru_x - x) * (ru_x - x) + (ru_y - y) * (ru_y - y);
	}

	static void score_up() {
		for (int i = 1; i <= P; i++) {
			if (!santa_list[i].isDead)
				santa_list[i].score++;
		}
	}

	static void awake_santas() {
		for (int i = 1; i <= P; i++) {
			if (santa_list[i].isSleep) {
				if (santa_list[i].awake_turn == now_turn) {
					santa_list[i].isSleep = false;
					santa_list[i].awake_turn = -1;
				}
			}
		}
	}

	static void check_ru_attack(int d) {
		for (int i = 1; i <= P; i++) {
			if (santa_list[i].isDead)
				continue;
			int sx = santa_list[i].x;
			int sy = santa_list[i].y;
			if (sx == ru_x && sy == ru_y) {
				ru_attack(i, d);
				break;
			}
		}
	}
	
	static int ru_move() {
		int target = find_target_santa();
		int ax = santa_list[target].x - ru_x;
		int ay = santa_list[target].y - ru_y;
		int d = -1;
		if (ax > 0) {
			if (ay > 0) {
				ru_x++;
				ru_y++;
				d = 7;
			} else if (ay == 0) {
				ru_x++;
				d = 2;
			} else {
				d = 6;
				ru_x++;
				ru_y--;
			}
		} else if (ax == 0) {
			if (ay > 0) {
				d = 1;
				ru_y++;
			} else {
				d = 3;
				ru_y--;
			}
		} else {
			if (ay > 0) {
				d = 5;
				ru_x--;
				ru_y++;
			} else if (ay == 0) {
				d = 0;
				ru_x--;
			} else {
				d = 4;
				ru_x--;
				ru_y--;
			}
		}
		return d;
	}

	static int find_target_santa() {
		int min_dist = Integer.MAX_VALUE;
		int target_santa = 0;
		for (int i = 1; i <= P; i++) {
			if (santa_list[i].isDead)
				continue;
			int now_dist = dist_from_ru(i);
			if (now_dist < min_dist) {
				min_dist = now_dist;
				target_santa = i;
			} else if (now_dist == min_dist) {
				if (santa_list[i].x > santa_list[target_santa].x) {
					target_santa = i;
				} else if (santa_list[i].x == santa_list[target_santa].x) {
					if (santa_list[i].y > santa_list[target_santa].y) {
						target_santa = i;
					}
				}
			}
		}

		return target_santa;
	}

	static int dist_from_ru(int i) {
		return (ru_x - santa_list[i].x) * (ru_x - santa_list[i].x)
				+ (ru_y - santa_list[i].y) * (ru_y - santa_list[i].y);
	}

	static boolean onBoard(int x, int y) {
		if (x <= 0 || x > N || y <= 0 || y > N)
			return false;
		return true;
	}

}
