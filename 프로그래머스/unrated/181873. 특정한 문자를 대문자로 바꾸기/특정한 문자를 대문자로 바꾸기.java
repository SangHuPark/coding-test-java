class Solution {
    public String solution(String my_string, String alp) {
        // if (my_string.contains(alp)) {
        //     my_string = my_string.replaceAll(alp, alp.toUpperCase());
        // }
        
        my_string = my_string.replaceAll(alp, alp.toUpperCase());
        return my_string;
    }
}