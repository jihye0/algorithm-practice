import java.util.Arrays;

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        Arrays.sort(times);
        //System.out.println(Arrays.toString(times));
        //int[] arr=new int[times[times.length-1]*n+1];
        long left=0;
        long right=times[times.length-1]*(long)n;
        while(left<=right){
            long mid=(left+right)/2;
            long sum=0;
            for(int i=0; i<times.length; i++){
                sum+=mid/times[i];
            }
            if(sum<n){
                left=mid+1;
            }
            else{
                //모두 검사받았지만 최솟값이 있을 수 있음
                right=mid-1;
                answer=mid;
            }
        }
        return answer;
    }
}