class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int total=brown+yellow;
        boolean flag=false;
        for(int y=3; y*y<=total; y++){
            if(total%y==0){
                //사각형이 되는 것임
                int x=total/y; //x가 가로 / y가 세로 4
                for(int i=1; (x-2*i)>=1; i++){
                    int tmp=x-2*i;
                    if(yellow%tmp==0){
                        int tmp2=yellow/tmp;
                        if((y-tmp2)%2==0){
                            if((y-tmp2)/2>=1){
                                answer[0]=x;
                                answer[1]=y;
                                flag=true;
                                return answer;
                            }
                        }
                    }
                }
            }
        }
        return answer;
    }
}