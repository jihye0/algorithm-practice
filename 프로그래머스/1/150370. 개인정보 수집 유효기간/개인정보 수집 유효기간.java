import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        ArrayList<Integer> answer = new ArrayList<Integer>();
        HashMap<String, Integer> termToMonth=new HashMap<>();
        for(int i=0; i<terms.length; i++){
            StringTokenizer st=new StringTokenizer(terms[i]);
            String name=st.nextToken();
            int month=Integer.parseInt(st.nextToken());
            termToMonth.put(name, month);
        }
        
        StringTokenizer st3=new StringTokenizer(today, ".");
        int t_year=Integer.parseInt(st3.nextToken());
        int t_month=Integer.parseInt(st3.nextToken());
        int t_day=Integer.parseInt(st3.nextToken());
        
        for(int i=0; i<privacies.length; i++){
            StringTokenizer st=new StringTokenizer(privacies[i]);
            String date=st.nextToken();
            String term=st.nextToken();
            
            int p_month=termToMonth.get(term);
            //내가 더해야할 년도와 달수
            int p_year=p_month/12;
            p_month=p_month%12;
            
            
            StringTokenizer st2=new StringTokenizer(date, ".");
            //개인정보수집일자
            int c_year=Integer.parseInt(st2.nextToken());
            int c_month=Integer.parseInt(st2.nextToken());
            int c_day=Integer.parseInt(st2.nextToken());
            
            boolean flag=false;
            if(c_month+p_month>12){
                int tmp_year=(c_month+p_month)/12; //더해야할 년수
                int tmp_month=(c_month+p_month)%12; //더해야할 달수
                p_year+=tmp_year;
                p_month=tmp_month;
                flag=true;
            }
            
            
            if(c_year+p_year<t_year) answer.add(i+1);
            else if((c_year+p_year)==t_year){
                if(flag){
                    if(p_month<t_month) answer.add(i+1);
                else if((p_month)==t_month){
                    if(c_day<=t_day) answer.add(i+1);
                }
                }else{
                if(c_month+p_month<t_month) answer.add(i+1);
                else if((c_month+p_month)==t_month){
                    if(c_day<=t_day) answer.add(i+1);
                }}
            }
            
        }
        
        
        int[] ansarr=new int[answer.size()];
        Collections.sort(answer);
        
        for(int i=0; i<answer.size(); i++){
            ansarr[i]=answer.get(i);
        }
        return ansarr;
    }
}