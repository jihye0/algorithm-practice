import java.util.ArrayList;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.Arrays;

class Solution {
    public int solution(int n, int[][] edge) {
        int answer = 0;
        ArrayList<Integer>[] adj_list=new ArrayList[n+1];
        for(int i=0; i<=n; i++){
            adj_list[i]=new ArrayList<Integer>();
        }
        int[] step=new int[n+1];
        for(int i=0; i<edge.length; i++){
            int a=edge[i][0];
            int b=edge[i][1];
            adj_list[a].add(b);
            adj_list[b].add(a);
        }
        Queue<Integer> q=new ArrayDeque<>();
        int count=0;
        int max=1;
        step[1]=-1;
        for(int i=0; i<adj_list[1].size(); i++){
            step[adj_list[1].get(i)]=1;
            count++;
            q.add(adj_list[1].get(i));
        }

        while(count<=n){
            if(q.isEmpty()) break;
            int now=q.poll();
            for(int i=0; i<adj_list[now].size(); i++){
                int check=adj_list[now].get(i);
                if(step[check]==0){
                    step[check]=step[now]+1;
                    count++;
                    max=Math.max(step[check], max);
                    q.add(check);
                }
            }
        }
        System.out.println(Arrays.toString(step));
        for(int i=0; i<=n; i++){
            if(step[i]==max) answer++;
        }
        return answer;
    }
}