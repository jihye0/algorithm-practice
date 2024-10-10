import java.util.Arrays;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 1;
        Arrays.sort(people);
        System.out.println(Arrays.toString(people));
        int p1=0;
        int p2=people.length-1;
        int size=limit;
        int count=0;
        while(p1<=p2){
           // System.out.println("p1: "+p1+" p2: "+p2+" size: "+size);
            if(count==2 && size>=people[p1]){
                count=0;
                answer++;
                size=limit;
            }
            
            if(people[p2]<=size){
                size-=people[p2];
                p2--;
                count++;
            }
            else if(people[p1]<=size){
                size-=people[p1];
                p1++;
                count++;
            }
            else{
                answer++;
                size=limit;
                count=0;
            }
            //System.out.println("p1: "+p1+" p2: "+p2+" size: "+size);
        }
        return answer;
    }
}