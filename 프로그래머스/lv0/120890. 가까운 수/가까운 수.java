class Solution {
    public int solution(int[] array, int n) {
        int result = array[0];

        for ( int i = 1; i < array.length; i++ ) {
            int temp = array[ i ];
            int fix = Math.abs( result - n );
            int curr = Math.abs( temp - n );

            if (fix > curr || (fix == curr && result > temp)) {
                result = temp;
            }
        }
        
        return result;
    }
}