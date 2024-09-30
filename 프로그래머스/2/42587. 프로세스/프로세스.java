import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        Queue<Integer> q=new ArrayDeque<>();
        PriorityQueue<Integer> pq=new PriorityQueue<>(Comparator.reverseOrder());
        for(int i=0; i<priorities.length; i++){
            q.add(priorities[i]);
            pq.add(priorities[i]);
        }
        System.out.println(pq.toString());
        while(true){
            if(location==0){
                if(q.peek()==pq.peek()){
                    answer++;
                    break;
                }
                else{
                    int tmp=q.poll();
                    q.add(tmp);
                    location=q.size()-1;
                }
            }
            else{
                if(q.peek()==pq.peek()){
                    answer++;
                    q.poll();
                    pq.poll();
                    location--;
                }else{
                    int tmp=q.poll();
                    q.add(tmp);
                    location--;
                }
            }
        }
        
        return answer;
    }
}