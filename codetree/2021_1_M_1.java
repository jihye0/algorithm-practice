//package S2021_1_M_1;

import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static HashSet<Integer>[] friends;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Scanner로 변경
        N = sc.nextInt();
        map = new int[N][N];
        friends = new HashSet[N * N + 1];
        for (int i = 1; i < N * N + 1; i++) {
            friends[i] = new HashSet<Integer>();
        }
        int total_score = 0;
        for (int f = 0; f < N * N; f++) {
            int n = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            int d = sc.nextInt();
            friends[n].add(a);
            friends[n].add(b);
            friends[n].add(c);
            friends[n].add(d);
            int max_friends = 0;
            int max_blanks = 0;
            int max_x = 0;
            int max_y = 0;
            
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] != 0)
                        continue;
                    int[] info = count_info(i, j, n);
                    if (info[0] > max_friends) {
                        max_friends = info[0];
                        max_x = i; max_y = j;
                        max_blanks = info[1];
                    } else if (info[0] == max_friends) {
                        if (info[1] > max_blanks) {
                            max_blanks = info[1];
                            max_x = i; max_y = j;
                        }
                    }
                }
            }
            
            //System.out.println("max friends: "+max_friends+" max_blanks: "+max_blanks);
            //System.out.println("max x: "+max_x+" max y: "+max_y);

            if (max_friends == 0 && max_blanks == 0) {
                boolean flag = false;
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (map[i][j] == 0) {
                            map[i][j] = n;
                            flag = true;
                            break;
                        }
                    }
                    if (flag) break;
                }
            } else {
                map[max_x][max_y] = n;
            }
            // System.out.println("-----------------------");
            // for(int i=0; i<N; i++) System.out.println(Arrays.toString(map[i]));
            // System.out.println("-----------------------");
        }
        // System.out.println("-----------------------");
        // for(int i=0; i<N; i++) System.out.println(Arrays.toString(map[i]));
        // System.out.println("-----------------------");
        total_score = score();
        System.out.println(total_score);
        sc.close();
    }

    static int score() {
        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int person = map[i][j];
                int friends_count = 0;
                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];
                    if (!onBoard(nx, ny)) continue;
                    if (friends[person].contains(map[nx][ny])) {
                        friends_count++;
                    }
                }
                if (friends_count == 1) result += 1;
                else if (friends_count == 2) result += 10;
                else if (friends_count == 3) result += 100;
                else if (friends_count == 4) result += 1000;
            }
        }
        return result;
    }

    static int[] count_info(int x, int y, int n) {
        int[] result = new int[2];
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (!onBoard(nx, ny)) continue;
            if (map[nx][ny] == 0) {
                result[1]++;
            }
            if (friends[n].contains(map[nx][ny])) {
                result[0]++;
            }
        }
        return result;
    }

    static boolean onBoard(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}
