class Solution {
    public String solution(int[] numLog) {
        String str = new String();
        int num = 0;
        
        for (int i = 1; i < numLog.length; i++) {
            num = numLog[i] - numLog[i-1];
            
            // if (numLog[i-1] + 1 == numLog[i])
            //     str += "w";
            // else if (numLog[i-1] - 1 == numLog[i])
            //     str += "s";
            // else if (numLog[i-1] + 10 == numLog[i])
            //     str += "d";
            // else if (numLog[i-1] - 10 == numLog[i])
            //     str += "a";
            switch(num) {
                    case(1) : str += "w"; break;
                    case(-1) : str += "s"; break;
                    case(10) : str += "d"; break;
                    case(-10) : str += "a"; break;
                    default : break;
            }
        }
        
        return str;
    }
}