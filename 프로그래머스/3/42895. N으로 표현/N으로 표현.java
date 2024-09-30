import java.util.*;

class Solution {
    
    public int solution(int N, int number) {
        Set<Integer>[] setArray=new Set[9];
        
        for(int i=0; i<9; i++){
            setArray[i]=new HashSet<Integer>();
        }
        
        setArray[1].add(N);
        if(number==N){
            return 1;
        }
        
        for(int i=2; i<9; i++){
            for(int j=1; j<i; j++){
                Set<Integer> set1=setArray[j];
                Set<Integer> set2=setArray[i-j];
                
                for(int n1: set1){
                    for(int n2: set2){
                        setArray[i].add(n1+n2);
                        setArray[i].add(n1*n2);
                        setArray[i].add(n1-n2);
                        if(n2!=0) setArray[i].add(n1/n2);
                    }
                }
            }
            
            String n=Integer.toString(N);
            String tmp="";
            for(int j=0; j<i; j++){
                tmp+=n;
            }
            
            setArray[i].add(Integer.parseInt(tmp));
            
            if(setArray[i].contains(number)) return i;
            
            //System.out.println(setArray[i].toString());
        }
        return -1;
        
    }
    

}