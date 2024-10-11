class Solution {
    static int[][] dp;
    static int len;
    static int answer;
    int solution(int[][] land) {
        answer=0;
        len=land.length;
        dp=new int[len][4];
        for(int i=0; i<4; i++){
            dp[0][i]=land[0][i];
        }
        for(int i=1; i<len; i++){
            for(int j=0; j<4; j++){
                dp[i][j]=land[i][j];
                for(int k=0; k<4; k++){
                    if(j==k) continue;
                    dp[i][j]=Math.max(dp[i][j], dp[i-1][k]+land[i][j]);
                }
            }
        }
        for(int i=0; i<4; i++){
            answer=Math.max(answer, dp[len-1][i]);
        }
        return answer;
    }
 
}