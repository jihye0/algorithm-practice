class Solution {
    public boolean solution(String s) {
        boolean answer = true;
        if(s.length()==4 || s.length()==6){
            for(int i=0; i<s.length(); i++){
                int check=s.charAt(i)-'0';
                if(check<0 || check>9) return false;
            }
        }
        else return false;
        return answer;
    }
}