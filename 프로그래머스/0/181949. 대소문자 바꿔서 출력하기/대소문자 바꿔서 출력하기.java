import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = sc.next();
        StringBuilder sb=new StringBuilder();
        for(int i=0; i<a.length(); i++){
            char check=a.charAt(i);
            if(Character.isUpperCase(check)){
                sb.append(Character.toLowerCase(check));
            }else{
                sb.append(Character.toUpperCase(check));
            }
        }
        System.out.println(sb);
    }
}