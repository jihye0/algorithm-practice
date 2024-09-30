import java.util.Arrays;

class Solution {
    public int solution(int[] citations) {
        // 오름차순 정렬
        Arrays.sort(citations);
        int N=citations.length;
        int max=citations[N-1];
        int[] arr=new int[max+1];
        System.out.println(Arrays.toString(citations));
        for(int i=0; i<=citations[0]; i++) arr[i]=N;
        for(int i=1; i<N; i++){
            //System.out.println(citations[i-1]);
            //System.out.println(citations[i]);
            for(int j=citations[i-1]+1; j<=citations[i]; j++){
                arr[j]=N-i;
            }
            //System.out.println(Arrays.toString(arr));
        }
        //arr[citations[N-1]]=1;
        System.out.println(Arrays.toString(arr));
        
        int answer = 0;
        for(int i=0; i<arr.length; i++){
            if(i<=arr[i]){
                answer=Math.max(answer, i);
            }
        }
        return answer;
    }
}