import java.util.HashMap;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        HashMap<String, Integer> map=new HashMap<>();
        for(int i=0; i<want.length; i++){
            map.put(want[i], number[i]);
        }
        int p1=0;
        int p2=0;
        for(int i=0; i<discount.length-10+1; i++){
            HashMap<String, Integer> dMap=new HashMap<>();
            
            for(int j=0; j<10; j++){
                dMap.put(discount[i+j], dMap.getOrDefault(discount[i+j], 0)+1);
            }
            
            boolean flag=false;
            for(String key: map.keySet()){
                if(map.get(key)!=dMap.get(key)){
                    flag=true;
                    break;
                }
            }
            if(!flag) answer++;
        }
        
        return answer;
    }
}