import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    public String solution(String number, int k) {
        String answer = "";
        char[] arr=number.toCharArray();
        //System.out.println(Arrays.toString(arr));
        int k2=number.length()-k;
        int start=0;
        StringBuilder sb=new StringBuilder();
        for(int i=0; i<k2; i++){
            char max='0';
            for(int j=start; j<=k+i; j++){
                if(max<arr[j]){
                    max=arr[j];
                    start=j+1;
                }
            }
            sb.append(max);
            
        }

        answer=sb.toString();
        return answer;
    }
}