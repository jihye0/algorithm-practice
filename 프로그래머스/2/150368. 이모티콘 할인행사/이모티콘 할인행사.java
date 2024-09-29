class Solution {
    static int max_users;
    static int max_money;

    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];
        int[] sale=new int[emoticons.length];
        max_users=0;
        max_money=0;
        comb(0, sale, users, emoticons);
        answer[0]=max_users;
        answer[1]=max_money;
        return answer;
    }
    
    public void comb(int index, int[] sale, int[][] users, int[] emoticons){
        if(index==emoticons.length){
            calculate(sale, users, emoticons);
            return;
        }
        
        for(int i=10; i<=40; i+=10){
            sale[index]=i;
            comb(index+1, sale, users, emoticons);
        }
    }
    
    public void calculate(int[] sale, int[][] users, int[] emoticons){
        int c_users=0;
        int c_money=0;
        for(int i=0; i<users.length; i++){
            //int a=users[i][0]; //할인율이 a 이상이면 산다
            //int b=users[i][1]; //구매액이 b 이상이면 서비스에 가입한다
            int check=0;
            for(int j=0; j<emoticons.length; j++){
                if(sale[j]>=users[i][0]){
                    check+=emoticons[j]*(100-sale[j])/100;
                }
            }
            if(check>=users[i][1]) c_users++;
            else c_money+=check;
        }
        if(c_users>max_users){
            max_users=c_users;
            max_money=c_money;
        }else if(c_users==max_users){
            if(c_money>max_money) max_money=c_money;
        }
        
        
    }
    
    
    
}