import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer = {};
        answer=new int[photo.length];
        HashMap<String, Integer> h1 = new HashMap<String, Integer>( );
        for(int i=0; i<name.length; i++){
            h1.put(name[i], yearning[i]);
        }
        for(int i=0; i<photo.length; i++){
            int tmp=0;
            for(int j=0; j<photo[i].length; j++){
                if(h1.containsKey(photo[i][j])){
                    tmp+=h1.get(photo[i][j]);
                }
                //tmp+=h1.get(photo[i][j]);
            }
            answer[i]=tmp;
        }
        return answer;
    }
}