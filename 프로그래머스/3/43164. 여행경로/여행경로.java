import java.util.Arrays;

class Solution {
    int N;
    boolean[] visited;
    boolean find;
    int arr[];
    
    public String[] solution(String[][] tickets) {
        N=tickets.length;
        find=false;
        String[] answer = new String[N+1];
        visited=new boolean[N];
        arr=new int[N];
        
        Arrays.sort(tickets, (o1, o2)->{
            int first=o1[0].compareTo(o2[0]);
            if(first==0){
                return o1[1].compareTo(o2[1]);
            }else return first;
        });
        
        //
        for(int i=0; i<N; i++) System.out.println(Arrays.toString(tickets[i]));
        //
        
        for(int i=0; i<N; i++){
            if(tickets[i][0].equals("ICN")){
                arr[0]=i;
                visited[i]=true;
                dfs(1, tickets);
                if(find){
                    System.out.println(Arrays.toString(arr));
                    for(int j=0; j<N; j++){
                        answer[j]=tickets[arr[j]][0];
                    }
                    answer[N]=tickets[arr[N-1]][1];
                    return answer;
                }
                visited[i]=false;
            }
        }
        return answer;
    }
    
    public void dfs(int depth, String[][] tickets){
        if(find) return;
        if(depth==N){
            find=true;
            return;
        }
        String now=tickets[arr[depth-1]][1];
        for(int i=0; i<N; i++){
            if(now.equals(tickets[i][0]) && !visited[i]){
                arr[depth]=i;
                visited[i]=true;
                dfs(depth+1, tickets);
                visited[i]=false;
            }
        }
        return;
    }
}