class Solution {
    public String solution(String rsp) {
        String[] arr = rsp.split("");
        String answer = new String();
        
        for (int i = 0; i < arr.length; i++) {
            
            switch(arr[i]) {
                case "2": answer += "0"; break;
                case "0": answer += "5"; break;
                case "5": answer += "2"; break;
                default: break;
            }
        }
        return answer;
    }
}