import java.util.StringTokenizer;
class Solution {
    public String solution(String s) {
        StringBuilder answer = new StringBuilder();
        String[] strArr = s.split(" ", -1);

        for (int i = 0; i < strArr.length; i++) {
            String word = strArr[i];
            for (int j = 0; j < word.length(); j++) {
                if (j % 2 == 0) {
                    answer.append(Character.toUpperCase(word.charAt(j)));
                } else {
                    answer.append(Character.toLowerCase(word.charAt(j)));
                }
            }
            // 단어 사이의 공백을 유지하기 위해, 마지막 단어가 아닐 경우 공백 추가
            if (i != strArr.length - 1) {
                answer.append(" ");
            }
        }

        return answer.toString();
    }
}