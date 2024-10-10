import java.util.StringTokenizer;
import java.lang.StringBuilder;

class Solution {
    public String solution(String s) {
        String answer = "";
        int max=Integer.MIN_VALUE;
        int min=Integer.MAX_VALUE;
        StringTokenizer st=new StringTokenizer(s);
        while(st.hasMoreTokens()){
            String tmp=st.nextToken();
            int t=Integer.parseInt(tmp);
            //System.out.println(t);
            if(t>max){
                max=t;
            }
            if(t<min){
                min=t;
            }
        }
        StringBuilder sb=new StringBuilder();
        sb.append(min).append(' ').append(max);
        answer=sb.toString();
        return answer;
    }
}