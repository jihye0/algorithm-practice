import java.util.Arrays;

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        int N=targets.length;
        Arrays.sort(targets, (o1, o2) -> o1[1]-o2[1]);
        // for(int i=0; i<N; i++)
        //     System.out.println(Arrays.toString(targets[i]));
        int end=targets[0][1];
        answer++;
        for(int i=0; i<N; i++){
            if(targets[i][0]>=end){
                end=targets[i][1];
                answer++;
            }
        }
        return answer;
    }
}