import java.util.PriorityQueue;
import java.util.Collections;

class Solution {

    public long solution(int n, int[] works) {
        long answer = 0;
        PriorityQueue<Integer> pq=new PriorityQueue<Integer>(Collections.reverseOrder());
        for(int i=-0; i<works.length; i++){
            pq.add(works[i]);
        }
        while(true){
            int a=pq.poll();
            if(a==0) break;
            n--;
            pq.add(a-1);
            if(n==0) break;
        }
        System.out.println(pq.toString());
        for(int item: pq){
            System.out.println(item);
            answer+=item*item;
        }
        return answer;
    }
}