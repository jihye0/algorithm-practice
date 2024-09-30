import java.util.PriorityQueue;


class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        PriorityQueue<int[]> pq=new PriorityQueue<>((a, b) -> a[1]-b[1]);
        int time=0; //현재 시간
        boolean[] visited=new boolean[jobs.length]; //이미 큐에 있는지?
        int check=0;
        while(check<jobs.length){
            //System.out.println("====time: "+time);
            for(int i=0; i<jobs.length; i++){
                if(jobs[i][0]<=time && !visited[i]){ 
                    pq.add(jobs[i]);
                    visited[i]=true;}
            }
            
            if(pq.isEmpty()) time++;
            else{
                int[] now=pq.poll();
                time=time+now[1];
                answer+=time-now[0];
                check++;
                //System.out.println(answer);
            }
        }
        answer=answer/jobs.length;
        return answer;
    }
}