class Solution {
    public int solution(int[][] triangle) {
        int[][] dp=new int[triangle.length][triangle.length];
        int N=triangle.length;
        dp[0][0]=triangle[0][0];
        int ans=0;
        for(int a=1; a<N; a++){
            for(int b=0; b<=a; b++){
                if(b==0){
                    dp[a][b]=dp[a-1][0]+triangle[a][b];
                }else if(b==a){
                    dp[a][b]=dp[a-1][a-1]+triangle[a][b];
                }else{
                    dp[a][b]=Math.max(dp[a-1][b-1],dp[a-1][b])+triangle[a][b];
                }
                ans=Math.max(ans, dp[a][b]);
            }
            //ans=Math.max(ans, dp[a][b]);
        }
        return ans;
    }
}