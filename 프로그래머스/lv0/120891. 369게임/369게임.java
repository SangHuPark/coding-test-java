class Solution {
    public int solution(int order) {
        int result = 0;
        String tmp = new StringBuilder().append(order).toString();
        
        for (char ch : tmp.toCharArray()) {
            if (ch == '3' || ch == '6' || ch == '9')
                result++;
        }
        
        return result;
    }
}