import java.util.Arrays;
class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;
        boolean[][] map=new boolean[n+1][n+1];
        for(int i=0; i<results.length; i++){
            int a=results[i][0];
            int b=results[i][1];
            map[a][b]=true;
        }
        for(int k=1; k<=n; k++){
            for(int i=1; i<=n; i++){
                for(int j=1; j<=n; j++){
                    if(map[i][k]==true && map[k][j]==true) map[i][j]=true;
                }
            }
        }
        int[] count = new int[n+1];
        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                if(map[i][j]){
                    count[i]++;
                    count[j]++;
                }
            }
        }
        System.out.println(Arrays.toString(count));
        for(int i=1; i<=n; i++){
            if(count[i]==(n-1)) answer++;
        }
        return answer;
    }
}