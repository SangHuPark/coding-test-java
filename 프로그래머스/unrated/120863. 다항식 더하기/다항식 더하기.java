class Solution {
    public String solution(String polynomial) {
        String answer = new String();
        String[] strArr = polynomial.replaceAll("[ + ]", "/").split("///");
        int pri = 0;
        int con = 0;
        
        for (String str : strArr) {
            if (str.contains("x")) {
                pri += str.replace("x", "").equals("") ? 1
                    : Integer.parseInt(str.replace("x", ""));
            } else {
                con += Integer.parseInt(str);
            }
        }
        
        if (pri == 1)
            answer += "x";
        else if (pri != 0)
            answer += pri + "x";
        
        if (pri == 0 && con > 0)
            answer += con;
        else if (con > 0)
            answer += " + " + con;
        
        return answer;
    }
}