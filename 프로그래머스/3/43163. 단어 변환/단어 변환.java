import java.util.ArrayDeque;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        //words에 target이 있는지 체크
        boolean flag=false;
        for(int i=0; i<words.length; i++){
            if(target.equals(words[i])){
                flag=true;
                break;
            }
        }
        if(!flag) return 0;
        
        //adj node 만들기
        ArrayList<Integer>[] adj=new ArrayList[words.length];
        for(int i=0; i<words.length; i++){
            adj[i]=new ArrayList<>();
        }
        
        //boolean[] finish=new boolean[words.length];
        //인접노드 만들기
        for(int i=0; i<words.length; i++){
            String a=words[i];
            for(int j=i; j<words.length; j++){
                String b=words[j];
                int count=0;
                for(int k=0; k<a.length(); k++){
                    if(a.charAt(k)!=b.charAt(k)) count++;
                    if(count>2) break;
                }
                if(count==1){
                    adj[i].add(j);
                    adj[j].add(i);
                }
            }
            
        }
        
        //for(int i=0; i<words.length; i++) System.out.println(adj[i].toString());
        //System.out.println(Arrays.toString(finish));
        
        boolean[] visited=new boolean[words.length];
        Queue<int[]> q= new ArrayDeque<>();
        
        
        for(int i=0; i<words.length; i++){
            int count=0;
            String a=words[i];
            for(int k=0; k<a.length(); k++){
                if(a.charAt(k)!=begin.charAt(k)) count++;
                if(count>2) break;
            }
            if(count==1){
                q.add(new int[] {i, 1});
                visited[i]=true;
            }
        }
        
        answer=0;
        System.out.println(q.toString());
        while(!q.isEmpty()){
            int[] now=q.poll();
            if(words[now[0]].equals(target)){
                answer=now[1];
                break;
            }
            for(int i=0; i<adj[now[0]].size(); i++){
                if(!visited[adj[now[0]].get(i)]){
                    q.add(new int[] {adj[now[0]].get(i), now[1]+1});
                    visited[adj[now[0]].get(i)]=true;
                }
            }
        }
        
        
        
        return answer;
    }
}