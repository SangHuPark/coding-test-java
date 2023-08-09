class Solution {
    public String solution(int[] numLog) {
        String str = new String();
        
        for (int i = 1; i < numLog.length; i++) {
            if (numLog[i-1] + 1 == numLog[i])
                str += "w";
            else if (numLog[i-1] - 1 == numLog[i])
                str += "s";
            else if (numLog[i-1] + 10 == numLog[i])
                str += "d";
            else if (numLog[i-1] - 10 == numLog[i])
                str += "a";
            // switch(numLog[i]) {
            //         case(numLog[i-1] + 1) : str += "w"; break;
            //         case(numLog[i-1] - 1) : str += "s"; break;
            //         case(numLog[i-1] + 10) : str += "d"; break;
            //         case(numLog[i-1] - 10) : str += "a"; break;
            //         default : break;
            // }
        }
        
        return str;
    }
}