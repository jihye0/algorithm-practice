import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        //int[] answer = {};
        HashMap<String, Integer> total=new HashMap<>();
        HashMap<String, Integer> first=new HashMap<>();
        HashMap<String, Integer> second=new HashMap<>();
        for(int i=0; i<genres.length; i++){
            if(total.containsKey(genres[i])){
                total.replace(genres[i], total.get(genres[i])+plays[i]);
                if(plays[i]>plays[first.get(genres[i])]){
                    int tmp=first.get(genres[i]);
                    first.replace(genres[i], i);
                    second.replace(genres[i], tmp);
                }else if(second.get(genres[i])==-1){
                     second.replace(genres[i], i);
                }
                else if(plays[i]>plays[second.get(genres[i])]){
                    second.replace(genres[i], i);
                }
            }else{
                total.put(genres[i], plays[i]);
                first.put(genres[i], i);
                second.put(genres[i], -1);
            }
        }

        List<Map.Entry<String, Integer>> entryList=new ArrayList<>(total.entrySet());

        entryList.sort((e1, e2)->e2.getValue().compareTo(e1.getValue()));
        
        List<Integer> answer_list=new ArrayList<>();
        int index=0;
        
        for(int i=0; i<entryList.size(); i++){
            String tmp=entryList.get(i).getKey();
            answer_list.add(first.get(tmp));
            index++;
            if(second.get(tmp)!=-1){
                answer_list.add(second.get(tmp));
            }
        }
        System.out.println(answer_list.toString());
        int[] answer={};
        answer=new int[answer_list.size()];
        for(int i=0; i<answer.length; i++){
            answer[i]=answer_list.get(i);
        }
        return answer;
        
    }
}