import java.util.PriorityQueue;
import java.util.Collections;

class Solution {
    static boolean[] visited;
    static int len;
    static int count;
    static int[] arr;
    
    public int solution(int[] cards) {
        int answer = 0;
        len=cards.length;
        visited=new boolean[len+1];
        arr=new int[len+1];
        for(int i=1; i<=len; i++){
            arr[i]=cards[i-1];
        }
        PriorityQueue<Integer> pq=new PriorityQueue<>(Collections.reverseOrder());
        for(int i=1; i<=len; i++){
            if(!visited[i]){
                count=0;
                dfs(i);
                //System.out.println(count);
                pq.add(count);
            }
        }
        if(pq.size()==1) return 0;
        answer=pq.poll()*pq.poll();
        return answer;
    }

    void dfs(int n){
        visited[n]=true;
        count++;
        int tmp=arr[n];
        if(!visited[tmp]){
            dfs(tmp);
        }
    }
}