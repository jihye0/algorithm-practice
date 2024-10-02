import java.util.HashMap;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        HashMap<String, Integer> map1=new HashMap<>();
        int index=0;
        for(int i=0; i<gems.length; i++){
            if(map1.containsKey(gems[i])){
                
            }else{
                map1.put(gems[i], index);
                index++;
            }
        }
        int N=map1.size();
        int p1=0;
        int p2=0;
        int min=Integer.MAX_VALUE;;
        HashMap<String, Integer> map=new HashMap<>();
        while(p2<gems.length && p1<gems.length && p1<=p2){
            map.clear();
            while(p2<gems.length){
                if(!map.containsKey(gems[p2])){
                    map.put(gems[p2], 1);
                }else{
                    map.replace(gems[p2], map.get(gems[p2])+1);
                }
                if(map.size()==N){
                    break;
                }
                p2++;
            }
            if(p2==gems.length) break;
            while(p1<gems.length && p1<=p2 && map.size()==N){
                if(map.get(gems[p1])>=2){
                    map.replace(gems[p1], map.get(gems[p1])-1);
                    p1++;
                }else{
                    break;
                }
            }
            if(p2-p1<min){
                min=p2-p1;
                answer[0]=p1+1;
                answer[1]=p2+1;
                if(min==0) break;
            }
            p2=p1+1;
            p1=p1+1;
        }
        return answer;
    }
}