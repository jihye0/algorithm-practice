import java.util.HashMap;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        HashMap<String, Integer> NameToIndex=new HashMap<>();
        HashMap<Integer, String> IndexToName=new HashMap<>();
        for(int i=0; i<players.length; i++){
            NameToIndex.put(players[i], i);
            IndexToName.put(i, players[i]);
        }
        for(String calling: callings){
            int curIndex=NameToIndex.get(calling);
            if(curIndex==0) continue;
            String prevPlayer=IndexToName.get(curIndex-1);
            
            NameToIndex.replace(prevPlayer, curIndex);
            NameToIndex.replace(calling, curIndex-1);
            
            IndexToName.replace(curIndex, prevPlayer);
            IndexToName.replace(curIndex-1, calling);
        }
        String[] answer=new String[players.length];
        for(int i=0; i<players.length; i++){
            answer[NameToIndex.get(players[i])]=players[i];
        }
        return answer;
    }
}