import java.util.Arrays;
import java.util.ArrayList;

class Solution {
    public static ArrayList<int[]> arr=new ArrayList<>();
    
    public int[][] solution(int n) {
        int[][] answer = {};
        move(n, 1, 2, 3);
        answer=new int[arr.size()][2];
        for(int i=0; i<arr.size(); i++){
            answer[i][0]=arr.get(i)[0];
            answer[i][1]=arr.get(i)[1];
        }
        return answer;
    }
    
    private static void move(int cnt, int start, int mid, int end) {
        if (cnt == 0) {
            return;
        }
        move(cnt - 1, start, end, mid);
        arr.add(new int[]{start, end});
        move(cnt - 1, mid, start, end);
    }
}