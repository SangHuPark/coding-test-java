import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        
        Map<String, Integer> partMap = new HashMap<>();
        for(String part : participant) {
            partMap.put(part, partMap.getOrDefault(part, 0) + 1);
        }
        for(String part : completion) {
            partMap.put(part, partMap.getOrDefault(part, 0) - 1);
        }
        
        for(Map.Entry<String, Integer> entry : partMap.entrySet()) {
            String key = entry.getKey();
            int value = entry.getValue();
            if(value == 1)
                return key;
        }
        
        return answer;
    }
}