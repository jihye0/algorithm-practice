import java.util.StringTokenizer;
import java.util.HashMap;

class Solution {
    public String[] solution(String[] record) {
        String[] answer = {};
        HashMap<String, String> idMap = new HashMap<>();
        int count=0;
        for(int i=0; i<record.length; i++){
            StringTokenizer st=new StringTokenizer(record[i]);
            String cmd=st.nextToken();
            if(cmd.equals("Enter")){
                String id=st.nextToken();
                String nickname=st.nextToken();
                
                idMap.put(id, nickname);
            
            }else if(cmd.equals("Leave")){
                String id=st.nextToken();
                
                continue;
                
            }else if(cmd.equals("Change")){
                String id=st.nextToken();
                String nickname=st.nextToken();
                
                idMap.replace(id, nickname);
                count++;
            }
        }
        
        answer=new String[record.length-count];
        int idx=0;
        
        for(int i=0; i<record.length; i++){
            StringTokenizer st=new StringTokenizer(record[i]);
            String cmd=st.nextToken();
            if(cmd.equals("Enter")){            
                String id=st.nextToken();
                answer[idx]=idMap.get(id)+"님이 들어왔습니다.";
                idx++;
            }else if(cmd.equals("Leave")){
                String id=st.nextToken();
                answer[idx]=idMap.get(id)+"님이 나갔습니다.";
                idx++;
            }else if(cmd.equals("Change")){
                continue;
            }
        }
        return answer;
    }
}