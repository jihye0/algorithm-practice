import java.util.Arrays;

class Solution
{
    public int solution(int [][]board)
    {
        int answer=0;
        int r=board.length;
        int c=board[0].length;
        int[][] dp=new int[r][c];
        for(int i=0; i<c; i++){
            if(board[0][i]==1){
                dp[0][i]=1;
                answer=1;
            }
        }
        for(int i=0; i<r; i++){
            if(board[i][0]==1){
                dp[i][0]=1;
                answer=1;
            }
        }
        //int answer=0;
        for(int i=1; i<r; i++){
            for(int j=1; j<c; j++){
                if(board[i][j]==1){
                    dp[i][j]=1;
                }else{
                    continue;
                }
                int n=dp[i-1][j-1];
                if(n>0){
                    int m=Math.min(n, dp[i-1][j]);
                    m=Math.min(m, dp[i][j-1]);
                    dp[i][j]=m+1;
                    answer=Math.max(answer, dp[i][j]);
                }
            }
            
        }
        // for(int i=0; i<r; i++){
        //     System.out.println(Arrays.toString(dp[i]));
        // }
        answer=answer*answer;
        return answer;
    }
}