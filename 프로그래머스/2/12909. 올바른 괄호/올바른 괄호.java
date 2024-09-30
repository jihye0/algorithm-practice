import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    boolean solution(String s) {
        boolean answer = true;
        
        Deque<Character> stack=new ArrayDeque<>();
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i)=='(') stack.push('(');
            else{
                if(stack.isEmpty()) return false;
                else if(stack.peek()=='('){
                    stack.pop();
                }
            }
        }
        if(!stack.isEmpty()) return false;
        return answer;
    }
}