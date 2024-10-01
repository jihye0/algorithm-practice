import java.util.HashSet;
import java.util.Arrays;
import java.util.Queue;
import java.util.ArrayDeque;

class Solution {
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;
        int[][] map=new int[102][102];
        boolean[][] visited=new boolean[102][102];
        int[] dx={-1, 1, 0, 0};
        int[] dy={0, 0, -1, 1};

        for(int i=0; i<rectangle.length; i++){
            int ax=rectangle[i][0]*2;
            int ay=rectangle[i][1]*2;
            int bx=rectangle[i][2]*2;
            int by=rectangle[i][3]*2;
            for(int x=ax; x<=bx; x++){
                for(int y=ay; y<=by; y++){
                    map[x][y]=1;
                }
            }
        }
        
        for(int x=0; x<=101; x++){
            for(int y=0; y<=101; y++){
                if(map[x][y]==1){
                    int tmp=0;
                    for(int i=-1; i<2; i++){
                        for(int j=-1; j<2; j++){
                            int nx=x+i;
                            int ny=y+j;
                            if(map[nx][ny]==0){
                                map[x][y]=2; 
                                break;
                            }
                        }
                    }
                }
            }
        }
        
//         for(int i=0; i<25; i++){
//             for(int j=0; j<25; j++) {System.out.print(map[i][j]); System.out.print(' ');}
//             System.out.print('\n');
//         }
        
        //길찾기 시작
        Queue<int[]> q=new ArrayDeque<>();
        q.add(new int[] {2*characterX, 2*characterY, 0});
        visited[2*characterX][2*characterY]=true;
        while(!q.isEmpty()){
            int[] now=q.poll();
            if(now[0]==2*itemX && now[1]==2*itemY){
                answer= now[2];
                break;
            }
            for(int i=0; i<4; i++){
                int nx=now[0]+dx[i];
                int ny=now[1]+dy[i];
                if(nx<0 || nx>=102 || ny<0 || ny>=102) continue;
                if(visited[nx][ny] || map[nx][ny]==0 || map[nx][ny]==1) continue;
                visited[nx][ny]=true;
                q.add(new int[] {nx, ny, now[2]+1});
            }
        }
        
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++) {System.out.print(map[i][j]); System.out.print(' ');}
            System.out.print('\n');
        }
        
        return answer/2;
    }
    
    static void dfs(){
        
    }
}