import java.util.Arrays;
import java.util.ArrayDeque;
import java.util.Deque;


class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Deque<Integer> s=new ArrayDeque<Integer>();
        for(int i=0; i<prices.length; i++){
            //System.out.println(s.toString());
            if(s.isEmpty()){
                s.push(i);
            }else{
                int check=s.peek();
                while(true){
                    check=s.peek();
                    if(prices[check]<=prices[i]) break;
                    int a=s.pop();
                    answer[a]=i-a;
                    if(s.isEmpty()) break;
                }
                s.push(i);
            }
        }
        while(!s.isEmpty()){
            int a=s.poll();
            answer[a]=prices.length-1-a;
        }
        
        return answer;
    }
}