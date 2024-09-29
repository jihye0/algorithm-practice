import java.util.HashMap;

class Solution {
    public int solution(String[][] clothes) {
        HashMap<String, Integer> map = new HashMap<>();
        
        // 옷의 종류별로 개수 카운팅
        for (String[] cloth : clothes) {
            String type = cloth[1];
            map.put(type, map.getOrDefault(type, 0) + 1);
        }
        
        int answer = 1;
        // 각 의상 종류마다 (해당 의상을 입는 경우 + 안 입는 경우)
        for (int count : map.values()) {
            answer *= (count + 1);
        }
        
        // 아무것도 입지 않는 경우 제외
        return answer - 1;
    }
}